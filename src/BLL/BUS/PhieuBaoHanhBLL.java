/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL.BUS;

import DAO.ChiTietPhieuBaoHanhDao;
import DTO.PhieuBaoHanhDTO;
import java.util.ArrayList;
import java.util.List;
import DTO.KhachHangDTO;
import DAO.KhachHangDao;
import DAO.PhieuBaoHanhDAO;
import DTO.ChiTietPhieuBaoHanhDTO;

/**
 *
 * @author thaoh
 */
public class PhieuBaoHanhBLL {
    private final PhieuBaoHanhDAO phieubaohanhdao;

    public PhieuBaoHanhBLL() {
        phieubaohanhdao = new PhieuBaoHanhDAO(); 
    }

    public boolean add(PhieuBaoHanhDTO baohanh) {
        return phieubaohanhdao.add(baohanh); 
    }

    public boolean update(PhieuBaoHanhDTO baohanh) {
        return phieubaohanhdao.update(baohanh); 
    }
   
    public boolean delete(int maPhieuBH) {
        return phieubaohanhdao.delete(maPhieuBH);
    }

    public PhieuBaoHanhDTO getBaoHanhById(int maPhieuBH) {
        return phieubaohanhdao.getPhieuBaoHanhById(maPhieuBH);
    }

    public List<PhieuBaoHanhDTO> getAll() {
        return phieubaohanhdao.getAll();
    }
    
  
    public List<PhieuBaoHanhDTO> getBaoHanhByNameSearch(String keyword, String type) {
        List<PhieuBaoHanhDTO> baoHanhList = phieubaohanhdao.getAll();

        List<PhieuBaoHanhDTO> filteredList = new ArrayList<>();

        for (PhieuBaoHanhDTO bh : baoHanhList) {
            switch (type.toLowerCase()) {
                case "theo tên khách hàng" -> {
                    KhachHangDao khachHangdao = new KhachHangDao();
                    List<KhachHangDTO> khachhangList = khachHangdao.getAllKhachHang();
                    for(KhachHangDTO kh : khachhangList){
                        if (kh.getMaKH() == bh.getMaKH() && kh.getHoTen().toLowerCase().contains(keyword.toLowerCase())) {
                            filteredList.add(bh);
                        }
                    }
                }
                case "theo sdt khách hàng" -> {
                    KhachHangDao khachHangdao = new KhachHangDao();
                    List<KhachHangDTO> khachhangList = khachHangdao.getAllKhachHang();
                    for(KhachHangDTO kh : khachhangList){
                        if (kh.getMaKH() == bh.getMaKH() && kh.getSdt().toLowerCase().contains(keyword.toLowerCase())) {
                            filteredList.add(bh);
                        }
                    }
                }
                case "theo số IMEI/SERIAL" -> {
                    ChiTietPhieuBaoHanhDao chiTietPhieuBHdao = new ChiTietPhieuBaoHanhDao();
                    List<ChiTietPhieuBaoHanhDTO> ctList = chiTietPhieuBHdao.getAll();
                    for(ChiTietPhieuBaoHanhDTO ctphieu : ctList){
                        if ((ctphieu.getMaPhieuBH()==bh.getMaPhieuBH()) && ctphieu.getMaIMEI().toLowerCase().contains(keyword.toLowerCase())) {
                            filteredList.add(bh);
                        }
                    }
                } 
                case "theo id phiếu BH" -> {
                    if (bh.getMaPhieuBH()==Integer.parseInt(keyword.toLowerCase())) {
                        filteredList.add(bh);
                    }
                }
            }
        }
        return filteredList;
    }

    public int generateNewId() {
        List<PhieuBaoHanhDTO> list = phieubaohanhdao.getAllAbsolute();
        if (list.isEmpty()) {
            return 1;
        } else {
            return list.getLast().getMaPhieuBH() + 1;
        }
    }

}
