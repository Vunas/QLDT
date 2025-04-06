package DAO;

import DTO.QuyenDTO;
import util.JdbcUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuyenDao {
    
    // Thêm quyền mới
    public boolean addQuyen(QuyenDTO quyen) {
        String sql = "INSERT INTO Quyen (tenQuyen, danhSachChucNang, trangThai) VALUES (?, ?, ?)";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, quyen.getTenQuyen());
            stmt.setString(2, quyen.getDanhSachChucNang());
            stmt.setInt(3, 1); // Mặc định trạng thái là 1 (hoạt động)
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật thông tin quyền
    public boolean updateQuyen(QuyenDTO quyen) {
        String sql = "UPDATE Quyen SET tenQuyen = ?, danhSachChucNang = ?, trangThai = ? WHERE maQuyen = ?";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, quyen.getTenQuyen());
            stmt.setString(2, quyen.getDanhSachChucNang());
            stmt.setInt(3, quyen.getTrangThai());
            stmt.setInt(4, quyen.getMaQuyen());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa mềm quyền
    public boolean xoaMemQuyen(int maQuyen) {
        String sql = "UPDATE Quyen SET trangThai = 0 WHERE maQuyen = ?"; // Đánh dấu trạng thái = 0
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maQuyen);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy thông tin tất cả quyền (trừ quyền đã bị xóa mềm)
    public List<QuyenDTO> getAllQuyen() {
        String sql = "SELECT maQuyen, tenQuyen, danhSachChucNang FROM Quyen WHERE trangThai != 0";
        List<QuyenDTO> list = new ArrayList<>();
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                QuyenDTO quyen = new QuyenDTO();
                quyen.setMaQuyen(rs.getInt("maQuyen"));
                quyen.setTenQuyen(rs.getString("tenQuyen"));
                quyen.setDanhSachChucNang(rs.getString("danhSachChucNang"));
                list.add(quyen);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy thông tin quyền theo ID
    public QuyenDTO getQuyenById(int maQuyen) {
        String sql = "SELECT maQuyen, tenQuyen, danhSachChucNang, trangThai FROM Quyen WHERE maQuyen = ? AND trangThai != 0";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maQuyen);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    QuyenDTO quyen = new QuyenDTO();
                    quyen.setMaQuyen(rs.getInt("maQuyen"));
                    quyen.setTenQuyen(rs.getString("tenQuyen"));
                    quyen.setDanhSachChucNang(rs.getString("danhSachChucNang"));
                    quyen.setTrangThai(rs.getInt("trangThai"));
                    return quyen;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
