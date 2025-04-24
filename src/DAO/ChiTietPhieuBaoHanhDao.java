/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;



import DTO.ChiTietHoaDonDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.ChiTietPhieuBaoHanhDTO;
import java.sql.Date;
import util.JdbcUtil;
/**
 *
 * @author thaoh
 */
public class ChiTietPhieuBaoHanhDao {
    public boolean add(ChiTietPhieuBaoHanhDTO BaoHanh) {
        String query = "INSERT INTO chitietphieubaohanh (MaChiTiet,MaPhieuBH,MaSanPham,maIMEI,NgayBatDauBH,NgayKetThucBH,TrangThai,GhiChu) VALUES (?, ?, ?, ?, ?,?,?,?)";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, BaoHanh.getMaChiTiet());
            stmt.setString(2, BaoHanh.getMaPhieuBH());
            stmt.setInt(3, BaoHanh.getMaSanPham());
            stmt.setString(4,BaoHanh.getMaIMEI());
            stmt.setDate(5, new java.sql.Date(BaoHanh.getNgayBatDauBH().getTime()));
            stmt.setDate(6, new java.sql.Date(BaoHanh.getNgayKetThucBH().getTime()));
            stmt.setInt(7,BaoHanh.getTrangThai());
            stmt.setString(8,BaoHanh.getGhiChu());
            
            return stmt.executeUpdate() > 0; // Trả về true nếu thêm thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Thêm thất bại
    }

    
    public boolean update(ChiTietPhieuBaoHanhDTO BaoHanh) {
        String query = "UPDATE chitietphieubaohanh SET maIMEI = ? ,NgayBatDauBH = ?,NgayKetThucBH = ?,GhiChu = ? WHERE MaChiTiet = ? AND TrangThai = 1";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1,BaoHanh.getMaIMEI());
            stmt.setDate(2,(Date) BaoHanh.getNgayBatDauBH());
            stmt.setDate(3,(Date) BaoHanh.getNgayKetThucBH());// Mặc định trạng thái là còn hiệu lực
            stmt.setString(4,BaoHanh.getGhiChu());
            stmt.setInt(5,BaoHanh.getMaChiTiet());
            

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public boolean delete(int maChiTiet) {
        String query = "UPDATE chitietphieubaohanh SET TrangThai = 0 WHERE MaChiTiet = ?";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, maChiTiet);
            return stmt.executeUpdate() > 0; // Trả về true nếu xóa mềm thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

        public boolean deleteByMaPhieuBH(String maPhieuBH) {
        String query = "UPDATE chitietphieubaohanh SET TrangThai = 0 WHERE MaPhieuBH = ?";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, maPhieuBH);
            return stmt.executeUpdate() > 0; // Trả về true nếu xóa mềm thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    //
    public ChiTietPhieuBaoHanhDTO getPhieuBaoHanhById(int maChiTiet) {
        String query = "SELECT * FROM chitietphieubaohanh WHERE MaChiTiet = ? AND TrangThai = 1";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, maChiTiet);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String maPhieuBH = rs.getString("MaPhieuBH");
                    int maSP = rs.getInt("MaSanPham");
                    String maIMEI = rs.getString("maIMEI");
                    Date ngayBatDauBH = rs.getDate("NgayBatDauBH");
                    Date ngayKetThucBH = rs.getDate("NgayKetThucBH");
                    int trangThai = rs.getInt("TrangThai");
                    String ghiChu = rs.getString("GhiChu");
                    return new ChiTietPhieuBaoHanhDTO(maChiTiet,maPhieuBH,maSP,maIMEI, ngayBatDauBH,ngayKetThucBH, trangThai ,ghiChu);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<ChiTietPhieuBaoHanhDTO> getPhieuBaoHanhByMaPhieuBH(String maPhieuBH) {
        List<ChiTietPhieuBaoHanhDTO> list = new ArrayList<>();
        String query = "SELECT * FROM chitietphieubaohanh WHERE maPhieuBH LIKE ?";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + maPhieuBH + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int maChiTiet = rs.getInt("MaChiTiet");
                    int maSP = rs.getInt("MaSanPham");
                    String maIMEI = rs.getString("maIMEI");
                    Date ngayBatDauBH = rs.getDate("NgayBatDauBH");
                    Date ngayKetThucBH = rs.getDate("NgayKetThucBH");
                    int trangThai = rs.getInt("TrangThai");
                    String ghiChu = rs.getString("GhiChu");
                    list.add(new ChiTietPhieuBaoHanhDTO(maChiTiet, maPhieuBH, maSP, maIMEI, ngayBatDauBH, ngayKetThucBH, trangThai, ghiChu));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<ChiTietPhieuBaoHanhDTO> getCTBaoHanhByMaPhieuBHVaIMEI(String maPhieuBH , String maImei) {
        List<ChiTietPhieuBaoHanhDTO> list = new ArrayList<>();
        String query = "SELECT * FROM chitietphieubaohanh WHERE maPhieuBH LIKE ? AND maIMEI LIKE ?";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + maPhieuBH + "%");
            stmt.setString(2, "%" + maImei + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int maChiTiet = rs.getInt("MaChiTiet");
                    int maSP = rs.getInt("MaSanPham");
                    Date ngayBatDauBH = rs.getDate("NgayBatDauBH");
                    Date ngayKetThucBH = rs.getDate("NgayKetThucBH");
                    int trangThai = rs.getInt("TrangThai");
                    String ghiChu = rs.getString("GhiChu");
                    list.add(new ChiTietPhieuBaoHanhDTO(maChiTiet, maPhieuBH, maSP, maImei, ngayBatDauBH, ngayKetThucBH, trangThai, ghiChu));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    //
    public List<ChiTietPhieuBaoHanhDTO> getAll() {
        List<ChiTietPhieuBaoHanhDTO> BaoHanhList = new ArrayList<>();
        String query = "SELECT * FROM chitietphieubaohanh WHERE trangThai = 1";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int maChiTiet = rs.getInt("MaChiTiet");
                String maPhieuBH = rs.getString("MaPhieuBH");
                int maSP = rs.getInt("MaSanPham");
                String maIMEI = rs.getString("maIMEI");
                Date ngayBatDauBH = rs.getDate("NgayBatDauBH");
                Date ngayKetThucBH = rs.getDate("NgayKetThucBH");
                String ghiChu = rs.getString("GhiChu");
                int trangThai = rs.getInt("TrangThai");

                BaoHanhList.add(new ChiTietPhieuBaoHanhDTO(maChiTiet,maPhieuBH,maSP,maIMEI, ngayBatDauBH,ngayKetThucBH, trangThai ,ghiChu));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BaoHanhList;
    }
    public List<ChiTietPhieuBaoHanhDTO> getAllAbsolute() {
        List<ChiTietPhieuBaoHanhDTO> BaoHanhList = new ArrayList<>();
        String query = "SELECT * FROM chitietphieubaohanh";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int maChiTiet = rs.getInt("MaChiTiet");
                String maPhieuBH = rs.getString("MaPhieuBH");
                int maSP = rs.getInt("MaSanPham");
                String maIMEI = rs.getString("maIMEI");
                Date ngayBatDauBH = rs.getDate("NgayBatDauBH");
                Date ngayKetThucBH = rs.getDate("NgayKetThucBH");
                String ghiChu = rs.getString("GhiChu");
                int trangThai = rs.getInt("TrangThai");

                BaoHanhList.add(new ChiTietPhieuBaoHanhDTO(maChiTiet,maPhieuBH,maSP,maIMEI, ngayBatDauBH,ngayKetThucBH, trangThai ,ghiChu));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BaoHanhList;
    }
}
