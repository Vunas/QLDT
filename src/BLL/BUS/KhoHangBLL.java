package BLL.BUS;

import java.util.ArrayList;
import java.util.List;

import DAO.KhoHangDao;
import DTO.KhoHangDTO;

public class KhoHangBLL {
    private KhoHangDao khoHangDao;

    public KhoHangBLL() {
        khoHangDao = new KhoHangDao();
    }

    // Thêm kho hàng
    public boolean addKhoHang(KhoHangDTO khoHang) {
        // Kiểm tra logic kinh doanh nếu cần
        if (khoHang.getTenKho() == null || khoHang.getTenKho().isEmpty()) {
            System.out.println("Tên kho không được để trống!");
            return false;
        }
        return khoHangDao.addKhoHang(khoHang);
    }

    // Cập nhật thông tin kho hàng
    public boolean updateKhoHang(KhoHangDTO khoHang) {
        // Kiểm tra logic kinh doanh nếu cần
        if (khoHang.getMaKho() <= 0) {
            System.out.println("Mã kho không hợp lệ!");
            return false;
        }
        return khoHangDao.updateKhoHang(khoHang);
    }

    // Xóa kho hàng
    public boolean deleteKhoHang(int maKho) {
        // Kiểm tra logic kinh doanh nếu cần
        if (maKho <= 0) {
            System.out.println("Mã kho không hợp lệ!");
            return false;
        }
        return khoHangDao.xoaMemKhoHang(maKho);
    }

    // Lấy thông tin kho hàng theo mã
    public KhoHangDTO getKhoHangById(int maKho) {
        if (maKho <= 0) {
            System.out.println("Mã kho không hợp lệ!");
            return null;
        }
        return khoHangDao.getKhoHangById(maKho);
    }

    // Lấy danh sách tất cả kho hàng
    public List<KhoHangDTO> getAllKhoHang() {
        return khoHangDao.getAllKhoHang();
    }

    // Lấy danh sách kho hàng theo tên tìm kiếm
    public List<KhoHangDTO> getKhoHangByNameSearch(String keyword) {
        // Lấy danh sách tất cả kho hàng từ DAO
        List<KhoHangDTO> khoHangList = khoHangDao.getAllKhoHang();

        // Tạo danh sách kết quả để lưu kho hàng phù hợp
        List<KhoHangDTO> filteredList = new ArrayList<>();

        // Tìm kiếm theo tên
        for (KhoHangDTO kho : khoHangList) {
            if (kho.getTenKho().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(kho);
            }
        }

        // Trả về danh sách kho hàng khớp với tiêu chí tìm kiếm
        return filteredList;
    }

    public int generateNewId() {
        // Giả định danh sách được sắp xếp theo mã tăng dần
        List<KhoHangDTO> allKhoHang = khoHangDao.getAllKhoHang();
        return allKhoHang.get(allKhoHang.size() - 1).getMaKho() + 1;
    }
}
