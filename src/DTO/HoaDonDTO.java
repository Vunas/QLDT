/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.time.LocalDate;

/**
 *
 * @author nguyen
 */
public class HoaDonDTO {
    private int maHoaDon;
    private LocalDate ngayXuat;	
    private int maKH;
    private int maNhanVien;
    private int makhuyenmai;

    public HoaDonDTO() {
    }

    public HoaDonDTO(int maHoaDon, LocalDate ngayXuat, int maKH, int maNhanVien, int makhuyenmai) {
        this.maHoaDon = maHoaDon;
        this.ngayXuat = ngayXuat;
        this.maKH = maKH;
        this.maNhanVien = maNhanVien;
        this.makhuyenmai = makhuyenmai;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public LocalDate getNgayXuat() {
        return ngayXuat;
    }

    public int getMaKH() {
        return maKH;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public int getMakhuyenmai() {
        return makhuyenmai;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public void setNgayXuat(LocalDate ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public void setMakhuyenmai(int makhuyenmai) {
        this.makhuyenmai = makhuyenmai;
    }
  
    
    
}
