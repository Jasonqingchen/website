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

import java.util.List;
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
     * 条件搜索
     */
    @RequestMapping("/list")
    public String list(Model model,@RequestParam(value = "p", required = false) String tv) {
        model.addAttribute("p",tv);
        return "list";
    }

    /**
     * add customer message
     */
    @RequestMapping("/add")
    @ResponseBody
    public Integer add (Customerlist customerlist){
       customerlist.setId(UUID.randomUUID().toString().replace("-", "").toString());
        int insert = cl.insert(customerlist);
        return insert;
    }

    /**
     * add customer message
     */
    @RequestMapping("/search")
    @ResponseBody
    public List<Product> search (Product product){
        List<Product> list = pm.selectByName(product.getType());
        return list;
    }

    /**
     * first page search (随机查询)
     * 暂时查询所有 等待前台建设好后进行添加条件
     */
    @RequestMapping("/search2")
    @ResponseBody
    public List<Product> searchFirctPage (Product product){
        List<Product> list = pm.randsearch();
        return list;
    }

    /**
     * new product
     * 暂时查询所有 等待前台建设好后进行添加条件
     */
    @RequestMapping("/search3")
    @ResponseBody
    public List<Product> searchNewProduct (Product product){
        List<Product> list = pm.selectnewproduct();
        return list;
    }

    /**
     * emp style
     * 暂时查询所有 等待前台建设好后进行添加条件
     */
    @RequestMapping("/search4")
    @ResponseBody
    public List<Product> emp (Product product){
        List<Product> list = pm.selectEmpByTppe();
        return list;
    }


}
