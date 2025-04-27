package GUI.DiaLog;

import BLL.BUS.*;
import DTO.*;
import GUI.Panel.InputType.InputText;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.List;
import GUI.DiaLog.ChiTietSanPhamDialog;

public class PhieuBaoHanhDialog extends JDialog {
    private JTable tblChiTiet, tblLichSu;
    private DefaultTableModel modelChiTiet, modelLichSu;
    private InputText maphieu, khachhang;
    private int maPBH;
    private JTextField txtSearchIMEI;
    private JButton btnLap;

    public PhieuBaoHanhDialog(JFrame main, int maPBH) {
        super(main, "Chi tiết phiếu bảo hành", true);
        this.maPBH = maPBH;
        initComponent();
        setLocationRelativeTo(main);
        loaddata();
    }

    private void initComponent() {
        setSize(1400, 600);
        setLayout(new BorderLayout());

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(0, 102, 204));
        header.setPreferredSize(new Dimension(1000, 60));
        JLabel title = new JLabel("CHI TIẾT PHIẾU BẢO HÀNH", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        // Search
        txtSearchIMEI = new JTextField();
        txtSearchIMEI.setPreferredSize(new Dimension(200, 30));
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBorder(BorderFactory.createTitledBorder("Tìm theo IMEI"));
        searchPanel.add(txtSearchIMEI, BorderLayout.CENTER);
        txtSearchIMEI.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filterTable(); }
            public void removeUpdate(DocumentEvent e) { filterTable(); }
            public void changedUpdate(DocumentEvent e) { filterTable(); }
        });

        // Thông tin phiếu
        JPanel infoPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        maphieu = new InputText("Mã phiếu");
        khachhang = new InputText("Khách hàng");
        maphieu.setEditable(false);
        khachhang.setEditable(false);
        JButton btnChiTietSP = new JButton("Xem chi tiết sản phẩm");
        btnChiTietSP.addActionListener(e -> moChiTietSanPham());
        infoPanel.add(maphieu);
        infoPanel.add(khachhang);
        infoPanel.add(searchPanel);
        infoPanel.add(btnChiTietSP);

        // Bảng chi tiết BH
        modelChiTiet = new DefaultTableModel(new String[]{"Mã SP", "IMEI", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái", "Ghi chú"}, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        tblChiTiet = new JTable(modelChiTiet);
        tblChiTiet.setRowHeight(30);
        tblChiTiet.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        styleTable(tblChiTiet);
        JScrollPane scrollChiTiet = new JScrollPane(tblChiTiet);

        // Bảng lịch sử sửa chữa
        modelLichSu = new DefaultTableModel(new String[]{"Mã SC", "IMEI", "Ngày nhận", "Tình trạng", "Xử lý", "Trạng thái", "Ghi chú"}, 0);
        tblLichSu = new JTable(modelLichSu);
        tblLichSu.setRowHeight(30);
        styleTable(tblLichSu);
        JScrollPane scrollLichSu = new JScrollPane(tblLichSu);

        // Nút lập phiếu sửa chữa
        btnLap = new JButton("🛠️ Lập phiếu sửa chữa");
        btnLap.setEnabled(false);
        btnLap.addActionListener(e -> openPhieuSuaChuaDialog());
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actionPanel.add(btnLap);

        // Left: Chi tiết BH
        JPanel leftPanel = new JPanel(new BorderLayout(5, 5));
        leftPanel.add(infoPanel, BorderLayout.NORTH);
        leftPanel.add(scrollChiTiet, BorderLayout.CENTER);
        leftPanel.add(actionPanel, BorderLayout.SOUTH);

        // Right: Lịch sử
        JPanel rightPanel = new JPanel(new BorderLayout(5, 5));
        JLabel lblRight = new JLabel(" Lịch sử sửa chữa", JLabel.LEFT);
        lblRight.setFont(new Font("Segoe UI", Font.BOLD, 14));
        rightPanel.add(lblRight, BorderLayout.NORTH);
        rightPanel.add(scrollLichSu, BorderLayout.CENTER);

        // Split center 50/50
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(600);
        splitPane.setResizeWeight(0.5);
        add(splitPane, BorderLayout.CENTER);

        // Sự kiện chọn dòng để load sửa chữa và trạng thái
        tblChiTiet.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                checkTrangThaiBHAndToggleButton();
                loadLichSuSuaChua();
            }
        });
    }

    private void styleTable(JTable table) {
        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(0, 35));
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(new Color(100, 149, 237));
        header.setForeground(Color.WHITE);
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(center);
        }
    }

    private void loaddata() {
        PhieuBaoHanhDTO pbh = new PhieuBaoHanhBLL().getBaoHanhById(maPBH);
        if (pbh == null) return;

        KhachHangDTO kh = new KhachHangBLL().getKhachHangById(pbh.getMaKH());
        if (kh == null) return;

        maphieu.setText(pbh.getMaPhieuBH()+"");
        khachhang.setText(kh.getHoTen());

        List<ChiTietPhieuBaoHanhDTO> list = new ChiTietPhieuBaoHanhBLL().getCTBaoHanhByMaPhieuBH(maPBH);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        modelChiTiet.setRowCount(0);

        for (ChiTietPhieuBaoHanhDTO ct : list) {
            String bd = ((Date) ct.getNgayBatDauBH()).toLocalDate().format(fmt);
            String kt = ((Date) ct.getNgayKetThucBH()).toLocalDate().format(fmt);
            String trangThai = switch (ct.getTrangThai()) {
                case 0 -> "Hết hạn";
                case 1 -> "Còn hiệu lực";
                default -> "Đã hủy";
            };

            modelChiTiet.addRow(new Object[]{
                    ct.getMaSanPham(), ct.getMaIMEI(), bd, kt, trangThai, ct.getGhiChu()
            });
        }
    }

    private void filterTable() {
        String search = txtSearchIMEI.getText().trim().toLowerCase();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelChiTiet);
        tblChiTiet.setRowSorter(sorter);
        RowFilter<DefaultTableModel, Object> imeiFilter = RowFilter.regexFilter("(?i)" + search, 1);
        sorter.setRowFilter(RowFilter.andFilter(List.of(imeiFilter)));
    }

    private void checkTrangThaiBHAndToggleButton() {
        int row = tblChiTiet.getSelectedRow();
        if (row == -1) {
            btnLap.setEnabled(false);
            return;
        }

        row = tblChiTiet.convertRowIndexToModel(row);
        String imei = modelChiTiet.getValueAt(row, 1).toString();
        List<ChiTietPhieuBaoHanhDTO> list = new ChiTietPhieuBaoHanhBLL()
                .getCTBaoHanhByMaPhieuBHVaIMEI(maPBH, imei);
        btnLap.setEnabled(!list.isEmpty() && list.get(0).getTrangThai() > 0);
    }

    private void loadLichSuSuaChua() {
        int row = tblChiTiet.getSelectedRow();
        if (row == -1) return;

        row = tblChiTiet.convertRowIndexToModel(row);
        String imei = modelChiTiet.getValueAt(row, 1).toString();
        List<PhieuSuaChuaDTO> list = new PhieuSuaChuaBLL().getByMaPhieuBHAndIMEI(maPBH, imei);
        modelLichSu.setRowCount(0);
        for (PhieuSuaChuaDTO sc : list) {
            modelLichSu.addRow(new Object[]{
                    sc.getMaPhieuSC(), sc.getMaIMEI(), sc.getNgayNhan(),
                    sc.getTinhTrang(), sc.getXuLy(), sc.getTrangThai(), sc.getGhiChu()
            });
        }
    }

    private void openPhieuSuaChuaDialog() {
        int row = tblChiTiet.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để lập phiếu sửa chữa.");
            return;
        }

        row = tblChiTiet.convertRowIndexToModel(row);
        int maSP = Integer.parseInt(modelChiTiet.getValueAt(row, 0).toString());
        String imei = modelChiTiet.getValueAt(row, 1).toString();

        new PhieuSuaChuaDialog((JFrame) getOwner(), maPBH, maSP, imei);
        loadLichSuSuaChua();
    }

    private void moChiTietSanPham() {
        int row = tblChiTiet.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để xem.");
            return;
        }
        row = tblChiTiet.convertRowIndexToModel(row);
        int maSP = Integer.parseInt(modelChiTiet.getValueAt(row, 0).toString());
        SanPhamDTO sp = new SanPhamBLL().getSanPhamByIdNoStatus(maSP);
        if (sp == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm.");
            return;
        }
        ChiTietSanPhamDialog dialog = new ChiTietSanPhamDialog((JFrame) getOwner(), sp);
        dialog.setVisible(true);
    }
}
