package GUI.pages;

import BLL.BUS.ChiTietPhieuBaoHanhBLL;
import BLL.BUS.KhachHangBLL;
import BLL.BUS.NhanVienBLL;
import BLL.BUS.PhieuBaoHanhBLL;
import DTO.ChiTietPhieuBaoHanhDTO;
import DTO.KhachHangDTO;
import DTO.NhanVienDTO;
import DTO.PhieuBaoHanhDTO;
import DTO.TaiKhoanDTO;
import GUI.DiaLog.PhieuBaoHanhDialog;
import GUI.Frame.Main;
import GUI.Panel.TaoPhieuBaoHanh;
import GUI.Panel.TopNav;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import util.ExportExcelUtility;

public class PhieuBaoHanhGUI extends JPanel {
    
    private TopNav topNav;
    private JPanel pnlBot;
    private JTable tbl;
    private DefaultTableModel tbmtb1;
    private JScrollPane scrtb1;
    private Main main;

    public PhieuBaoHanhGUI(Main main) {
        initComponent(main);
        loaddata();
        chucNang();
    }

    private void initComponent(Main main) {
        this.main = main;
        String[] itemFindFor = {"Tất Cả", "Mã Phiếu BH", "Khách Hàng", "IMEI"};
        
        topNav = new TopNav();
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
        
        String[] headtable = {"Mã Phiếu BH", "Khách Hàng", "Nhân Viên", "Ngày Lập", "Trạng Thái"};
        tbmtb1.setColumnIdentifiers(headtable);
        tbl.setModel(tbmtb1);

        // Center align content
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tbl.getColumnCount(); i++) {
            tbl.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Style table header
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
    }
    
    private void chucNang() {
        JButton[] btn = topNav.getBtn();
        JButton reFresh = topNav.getBtnRefresh();
        JTextField textSearch = topNav.getTextSearch();
        btn[1].setVisible(false);
        
        // Add new warranty button
        btn[0].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setPanel(new TaoPhieuBaoHanh(main));
            }
        });
        
        // View details button
        btn[3].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if(selectedRow == -1){
                    JOptionPane.showMessageDialog(null, "Chọn 1 dòng để xem chi tiết");
                } else {
                    String maBH = tbl.getValueAt(selectedRow, 0).toString();
                    PhieuBaoHanhDialog dialog = new PhieuBaoHanhDialog(main, maBH);
                    dialog.setVisible(true);
                }
            }
        });
        
        // Delete button
        btn[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if(selectedRow != -1){
                    int confirm = JOptionPane.showConfirmDialog(null, 
                            "Bạn chắc chắn muốn xóa phiếu bảo hành này?", 
                            "Xác nhận xóa", 
                            JOptionPane.YES_NO_OPTION);
                    if(confirm == JOptionPane.YES_OPTION){
                        String maBH = tbmtb1.getValueAt(selectedRow, 0).toString();
                        new ChiTietPhieuBaoHanhBLL().deleteChiTietBH(maBH);
                        new PhieuBaoHanhBLL().delete(maBH);
                        loaddata();
                        JOptionPane.showMessageDialog(null, "Xóa thành công");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Chọn 1 dòng để xóa");
                }
            }
        });
        
        // Export button
        btn[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ExportExcelUtility.saveTableToExcel(tbl, "Danh_sach_phieu_bao_hanh");
            }
        });
        
        // Refresh button
        reFresh.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                topNav.getFindFor().setSelectedIndex(0);
                topNav.getTextSearch().setText("");
                loaddata();
            }
        });
        
        // Search functionality
        textSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String type = topNav.getFindFor().getSelectedItem().toString();
                String keyword = textSearch.getText().trim().toLowerCase();
                
                List<PhieuBaoHanhDTO> filteredList = new ArrayList<>();
                List<PhieuBaoHanhDTO> allList = new PhieuBaoHanhBLL().getAll();
                
                for (PhieuBaoHanhDTO pbh : allList) {
                    KhachHangDTO kh = new KhachHangBLL().getKhachHangById(pbh.getMaKH());
                    TaiKhoanDTO tk = TaiKhoanDTO.getTaiKhoanHienTai();
                    
                    boolean match = false;
                    switch(type) {
                        case "Tất Cả":
                            match = String.valueOf(pbh.getMaPhieuBH()).contains(keyword) ||
                                   kh.getHoTen().toLowerCase().contains(keyword) ||
                                   tk.getTenDangNhap().toLowerCase().contains(keyword);
                            break;
                        case "Mã Phiếu BH":
                            match = String.valueOf(pbh.getMaPhieuBH()).contains(keyword);
                            break;
                        case "Khách Hàng":
                            match = kh.getHoTen().toLowerCase().contains(keyword);
                            break;
                        case "IMEI":
                            List<ChiTietPhieuBaoHanhDTO> ctList = 
                                new ChiTietPhieuBaoHanhBLL().getCTBaoHanhByMaPhieuBH(pbh.getMaPhieuBH());
                            for (ChiTietPhieuBaoHanhDTO ct : ctList) {
                                if (ct.getMaIMEI().toLowerCase().contains(keyword)) {
                                    match = true;
                                    break;
                                }
                            }
                            break;
                    }
                    
                    if (match) filteredList.add(pbh);
                }
                
                updateTable(filteredList);
            }
        });
    }
    
private void updateTable(List<PhieuBaoHanhDTO> data) {
        tbmtb1.setRowCount(0);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (PhieuBaoHanhDTO pbh : data) {
            KhachHangDTO kh = new KhachHangBLL().getKhachHangById(pbh.getMaKH());

            NhanVienDTO nv = new NhanVienBLL().getNhanVienById(pbh.getMaNhanVien());
            String tenNV = (nv != null) ? nv.getHoTen() : "Không xác định";

            // Use LocalDate's format method instead
            String formattedDate = pbh.getNgayLap().format(dateFormat);

            String trangThai = getTrangThaiString(pbh.getTrangThai());

            tbmtb1.addRow(new Object[]{
                pbh.getMaPhieuBH(),
                kh.getHoTen(),
                tenNV,
                formattedDate,
                trangThai
            });
        }
    }    
    private String getTrangThaiString(int status) {
        switch(status) {
            case 1: return "Đang hiệu lực";
            case 0: return "Đã hủy";
            default: return "Không xác định";
        }
    }
    
    private void loaddata() {
        List<PhieuBaoHanhDTO> list = new PhieuBaoHanhBLL().getAll();
        updateTable(list);
    }
}
