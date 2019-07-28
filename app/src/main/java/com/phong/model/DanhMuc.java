package com.phong.model;

import java.util.ArrayList;

public class DanhMuc {
    private String ma;
    private String ten;
    //private ArrayList <SanPham> sanPhams; để như này là bị Null
    private ArrayList <SanPham> sanPhams = new ArrayList<>();//Khởi tạo để ko bị Null

    public DanhMuc() {
    }

    public DanhMuc(String ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public ArrayList<SanPham> getSanPhams() {
        return sanPhams;
    }

    public void setSanPhams(ArrayList<SanPham> sanPhams) {
        this.sanPhams = sanPhams;
    }

    @Override
    public String toString() {
        return this.ma + " - " + this.ten;
    }
}
