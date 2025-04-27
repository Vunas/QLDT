package GUI.Panel;

import BLL.BUS.*;
import DTO.*;
import GUI.Frame.Main;
import GUI.Panel.InputType.*;
import GUI.pages.HoaDonGUI;
import GUI.pages.PhieuBaoHanhGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class TaoPhieuBaoHanh extends JPanel {
    private Main main;
    private InputText maphieu, nhanvien;
    private JComboBox<Integer> cbxHoaDon;
    private JTable table;
    private DefaultTableModel model;
    private ButtonCustom btnThemSanPham, btnTaoPhieu;

    public TaoPhieuBaoHanh(Main main) {
        this.main = main;
        initComponent();
    }

    public void initComponent() {
        this.setLayout(new BorderLayout(10, 10));

        // Top Panel chia thành 2 hàng rõ ràng
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));

        maphieu = new InputText("Mã phiếu BH");
        maphieu.setEditable(false);
        maphieu.setText(new PhieuBaoHanhBLL().generateNewId()+"");

        nhanvien = new InputText("Nhân viên lập");
        nhanvien.setEditable(false);
        nhanvien.setText(new NhanVienBLL().getNhanVienById(TaiKhoanDTO.getTaiKhoanHienTai().getMaNV()).getHoTen());

        JPanel hoaDonPanel = new JPanel(new BorderLayout(5, 0));
        hoaDonPanel.add(new JLabel("Hóa đơn liên quan"), BorderLayout.WEST);
        cbxHoaDon = new JComboBox<>();
        for (HoaDonDTO hd : new HoaDonBLL().getAllHoaDon()) {
            List<ChiTietHoaDonDTO> ctList = new ChiTietHoaDonBLL().getChiTietTheoMaHoaDon(hd.getMaHoaDon());
            boolean ChuaCoBaoHanh = ctList.stream().anyMatch(ct ->
                    ct.getMaBaoHanh() == 0
            );
            if (ChuaCoBaoHanh) {
                cbxHoaDon.addItem(hd.getMaHoaDon());
            }
        }
        hoaDonPanel.add(cbxHoaDon, BorderLayout.CENTER);

        row1.add(maphieu);
        row1.add(nhanvien);
        row2.add(hoaDonPanel);

        topPanel.add(row1);
        topPanel.add(row2);

        // Table setup
        String[] header = {"Tên SP", "IMEI", "RAM", "ROM", "Chip", "TGBH (tháng)", "Ngày bắt đầu", "Ngày kết thúc"};
        model = new DefaultTableModel(header, 0);
        table = new JTable(model);
        table.setRowHeight(30);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createTitledBorder("Danh sách sản phẩm bảo hành"));

        // Căn giữa dữ liệu
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < header.length; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Bottom panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        
        btnThemSanPham = new ButtonCustom("Chọn SP từ HĐ", "success", 14);
        btnThemSanPham.addActionListener(e -> chonSanPhamTheoHoaDon());
//        btnThemSanPham.setEnabled(cbxHoaDon.getItemCount() > 0);

        btnTaoPhieu = new ButtonCustom("Tạo phiếu BH", "success", 14);
        btnTaoPhieu.addActionListener(e -> taoPhieuBaoHanh());

        bottomPanel.add(btnThemSanPham);
        bottomPanel.add(btnTaoPhieu);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(scroll, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    public void chonSanPhamTheoHoaDon() {
        Integer maHD = (Integer) cbxHoaDon.getSelectedItem();
        if (maHD == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn trước khi chọn sản phẩm!");
            return;
        }

        model.setRowCount(0);
        List<ChiTietHoaDonDTO> listCT = new ChiTietHoaDonBLL().getChiTietTheoMaHoaDon(maHD);

        for (ChiTietHoaDonDTO ct : listCT) {
            if(ct.getMaBaoHanh() == 0){
                int maSP = ct.getMaSanPham();
                SanPhamDTO sp = new SanPhamBLL().getSanPhamById(maSP);
                float thoiGianBH = sp.getThoiGianBaoHanh();

                List<String> imeis = new ChiTietSanPhamBLL().getImeisByHoaDonAndSanPham(maHD, maSP);
                for (String imei : imeis) {

                    LocalDate batDau = LocalDate.now();
                    LocalDate ketThuc = batDau.plusMonths((long) thoiGianBH);
                    model.addRow(new Object[]{
                        sp.getTenSP(), imei,
                        sp.getRam(), sp.getRom(), sp.getChip(), thoiGianBH,
                        batDau, ketThuc
                    });
                }
            }
        }
    }


    public void taoPhieuBaoHanh() {
        Integer maHD = (Integer) cbxHoaDon.getSelectedItem();
        if (maHD == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn trước khi tạo phiếu bảo hành!");
            return;
        }

        HoaDonDTO hoaDon = new HoaDonBLL().getHoaDonById(maHD);
        if (hoaDon == null) {
            JOptionPane.showMessageDialog(this, "Hóa đơn không tồn tại!");
            return;
        }

        int maKH = hoaDon.getMaKH();
        int maPBH = Integer.parseInt(maphieu.getText());
        LocalDate ngayLap = LocalDate.now();
        int maNV = 1;

        PhieuBaoHanhDTO dto = new PhieuBaoHanhDTO(maPBH, ngayLap, maKH, maNV, "", 1);
        boolean success = new PhieuBaoHanhBLL().add(dto);

        if (success) {
            for (int i = 0; i < model.getRowCount(); i++) {
                String tenSP = model.getValueAt(i, 0).toString();
                int maSP = new SanPhamBLL().getSanPhamByName(tenSP).getMaSP();
                String imei = model.getValueAt(i, 1).toString();
                LocalDate batDau = (LocalDate) model.getValueAt(i, 6);
                LocalDate ketThuc = (LocalDate) model.getValueAt(i, 7);

                Date d1 = Date.from(batDau.atStartOfDay(ZoneId.systemDefault()).toInstant());
                Date d2 = Date.from(ketThuc.atStartOfDay(ZoneId.systemDefault()).toInstant());

                ChiTietPhieuBaoHanhDTO ct = new ChiTietPhieuBaoHanhDTO(
                    new ChiTietPhieuBaoHanhBLL().generateNewId(),
                    maPBH, maSP, imei, d1, d2, 1, ""
                );
                new ChiTietPhieuBaoHanhBLL().add(ct);
                new ChiTietSanPhamBLL().capNhatMaBaoHanh(imei,maPBH);
            }
            new ChiTietHoaDonBLL().capNhatMaBaoHanh(maHD, maPBH);
            JOptionPane.showMessageDialog(this, "Tạo phiếu bảo hành thành công!");
            main.setPanel(new PhieuBaoHanhGUI(main));
        } else {
            JOptionPane.showMessageDialog(this, "Tạo phiếu bảo hành thất bại!");
        }
    }

}
