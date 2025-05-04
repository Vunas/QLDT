package DTO;

import java.sql.Date;

public class KhuyenMaiDTO {
    int maKM;
    String tenKM;
    int soLuong;
    Date ngayBD;
    Date ngayKT;
    int apDungChoHoaDonTu;
    int giaTri;
    int hinhThuc;
    int trangThai;
    String mota;

    public KhuyenMaiDTO(int maKM2, String string, Integer integer, Date date, Date date2, Integer integer2, Integer integer3, byte hinhthuc2, String string2, int i) {
    }

    public KhuyenMaiDTO(int maKM, String tenKM, int soLuong, Date ngayBD, Date ngayKT, int apDungChoHoaDonTu,
            int giaTri, int hinhThuc, int trangThai) {
        this.maKM = maKM;
        this.tenKM = tenKM;
        this.soLuong = soLuong;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.apDungChoHoaDonTu = apDungChoHoaDonTu;
        this.giaTri = giaTri;
        this.hinhThuc = hinhThuc;
        this.trangThai = trangThai;
    }

    public KhuyenMaiDTO(int maKM, String tenKM, int soLuong, Date ngayBD, Date ngayKT, int apDungChoHoaDonTu,
            int giaTri, int hinhThuc, int trangThai, String mota) {
        this.maKM = maKM;
        this.tenKM = tenKM;
        this.soLuong = soLuong;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.apDungChoHoaDonTu = apDungChoHoaDonTu;
        this.giaTri = giaTri;
        this.hinhThuc = hinhThuc;
        this.trangThai = trangThai;
        this.mota = mota;
    }

    public KhuyenMaiDTO(int maKM, String tenKM, int soLuong, Date ngayBD, Date ngayKT, int apDungChoHoaDonTu,
            int giaTri, int hinhThuc, String mota) {
        this.maKM = maKM;
        this.tenKM = tenKM;
        this.soLuong = soLuong;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.apDungChoHoaDonTu = apDungChoHoaDonTu;
        this.giaTri = giaTri;
        this.hinhThuc = hinhThuc;
        this.mota = mota;
    }

    public int getMaKM() {
        return maKM;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public Date getNgayBD() {
        return ngayBD;
    }

    public Date getNgayKT() {
        return ngayKT;
    }

    public int getApDungChoHoaDonTu() {
        return apDungChoHoaDonTu;
    }

    public int getGiaTri() {
        return giaTri;
    }

    public int getHinhThuc() {
        return hinhThuc;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setMaKM(int maKM) {
        this.maKM = maKM;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setNgayBD(Date ngayBD) {
        this.ngayBD = ngayBD;
    }

    public void setNgayKT(Date ngayKT) {
        this.ngayKT = ngayKT;
    }

    public void setApDungChoHoaDonTu(int apDungChoHoaDonTu) {
        this.apDungChoHoaDonTu = apDungChoHoaDonTu;
    }

    public void setGiaTri(int giaTri) {
        this.giaTri = giaTri;
    }

    public void setHinhThuc(int hinhThuc) {
        this.hinhThuc = hinhThuc;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getTenKM() {
        return tenKM;
    }

    public void setTenKM(String tenKM) {
        this.tenKM = tenKM;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

}
