package DTO;

import java.sql.Date;
import java.time.LocalDate;

public class ThongKeDTO {
    int ma;
    String ten;
    int soluong = 0;
    int tongGiaTri = 0;
    Date ngayXuat;
    LocalDate start;
    LocalDate end;
    
    public ThongKeDTO() {
    }
    
    public ThongKeDTO(int ma, String ten, int soluong) {
        this.ma = ma;
        this.ten = ten;
        this.soluong = soluong;
    }

    public ThongKeDTO(int tongGiaTri, Date ngayXuat) {
        this.tongGiaTri = tongGiaTri;
        this.ngayXuat = ngayXuat;
    }

    public ThongKeDTO(int ma, String ten, int soluong, int tongGiaTri) {
        this.ma = ma;
        this.ten = ten;
        this.soluong = soluong;
        this.tongGiaTri = tongGiaTri;
    }

    public ThongKeDTO(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public void setTongGiaTri(int tongGiaTri) {
        this.tongGiaTri = tongGiaTri;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public int getTongGiaTri() {
        return tongGiaTri;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getMa() {
        return ma;
    }

    public String getTen() {
        return ten;
    }

    public int gettongGiaTri() {
        return tongGiaTri;
    }

    public void settongGiaTri(int tongGiaTri) {
        this.tongGiaTri = tongGiaTri;
    }

    public Date getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(Date ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    public int getSoluong() {
        return soluong;
    }
    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
