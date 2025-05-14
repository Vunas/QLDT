package BLL.BUS;

import java.util.ArrayList;
import java.util.List;

import DAO.KhuyenMaiDao;
import DTO.KhuyenMaiDTO;

public class KhuyenMaiBLL {
    private KhuyenMaiDao khuyenmai;

    public KhuyenMaiBLL() {
        this.khuyenmai = new KhuyenMaiDao();
    }

    public List<KhuyenMaiDTO> getAllKhuyenMai() {
        return khuyenmai.getALLKhuyenMai();
    }

    public boolean autoUpdateTrangThai(List<KhuyenMaiDTO> khuyenMaiDTO) {
        return khuyenmai.autoUpdateTrangThai(khuyenMaiDTO);
    }

    public boolean addKhuyenMai(KhuyenMaiDTO khuyenMaiDTO) {
        return khuyenmai.addKhuyenMai(khuyenMaiDTO);
    }

    public boolean deleteKhuyenMai(int maKM) {
        return khuyenmai.deleteKhuyenMai(maKM);
    }

    public boolean updateKhuyenMai(KhuyenMaiDTO khuyenMaiDTO) {
        return khuyenmai.updateKhuyenMai(khuyenMaiDTO);
    }

    public KhuyenMaiDTO getKhuyenMaiByID(int ma) {
        return khuyenmai.getKhuyenMaiByID(ma);
    }

    public KhuyenMaiDTO getKhuyenMaiByIDignoreTrangThai(int ma) {
        return khuyenmai.getKhuyenMaiByIDignoreTrangThai(ma);
    }

    public int generateNewId() {
        return khuyenmai.getALLKhuyenMaiToAdd().getLast().getMaKM() + 1;
    }

    public List<KhuyenMaiDTO> getKhuyenMaiByFilter(String type) {
        List<KhuyenMaiDTO> filterKhuyenMaiDTOs = new ArrayList<>();

        switch (type) {
            case "Tất Cả":
                filterKhuyenMaiDTOs = khuyenmai.getALLKhuyenMai();
                break;
            case "Theo Số Tiền":
                filterKhuyenMaiDTOs = khuyenmai.getKhuyenMaiBySoTien();
                break;
            case "Theo Phần Trăm":
                filterKhuyenMaiDTOs = khuyenmai.getKhuyenMaiByPhanTram();
                break;
            default:
                break;
        }
        return filterKhuyenMaiDTOs;
    }

    public boolean giamSoLuongKM(int maKM) {
        return khuyenmai.giamSoLuongKM(maKM);
    }

    public List<KhuyenMaiDTO> search(String word) {
        return khuyenmai.getKhuyenMaiByName(word);
    }
}
