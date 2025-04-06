package DTO;

public class KhachHangDTO {
    private int maKH;
    private String hoTen;
    private String diaChi;
    private String sdt;
    private int trangthai;

    public KhachHangDTO() {
    }
    
    

    // Constructor
    
    public KhachHangDTO(int maKH, String hoTen, String diaChi, String sdt, int trangthai) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.trangthai = trangthai;
    }

    public int getMaKH() {
        return maKH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
   
}
