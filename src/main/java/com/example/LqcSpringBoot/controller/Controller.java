package com.example.LqcSpringBoot.controller;

import com.example.LqcSpringBoot.mapper.CustomerlistMapper;
import com.example.LqcSpringBoot.mapper.ProductMapper;
import com.example.LqcSpringBoot.model.Customerlist;
import com.example.LqcSpringBoot.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 跳转page
 * liuqingchen 2023/12/09
 */
@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private CustomerlistMapper cl;
    @Autowired
    private ProductMapper pm;

    /**
     * 条件搜索
     */
    @RequestMapping("/index")
    public String seach() {
        return "index";
    }

    /**
     * 条件搜索 导航栏搜索
     */
    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "p", required = false) String pre) {
        model.addAttribute("p", pre);
        return "list";
    }


    /**
     * 全局搜索
     */
    @RequestMapping("/searchqj")
    @ResponseBody
    public List<Product> searchqj(String proname) {
        List<Product> list = new ArrayList<>();
        if (proname.isEmpty()) {
            return list;
        } else {
            list = pm.selectByNameLike(proname);
        }
        return list;
    }


    /**
     * 条件搜索 主页全局搜索
     */
    @RequestMapping("/qjlist")
    public String qjlist(Model model, String proname) {
        model.addAttribute("proname", proname);
        return "list";
    }


    /**
     * add customer message
     */
    @RequestMapping("/add")
    @ResponseBody
    public Integer add(Customerlist customerlist) {
        customerlist.setId(UUID.randomUUID().toString().replace("-", "").toString());
        int insert = cl.insert(customerlist);
        return insert;
    }

    /**
     * add customer message
     */
    @RequestMapping("/search")
    @ResponseBody
    public List<Product> search(Product product) {
        List<Product> list = pm.selectByName(product.getPtype());
        return list;
    }

    /**
     * add customer message
     */
    @RequestMapping("/zyqjsearch")
    @ResponseBody
    public List<Product> zyqjsearch(Product product) {
        List<Product> list = pm.selectByNameLike(product.getPname());
        return list;
    }


    /**
     * first page search (随机查询)
     * 暂时查询所有 等待前台建设好后进行添加条件
     */
    @RequestMapping("/search2")
    @ResponseBody
    public List<Product> searchFirctPage(Product product) {
        List<Product> list = pm.randsearch();
        return list;
    }

    /**
     * new product
     * 暂时查询所有 等待前台建设好后进行添加条件
     */
    @RequestMapping("/search3")
    @ResponseBody
    public List<Product> searchNewProduct(Product product) {
        List<Product> list = pm.selectnewproduct();
        return list;
    }

    /**
     * emp style
     * 暂时查询所有 等待前台建设好后进行添加条件
     */
    @RequestMapping("/search4")
    @ResponseBody
    public List<Product> emp(Product product) {
        List<Product> list = pm.selectEmpByTppe();
        return list;
    }

    /**
     * upload picture url
     * 后台照片上传
     */
    @RequestMapping("/upload")
    public String demo() {
        return "backstage";
    }

    /**
     * 后台保存
     */
    @RequestMapping("/addhtdata")
    @ResponseBody
    public Integer addhtdata(Product product) {
        product.setPname(product.getPname().substring(0, product.getPname().indexOf(".")));
        product.setId(UUID.randomUUID().toString());
        return pm.insert(product);
    }

    /**
     * 后台查询
     */
    @RequestMapping("/listdata")
    @ResponseBody
    public List<Product> listdata() {
        return pm.selectList(null);
    }

    /**
     * 后台删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Integer delete(Product product) {
        Integer count = pm.deleteById(product.getId());
        return count;
    }





}
