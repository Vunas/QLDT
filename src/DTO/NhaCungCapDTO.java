package DTO;

public class NhaCungCapDTO {
    private int maNhaCungCap; // Mã nhà cung cấp
    private String ten;       // Tên nhà cung cấp
    private String diaChi;    // Địa chỉ nhà cung cấp
    private String sDT;       // Số điện thoại nhà cung cấp
    private int trangThai;    // Trạng thái (0: xóa mềm, 1: còn hiệu lực)

    public NhaCungCapDTO() {
    }
    
    public NhaCungCapDTO(int maNhaCungCap, String ten, String diaChi, String sDT, int trangThai) {
        this.maNhaCungCap = maNhaCungCap;
        this.ten = ten;
        this.diaChi = diaChi;
        this.sDT = sDT;
        this.trangThai = trangThai;
    }

    public int getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(int maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getsDT() {
        return sDT;
    }

    public void setsDT(String sDT) {
        this.sDT = sDT;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

}
