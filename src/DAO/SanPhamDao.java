package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.SanPhamDTO;
import util.JdbcUtil;

public class SanPhamDao {
    
     public boolean addSanPham(SanPhamDTO SanPham){
        String query = "INSERT INTO SanPham (maSP, tenSP, img, soLuong, giaNhap, giaBan , mauSac , thuongHieu , Ram , Rom , Chip , thoiGianBaoHanh) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = JdbcUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
    
            stmt.setInt(1, SanPham.getMaSP());
            stmt.setString(2, SanPham.getTenSP());
            stmt.setString(3, SanPham.getImg());
            stmt.setInt(4, SanPham.getSoLuong());
            stmt.setInt(5, SanPham.getGiaNhap());
            stmt.setInt(6, SanPham.getGiaBan());
            stmt.setString(7, SanPham.getMauSac());
            stmt.setString(8, SanPham.getThuongHieu());
            stmt.setInt(9, SanPham.getRam());
            stmt.setInt(10, SanPham.getRom());
            stmt.setString(11, SanPham.getChip());
            stmt.setFloat(12, SanPham.getThoiGianBaoHanh());
            
            
    
            return stmt.executeUpdate() > 0; // Trả về true nếu thêm thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Thêm thất bại
    }
    
    
    public boolean updateSanPham(SanPhamDTO SanPham) {
        String query = "UPDATE SanPham SET tenSP = ?, img = ?, soLuong = ?, giaNhap = ?, giaBan = ?, mauSac = ?, thuongHieu = ?, Ram = ?, Rom = ?, Chip = ?, thoiGianBaoHanh = ? WHERE maSP = ?;";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            stmt.setString(1, SanPham.getTenSP());
            stmt.setString(2, SanPham.getImg());
            stmt.setInt(3, SanPham.getSoLuong());
            stmt.setInt(4, SanPham.getGiaNhap());
            stmt.setInt(5, SanPham.getGiaBan());
            stmt.setString(6, SanPham.getMauSac());
            stmt.setString(7, SanPham.getThuongHieu());
            stmt.setInt(8, SanPham.getRam());
            stmt.setInt(9, SanPham.getRom());
            stmt.setString(10, SanPham.getChip());
            stmt.setFloat(11, SanPham.getThoiGianBaoHanh());
            stmt.setInt(12, SanPham.getMaSP());
    
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    public boolean deleteSanPham(int maSP) {
        String query = "DELETE FROM SanPham WHERE maSP = ?";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            stmt.setInt(1, maSP);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public SanPhamDTO getSanPhamById(int maSP) {
        String query = "SELECT * FROM SanPham WHERE maSP = ?";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            stmt.setInt(1, maSP);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String tenSP = rs.getString("tenSP");
                    String img = rs.getString("img");
                    int soLuong = rs.getInt("soLuong");
                    int giaNhap = rs.getInt("giaNhap");
                    int giaBan = rs.getInt("giaBan");
                    String mauSac = rs.getString("mauSac");
                    String thuongHieu = rs.getString("thuongHieu");
                    int Ram = rs.getInt("Ram");
                    int Rom = rs.getInt("Rom");
                    String Chip= rs.getString("Chip");
                    float thoiGianBaoHanh = rs.getFloat("thoiGianBaoHanh");
                    return new SanPhamDTO(maSP, tenSP, img, soLuong, giaNhap, giaBan , mauSac , thuongHieu , Ram , Rom , Chip , thoiGianBaoHanh);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    

    public List<SanPhamDTO> getAllSanPham() {
        List<SanPhamDTO> SanPhamList = new ArrayList<>();
        String query = "SELECT * FROM SanPham";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int maSP = rs.getInt("maSP");
                String tenSP = rs.getString("tenSP");
                String img = rs.getString("img");
                int soLuong = rs.getInt("soLuong");
                int giaNhap = rs.getInt("giaNhap");
                int giaBan = rs.getInt("giaBan");
                String mauSac = rs.getString("mauSac");
                String thuongHieu = rs.getString("thuongHieu");
                int Ram = rs.getInt("Ram");
                int Rom = rs.getInt("Rom");
                String Chip= rs.getString("Chip");
                float thoiGianBaoHanh = rs.getFloat("thoiGianBaoHanh");

                SanPhamList.add(new SanPhamDTO(maSP, tenSP, img, soLuong, giaNhap, giaBan , mauSac , thuongHieu , Ram , Rom , Chip , thoiGianBaoHanh));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return SanPhamList;
    }
}
