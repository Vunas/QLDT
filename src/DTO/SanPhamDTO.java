package DTO;

public class SanPhamDTO {
    private int maSP;
    private String tenSP;
    private String img;
    private int soLuong;
    private int giaNhap;
    private int giaBan;
    private String mauSac;
    private String thuongHieu;
    private int Ram;
    private int Rom;
    private String Chip;
    private float thoiGianBaoHanh;
    private int trangThai; // Trạng thái (0: xóa mềm, 1: còn hiệu lực)

    public SanPhamDTO() {}

    public SanPhamDTO(int maSP, String tenSP, String img, int soLuong, int giaNhap, int giaBan, String mauSac, String thuongHieu, int Ram, int Rom, String Chip, float thoiGianBaoHanh, int trangThai) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.img = img;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.mauSac = mauSac;
        this.thuongHieu = thuongHieu;
        this.Ram = Ram;
        this.Rom = Rom;
        this.Chip = Chip;
        this.thoiGianBaoHanh = thoiGianBaoHanh;
        this.trangThai = trangThai;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(int giaNhap) {
        this.giaNhap = giaNhap;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(String thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public int getRam() {
        return Ram;
    }

    public void setRam(int Ram) {
        this.Ram = Ram;
    }

    public int getRom() {
        return Rom;
    }

    public void setRom(int Rom) {
        this.Rom = Rom;
    }

    public String getChip() {
        return Chip;
    }

    public void setChip(String Chip) {
        this.Chip = Chip;
    }

    public float getThoiGianBaoHanh() {
        return thoiGianBaoHanh;
    }

    public void setThoiGianBaoHanh(float thoiGianBaoHanh) {
        this.thoiGianBaoHanh = thoiGianBaoHanh;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

}
