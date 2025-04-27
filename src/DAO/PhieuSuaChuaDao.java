/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.PhieuBaoHanhDTO;
import DTO.PhieuSuaChuaDTO;
import java.util.Date;
import util.JdbcUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author thaoh
 */
public class PhieuSuaChuaDao {
    public boolean add(PhieuSuaChuaDTO dto) {
        String sql = "INSERT INTO phieusuachua(maPhieuBH, maSanPham, maIMEI, ngayNhan, tinhTrang, xuLy, trangThai, ghiChu) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = JdbcUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, dto.getMaPhieuBH());
            stmt.setInt(2, dto.getMaSanPham());
            stmt.setString(3, dto.getMaIMEI());
            stmt.setDate(4, java.sql.Date.valueOf(dto.getNgayNhan()));
            stmt.setString(5, dto.getTinhTrang());
            stmt.setString(6, dto.getXuLy());
            stmt.setString(7, dto.getTrangThai());
            stmt.setString(8, dto.getGhiChu());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateTrangThai(int maSC , String trangThai) {
        String sql = "UPDATE phieusuachua SET trangThai = ? WHERE maPhieuSC = ?";
        try (Connection conn = JdbcUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1,trangThai);
            stmt.setInt(2, maSC);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }        
    public List<PhieuSuaChuaDTO> getAll(){
        List<PhieuSuaChuaDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM phieusuachua";
        try(Connection conn = JdbcUtil.getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){
            while (rs.next()) {
                int maPhieuSC = rs.getInt("maPhieuSC");
                int maPhieuBH = rs.getInt("maPhieuBH");
                int maSP = rs.getInt("maSanPham");
                String maIMEI = rs.getString("maIMEI");
                LocalDate ngayNhan = rs.getDate("ngayNhan").toLocalDate();
                String tinhTrang = rs.getString("tinhTrang");
                String xuLy = rs.getString("xuLy");
                String trangThai = rs.getString("trangThai");
                String ghiChu = rs.getString("ghiChu");

                list.add(new PhieuSuaChuaDTO(maPhieuSC, maPhieuBH, maSP, maIMEI, ngayNhan, tinhTrang, xuLy, trangThai, ghiChu));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    // , getById(), update(), delete() có thể thêm sau
    public PhieuSuaChuaDTO getById(int maPhieuSC){
        String query = "SELECT * FROM phieusuachua WHERE maPhieuSC = ?";
        try(Connection conn = JdbcUtil.getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, maPhieuSC);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    int maPhieuBH = rs.getInt("maPhieuBH");
                    int maSP = rs.getInt("maSanPham");
                    String maIMEI = rs.getString("maIMEI");
                    LocalDate ngayNhan = rs.getDate("ngayNhan").toLocalDate();
                    String tinhTrang = rs.getString("tinhTrang");
                    String xuLy = rs.getString("xuLy");
                    String trangThai = rs.getString("trangThai");
                    String ghiChu = rs.getString("ghiChu");
                    return new PhieuSuaChuaDTO(maPhieuSC, maPhieuBH, maSP, maIMEI, ngayNhan, tinhTrang, xuLy, trangThai, ghiChu);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public List<PhieuSuaChuaDTO> getByMaPhieuBH(int maPhieuBH){
        List<PhieuSuaChuaDTO> list = new ArrayList<>();
        String query = "SELECT * FROM phieusuachua WHERE maPhieuBH LIKE ?";
        try(Connection conn = JdbcUtil.getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, "%"+maPhieuBH+"%");
            try(ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    int maPhieuSC = rs.getInt("maPhieuSC");
                    int maSP = rs.getInt("maSanPham");
                    String maIMEI = rs.getString("maIMEI");
                    LocalDate ngayNhan = rs.getDate("ngayNhan").toLocalDate();
                    String tinhTrang = rs.getString("tinhTrang");
                    String xuLy = rs.getString("xuLy");
                    String trangThai = rs.getString("trangThai");
                    String ghiChu = rs.getString("ghiChu");
                    list.add(new PhieuSuaChuaDTO(maPhieuSC, maPhieuBH, maSP, maIMEI, ngayNhan, tinhTrang, xuLy, trangThai, ghiChu));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public PhieuSuaChuaDTO getBymaIMEI(String maIMEI){
        String query = "SELECT * FROM phieusuachua WHERE maIMEI LIKE ?";
        try(Connection conn = JdbcUtil.getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1,"%" + maIMEI + "%");
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    int maPhieuSC = rs.getInt("maPhieuSC");
                    int maPhieuBH = rs.getInt("maPhieuBH");
                    int maSP = rs.getInt("maSanPham");
                    LocalDate ngayNhan = rs.getDate("ngayNhan").toLocalDate();
                    String tinhTrang = rs.getString("tinhTrang");
                    String xuLy = rs.getString("xuLy");
                    String trangThai = rs.getString("trangThai");
                    String ghiChu = rs.getString("ghiChu");
                    return new PhieuSuaChuaDTO(maPhieuSC, maPhieuBH, maSP, maIMEI, ngayNhan, tinhTrang, xuLy, trangThai, ghiChu);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public List<PhieuSuaChuaDTO> getByMaPhieuBHAndIMEI(int maPBH , String maIMEI){
        List<PhieuSuaChuaDTO> PSC = new ArrayList<>();
        String query = "SELECT * FROM phieusuachua WHERE maPhieuBH LIKE ? AND maIMEI LIKE ?";
        try(Connection conn = JdbcUtil.getConnection();
            PreparedStatement stmt = conn.prepareCall(query)){
            stmt.setString(1,"%"+maPBH+"%");
            stmt.setString(2,"%"+maIMEI+"%");
            try(ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    int maPhieuSC = rs.getInt("maPhieuSC");
                    int maSP = rs.getInt("maSanPham");
                    LocalDate ngayNhan = rs.getDate("ngayNhan").toLocalDate();
                    String tinhTrang = rs.getString("tinhTrang");
                    String xuLy = rs.getString("xuLy");
                    String trangThai = rs.getString("trangThai");
                    String ghiChu = rs.getString("ghiChu");
                    PSC.add( new PhieuSuaChuaDTO(maPhieuSC, maPBH, maSP, maIMEI, ngayNhan, tinhTrang, xuLy, trangThai, ghiChu));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return PSC;
    }
}
