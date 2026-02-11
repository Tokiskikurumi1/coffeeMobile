package com.example.javatest.model;

public class Revenue {

    private String maHD;
    private String ngayTao;
    private double tongTien;

    public Revenue(String maHD, String ngayTao, double tongTien) {
        this.maHD = maHD;
        this.ngayTao = ngayTao;
        this.tongTien = tongTien;
    }

    public String getMaHD() {
        return maHD;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public double getTongTien() {
        return tongTien;
    }
}
