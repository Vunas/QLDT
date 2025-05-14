package BLL.BUS;

import DAO.HoaDonDao;
import DTO.ChiTietHoaDonDTO;
import DTO.HoaDonDTO;
import util.HoaDonPDFExporter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nguyen
 */
public class HoaDonBLL {
    private HoaDonDao hoadondao = new HoaDonDao();

    public boolean addHoaDon(HoaDonDTO hd) {
        return hoadondao.addHoaDon(hd);
    }

    public List<HoaDonDTO> getAllHoaDon() {
        return hoadondao.getAllHoaDon();
    }

    public boolean deleteHoaDon(int mahoadon) {
        return hoadondao.xoaMemHoaDon(mahoadon);
    }

    public int getMaHoaDon() {
        return hoadondao.getMaHoaDon();
    }

    public HoaDonDTO getHoaDonById(int mahoadon) {
        return hoadondao.timHoaDon(mahoadon);
    }

    public void xuatHoaDonPDF(HoaDonDTO hoaDon) throws IOException {
        List<ChiTietHoaDonDTO> chiTietHoaDonDTO = new ChiTietHoaDonBLL().getChiTietTheoMaHoaDon(hoaDon.getMaHoaDon());
        try {
            HoaDonPDFExporter.xuatHoaDonPDF(hoaDon, chiTietHoaDonDTO);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi xuất hóa đơn! Kiểm tra font hoặc đường dẫn.");
        }
    }

}
