package GUI.pages;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import BLL.BUS.QuyenBLL;
import DTO.QuyenDTO;
import GUI.DiaLog.QuyenDiaLog;
import GUI.Panel.TopNav;
import util.ExportExcelUtility;

public class QuyenGUI extends JPanel {
    TopNav topNav;
    JPanel pnlBot;
    JTable tbl;
    QuyenBLL quyenBLL;

    public QuyenGUI(TopNav topNav) {
        quyenBLL = new QuyenBLL();
        initComponent(topNav);
        addSearchFunctionality();
        loadData(quyenBLL.getAllQuyen());
        chucNang();
    }

    private void initComponent(TopNav topNav) {
        this.topNav= topNav;
        String[] itemFindFor = { "Tất Cả" };
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

        // Set layout for QuyenGUI
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
                loadData(quyenBLL.getQuyenByNameSearch(keyword));
            }
        });

        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                topNav.getFindFor().setSelectedIndex(0);
                textSearch.setText(""); // Clear the search keyword
                loadData(quyenBLL.getAllQuyen()); // Reload all data
            }
        });
    }

    private void loadData(List<QuyenDTO> quyenList) {
        // Fetch all permissions data from BLL
        String[] columnNames = { "Mã Quyền", "Tên Quyền" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Add data to the table model
        for (QuyenDTO quyen : quyenList) {
            Object[] rowData = {
                    quyen.getMaQuyen(),
                    quyen.getTenQuyen()
            };
            model.addRow(rowData);
        }

        // Set the new model to the table
        tbl.setModel(model);
    }

    private void chucNang() {
        JButton[] btn = topNav.getBtn();

        // Add permission
        btn[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame pwner = (JFrame) SwingUtilities.getWindowAncestor(QuyenGUI.this);
                QuyenDiaLog dialog = new QuyenDiaLog(pwner, null, "Thêm quyền");
                dialog.setVisible(true);
                if (dialog.isSaved()) {
                    try {
                        // Assign a new unique ID
                        QuyenDTO quyenDTO = dialog.getDataQuyenDTO();
                        if (quyenBLL.addQuyen(quyenDTO)) {
                            JOptionPane.showMessageDialog(null, "Thêm  thành công!");
                            loadData(quyenBLL.getAllQuyen());
                        } else {
                            JOptionPane.showMessageDialog(null, "Thêm  thất bại!");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Lỗi khi thêm : " + ex.getMessage());
                    }
                }
            }
        });

        // Edit permission
        // Edit permission
        btn[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một quyền để sửa!");
                    return;
                }

                int maQuyen = (int) tbl.getValueAt(selectedRow, 0);
                QuyenDTO quyen = quyenBLL.getQuyenById(maQuyen); // Lấy dữ liệu quyền từ BLL

                JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(QuyenGUI.this);
                QuyenDiaLog dialog = new QuyenDiaLog(owner, quyen, "Chỉnh sửa quyền");
                dialog.setVisible(true);

                if (dialog.isSaved()) {
                    try {
                        QuyenDTO updatedQuyen = dialog.getDataQuyenDTO();
                        updatedQuyen.setMaQuyen(maQuyen);
                        System.out.println((updatedQuyen.getDanhSachChucNang()+ updatedQuyen.getTenQuyen()));
                        if (quyenBLL.updateQuyen(updatedQuyen)) {
                            JOptionPane.showMessageDialog(null, "Chỉnh sửa thành công!");
                            loadData(quyenBLL.getAllQuyen()); // Tải lại dữ liệu
                        } else {
                            JOptionPane.showMessageDialog(null, "Chỉnh sửa thất bại!");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Lỗi khi sửa: " + ex.getMessage());
                    }
                }
            }
        });

        // Delete permission
        // Delete permission
        btn[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một quyền để xóa!");
                    return;
                }

                int maQuyen = (int) tbl.getValueAt(selectedRow, 0);
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa quyền này?",
                        "Xóa Quyền", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (quyenBLL.deleteQuyen(maQuyen)) {
                        JOptionPane.showMessageDialog(null, "Xóa quyền thành công!");
                        loadData(quyenBLL.getAllQuyen()); // Tải lại dữ liệu
                    } else {
                        JOptionPane.showMessageDialog(null, "Xóa quyền thất bại!");
                    }
                }
            }
        });

        // View details
        // View details
        btn[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một quyền để xem chi tiết!");
                    return;
                }

                int maQuyen = (int) tbl.getValueAt(selectedRow, 0);
                QuyenDTO quyen = quyenBLL.getQuyenById(maQuyen); // Lấy dữ liệu quyền từ BLL

                JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(QuyenGUI.this);
                QuyenDiaLog dialog = new QuyenDiaLog(owner, quyen, "Xem chi tiết quyền");
                dialog.setVisible(true);
            }
        });

        btn[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ExportExcelUtility.saveTableToExcel(tbl, "Quyền");
            }
        });
    }
}
