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

import BLL.BUS.KhoHangBLL;
import DTO.KhoHangDTO;
import GUI.DiaLog.KhoHangDialog;
import GUI.Panel.TopNav;
import util.ExportExcelUtility;

import java.awt.*;

public class KhoHangGui extends JPanel {
    TopNav topNav;
    JPanel pnlBot;
    JTable tbl;
    KhoHangBLL khoHangBLL;

    public KhoHangGui(TopNav topNav) {
        khoHangBLL = new KhoHangBLL();
        initComponent(topNav);
        addSearchFunctionality();
        loadData(khoHangBLL.getAllKhoHang());
        chucNang(); // Add functionality to the buttons
    }

    private void initComponent(TopNav topNav) {
        this.topNav = topNav;
        String[] itemFindFor = { "Tất Cả"};
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

        // Set layout for KhoHangGUI
        this.setLayout(new BorderLayout());
        this.add(topNav, BorderLayout.NORTH);
        this.add(pnlBot, BorderLayout.CENTER);
    }

    private void addSearchFunctionality() {
        JTextField textSearch = topNav.getTextSearch();
        JButton btnRefresh = topNav.getBtnRefresh();

        textSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String keyword = textSearch.getText().trim();

                loadData(khoHangBLL.getKhoHangByNameSearch(keyword));
            }
        });

        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                topNav.getFindFor().setSelectedIndex(0);
                textSearch.setText(""); // Clear the search keyword
                loadData(khoHangBLL.getAllKhoHang()); // Reload all data
            }
        });
    }

    private void loadData(List<KhoHangDTO> khoHangList) {
        // Fetch all warehouse data from BLL
        String[] columnNames = { "Mã Kho", "Tên Kho", "Địa Chỉ" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Add data to the table model
        for (KhoHangDTO kho : khoHangList) {
            Object[] rowData = {
                kho.getMaKho(),
                kho.getTenKho(),
                kho.getDiaChi()
            };
            model.addRow(rowData);
        }

        // Set the new model to the table
        tbl.setModel(model);
    }

    private void chucNang() {
        JButton[] btn = topNav.getBtn();

        // Add warehouse
        btn[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(KhoHangGui.this);
                KhoHangDialog dialog = new KhoHangDialog(parentFrame, null, "Thêm Kho");
                dialog.setVisible(true);

                if (dialog.isSaved()) {
                    try {
                        int maKho = khoHangBLL.generateNewId();
                        KhoHangDTO newKhoHang = dialog.getKhoHangData(maKho);
                        if (khoHangBLL.addKhoHang(newKhoHang)) {
                            JOptionPane.showMessageDialog(null, "Thêm kho thành công!");
                            loadData(khoHangBLL.getAllKhoHang());
                        } else {
                            JOptionPane.showMessageDialog(null, "Thêm kho thất bại!");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Lỗi khi thêm kho: " + ex.getMessage());
                    }
                }
            }
        });

        // Edit warehouse
        btn[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một kho để sửa!");
                    return;
                }

                int maKho = (int) tbl.getValueAt(selectedRow, 0);
                String tenKho = (String) tbl.getValueAt(selectedRow, 1);
                String diaChi = (String) tbl.getValueAt(selectedRow, 2);

                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(KhoHangGui.this);

                KhoHangDialog dialog = new KhoHangDialog(parentFrame, new KhoHangDTO(maKho, tenKho, diaChi,1), "Chỉnh Sửa Kho");

                dialog.setVisible(true);

                if (dialog.isSaved()) {
                    KhoHangDTO updatedKhoHang = dialog.getKhoHangData(maKho);
                    if (khoHangBLL.updateKhoHang(updatedKhoHang)) {
                        JOptionPane.showMessageDialog(null, "Cập nhật kho thành công!");
                        loadData(khoHangBLL.getAllKhoHang());
                    } else {
                        JOptionPane.showMessageDialog(null, "Cập nhật thất bại!");
                    }
                }
            }
        });

        // Delete warehouse
        btn[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một kho để xóa!");
                    return;
                }

                int maKho = (int) tbl.getValueAt(selectedRow, 0);
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa kho này?",
                        "Xóa Kho", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (khoHangBLL.deleteKhoHang(maKho)) {
                        JOptionPane.showMessageDialog(null, "Xóa kho thành công!");
                        loadData(khoHangBLL.getAllKhoHang());
                    } else {
                        JOptionPane.showMessageDialog(null, "Xóa kho thất bại!");
                    }
                }
            }
        });

        // View details of warehouse
        btn[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một kho để xem chi tiết!");
                    return;
                }

                int maKho = (int) tbl.getValueAt(selectedRow, 0);
                String tenKho = (String) tbl.getValueAt(selectedRow, 1);
                String diaChi = (String) tbl.getValueAt(selectedRow, 2);

                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(KhoHangGui.this);
                KhoHangDialog dialog = new KhoHangDialog(parentFrame, new KhoHangDTO(maKho, tenKho, diaChi,1), "Xem chi tiết");
                dialog.setVisible(true);
            }
        });

        btn[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ExportExcelUtility.saveTableToExcel(tbl, "Nhân viên");
            }
        });
    }
}
