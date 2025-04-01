/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DTO.ChiTietPhieuNhapDTO;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import util.JdbcUtil;

/**
 *
 * @author nguyen
 */
public class ChiTietPhieuNhapDao {
    public boolean addChiTietPhieuNhap(ChiTietPhieuNhapDTO ctpn){
        String sql = "INSERT INTO chitietphieunhap(soLuong,donGia,maPhieuNhap,maSanPham) VALUES(?,?,?,?)";
        try (Connection conn = JdbcUtil.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ctpn.getSoLuong());
            stmt.setInt(2, ctpn.getDonGia());
            stmt.setInt(3, ctpn.getMaPhieuNhap());
            stmt.setInt(4, ctpn.getMaSanPham());
            int check = stmt.executeUpdate();
            return check>0;      
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<ChiTietPhieuNhapDTO> getChiTietPhieuNhapByMaPhieuNhap(int maPhieuNhap){
        List<ChiTietPhieuNhapDTO> list = new ArrayList<>();
        String sql="SELECT * FROM chitietphieunhap WHERE maPhieuNhap = ?";
        try(Connection conn = JdbcUtil.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, maPhieuNhap);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int maCTPhieuNhap=rs.getInt("maCTPhieuNhap");
                int soLuong = rs.getInt("soLuong");
                int donGia = rs.getInt("donGia");
                int maSanPham = rs.getInt("maSanPham");
                
                list.add(new ChiTietPhieuNhapDTO(maCTPhieuNhap, soLuong, donGia, maPhieuNhap, maSanPham));
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean xoaCTPhieuNhap(int maPhieuNhap){
      String sql = "DELETE FROM chitietphieunhap WHERE maPhieuNhap = ?";
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
}
