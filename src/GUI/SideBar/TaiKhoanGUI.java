package GUI.SideBar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import BLL.BUS.TaiKhoanBLL;
import DTO.TaiKhoanDTO;
import GUI.DiaLog.TaiKhoanDialog;
import GUI.Panel.TopNav;
import util.ExportExcelUtility;

public class TaiKhoanGUI extends JPanel {
    TopNav topNav;
    JPanel pnlBot;
    JTable tbl;
    TaiKhoanBLL taiKhoanBLL;

    public TaiKhoanGUI() {
        taiKhoanBLL = new TaiKhoanBLL();
        initComponent();
        addSearchFunctionality();
        loadData(taiKhoanBLL.getAllTaiKhoan());
        chucNang(); // Add functionality to the buttons
    }

    private void initComponent() {
        String[] itemFindFor = { "Tất Cả" };

        topNav = new TopNav("Tài Khoản", "account_circle", itemFindFor);

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

    private void addSearchFunctionality() {
        JTextField textSearch = topNav.getTextSearch();
        JButton btnRefresh = topNav.getBtnRefresh();

        textSearch.addActionListener(e -> {
            String keyword = textSearch.getText().trim();
            loadData(taiKhoanBLL.getTaiKhoanByNameSearch(keyword));
        });

        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                topNav.getFindFor().setSelectedIndex(0);
                textSearch.setText(""); // Clear the search keyword
                loadData(taiKhoanBLL.getAllTaiKhoan()); // Reload all data
            }
        });
    }

    private void loadData(List<TaiKhoanDTO> taiKhoanList) {
        // Fetch all account data from BLL
        String[] columnNames = { "Mã Nhân Viên", "Tên Đăng Nhập", "Mật Khẩu", "Mã Quyền" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Add data to the table model
        for (TaiKhoanDTO tk : taiKhoanList) {
            Object[] rowData = {
                tk.getMaNV(),
                tk.getTenDangNhap(),
                tk.getMatKhau(),
                tk.getMaQuyen()
            };
            model.addRow(rowData);
        }

        // Set the new model to the table
        tbl.setModel(model);
    }
    

    private void chucNang() {
        JButton[] btn = topNav.getBtn();

        // Add account
        btn[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(TaiKhoanGUI.this);
                TaiKhoanDialog dialog = new TaiKhoanDialog(parentFrame, null, "Thêm Tài Khoản");
                dialog.setVisible(true);

                if (dialog.isSaved()) {
                    try {
                        TaiKhoanDTO newTaiKhoan = dialog.getTaiKhoanData();
                        if (taiKhoanBLL.addTaiKhoan(newTaiKhoan)) {
                            JOptionPane.showMessageDialog(null, "Thêm tài khoản thành công!");
                            loadData(taiKhoanBLL.getAllTaiKhoan());
                        } else {
                            JOptionPane.showMessageDialog(null, "Thêm tài khoản thất bại!");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Lỗi khi thêm tài khoản: " + ex.getMessage());
                    }
                }
            }
        });

        // Edit account
        btn[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một tài khoản để sửa!");
                    return;
                }

                int maNV = (int) tbl.getValueAt(selectedRow, 0);
                String tenDangNhap = (String) tbl.getValueAt(selectedRow, 1);
                String matKhau = (String) tbl.getValueAt(selectedRow, 2);
                int maQuyen = (int) tbl.getValueAt(selectedRow, 3);

                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(TaiKhoanGUI.this);
                TaiKhoanDialog dialog = new TaiKhoanDialog(parentFrame,
                        new TaiKhoanDTO(maNV, tenDangNhap, matKhau, maQuyen), "Chỉnh Sửa Tài Khoản");
                dialog.setVisible(true);

                if (dialog.isSaved()) {
                    TaiKhoanDTO updatedTaiKhoan = dialog.getTaiKhoanData();
                    if (taiKhoanBLL.updateTaiKhoan(updatedTaiKhoan)) {
                        JOptionPane.showMessageDialog(null, "Cập nhật tài khoản thành công!");
                        loadData(taiKhoanBLL.getAllTaiKhoan());
                    } else {
                        JOptionPane.showMessageDialog(null, "Cập nhật thất bại!");
                    }
                }
            }
        });

        // Delete account
        btn[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một tài khoản để xóa!");
                    return;
                }

                int maNV = (int) tbl.getValueAt(selectedRow, 0);
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa tài khoản này?",
                        "Xóa Tài Khoản", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (taiKhoanBLL.deleteTaiKhoan(maNV)) {
                        JOptionPane.showMessageDialog(null, "Xóa tài khoản thành công!");
                        loadData(taiKhoanBLL.getAllTaiKhoan());
                    } else {
                        JOptionPane.showMessageDialog(null, "Xóa tài khoản thất bại!");
                    }
                }
            }
        });

        // View details of account
        btn[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một tài khoản để xem chi tiết!");
                    return;
                }

                int maNV = (int) tbl.getValueAt(selectedRow, 0);
                String tenDangNhap = (String) tbl.getValueAt(selectedRow, 1);
                String matKhau = (String) tbl.getValueAt(selectedRow, 2);
                int maQuyen = (int) tbl.getValueAt(selectedRow, 3);

                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(TaiKhoanGUI.this);
                TaiKhoanDialog dialog = new TaiKhoanDialog(parentFrame,
                        new TaiKhoanDTO(maNV, tenDangNhap, matKhau, maQuyen), "Xem chi tiết");
                dialog.setVisible(true);
            }
        });

        btn[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ExportExcelUtility.saveTableToExcel(tbl, "Tài Khoản");
            }
        });
    }
}
