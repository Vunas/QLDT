package BLL.BUS;

import java.util.ArrayList;
import java.util.List;

import DAO.QuyenDao;
import DTO.QuyenDTO;

public class QuyenBLL {
    private QuyenDao quyenDao;

    public QuyenBLL() {
        quyenDao = new QuyenDao();
    }

    // Thêm quyền
    public boolean addQuyen(QuyenDTO quyen) {
        return quyenDao.addQuyen(quyen);
    }

    // Cập nhật thông tin quyền
    public boolean updateQuyen(QuyenDTO quyen) {
        return quyenDao.updateQuyen(quyen);
    }

    // Xóa quyền
    public boolean deleteQuyen(int maQuyen) {
        return quyenDao.xoaMemQuyen(maQuyen);
    }

    // Lấy thông tin quyền theo mã
    public QuyenDTO getQuyenById(int maQuyen) {
        return quyenDao.getQuyenById(maQuyen);
    }

    // Lấy danh sách tất cả quyền
    public List<QuyenDTO> getAllQuyen() {
        return quyenDao.getAllQuyen();
    }

    // Lấy danh sách quyền theo tên tìm kiếm
    public List<QuyenDTO> getQuyenByNameSearch(String keyword) {
        // Lấy danh sách tất cả quyền từ DAO
        List<QuyenDTO> quyenList = quyenDao.getAllQuyen();

        // Tạo danh sách kết quả để lưu quyền phù hợp
        List<QuyenDTO> filteredList = new ArrayList<>();

        // Tìm kiếm theo tên
        for (QuyenDTO quyen : quyenList) {
            if (quyen.getTenQuyen().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(quyen);
            }
        }

        // Trả về danh sách quyền khớp với tiêu chí tìm kiếm
        return filteredList;
    }
}
