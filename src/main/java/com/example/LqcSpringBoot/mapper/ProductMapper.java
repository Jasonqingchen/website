package com.example.LqcSpringBoot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.LqcSpringBoot.model.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ProductMapper extends BaseMapper<Product>{
    List<Product> selectByName(String type);
    List<Product> selectnewproduct();
    List<Product> randsearch();
    List<Product> selectEmpByTppe();
    //全局搜索
    List<Product> selectByNameLike(String proname);





}
