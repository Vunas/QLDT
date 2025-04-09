package BLL.BUS;

import DAO.PhieuNhapDao;
import DTO.PhieuNhapDTO;
import java.util.List;

public class PhieuNhapBLL {
    private PhieuNhapDao phieuNhapDao = new PhieuNhapDao();

    public List<PhieuNhapDTO> getAllPhieuNhap() {
        return phieuNhapDao.getAllPhieuNhap();
    }

    public PhieuNhapDTO getPhieuNhapById(int maPhieuNhap) {
        return phieuNhapDao.timPhieuNhap(maPhieuNhap);
    }

    public boolean addPhieuNhap(PhieuNhapDTO phieuNhap) {
        return phieuNhapDao.addPhieuNhap(phieuNhap);
    }

    public boolean deletePhieuNhap(int maPhieuNhap) {
        return phieuNhapDao.xoamemPhieuNhap(maPhieuNhap);
    }
    
    

    public int getMaPhieuNhap() {
        return phieuNhapDao.getMaPhieuNhap();
    }
}
