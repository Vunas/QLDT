/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author thaoh
 */
public class PhieuBaoHanhDTO {
    private int maPhieuBH;
    private LocalDate ngayLap;
    private int maKH;
    private String ghiChu;
    private int trangThai;
    private int MaNhanVien;

    public PhieuBaoHanhDTO() {
       
    }
    public PhieuBaoHanhDTO(int maPhieuBH, LocalDate ngayLap, int maKH, int MaNhanVien , String ghiChu, int trangThai) {
        this.maPhieuBH = maPhieuBH;
        this.ngayLap = ngayLap;
        this.maKH = maKH;
        this.MaNhanVien = MaNhanVien;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
    }

    public int getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(int MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public int getMaPhieuBH() {
        return maPhieuBH;
    }

    public void setMaPhieuBH(int maPhieuBH) {
        this.maPhieuBH = maPhieuBH;
    }

    public LocalDate getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDate ngayLap) {
        this.ngayLap = ngayLap;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.maPhieuBH);
        hash = 79 * hash + Objects.hashCode(this.ngayLap);
        hash = 79 * hash + this.maKH;
        hash = 79 * hash + Objects.hashCode(this.ghiChu);
        hash = 79 * hash + this.trangThai;
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
        final PhieuBaoHanhDTO other = (PhieuBaoHanhDTO) obj;
        if (this.maKH != other.maKH) {
            return false;
        }
        if (this.trangThai != other.trangThai) {
            return false;
        }
        if (!Objects.equals(this.maPhieuBH, other.maPhieuBH)) {
            return false;
        }
        if (!Objects.equals(this.ghiChu, other.ghiChu)) {
            return false;
        }
        return Objects.equals(this.ngayLap, other.ngayLap);
    }

    @Override
    public String toString() {
        return "PhieuBaoHanhDTO{" + "maPhieuBH=" + maPhieuBH + ", ngayLap=" + ngayLap + ", maKH=" + maKH + ", ghiChu=" + ghiChu + ", trangThai=" + trangThai + '}';
    }
    
    
}
