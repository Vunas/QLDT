/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.DiaLog;

import BLL.BUS.ChiTietHoaDonBLL;
import BLL.BUS.ChiTietSanPhamBLL;
import BLL.BUS.HoaDonBLL;
import BLL.BUS.KhachHangBLL;
import BLL.BUS.SanPhamBLL;
import DTO.ChiTietHoaDonDTO;
import DTO.HoaDonDTO;
import DTO.KhachHangDTO;
import DTO.SanPhamDTO;
import DTO.TaiKhoanDTO;
import GUI.Panel.InputType.InputText;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguyen
 */
public class HoaDonDiaLog extends JDialog{
    JPanel top,center,center_top,center_mid;
    JTable thongtinchitiet,chitietimei;
    JScrollPane scrollthongtinchitiet,scrollchitietimei;
    DefaultTableModel dftmthongtinchitiet,dftmchitietimei;
    JLabel title;
    InputText mahoadon,nhanviennhap,khachhang;
    int maHD;
    
    public HoaDonDiaLog(JFrame main, int maPN ) {
        super(main,"",true);
        this.maHD = maPN;
        initComponent();
        this.setLocationRelativeTo(main);
        loaddata();
        loaddataimei();
        
    }
    
    public void initComponent(){
        this.setSize(new Dimension(1100, 500));
        this.setLayout(new BorderLayout(0, 0));

        
        top = new JPanel();
        top.setLayout(new BorderLayout());
        top.setBackground(new Color(22, 122, 198));
        top.setPreferredSize(new Dimension(400, 60));

        title = new JLabel("CHI TIẾT PHIẾU NHẬP");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        
        center = new JPanel();
        center.setLayout(new BorderLayout());
        
        thongtinchitiet = new JTable();
        scrollthongtinchitiet = new JScrollPane();
        dftmthongtinchitiet = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String[] headerct = new String[]{"Mã SP", "Tên SP", "RAM", "ROM", "Màu sắc", "Đơn giá", "Số lượng"};
        dftmthongtinchitiet.setColumnIdentifiers(headerct);
        thongtinchitiet.setModel(dftmthongtinchitiet);
        thongtinchitiet.setFocusable(false);
        scrollthongtinchitiet.setViewportView(thongtinchitiet);
        
        
        
        chitietimei = new JTable();
        scrollchitietimei = new JScrollPane();
        dftmchitietimei = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String[] headerimei = new String[]{"STT", "Imei"};
        dftmchitietimei.setColumnIdentifiers(headerimei);
        chitietimei.setModel(dftmchitietimei);
        chitietimei.setFocusable(false);
        scrollchitietimei.setViewportView(chitietimei);
        
        center_mid = new JPanel();
        center_mid.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        center_mid.add(scrollthongtinchitiet, BorderLayout.CENTER);
        center_mid.add(scrollchitietimei, BorderLayout.EAST);
        
        center_top = new JPanel();
        center_top.setLayout(new GridLayout(1,3));
        

        scrollthongtinchitiet.setPreferredSize(new Dimension(850, 322));
        scrollchitietimei.setPreferredSize(new Dimension(220, 322));
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < thongtinchitiet.getColumnCount(); i++) {
            thongtinchitiet.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        

        mahoadon = new InputText("MÃ HÓA ĐƠN");
        mahoadon.setEditable(false);
        nhanviennhap = new InputText("NHÂN VIÊN NHẬP");
        nhanviennhap.setEditable(false);
        khachhang = new InputText("KHÁCH HÀNG");
        khachhang.setEditable(false);
        
        center_top.add(mahoadon);
        center_top.add(nhanviennhap);
        center_top.add(khachhang);

        center.add(center_top,BorderLayout.NORTH);
        center.add(center_mid,BorderLayout.CENTER);

        
        top.add(title,BorderLayout.CENTER);
        this.add(top,BorderLayout.NORTH);
        this.add(center,BorderLayout.CENTER);
        
    }
    
     public void loaddata(){
        mahoadon.setText(String.valueOf(maHD));
        TaiKhoanDTO taiKhoan = TaiKhoanDTO.getTaiKhoanHienTai();
        nhanviennhap.setText(taiKhoan.getTenDangNhap());
        HoaDonDTO hddto =  new HoaDonBLL().getHoaDonById(maHD);   
         KhachHangDTO khdto = new KhachHangBLL().getKhachHangById(hddto.getMaKH());
        khachhang.setText(khdto.getHoTen());
        List<ChiTietHoaDonDTO> list = new ChiTietHoaDonBLL().getChiTietTheoMaHoaDon(maHD); 
        for(ChiTietHoaDonDTO cthd : list){
            int masp = cthd.getMaSanPham();
            SanPhamDTO pdto = new  SanPhamBLL().getSanPhamById(masp);
            String tensp = pdto.getTenSP();
            int ram = pdto.getRam();
            int rom = pdto.getRom();
            String mausac = pdto.getMauSac();
            int dongia = pdto.getGiaNhap();
            int soluong = cthd.getSoLuong();
            dftmthongtinchitiet.addRow(new Object[]{masp,tensp,ram,rom,mausac,dongia,soluong});
        }
    }
    public void loaddataimei(){
        thongtinchitiet.getSelectionModel().addListSelectionListener(event -> {
                int selectedRow = thongtinchitiet.getSelectedRow();
            if(selectedRow!=-1){
                dftmchitietimei.setRowCount(0);
                int masp=Integer.parseInt(thongtinchitiet.getValueAt(selectedRow, 0).toString());
                int mahd = Integer.parseInt(mahoadon.getText());
                List<String> imeilist = new ChiTietSanPhamBLL().getImeisByHoaDonAndSanPham(mahd, masp);
                int soluong = (int)dftmthongtinchitiet.getValueAt(selectedRow, 6);
                for (int i = 0;i < imeilist.size()&& i<soluong; i++) {
                int stt = i + 1;
                String imei = imeilist.get(i);
                dftmchitietimei.addRow(new Object[]{stt,imei});
                }
            }
        });
    }
    
    
}
