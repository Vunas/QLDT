/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


import DTO.ThuocTinhSanPham.BrandDTO;
import util.JdbcUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author thaoh
 */
public class BrandDao implements DaoInterface<BrandDTO>{
    public static BrandDao getInstance(){
        return new BrandDao();
    }
    @Override
    public int insert(BrandDTO t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JdbcUtil.getConnection();
            String sql = "INSERT INTO `thuonghieu`(`tenthuonghieu`) VALUES (?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTenthuonghieu());
            result = pst.executeUpdate();
            JdbcUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(BrandDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(BrandDTO t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JdbcUtil.getConnection();
            String sql = "UPDATE `thuonghieu` SET`tenthuonghieu`=? WHERE `mathuonghieu`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTenthuonghieu());
            pst.setInt(2, t.getMathuonghieu());
            result = pst.executeUpdate();
            JdbcUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(BrandDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JdbcUtil.getConnection();
            String sql = "UPDATE `thuonghieu` SET `trangthai` = 0 WHERE `mathuonghieu`= ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JdbcUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(BrandDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<BrandDTO> selectAll() {
        ArrayList<BrandDTO> result = new ArrayList<BrandDTO>();
        try {
            Connection con = (Connection) JdbcUtil.getConnection();
            String sql = "SELECT * FROM thuonghieu WHERE `trangthai`=1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int mathuonghieu = rs.getInt("mathuonghieu");
                String tenthuonghieu = rs.getString("tenthuonghieu");
                
                BrandDTO lh = new BrandDTO(mathuonghieu, tenthuonghieu);
                result.add(lh);
            }
            JdbcUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public BrandDTO selectById(String t) {
        BrandDTO result = null;
        try {
            Connection con = (Connection) JdbcUtil.getConnection();
            String sql = "SELECT * FROM thuonghieu WHERE mathuonghieu=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int mathuonghieu = rs.getInt("mathuonghieu");
                String tenloaihang = rs.getString("tenthuonghieu");
                result = new BrandDTO(mathuonghieu, tenloaihang);
            }
            JdbcUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) JdbcUtil.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'qldt' AND   TABLE_NAME   = 'thuonghieu'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery(sql);
            if (!rs2.isBeforeFirst() ) {
                System.out.println("No data");
            } else {
                while ( rs2.next() ) {
                    result = rs2.getInt("AUTO_INCREMENT");
                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BrandDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
        
}
