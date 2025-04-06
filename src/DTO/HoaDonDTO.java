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
    private int trangThai; // Trạng thái (0: xóa mềm, 1: còn hiệu lực)

    public HoaDonDTO() {
    }

    public HoaDonDTO(int maHoaDon, LocalDate ngayXuat, int maKH, int maNhanVien, int makhuyenmai, int trangThai) {
        this.maHoaDon = maHoaDon;
        this.ngayXuat = ngayXuat;
        this.maKH = maKH;
        this.maNhanVien = maNhanVien;
        this.makhuyenmai = makhuyenmai;
        this.trangthai = trangthai;

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

   public int getTrangThai() {
        return trangThai;
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

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

}
