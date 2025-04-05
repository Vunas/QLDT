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
    private int trangThai; // Trạng thái (0: xóa mềm, 1: còn hiệu lực)

    public ChiTietPhieuNhapDTO() {
    }

    public ChiTietPhieuNhapDTO(int maCTPhieuNhap, int soLuong, int donGia, int maPhieuNhap, int maSanPham, int trangThai) {
        this.maCTPhieuNhap = maCTPhieuNhap;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.maPhieuNhap = maPhieuNhap;
        this.maSanPham = maSanPham;
        this.trangThai = trangThai;
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

    public int getTrangThai() {
        return trangThai;
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

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

}
