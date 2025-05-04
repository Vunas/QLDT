package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import DTO.KhuyenMaiDTO;
import util.JdbcUtil;

public class KhuyenMaiDao {
    public List<KhuyenMaiDTO> getALLKhuyenMai() {
        List<KhuyenMaiDTO> list = new ArrayList<>();
        String sql = """
                    SELECT *
                    FROM `khuyenmai`
                    WHERE trangthai = 1
                """;
        try (Connection conn = JdbcUtil.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet rs = statement.executeQuery();) {
            while (rs.next()) {
                int maKM = rs.getInt("makhuyenmai");
                String tenKM = rs.getString("tenkhuyenmai");
                int soLuong = rs.getInt("soluong");
                Date start = rs.getDate("ngaybatdau");
                Date end = rs.getDate("ngayketthuc");
                int apDung = rs.getInt("apdungchohoadontu");
                int giaTri = rs.getInt("giatri");
                int hinhThuc = rs.getInt("hinhthuc");
                String mota = rs.getString("mota");
                KhuyenMaiDTO khuyenMaiDTO = new KhuyenMaiDTO(maKM, tenKM, soLuong, start, end, apDung, giaTri, hinhThuc,
                        mota);
                list.add(khuyenMaiDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<KhuyenMaiDTO> getALLKhuyenMaiToAdd() {
        List<KhuyenMaiDTO> list = new ArrayList<>();
        String sql = """
                    SELECT *
                    FROM `khuyenmai`
                    WHERE trangthai = 1
                """;
        try (Connection conn = JdbcUtil.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet rs = statement.executeQuery();) {
            while (rs.next()) {
                int maKM = rs.getInt("makhuyenmai");
                String tenKM = rs.getString("tenkhuyenmai");
                int soLuong = rs.getInt("soluong");
                Date start = rs.getDate("ngaybatdau");
                Date end = rs.getDate("ngayketthuc");
                int apDung = rs.getInt("apdungchohoadontu");
                int giaTri = rs.getInt("giatri");
                int hinhThuc = rs.getInt("hinhthuc");
                String mota = rs.getString("mota");
                KhuyenMaiDTO khuyenMaiDTO = new KhuyenMaiDTO(maKM, tenKM, soLuong, start, end, apDung, giaTri, hinhThuc,
                        mota);
                list.add(khuyenMaiDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean autoUpdateTrangThai(List<KhuyenMaiDTO> khuyenMaiDTO) {
        String sql = """
                    UPDATE khuyenmai
                    SET trangthai = ?
                    WHERE makhuyenmai = ?
                """;
        LocalDate dayNow = LocalDate.now();
        try (Connection conn = JdbcUtil.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            for (KhuyenMaiDTO dto : khuyenMaiDTO) {
                if (dto.getNgayBD().toLocalDate().isAfter(dayNow) || dto.getNgayKT().toLocalDate().isBefore(dayNow)) {
                    statement.setInt(1, 0);
                } else {
                    statement.setInt(1, 1);
                }
                statement.setInt(2, dto.getMaKM());
                statement.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addKhuyenMai(KhuyenMaiDTO khuyenMaiDTO) {
        String sql = """
                INSERT INTO `khuyenmai`( `tenkhuyenmai`, `soluong`, `ngaybatdau`, `ngayketthuc`, `apdungchohoadontu`, `giatri`, `hinhthuc`, `mota` )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;
        // LocalDate dayNow = LocalDate.now();
        try (Connection conn = JdbcUtil.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, khuyenMaiDTO.getTenKM());
            statement.setInt(2, khuyenMaiDTO.getSoLuong());
            statement.setDate(3, khuyenMaiDTO.getNgayBD());
            statement.setDate(4, khuyenMaiDTO.getNgayKT());
            statement.setInt(5, khuyenMaiDTO.getApDungChoHoaDonTu());
            statement.setInt(6, khuyenMaiDTO.getGiaTri());
            statement.setInt(7, khuyenMaiDTO.getHinhThuc());
            statement.setString(8, khuyenMaiDTO.getMota());

            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteKhuyenMai(int maKM) {
        String sql = """
                UPDATE khuyenmai SET trangthai = 0 WHERE makhuyenmai = ?
                """;
        try (Connection conn = JdbcUtil.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, maKM);
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateKhuyenMai(KhuyenMaiDTO khuyenMaiDTO) {
        String sql = """
        UPDATE khuyenmai SET makhuyenmai = ?, tenkhuyenmai = ?, soluong = ?,
        ngaybatdau = ?, ngayketthuc = ?, apdungchohoadontu = ?, giatri = ?, hinhthuc
        = ?, mota = ?
        WHERE makhuyenmai = ? AND trangthai = 1
        """;

        // String sql = """
        //                         UPDATE khuyenmai
        //         SET makhuyenmai = ?, tenkhuyenmai = ?, soluong = ?, ngaybatdau = ?, ngayketthuc = ?, apdungchohoadontu = ?, giatri = ?, hinhthuc = ?, mota = ?
        //         WHERE makhuyenmai = ? AND trangthai = 1

        //                         """;

        try (Connection conn = JdbcUtil.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, khuyenMaiDTO.getMaKM());
            statement.setString(2, khuyenMaiDTO.getTenKM());
            statement.setInt(3, khuyenMaiDTO.getSoLuong());
            statement.setDate(4, khuyenMaiDTO.getNgayBD());
            statement.setDate(5, khuyenMaiDTO.getNgayKT());
            statement.setInt(6, khuyenMaiDTO.getApDungChoHoaDonTu());
            statement.setInt(7, khuyenMaiDTO.getGiaTri());
            statement.setInt(8, khuyenMaiDTO.getHinhThuc());
            statement.setString(9, khuyenMaiDTO.getMota());
            statement.setInt(10, khuyenMaiDTO.getMaKM());
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public KhuyenMaiDTO getKhuyenMaiByID(int maKM) {
        KhuyenMaiDTO khuyenMaiDTO = null;
        String sql = """
                    SELECT *
                    FROM khuyenmai
                    WHERE makhuyenmai = ? AND trangthai = 1
                """;
        try (Connection conn = JdbcUtil.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);) {
            statement.setInt(1, maKM);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    int ma = rs.getInt("makhuyenmai");
                    String ten = rs.getString("tenkhuyenmai");
                    int soLuong = rs.getInt("soluong");
                    Date start = rs.getDate("ngaybatdau");
                    Date end = rs.getDate("ngayketthuc");
                    int apDung = rs.getInt("apdungchohoadontu");
                    int giaTri = rs.getInt("giatri");
                    int hinhThuc = rs.getInt("hinhthuc");
                    String mota = rs.getString("mota");
                    khuyenMaiDTO = new KhuyenMaiDTO(ma, ten, soLuong, start, end, apDung, giaTri, hinhThuc, 1, mota);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return khuyenMaiDTO;
    }

    public List<KhuyenMaiDTO> getKhuyenMaiBySoTien() {
        List<KhuyenMaiDTO> list = new ArrayList<>();
        String sql = """
                    SELECT *
                    FROM khuyenmai
                    WHERE hinhthuc = 2 AND trangthai = 1

                """;
        try (Connection conn = JdbcUtil.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                int maKM = rs.getInt("makhuyenmai");
                String tenKM = rs.getString("tenkhuyenmai");
                int soLuong = rs.getInt("soluong");
                Date ngayBD = rs.getDate("ngaybatdau");
                Date ngayKT = rs.getDate("ngayketthuc");
                int apDungChoHoaDonTu = rs.getInt("apdungchohoadontu");
                int giaTri = rs.getInt("giatri");
                int hinhThuc = rs.getInt("hinhthuc");
                String mota = rs.getString("mota");
                KhuyenMaiDTO khuyenMaiDTO = new KhuyenMaiDTO(maKM, tenKM, soLuong, ngayBD, ngayKT, apDungChoHoaDonTu,
                        giaTri, hinhThuc, 1, mota);
                list.add(khuyenMaiDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<KhuyenMaiDTO> getKhuyenMaiByPhanTram() {
        List<KhuyenMaiDTO> list = new ArrayList<>();
        String sql = """
                    SELECT *
                    FROM khuyenmai
                    WHERE hinhthuc = 1 AND trangthai = 1

                """;
        try (Connection conn = JdbcUtil.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                int maKM = rs.getInt("makhuyenmai");
                String tenKM = rs.getString("tenkhuyenmai");
                int soLuong = rs.getInt("soluong");
                Date ngayBD = rs.getDate("ngaybatdau");
                Date ngayKT = rs.getDate("ngayketthuc");
                int apDungChoHoaDonTu = rs.getInt("apdungchohoadontu");
                int giaTri = rs.getInt("giatri");
                int hinhThuc = rs.getInt("hinhthuc");
                String mota = rs.getString("mota");
                KhuyenMaiDTO khuyenMaiDTO = new KhuyenMaiDTO(maKM, tenKM, soLuong, ngayBD, ngayKT, apDungChoHoaDonTu,
                        giaTri, hinhThuc, 1, mota);
                list.add(khuyenMaiDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean giamSoLuongKM(int maKM) {
        KhuyenMaiDTO khuyenMaiDTO = getKhuyenMaiByID(maKM);
        String sql = """
                UPDATE khuyenmai SET soluong = ? WHERE makhuyenmai = ? AND trangthai = 1
                """;
        try (Connection conn = JdbcUtil.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, khuyenMaiDTO.getSoLuong() - 1);
            statement.setInt(2, maKM);

            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
