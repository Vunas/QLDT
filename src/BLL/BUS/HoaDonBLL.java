package BLL.BUS;


import DAO.HoaDonDao;
import DTO.HoaDonDTO;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nguyen
 */
public class HoaDonBLL {
    private HoaDonDao hoadondao = new HoaDonDao();
    
    public boolean addHoaDon (HoaDonDTO hd){
        return hoadondao.addHoaDon(hd);
    }
    
    public List<HoaDonDTO> getAllHoaDon(){
        return hoadondao.getAllHoaDon();
    }
    
     public boolean deleteHoaDon(int mahoadon){
        return hoadondao.deleteHoaDon(mahoadon);
     }
     
      public int getMaHoaDon(){
          return hoadondao.getMaHoaDon();
      }
      
     public HoaDonDTO getHoaDonById(int mahoadon){
         return hoadondao.timHoaDon(mahoadon);
     }
}
