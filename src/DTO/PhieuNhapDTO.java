package DTO;

import java.time.LocalDate;

/**
 *
 * @author nguyen
 */
public class PhieuNhapDTO {
   private int maPhieuNhap;
   private LocalDate ngayNhap;
   private int maNhaCungCap;
   private int maNhanVien;
   private int trangThai; // Trạng thái (0: xóa mềm, 1: còn hiệu lực)

    public PhieuNhapDTO() {
    }

    public PhieuNhapDTO(int maPhieuNhap, LocalDate ngayNhap, int maNhaCungCap, int maNhanVien, int trangThai) {
        this.maPhieuNhap = maPhieuNhap;
        this.ngayNhap = ngayNhap;
        this.maNhaCungCap = maNhaCungCap;
        this.maNhanVien = maNhanVien;
        this.trangThai = trangThai;
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

    public int getTrangThai() {
        return trangThai;
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

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

}
