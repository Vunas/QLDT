/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietSanPhamDTO;
import util.JdbcUtil;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author nguyen
 */
public class ChiTietSanPhamDao {
    public boolean addChiTietSanPham(ChiTietSanPhamDTO ctsp){
        String sql = "INSERT INTO ctsanpham(maimei, masanpham, maphieunhap, mahoadon, tinhtrang) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = JdbcUtil.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ctsp.getMaImei());
            stmt.setInt(2, ctsp.getMaSanpham());
            stmt.setInt(3, ctsp.getMaPhieuNhap());
            stmt.setNull(4, java.sql.Types.INTEGER);
            stmt.setInt(5, 0);
            int check = stmt.executeUpdate();
            if( check>0)
            return true; 
        } catch (Exception e) {
        }
        return false;
    }
    public boolean xacNhanDaBan(ChiTietSanPhamDTO ctsp){
        String sql = "UPDATE ctsanpham SET trangthai = ?, mahoadon = ? WHERE maimei = ?";
        try (Connection conn = JdbcUtil.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,1);
            stmt.setInt(2,ctsp.getMaHoadon());
            stmt.setString(3,ctsp.getMaImei());
            int check = stmt.executeUpdate();
            if( check>0)
            return true; 
        } catch (Exception e) {
        }
        return false;
    }
    public List<String> getImeisByPhieuNhapAndSanPham(int maphieunhap, int masanpham) {
    List<String> imeiList = new ArrayList<>();
    String sql = "SELECT maimei FROM ctsanpham WHERE maphieunhap = ? AND masanpham = ?";
    try (Connection conn = JdbcUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, maphieunhap);
        stmt.setInt(2, masanpham);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            imeiList.add(rs.getString("maimei"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return imeiList;
}
}

