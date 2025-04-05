package BLL.BUS;

import DTO.SanPhamDTO;
import java.util.ArrayList;
import java.util.List;

import DAO.SanPhamDao;

public class SanPhamBLL { // Đổi tên lớp từ "SanPhamDAO" thành "SanPhamBLL"
    private final SanPhamDao sanPhamDao; // Đổi tên đối tượng từ "SanPhamBLL" thành "SanPhamDAO"

    public SanPhamBLL() {
        sanPhamDao = new SanPhamDao(); // Đổi tên tham chiếu đối tượng
    }

    public boolean addSanPham(SanPhamDTO sanPham) {
        return sanPhamDao.addSanPham(sanPham); // Đổi "SanPham" thành "sanPham"
    }

    public boolean updateSanPham(SanPhamDTO sanPham) {
        return sanPhamDao.updateSanPham(sanPham); // Đổi "SanPham" thành "sanPham"
    }
    
    public boolean updateSoluong(int maSP, int soluongMoi) {
        return sanPhamDao.updateSoluong(maSP, soluongMoi);
    }
   
    public boolean deleteSanPham(int maSP) {
        return sanPhamDao.deleteSanPham(maSP);
    }

    public SanPhamDTO getSanPhamById(int maSP) {
        return sanPhamDao.getSanPhamById(maSP);
    }

    public List<SanPhamDTO> getAllSanPham() {
        return sanPhamDao.getAllSanPham();
    }

    public List<SanPhamDTO> getSanPhamByNameSearch(String keyword, String type) {
        List<SanPhamDTO> sanPhamList = sanPhamDao.getAllSanPham(); // Đổi "SanPhamList" thành "sanPhamList"

        List<SanPhamDTO> filteredList = new ArrayList<>();

        for (SanPhamDTO sp : sanPhamList) { // Đổi "SanPhamList" thành "sanPhamList"
            switch (type.toLowerCase()) {
                case "tất cả" -> {
                    if (sp.getTenSP().toLowerCase().contains(keyword.toLowerCase())) {
                        filteredList.add(sp);
                    }
                }
                case "theo tên" -> {
                    if (sp.getTenSP().toLowerCase().contains(keyword.toLowerCase())) {
                        filteredList.add(sp);
                    }
                }
            }
        }
        return filteredList;
    }

    public int generateNewId() {
        return sanPhamDao.getAllSanPham().getLast().getMaSP() + 1; // Đổi "SanPhamDao" thành "sanPhamDao"
    }
}
