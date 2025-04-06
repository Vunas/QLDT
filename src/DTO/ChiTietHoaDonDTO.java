/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author nguyen
 */
public class ChiTietHoaDonDTO {
    private int maChiTietHoaDon;
    private int maHoaDon;
    private int maBaoHanh;
    private int maSanPham;
    private int soLuong;
    private int donGia;
    private int mabaohanh;
    private int trangThai;

    public ChiTietHoaDonDTO() {
    }

    public ChiTietHoaDonDTO(int maChiTietHoaDon, int maHoaDon, int maBaoHanh, int maSanPham, int soLuong, int donGia, int trangThai) {
        this.maChiTietHoaDon = maChiTietHoaDon;
        this.maHoaDon = maHoaDon;
        this.maBaoHanh = maBaoHanh;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.trangThai = trangThai;
    }

    public int getMaChiTietHoaDon() {
        return maChiTietHoaDon;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public int getMaBaoHanh() {
        return maBaoHanh;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public int getDonGia() {
        return donGia;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setMaChiTietHoaDon(int maChiTietHoaDon) {
        this.maChiTietHoaDon = maChiTietHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public void setMaBaoHanh(int maBaoHanh) {
        this.maBaoHanh = maBaoHanh;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    
    
    
}
