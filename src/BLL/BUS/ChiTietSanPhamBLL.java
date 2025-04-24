/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL.BUS;

import DAO.ChiTietSanPhamDao;
import DTO.ChiTietSanPhamDTO;
import java.util.List;

/**
 *
 * @author nguyen
 */
public class ChiTietSanPhamBLL {
    private ChiTietSanPhamDao chiTietSanPhamDao = new ChiTietSanPhamDao();
    
     public boolean addChiTietSanPham(ChiTietSanPhamDTO ctsp){
         return chiTietSanPhamDao.addChiTietSanPham(ctsp);
     }
     
     public boolean xacNhanDaBan(ChiTietSanPhamDTO ctsp){
         return chiTietSanPhamDao.xacNhanDaBan(ctsp);
     }

     public List<String> getImeisByPhieuNhapAndSanPham(int maphieunhap, int masanpham){
         return chiTietSanPhamDao.getImeisByPhieuNhapAndSanPham(maphieunhap, masanpham);
     }
     
     public List<String> getImeisByHoaDonAndSanPham(int mahoadon, int masanpham){
         return chiTietSanPhamDao.getImeisByHoaDonAndSanPham(mahoadon, masanpham);
     }
     public List<String> getImeisBySanPham(int masanpham){
         return chiTietSanPhamDao.getImeisBySanPham(masanpham);
     } 
    public boolean capNhatMaBaoHanh(String maIMEI , String maPBH){
         return chiTietSanPhamDao.capNhatMaBaoHanh(maIMEI,maPBH);
     }
    public int getSoLuongImeisBySanPham(int masp){
        return chiTietSanPhamDao.getSoLuongImeisBySanPham(masp);
    }
}
