package GUI.pages;

import BLL.BUS.ChiTietPhieuNhapBLL;
import BLL.BUS.NhaCungCapBLL;
import BLL.BUS.NhanVienBLL;
import BLL.BUS.PhieuNhapBLL;
import DTO.ChiTietPhieuNhapDTO;
import DTO.NhaCungCapDTO;
import DTO.NhanVienDTO;
import DTO.PhieuNhapDTO;
import DTO.TaiKhoanDTO;
import GUI.DiaLog.PhieuNhapDiaLog;
import GUI.Frame.Main;
import GUI.Panel.TaoPhieuNhap;
import GUI.Panel.TopNav;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import util.ExportExcelUtility;

public class PhieuNhapGUI extends JPanel {
    private TopNav topNav;
    private JPanel pnlBot;
    private JTable tbl,tb2;
    private DefaultTableModel tbmtb1,tbmtb2;
    private JScrollPane scrtb1;
    private Main main;


    public PhieuNhapGUI(Main main,TopNav topNav) {
        initComponent(main,topNav);
        loaddata();
        loaddatactpn();
        chucNang();
    }

    private void initComponent(Main main,TopNav topNav) {
        this.main = main;
        this.topNav = topNav;
        String[] itemFindFor = {"Tất Cả", "Mã Phiếu Nhập", "Nhà Cung Cấp", "Nhân Viên Nhập"};
        
        topNav.setItemComboBox(itemFindFor);
        
        
        
        pnlBot = new JPanel(new BorderLayout());
        pnlBot.setPreferredSize(new Dimension(0, 500));
        pnlBot.setBorder(new EmptyBorder(10, 15, 10, 15));
        
        tbl = new JTable();
        tbl.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbl.setRowHeight(35);
        tbl.setFocusable(false);

        tbmtb1 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        String[] headtable = {"Mã Phiếu Nhập", "Nhà Cung Cấp", "Nhân Viên Nhập", "Thời Gian", "Tổng Tiền"};
        tbmtb1.setColumnIdentifiers(headtable);
        tbl.setModel(tbmtb1);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tbl.getColumnCount(); i++) {
            tbl.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JTableHeader header = tbl.getTableHeader();
        header.setPreferredSize(new Dimension(0, 40));
        header.setBackground(new Color(100, 149, 237));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));

        scrtb1 = new JScrollPane(tbl);
        scrtb1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrtb1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        pnlBot.add(scrtb1, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(topNav, BorderLayout.NORTH);
        add(pnlBot, BorderLayout.CENTER);
        
        tb2 = new JTable();
         String[] headtable2 = {"maCTPhieuNhap", "soLuong", "donGia", "maPhieuNhap", "maSanPham"};
         tbmtb2 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
         tbmtb2.setColumnIdentifiers(headtable2);
         tb2.setModel(tbmtb2);
         
    }
    
    public void loaddatactpn(){
        List<ChiTietPhieuNhapDTO> list = new ChiTietPhieuNhapBLL().getAllChiTietPhieuNhap();
        for(ChiTietPhieuNhapDTO ctpn : list){
            tbmtb2.addRow(new Object[]{ctpn.getMaCTPhieuNhap(),ctpn.getSoLuong(),ctpn.getDonGia(),ctpn.getMaPhieuNhap(),ctpn.getMaSanPham()});
        }
    }

   private void chucNang(){
    JButton[] btn = topNav.getBtn();
    JButton reFresh = topNav.getBtnRefresh();
    JTextField textSearch = topNav.getTextSearch();
    btn[1].setVisible(false);
    btn[4].setVisible(false);

    btn[0].addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            main.setPanel(new TaoPhieuNhap(main,topNav));
        }
    });
     btn[3].addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = tbl.getSelectedRow();
            if(selectedRow==-1){
                 JOptionPane.showMessageDialog(null, "Chọn 1 dòng để xem chi tiết");
            }
            else{
               int maPN = Integer.parseInt(tbl.getValueAt(selectedRow, 0).toString());

            // Nếu dialog chưa tạo hoặc đã đóng thì tạo mới
               
                  PhieuNhapDiaLog pndialog = new PhieuNhapDiaLog(main, maPN);
                    pndialog.setVisible(true);
            }
            
        }
    });
     btn[2].addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = tbl.getSelectedRow();
            if(selectedRow != -1){
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa chứ","Xác ", JOptionPane.YES_NO_OPTION);
                if(confirm == JOptionPane.YES_OPTION){
                    int maPN = Integer.parseInt(tbmtb1.getValueAt(selectedRow, 0).toString());
                    new ChiTietPhieuNhapBLL().deleteChiTietPhieuNhap(maPN);
                    new PhieuNhapBLL().deletePhieuNhap(maPN);
                    loaddata();
                    JOptionPane.showMessageDialog(null, "Xóa thành công");
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Chọn 1 dòng để xóa");
            }
    }
     });
     
     btn[5].addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ExportExcelUtility.save2TableToExcel(tbl, "Phiếu Nhập", tb2, "Chi Tiết Phiếu Nhập");
            
       }
     });
     
     reFresh.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            topNav.getFindFor().setSelectedIndex(0);
            topNav.getTextSearch().setText("");
            loaddata();
        }
     });
     
     textSearch.addKeyListener(new KeyAdapter() {
    @Override
    public void keyReleased(KeyEvent e) {
        String type = topNav.getFindFor().getSelectedItem().toString().toLowerCase();
        String keyword = textSearch.getText().trim();
        
        List<PhieuNhapDTO> list = new PhieuNhapBLL().getAllPhieuNhap();

        // Tạo danh sách kết quả để lưu khách hàng phù hợp
        List<PhieuNhapDTO> filteredList = new ArrayList<>();
        NhaCungCapDTO tenNCC;
        
        // Lọc dữ liệu theo tiêu chí tìm kiếm
        for (PhieuNhapDTO pn : list) {
            tenNCC = new NhaCungCapBLL().getNhaCungCapById(pn.getMaNhaCungCap());
            NhanVienDTO nhanvien = new NhanVienBLL().getNhanVienById(pn.getMaNhanVien());
            
            boolean isMatch = false;
            switch (type.toLowerCase()) {
                case "tất cả":
                    // Lọc tất cả các tiêu chí
                    if (String.valueOf(pn.getMaPhieuNhap()).toLowerCase().contains(keyword.toLowerCase()) ||
                        tenNCC.getTen().toLowerCase().contains(keyword.toLowerCase()) ||
                        nhanvien.getHoTen().toLowerCase().contains(keyword.toLowerCase())) {
                        isMatch = true;
                    }
                    break;
                case "mã phiếu nhập":
                    if (String.valueOf(pn.getMaPhieuNhap()).toLowerCase().contains(keyword.toLowerCase())) {
                        isMatch = true;
                    }
                    break;
                case "nhà cung cấp":
                    if (tenNCC.getTen().toLowerCase().contains(keyword.toLowerCase())) {
                        isMatch = true;
                    }
                    break;
                case "nhân viên nhập":
                    if (nhanvien.getHoTen().toLowerCase().contains(keyword.toLowerCase())) {
                        isMatch = true;
                    }
                    break;
            }

            // Nếu có kết quả phù hợp, thêm vào danh sách lọc
            if (isMatch) {
                filteredList.add(pn);
            }
        }

        // Cập nhật lại bảng với dữ liệu đã lọc
        tbmtb1.setRowCount(0); // Xóa dữ liệu cũ trên bảng
        for (PhieuNhapDTO pn : filteredList) {
            NhaCungCapDTO ncc = new NhaCungCapBLL().getNhaCungCapById(pn.getMaNhaCungCap());
            NhanVienDTO nhanvien = new NhanVienBLL().getNhanVienById(pn.getMaNhanVien());
            
            int tongtien = 0;
            List<ChiTietPhieuNhapDTO> ctpn = new ChiTietPhieuNhapBLL().getChiTietPhieuNhapByPhieuNhap(pn.getMaPhieuNhap());
            for (ChiTietPhieuNhapDTO ct : ctpn) {
                tongtien += ct.getDonGia() * ct.getSoLuong();
            }
            tbmtb1.addRow(new Object[]{pn.getMaPhieuNhap(), ncc.getTen(), nhanvien.getHoTen(), pn.getNgayNhap(), tongtien});
        }
    }
});

     
}

    private void loaddata() {
        List<PhieuNhapDTO> list = new PhieuNhapBLL().getAllPhieuNhap();
        tbmtb1.setRowCount(0);
        for (PhieuNhapDTO pn : list) {
            NhaCungCapDTO ncc = new NhaCungCapBLL().getNhaCungCapById(pn.getMaNhaCungCap());
            NhanVienDTO nhanvien = new NhanVienBLL().getNhanVienById(pn.getMaNhanVien());
            
            
            int tongtien = 0;
            List<ChiTietPhieuNhapDTO> ctpn = new ChiTietPhieuNhapBLL().getChiTietPhieuNhapByPhieuNhap(pn.getMaPhieuNhap());
            for (ChiTietPhieuNhapDTO ct : ctpn) {
                tongtien += ct.getDonGia() * ct.getSoLuong();
            }
            tbmtb1.addRow(new Object[]{pn.getMaPhieuNhap(), ncc.getTen(), nhanvien.getHoTen(), pn.getNgayNhap(), tongtien});
        }
    }
    
    
}
