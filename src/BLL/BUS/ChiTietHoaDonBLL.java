package BLL.BUS;

import DAO.ChiTietHoaDonDao;
import DTO.ChiTietHoaDonDTO;
import java.util.List;

public class ChiTietHoaDonBLL {
    private ChiTietHoaDonDao chiTietHoaDonDao = new ChiTietHoaDonDao();

    public boolean addChiTietHoaDon(ChiTietHoaDonDTO cthd) {
        return chiTietHoaDonDao.addChiTietHoaDon(cthd);
    }

    public List<ChiTietHoaDonDTO> getChiTietTheoMaHoaDon(int maHoaDon) {
        return chiTietHoaDonDao.getChiTietHoaDonByMaHoaDon(maHoaDon);
    }

    public boolean deleteChiTietHoaDon(int maHoaDon) {
        return chiTietHoaDonDao.xoaMemChiTietHoaDon(maHoaDon);
    }
    
    public boolean capNhatMaBaoHanh(int maHD,int maPBH){
        return chiTietHoaDonDao.capNhatMaBaoHanh(maHD,maPBH);
    }
}
