package BLL.BUS;

import java.util.ArrayList;
import java.util.List;

import DAO.KhachHangDao;
import DTO.KhachHangDTO;

public class KhachHangBLL {
    private KhachHangDao khachHangDao;

    public KhachHangBLL() {
        khachHangDao = new KhachHangDao();
    }

    // Thêm khách hàng mới
    public boolean addKhachHang(KhachHangDTO khachHang) {
        // Kiểm tra logic kinh doanh ở đây nếu cần
        return khachHangDao.addKhachHang(khachHang);
    }

    // Cập nhật thông tin khách hàng
    public boolean updateKhachHang(KhachHangDTO khachHang) {
        // Kiểm tra logic kinh doanh ở đây nếu cần
        return khachHangDao.updateKhachHang(khachHang);
    }

    // Xóa khách hàng
    public boolean deleteKhachHang(int maKH) {
        // Kiểm tra logic kinh doanh ở đây nếu cần
        return khachHangDao.xoaMemKhachHang(maKH);
    }

    // Lấy thông tin khách hàng theo mã
    public KhachHangDTO getKhachHangById(int maKH) {
        return khachHangDao.getKhachHangById(maKH);
    }

    // Lấy danh sách tất cả khách hàng
    public List<KhachHangDTO> getAllKhachHang() {
        return khachHangDao.getAllKhachHang();
    }

    // Lấy danh sách khách hàng theo tên tìm kiếm
    public List<KhachHangDTO> getKhachHangByNameSearch(String keyword, String type) {
        // Lấy danh sách tất cả khách hàng từ DAO
        List<KhachHangDTO> khachHangList = khachHangDao.getAllKhachHang();

        // Tạo danh sách kết quả để lưu khách hàng phù hợp
        List<KhachHangDTO> filteredList = new ArrayList<>();

        // Tìm kiếm theo tiêu chí
        for (KhachHangDTO kh : khachHangList) {
            switch (type.toLowerCase()) {
                case "tất cả":
                    if (kh.getHoTen().toLowerCase().contains(keyword.toLowerCase()) ||
                            kh.getDiaChi().toLowerCase().contains(keyword.toLowerCase()) ||
                            kh.getSdt().toLowerCase().contains(keyword.toLowerCase())) {
                        filteredList.add(kh); // Thêm khách hàng phù hợp vào danh sách kết quả
                    }
                    break;
                case "theo tên":
                    if (kh.getHoTen().toLowerCase().contains(keyword.toLowerCase())) {
                        filteredList.add(kh);
                    }
                    break;
                case "theo sdt":
                    if (kh.getSdt().toLowerCase().contains(keyword.toLowerCase())) {
                        filteredList.add(kh);
                    }
                    break;
            }
        }

        // Trả về danh sách khách hàng khớp với tiêu chí tìm kiếm
        return filteredList;
    }

    public int generateNewId() {
        return khachHangDao.getAllKhachHang().getLast().getMaKH() + 1;
    }
    
    public String[] getNameKhachHang(){
        return khachHangDao.getNameKhachHang();
    }
    
    public KhachHangDTO getKhachHangByName(String ten){
        return khachHangDao.getKhachHangByName(ten);
    }
}
