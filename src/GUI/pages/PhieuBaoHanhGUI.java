package GUI.pages;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


import BLL.BUS.*;
import DTO.*;
import GUI.DiaLog.PhieuBaoHanhDialog;
import GUI.DiaLog.PhieuSuaChuaDialog;
import GUI.DiaLog.ChiTietPhieuSuaChuaDialog;
import GUI.Frame.Main;
import GUI.Panel.Component.InputDate;
import GUI.Panel.TaoPhieuBaoHanh;
import GUI.Panel.TopNav;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.event.DocumentListener;

public class PhieuBaoHanhGUI extends JPanel {

    private TopNav topNav;
    private JPanel pnlBot;
    private JTable tblPhieuBH, tblPhieuSC;
    private DefaultTableModel modelPhieuBH, modelPhieuSC;
    private JScrollPane scrPhieuBH, scrPhieuSC;
    private InputDate dateStart, dateEnd;
    private JButton searchBtn;
    private JComboBox<String> cbLocTrangThai;
    private JTextField txtSearchSC;
    private JLabel lblTongPhieu, lblTongSuaChua;
    private Main main;

    public PhieuBaoHanhGUI(Main main) {
        initComponent(main);
        loaddata();
        chucNang();
    }

    private void initComponent(Main main) {
        this.main = main;

        // Top Nav setup
        String[] itemFindFor = {"Tất Cả", "Mã Phiếu BH", "Tên/SDT Khách Hàng", "IMEI"};
        dateStart = new InputDate("Từ ngày");
        dateEnd = new InputDate("Đến ngày");
               
        searchBtn = new JButton("Tìm", new FlatSVGIcon("./resources/icon/search.svg", 0.4f));
        searchBtn.setPreferredSize(new Dimension(90, 60));
        searchBtn.putClientProperty(FlatClientProperties.STYLE, "arc: 12");
        searchBtn.setOpaque(false);
        searchBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        searchBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        searchBtn.addActionListener(evt -> {
            try {
                searchBtnActionPerformed(evt);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        });

        JPanel datePanel = new JPanel(new GridLayout());
        datePanel.add(dateStart);
        datePanel.add(dateEnd);
        datePanel.add(searchBtn);
        topNav = new TopNav();
        topNav.setItemComboBox(itemFindFor);
        topNav.add(datePanel);

//        JButton btnLapPhieuSC = new JButton("🛠️ Lập phiếu sửa chữa");
//        btnLapPhieuSC.addActionListener(e -> openPhieuSuaChuaDialog());
//        topNav.add(btnLapPhieuSC);

        

        // Bottom panel
        pnlBot = new JPanel(new BorderLayout());
        pnlBot.setPreferredSize(new Dimension(0, 500));
        pnlBot.setBorder(new EmptyBorder(10, 15, 10, 15));

        // Table phiếu bảo hành
        modelPhieuBH = new DefaultTableModel(new String[]{"Mã Phiếu BH", "Khách Hàng", "SĐT", "Nhân Viên", "Ngày Lập"}, 0);
        tblPhieuBH = new JTable(modelPhieuBH);
        tblPhieuBH.setRowHeight(35);
        scrPhieuBH = new JScrollPane(tblPhieuBH);

        // Table phiếu sửa chữa
        modelPhieuSC = new DefaultTableModel(new String[]{"Mã SC", "IMEI", "Ngày Nhận", "Trạng Thái"}, 0);
        tblPhieuSC = new JTable(modelPhieuSC);
        tblPhieuSC.setRowHeight(35);
        scrPhieuSC = new JScrollPane(tblPhieuSC);
                // Row sorter và filter cho bảng sửa chữa
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelPhieuSC);
        tblPhieuSC.setRowSorter(sorter);

        // Bộ lọc theo trạng thái
        cbLocTrangThai = new JComboBox<>(new String[]{"Tất cả", "Đang xử lý", "Đã sửa xong", "Từ chối bảo hành"});
        cbLocTrangThai.addActionListener(e -> {
            String selected = cbLocTrangThai.getSelectedItem().toString();
            if (selected.equals("Tất cả")) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter(selected, 3));
            }
        });

        // Tìm kiếm trong bảng sửa chữa
        txtSearchSC = new JTextField();
        txtSearchSC.setPreferredSize(new Dimension(150, 25));
        txtSearchSC.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filter(); }

            private void filter() {
                String text = txtSearchSC.getText().trim();
                if (text.isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });
        JButton btnChiTietSC = new JButton("🔍 Chi tiết sửa chữa");
        btnChiTietSC.addActionListener(e -> openChiTietSuaChua());
        JButton btnCapNhatSC = new JButton("Cập nhật sửa chữa");
        btnCapNhatSC.addActionListener(e -> openPhieuSuaChuaDialog());
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(new JLabel("🔍 Lọc SC:"));
        filterPanel.add(txtSearchSC);
        filterPanel.add(new JLabel("  Trạng thái:"));
        filterPanel.add(cbLocTrangThai);
        filterPanel.add(btnChiTietSC); 
        filterPanel.add(btnCapNhatSC);
        JPanel rightPanel = new JPanel(new BorderLayout(5, 5));
        rightPanel.add(filterPanel, BorderLayout.NORTH);
        rightPanel.add(scrPhieuSC, BorderLayout.CENTER);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrPhieuBH, rightPanel);
        splitPane.setResizeWeight(0.5);
        splitPane.setDividerSize(4);
        pnlBot.add(splitPane, BorderLayout.CENTER);
        btnChiTietSC.setMnemonic(KeyEvent.VK_C);
        btnChiTietSC.setToolTipText("Chi tiết sửa chữa (Alt + C)");

        btnCapNhatSC.setMnemonic(KeyEvent.VK_U);
        btnCapNhatSC.setToolTipText("Cập nhật sửa chữa (Alt + U)");
        // Label thống kê
        lblTongPhieu = new JLabel();
        lblTongSuaChua = new JLabel();
        JPanel summaryPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        summaryPanel.add(lblTongPhieu);
        summaryPanel.add(Box.createHorizontalStrut(40));
        summaryPanel.add(lblTongSuaChua);
        pnlBot.add(summaryPanel, BorderLayout.SOUTH);

        // Layout chính
        this.setLayout(new BorderLayout());
        this.add(topNav, BorderLayout.NORTH);
        this.add(pnlBot, BorderLayout.CENTER);

        tblPhieuBH.getSelectionModel().addListSelectionListener(e -> {
            int row = tblPhieuBH.getSelectedRow();
            if (row != -1) {
                int ma = Integer.parseInt(modelPhieuBH.getValueAt(row, 0).toString());
                loadPhieuSuaChua(ma);
            }
        });
    }

    private void chucNang() {
        JButton[] btn = topNav.getBtn();
        JButton reFresh = topNav.getBtnRefresh();
        JTextField textSearch = topNav.getTextSearch();
        // Sau khi khởi tạo các nút topNav (btn[])

        // Gán mnemonic và tooltip
        btn[0].setMnemonic(KeyEvent.VK_N);
        btn[0].setToolTipText("Tạo phiếu bảo hành (Alt + N)");

        btn[2].setMnemonic(KeyEvent.VK_D);
        btn[2].setToolTipText("Xóa phiếu bảo hành (Alt + D)");

        btn[3].setMnemonic(KeyEvent.VK_V);
        btn[3].setToolTipText("Xem chi tiết phiếu (Alt + V)");

        reFresh.setMnemonic(KeyEvent.VK_R);
        reFresh.setToolTipText("Làm mới danh sách (Alt + R)");

        searchBtn.setMnemonic(KeyEvent.VK_S);
        searchBtn.setToolTipText("Tìm kiếm (Alt + S)");

        btn[1].setVisible(false);
        btn[4].setVisible(false);
        btn[5].setVisible(false);

        btn[0].addActionListener(e -> main.setPanel(new TaoPhieuBaoHanh(main)));

        btn[3].addActionListener(e -> {
            int row = tblPhieuBH.getSelectedRow();
            if (row != -1) {
                int ma = Integer.parseInt(modelPhieuBH.getValueAt(row, 0).toString());
                new PhieuBaoHanhDialog(main, ma).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để xem chi tiết.");
            }
        });

        btn[2].addActionListener(e -> {
            int row = tblPhieuBH.getSelectedRow();
            if (row != -1) {
                int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận xóa phiếu?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    int ma = Integer.parseInt(modelPhieuBH.getValueAt(row, 0).toString());
                    new ChiTietPhieuBaoHanhBLL().deleteChiTietBH(ma);
                    new PhieuBaoHanhBLL().delete(ma);
                    loaddata();
                }
            }
        });

        reFresh.addActionListener(e -> {
            topNav.getFindFor().setSelectedIndex(0);
            topNav.getTextSearch().setText("");
            loaddata();
            int row = tblPhieuSC.getSelectedRow();
            if(row != -1){
                int maSC = Integer.parseInt(modelPhieuSC.getValueAt(row, 0).toString()); 
                loadPhieuSuaChua(new PhieuSuaChuaBLL().getById(maSC).getMaPhieuBH());
            }
        });

        textSearch.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                filterSearch();
            }
        });
    }

    private void openPhieuSuaChuaDialog() {
        int row = tblPhieuSC.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn phiếu bảo hành để lập sửa chữa.");
            return;
        }  
        int maSC = Integer.parseInt(modelPhieuSC.getValueAt(row, 0).toString());
        String imei = modelPhieuSC.getValueAt(row, 1).toString();
        new PhieuSuaChuaDialog(main, new PhieuSuaChuaBLL().getById(maSC).getMaPhieuBH(), new PhieuSuaChuaBLL().getById(maSC).getMaSanPham(), imei,true);
    }

    private void openChiTietSuaChua() {
        int row = tblPhieuSC.getSelectedRow();
        if (row != -1) {
            int maSC = Integer.parseInt(modelPhieuSC.getValueAt(row, 0).toString());
            PhieuSuaChuaDTO dto = new PhieuSuaChuaBLL().getById(maSC);
            new ChiTietPhieuSuaChuaDialog(main, dto);
        } else {
            JOptionPane.showMessageDialog(this, "Chọn phiếu sửa chữa để xem chi tiết.");
        }
    }

    private void loadPhieuSuaChua(int maPhieuBH) {
        modelPhieuSC.setRowCount(0);
        List<PhieuSuaChuaDTO> list = new PhieuSuaChuaBLL().getByMaPhieuBH(maPhieuBH);
        for (PhieuSuaChuaDTO sc : list) {
            modelPhieuSC.addRow(new Object[]{
                    sc.getMaPhieuSC(), sc.getMaIMEI(), sc.getNgayNhan(), sc.getTrangThai()
            });
        }
        lblTongSuaChua.setText("🛠️ Tổng sửa chữa: " + list.size());
    }

    private void updatePhieuBHTable(List<PhieuBaoHanhDTO> list) {
        modelPhieuBH.setRowCount(0);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (PhieuBaoHanhDTO p : list) {
            KhachHangDTO kh = new KhachHangBLL().getKhachHangById(p.getMaKH());
            NhanVienDTO nv = new NhanVienBLL().getNhanVienById(p.getMaNhanVien());

            modelPhieuBH.addRow(new Object[]{
                    p.getMaPhieuBH(),
                    kh.getHoTen(),
                    kh.getSdt(),
                    (nv != null) ? nv.getHoTen() : "N/A",
                    p.getNgayLap().format(fmt)     
            });
        }
        lblTongPhieu.setText("📄 Tổng phiếu BH: " + list.size());
    }

    private void searchBtnActionPerformed(ActionEvent evt) throws ParseException {
        List<PhieuBaoHanhDTO> filteredList = new ArrayList<>();
        List<PhieuBaoHanhDTO> allList = new PhieuBaoHanhBLL().getAll();

        Date date_start = dateStart.getDate();
        Date date_end = dateEnd.getDate();

        if (date_start == null || date_end == null) {
            JOptionPane.showMessageDialog(this, "Chọn đầy đủ ngày.");
            return;
        }

        LocalDate localStart = date_start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localEnd = date_end.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        for (PhieuBaoHanhDTO p : allList) {
            if (!p.getNgayLap().isBefore(localStart) && !p.getNgayLap().isAfter(localEnd)) {
                filteredList.add(p);
            }
        }
        updatePhieuBHTable(filteredList);
    }

    private void filterSearch() {
        String type = topNav.getFindFor().getSelectedItem().toString();
        String keyword = topNav.getTextSearch().getText().toLowerCase().trim();

        List<PhieuBaoHanhDTO> result = new ArrayList<>();
        for (PhieuBaoHanhDTO p : new PhieuBaoHanhBLL().getAll()) {
            KhachHangDTO kh = new KhachHangBLL().getKhachHangById(p.getMaKH());
            boolean match = switch (type) {
                case "Mã Phiếu BH" -> p.getMaPhieuBH()==Integer.parseInt(keyword) && (!keyword.isEmpty());
                case "Tên/SDT Khách Hàng" -> kh.getHoTen().toLowerCase().contains(keyword) || kh.getSdt().contains(keyword);
                case "IMEI" -> new ChiTietPhieuBaoHanhBLL().getCTBaoHanhByMaPhieuBH(p.getMaPhieuBH()).stream()
                        .anyMatch(ct -> ct.getMaIMEI().toLowerCase().contains(keyword));
                default -> p.getMaPhieuBH()==Integer.parseInt(keyword)
                        || kh.getHoTen().toLowerCase().contains(keyword)
                        || kh.getSdt().contains(keyword);
            };
            if (match) result.add(p);
        }
        updatePhieuBHTable(result);
    }

    private void loaddata() {
        updatePhieuBHTable(new PhieuBaoHanhBLL().getAll());
    }
}
