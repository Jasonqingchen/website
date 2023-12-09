package com.example.LqcSpringBoot.ut;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/kunyueye")
public class CheckCodeServlet {


    @RequestMapping("/url")
    public void getUrl(HttpSession session, HttpServletResponse response) throws ServletException, IOException {
        //禁止浏览器缓存验证码
        response.setDateHeader("Expires",-1);
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Pragma","no-cache");
        //生成验证码
        VerifyCode vc = new VerifyCode();
        //获取验证码的值，存储到session中
        vc.drawImage(response.getOutputStream());
        String valistr = vc.getCode();
        vc.drawImage(response.getOutputStream());
        session.setAttribute("valistr",valistr);
        //打印到控制台
       // System.out.println(valistr);
    }

}
