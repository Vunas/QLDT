/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.DiaLog;

import BLL.BUS.ChiTietPhieuNhapBLL;
import BLL.BUS.ChiTietSanPhamBLL;
import BLL.BUS.NhaCungCapBLL;
import BLL.BUS.NhanVienBLL;
import BLL.BUS.PhieuNhapBLL;
import DAO.SanPhamBLL;
import DTO.ChiTietPhieuNhapDTO;
import DTO.NhaCungCapDTO;
import DTO.PhieuNhapDTO;
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
public class PhieuNhapDiaLog extends JDialog{
    JPanel top,center,center_top,center_mid;
    JTable thongtinchitiet,chitietimei;
    JScrollPane scrollthongtinchitiet,scrollchitietimei;
    DefaultTableModel dftmthongtinchitiet,dftmchitietimei;
    JLabel title;
    InputText maphieunhap,nhanviennhap,nhacungcap;
    int maPN;
    
    public PhieuNhapDiaLog(JFrame main, int maPN ) {
        super(main);
        this.maPN = maPN;
        initComponent();
        this.setLocationRelativeTo(main);
        this.setVisible(true);
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

        

        maphieunhap = new InputText("MÃ PHIẾU NHẬP");
        maphieunhap.setEditable(false);
        nhanviennhap = new InputText("NHÂN VIÊN NHẬP");
        nhanviennhap.setEditable(false);
        nhacungcap = new InputText("NHÀ CUNG CẤP");
        nhacungcap.setEditable(false);
        
        center_top.add(maphieunhap);
        center_top.add(nhanviennhap);
        center_top.add(nhacungcap);

        center.add(center_top,BorderLayout.NORTH);
        center.add(center_mid,BorderLayout.CENTER);

        
        top.add(title,BorderLayout.CENTER);
        this.add(top,BorderLayout.NORTH);
        this.add(center,BorderLayout.CENTER);
        
    }
    
    public void loaddata(){
        maphieunhap.setText(String.valueOf(maPN));
        TaiKhoanDTO taiKhoan = TaiKhoanDTO.getTaiKhoanHienTai();
        nhanviennhap.setText(taiKhoan.getTenDangNhap());
        PhieuNhapDTO pndto =  new PhieuNhapBLL().getPhieuNhapById(maPN);   
        NhaCungCapDTO nccdto = new NhaCungCapBLL().getNhaCungCapById(pndto.getMaNhaCungCap());
        nhacungcap.setText(nccdto.getTen());
        List<ChiTietPhieuNhapDTO> list = new ChiTietPhieuNhapBLL().getChiTietPhieuNhapByPhieuNhap(maPN); 
        for(ChiTietPhieuNhapDTO ctpn : list){
            int masp = ctpn.getMaSanPham();
            SanPhamDTO pdto = new  SanPhamBLL().getSanPhamById(masp);
            String tensp = pdto.getTenSP();
            int ram = pdto.getRam();
            int rom = pdto.getRom();
            String mausac = pdto.getMauSac();
            int dongia = pdto.getGiaNhap();
            int soluong = ctpn.getSoLuong();
            dftmthongtinchitiet.addRow(new Object[]{masp,tensp,ram,rom,mausac,dongia,soluong});
        }
    }
    public void loaddataimei(){
        thongtinchitiet.getSelectionModel().addListSelectionListener(event -> {
            int selectedRow = thongtinchitiet.getSelectedRow();
            if(selectedRow!=-1){
                dftmchitietimei.setRowCount(0);
                int masp=Integer.parseInt(thongtinchitiet.getValueAt(selectedRow, 0).toString());
                int mapn = Integer.parseInt(maphieunhap.getText());
                List<String> imeilist = new ChiTietSanPhamBLL().getImeisByPhieuNhapAndSanPham(mapn, masp);
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
