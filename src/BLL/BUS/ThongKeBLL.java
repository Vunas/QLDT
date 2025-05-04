package BLL.BUS;

import java.util.List;
import DAO.ThongKeDao;
import DTO.ThongKeDTO;
public class ThongKeBLL{
    private ThongKeDao thongke;

    public ThongKeBLL() {
        this.thongke = new ThongKeDao();
    }

    public List<ThongKeDTO> thongKeDoanhThu(){
        return thongke.ThongKeDoanhThu();
    }

    public List<ThongKeDTO> thongKeDoanhThuByFilter(ThongKeDTO thongKeDTO){
        return thongke.ThongKeDoanhThuByFilter(thongKeDTO);
    }

    public List<ThongKeDTO> thongKeSanPham(){
        return thongke.ThongKeSanPham();
    }

    public List<ThongKeDTO> thongKeSanPhamByFilter(ThongKeDTO thongKeDTO){
        return thongke.ThongKeSanPhamByFilter(thongKeDTO);
    }

    public List<ThongKeDTO> thongKeKhachHang(){
        return thongke.ThongKeKhachHang();
    }

    public List<ThongKeDTO> thongKeKhachHangByFilter(ThongKeDTO thongKeDTO){
        return thongke.ThongKeKhachHangByFilter(thongKeDTO);
    }

    public List<ThongKeDTO> thongKeNhanVien(){
        return thongke.ThongKeNhanVien();
    }

    public List<ThongKeDTO> thongKeNhanVienByFilter(ThongKeDTO thongKeDTO){
        return thongke.ThongKeNhanVienByFilter(thongKeDTO);
    }

    public int thongKeTongKhachHang(){
        return thongke.ThongKeTongKhachHang();
    }

    public int thongKeTongNhanVien(){
        return thongke.ThongKeTongNhanVien();
    }
}
