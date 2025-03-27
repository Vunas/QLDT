/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Panel;

import BLL.BUS.NhaCungCapBLL;
import BLL.BUS.PhieuNhapBLL;
import DAO.PhieuNhapDao;
import DAO.SanPhamBLL;
import DTO.NhaCungCapDTO;
import DTO.SanPhamDTO;
import DTO.TaiKhoanDTO;
import GUI.Panel.InputType.ButtonCustom;
import GUI.Panel.InputType.InputText;
import GUI.Panel.InputType.SelectForm;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguyen
 */
public class TaoPhieuNhap extends JPanel{
     JPanel left,right,luachonsanpham,thongtinsanpham,thongtinsanpham_top,thongtinsanpham_bottom,luachonsanpham_left,right_top,right_bottom,tongsotien,thongtinsanpham_top_matensp,thongtinsanpham_top_chgt,thongtinsanpham_top_imeisl,thongtinsanpham_bottom_button;
     JTable sanphamTable,sanphamdathemTable;
     JLabel tongTien,sotien;
     DefaultTableModel tbmsanphamTable,tbmsanphamdathemTable;
     JScrollPane scrollsanphamTable,scrollsanphamdathemTable;
     InputText masptxt,tensptxt,gianhaptxt,maimeitxt,soluongtxt,maphieunhaptxt,nhanviennhaptxt;
     JTextField timkiemsptxt;
     SelectForm cbxcauhinh,cbxnhacungcap;
     ButtonCustom addsp,nhaphang,sua,xoa;
     SanPhamBLL sanphamBLL;
     PhieuNhapDao phieunhapDAO;

    public TaoPhieuNhap() {
        initComponent();
        loaddatasanpham();
        chonsanpham();
        setThongPhieuNhap();
    }
    
    public void initComponent(){
        this.setSize(600, 600);
        this.setLayout(new BorderLayout(2, 2));
       
       left = new JPanel();
       left.setLayout(new BorderLayout(10,10));
       
       
       
       right = new JPanel();
       right.setLayout(new BorderLayout());
       right.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
       right.setPreferredSize(new Dimension(250,500));
       right.setBackground(Color.white);
       

       right_top = new JPanel();
       right_top.setLayout(new FlowLayout());
       
       maphieunhaptxt = new InputText("Mã phiếu nhập");
       maphieunhaptxt.setPreferredSize(new Dimension(250,80));
       maphieunhaptxt.setBackground(Color.white);
       maphieunhaptxt.setEditable(false);
       nhanviennhaptxt = new InputText("Nhân viên nhập");
       nhanviennhaptxt.setPreferredSize(new Dimension(250,80));
       nhanviennhaptxt.setBackground(Color.white);
       nhanviennhaptxt.setEditable(false);
       String [] arrnhacungcap = new NhaCungCapBLL().getNameNhaCungCap();
       cbxnhacungcap = new SelectForm("Nhà cung cấp", arrnhacungcap);
       cbxnhacungcap.setPreferredSize(new Dimension(250,50));
       cbxnhacungcap.setBackground(Color.white);
      
       right_bottom = new JPanel();
       right_bottom.setLayout(new BorderLayout(5,5));
       
       nhaphang = new ButtonCustom("Nhập hàng","success",14);
       nhaphang.setPreferredSize(new Dimension(250,50));
       
       
       tongsotien = new JPanel();
       tongTien = new JLabel("Tổng tiền:");
       tongTien.setFont(new Font("Arial", 1, 18));
       tongTien.setForeground(new Color(255, 51, 51));
       sotien = new JLabel("0đ");
       sotien.setFont(new Font("Arial", 1, 18));
       tongsotien.add(tongTien);
       tongsotien.add(sotien);
       
       
       right_bottom.add(tongsotien,BorderLayout.CENTER);
       right_bottom.add(nhaphang,BorderLayout.SOUTH);
       
       right_top.add(maphieunhaptxt);
       right_top.add(nhanviennhaptxt);
       right_top.add(cbxnhacungcap);
       
       right.add(right_top);
       right.add(right_bottom,BorderLayout.SOUTH);
       
       luachonsanpham = new JPanel();
       luachonsanpham.setLayout(new GridLayout(1,2));
       luachonsanpham_left = new JPanel();
       luachonsanpham_left.setLayout(new BorderLayout());
       
       timkiemsptxt = new JTextField();
       timkiemsptxt.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e) {
                timkiemsanpham();
            }
            
       });
       timkiemsptxt.putClientProperty("JTextField.placeholderText", "Tên sản phẩm, mã sản phẩm...");
       
       addsp = new ButtonCustom("Thêm sản phẩm", "success", 14);
       
        
        sanphamTable = new JTable();
        tbmsanphamTable = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
        return false; // Không cho phép chỉnh sửa ô
            }
        };
        scrollsanphamTable = new JScrollPane();
        String[] headerSP = new String[]{"Mã SP", "Tên sản phẩm", "Số lượng tồn"};
        tbmsanphamTable.setColumnIdentifiers(headerSP);
        sanphamTable.setModel(tbmsanphamTable);
        scrollsanphamTable.setViewportView(sanphamTable);
        scrollsanphamTable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
        sanphamTable.setFocusable(false);
        
        
        sanphamdathemTable = new JTable();
        tbmsanphamdathemTable = new DefaultTableModel();
        scrollsanphamdathemTable = new JScrollPane();
        String[] headerSPdathem = new String[]{"Mã SP", "Tên sản phẩm", "Ram", "Rom", "Màu sắc","Đơn giá","Số lượng"};
        tbmsanphamdathemTable.setColumnIdentifiers(headerSPdathem);
        sanphamdathemTable.setModel(tbmsanphamdathemTable);
        scrollsanphamdathemTable.setViewportView(sanphamdathemTable);
        sanphamdathemTable.setFocusable(false);
        scrollsanphamdathemTable.setPreferredSize(new Dimension(350, 250));
        scrollsanphamdathemTable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));

        
        
        thongtinsanpham_top = new JPanel();
        thongtinsanpham_top.setLayout(new GridLayout(3, 1));
        thongtinsanpham_top.setBackground(Color.white);
        
        thongtinsanpham_top_matensp =  new JPanel();
        thongtinsanpham_top_matensp.setLayout(new FlowLayout(FlowLayout.CENTER));
        thongtinsanpham_top_matensp.setBackground(Color.white);
        
        thongtinsanpham_top_chgt =  new JPanel();
        thongtinsanpham_top_chgt.setLayout(new FlowLayout(FlowLayout.CENTER));
        thongtinsanpham_top_chgt.setBackground(Color.white);
        
        thongtinsanpham_top_imeisl =  new JPanel();
        thongtinsanpham_top_imeisl.setLayout(new FlowLayout(FlowLayout.CENTER));
        thongtinsanpham_top_imeisl.setBackground(Color.white);
        
        thongtinsanpham = new JPanel();
        thongtinsanpham.setLayout(new BorderLayout());
        String[] arrcauhinh = {"Chọn sản phẩm"};
        cbxcauhinh = new SelectForm("Cấu hình", arrcauhinh);
        cbxcauhinh.setBackground(Color.white);
        masptxt = new InputText("Mã sản phẩm");
        masptxt.setBackground(Color.white);
        masptxt.setEditable(false);
        tensptxt = new InputText("Tên sản phẩm");
        tensptxt.setBackground(Color.white);
        tensptxt.setPreferredSize(new Dimension(285,75));
        tensptxt.setEditable(false);
        gianhaptxt = new InputText("Giá nhập");
        gianhaptxt.setBackground(Color.white);
        gianhaptxt.setPreferredSize(new Dimension(250,75));
        maimeitxt = new InputText("Mã imei");
        maimeitxt.setBackground(Color.white);
        maimeitxt.setPreferredSize(new Dimension(300,75));
        soluongtxt = new InputText("Số lượng");
        soluongtxt.setBackground(Color.white);
        thongtinsanpham_top_matensp.add(masptxt);
        thongtinsanpham_top_matensp.add(tensptxt);
        thongtinsanpham_top_chgt.add(cbxcauhinh);
        thongtinsanpham_top_chgt.add(gianhaptxt);
        thongtinsanpham_top_imeisl.add(maimeitxt);
        thongtinsanpham_top_imeisl.add(soluongtxt);
        
        thongtinsanpham_top.add(thongtinsanpham_top_matensp);
        thongtinsanpham_top.add(thongtinsanpham_top_chgt);
        thongtinsanpham_top.add(thongtinsanpham_top_imeisl);
        
        thongtinsanpham_bottom  = new JPanel();
        thongtinsanpham_bottom.setLayout(new BorderLayout());
        thongtinsanpham_bottom.setBackground(Color.white);
        thongtinsanpham_bottom_button =  new JPanel();
        thongtinsanpham_bottom_button.setLayout(new GridLayout(1,2,5,5));
        
        sua = new ButtonCustom("Sửa", "warning", 14);
        
        xoa = new ButtonCustom("Xóa", "danger", 14);
        
        thongtinsanpham_bottom_button.add(sua);
        thongtinsanpham_bottom_button.add(xoa);
        thongtinsanpham_bottom.add(thongtinsanpham_bottom_button,BorderLayout.SOUTH);
        
        thongtinsanpham.add(thongtinsanpham_top,BorderLayout.NORTH);
        thongtinsanpham.add(thongtinsanpham_bottom,BorderLayout.CENTER);
        
        
        
        luachonsanpham_left.add(timkiemsptxt,BorderLayout.NORTH);
        luachonsanpham_left.add(scrollsanphamTable,BorderLayout.CENTER);
        luachonsanpham_left.add(addsp,BorderLayout.SOUTH);
        
        luachonsanpham.add(luachonsanpham_left);
        luachonsanpham.add(thongtinsanpham);
        left.add(luachonsanpham,BorderLayout.CENTER);
        left.add(scrollsanphamdathemTable,BorderLayout.SOUTH);
        
        
        this.add(left,BorderLayout.CENTER);
        this.add(right,BorderLayout.EAST);
        this.setVisible(true);
    }
    
    public void loaddatasanpham(){
        sanphamBLL = new SanPhamBLL();
        List<SanPhamDTO> list = sanphamBLL.getAllSanPham();
        tbmsanphamTable.setRowCount(0);
        for(SanPhamDTO sp : list){
            tbmsanphamTable.addRow(new Object[]{sp.getMaSP(),sp.getTenSP(),sp.getSoLuong()});
        }
    }
    
    public void chonsanpham(){
        sanphamTable.getSelectionModel().addListSelectionListener(event -> {
            int selectedRow = sanphamTable.getSelectedRow();
            Object maSP = sanphamTable.getValueAt(selectedRow, 0);
            Object tenSP = sanphamTable.getValueAt(selectedRow, 1);
            masptxt.setText(maSP.toString());
            tensptxt.setText(tenSP.toString());
        });
    }
    
    public void timkiemsanpham(){
       String keyword = timkiemsptxt.getText().trim().toLowerCase();
       tbmsanphamTable.setRowCount(0);
       
       List<SanPhamDTO> list = sanphamBLL.getAllSanPham();
       for(SanPhamDTO sp : list){
           String maSP = String.valueOf(sp.getMaSP());
           String tenSP = sp.getTenSP().toLowerCase();
           if(maSP.contains(keyword)||tenSP.contains(keyword)){
               tbmsanphamTable.addRow(new Object[]{sp.getMaSP(), sp.getTenSP(), sp.getSoLuong()});
           }
       }
    }
    
  
    
    public void setThongPhieuNhap(){
        TaiKhoanDTO taiKhoan = TaiKhoanDTO.getTaiKhoanHienTai();
        nhanviennhaptxt.setText(taiKhoan.getTenDangNhap());
        int maphieunhap = new PhieuNhapBLL().getMaPhieuNhap();
        maphieunhaptxt.setText(String.valueOf(maphieunhap));
    }
    

    
    
    
    
    
}
