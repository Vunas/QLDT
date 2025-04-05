package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.KhoHangDTO;
import util.JdbcUtil;

public class KhoHangDao {

    // Thêm kho hàng mới
    public boolean addKhoHang(KhoHangDTO khoHang) {
        String sql = "INSERT INTO kho_hang (tenKho, diaChi, trangthai) VALUES (?, ?, ?)";
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, khoHang.getTenKho());
            statement.setString(2, khoHang.getDiaChi());
            statement.setInt(3, 1); // Mặc định trạng thái là 1 (còn hiệu lực)

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật thông tin kho hàng
    public boolean updateKhoHang(KhoHangDTO khoHang) {
        String sql = "UPDATE kho_hang SET tenKho = ?, diaChi = ? WHERE maKho = ? AND trangthai = 1";
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, khoHang.getTenKho());
            statement.setString(2, khoHang.getDiaChi());
            statement.setInt(3, khoHang.getMaKho());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa mềm kho hàng
    public boolean xoaMemKhoHang(int maKho) {
        String sql = "UPDATE kho_hang SET trangthai = 0 WHERE maKho = ?";
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, maKho);
            return statement.executeUpdate() > 0; // Trả về true nếu có dòng bị ảnh hưởng
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy thông tin kho hàng theo mã
    public KhoHangDTO getKhoHangById(int maKho) {
        String sql = "SELECT * FROM kho_hang WHERE maKho = ? AND trangthai = 1"; // Chỉ lấy kho còn hiệu lực
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, maKho);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new KhoHangDTO(
                        resultSet.getInt("maKho"),
                        resultSet.getString("tenKho"),
                        resultSet.getString("diaChi"),
                        resultSet.getInt("trangThai")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Lấy danh sách tất cả kho hàng còn hiệu lực
    public List<KhoHangDTO> getAllKhoHang() {
        List<KhoHangDTO> khoHangList = new ArrayList<>();
        String sql = "SELECT * FROM kho_hang WHERE trangthai = 1"; // Chỉ lấy kho còn hiệu lực
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                KhoHangDTO khoHang = new KhoHangDTO(
                        resultSet.getInt("maKho"),
                        resultSet.getString("tenKho"),
                        resultSet.getString("diaChi"),
                        resultSet.getInt("trangThai")
                );
                khoHangList.add(khoHang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return khoHangList;
    }
}
