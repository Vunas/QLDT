package DTO;

public class QuyenDTO {
    private int maQuyen; // Mã quyền
    private String tenQuyen; // Tên quyền
    private String danhSachChucNang; // Danh sách chức năng
    private int trangThai; 

    // Constructor không tham số
    public QuyenDTO() {}

    // Constructor có tham số
    public QuyenDTO(String tenQuyen, String danhSachChucNang, int trangThai){
        this.danhSachChucNang = danhSachChucNang;
        this.trangThai = trangThai;
    }

    // Các phương thức getter và setter
    public int getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(int maQuyen) {
        this.maQuyen = maQuyen;
    }

    public String getTenQuyen() {
        return tenQuyen;
    }

    public void setTenQuyen(String tenQuyen) {
        this.tenQuyen = tenQuyen;
    }

    public String getDanhSachChucNang() {
        return danhSachChucNang;
    }

    public void setDanhSachChucNang(String danhSachChucNang) {
        this.danhSachChucNang = danhSachChucNang;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
