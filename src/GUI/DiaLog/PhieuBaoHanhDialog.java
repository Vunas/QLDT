// GUI hiển thị chi tiết phiếu bảo hành
package GUI.DiaLog;

import BLL.BUS.ChiTietPhieuBaoHanhBLL;
import BLL.BUS.KhachHangBLL;
import BLL.BUS.PhieuBaoHanhBLL;
import DTO.ChiTietPhieuBaoHanhDTO;
import DTO.KhachHangDTO;
import DTO.PhieuBaoHanhDTO;
import DTO.TaiKhoanDTO;
import GUI.Panel.InputType.InputText;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.TableRowSorter;

public class PhieuBaoHanhDialog extends JDialog {
    JPanel top, center, center_top, center_mid;
    JTable chitietbaohanh;
    JScrollPane scrollchitietbaohanh;
    DefaultTableModel dftmchitietbaohanh;
    JLabel title;
    InputText maphieu, khachhang;
    JTextField txtSearchIMEI;
    String maPBH;
    

    public PhieuBaoHanhDialog(JFrame main, String maPBH) {
        super(main, "Chi tiết phiếu bảo hành", true);
        this.maPBH = maPBH;
        initComponent();
        this.setLocationRelativeTo(main);
        loaddata();
    }

    public void initComponent() {
        this.setSize(new Dimension(900, 500));
        this.setLayout(new BorderLayout());

        top = new JPanel(new BorderLayout());
        top.setBackground(new Color(22, 122, 198));
        top.setPreferredSize(new Dimension(400, 60));

        title = new JLabel("CHI TIẾT PHIẾU BẢO HÀNH", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        top.add(title);

        center = new JPanel(new BorderLayout());

        dftmchitietbaohanh = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        chitietbaohanh = new JTable(dftmchitietbaohanh);
        scrollchitietbaohanh = new JScrollPane(chitietbaohanh);
        String[] headers = new String[]{"Mã SP", "IMEI", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái", "Ghi chú"};
        dftmchitietbaohanh.setColumnIdentifiers(headers);
        center.add(scrollchitietbaohanh, BorderLayout.CENTER);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < headers.length; i++) {
            chitietbaohanh.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        center_top = new JPanel(new BorderLayout(10, 10));
        JPanel leftInfo = new JPanel(new GridLayout(1, 2));
        maphieu = new InputText("MÃ PHIẾU");
        maphieu.setEditable(false);
        khachhang = new InputText("KHÁCH HÀNG");
        khachhang.setEditable(false);
        leftInfo.add(maphieu);
        leftInfo.add(khachhang);

        // Search bar IMEI
        txtSearchIMEI = new JTextField();
        txtSearchIMEI.setPreferredSize(new Dimension(200, 30));
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBorder(BorderFactory.createTitledBorder("Tìm theo IMEI"));
        searchPanel.add(txtSearchIMEI, BorderLayout.CENTER);

        // Add vào panel
        center_top.add(leftInfo, BorderLayout.CENTER);
        center_top.add(searchPanel, BorderLayout.EAST);


        center.add(center_top, BorderLayout.NORTH);

        this.add(top, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
    }

    public void loaddata() {
        PhieuBaoHanhDTO pbh = new PhieuBaoHanhBLL().getBaoHanhById(maPBH);
        KhachHangDTO kh = new KhachHangBLL().getKhachHangById(pbh.getMaKH());

        maphieu.setText(pbh.getMaPhieuBH());
        khachhang.setText(kh.getHoTen());

        List<ChiTietPhieuBaoHanhDTO> list = new ChiTietPhieuBaoHanhBLL().getCTBaoHanhByMaPhieuBH(maPBH);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (ChiTietPhieuBaoHanhDTO ct : list) {
            java.sql.Date sqlNgayBatDau = (java.sql.Date) ct.getNgayBatDauBH();
            java.sql.Date sqlNgayKetThuc = (java.sql.Date) ct.getNgayKetThucBH();

            String ngayBatDauStr = sqlNgayBatDau.toLocalDate().format(fmt);
            String ngayKetThucStr = sqlNgayKetThuc.toLocalDate().format(fmt);

            dftmchitietbaohanh.addRow(new Object[]{
                ct.getMaSanPham(),
                ct.getMaIMEI(),
                ngayBatDauStr,
                ngayKetThucStr,
                ct.getTrangThai() == 1 ? "Còn hiệu lực" : "Hết hạn",
                ct.getGhiChu()
            });
        }
        txtSearchIMEI.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
        @Override
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            filterTable();
        }
        @Override
        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            filterTable();
        }
        @Override
        public void changedUpdate(javax.swing.event.DocumentEvent e) {
            filterTable();
        }
        public void filterTable() {
            String search = txtSearchIMEI.getText().trim().toLowerCase();
            DefaultTableModel model = (DefaultTableModel) chitietbaohanh.getModel();

            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            chitietbaohanh.setRowSorter(sorter);

            if (search.isEmpty()) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + search, 1)); // cột 1 là IMEI
            }
        }

        });

    }

}