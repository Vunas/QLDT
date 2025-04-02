/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL.BUS;


import DAO.BrandDao;
import DTO.ThuocTinhSanPham.BrandDTO;
import java.util.ArrayList;
/**
 *
 * @author thaoh
 */
public class BrandBLL {
private final BrandDao lhDAO = new BrandDao();
    private ArrayList<BrandDTO> listLH = new ArrayList<>();

    public BrandBLL() {
        listLH = lhDAO.selectAll();
    }

    public ArrayList<BrandDTO> getAll() {
        return this.listLH;
    }

    public BrandDTO getByIndex(int index) {
        return this.listLH.get(index);
    }

    public int getIndexByMaLH(int maloaihang) {
        int i = 0;
        int vitri = -1;
        while (i < this.listLH.size() && vitri == -1) {
            if (listLH.get(i).getMathuonghieu() == maloaihang) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public Boolean add(String name) {
        BrandDTO lh = new BrandDTO(lhDAO.getAutoIncrement(), name);
        boolean check = lhDAO.insert(lh) != 0;
        if (check) {
            this.listLH.add(lh);
        }
        return check;
    }

    public Boolean delete(BrandDTO lh) {
        boolean check = lhDAO.delete(Integer.toString(lh.getMathuonghieu())) != 0;
        if (check) {
            this.listLH.remove(lh);
        }
        return check;
    }

    public Boolean update(BrandDTO lh) {
        boolean check = lhDAO.update(lh) != 0;
        if (check) {
            this.listLH.set(getIndexByMaLH(lh.getMathuonghieu()), lh);
        }
        return check;
    }

    public ArrayList<BrandDTO> search(String text) {
        text = text.toLowerCase();
        ArrayList<BrandDTO> result = new ArrayList<>();
        for (BrandDTO i : this.listLH) {
            if (Integer.toString(i.getMathuonghieu()).toLowerCase().contains(text) || i.getTenthuonghieu().toLowerCase().contains(text)) {
                result.add(i);
            }
        }
        return result;
    }

    public String[] getArrTenThuongHieu() {
        int size = listLH.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = listLH.get(i).getTenthuonghieu();
        }
        return result;
    }

    public String getTenThuongHieu(int mathuonghieu) {
        return this.listLH.get(this.getIndexByMaLH(mathuonghieu)).getTenthuonghieu();
    }
 
    public boolean checkDup(String name) {
        boolean check = true;
        int i = 0;
        while (i < this.listLH.size() && check) { // Fix the loop condition
            if (this.listLH.get(i).getTenthuonghieu().toLowerCase().contains(name.toLowerCase())) {
                check = false;
            } else {
                i++;
            }
        }
        return check;
    }
}
