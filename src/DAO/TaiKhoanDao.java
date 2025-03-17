package DAO;

import DTO.TaiKhoanDTO;
import util.JdbcUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDao {

    // 1. Thêm tài khoản mới
    public boolean addTaiKhoan(TaiKhoanDTO taiKhoan) {
        String sql = "INSERT INTO TaiKhoan (maNV, tenDangNhap, matKhau, maQuyen) VALUES (?, ?, ?, ?)";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, taiKhoan.getMaNV());
            ps.setString(2, taiKhoan.getTenDangNhap());
            ps.setString(3, taiKhoan.getMatKhau());
            ps.setInt(4, taiKhoan.getMaQuyen());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2. Lấy danh sách tài khoản
    public List<TaiKhoanDTO> getAllTaiKhoan() {
        List<TaiKhoanDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM TaiKhoan";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TaiKhoanDTO taiKhoan = new TaiKhoanDTO(
                        rs.getInt("maNV"),
                        rs.getString("tenDangNhap"),
                        rs.getString("matKhau"),
                        rs.getInt("maQuyen")
                );
                list.add(taiKhoan);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 3. Cập nhật tài khoản
    public boolean updateTaiKhoan(TaiKhoanDTO taiKhoan) {
        String sql = "UPDATE TaiKhoan SET tenDangNhap = ?, matKhau = ?, maQuyen = ? WHERE maNV = ?";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, taiKhoan.getTenDangNhap());
            ps.setString(2, taiKhoan.getMatKhau());
            ps.setInt(3, taiKhoan.getMaQuyen());
            ps.setInt(4, taiKhoan.getMaNV());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 4. Xóa tài khoản
    public boolean deleteTaiKhoan(int maNV) {
        String sql = "DELETE FROM TaiKhoan WHERE maNV = ?";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maNV);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 5. Kiểm tra thông tin đăng nhập
    public TaiKhoanDTO login(String tenDangNhap, String matKhau) {
        String sql = "SELECT * FROM TaiKhoan WHERE tenDangNhap = ? AND matKhau = ?";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tenDangNhap);
            ps.setString(2, matKhau);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new TaiKhoanDTO(
                            rs.getInt("maNV"),
                            rs.getString("tenDangNhap"),
                            rs.getString("matKhau"),
                            rs.getInt("maQuyen")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Đăng nhập thất bại
    }
}
