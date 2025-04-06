
package DAO;

import DTO.ThuocTinhSanPham.RamDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.JdbcUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RamDao implements DaoInterface<RamDTO>{
public static RamDao getInstance() {
        return new RamDao();
    }
    @Override
    public int insert(RamDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JdbcUtil.getConnection();
            String sql = "INSERT INTO `dungluongram`(`madlram`, `kichthuocram`,`trangthai`) VALUES (?,?,1)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMadlram());
            pst.setInt(2, t.getDungluongram());
            result = pst.executeUpdate();
            JdbcUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(RamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(RamDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JdbcUtil.getConnection();
            String sql = "UPDATE `dungluongram` SET `kichthuocram`=? WHERE `madlram`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getDungluongram());
            pst.setInt(2, t.getMadlram());
            result = pst.executeUpdate();
            JdbcUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(RamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) JdbcUtil.getConnection();
            String sql = "UPDATE `dungluongram` SET trangthai = 0 WHERE madlram = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JdbcUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(RamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<RamDTO> selectAll() {
        ArrayList<RamDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JdbcUtil.getConnection();
            String sql = "SELECT * FROM dungluongram WHERE trangthai = 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maram = rs.getInt("madlram");
                int kichthuocram = rs.getInt("kichthuocram");
                RamDTO dlr = new RamDTO(maram, kichthuocram);
                result.add(dlr);
            }
            JdbcUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public RamDTO selectById(String t) {
        RamDTO result = null;
        try {
            Connection con = (Connection) JdbcUtil.getConnection();
            String sql = "SELECT * FROM dungluongram WHERE madlram=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int madlram = rs.getInt("madlram");
                int kichthuoram = rs.getInt("kichthuocram");
                result = new RamDTO(madlram, kichthuoram);
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
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'qldt' AND   TABLE_NAME   = 'dungluongram'";
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
            Logger.getLogger(RamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
