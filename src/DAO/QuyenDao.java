package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.QuyenDTO;
import util.JdbcUtil;

public class QuyenDao {
    
    // Thêm quyền mới
    public boolean addQuyen(QuyenDTO quyen) {
        String sql = "INSERT INTO Quyen (tenQuyen, danhSachChucNang) VALUES (?, ?)";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, quyen.getTenQuyen());
            stmt.setString(2, quyen.getDanhSachChucNang());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật thông tin quyền
    public boolean updateQuyen(QuyenDTO quyen) {
        String sql = "UPDATE Quyen SET tenQuyen = ?, danhSachChucNang = ? WHERE maQuyen = ?";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, quyen.getTenQuyen());
            stmt.setString(2, quyen.getDanhSachChucNang());
            stmt.setInt(3, quyen.getMaQuyen());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa quyền
    public boolean deleteQuyen(int maQuyen) {
        String sql = "DELETE FROM Quyen WHERE maQuyen = ?";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maQuyen);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy thông tin tất cả quyền
    public List<QuyenDTO> getAllQuyen() {
        String sql = "SELECT maQuyen, tenQuyen, danhSachChucNang FROM Quyen";
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
        String sql = "SELECT maQuyen, tenQuyen, danhSachChucNang FROM Quyen WHERE maQuyen = ?";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maQuyen);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    QuyenDTO quyen = new QuyenDTO();
                    quyen.setMaQuyen(rs.getInt("maQuyen"));
                    quyen.setTenQuyen(rs.getString("tenQuyen"));
                    quyen.setDanhSachChucNang(rs.getString("danhSachChucNang"));
                    return quyen;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
