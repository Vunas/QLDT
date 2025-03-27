/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author nguyen
 */
public class PhieuNhapDTO {
   private int maPhieuNhap;
   private LocalDate ngayNhap;
   private int maNhaCungCap;
   private int maNhanVien;

    public PhieuNhapDTO() {
    }

    public PhieuNhapDTO(int maPhieuNhap, LocalDate ngayNhap, int maNhaCungCap, int maNhanVien) {
        this.maPhieuNhap = maPhieuNhap;
        this.ngayNhap = ngayNhap;
        this.maNhaCungCap = maNhaCungCap;
        this.maNhanVien = maNhanVien;
    }

    public int getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public LocalDate getNgayNhap() {
        return ngayNhap;
    }

    public int getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaPhieuNhap(int maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public void setNgayNhap(LocalDate ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public void setMaNhaCungCap(int maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }
    
    
    
    
    
}
