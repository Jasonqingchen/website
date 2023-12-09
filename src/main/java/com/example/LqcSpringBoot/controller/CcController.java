package com.example.LqcSpringBoot.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.LqcSpringBoot.mapper.CcMapper;
import com.example.LqcSpringBoot.mapper.JxcMapper;
import com.example.LqcSpringBoot.mapper.RcMapper;
import com.example.LqcSpringBoot.model.Cctable;
import com.example.LqcSpringBoot.model.Jxctable;
import com.example.LqcSpringBoot.model.Rctable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * 出仓
 * liuqingchen 2023/10/31
 */
@Controller
@RequestMapping("/cc")
public class CcController {
    @Autowired
    public CcMapper cct;
    @Autowired
    public RcMapper rc;
    @Autowired
    public JxcMapper jxc;

    /**
     * 条件搜索
     */
    @RequestMapping("/seach_ht")
    @ResponseBody
    public List<Cctable> seach(@RequestParam Map map) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String start = null;
        String end = null;
        if(map.get("rcval1").toString().length()>0 && map.get("rcval2").toString().length()>0) {
            try {
                start = sdf.format(sdf.parse(map.get("rcval1").toString()));
                end = sdf.format(sdf.parse(map.get("rcval2").toString()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            };
        }
        List<Cctable> list = cct.selectKuserBysameting((String) map.get("orderid"), (String) map.get("customerphone"), start, end);
        return list;
    }

    /**
     * 删除
     */
    @RequestMapping("/del")
    @ResponseBody
    public Integer del(@RequestParam Map map) {
        Integer i = cct.deleteKuserById((String) map.get("id"));
        return i;
    }

    /**
     * 列表查询（）
     */
    @RequestMapping("/lists")
    @ResponseBody
    public List<Cctable> lists(@RequestParam Map map) {
        return cct.selectList();
    }

    /**
     * 添加出仓商品清单（出仓单）
     *
     * @param
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public String inser(@RequestParam Map map) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<Cctable> list = JSONObject.parseArray(map.get("list").toString(), Cctable.class);
        list.forEach(rcs -> {
            String d = map.get("ccdate").toString().substring(9, 11);
            String m = map.get("ccdate").toString().substring(6, 8);
            String y = map.get("ccdate").toString().substring(1, 5);

            if ("01".equals(m) || "03".equals(m) || "05".equals(m) || "07".equals(m) || "08".equals(m) || "10".equals(m) && "31".equals(d)) {
                m =String.valueOf(Integer.valueOf(m)+1);
                d = "01";
            }  else if("04".equals(m) || "06".equals(m) || "09".equals(m) || "11".equals(m) && "30".equals(d)) {
                    m =String.valueOf(Integer.valueOf(m)+1);
                    d = "01";
            } else if ("12".equals(m) && "31".equals(d)){
                d = "01";
                m = "01";
                y = String.valueOf(Integer.valueOf(y)+1);
            } else {
                d =String.valueOf(Integer.valueOf(d)+1);
            }
             if((Integer.valueOf(y) % 4 == 0 && Integer.valueOf(y) % 100 !=0) || (Integer.valueOf(y) % 400==0)) {
                //闰年
                if ("02".equals(m) && "29".equals(d)) {
                    m =String.valueOf(Integer.valueOf(m)+1);
                    d = "01";
                }
            } else {
                 //平年
                 if ("02".equals(m) && "28".equals(d)) {
                     m = String.valueOf(Integer.valueOf(m) + 1);
                     d = "01";
                 }
             }
            if (m.length()==1){
                m="0"+m;
            }
            if (d.length()==1){
                d="0"+d;
            }
            rcs.setCcdate(y+"-"+m+"-"+d);
            rcs.setCustomeraddress(map.get("customeraddress").toString());
            rcs.setCustomername(map.get("customername").toString());
            rcs.setCustomerphone(map.get("customerphone").toString());
            rcs.setOrderid(map.get("ccorder").toString());
            rcs.setCcsumprice(String.valueOf(Integer.parseInt(rcs.getCccount()) * Integer.parseInt(rcs.getCcprice().replace(" ", ""))));
            //新增
            rcs.setId(cn.hutool.core.lang.UUID.randomUUID().toString().replace("-", ""));
            cct.insert(rcs);
            //以下是对商品表的操作 减库存 重新计算 货物值
            Rctable rctable = rc.selectBYPnumber(rcs.getPnumber());
            String orldcount = rctable.getRccount();//入仓数量
            String cccount = rcs.getCccount().toString();//出库数量
            int newcount = Integer.parseInt(orldcount) - Integer.parseInt(cccount);
            rctable.setRccount(String.valueOf(newcount));//设置回新的库存量
            DecimalFormat df = new DecimalFormat("#.00");
            //计算单价成本总价
            double newprice = Double.valueOf(rctable.getCostprice()) * newcount;
            rctable.setCostcount(String.valueOf(newprice));
            rc.updateById(rctable);
            //对进销存表操作
            Jxctable jxctable = jxc.selectByPnamber(rcs.getPnumber());

           int newcccount =  Integer.valueOf((String)jxctable.getCccount()) + Integer.valueOf((String)rcs.getCccount());

            jxctable.setCccount(String.valueOf(newcccount));
            jxctable.setJccount(String.valueOf(Integer.valueOf((String)jxctable.getQcs()) + Integer.valueOf((String)jxctable.getRccount())- Integer.valueOf((String)jxctable.getCccount())));
            jxc.updateById(jxctable);
        });
        return "1";
    }
}
