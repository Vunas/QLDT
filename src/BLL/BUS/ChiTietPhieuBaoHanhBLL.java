/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL.BUS;

import DAO.ChiTietPhieuBaoHanhDao;
import DTO.ChiTietPhieuBaoHanhDTO;
import java.util.List;
/**
 *
 * @author thaoh
 */
public class ChiTietPhieuBaoHanhBLL {
     private final ChiTietPhieuBaoHanhDao chitietphieubaohanhdao;

    public ChiTietPhieuBaoHanhBLL() {
        chitietphieubaohanhdao = new ChiTietPhieuBaoHanhDao(); 
    }

    public boolean add(ChiTietPhieuBaoHanhDTO ctbaohanh) {
        return chitietphieubaohanhdao.add(ctbaohanh); 
    }

    public boolean update(ChiTietPhieuBaoHanhDTO ctbaohanh) {
        return chitietphieubaohanhdao.update(ctbaohanh); 
    }
   
    public boolean delete(int maPhieuCT) {
        return chitietphieubaohanhdao.delete(maPhieuCT);
    }
    
    public boolean deleteChiTietBH(int maPhieuBH) {
        return chitietphieubaohanhdao.deleteByMaPhieuBH(maPhieuBH);
    }        
    public ChiTietPhieuBaoHanhDTO getCTBaoHanhById(int maPhieuCT) {
        return chitietphieubaohanhdao.getPhieuBaoHanhById(maPhieuCT);
    }
    
    public List<ChiTietPhieuBaoHanhDTO> getCTBaoHanhByMaPhieuBH(int maPhieuBH) {
        return chitietphieubaohanhdao.getPhieuBaoHanhByMaPhieuBH(maPhieuBH);
    }
    public List<ChiTietPhieuBaoHanhDTO> getCTBaoHanhByMaPhieuBHVaIMEI(int maPhieuBH , String maImei) {
        return chitietphieubaohanhdao.getCTBaoHanhByMaPhieuBHVaIMEI(maPhieuBH,maImei);
    }
    public List<ChiTietPhieuBaoHanhDTO> getAll() {
        return chitietphieubaohanhdao.getAll();
    }
    public List<ChiTietPhieuBaoHanhDTO> getAllAbsolute() {
        return chitietphieubaohanhdao.getAllAbsolute();
    }
    public int generateNewId() {
        List<ChiTietPhieuBaoHanhDTO> list = getAllAbsolute();
        if (list.isEmpty()) return 1;

        ChiTietPhieuBaoHanhDTO last = list.get(list.size() - 1);
        return last.getMaChiTiet() + 1;
    }

}


