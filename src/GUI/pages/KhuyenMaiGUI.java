package GUI.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import BLL.BUS.KhuyenMaiBLL;
import DTO.KhuyenMaiDTO;
import GUI.DiaLog.KhuyenMaiDialog;
import GUI.Panel.TopNav;
import util.ExportExcelUtility;
import util.ImportExcelUtility;

public class KhuyenMaiGUI extends JPanel {
    TopNav topNav;
    JPanel pnlBot;
    JTable tbl;
    KhuyenMaiBLL khuyenMaiBLL;

    public KhuyenMaiGUI(TopNav topNav) {
        khuyenMaiBLL = new KhuyenMaiBLL();
        khuyenMaiBLL.autoUpdateTrangThai(khuyenMaiBLL.getAllKhuyenMai());
        initComponent(topNav);
        filter();
        refresh();
        loadData(khuyenMaiBLL.getAllKhuyenMai());
        chucnang(); // Add functionality to the buttons
    }

    private void initComponent(TopNav topNav) {
        this.topNav = topNav;
        String[] itemFindFor = { "Tất Cả", "Theo Số Tiền", "Theo Phần Trăm" };
        topNav.setItemComboBox(itemFindFor);

        // Bottom Panel
        pnlBot = new JPanel(new BorderLayout());
        pnlBot.setPreferredSize(new Dimension(0, 500));

        // Create JTable
        tbl = new JTable();
        tbl.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbl.setRowHeight(35);
        tbl.setFocusable(false);

        // Style the table header
        JTableHeader header = tbl.getTableHeader();
        header.setPreferredSize(new Dimension(0, 40));
        header.setBackground(new Color(100, 149, 237)); // Cornflower Blue
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));

        // Add JScrollPane containing the table
        JScrollPane scrollPane = new JScrollPane(tbl);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        pnlBot.setBorder(new EmptyBorder(10, 15, 10, 15));
        pnlBot.add(scrollPane, BorderLayout.CENTER);

        // Set layout for TaiKhoanGUI
        this.setLayout(new BorderLayout());
        this.add(topNav, BorderLayout.NORTH);
        this.add(pnlBot, BorderLayout.CENTER);
    }

    public void loadData(List<KhuyenMaiDTO> khuyenMaiList) {
        String[] columnName = { "Mã Khuyến Mãi", "Tên Khuyến Mãi", "Số Lượng", "Ngày Bắt Đầu", "Ngày Kết Thúc",
                "Áp Dụng Cho Hóa Đơn Từ",
                "Giá Trị", "Hình Thức" };
        DefaultTableModel model = new DefaultTableModel(columnName, 0);
        for (KhuyenMaiDTO dto : khuyenMaiList) {
            String hinhthuc;
            if (dto.getHinhThuc() == 1) {
                hinhthuc = "%";
            } else {
                hinhthuc = "tien";
            }
            Object[] km = {
                    dto.getMaKM(),
                    dto.getTenKM(),
                    dto.getSoLuong(),
                    dto.getNgayBD(),
                    dto.getNgayKT(),
                    dto.getApDungChoHoaDonTu(),
                    dto.getGiaTri(),
                    hinhthuc
            };
            model.addRow(km);
        }
        tbl.setModel(model);
    }

    private void chucnang() {
        JButton[] btn = topNav.getBtn();
        btn[0].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(KhuyenMaiGUI.this);
                KhuyenMaiDialog dialog = new KhuyenMaiDialog(parentFrame, null, "Thêm Khuyến Mãi");
                dialog.setVisible(true);

                if (dialog.isSaved()) {
                    try {
                        int maKM = khuyenMaiBLL.generateNewId();
                        KhuyenMaiDTO newKhuyenMaiDTO = dialog.getKhuyenMaiData(maKM);
                        if (khuyenMaiBLL.addKhuyenMai(newKhuyenMaiDTO)) {
                            JOptionPane.showMessageDialog(null, "Thêm Khuyến Mãi Thành Công");
                            khuyenMaiBLL.autoUpdateTrangThai(khuyenMaiBLL.getAllKhuyenMai());
                            loadData(khuyenMaiBLL.getAllKhuyenMai());
                        } else {
                            JOptionPane.showMessageDialog(null, "Thêm Khuyến Mãi Thất Bại");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Lỗi Thêm Khuyến Mãi" + ex.getMessage());
                    }
                }
            }
        });

        btn[1].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Chọn 1 Khuyến Mãi Để Sửa !");
                    return;
                }
                int maKM = (int) tbl.getValueAt(selectedRow, 0);
                KhuyenMaiDTO khuyenMaiDTO = khuyenMaiBLL.getKhuyenMaiByID(maKM);
                

                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(KhuyenMaiGUI.this);
                KhuyenMaiDialog dialog = new KhuyenMaiDialog(parentFrame, khuyenMaiDTO, "Chỉnh Sửa Khuyến Mãi");
                dialog.setVisible(true);

                if (khuyenMaiBLL.updateKhuyenMai(khuyenMaiDTO)) {
                    if (dialog.isSaved()) {
                        JOptionPane.showMessageDialog(dialog, "Cập Nhật Khuyến Mãi Thành Công");
                        khuyenMaiBLL.autoUpdateTrangThai(khuyenMaiBLL.getAllKhuyenMai());
                        loadData(khuyenMaiBLL.getAllKhuyenMai());
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Cập Nhật Khuyến Mãi Thất Bại");
                    }
                }
            }

        });

        btn[2].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Chọn 1 Khuyến Mãi Để Xóa");
                    return;
                }
                int maKM = (int) tbl.getValueAt(selectedRow, 0);
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn Có Chắc Xóa Khuyến Mãi Này Không",
                        "Xóa Khuyến Mãi", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (khuyenMaiBLL.deleteKhuyenMai(maKM)) {
                        JOptionPane.showMessageDialog(null, "Xóa Khuyến Mãi Thành Công");
                        loadData(khuyenMaiBLL.getAllKhuyenMai());
                    } else {
                        JOptionPane.showMessageDialog(null, "Xóa Khuyến Mãi Không Thành Công");
                    }
                }
            }

        });

        btn[3].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Chọn 1 Khuyến Mãi Để Xem Chi Tiết");
                    return;
                }
                int maKM = (int) tbl.getValueAt(selectedRow, 0);
                KhuyenMaiDTO khuyenMaiDTO = khuyenMaiBLL.getKhuyenMaiByID(maKM);
                

                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(KhuyenMaiGUI.this);
                KhuyenMaiDialog dialog = new KhuyenMaiDialog(parentFrame, khuyenMaiDTO, "Xem Chi Tiết");
                dialog.setVisible(true);
            }
        });

        btn[4].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ImportExcelUtility.openAndImportExcel(
                        (KhuyenMaiDTO dto) -> khuyenMaiBLL.addKhuyenMai(dto),
                        (Object[] rowData) -> new KhuyenMaiDTO(
                                khuyenMaiBLL.generateNewId(),
                                (String) rowData[1],
                                (int) rowData[0],
                                (Date) rowData[2],
                                (Date) rowData[3],
                                (int) rowData[4],
                                (int) rowData[5],
                                (int) rowData[6], 1));
                loadData(khuyenMaiBLL.getAllKhuyenMai());
            }

        });

        btn[5].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                ExportExcelUtility.saveTableToExcel(tbl, "Khuyến Mãi");
            }
        });
    }

    private void filter() {
        JComboBox<String> comboBox = topNav.getFindFor();
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String type = comboBox.getSelectedItem().toString();
                loadData(khuyenMaiBLL.getKhuyenMaiByFilter(type));
            }
        });
    }

    private void refresh() {
        JButton btnRefresh = topNav.getBtnRefresh();
        btnRefresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                topNav.getFindFor().setSelectedIndex(0);
                topNav.getTextSearch().setText("");
                loadData(khuyenMaiBLL.getAllKhuyenMai());
            }

        });
    }
}
