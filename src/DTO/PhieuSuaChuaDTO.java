/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.time.LocalDate;

/**
 *
 * @author thaoh
 */
public class PhieuSuaChuaDTO {
    private int maPhieuSC;
    private String maPhieuBH;
    private int maSanPham;
    private String maIMEI;
    private LocalDate ngayNhan;
    private String tinhTrang;
    private String xuLy;
    private String trangThai;
    private String ghiChu;

    public PhieuSuaChuaDTO(int maPhieuSC, String maPhieuBH, int maSanPham, String maIMEI, LocalDate ngayNhan, String tinhTrang, String xuLy, String trangThai, String ghiChu) {
        this.maPhieuSC = maPhieuSC;
        this.maPhieuBH = maPhieuBH;
        this.maSanPham = maSanPham;
        this.maIMEI = maIMEI;
        this.ngayNhan = ngayNhan;
        this.tinhTrang = tinhTrang;
        this.xuLy = xuLy;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
    }

    public int getMaPhieuSC() {
        return maPhieuSC;
    }

    public void setMaPhieuSC(int maPhieuSC) {
        this.maPhieuSC = maPhieuSC;
    }

    public String getMaPhieuBH() {
        return maPhieuBH;
    }

    public void setMaPhieuBH(String maPhieuBH) {
        this.maPhieuBH = maPhieuBH;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getMaIMEI() {
        return maIMEI;
    }

    public void setMaIMEI(String maIMEI) {
        this.maIMEI = maIMEI;
    }

    public LocalDate getNgayNhan() {
        return ngayNhan;
    }

    public void setNgayNhan(LocalDate ngayNhan) {
        this.ngayNhan = ngayNhan;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getXuLy() {
        return xuLy;
    }

    public void setXuLy(String xuLy) {
        this.xuLy = xuLy;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
    
}
