package DTO;

public class TaiKhoanDTO {
    private int maNV;            // Mã nhân viên
    private String tenDangNhap;  // Tên đăng nhập
    private String matKhau;      // Mật khẩu
    private int maQuyen;         // Mã quyền (quản lý, nhân viên, v.v.)

    private static TaiKhoanDTO taiKhoanHienTai;
    
    // Constructor không tham số
    public TaiKhoanDTO() {}
    

    // Constructor có tham số
    public TaiKhoanDTO(int maNV, String tenDangNhap, String matKhau, int maQuyen) {
        this.maNV = maNV;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.maQuyen = maQuyen;
    }

     public static TaiKhoanDTO getTaiKhoanHienTai() {
        return taiKhoanHienTai;
    }

    public static void setTaiKhoanHienTai(TaiKhoanDTO taiKhoan) {
        taiKhoanHienTai = taiKhoan;
    }
        
    // Getter và Setter cho từng thuộc tính
    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(int maQuyen) {
        this.maQuyen = maQuyen;
    }

    // Phương thức toString() để dễ dàng kiểm tra thông tin
    @Override
    public String toString() {
        return "TaiKhoanDTO{" +
                "maNV=" + maNV +
                ", tenDangNhap='" + tenDangNhap + '\'' +
                ", matKhau='***'" +  // Bảo mật mật khẩu khi hiển thị
                ", maQuyen=" + maQuyen +
                '}';
    }
}


