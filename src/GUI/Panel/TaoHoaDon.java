/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Panel;

import BLL.BUS.ChiTietHoaDonBLL;
import BLL.BUS.ChiTietSanPhamBLL;
import BLL.BUS.HoaDonBLL;
import BLL.BUS.KhachHangBLL;
import BLL.BUS.KhuyenMaiBLL;
import BLL.BUS.NhaCungCapBLL;
import BLL.BUS.PhieuNhapBLL;
import BLL.BUS.SanPhamBLL;
import DAO.PhieuNhapDao;
import BLL.BUS.SanPhamBLL;
import DTO.ChiTietHoaDonDTO;
import DTO.ChiTietSanPhamDTO;
import DTO.HoaDonDTO;
import DTO.KhachHangDTO;
import DTO.KhuyenMaiDTO;
import DTO.SanPhamDTO;
import DTO.TaiKhoanDTO;
import GUI.DiaLog.ThemKhuyenMaiDiaLog;
import GUI.Frame.Main;
import GUI.Panel.InputType.ButtonCustom;
import GUI.Panel.InputType.InputText;
import GUI.Panel.InputType.SelectForm;
import GUI.pages.HoaDonGUI;
import GUI.pages.PhieuNhapGUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguyen
 */
public class TaoHoaDon extends JPanel {
    JPanel left, right, luachonsanpham, thongtinsanpham, thongtinsanpham_top, thongtinsanpham_bottom,
            luachonsanpham_left, right_top, right_bottom, tongsotien, thongtinsanpham_top_matensp,
            thongtinsanpham_top_chgtsl, thongtinsanpham_bottom_button, thongtinsanpham_bottom_imei, checkbox_imei;
    JTable sanphamTable, sanphamdathemTable, chonimeiTable;
    JLabel tongTien, sotien;
    DefaultTableModel tbmsanphamTable, tbmsanphamdathemTable;
    JScrollPane scrollsanphamTable, scrollsanphamdathemTable, scrollchonimeiCheckbox;
    InputText masptxt, tensptxt, giabantxt, soluongtxt, mahoadontxt, nhanviennhaptxt;
    JTextField timkiemsptxt;
    SelectForm cbxcauhinh, cbxkhachhang;
    ButtonCustom addsp, xuathoadon, sua, xoa, xacnhanimei;
    SanPhamBLL sanphamBLL;
    PhieuNhapDao phieunhapDAO;
    Main main;
    JPanel checkbox_imei_button;
    List<JCheckBox> checkBoxes;
    List<String> selectedImei = new ArrayList<>();
    public InputText makhuyenmai;
    public int tong;
    KhuyenMaiBLL khuyenMaiBLL;

    // List<List<String>> imeitheohang;
    public TaoHoaDon(Main main) {
        initComponent(main);
        loaddatasanpham();
        setThongTinHoaDon();
        chonsanphamdethem();
        chonsanphamdexoasua();
        khuyenMaiBLL = new KhuyenMaiBLL();
        khuyenMaiBLL.autoUpdateTrangThai(khuyenMaiBLL.getAllKhuyenMai());
    }

    public int getTong() {
        return tong;
    }

    public void initComponent(Main main) {
        this.main = main;
        this.setSize(600, 600);
        this.setLayout(new BorderLayout(2, 2));

        left = new JPanel();
        left.setLayout(new BorderLayout(10, 10));

        right = new JPanel();
        right.setLayout(new BorderLayout());
        right.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
        right.setPreferredSize(new Dimension(250, 500));
        right.setBackground(Color.white);

        right_top = new JPanel();
        right_top.setLayout(new FlowLayout());
        right_top.setBackground(Color.white);

        mahoadontxt = new InputText("Mã hóa đơn");
        mahoadontxt.setPreferredSize(new Dimension(250, 80));
        mahoadontxt.setBackground(Color.white);
        mahoadontxt.setEditable(false);
        nhanviennhaptxt = new InputText("Nhân viên nhập");
        nhanviennhaptxt.setPreferredSize(new Dimension(250, 80));
        nhanviennhaptxt.setBackground(Color.white);
        nhanviennhaptxt.setEditable(false);
        String[] arrkhachhang = new KhachHangBLL().getNameKhachHang();
        cbxkhachhang = new SelectForm("Khách hàng", arrkhachhang);
        cbxkhachhang.setPreferredSize(new Dimension(250, 50));
        cbxkhachhang.setBackground(Color.white);
        makhuyenmai = new InputText("Mã khuyến mãi");
        makhuyenmai.setPreferredSize(new Dimension(250, 80));
        makhuyenmai.setBackground(Color.white);

        right_bottom = new JPanel();
        right_bottom.setLayout(new BorderLayout(5, 5));

        xuathoadon = new ButtonCustom("Xuất hóa đơn", "success", 14);
        xuathoadon.setPreferredSize(new Dimension(250, 50));
        xuathoadon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                themhoadon();
                themchitiethoadon();
                xacnhandaban();
                if (makhuyenmai.getText() != null && !makhuyenmai.getText().isEmpty()) {
                    updateSoLuongKM();
                }
                main.setPanel(new HoaDonGUI(main));
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

        ButtonCustom btnThemKM = new ButtonCustom("Thêm Khuyến Mãi", "success", 14);
        btnThemKM.setPreferredSize(new Dimension(250, 50));
        btnThemKM.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(TaoHoaDon.this);
                ThemKhuyenMaiDiaLog dialog = new ThemKhuyenMaiDiaLog(parentFrame, null, "Thêm Khuyến Mãi");
                dialog.setTongTienHoaDon(tong);
                dialog.setVisible(true);

                if (dialog.isSaved()) {
                    KhuyenMaiDTO selectedKM = dialog.getSelectedKhuyenMaiDTO();
                    if (selectedKM != null) {
                        makhuyenmai.setText(String.valueOf(selectedKM.getMaKM()));
                        updateTongTien();
                    } else {
                        JOptionPane.showMessageDialog(null, "Không Có Khuyến Mãi Được Chọn", "Thông Báo",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            }

        });

        right_bottom.add(tongsotien, BorderLayout.CENTER);
        right_bottom.add(xuathoadon, BorderLayout.SOUTH);

        right_top.add(mahoadontxt);
        right_top.add(nhanviennhaptxt);
        right_top.add(cbxkhachhang);
        right_top.add(makhuyenmai);
        right_top.add(btnThemKM);

        right.add(right_top);
        right.add(right_bottom, BorderLayout.SOUTH);

        luachonsanpham = new JPanel();
        luachonsanpham.setLayout(new GridLayout(1, 2));
        luachonsanpham_left = new JPanel();
        luachonsanpham_left.setLayout(new BorderLayout());

        timkiemsptxt = new JTextField();
        timkiemsptxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                timkiemsanpham();
            }

        });
        timkiemsptxt.putClientProperty("JTextField.placeholderText", "Tên sản phẩm, mã sản phẩm...");

        addsp = new ButtonCustom("Thêm sản phẩm", "success", 14);
        addsp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkInput(sanphamTable)) {
                    themsanpham();

                    for (JCheckBox cb : checkBoxes) {
                        if (cb.isSelected()) {
                            selectedImei.add(cb.getText());
                        }
                    }
                    updateTongTien();
                    clearOnhap();
                }

            }
        });

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        sanphamTable = new JTable();

        tbmsanphamTable = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép chỉnh sửa ô
            }
        };

        scrollsanphamTable = new JScrollPane();
        String[] headerSP = new String[] { "Mã SP", "Tên sản phẩm", "Số lượng tồn" };
        tbmsanphamTable.setColumnIdentifiers(headerSP);
        sanphamTable.setModel(tbmsanphamTable);
        scrollsanphamTable.setViewportView(sanphamTable);
        scrollsanphamTable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
        sanphamTable.setFocusable(false);
        for (int i = 0; i < sanphamTable.getColumnCount(); i++) {
            sanphamTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        sanphamdathemTable = new JTable();
        tbmsanphamdathemTable = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        scrollsanphamdathemTable = new JScrollPane();
        String[] headerSPdathem = new String[] { "Mã SP", "Tên sản phẩm", "Ram", "Rom", "Màu sắc", "Đơn giá",
                "Số lượng" };
        tbmsanphamdathemTable.setColumnIdentifiers(headerSPdathem);
        sanphamdathemTable.setModel(tbmsanphamdathemTable);
        scrollsanphamdathemTable.setViewportView(sanphamdathemTable);
        sanphamdathemTable.setFocusable(false);
        scrollsanphamdathemTable.setPreferredSize(new Dimension(350, 250));
        scrollsanphamdathemTable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
        for (int i = 0; i < sanphamdathemTable.getColumnCount(); i++) {
            sanphamdathemTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        xacnhanimei = new ButtonCustom("Chọn Imei", "success", 14);
        xacnhanimei.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int soluong = 0;
                for (JCheckBox cb : checkBoxes) {
                    if (cb.isSelected()) {
                        soluong += 1;
                    }
                }
                soluongtxt.setText(String.valueOf(soluong));
            }
        });
        checkbox_imei_button = new JPanel();
        // checkbox_imei_button.setBackground(Color.white);
        checkbox_imei_button.setLayout(new FlowLayout(FlowLayout.CENTER));
        checkbox_imei_button.add(xacnhanimei);

        checkbox_imei = new JPanel();
        // checkbox_imei.setBackground(Color.white);
        checkbox_imei.setLayout(new BoxLayout(checkbox_imei, BoxLayout.Y_AXIS));
        // checkbox_imei.setLayout(new FlowLayout(FlowLayout.CENTER));
        scrollchonimeiCheckbox = new JScrollPane();
        scrollchonimeiCheckbox.setViewportView(checkbox_imei);

        thongtinsanpham_top = new JPanel();
        thongtinsanpham_top.setLayout(new GridLayout(2, 1));
        thongtinsanpham_top.setBackground(Color.white);

        thongtinsanpham_top_matensp = new JPanel();
        thongtinsanpham_top_matensp.setLayout(new FlowLayout(FlowLayout.CENTER));
        thongtinsanpham_top_matensp.setBackground(Color.white);

        thongtinsanpham_top_chgtsl = new JPanel();
        thongtinsanpham_top_chgtsl.setLayout(new FlowLayout(FlowLayout.CENTER));
        thongtinsanpham_top_chgtsl.setBackground(Color.white);

        thongtinsanpham = new JPanel();
        thongtinsanpham.setLayout(new BorderLayout());
        String[] arr = { "" };
        cbxcauhinh = new SelectForm("Cấu hình", arr);
        cbxcauhinh.setBackground(Color.white);
        cbxcauhinh.setPreferredSize(new Dimension(cbxcauhinh.getPreferredSize().width + 5, 60));
        masptxt = new InputText("Mã SP");
        masptxt.setBackground(Color.white);
        masptxt.setEditable(false);
        masptxt.setPreferredSize(new Dimension(masptxt.getPreferredSize().width, 80));
        tensptxt = new InputText("Tên sản phẩm");
        tensptxt.setBackground(Color.white);
        tensptxt.setPreferredSize(new Dimension(285, 80));
        tensptxt.setEditable(false);
        giabantxt = new InputText("Giá bán");
        giabantxt.setInputType("number");
        giabantxt.setBackground(Color.white);
        giabantxt.setPreferredSize(new Dimension(140, 80));
        soluongtxt = new InputText("Số lượng");
        soluongtxt.setInputType("number");
        soluongtxt.setPreferredSize(new Dimension(soluongtxt.getPreferredSize().width + 50, 80));
        soluongtxt.setBackground(Color.white);
        soluongtxt.setEditable(false);

        thongtinsanpham_top_matensp.add(masptxt);
        thongtinsanpham_top_matensp.add(tensptxt);
        thongtinsanpham_top_chgtsl.add(cbxcauhinh);
        thongtinsanpham_top_chgtsl.add(giabantxt);
        thongtinsanpham_top_chgtsl.add(soluongtxt);

        thongtinsanpham_top.add(thongtinsanpham_top_matensp);
        thongtinsanpham_top.add(thongtinsanpham_top_chgtsl);

        thongtinsanpham_bottom = new JPanel();
        thongtinsanpham_bottom.setLayout(new BorderLayout());
        thongtinsanpham_bottom.setBackground(Color.white);
        thongtinsanpham_bottom_imei = new JPanel();
        thongtinsanpham_bottom_imei.add(checkbox_imei_button);
        thongtinsanpham_bottom_button = new JPanel();
        thongtinsanpham_bottom_button.setLayout(new GridLayout(1, 2));

        sua = new ButtonCustom("Sửa", "warning", 14);
        sua.setEnabled(false);
        sua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                suasp();
            }
        });

        xoa = new ButtonCustom("Xóa", "danger", 14);
        xoa.setEnabled(false);
        xoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = sanphamdathemTable.getSelectedRow();
                if (selectedRow != -1) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa", "Xác nhận",
                            JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        tbmsanphamdathemTable.removeRow(selectedRow);
                        updateTongTien();
                        clearOnhap();
                        checkbox_imei.removeAll();
                    }
                }
            }
        });

        thongtinsanpham_bottom_button.add(sua);
        thongtinsanpham_bottom_button.add(xoa);
        thongtinsanpham_bottom.add(thongtinsanpham_bottom_button, BorderLayout.SOUTH);
        thongtinsanpham_bottom.add(thongtinsanpham_bottom_imei, BorderLayout.CENTER);

        thongtinsanpham.add(thongtinsanpham_top, BorderLayout.NORTH);
        thongtinsanpham.add(scrollchonimeiCheckbox, BorderLayout.CENTER);
        thongtinsanpham.add(thongtinsanpham_bottom, BorderLayout.SOUTH);

        luachonsanpham_left.add(timkiemsptxt, BorderLayout.NORTH);
        luachonsanpham_left.add(scrollsanphamTable, BorderLayout.CENTER);
        luachonsanpham_left.add(addsp, BorderLayout.SOUTH);

        luachonsanpham.add(luachonsanpham_left);
        luachonsanpham.add(thongtinsanpham);
        left.add(luachonsanpham, BorderLayout.CENTER);
        left.add(scrollsanphamdathemTable, BorderLayout.SOUTH);

        this.add(left, BorderLayout.CENTER);
        this.add(right, BorderLayout.EAST);
        this.setVisible(true);
    }

    public void loaddatasanpham() {
        sanphamBLL = new SanPhamBLL();
        List<SanPhamDTO> list = sanphamBLL.getAllSanPham();
        tbmsanphamTable.setRowCount(0);
        for (SanPhamDTO sp : list) {
            tbmsanphamTable.addRow(new Object[] { sp.getMaSP(), sp.getTenSP(), sp.getSoLuong() });
        }
    }

    public void setThongTinHoaDon() {
        TaiKhoanDTO taiKhoan = TaiKhoanDTO.getTaiKhoanHienTai();
        nhanviennhaptxt.setText(taiKhoan.getTenDangNhap());
        int mahoadon = new HoaDonBLL().getMaHoaDon();
        mahoadontxt.setText(String.valueOf(mahoadon));
    }

    public void chonsanphamdethem() {
        sanphamTable.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) { // Chỉ thực thi khi người dùng thực sự chọn
                clearOnhap();
                addsp.setEnabled(true);
                sua.setEnabled(false);
                xoa.setEnabled(false);
                int selectedRow = sanphamTable.getSelectedRow();
                if (selectedRow == -1)
                    return;

                int masp = Integer.parseInt(tbmsanphamTable.getValueAt(selectedRow, 0).toString());
                for (int row = 0; row < sanphamdathemTable.getRowCount(); row++) {
                    int maspdaco = Integer.parseInt(sanphamdathemTable.getValueAt(row, 0).toString());
                    if (masp == maspdaco) {
                        JOptionPane.showMessageDialog(null,
                                "Sản phẩm mã " + maspdaco + " đã được thêm chỉ có thể sửa hoặc xóa");
                        checkbox_imei.removeAll();
                        sanphamTable.clearSelection();
                        sanphamdathemTable.clearSelection();
                        return;
                    }
                }

                Object maSP = sanphamTable.getValueAt(selectedRow, 0);
                SanPhamDTO sanphamDaChon = new SanPhamBLL().getSanPhamById((int) maSP);
                tensptxt.setText(sanphamDaChon.getTenSP());
                masptxt.setText(String.valueOf(sanphamDaChon.getMaSP()));
                giabantxt.setText(String.valueOf(sanphamDaChon.getGiaBan()));
                String[] cauhinh = { cauhinh(sanphamTable) };
                cbxcauhinh.setArr(cauhinh);
                sanphamdathemTable.clearSelection();

                checkbox_imei.removeAll();

                List<String> imeiList = new ChiTietSanPhamBLL()
                        .getImeisBySanPham(Integer.parseInt(sanphamTable.getValueAt(selectedRow, 0).toString()));
                if (imeiList.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Sản phẩm bạn chọn đã hết hàng hãy chọn sản phẩm khác hoặc nhập thêm");
                    clearOnhap();
                }
                checkBoxes = new ArrayList<>();
                for (String imei : imeiList) {
                    JCheckBox checkBox = new JCheckBox(imei);
                    checkBox.setAlignmentX(Component.CENTER_ALIGNMENT);
                    checkbox_imei.add(checkBox);
                    checkBoxes.add(checkBox);
                }

            }
        });

    }

    public void chonsanphamdexoasua() {
        sanphamdathemTable.getSelectionModel().addListSelectionListener(event -> {
            addsp.setEnabled(false);
            sua.setEnabled(true);
            xoa.setEnabled(true);
            int selectedRow = sanphamdathemTable.getSelectedRow();
            if (selectedRow != -1) {
                tensptxt.setText(tbmsanphamdathemTable.getValueAt(selectedRow, 1).toString());
                masptxt.setText(tbmsanphamdathemTable.getValueAt(selectedRow, 0).toString());
                giabantxt.setText(tbmsanphamdathemTable.getValueAt(selectedRow, 5).toString());
                soluongtxt.setText(tbmsanphamdathemTable.getValueAt(selectedRow, 6).toString());
                String[] cauhinh = { cauhinh(sanphamdathemTable) };
                cbxcauhinh.setArr(cauhinh);
                sanphamTable.clearSelection();

                checkbox_imei.removeAll();

                List<String> imeiList = new ChiTietSanPhamBLL().getImeisBySanPham(
                        Integer.parseInt(tbmsanphamdathemTable.getValueAt(selectedRow, 0).toString()));
                checkBoxes = new ArrayList<>();
                for (String imei : imeiList) {
                    JCheckBox checkBox = new JCheckBox(imei);
                    checkBox.setAlignmentX(Component.CENTER_ALIGNMENT);
                    checkbox_imei.add(checkBox);
                    checkBoxes.add(checkBox);
                }

                for (String imeiSelected : selectedImei) {
                    // Tìm checkbox tương ứng với IMEI đã chọn và đánh dấu là chọn
                    for (JCheckBox checkBox : checkBoxes) {
                        if (checkBox.getText().equals(imeiSelected)) {
                            checkBox.setSelected(true); // Đánh dấu checkbox đã chọn
                        }
                    }
                }

            }

        });
    }

    public void clearOnhap() {
        tensptxt.setText("");
        masptxt.setText("");
        giabantxt.setText("");
        String[] cauhinh = { "" };
        cbxcauhinh.setArr(cauhinh);
        soluongtxt.setText("");
    }

    public String cauhinh(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            return "";
        }
        int maSP = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
        SanPhamDTO sanphamDaChon = new SanPhamBLL().getSanPhamById(maSP);

        return sanphamDaChon.getRam() + "-" + sanphamDaChon.getRom() + "-" + sanphamDaChon.getMauSac();
    }

    public void themsanpham() {
        String masp = masptxt.getText();
        String tensp = tensptxt.getText();
        String[] cauhinh = cbxcauhinh.getValue().split("-");
        String ram = cauhinh[0];
        String rom = cauhinh[1];
        String mausac = cauhinh[2];
        String dongia = giabantxt.getText();
        String soluong = soluongtxt.getText();
        tbmsanphamdathemTable.addRow(new Object[] { masp, tensp, ram, rom, mausac, dongia, soluong });
        checkbox_imei.removeAll();
    }

    public boolean checkInput(JTable table) {
        boolean check = true;

        if (table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Hãy chọn 1 sản phẩm");
            check = false;
        }

        if (giabantxt.getText().length() == 0) {
            giabantxt.setLblError("Thiếu thông tin");
            check = false;
        } else {
            giabantxt.setLblError("");
            if (giabantxt.getText().equals("0")) {
                giabantxt.setLblError("Giá nhập khác 0");
                check = false;
            } else {
                giabantxt.setLblError("");
            }
        }

        if (soluongtxt.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Hãy chọn imei theo số lượng cần mua");
            check = false;
        } else {
            soluongtxt.setLblError("");
            if (soluongtxt.getText().equals("0")) {
                JOptionPane.showMessageDialog(null, "Hãy chọn imei theo số lượng cần mua");
                check = false;
            } else {
                soluongtxt.setLblError("");
            }
        }

        return check;
    }

    public void suasp() {
        if (checkInput(sanphamdathemTable)) {
            int selectedRow = sanphamdathemTable.getSelectedRow();
            if (selectedRow != -1) {
                String masp = masptxt.getText();
                sanphamdathemTable.setValueAt(masp, selectedRow, 0);
                String tensp = tensptxt.getText();
                sanphamdathemTable.setValueAt(tensp, selectedRow, 1);
                String[] cauhinh = cbxcauhinh.getValue().split("-");
                String ram = cauhinh[0];
                sanphamdathemTable.setValueAt(ram, selectedRow, 2);
                String rom = cauhinh[1];
                sanphamdathemTable.setValueAt(rom, selectedRow, 3);
                String mausac = cauhinh[2];
                sanphamdathemTable.setValueAt(mausac, selectedRow, 4);
                String dongia = giabantxt.getText();
                sanphamdathemTable.setValueAt(dongia, selectedRow, 5);
                String soluong = soluongtxt.getText();
                sanphamdathemTable.setValueAt(soluong, selectedRow, 6);
                updateTongTien();

            }
            JOptionPane.showMessageDialog(null, "Đã sửa thành công");
        }
    }

    public void themhoadon() {
        HoaDonDTO hoadon = new HoaDonDTO();
        KhachHangDTO khachhang = new KhachHangDTO();
        khachhang = new KhachHangBLL().getKhachHangByName(cbxkhachhang.getValue());
        TaiKhoanDTO taiKhoan = TaiKhoanDTO.getTaiKhoanHienTai();
        hoadon.setMaKH(khachhang.getMaKH());
        hoadon.setMaNhanVien(taiKhoan.getMaNV());
        LocalDate today = LocalDate.now();
        hoadon.setNgayXuat(today);
        new HoaDonBLL().addHoaDon(hoadon);
    }

    public void themchitiethoadon() {
        for (int row = 0; row < sanphamdathemTable.getRowCount(); row++) {
            ChiTietHoaDonDTO chitiethoadon = new ChiTietHoaDonDTO();
            chitiethoadon.setMaSanPham(Integer.parseInt(sanphamdathemTable.getValueAt(row, 0).toString()));
            chitiethoadon.setSoLuong(Integer.parseInt(sanphamdathemTable.getValueAt(row, 6).toString()));
            SanPhamDTO sanphamdto = new SanPhamBLL()
                    .getSanPhamById(Integer.parseInt(sanphamdathemTable.getValueAt(row, 0).toString()));
            int soluongMoi = sanphamdto.getSoLuong()
                    - Integer.parseInt(sanphamdathemTable.getValueAt(row, 6).toString());
            new SanPhamBLL().updateSoluong(Integer.parseInt(sanphamdathemTable.getValueAt(row, 0).toString()),
                    soluongMoi);
            // chitiethoadon.setDonGia(Integer.parseInt(sanphamdathemTable.getValueAt(row,
            // 5).toString()));
            chitiethoadon.setDonGia(tong);
            chitiethoadon.setMaHoaDon(Integer.parseInt(mahoadontxt.getText()));
            ChiTietHoaDonBLL bll = new ChiTietHoaDonBLL();
            bll.addChiTietHoaDon(chitiethoadon);
        }
    }

    public void xacnhandaban() {
        ChiTietSanPhamDTO ctsp = new ChiTietSanPhamDTO();
        ChiTietSanPhamBLL ctspbll = new ChiTietSanPhamBLL();
        ctsp.setMaHoadon(Integer.parseInt(mahoadontxt.getText()));
        for (String imeiSelected : selectedImei) {
            ctsp.setMaImei(imeiSelected);
            ctspbll.xacNhanDaBan(ctsp);
        }

    }

    public void timkiemsanpham() {
        String keyword = timkiemsptxt.getText().trim().toLowerCase();
        tbmsanphamTable.setRowCount(0);

        List<SanPhamDTO> list = sanphamBLL.getAllSanPham();
        for (SanPhamDTO sp : list) {
            String maSP = String.valueOf(sp.getMaSP());
            String tenSP = sp.getTenSP().toLowerCase();
            if (maSP.contains(keyword) || tenSP.contains(keyword)) {
                tbmsanphamTable.addRow(new Object[] { sp.getMaSP(), sp.getTenSP(), sp.getSoLuong() });
            }
        }
    }

    public void updateTongTien() {
        tong = 0;
        for (int row = 0; row < tbmsanphamdathemTable.getRowCount(); row++) {
            int tiensanpham = Integer.parseInt(tbmsanphamdathemTable.getValueAt(row, 5).toString())
                    * Integer.parseInt(tbmsanphamdathemTable.getValueAt(row, 6).toString());
            tong = tong + tiensanpham;
        }
        
        String maKMText = makhuyenmai.getText();
        
        if (maKMText != null && !maKMText.isEmpty()) {
            try {
                int maKM = Integer.valueOf(maKMText);
                KhuyenMaiDTO dto = khuyenMaiBLL.getKhuyenMaiByID(maKM);
                if (dto != null) {
                    if (dto.getHinhThuc() == 1) {
                        double phamTramGiam = dto.getGiaTri();
                        tong = (int) (tong * (100.0 - phamTramGiam) / 100.0);
                    } else if (dto.getHinhThuc() == 2) {
                        tong -= dto.getGiaTri();
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();

            }
        }
        NumberFormat kieuhienthi = NumberFormat.getInstance(Locale.US);
        sotien.setText(kieuhienthi.format(tong) + "đ");
    }

    public void updateSoLuongKM() {
        String maKMText = makhuyenmai.getText();
        if (maKMText != null && !maKMText.isEmpty()) {
            try {
                int maKM = Integer.valueOf(maKMText);
                khuyenMaiBLL.giamSoLuongKM(maKM);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }
}
