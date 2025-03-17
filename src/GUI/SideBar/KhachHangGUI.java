package GUI.SideBar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import BLL.BUS.KhachHangBLL;
import DTO.KhachHangDTO;
import GUI.DiaLog.KhachHangDiaLog;
import GUI.Panel.TopNav;
import util.ExportExcelUtility;

import java.awt.*;
import java.util.List;

public class KhachHangGUI extends JPanel {
    TopNav topNav;
    JPanel pnlBot;
    JTable tbl;
    KhachHangBLL khachHangBLL;

    public KhachHangGUI() {
        khachHangBLL = new KhachHangBLL();
        initComponent();
        chucNang();
        addSearchFunctionality();
        loadData(khachHangBLL.getAllKhachHang());

    }

    private void initComponent() {
        String[] itemFindFor = { "Tất Cả","Theo tên", "Theo SDT" };

        topNav = new TopNav("Khách Hàng", "user", itemFindFor);

        // Panel dưới
        pnlBot = new JPanel(new BorderLayout());
        pnlBot.setPreferredSize(new Dimension(0, 500));

        // Tạo JTable
        tbl = new JTable();
        tbl.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbl.setRowHeight(35);
        tbl.setFocusable(false);

        // Tô màu header bảng
        JTableHeader header = tbl.getTableHeader();
        header.setPreferredSize(new Dimension(0, 40));
        header.setBackground(new Color(100, 149, 237)); // Màu nền header (Cornflower Blue)
        header.setForeground(Color.WHITE); // Màu chữ header
        header.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Font chữ header

        // Thêm JScrollPane chứa bảng
        JScrollPane scrollPane = new JScrollPane(tbl);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        pnlBot.setLayout(new BorderLayout());
        pnlBot.setBorder(new EmptyBorder(10, 15, 10, 15));
        pnlBot.add(scrollPane, BorderLayout.CENTER);

        // Thiết lập layout cho KhachHang
        this.setLayout(new BorderLayout());
        this.add(topNav, BorderLayout.NORTH);
        this.add(pnlBot, BorderLayout.CENTER);
    }

    private void chucNang() {
        JButton[] btn = topNav.getBtn();
        btn[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(KhachHangGUI.this);
                KhachHangDiaLog dialog = new KhachHangDiaLog(parentFrame, null, "Thêm Khách Hàng");
                dialog.setVisible(true);

                if (dialog.isSaved()) {
                    try {
                        // Assign a new unique ID (you might generate this differently)
                        int maKH = khachHangBLL.generateNewId();
                        KhachHangDTO newKhachHang = dialog.getKhachHangData(maKH);
                        if (khachHangBLL.addKhachHang(newKhachHang)) {
                            JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công!");
                            loadData(khachHangBLL.getAllKhachHang());
                        } else {
                            JOptionPane.showMessageDialog(null, "Thêm khách hàng thất bại!");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Lỗi khi thêm khách hàng: " + ex.getMessage());
                    }
                }
            }
        });

        btn[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một khách hàng để sửa!");
                    return;
                }

                // Lấy dữ liệu khách hàng từ bảng
                int maKH = (int) tbl.getValueAt(selectedRow, 0);
                String hoTen = (String) tbl.getValueAt(selectedRow, 1);
                String diaChi = (String) tbl.getValueAt(selectedRow, 2);
                String sdt = (String) tbl.getValueAt(selectedRow, 3);

                // Tạo đối tượng DTO từ dữ liệu bảng
                KhachHangDTO kh = new KhachHangDTO(maKH, hoTen, diaChi, sdt);

                // Hiển thị JDialog KhachHangDiaLog
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(KhachHangGUI.this);
                KhachHangDiaLog dialog = new KhachHangDiaLog(parentFrame, kh, "Chỉnh Sửa Khách Hàng");
                dialog.setVisible(true);

                // Sau khi dialog đóng, cập nhật dữ liệu
                if (khachHangBLL.updateKhachHang(kh)) {
                    if (dialog.isSaved()) {
                        JOptionPane.showMessageDialog(null, "Cập nhật thông tin khách hàng thành công!");
                        loadData(khachHangBLL.getAllKhachHang()); // Tải lại bảng
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật thông tin thất bại!");
                }
            }
        });

        btn[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một khách hàng để xóa!");
                    return;
                }

                int maKH = (int) tbl.getValueAt(selectedRow, 0);

                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa khách hàng này?",
                        "Xóa Khách Hàng", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (khachHangBLL.deleteKhachHang(maKH)) {
                        JOptionPane.showMessageDialog(null, "Xóa khách hàng thành công!");
                        loadData(khachHangBLL.getAllKhachHang());
                    } else {
                        JOptionPane.showMessageDialog(null, "Xóa khách hàng thất bại!");
                    }
                }
            }
        });

        btn[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một khách hàng để xem chi tiết!");
                    return;
                }

                // Retrieve data from the selected row
                int maKH = (int) tbl.getValueAt(selectedRow, 0);
                String hoTen = (String) tbl.getValueAt(selectedRow, 1);
                String diaChi = (String) tbl.getValueAt(selectedRow, 2);
                String sdt = (String) tbl.getValueAt(selectedRow, 3);

                // Create a KhachHangDTO object
                KhachHangDTO khachHang = new KhachHangDTO(maKH, hoTen, diaChi, sdt);

                // Open KhachHangDiaLog in "Xem chi tiết" mode
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(KhachHangGUI.this);
                KhachHangDiaLog dialog = new KhachHangDiaLog(parentFrame, khachHang, "Xem chi tiết");
                dialog.setVisible(true);
            }
        });

        btn[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                ExportExcelUtility.saveTableToExcel(tbl, "Khách Hàng");
            }
        });

    }

    private void addSearchFunctionality() {
        JTextField textSearch = topNav.getTextSearch();
        JButton btnRefresh = topNav.getBtnRefresh();

        textSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                // Lấy từ khóa từ trường nhập
                String type = topNav.getFindFor().getSelectedItem().toString().toLowerCase();
                String keyword = textSearch.getText().trim();

                // Gọi phương thức tìm kiếm với từ khóa
                loadData(khachHangBLL.getKhachHangByNameSearch(keyword, type));
            }
        });

        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                topNav.getFindFor().setSelectedIndex(0);
                textSearch.setText(""); // Xóa từ khóa tìm kiếm
                loadData(khachHangBLL.getAllKhachHang()); // Tải lại toàn bộ dữ liệu
            }
        });
    }

    private void loadData(List<KhachHangDTO> khachHangList) {
        // Lấy dữ liệu từ BLL
        String[] columnNames = { "Mã KH", "Tên", "Địa Chỉ", "SĐT" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Thêm dữ liệu vào bảng
        for (KhachHangDTO kh : khachHangList) {
            Object[] rowData = { kh.getMaKH(), kh.getHoTen(), kh.getDiaChi(), kh.getSdt() };
            model.addRow(rowData);
        }
        tbl.setModel(model);
    }
}
