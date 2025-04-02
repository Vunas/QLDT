/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ThuocTinhSanPham.ColorDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.JdbcUtil;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
/**
 *
 * @author thaoh
 */
public class ColorDao implements DaoInterface<ColorDTO>{
    public static ColorDao getInstance() {
        return new ColorDao();
    }

    @Override
    public int insert(ColorDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JdbcUtil.getConnection();
            String sql = "INSERT INTO `mausac`(`mamau`, `tenmau`,`trangthai`) VALUES (?,?,1)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMamau());
            pst.setString(2, t.getTenmau());
            result = pst.executeUpdate();
            JdbcUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ColorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(ColorDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JdbcUtil.getConnection();
            String sql = "UPDATE `mausac` SET `tenmau`=? WHERE `mamau`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTenmau());
            pst.setInt(2, t.getMamau());
            result = pst.executeUpdate();
            JdbcUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ColorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) JdbcUtil.getConnection();
            String sql = "UPDATE `mausac` SET `trangthai` = 0 WHERE mamau = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1,t);
            result = pst.executeUpdate();
            JdbcUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ColorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<ColorDTO> selectAll() {
        ArrayList<ColorDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JdbcUtil.getConnection();
            String sql = "SELECT * FROM mausac WHERE trangthai = 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int mamau = rs.getInt("mamau");
                String tenmau = rs.getString("tenmau");
                ColorDTO ms = new ColorDTO(mamau, tenmau);
                result.add(ms);
            }
            JdbcUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
    
    public ArrayList<ColorDTO> getAll() {
        ArrayList<ColorDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JdbcUtil.getConnection();
            String sql = "SELECT * FROM mausac";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int mamau = rs.getInt("mamau");
                String tenmau = rs.getString("tenmau");
                ColorDTO ms = new ColorDTO(mamau, tenmau);
                result.add(ms);
            }
            JdbcUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public ColorDTO selectById(String t) {
        ColorDTO result = null;
        try {
            Connection con = (Connection) JdbcUtil.getConnection();
            String sql = "SELECT * FROM mausac WHERE mamau=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int mamau = rs.getInt("mamau");
                String tenmau = rs.getString("tenmau");
                result = new ColorDTO(mamau, tenmau);
            }
            JdbcUtil.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) JdbcUtil.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'qldt' AND   TABLE_NAME   = 'mausac'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery(sql);
            if (!rs2.isBeforeFirst()) {
                System.out.println("No data");
            } else {
                while (rs2.next()) {
                    result = rs2.getInt("AUTO_INCREMENT");

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }    
}
