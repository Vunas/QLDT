/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.HoaDonDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import util.JdbcUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author nguyen
 */
public class HoaDonDao {
    public boolean addHoaDon (HoaDonDTO hd){
         String sql = "INSERT INTO hoadon(ngayxuat, makhachhang, manhanvien, makhuyenmai) VALUES (?,?,?,?)";
        try(Connection conn = JdbcUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1,Date.valueOf(hd.getNgayXuat()));
            stmt.setInt(2,hd.getMaKH());
            stmt.setInt(3, hd.getMaNhanVien());
            stmt.setInt(4, hd.getMakhuyenmai());
            int check = stmt.executeUpdate();
            return check>0;   
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
      
  public List<HoaDonDTO> getAllHoaDon() {
    List<HoaDonDTO> list = new ArrayList<>();
    String sql = "SELECT * FROM hoadon";
    try (Connection conn = JdbcUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            list.add(new HoaDonDTO(
                    rs.getInt("mahoadon"),
                    rs.getDate("ngayxuat").toLocalDate(),
                    rs.getInt("makhachhang"),
                    rs.getInt("manhanvien"),
                    rs.getInt("makhuyenmai"),
                    rs.getInt("trangThai")
            ));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}
  
  public boolean deleteHoaDon(int mahoadon){
      String sql = "DELETE FROM hoadon WHERE mahoadon= ?";
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
  
  public HoaDonDTO timHoaDon(int mahoadon){
       String sql ="SELECT * FROM hoadon WHERE mahoadon=?";
      try (Connection conn = JdbcUtil.getConnection()){
          PreparedStatement stmt = conn.prepareStatement(sql);
          stmt.setInt(1,mahoadon);
          ResultSet rs = stmt.executeQuery();
          
          if(rs.next()){
              return new HoaDonDTO(
                      rs.getInt("mahoadon"),
                      rs.getDate("ngayxuat").toLocalDate(),
                      rs.getInt("makhachhang"),
                      rs.getInt("manhanvien"),
                      rs.getInt("makhuyenmai"),
                      rs.getInt("trangThai")
              );
          }  
      } catch (Exception e) {
          e.printStackTrace();
      }
      return null;
      
  }
  
  public int getMaHoaDon(){
      String sql = "SHOW TABLE STATUS WHERE Name = 'hoadon'";

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
