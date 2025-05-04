package BLL.BUS;

import DTO.SanPhamDTO;
import java.util.ArrayList;
import java.util.List;

import DAO.SanPhamDao;

public class SanPhamBLL {
    private final SanPhamDao sanPhamDao;

    public SanPhamBLL() {
        sanPhamDao = new SanPhamDao();
    }

    public boolean addSanPham(SanPhamDTO sanPham) {
        return sanPhamDao.addSanPham(sanPham);
    }

    public boolean updateSanPham(SanPhamDTO sanPham) {
        return sanPhamDao.updateSanPham(sanPham);
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

    public SanPhamDTO getSanPhamByIdNoStatus(int maSP) {
        return sanPhamDao.getSanPhamByIdNoStatus(maSP);
    }

    public SanPhamDTO getSanPhamByName(String nameSP) {
        return sanPhamDao.getSanPhamByName(nameSP);
    }

    public List<SanPhamDTO> getAllSanPham() {
        return sanPhamDao.getAllSanPham();
    }

    public List<SanPhamDTO> getSanPhamByNameSearch(String keyword, String type) {
        List<SanPhamDTO> sanPhamList = sanPhamDao.getAllSanPham();

        List<SanPhamDTO> filteredList = new ArrayList<>();

        for (SanPhamDTO sp : sanPhamList) {
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
        List<SanPhamDTO> list = sanPhamDao.getAllSanPhamToAdd();
        if (list.isEmpty()) {
            return 1;
        } else {
            return list.getLast().getMaSP() + 1;
        }
    }
}
