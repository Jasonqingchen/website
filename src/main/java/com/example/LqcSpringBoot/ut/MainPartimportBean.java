package com.example.LqcSpringBoot.ut;

import com.example.LqcSpringBoot.mapper.ContainerMapper;
import com.example.LqcSpringBoot.model.Container;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * @author：liuqingchen
 * @since：2023/11/02
 */
@Component
public class MainPartimportBean {

    @Autowired
    public ContainerMapper cMapper;

    public  void insertDB(InputStream fileInputStream) {

        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            //HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);// 创建工作薄
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);// 得到工作表
            Row row = null;// 对应excel的行
            Cell cell = null;// 对应excel的列
            String Var="";
            row = sheet.getRow((short)0);


            int totalRow = sheet.getLastRowNum();// 得到excel的总记录条数
            String gnumber = "";//第1列
            String gysfs = "";//第2列
            String phone = "";//第3列
            String pol = "";//4列
            String pod = "";//第5列
            String gsize = "";//第6列
            String dls = "";//第7列
            String type = "";//第8列
            String count = "";//第9列
            String price = "";//第10列
            String chuanname = "";//第11列
            String cgdate = "";//第12列
            String dgdate = "";//第13列
            String dlgs = "";//第14列
            String status ="";//第 15 行
            String content  = "";//第16列
            int x =0;
            for (short i = 1; i <=totalRow; i++) {
                Container mp1 = new Container();
                cell = sheet.getRow(i).getCell((short)0);
                if(cell!=null){
                    sheet.getRow(i).getCell(0).setCellType(CellType.STRING);
                    gnumber = sheet.getRow(i).getCell(0).getStringCellValue();
                    mp1.setGnumber(gnumber);
                }
                cell = sheet.getRow(i).getCell((short)1);
                if(cell!=null){
                    sheet.getRow(i).getCell(1).setCellType(CellType.STRING);
                    gysfs =sheet.getRow(i).getCell(1).getStringCellValue();
                    mp1.setGysfs(gysfs);

                }
                cell = sheet.getRow(i).getCell((short)2);
                if(cell!=null){
                    sheet.getRow(i).getCell(2).setCellType(CellType.STRING);
                    phone =sheet.getRow(i).getCell(2).getStringCellValue();
                    mp1.setPhone(phone);

                }
                cell = sheet.getRow(i).getCell((short)3);
                if(cell!=null){
                    sheet.getRow(i).getCell(3).setCellType(CellType.STRING);
                    pol =sheet.getRow(i).getCell(3).getStringCellValue();
                    mp1.setPol(pol);

                }
                cell = sheet.getRow(i).getCell((short)4);
                if(cell!=null){
                    sheet.getRow(i).getCell(4).setCellType(CellType.STRING);
                    pod =sheet.getRow(i).getCell(4).getStringCellValue();
                    mp1.setPod(pod);

                }
                cell = sheet.getRow(i).getCell((short)5);
                if(cell!=null){
                    sheet.getRow(i).getCell(5).setCellType(CellType.STRING);
                    gsize =sheet.getRow(i).getCell(5).getStringCellValue();
                    mp1.setGsize(gsize);

                }
                cell = sheet.getRow(i).getCell((short)6);
                if(cell!=null){
                    sheet.getRow(i).getCell(6).setCellType(CellType.STRING);
                    dls =sheet.getRow(i).getCell(6).getStringCellValue();
                    mp1.setDls(dls);

                }
                cell = sheet.getRow(i).getCell((short)7);
                if(cell!=null){
                    sheet.getRow(i).getCell(7).setCellType(CellType.STRING);
                    type =sheet.getRow(i).getCell(7).getStringCellValue();
                    mp1.setType(type);

                }
                cell = sheet.getRow(i).getCell((short)8);
                if(cell!=null){
                    sheet.getRow(i).getCell(8).setCellType(CellType.STRING);
                    count =sheet.getRow(i).getCell(8).getStringCellValue();
                    mp1.setCount(count);

                }
                cell = sheet.getRow(i).getCell((short)9);
                if(cell!=null){
                    sheet.getRow(i).getCell(9).setCellType(CellType.STRING);
                    price =sheet.getRow(i).getCell(9).getStringCellValue();
                    mp1.setPrice(price);

                }
                cell = sheet.getRow(i).getCell((short)10);
                if(cell!=null){
                    sheet.getRow(i).getCell(10).setCellType(CellType.STRING);
                    chuanname =sheet.getRow(i).getCell(10).getStringCellValue();
                    mp1.setChuanname(chuanname);

                }
                cell = sheet.getRow(i).getCell((short)11);
                if(cell!=null){
                    sheet.getRow(i).getCell(11).setCellType(CellType.STRING);
                    cgdate =sheet.getRow(i).getCell(11).getStringCellValue();
                    Date date = org.apache.poi.ss.usermodel.DateUtil
                            .getJavaDate(Double.valueOf(cgdate));
                    mp1.setCgdate(format.format(date));

                }
                cell = sheet.getRow(i).getCell((short)12);
                if(cell!=null){
                    sheet.getRow(i).getCell(12).setCellType(CellType.STRING);
                    dgdate =sheet.getRow(i).getCell(12).getStringCellValue();
                    Date date = org.apache.poi.ss.usermodel.DateUtil
                            .getJavaDate(Double.valueOf(dgdate));
                    mp1.setDgdate(format.format(date));

                }
                cell = sheet.getRow(i).getCell((short)13);
                if(cell!=null){
                    sheet.getRow(i).getCell(13).setCellType(CellType.STRING);
                    dlgs =sheet.getRow(i).getCell(13).getStringCellValue();
                    mp1.setDlgs(dlgs);

                }
                cell = sheet.getRow(i).getCell((short)14);
                if(cell!=null){
                    sheet.getRow(i).getCell(14).setCellType(CellType.STRING);
                    status =sheet.getRow(i).getCell(14).getStringCellValue();
                    mp1.setStatus(status);

                }
                cell = sheet.getRow(i).getCell((short)15);
                if(cell!=null){
                    sheet.getRow(i).getCell(15).setCellType(CellType.STRING);
                    content =sheet.getRow(i).getCell(15).getStringCellValue();
                    mp1.setContent(content);
                }

                String gnum = cMapper.selectByGnumber(gnumber);
                if (gnum==null || gnum=="") {
                    mp1.setId(UUID.randomUUID().toString());
                    cMapper.insert(mp1);
                } else {
                    //修改 从用户体验感角度而言 需要
                    //先根据货柜号码查询出 id
                    String id = cMapper.selectByGnumber(gnumber);
                    //根据 id 修改
                    mp1.setId(id);
                    cMapper.updateById(mp1);

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
