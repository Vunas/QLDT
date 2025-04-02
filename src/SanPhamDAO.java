


import BLL.BUS.*;
import DTO.SanPhamDTO;
import java.util.ArrayList;
import java.util.List;


public class SanPhamDAO {
        private final DAO.SanPhamBLL SanPhamDao;

    public SanPhamDAO() {
        SanPhamDao = new DAO.SanPhamBLL();
    }

    
    public boolean addSanPham(SanPhamDTO SanPham) {
        
        return SanPhamDao.addSanPham(SanPham);
    }

    
    public boolean updateSanPham(SanPhamDTO SanPham) {
        
        return SanPhamDao.updateSanPham(SanPham);
    }

   
    public boolean deleteSanPham(int maSP) {
        
        return SanPhamDao.deleteSanPham(maSP);
    }

   
    public SanPhamDTO getSanPhamById(int maSP) {
        return SanPhamDao.getSanPhamById(maSP);
    }

   
    public List<SanPhamDTO> getAllSanPham() {
        return SanPhamDao.getAllSanPham();
    }

    
    public List<SanPhamDTO> getSanPhamByNameSearch(String keyword, String type) {
        
        List<SanPhamDTO> SanPhamList = SanPhamDao.getAllSanPham();

        
        List<SanPhamDTO> filteredList = new ArrayList<>();

        for (SanPhamDTO sp : SanPhamList) {
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
        return SanPhamDao.getAllSanPham().getLast().getMaSP()+ 1;
    }
}
