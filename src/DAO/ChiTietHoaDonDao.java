package DAO;

import DTO.ChiTietHoaDonDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.JdbcUtil;

public class ChiTietHoaDonDao {

    public boolean addChiTietHoaDon(ChiTietHoaDonDTO cthd) {
        String sql = "INSERT INTO chitiethoadon(maHoaDon, maBaoHanh, maSanPham, soLuong, donGia, trangThai) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = JdbcUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, cthd.getMaHoaDon());
            stmt.setInt(2, cthd.getMaBaoHanh());
            stmt.setInt(3, cthd.getMaSanPham());
            stmt.setInt(4, cthd.getSoLuong());
            stmt.setInt(5, cthd.getDonGia());
            stmt.setInt(6, 1);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ChiTietHoaDonDTO> getChiTietHoaDonByMaHoaDon(int maHoaDon) {
        List<ChiTietHoaDonDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM chitiethoadon WHERE maHoaDon = ? AND trangThai = 1";
        try (Connection conn = JdbcUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, maHoaDon);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int maCTHD = rs.getInt("maChiTietHoaDon");
                int maBH = rs.getInt("maBaoHanh");
                int maSP = rs.getInt("maSanPham");
                int soLuong = rs.getInt("soLuong");
                int donGia = rs.getInt("donGia");
                int trangThai = rs.getInt("trangThai");

                list.add(new ChiTietHoaDonDTO(maCTHD, maHoaDon, maBH, maSP, soLuong, donGia, trangThai));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean xoaMemChiTietHoaDon(int maHoaDon) {
        String sql = "UPDATE chitiethoadon SET trangThai = 0 WHERE maHoaDon = ?";
        try (Connection conn = JdbcUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, maHoaDon);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteCTHoaDon(int mahoadon){
      String sql = "DELETE FROM chitiethoadon WHERE mahoadon= ?";
      try(Connection conn = JdbcUtil.getConnection()) {
          PreparedStatement stmt = conn.prepareStatement(sql);
          stmt.setInt(1,mahoadon);
          int check = stmt.executeUpdate();
          return check>0;
      } catch (Exception e) {
          e.printStackTrace();
      }
        return false;
  }
    
    
}
