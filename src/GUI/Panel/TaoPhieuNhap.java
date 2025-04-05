/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Panel;

import BLL.BUS.ChiTietPhieuNhapBLL;
import BLL.BUS.ChiTietSanPhamBLL;
import BLL.BUS.NhaCungCapBLL;
import BLL.BUS.PhieuNhapBLL;
import BLL.BUS.SanPhamBLL;
import DAO.PhieuNhapDao;
import DTO.ChiTietPhieuNhapDTO;
import DTO.ChiTietSanPhamDTO;
import DTO.NhaCungCapDTO;
import DTO.PhieuNhapDTO;
import DTO.SanPhamDTO;
import DTO.TaiKhoanDTO;
import GUI.Frame.Main;
import GUI.Panel.InputType.ButtonCustom;
import GUI.Panel.InputType.InputText;
import GUI.Panel.InputType.SelectForm;
import GUI.pages.PhieuNhapGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
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
     Main main;

    public TaoPhieuNhap(Main main) {
        initComponent(main);
        loaddatasanpham();
        chonsanphamdethem();
        chonsanphamdexoasua();
        setThongPhieuNhap();
    }
    
    public void initComponent(Main main){
        this.main = main;
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
       right_top.setBackground(Color.white);
       
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
       nhaphang.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                themphieunhap();
                themchitietphieunhap();
                themchitietsanpham();
                main.setPanel(new PhieuNhapGUI(main));
            }
           
       });
       
       
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
       addsp.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               if(checkInput(sanphamTable))
               {
                   themsanpham();
                   setTongTien();
                   clearOnhap();
               }
               
            }
       });
       
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
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
        for (int i = 0; i < sanphamTable.getColumnCount(); i++) {
        sanphamTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        
        sanphamdathemTable = new JTable();
        
        tbmsanphamdathemTable = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
        return false;
            }
        };
        scrollsanphamdathemTable = new JScrollPane();
        String[] headerSPdathem = new String[]{"Mã SP", "Tên sản phẩm", "Ram", "Rom", "Màu sắc","Đơn giá","Số lượng","imei"};
        tbmsanphamdathemTable.setColumnIdentifiers(headerSPdathem);
        sanphamdathemTable.setModel(tbmsanphamdathemTable);
        scrollsanphamdathemTable.setViewportView(sanphamdathemTable);
        sanphamdathemTable.setFocusable(false);
        scrollsanphamdathemTable.setPreferredSize(new Dimension(350, 250));
        scrollsanphamdathemTable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
        for (int i = 0; i < sanphamdathemTable.getColumnCount(); i++) {
        sanphamdathemTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        
        
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
        String [] arr = {""} ;
        cbxcauhinh = new SelectForm("Cấu hình",arr );
        cbxcauhinh.setBackground(Color.white);
        cbxcauhinh.setPreferredSize(new Dimension(cbxcauhinh.getPreferredSize().width+50,60));
        masptxt = new InputText("Mã SP");
        masptxt.setBackground(Color.white);
        masptxt.setEditable(false);
        masptxt.setPreferredSize(new Dimension(masptxt.getPreferredSize().width,80));
        tensptxt = new InputText("Tên sản phẩm");
        tensptxt.setBackground(Color.white);
        tensptxt.setPreferredSize(new Dimension(285,80));
        tensptxt.setEditable(false);
        gianhaptxt = new InputText("Giá nhập");
        gianhaptxt.setInputType("number");
        gianhaptxt.setBackground(Color.white);
        gianhaptxt.setPreferredSize(new Dimension(235,80));
        maimeitxt = new InputText("Mã imei");
        maimeitxt.setInputType("number");
        maimeitxt.setBackground(Color.white);
        maimeitxt.setPreferredSize(new Dimension(250,80));
        soluongtxt = new InputText("Số lượng");
        soluongtxt.setInputType("number");
        soluongtxt.setPreferredSize(new Dimension(soluongtxt.getPreferredSize().width+50,80));
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
        thongtinsanpham_bottom_button.setLayout(new GridLayout(1,2));
        
        sua = new ButtonCustom("Sửa", "warning", 14);
        sua.setEnabled(false);
        sua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkInput(sanphamdathemTable)){
                    int selectedRow = sanphamdathemTable.getSelectedRow();
                if(selectedRow != -1){
                    String masp = masptxt.getText();
                    sanphamdathemTable.setValueAt(masp, selectedRow, 0);
                    String tensp = tensptxt.getText();
                    sanphamdathemTable.setValueAt(tensp, selectedRow, 1);
                    String [] cauhinh = cbxcauhinh.getValue().split("-");
                    String ram = cauhinh[0];
                    sanphamdathemTable.setValueAt(ram, selectedRow, 2);
                    String rom = cauhinh[1];
                    sanphamdathemTable.setValueAt(rom, selectedRow, 3);
                    String mausac = cauhinh[2];
                    sanphamdathemTable.setValueAt(mausac, selectedRow, 4);
                    String dongia = gianhaptxt.getText();
                    sanphamdathemTable.setValueAt(dongia, selectedRow, 5);
                    String soluong = soluongtxt.getText();
                    sanphamdathemTable.setValueAt(soluong, selectedRow, 6);
                    setTongTien();
                    
                }
                JOptionPane.showMessageDialog(null, "Đã sửa thành công");
                }
            }
        });
        
        xoa = new ButtonCustom("Xóa", "danger", 14);
        xoa.setEnabled(false);
        xoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = sanphamdathemTable.getSelectedRow();
                if(selectedRow != -1){
                   int confirm = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa", "Xác nhận", JOptionPane.YES_NO_OPTION);
                  if(confirm == JOptionPane.YES_OPTION)
                  {
                      tbmsanphamdathemTable.removeRow(selectedRow);
                      setTongTien();
                      clearOnhap();
                  } 
                }
            }
        });
        
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
        sanphamdathemTable.removeColumn(sanphamdathemTable.getColumnModel().getColumn(7));
    }
    
    public void themsanpham(){
        String masp = masptxt.getText();
        String tensp = tensptxt.getText();
        String [] cauhinh = cbxcauhinh.getValue().split("-");
        String ram = cauhinh[0];
        String rom = cauhinh[1];
        String mausac = cauhinh[2];
        String dongia = gianhaptxt.getText();
        String soluong = soluongtxt.getText();
        String imei = maimeitxt.getText();
        tbmsanphamdathemTable.addRow(new Object[]{masp,tensp,ram,rom,mausac,dongia,soluong,imei});
    }
    
    public void chonsanphamdethem(){
            sanphamTable.getSelectionModel().addListSelectionListener(event -> {
    if (!event.getValueIsAdjusting()) {  // Chỉ thực thi khi người dùng thực sự chọn
        clearOnhap();
        addsp.setEnabled(true);
        sua.setEnabled(false);
        xoa.setEnabled(false);
        int selectedRow = sanphamTable.getSelectedRow();
        if (selectedRow == -1) return;

        int masp = Integer.parseInt(tbmsanphamTable.getValueAt(selectedRow, 0).toString());
        for(int row = 0 ; row<sanphamdathemTable.getRowCount() ; row ++){
            int maspdaco = Integer.parseInt(sanphamdathemTable.getValueAt(row, 0).toString());
            if(masp == maspdaco){
                 JOptionPane.showMessageDialog(null, "Sản phẩm mã " + maspdaco + " đã được thêm chỉ có thể sửa hoặc xóa");
                 sanphamTable.clearSelection();
                 sanphamdathemTable.clearSelection();
                 return;
            }
        }

        Object maSP = sanphamTable.getValueAt(selectedRow, 0);
        SanPhamDTO sanphamDaChon = new SanPhamBLL().getSanPhamById((int) maSP);
        tensptxt.setText(sanphamDaChon.getTenSP());
        masptxt.setText(String.valueOf(sanphamDaChon.getMaSP()));
        gianhaptxt.setText(String.valueOf(sanphamDaChon.getGiaNhap()));

        String[] cauhinh = {cauhinh(sanphamTable)};
        cbxcauhinh.setArr(cauhinh);
        sanphamdathemTable.clearSelection();
    }
});

    }
     public void clearOnhap(){
         tensptxt.setText("");
         masptxt.setText("");
         gianhaptxt.setText("");
         String [] cauhinh = {""};
         cbxcauhinh.setArr(cauhinh);
         maimeitxt.setText("");
         soluongtxt.setText("");
     }
    
    public void chonsanphamdexoasua(){
            sanphamdathemTable.getSelectionModel().addListSelectionListener(event -> {
                addsp.setEnabled(false);
                sua.setEnabled(true);
                xoa.setEnabled(true);
            int selectedRow = sanphamdathemTable.getSelectedRow();
            if(selectedRow != -1)
            {
            tensptxt.setText(tbmsanphamdathemTable.getValueAt(selectedRow, 1).toString());
            masptxt.setText(tbmsanphamdathemTable.getValueAt(selectedRow, 0).toString());
            gianhaptxt.setText(tbmsanphamdathemTable.getValueAt(selectedRow, 5).toString());
            soluongtxt.setText(tbmsanphamdathemTable.getValueAt(selectedRow, 6).toString());
            maimeitxt.setText(tbmsanphamdathemTable.getValueAt(selectedRow, 7).toString());
            String [] cauhinh = {cauhinh(sanphamdathemTable)};
            cbxcauhinh.setArr(cauhinh);
            
                        sanphamTable.clearSelection();
            }

            
        });
    }
    
    
    public void setTongTien(){
        int tong=0;
        for(int row = 0;  row < tbmsanphamdathemTable.getRowCount();row++){
            int tiensanpham= Integer.parseInt(tbmsanphamdathemTable.getValueAt(row, 5).toString())*Integer.parseInt(tbmsanphamdathemTable.getValueAt(row, 6).toString());
            tong = tong + tiensanpham;
        }
        NumberFormat kieuhienthi = NumberFormat.getInstance(Locale.US);
        sotien.setText(kieuhienthi.format(tong) + "đ");
    }
    public String cauhinh(JTable table){
            int selectedRow = table.getSelectedRow();
            if(selectedRow == -1)
            {
                return "" ;
            }
            int maSP = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
            SanPhamDTO sanphamDaChon = new SanPhamBLL().getSanPhamById( maSP);
            
            return sanphamDaChon.getRam()+"-"+sanphamDaChon.getRom()+"-"+sanphamDaChon.getMauSac(); 
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
    public void themphieunhap(){
        PhieuNhapDTO phieunhap = new PhieuNhapDTO();
        NhaCungCapDTO nccdto = new NhaCungCapDTO();
        nccdto = new NhaCungCapBLL().getNhaCungCapByName(cbxnhacungcap.getValue());
        phieunhap.setMaNhaCungCap(nccdto.getMaNhaCungCap());
        
        TaiKhoanDTO taiKhoan = TaiKhoanDTO.getTaiKhoanHienTai();
        phieunhap.setMaNhanVien(taiKhoan.getMaNV());
        phieunhap.setMaPhieuNhap(Integer.parseInt(maphieunhaptxt.getText()));
        LocalDate today = LocalDate.now();
        phieunhap.setNgayNhap(today);
        PhieuNhapBLL bll = new PhieuNhapBLL();
        bll.addPhieuNhap(phieunhap);
    }
    
    public void themchitietphieunhap(){
        for(int row = 0; row< sanphamdathemTable.getRowCount();row ++){
            ChiTietPhieuNhapDTO chitietphieunhap = new ChiTietPhieuNhapDTO();
            chitietphieunhap.setMaSanPham(Integer.parseInt(sanphamdathemTable.getValueAt(row, 0).toString()));
            chitietphieunhap.setSoLuong(Integer.parseInt(sanphamdathemTable.getValueAt(row, 6).toString()));
            SanPhamDTO sanphamdto = new SanPhamBLL().getSanPhamById(Integer.parseInt(sanphamdathemTable.getValueAt(row, 0).toString()));
            int soluongMoi=Integer.parseInt(sanphamdathemTable.getValueAt(row, 6).toString())+ sanphamdto.getSoLuong() ;
            new SanPhamBLL().updateSoluong(Integer.parseInt(sanphamdathemTable.getValueAt(row, 0).toString()), soluongMoi );
            chitietphieunhap.setDonGia(Integer.parseInt(sanphamdathemTable.getValueAt(row, 5).toString()));
            chitietphieunhap.setMaPhieuNhap(Integer.parseInt(maphieunhaptxt.getText()));
            ChiTietPhieuNhapBLL bll = new ChiTietPhieuNhapBLL();
            bll.addChiTietPhieuNhap(chitietphieunhap);
        } 
    }
    
    public void themchitietsanpham(){
        ChiTietSanPhamBLL ctspbll = new ChiTietSanPhamBLL();
        for(int row = 0; row< sanphamdathemTable.getRowCount();row ++){
            String maimeiStr = tbmsanphamdathemTable.getValueAt(row, 7).toString().trim();
            for(int i=0;i<Integer.parseInt(sanphamdathemTable.getValueAt(row, 6).toString());i++){
                ChiTietSanPhamDTO ctsp = new ChiTietSanPhamDTO();
                ctsp.setMaImei(String.valueOf(Long.parseLong(maimeiStr) + i));
                ctsp.setMaPhieuNhap(Integer.parseInt(maphieunhaptxt.getText()));
                ctsp.setMaSanpham(Integer.parseInt(sanphamdathemTable.getValueAt(row, 0).toString()));
                ctspbll.addChiTietSanPham(ctsp);
            }
        }
    }
  
    
    public void setThongPhieuNhap(){
        TaiKhoanDTO taiKhoan = TaiKhoanDTO.getTaiKhoanHienTai();
        nhanviennhaptxt.setText(taiKhoan.getTenDangNhap());
        int maphieunhap = new PhieuNhapBLL().getMaPhieuNhap();
        maphieunhaptxt.setText(String.valueOf(maphieunhap));
    }
    
    public boolean checkInput(JTable table){
        boolean check = true;
        
        if(table.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null, "Hãy chọn 1 sản phẩm");
            check = false;
        }
        
        if(gianhaptxt.getText().length()==0){
            gianhaptxt.setLblError("Thiếu thông tin");
            check = false;
        }
        else{
            gianhaptxt.setLblError("");
            if(gianhaptxt.getText().equals("0")){
                gianhaptxt.setLblError("Giá nhập khác 0");
                check = false;
            }
            else{
                gianhaptxt.setLblError("");
            }
        }
        
        if(maimeitxt.getText().length()==0){
            maimeitxt.setLblError("Thiếu thông tin");
            check = false;
        }
        else{
            maimeitxt.setLblError("");
            
            if(maimeitxt.getText().length() != 15){
                maimeitxt.setLblError("Mã Imei là 15 chữ số");
                check = false;
            }
            else {
                maimeitxt.setLblError("");
            }
        }
        
        if(soluongtxt.getText().length()==0){
            soluongtxt.setLblError("Thiếu thông tin");
            check = false;
        }
        else{
            soluongtxt.setLblError("");
                if(soluongtxt.getText().equals("0")){
                soluongtxt.setLblError("Số lượng khác 0");
                check = false;
            }
            else{
                soluongtxt.setLblError("");
            }
        }

        return check;
    }

}
