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
            if(check > 0)
                return true;      
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<ChiTietPhieuNhapDTO> getChiTietPhieuNhapByMaPhieuNhap(int maPhieuNhap){
        List<ChiTietPhieuNhapDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM chitietphieunhap WHERE maPhieuNhap = ? AND trangthai = 1"; // Lọc chỉ các bản ghi "còn"
        try (Connection conn = JdbcUtil.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, maPhieuNhap);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int maCTPhieuNhap = rs.getInt("maCTPhieuNhap");
                int soLuong = rs.getInt("soLuong");
                int donGia = rs.getInt("donGia");
                int maSanPham = rs.getInt("maSanPham");
                int trangThai = rs.getInt("trangThai");
                
                list.add(new ChiTietPhieuNhapDTO(maCTPhieuNhap, soLuong, donGia, maPhieuNhap, maSanPham, trangThai));
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
     public List<ChiTietPhieuNhapDTO> getAllChiTietPhieuNhap(){
        List<ChiTietPhieuNhapDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM chitietphieunhap WHERE trangthai = 1"; // Lọc chỉ các bản ghi "còn"
        try (Connection conn = JdbcUtil.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int maCTPhieuNhap = rs.getInt("maCTPhieuNhap");
                int soLuong = rs.getInt("soLuong");
                int donGia = rs.getInt("donGia");
                int maPhieuNhap = rs.getInt("maPhieuNhap");
                int maSanPham = rs.getInt("maSanPham");
                int trangThai = rs.getInt("trangThai");
                
                list.add(new ChiTietPhieuNhapDTO(maCTPhieuNhap, soLuong, donGia, maPhieuNhap, maSanPham, trangThai));
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    
    public boolean xoaMemCTPhieuNhap(int maPhieuNhap){
        // Xóa mềm bằng cách cập nhật "trangthai" thành 0
        String sql = "UPDATE chitietphieunhap SET trangthai = 0 WHERE maPhieuNhap = ?";
        try (Connection conn = JdbcUtil.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, maPhieuNhap);
            int check = stmt.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
