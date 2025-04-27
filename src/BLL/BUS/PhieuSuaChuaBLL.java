/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL.BUS;

import DAO.PhieuSuaChuaDao;
import DTO.PhieuSuaChuaDTO;
import java.util.List;

/**
 *
 * @author thaoh
 */
public class PhieuSuaChuaBLL {
    private final PhieuSuaChuaDao phieuSuaChuaDao;
    public PhieuSuaChuaBLL(){
        phieuSuaChuaDao = new PhieuSuaChuaDao();
    }
    public boolean add(PhieuSuaChuaDTO PSC){
        return phieuSuaChuaDao.add(PSC);
    }
    public boolean updateTrangThai(int maSC , String trangThai){
        return phieuSuaChuaDao.updateTrangThai(maSC,trangThai);
    }       
    public List<PhieuSuaChuaDTO> getByMaPhieuBH(int maPhieuBH){
        return phieuSuaChuaDao.getByMaPhieuBH(maPhieuBH);
    }
    public PhieuSuaChuaDTO getById(int maPSC){
        return phieuSuaChuaDao.getById(maPSC);
    }
    public List<PhieuSuaChuaDTO> getByMaPhieuBHAndIMEI(int maPBH , String imei){
        return phieuSuaChuaDao.getByMaPhieuBHAndIMEI(maPBH , imei);
    }
}
