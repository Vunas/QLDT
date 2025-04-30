/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.PhieuBaoHanhDTO;
import java.sql.Date;
import java.time.LocalDate;
import util.JdbcUtil;
/**
 *
 * @author thaoh
 */
public class PhieuBaoHanhDAO {
// 
    public boolean add(PhieuBaoHanhDTO BaoHanh) {
        String query = "INSERT INTO phieubaohanh (maPhieuBH,ngayLap,maKhachHang,MaNhanVien,ghiChu,trangThai) VALUES (?, ?, ?, ?, ?, ?)"; 
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, BaoHanh.getMaPhieuBH());
            stmt.setDate(2, java.sql.Date.valueOf(BaoHanh.getNgayLap()));
            stmt.setInt(3, BaoHanh.getMaKH());
            stmt.setInt(4, BaoHanh.getMaNhanVien());
            stmt.setString(5, BaoHanh.getGhiChu());
            stmt.setInt(6, 1); // Mặc định trạng thái là còn hiệu lực

            return stmt.executeUpdate() > 0; // Trả về true nếu thêm thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Thêm thất bại
    }

    
    public boolean update(PhieuBaoHanhDTO BaoHanh) {
        String query = "UPDATE phieubaohanh SET ngayLap = ?, maKhachHang = ?, maNhanVien = ?,ghiChu = ? WHERE maPhieuBH = ? AND trangThai = 1";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDate(1,java.sql.Date.valueOf(BaoHanh.getNgayLap()));
            stmt.setInt(2, BaoHanh.getMaKH());
            stmt.setInt(3, BaoHanh.getMaNhanVien());
            stmt.setString(4, BaoHanh.getGhiChu());
            stmt.setInt(5, BaoHanh.getMaPhieuBH());
            stmt.setInt(6, BaoHanh.getTrangThai());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public boolean delete(int maPhieuBH) {
        String query = "UPDATE phieubaohanh SET trangThai = 0 WHERE maPhieuBH = ?";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, maPhieuBH);
            return stmt.executeUpdate() > 0; // Trả về true nếu xóa mềm thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //
    public PhieuBaoHanhDTO getPhieuBaoHanhById(int maPhieuBH) {
        String query = "SELECT * FROM phieubaohanh WHERE maPhieuBH = ? AND trangThai = 1";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, maPhieuBH);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    LocalDate ngayLap = rs.getDate("ngayLap").toLocalDate();
                    int maKH = rs.getInt("maKhachHang");
                    int MaNhanVien = rs.getInt("maNhanVien");
                    String ghiChu = rs.getString("ghiChu");
                    return new PhieuBaoHanhDTO(maPhieuBH, ngayLap, maKH, MaNhanVien, ghiChu,1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //
    public List<PhieuBaoHanhDTO> getAll() {
        List<PhieuBaoHanhDTO> BaoHanhList = new ArrayList<>();
        String query = "SELECT * FROM phieubaohanh WHERE trangThai = 1";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int maPhieuBH = rs.getInt("maPhieuBH");
                LocalDate ngayLap = rs.getDate("ngayLap").toLocalDate();
                int maKH = rs.getInt("maKhachHang");
                int MaNhanVien = rs.getInt("maNhanVien");
                String ghiChu = rs.getString("ghiChu");
                int trangThai = rs.getInt("trangThai");

                BaoHanhList.add(new PhieuBaoHanhDTO(maPhieuBH,ngayLap, maKH, MaNhanVien,ghiChu,trangThai));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BaoHanhList;
    }
    public List<PhieuBaoHanhDTO> getAllAbsolute() {
        List<PhieuBaoHanhDTO> BaoHanhList = new ArrayList<>();
        String query = "SELECT * FROM phieubaohanh";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int maPhieuBH = rs.getInt("maPhieuBH");
                LocalDate ngayLap = rs.getDate("ngayLap").toLocalDate();
                int maKH = rs.getInt("maKhachHang");
                int MaNhanVien = rs.getInt("maNhanVien");
                String ghiChu = rs.getString("ghiChu");
                int trangThai = rs.getInt("trangThai");

                BaoHanhList.add(new PhieuBaoHanhDTO(maPhieuBH,ngayLap, maKH, MaNhanVien,ghiChu,trangThai));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BaoHanhList;
    }
   
}
