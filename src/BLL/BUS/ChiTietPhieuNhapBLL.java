/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL.BUS;

import DAO.ChiTietPhieuNhapDao;
import DTO.ChiTietPhieuNhapDTO;
import java.util.List;

/**
 *
 * @author nguyen
 */
public class ChiTietPhieuNhapBLL {
    private ChiTietPhieuNhapDao chiTietPhieuNhapDAO = new ChiTietPhieuNhapDao();
    
    public List<ChiTietPhieuNhapDTO> getChiTietPhieuNhapByPhieuNhap(int maPhieuNhap) {
        return chiTietPhieuNhapDAO.getChiTietPhieuNhapByMaPhieuNhap(maPhieuNhap);
    }

    public boolean addChiTietPhieuNhap(ChiTietPhieuNhapDTO chiTietPhieuNhap) {
        return chiTietPhieuNhapDAO.addChiTietPhieuNhap(chiTietPhieuNhap);
    }
    
    public boolean deleteChiTietPhieuNhap(int maPhieuNhap) {
        return chiTietPhieuNhapDAO.xoaMemCTPhieuNhap(maPhieuNhap);
    }
}
