package DTO;

public class NhaCungCapDTO {
    int maNhaCungCap;
    String ten;
    String diaChi;
    String sDT;

    public NhaCungCapDTO() {
    }
    
    
    
    public NhaCungCapDTO(int maNhaCungCap, String ten, String diaChi, String sDT) {
        this.maNhaCungCap = maNhaCungCap;
        this.ten = ten;
        this.diaChi = diaChi;
        this.sDT = sDT;
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

    

    
}
