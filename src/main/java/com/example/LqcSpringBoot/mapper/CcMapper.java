package com.example.LqcSpringBoot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.LqcSpringBoot.model.Cctable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 出仓库
 *
 */
@Mapper
public interface CcMapper extends BaseMapper<Cctable> {
    List<Cctable> selectList();
    Cctable  selectBYPnumber(String rccount);//商品入仓时 验证是否已经存在该商品编号

    List<Cctable> selectKuserBysameting(@Param("orderid") String orderid, @Param("customerphone") String customerphone, @Param("start") String start, @Param("end") String end);

    //删除
    Integer deleteKuserById(String id);

    String selectcountBYtable();//出库总数

    List selectListByyears();//每月出库数
}
