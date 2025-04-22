package DTO;

/**
 *
 * @author nguyen
 */
public class ChiTietSanPhamDTO {
    private String maImei;
    private int maSanpham;
    private int maPhieuNhap;
    private int maHoadon;
    private int tinhTrang; // Tình trạng sản phẩm
    private int trangThai; // Trạng thái (0: xóa mềm, 1: còn hiệu lực)

    public ChiTietSanPhamDTO() {
    }

    public ChiTietSanPhamDTO(String maImei, int maSanpham, int maPhieuNhap, int maHoadon, int tinhTrang, int trangThai) {
        this.maImei = maImei;
        this.maSanpham = maSanpham;
        this.maPhieuNhap = maPhieuNhap;
        this.maHoadon = maHoadon;
        this.tinhTrang = tinhTrang;
        this.trangThai = trangThai;
    }
    
    public String getMaImei() {
        return maImei;
    }

    public int getMaSanpham() {
        return maSanpham;
    }

    public int getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public int getMaHoadon() {
        return maHoadon;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setMaImei(String maImei) {
        this.maImei = maImei;
    }

    public void setMaSanpham(int maSanpham) {
        this.maSanpham = maSanpham;
    }

    public void setMaPhieuNhap(int maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public void setMaHoadon(int maHoadon) {
        this.maHoadon = maHoadon;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }


}
