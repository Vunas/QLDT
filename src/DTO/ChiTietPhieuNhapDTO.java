/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author nguyen
 */
public class ChiTietPhieuNhapDTO {
    private int maCTPhieuNhap;
    private int soLuong;
    private int donGia;
    private int maPhieuNhap;
    private int maSanPham;

    public ChiTietPhieuNhapDTO() {
    }

    public ChiTietPhieuNhapDTO(int maCTPhieuNhap, int soLuong, int donGia, int maPhieuNhap, int maSanPham) {
        this.maCTPhieuNhap = maCTPhieuNhap;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.maPhieuNhap = maPhieuNhap;
        this.maSanPham = maSanPham;
    }

    public int getMaCTPhieuNhap() {
        return maCTPhieuNhap;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public int getDonGia() {
        return donGia;
    }

    public int getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaCTPhieuNhap(int maCTPhieuNhap) {
        this.maCTPhieuNhap = maCTPhieuNhap;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public void setMaPhieuNhap(int maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }
    
    
}
