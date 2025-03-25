package DTO;

public class QuyenDTO {
    private int maQuyen; // Mã quyền
    private String tenQuyen; // Tên quyền
    private String danhSachChucNang; // Danh sách chức năng

    public QuyenDTO(){}

    public QuyenDTO(String tenQuyen, String danhSachChucNang) {
        this.tenQuyen = tenQuyen;
        this.danhSachChucNang = danhSachChucNang;
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
}
