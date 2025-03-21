package BLL.BUS;

import DAO.TaiKhoanDao;
import DTO.QuyenDTO;
import DTO.TaiKhoanDTO;
import GUI.Panel.ItemBar;
import GUI.Panel.SideBar;
import GUI.Panel.TopNav;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

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

    public void chinhSuaQuyen(SideBar sideBar, QuyenDTO quyenDTO){
        String[] chucNangList = quyenDTO.getDanhSachChucNang().split("/"); // Tách chuỗi quyền theo dấu "/"
        ItemBar[] itemBars = sideBar.getItemBars();
        for (int i = 0; i < chucNangList.length; i++) {
            if (!chucNangList[i].contains("r")){
                itemBars[i+1].setVisible(false);
            }
        }   
    }

    public void chinhSuaChucNang(TopNav topNav, QuyenDTO quyenDTO, int index){
        String[] chucNangList = quyenDTO.getDanhSachChucNang().split("/");
        String chucNang = chucNangList[index - 1];
        JButton[] btn= topNav.getBtn();
        if (!chucNang.contains("c")){
            btn[0].setVisible(false);
        }
        if (!chucNang.contains("f")){
            btn[1].setVisible(false);
        }
        if (!chucNang.contains("d")){
            btn[2].setVisible(false);
        }
        if (!chucNang.contains("cfd")){
            btn[4].setVisible(false);
        }
    }
}
