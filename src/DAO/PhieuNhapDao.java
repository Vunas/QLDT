/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


import DTO.PhieuNhapDTO;
import util.JdbcUtil;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
/**
 *
 * @author nguyen
 */
public class PhieuNhapDao {
    public boolean addPhieuNhap(PhieuNhapDTO pn){
        String sql = "INSERT INTO phieunhap(ngayNhap, maNhaCungCap, maNhanVien) VALUES (?,?,?)";
        try(Connection conn = JdbcUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1,Date.valueOf(pn.getNgayNhap()));
            stmt.setInt(2,pn.getMaNhaCungCap());
            stmt.setInt(3, pn.getMaNhanVien());
            int check = stmt.executeUpdate();
            return check>0;   
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
  public List<PhieuNhapDTO> getAllPhieuNhap() {
        List<PhieuNhapDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM phieunhap";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new PhieuNhapDTO(
                        rs.getInt("maPhieuNhap"),
                        rs.getDate("ngayNhap").toLocalDate(),
                        rs.getInt("maNhaCungCap"),
                        rs.getInt("maNhanVien")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
  
  public PhieuNhapDTO timPhieuNhap(int maPhieuNhap){
      String sql ="SELECT * FROM phieunhap WHERE maPhieuNhap=?";
      try (Connection conn = JdbcUtil.getConnection()){
          PreparedStatement stmt = conn.prepareStatement(sql);
          stmt.setInt(1,maPhieuNhap);
          ResultSet rs = stmt.executeQuery();
          
          if(rs.next()){
              return new PhieuNhapDTO(
                      rs.getInt("maPhieuNhap"),
                      rs.getDate("ngayNhap").toLocalDate(),
                      rs.getInt("maNhaCungCap"),
                      rs.getInt("maNhanVien")
              );
          }  
      } catch (Exception e) {
          e.printStackTrace();
      }
      return null;
  }
  
  public boolean xoaPhieuNhap(int maPhieuNhap){
      String sql = "DELETE FROM phieunhap WHERE maPhieuNhap = ?";
      try (Connection conn = JdbcUtil.getConnection()){
          PreparedStatement stmt = conn.prepareStatement(sql);
          stmt.setInt(1,maPhieuNhap);
          
          int check = stmt.executeUpdate();
          return check>0;
          
      } catch (Exception e) {
          e.printStackTrace();
      }
      return false;
  }
  
   public int getMaPhieuNhap() {
    String sql = "SHOW TABLE STATUS WHERE Name = 'phieunhap'";

    try (Connection conn = JdbcUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        if (rs.next()) {
            return rs.getInt("Auto_increment"); // Lấy giá trị tiếp theo của AUTO_INCREMENT
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return -1;

}
}
