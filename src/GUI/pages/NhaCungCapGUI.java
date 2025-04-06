package GUI.pages;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import BLL.BUS.NhaCungCapBLL;
import DTO.NhaCungCapDTO;
import GUI.DiaLog.NhaCungCapDiaLog;
import GUI.Panel.TopNav;
import util.ExportExcelUtility;

import java.awt.*;

public class NhaCungCapGUI extends JPanel {
    TopNav topNav;
    JPanel pnlBot;
    JTable tbl;
    NhaCungCapBLL nhaCungCapBLL;

    public NhaCungCapGUI(TopNav topNav) {
        nhaCungCapBLL = new NhaCungCapBLL();
        initComponent(topNav);
        chucNang();
        addSearchFunctionality();
        loadData(nhaCungCapBLL.getAllNhaCungCap());
    }

    private void initComponent(TopNav topNav) {
        this.topNav = topNav;
        String[] itemFindFor = { "Tất Cả", "Theo Tên", "Theo SDT" };
        topNav.setItemComboBox(itemFindFor);

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

        // Thiết lập layout cho NhaCungCap
        this.setLayout(new BorderLayout());
        this.add(topNav, BorderLayout.NORTH);
        this.add(pnlBot, BorderLayout.CENTER);
    }

    private void chucNang() {
        JButton[] btn = topNav.getBtn();

        // Add new supplier
        btn[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(NhaCungCapGUI.this);
                NhaCungCapDiaLog dialog = new NhaCungCapDiaLog(owner, null, "Thêm Nhà Cung Cấp");
                dialog.setVisible(true);

                if (dialog.isSaved()) {
                    try {
                        // Assign a new unique ID
                        int maNCC = nhaCungCapBLL.generateNewId();
                        NhaCungCapDTO newNhaCungCap = dialog.getNhaCungCapData(maNCC);
                        if (nhaCungCapBLL.addNhaCungCap(newNhaCungCap)) {
                            JOptionPane.showMessageDialog(null, "Thêm nhà cung cấp thành công!");
                            loadData(nhaCungCapBLL.getAllNhaCungCap());
                        } else {
                            JOptionPane.showMessageDialog(null, "Thêm nhà cung cấp thất bại!");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Lỗi khi thêm nhà cung cấp: " + ex.getMessage());
                    }
                }
            }
        });

        // Edit supplier
        btn[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một nhà cung cấp để sửa!");
                    return;
                }

                // Retrieve supplier data from the table
                int maNCC = (int) tbl.getValueAt(selectedRow, 0);
                String ten = (String) tbl.getValueAt(selectedRow, 1);
                String diaChi = (String) tbl.getValueAt(selectedRow, 2);
                String sDT = (String) tbl.getValueAt(selectedRow, 3);

                // Show NhaCungCapDiaLog dialog
                JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(NhaCungCapGUI.this);
                NhaCungCapDiaLog dialog = new NhaCungCapDiaLog(owner, new NhaCungCapDTO(maNCC, ten, diaChi, sDT),
                        "Chỉnh Sửa Nhà Cung Cấp");
                dialog.setVisible(true);

                // Update supplier data if the dialog is saved
                if (dialog.isSaved()) {
                    NhaCungCapDTO updatedNhaCungCap = dialog.getNhaCungCapData(maNCC);
                    if (nhaCungCapBLL.updateNhaCungCap(updatedNhaCungCap)) {
                        JOptionPane.showMessageDialog(null, "Cập nhật thông tin nhà cung cấp thành công!");
                        loadData(nhaCungCapBLL.getAllNhaCungCap());
                    } else {
                        JOptionPane.showMessageDialog(null, "Cập nhật thông tin thất bại!");
                    }
                }
            }
        });

        // Delete supplier
        btn[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một nhà cung cấp để xóa!");
                    return;
                }

                int maNCC = (int) tbl.getValueAt(selectedRow, 0);

                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa nhà cung cấp này?",
                        "Xóa Nhà Cung Cấp", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (nhaCungCapBLL.deleteNhaCungCap(maNCC)) {
                        JOptionPane.showMessageDialog(null, "Xóa nhà cung cấp thành công!");
                        loadData(nhaCungCapBLL.getAllNhaCungCap());
                    } else {
                        JOptionPane.showMessageDialog(null, "Xóa nhà cung cấp thất bại!");
                    }
                }
            }
        });

        // View supplier details
        btn[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một nhà cung cấp để xem chi tiết!");
                    return;
                }

                int maNCC = (int) tbl.getValueAt(selectedRow, 0);
                String ten = (String) tbl.getValueAt(selectedRow, 1);
                String diaChi = (String) tbl.getValueAt(selectedRow, 2);
                String sDT = (String) tbl.getValueAt(selectedRow, 3);

                JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(NhaCungCapGUI.this);
                NhaCungCapDiaLog dialog = new NhaCungCapDiaLog(owner, new NhaCungCapDTO(maNCC, ten, diaChi, sDT),
                        "Xem chi tiết");
                dialog.setVisible(true);
            }
        });

        // Export to Excel
        btn[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ExportExcelUtility.saveTableToExcel(tbl, "Nhà cung cấp");
            }
        });
    }

    private void addSearchFunctionality() {
        JTextField textSearch = topNav.getTextSearch();
        JButton btnRefresh = topNav.getBtnRefresh();

        textSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String type = topNav.getFindFor().getSelectedItem().toString().toLowerCase();
                String keyword = textSearch.getText().trim();

                loadData(nhaCungCapBLL.getNhaCungCapByNameSearch(keyword, type));
            }
        });

        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                topNav.getFindFor().setSelectedIndex(0);
                textSearch.setText(""); // Xóa từ khóa tìm kiếm
                loadData(nhaCungCapBLL.getAllNhaCungCap()); // Tải lại toàn bộ dữ liệu
            }
        });
    }

    private void loadData(List<NhaCungCapDTO> nhaCungCapList) {
        // Lấy danh sách tất cả nhà cung cấp từ BLL
        String[] columnNames = { "Mã NCC", "Tên", "Địa Chỉ", "SĐT" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Thêm dữ liệu từ danh sách nhà cung cấp vào bảng
        for (NhaCungCapDTO ncc : nhaCungCapList) {
            Object[] rowData = {
                    ncc.getMaNhaCungCap(),
                    ncc.getTen(),
                    ncc.getDiaChi(),
                    ncc.getsDT()
            };
            model.addRow(rowData);
        }

        // Gán model mới vào bảng
        tbl.setModel(model);
    }
}
