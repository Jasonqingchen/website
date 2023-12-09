package com.example.LqcSpringBoot.model;

import java.io.Serializable;

public class Cctable implements Serializable {
    private String id;

    private String type;

    private String customername;

    private String customerphone;

    private String color;

    private String ccdate;

    private String cccount;

    private String ccprice;

    private String ccsumprice;

    private String bz;

    private String pname;

    private String pnumber;

    private String customeraddress;

    private String orderid;
    private String ccfs;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername == null ? null : customername.trim();
    }

    public String getCustomerphone() {
        return customerphone;
    }

    public void setCustomerphone(String customerphone) {
        this.customerphone = customerphone == null ? null : customerphone.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public String getCcdate() {
        return ccdate;
    }

    public void setCcdate(String ccdate) {
        this.ccdate = ccdate == null ? null : ccdate.trim();
    }

    public String getCccount() {
        return cccount;
    }

    public void setCccount(String cccount) {
        this.cccount = cccount == null ? null : cccount.trim();
    }

    public String getCcprice() {
        return ccprice;
    }

    public void setCcprice(String ccprice) {
        this.ccprice = ccprice;
    }

    public String getCcsumprice() {
        return ccsumprice;
    }

    public void setCcsumprice(String ccsumprice) {
        this.ccsumprice = ccsumprice;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber == null ? null : pnumber.trim();
    }

    public String getCustomeraddress() {
        return customeraddress;
    }

    public void setCustomeraddress(String customeraddress) {
        this.customeraddress = customeraddress == null ? null : customeraddress.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }


    public String getCcfs() {
        return ccfs;
    }

    public void setCcfs(String ccfs) {
        this.ccfs = ccfs;
    }
}