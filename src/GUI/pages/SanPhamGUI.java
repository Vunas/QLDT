package GUI.pages;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;

import BLL.BUS.SanPhamBLL;
import DTO.NhanVienDTO;
import DTO.SanPhamDTO;
import GUI.DiaLog.SanPhamDiaLog;//1
import GUI.Panel.TopNav;
import util.ExportExcelUtility;
import util.ImportExcelUtility;

import java.awt.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SanPhamGUI extends JPanel {
    TopNav topNav;
    JPanel pnlBot;
    JTable tbl;
    SanPhamBLL sanPhamBLL;

    public SanPhamGUI(TopNav topNav) {
        sanPhamBLL = new SanPhamBLL();
        initComponent(topNav);
        chucNang();
        addSearchFunctionality();
        loadData(sanPhamBLL.getAllSanPham());

    }

    private void initComponent(TopNav topNav) {
        this.topNav = topNav;
        String[] itemFindFor = { "Tất Cả", "Theo tên" };
        topNav.setItemComboBox(itemFindFor);

        // Panel dưới
        pnlBot = new JPanel(new BorderLayout());
        pnlBot.setPreferredSize(new Dimension(0, 500));

        // Tạo JTable
        tbl = new JTable();
        tbl.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        // tbl.setRowHeight(35);
        tbl.setRowHeight(50);
        tbl.setFocusable(false);
        tbl.setAutoCreateRowSorter(true);
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

        this.setLayout(new BorderLayout());
        this.add(topNav, BorderLayout.NORTH);
        this.add(pnlBot, BorderLayout.CENTER);
    }

    private void chucNang() {
        JButton[] btn = topNav.getBtn();
        btn[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(SanPhamGUI.this);
                SanPhamDiaLog dialog = new SanPhamDiaLog(parentFrame, null, "Thêm Sản Phẩm");
                dialog.setVisible(true);

                if (dialog.isSaved()) {
                    try {
                        // Assign a new unique ID (you might generate this differently)
                        int maSP = sanPhamBLL.generateNewId();
                        SanPhamDTO newKhachHang = dialog.getSanPhamData(maSP);
                        if (sanPhamBLL.addSanPham(newKhachHang)) {
                            JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công!");
                            loadData(sanPhamBLL.getAllSanPham());
                        } else {
                            JOptionPane.showMessageDialog(null, "Thêm sản phẩm thất bại!");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Lỗi khi thêm sản phẩm: " + ex.getMessage());
                    }
                }
            }
        });

        btn[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một sản phẩm để sửa!");
                    return;
                }
                NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                // Lấy dữ liệu sản phẩm từ bảng
                int maSP = (int) tbl.getValueAt(selectedRow, 0);
                String tenSP = (String) tbl.getValueAt(selectedRow, 1);

                String Img = (String) tbl.getValueAt(selectedRow, 3);

                int soLuong = (int) tbl.getValueAt(selectedRow, 4);

                int giaNhap = 0;
                try {
                    giaNhap = (int) formatter.parse((String) tbl.getValueAt(selectedRow, 5)).intValue();
                } catch (ParseException ex) {
                    Logger.getLogger(SanPhamGUI.class.getName()).log(Level.SEVERE, null, ex);
                }

                int giaBan = 0;
                try {
                    giaBan = (int) formatter.parse((String) tbl.getValueAt(selectedRow, 6)).intValue();
                } catch (ParseException ex) {
                    Logger.getLogger(SanPhamGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                String mauSac = (String) tbl.getValueAt(selectedRow, 7);
                String thuongHieu = (String) tbl.getValueAt(selectedRow, 8);
                int Ram = (int) tbl.getValueAt(selectedRow, 9);
                int Rom = (int) tbl.getValueAt(selectedRow, 10);
                String Chip = (String) tbl.getValueAt(selectedRow, 11);
                float thoiGianBaoHanh = (float) tbl.getValueAt(selectedRow, 12);

                // Tạo đối tượng DTO từ dữ liệu bảng
                SanPhamDTO sanPham = new SanPhamDTO(maSP, tenSP, Img, soLuong, giaNhap, giaBan, mauSac, thuongHieu, Ram,
                        Rom, Chip, thoiGianBaoHanh, 1);

                // Hiển thị JDialog
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(SanPhamGUI.this);
                SanPhamDiaLog dialog = new SanPhamDiaLog(parentFrame, sanPham, "Chỉnh Sửa");
                dialog.setVisible(true);

                // Sau khi dialog đóng, cập nhật dữ liệu
                if (sanPhamBLL.updateSanPham(sanPham)) {
                    if (dialog.isSaved()) {
                        JOptionPane.showMessageDialog(null, "Cập nhật thông tin thành công!");
                        loadData(sanPhamBLL.getAllSanPham()); // Tải lại bảng
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
                    JOptionPane.showMessageDialog(null, "Hãy chọn một sản phẩm để xóa!");
                    return;
                }

                int maSP = (int) tbl.getValueAt(selectedRow, 0);

                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa sản phẩm này?",
                        "Xóa Sản Phẩm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (sanPhamBLL.deleteSanPham(maSP)) {
                        JOptionPane.showMessageDialog(null, "Xóa thành công!");
                        loadData(sanPhamBLL.getAllSanPham());
                    } else {
                        JOptionPane.showMessageDialog(null, "Xóa thất bại!");
                    }
                }
            }
        });

        btn[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một sản phẩm để xem chi tiết!");
                    return;
                }
                NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                // Retrieve data from the selected row
                int maSP = (int) tbl.getValueAt(selectedRow, 0);
                String tenSP = (String) tbl.getValueAt(selectedRow, 1);

                String Img = (String) tbl.getValueAt(selectedRow, 3);

                int soLuong = (int) tbl.getValueAt(selectedRow, 4);

                int giaNhap = 0;
                try {
                    giaNhap = (int) formatter.parse((String) tbl.getValueAt(selectedRow, 5)).intValue();
                } catch (ParseException ex) {
                    Logger.getLogger(SanPhamGUI.class.getName()).log(Level.SEVERE, null, ex);
                }

                int giaBan = 0;
                try {
                    giaBan = (int) formatter.parse((String) tbl.getValueAt(selectedRow, 6)).intValue();
                } catch (ParseException ex) {
                    Logger.getLogger(SanPhamGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                String mauSac = (String) tbl.getValueAt(selectedRow, 7);
                String thuongHieu = (String) tbl.getValueAt(selectedRow, 8);
                int Ram = (int) tbl.getValueAt(selectedRow, 9);
                int Rom = (int) tbl.getValueAt(selectedRow, 10);
                String Chip = (String) tbl.getValueAt(selectedRow, 11);
                float thoiGianBaoHanh = (float) tbl.getValueAt(selectedRow, 12);

                // Create a object
                SanPhamDTO sanPham = new SanPhamDTO(maSP, tenSP, Img, soLuong, giaNhap, giaBan, mauSac, thuongHieu, Ram,
                        Rom, Chip, thoiGianBaoHanh, 1);

                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(SanPhamGUI.this);
                SanPhamDiaLog dialog = new SanPhamDiaLog(parentFrame, sanPham, "Xem chi tiết");
                dialog.setVisible(true);
            }
        });

        btn[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImportExcelUtility.openAndImportExcel((SanPhamDTO dto) -> sanPhamBLL.addSanPham(dto),
                        (Object[] rowData) -> new SanPhamDTO(sanPhamBLL.generateNewId(), rowData[0].toString(),
                                rowData[1].toString(), (int) rowData[2], (int) rowData[3], (int) rowData[4],
                                rowData[5].toString(), rowData[6].toString(), (int) rowData[7], (int) rowData[8],
                                rowData[9].toString(), (float) rowData[10], 1));

                loadData(sanPhamBLL.getAllSanPham());
            }
        });

        btn[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                loadData(sanPhamBLL.getSanPhamByNameSearch(keyword, type));
            }
        });

        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                topNav.getFindFor().setSelectedIndex(0);
                textSearch.setText(""); // Xóa từ khóa tìm kiếm
                loadData(sanPhamBLL.getAllSanPham()); // Tải lại toàn bộ dữ liệu
            }
        });
    }

    // private void loadData(List<SanPhamDTO> sanPhamList) {
    // // Lấy dữ liệu từ BLL
    // String[] columnNames = { "Mã SP", "Tên SP", "Image", "Số Lượng", "Giá Nhập",
    // "Giá Bán", "Màu Sắc", "Thương Hiệu", "Ram", "Rom", "Chip", "Thời Gian BH"};
    // DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    // NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi",
    // "VN"));
    // // Thêm dữ liệu vào bảng
    // for (SanPhamDTO sp : sanPhamList) {
    // Object[] rowData = { sp.getMaSP(), sp.getTenSP(), sp.getImg(),
    // sp.getSoLuong(),formatter.format(sp.getGiaNhap()),
    // formatter.format(sp.getGiaBan()), sp.getMauSac(),
    // sp.getThuongHieu(),sp.getRam(),sp.getRom(),sp.getChip(),sp.getThoiGianBaoHanh()};
    // model.addRow(rowData);
    // }
    // tbl.setModel(model);
    // }
    private void loadData(List<SanPhamDTO> sanPhamList) {
        // Add an extra hidden column to store the image path
        String[] columnNames = { "Mã SP", "Tên SP", "Image", "Hidden Path", "Số Lượng", "Giá Nhập", "Giá Bán",
                "Màu Sắc", "Thương Hiệu", "Ram", "Rom", "Chip", "Thời Gian BH" };

        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                return (column == 2) ? ImageIcon.class : Object.class; // Column 2 displays ImageIcon
            }
        };

        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

        for (SanPhamDTO sp : sanPhamList) {
            ImageIcon icon = new ImageIcon(sp.getImg()); // Load image
            Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH); // Resize
            ImageIcon resizedIcon = new ImageIcon(img);

            Object[] rowData = {
                    sp.getMaSP(),
                    sp.getTenSP(),
                    resizedIcon, // Display Image
                    sp.getImg(), // Hidden Path
                    sp.getSoLuong(),
                    formatter.format(sp.getGiaNhap()),
                    formatter.format(sp.getGiaBan()),
                    sp.getMauSac(),
                    sp.getThuongHieu(),
                    sp.getRam(),
                    sp.getRom(),
                    sp.getChip(),
                    sp.getThoiGianBaoHanh()
            };
            model.addRow(rowData);
        }

        tbl.setModel(model);
        tbl.setRowHeight(80); // Adjust row height for images

        // Hide the "Hidden Path" column (Index 3)
        tbl.getColumnModel().getColumn(3).setMinWidth(0);
        tbl.getColumnModel().getColumn(3).setMaxWidth(0);
        tbl.getColumnModel().getColumn(3).setWidth(0);
    }

}
