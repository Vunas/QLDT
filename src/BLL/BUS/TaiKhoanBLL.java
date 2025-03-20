package BLL.BUS;

import DAO.TaiKhoanDao;
import DTO.TaiKhoanDTO;

import java.util.ArrayList;
import java.util.List;

public class TaiKhoanBLL {
    private TaiKhoanDao taiKhoanDao;

    // Constructor
    public TaiKhoanBLL() {
        // Khởi tạo DAO (giả sử đã có kết nối CSDL trong TaiKhoanDao)
        taiKhoanDao = new TaiKhoanDao();
    }

    // 1. Lấy danh sách tất cả tài khoản
    public List<TaiKhoanDTO> getAllTaiKhoan() {
        return taiKhoanDao.getAllTaiKhoan();
    }

    // 2. Thêm một tài khoản mới
    public boolean addTaiKhoan(TaiKhoanDTO taiKhoan) {
        // Thực hiện các kiểm tra hoặc xử lý nghiệp vụ tại đây (nếu cần)
        if (taiKhoan.getTenDangNhap() == null || taiKhoan.getTenDangNhap().isEmpty()) {
            throw new IllegalArgumentException("Tên đăng nhập không được để trống");
        }
        return taiKhoanDao.addTaiKhoan(taiKhoan);
    }

    // 3. Cập nhật thông tin tài khoản
    public boolean updateTaiKhoan(TaiKhoanDTO taiKhoan) {
        // Kiểm tra dữ liệu hợp lệ trước khi cập nhật
        if (taiKhoan.getMaNV() <= 0) {
            throw new IllegalArgumentException("Mã nhân viên không hợp lệ");
        }
        return taiKhoanDao.updateTaiKhoan(taiKhoan);
    }

    // 4. Xóa một tài khoản
    public boolean deleteTaiKhoan(int maNV) {
        // Thực hiện các logic trước khi xóa (ví dụ: kiểm tra quyền hạn, ...)
        return taiKhoanDao.deleteTaiKhoan(maNV);
    }

    // 5. Kiểm tra thông tin đăng nhập
    public TaiKhoanDTO login(String tenDangNhap, String matKhau) {
        TaiKhoanDTO taiKhoan = taiKhoanDao.login(tenDangNhap, matKhau);
        if (taiKhoan == null) {
            throw new IllegalArgumentException("Thông tin đăng nhập không hợp lệ");
        }
        return taiKhoan;
    }

    public TaiKhoanDTO getTaiKhoanByTenDangNhap(String tenDangNhap){
        return taiKhoanDao.getTaiKhoanByTenDangNhap(tenDangNhap);
    }

    // Lấy danh sách tài khoản theo tên đăng nhập tìm kiếm
    public List<TaiKhoanDTO> getTaiKhoanByNameSearch(String keyword) {
        // Lấy danh sách tất cả tài khoản từ DAO
        List<TaiKhoanDTO> taiKhoanList = taiKhoanDao.getAllTaiKhoan();

        // Tạo danh sách kết quả để lưu tài khoản phù hợp
        List<TaiKhoanDTO> filteredList = new ArrayList<>();

        // Tìm kiếm theo tên đăng nhập
        for (TaiKhoanDTO taiKhoan : taiKhoanList) {
            if (taiKhoan.getTenDangNhap().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(taiKhoan);
            }
        }

        // Trả về danh sách tài khoản khớp với tiêu chí tìm kiếm
        return filteredList;
    }

}
