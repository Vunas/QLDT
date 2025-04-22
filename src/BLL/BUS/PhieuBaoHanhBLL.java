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
   
    public boolean delete(String maPhieuBH) {
        return phieubaohanhdao.delete(maPhieuBH);
    }

    public PhieuBaoHanhDTO getBaoHanhById(String maPhieuBH) {
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
                        if (ctphieu.getMaPhieuBH().equals(bh.getMaPhieuBH()) && ctphieu.getMaIMEI().toLowerCase().contains(keyword.toLowerCase())) {
                            filteredList.add(bh);
                        }
                    }
                } 
                case "theo id phiếu BH" -> {
                    if (bh.getMaPhieuBH().toLowerCase().contains(keyword.toLowerCase())) {
                        filteredList.add(bh);
                    }
                }
            }
        }
        return filteredList;
    }

    public String generateNewId() {
        List<PhieuBaoHanhDTO> list = phieubaohanhdao.getAll();
        if (list.isEmpty()) {
            return "PBH01";
        }

        // Lấy phần tử cuối cùng
        String lastId = list.get(list.size() - 1).getMaPhieuBH(); // VD: PBH03
        int number = Integer.parseInt(lastId.replaceAll("[^0-9]", "")); // Lấy số: 3
        number++; // Tăng: 4

        // Format lại chuỗi ID
        return String.format("PBH%02d", number); // PBH04
    }

}
