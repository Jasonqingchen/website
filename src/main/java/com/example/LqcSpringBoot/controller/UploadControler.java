package com.example.LqcSpringBoot.controller;

import com.jcraft.jsch.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * 上传图片到服务器指定路径
 */
@Controller
@RequestMapping("/upload")
public class UploadControler {
    @RequestMapping("/ups")
    @ResponseBody
    private Boolean saveFiles(MultipartFile file) throws FileNotFoundException {
        if (file != null) {
            String ip = "8.134.191.139";
            String user = "root";
            String pwd = "!Qingchen301626";
            String path = "/usr/local/src/apache-tomcat-8.5.97/webapps/image";
            JSch jsch = new JSch();
            try {
                //user 用户名 、ip、22为端口号
                Session session = jsch.getSession(user, ip, 22);
                //设置密码
                session.setPassword(pwd);
                session.setTimeout(60000);
                session.setConfig("userauth.gssapi-with-mic", "no");
                session.setConfig("StrictHostKeyChecking", "no");
                session.connect(5000);
                //设置传输方式
                Channel openChannel = session.openChannel("sftp");
                openChannel.connect();
                ChannelSftp sftp = (ChannelSftp) openChannel;
                try {
                    //创建文件流
                    InputStream ins = file.getInputStream();
                    sftp.cd(path);
                    //设置编码格式
                    String utf8 = new String(file.getOriginalFilename().getBytes(), "UTF-8");
                    sftp.put(ins, utf8);
                } catch (SftpException e) {
                    e.printStackTrace();
                }
                sftp.quit();
                if (sftp.isConnected()) {
                    sftp.disconnect();
                }
                if (session.isConnected()) {
                    session.disconnect();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
                System.exit(0);
            }

            /*try {
                // 文件保存路径
                String filePath = "D:\\photo\\"; // 映射的地址
                // String filePath =
                // request.getSession().getServletContext().getRealPath("upload/");本地项目路径

                String filename = file.getOriginalFilename();// 获取file图片名称

                uploadFile(file.getBytes(), filePath, filename);

                return true;

            } catch (Exception e) {
                e.printStackTrace();
            }*/
            return false;
        } else {
            return true;
        }
    }
}
