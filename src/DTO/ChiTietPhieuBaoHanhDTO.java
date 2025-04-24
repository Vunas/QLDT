/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author thaoh
 */
public class ChiTietPhieuBaoHanhDTO {
    private int maChiTiet;
    private String maPhieuBH;
    private int maSanPham;
    private String maIMEI;
    private Date ngayBatDauBH;
    private Date ngayKetThucBH;
    private int trangThai;
    private String ghiChu;

    public ChiTietPhieuBaoHanhDTO() {
     
    }
    
    public ChiTietPhieuBaoHanhDTO(int maChiTiet, String maPhieuBH, int maSanPham, String maIMEI, Date ngayBatDauBH, Date ngayKetThucBH, int trangThai, String ghiChu) {
        this.maChiTiet = maChiTiet;
        this.maPhieuBH = maPhieuBH;
        this.maSanPham = maSanPham;
        this.maIMEI = maIMEI;
        this.ngayBatDauBH = ngayBatDauBH;
        this.ngayKetThucBH = ngayKetThucBH;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
    }

    public int getMaChiTiet() {
        return maChiTiet;
    }

    public void setMaChiTiet(int maChiTiet) {
        this.maChiTiet = maChiTiet;
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

    public Date getNgayBatDauBH() {
        return ngayBatDauBH;
    }

    public void setNgayBatDauBH(Date ngayBatDauBH) {
        this.ngayBatDauBH = ngayBatDauBH;
    }

    public Date getNgayKetThucBH() {
        return ngayKetThucBH;
    }

    public void setNgayKetThucBH(Date ngayKetThucBH) {
        this.ngayKetThucBH = ngayKetThucBH;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.maChiTiet;
        hash = 11 * hash + Objects.hashCode(this.maPhieuBH);
        hash = 11 * hash + this.maSanPham;
        hash = 11 * hash + Objects.hashCode(this.maIMEI);
        hash = 11 * hash + Objects.hashCode(this.ngayBatDauBH);
        hash = 11 * hash + Objects.hashCode(this.ngayKetThucBH);
        hash = 11 * hash + this.trangThai;
        hash = 11 * hash + Objects.hashCode(this.ghiChu);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ChiTietPhieuBaoHanhDTO other = (ChiTietPhieuBaoHanhDTO) obj;
        if (this.maChiTiet != other.maChiTiet) {
            return false;
        }
        if (this.maSanPham != other.maSanPham) {
            return false;
        }
        if (this.trangThai != other.trangThai) {
            return false;
        }
        if (!Objects.equals(this.maPhieuBH, other.maPhieuBH)) {
            return false;
        }
        if (!Objects.equals(this.maIMEI, other.maIMEI)) {
            return false;
        }
        if (!Objects.equals(this.ghiChu, other.ghiChu)) {
            return false;
        }
        if (!Objects.equals(this.ngayBatDauBH, other.ngayBatDauBH)) {
            return false;
        }
        return Objects.equals(this.ngayKetThucBH, other.ngayKetThucBH);
    }

    @Override
    public String toString() {
        return "ChiTietPhieuBaoHanhDTO{" + "maChiTiet=" + maChiTiet + ", maPhieuBH=" + maPhieuBH + ", maSanPham=" + maSanPham + ", maIMEI=" + maIMEI + ", ngayBatDauBH=" + ngayBatDauBH + ", ngayKetThucBH=" + ngayKetThucBH + ", trangThai=" + trangThai + ", ghiChu=" + ghiChu + '}';
    }
    
}
