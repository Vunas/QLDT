/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author nguyen
 */
public class ChiTietSanPhamDTO {
    private String maImei;
    private int maSanpham;
    private int maPhieuNhap;
    private int maHoadon;
    private int tinhTrang;

    public ChiTietSanPhamDTO() {
    }

    public ChiTietSanPhamDTO(String maImei, int maSanpham, int maPhieuNhap, int maHoadon, int tinhTrang) {
        this.maImei = maImei;
        this.maSanpham = maSanpham;
        this.maPhieuNhap = maPhieuNhap;
        this.maHoadon = maHoadon;
        this.tinhTrang = tinhTrang;
    }

    public String getMaImei() {
        return maImei;
    }

    public int getMaSanpham() {
        return maSanpham;
    }

    public int getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public int getMaHoadon() {
        return maHoadon;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setMaImei(String maImei) {
        this.maImei = maImei;
    }

    public void setMaSanpham(int maSanpham) {
        this.maSanpham = maSanpham;
    }

    public void setMaPhieuNhap(int maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public void setMaHoadon(int maHoadon) {
        this.maHoadon = maHoadon;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    @Override
    public String toString() {
        return "ChiTietSanPhamDTO{" + "maImei=" + maImei + ", maSanpham=" + maSanpham + ", maPhieuNhap=" + maPhieuNhap + ", maHoadon=" + maHoadon + ", tinhTrang=" + tinhTrang + '}';
    }
    
    
    
}
