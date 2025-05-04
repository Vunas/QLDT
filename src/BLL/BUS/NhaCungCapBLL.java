package BLL.BUS;

import java.util.ArrayList;
import java.util.List;

import DAO.NhaCungCapDao;
import DTO.NhaCungCapDTO;

public class NhaCungCapBLL {
    private NhaCungCapDao nhaCungCapDao;

    public NhaCungCapBLL() {
        nhaCungCapDao = new NhaCungCapDao();
    }

    // Thêm nhà cung cấp
    public boolean addNhaCungCap(NhaCungCapDTO nhaCungCap) {
        // Kiểm tra logic kinh doanh ở đây nếu cần
        return nhaCungCapDao.addNhaCungCap(nhaCungCap);
    }

    // Cập nhật thông tin nhà cung cấp
    public boolean updateNhaCungCap(NhaCungCapDTO nhaCungCap) {
        // Kiểm tra logic kinh doanh ở đây nếu cần
        return nhaCungCapDao.updateNhaCungCap(nhaCungCap);
    }

    // Xóa nhà cung cấp
    public boolean deleteNhaCungCap(int maNCC) {
        // Kiểm tra logic kinh doanh ở đây nếu cần
        return nhaCungCapDao.xoaMemNhaCungCap(maNCC);
    }

    // Lấy thông tin nhà cung cấp theo mã
    public NhaCungCapDTO getNhaCungCapById(int maNCC) {
        return nhaCungCapDao.getNhaCungCapById(maNCC);
    }
    
    public NhaCungCapDTO getNhaCungCapByName(String ten){
        return nhaCungCapDao.getNhaCungCapByName(ten);
    }

    // Lấy danh sách tất cả nhà cung cấp
    public List<NhaCungCapDTO> getAllNhaCungCap() {
        return nhaCungCapDao.getAllNhaCungCap();
    }

    // Lấy danh sách nhà cung cấp theo tên tìm kiếm
    public List<NhaCungCapDTO> getNhaCungCapByNameSearch(String keyword, String type) {
        // Lấy danh sách tất cả nhà cung cấp từ DAO
        List<NhaCungCapDTO> nhaCungCapList = nhaCungCapDao.getAllNhaCungCap();

        // Tạo danh sách kết quả để lưu nhà cung cấp phù hợp
        List<NhaCungCapDTO> filteredList = new ArrayList<>();

        // Tìm kiếm theo tiêu chí
        for (NhaCungCapDTO ncc : nhaCungCapList) {
            switch (type.toLowerCase()) {
                case "tất cả":
                    if (ncc.getTen().toLowerCase().contains(keyword.toLowerCase()) ||
                            ncc.getsDT().toLowerCase().contains(keyword.toLowerCase())) {
                        filteredList.add(ncc); // Thêm nhà cung cấp phù hợp vào danh sách kết quả
                    }
                    break;
                case "theo tên":
                    if (ncc.getTen().toLowerCase().contains(keyword.toLowerCase())) {
                        filteredList.add(ncc);
                    }
                    break;
                case "theo sdt":
                    if (ncc.getsDT().toLowerCase().contains(keyword.toLowerCase())) {
                        filteredList.add(ncc);
                    }
                    break;
            }
        }

        // Trả về danh sách nhà cung cấp khớp với tiêu chí tìm kiếm
        return filteredList;
    }

    public int generateNewId() {
        // Giả định danh sách được sắp xếp theo mã tăng dần
        List<NhaCungCapDTO> allNhaCungCap = nhaCungCapDao.getAllNhaCungCapToAdd();
        return allNhaCungCap.get(allNhaCungCap.size() - 1).getMaNhaCungCap() + 1;
    }
    
    public String[] getNameNhaCungCap(){
        return nhaCungCapDao.getNameNhaCungCap();
    }
}
