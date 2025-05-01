package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.ThongKeDTO;
import util.JdbcUtil;

public class ThongKeDao {
    public List<ThongKeDTO> ThongKeDoanhThu() {
        List<ThongKeDTO> thongKeDTOList = new ArrayList<>();
        String sql = """
                    SELECT h.ngayxuat, SUM(ct.soluong * ct.dongia) AS tong_doanh_thu
                    FROM hoadon h
                    JOIN chitiethoadon ct ON h.mahoadon = ct.mahoadon
                    WHERE h.trangthai = 1
                    GROUP BY h.ngayxuat
                    ORDER BY h.ngayxuat;
                """;
        try (Connection conn = JdbcUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int tongDoanhThu = rs.getInt("tong_doanh_thu");
                Date ngayXuat = rs.getDate("ngayxuat");
                ThongKeDTO thongKeDTO = new ThongKeDTO(tongDoanhThu, ngayXuat);
                thongKeDTOList.add(thongKeDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thongKeDTOList;
    }

    public List<ThongKeDTO> ThongKeDoanhThuByFilter(ThongKeDTO thongke) {
        List<ThongKeDTO> thongKeDTOList = new ArrayList<>();
        String sql = """
                    SELECT h.ngayxuat, SUM(ct.soluong * ct.dongia) AS tong_doanh_thu
                    FROM hoadon h
                    JOIN chitiethoadon ct ON h.mahoadon = ct.mahoadon
                    WHERE h.trangthai = 1 AND h.ngayxuat BETWEEN ? AND ?
                    GROUP BY h.ngayxuat
                    ORDER BY h.ngayxuat
                """;
        try (Connection conn = JdbcUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(thongke.getStart()));
            stmt.setDate(2, Date.valueOf(thongke.getEnd()));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int tongDoanhThu = rs.getInt("tong_doanh_thu");
                Date ngayXuat = rs.getDate("ngayxuat");
                ThongKeDTO thongKeDTO = new ThongKeDTO(tongDoanhThu, ngayXuat);
                thongKeDTOList.add(thongKeDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thongKeDTOList;
    }

    public List<ThongKeDTO> ThongKeSanPham() {
        List<ThongKeDTO> list = new ArrayList<>();
        String sql = """
                SELECT sp.tenSP, cthd.masanpham, SUM(cthd.soluong) AS so_luong_ban_ra
                FROM hoadon hd
                JOIN chitiethoadon cthd
                ON hd.mahoadon = cthd.mahoadon
                JOIN sanpham sp ON cthd.masanpham = sp.maSP
                WHERE hd.trangthai = 1
                GROUP BY cthd.masanpham, sp.tenSP
                """;
        try (Connection conn = JdbcUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int maSP = rs.getInt("masanpham");
                String tenSp = rs.getString("tenSP");
                int soluong = rs.getInt("so_luong_ban_ra");
                ThongKeDTO thongKeDTO = new ThongKeDTO(maSP, tenSp, soluong);
                list.add(thongKeDTO);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        return list;
    }

    public List<ThongKeDTO> ThongKeSanPhamByFilter(ThongKeDTO thongke) {
        List<ThongKeDTO> list = new ArrayList<>();
        String sql = """
                SELECT sp.tenSP, cthd.masanpham, SUM(cthd.soluong) AS so_luong_ban_ra
                FROM hoadon hd
                JOIN chitiethoadon cthd
                ON hd.mahoadon = cthd.mahoadon
                JOIN sanpham sp ON cthd.masanpham = sp.maSP
                WHERE hd.trangthai = 1 AND hd.ngayxuat BETWEEN ? AND ?
                GROUP BY cthd.masanpham, sp.tenSP
                """;
        try (Connection conn = JdbcUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(thongke.getStart()));
            stmt.setDate(2, Date.valueOf(thongke.getEnd()));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int maSP = rs.getInt("masanpham");
                String tenSp = rs.getString("tenSP");
                int soluong = rs.getInt("so_luong_ban_ra");
                ThongKeDTO thongKeDTO = new ThongKeDTO(maSP, tenSp, soluong);
                list.add(thongKeDTO);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        return list;
    }

    public List<ThongKeDTO> ThongKeKhachHang() {
        List<ThongKeDTO> list = new ArrayList<>();
        String sql = """
                SELECT hd.makhachhang, kh.ten, sum(cthd.soluong * cthd.dongia) AS tong_gia_tri_mua_hang
                FROM khach_hang kh
                JOIN hoadon hd ON kh.maKH = hd.makhachhang
                JOIN chitiethoadon cthd ON hd.mahoadon = cthd.mahoadon
                WHERE cthd.trangthai = 1
                GROUP BY hd.makhachhang
                """;

        try (Connection conn = JdbcUtil.getConnection();
                PreparedStatement statement1 = conn.prepareStatement(sql);
                ResultSet rs1 = statement1.executeQuery()) {

            while (rs1.next()) {
                int maKH = rs1.getInt("makhachhang");
                String tenKH = rs1.getString("ten");
                int tong_gia_tri_mua_hang = rs1.getInt("tong_gia_tri_mua_hang");
                ThongKeDTO thongKeDTO = new ThongKeDTO(maKH, tenKH, tong_gia_tri_mua_hang);
                list.add(thongKeDTO);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return list;
    }

    public List<ThongKeDTO> ThongKeKhachHangByFilter(ThongKeDTO thongke) {
        List<ThongKeDTO> list = new ArrayList<>();
        String sql = """
                SELECT hd.makhachhang, kh.ten, sum(cthd.soluong * cthd.dongia) AS tong_gia_tri_mua_hang
                FROM khach_hang kh
                JOIN hoadon hd ON kh.maKH = hd.makhachhang
                JOIN chitiethoadon cthd ON hd.mahoadon = cthd.mahoadon
                WHERE cthd.trangthai = 1 AND hd.ngayxuat BETWEEN ? AND ?
                GROUP BY hd.makhachhang
                """;

        try (Connection conn = JdbcUtil.getConnection();
                PreparedStatement statement1 = conn.prepareStatement(sql)) {
            statement1.setDate(1, Date.valueOf(thongke.getStart()));
            statement1.setDate(2, Date.valueOf(thongke.getEnd()));
            ResultSet rs1 = statement1.executeQuery();
            while (rs1.next()) {
                int maKH = rs1.getInt("makhachhang");
                String tenKH = rs1.getString("ten");
                int tong_gia_tri_mua_hang = rs1.getInt("tong_gia_tri_mua_hang");
                ThongKeDTO thongKeDTO = new ThongKeDTO(maKH, tenKH, tong_gia_tri_mua_hang);
                list.add(thongKeDTO);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return list;
    }

    public int ThongKeTongKhachHang() {
        int TongKhachHang = 0;
        String sql = """
                    SELECT COUNT(DISTINCT cthd.mahoadon) AS tong_khach_hang
                    FROM  chitiethoadon cthd
                    WHERE cthd.trangthai = 1
                """;
        try (Connection conn = JdbcUtil.getConnection();
                PreparedStatement statement2 = conn.prepareStatement(sql);
                ResultSet rs2 = statement2.executeQuery()) {
            if (rs2.next()) {
                TongKhachHang = rs2.getInt("tong_khach_hang");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        return TongKhachHang;
    }

    public List<ThongKeDTO> ThongKeNhanVien() {
        List<ThongKeDTO> list = new ArrayList<>();
        String sql = """
                    SELECT nv.MaNV, nv.HoTen, COUNT(nv.MaNV) AS nhan_vien_ban_duoc_hang_nhieu_nhat
                    FROM nhanvien nv
                    JOIN hoadon hd ON hd.manhanvien = nv.MaNV
                    JOIN chitiethoadon cthd ON hd.mahoadon = cthd.mahoadon
                    WHERE cthd.trangthai = 1
                    GROUP BY hd.manhanvien
                """;
        try (Connection conn = JdbcUtil.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                int ma = rs.getInt("MaNV");
                String ten = rs.getString("HoTen");
                int soLuong = rs.getInt("nhan_vien_ban_duoc_hang_nhieu_nhat");
                ThongKeDTO thongKeDTO = new ThongKeDTO(ma, ten, soLuong);
                list.add(thongKeDTO);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return list;
    }

    public List<ThongKeDTO> ThongKeNhanVienByFilter(ThongKeDTO thongke) {
        List<ThongKeDTO> list = new ArrayList<>();
        String sql = """
                    SELECT nv.MaNV, nv.HoTen, COUNT(nv.MaNV) AS nhan_vien_ban_duoc_hang_nhieu_nhat
                    FROM nhanvien nv
                    JOIN hoadon hd ON hd.manhanvien = nv.MaNV
                    JOIN chitiethoadon cthd ON hd.mahoadon = cthd.mahoadon
                    WHERE cthd.trangthai = 1 AND hd.ngayxuat BETWEEN ? AND ?
                    GROUP BY hd.manhanvien
                """;
        try (Connection conn = JdbcUtil.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setDate(1, Date.valueOf(thongke.getStart()));
            statement.setDate(2, Date.valueOf(thongke.getEnd()));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int ma = rs.getInt("MaNV");
                String ten = rs.getString("HoTen");
                int soLuong = rs.getInt("nhan_vien_ban_duoc_hang_nhieu_nhat");
                ThongKeDTO thongKeDTO = new ThongKeDTO(ma, ten, soLuong);
                list.add(thongKeDTO);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return list;
    }

    public int ThongKeTongNhanVien() {
        int TongNhanVien = 0;
        String sql = """
                    SELECT COUNT(DISTINCT nv.MaNV) AS tong_nhan_vien
                    FROM nhanvien nv
                    JOIN hoadon hd ON hd.manhanvien = nv.MaNV
                    JOIN chitiethoadon cthd ON hd.mahoadon = cthd.mahoadon
                    WHERE cthd.trangthai = 1
                """;
        try (Connection conn = JdbcUtil.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                TongNhanVien = rs.getInt("tong_nhan_vien");
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return TongNhanVien;
    }
}
