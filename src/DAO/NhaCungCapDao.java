package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.NhaCungCapDTO;
import util.JdbcUtil;

public class NhaCungCapDao {

    // Thêm nhà cung cấp mới
    public boolean addNhaCungCap(NhaCungCapDTO nhaCungCap) {
        String sql = "INSERT INTO nha_cung_cap (maNCC, ten, diaChi, sdt, trangthai) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, nhaCungCap.getMaNhaCungCap());
            statement.setString(2, nhaCungCap.getTen());
            statement.setString(3, nhaCungCap.getDiaChi());
            statement.setString(4, nhaCungCap.getsDT());
            statement.setInt(5, 1); // Mặc định trạng thái là 1 (còn hiệu lực)

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật thông tin nhà cung cấp
    public boolean updateNhaCungCap(NhaCungCapDTO nhaCungCap) {
        String sql = "UPDATE nha_cung_cap SET ten = ?, diaChi = ?, sdt = ? WHERE maNCC = ? AND trangthai = 1";
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nhaCungCap.getTen());
            statement.setString(2, nhaCungCap.getDiaChi());
            statement.setString(3, nhaCungCap.getsDT());
            statement.setInt(4, nhaCungCap.getMaNhaCungCap());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa mềm nhà cung cấp
    public boolean xoaMemNhaCungCap(int maNCC) {
        String sql = "UPDATE nha_cung_cap SET trangthai = 0 WHERE maNCC = ?";
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, maNCC);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy thông tin nhà cung cấp theo mã
    public NhaCungCapDTO getNhaCungCapById(int maNCC) {
        String sql = "SELECT * FROM nha_cung_cap WHERE maNCC = ? AND trangthai = 1"; // Chỉ lấy nhà cung cấp còn hiệu lực
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, maNCC);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new NhaCungCapDTO(
                        resultSet.getInt("maNCC"),
                        resultSet.getString("ten"),
                        resultSet.getString("diaChi"),
                        resultSet.getString("sdt"),
                        resultSet.getInt("trangThai")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Lấy danh sách tất cả nhà cung cấp còn hiệu lực
    public List<NhaCungCapDTO> getAllNhaCungCap() {
        List<NhaCungCapDTO> nhaCungCapList = new ArrayList<>();
        String sql = "SELECT * FROM nha_cung_cap WHERE trangthai = 1"; // Chỉ lấy nhà cung cấp còn hiệu lực
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                NhaCungCapDTO nhaCungCap = new NhaCungCapDTO(
                        resultSet.getInt("maNCC"),
                        resultSet.getString("ten"),
                        resultSet.getString("diaChi"),
                        resultSet.getString("sdt"),
                        resultSet.getInt("trangThai")
                );
                nhaCungCapList.add(nhaCungCap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nhaCungCapList;
    }
    
      public List<NhaCungCapDTO> getAllNhaCungCapToAdd() {
        List<NhaCungCapDTO> nhaCungCapList = new ArrayList<>();
        String sql = "SELECT * FROM nha_cung_cap"; // Chỉ lấy nhà cung cấp còn hiệu lực
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                NhaCungCapDTO nhaCungCap = new NhaCungCapDTO(
                        resultSet.getInt("maNCC"),
                        resultSet.getString("ten"),
                        resultSet.getString("diaChi"),
                        resultSet.getString("sdt"),
                        resultSet.getInt("trangThai")
                );
                nhaCungCapList.add(nhaCungCap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nhaCungCapList;
    }

    // Lấy danh sách tên các nhà cung cấp còn hiệu lực
    public String[] getNameNhaCungCap() {
        List<String> listNCC = new ArrayList<>();
        String sql = "SELECT ten FROM nha_cung_cap WHERE trangthai = 1"; // Chỉ lấy nhà cung cấp còn hiệu lực
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                listNCC.add(rs.getString("ten"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNCC.toArray(new String[0]);
    }

    public NhaCungCapDTO getNhaCungCapByName(String ten) {
        String sql = "SELECT * FROM nha_cung_cap WHERE ten = ? AND trangthai = 1"; // Lọc nhà cung cấp còn hiệu lực
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, ten);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new NhaCungCapDTO(
                        resultSet.getInt("maNCC"),
                        resultSet.getString("ten"),
                        resultSet.getString("diaChi"),
                        resultSet.getString("sdt"),
                        resultSet.getInt("trangThai")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
