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
import GUI.Panel.TopNav;

public class TaiKhoan extends JPanel {
    TopNav topNav;
    JPanel pnlBot;
    JTable tbl;
    TaiKhoanBLL taiKhoanBLL;

    public TaiKhoan() {
        taiKhoanBLL = new TaiKhoanBLL();
        initComponent();
        chucNang();
        loadData();
    }

    private void initComponent() {
        String[] itemFindFor = { "Tất Cả", "Theo tên", "Theo SDT" };
        topNav = new TopNav("Tài Khoản", "account",itemFindFor);

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

        // Thiết lập layout cho TaiKhoan
        this.setLayout(new BorderLayout());
        this.add(topNav, BorderLayout.NORTH);
        this.add(pnlBot, BorderLayout.CENTER);
    }

    private void chucNang() {
        JButton[] btn = topNav.getBtn();

        // Thêm tài khoản
        btn[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField tfMaNV = new JTextField();
                JTextField tfTenDangNhap = new JTextField();
                JTextField tfMatKhau = new JTextField();
                JTextField tfMaQuyen = new JTextField();

                Object[] message = {
                    "Mã NV:", tfMaNV,
                    "Tên Đăng Nhập:", tfTenDangNhap,
                    "Mật Khẩu:", tfMatKhau,
                    "Mã Quyền:", tfMaQuyen
                };

                int option = JOptionPane.showConfirmDialog(null, message, "Thêm Tài Khoản", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    try {
                        int maNV = Integer.parseInt(tfMaNV.getText());
                        String tenDangNhap = tfTenDangNhap.getText();
                        String matKhau = tfMatKhau.getText();
                        int maQuyen = Integer.parseInt(tfMaQuyen.getText());

                        TaiKhoanDTO tk = new TaiKhoanDTO(maNV, tenDangNhap, matKhau, maQuyen);
                        if (taiKhoanBLL.addTaiKhoan(tk)) {
                            JOptionPane.showMessageDialog(null, "Thêm tài khoản thành công!");
                            loadData();
                        } else {
                            JOptionPane.showMessageDialog(null, "Thêm tài khoản thất bại!");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Mã NV và Mã Quyền phải là số!");
                    }
                }
            }
        });

        // Sửa tài khoản
        btn[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một tài khoản để sửa!");
                    return;
                }

                // Lấy dữ liệu tài khoản từ bảng
                int maNV = (int) tbl.getValueAt(selectedRow, 0);
                String tenDangNhap = (String) tbl.getValueAt(selectedRow, 1);
                String matKhau = (String) tbl.getValueAt(selectedRow, 2);
                int maQuyen = (int) tbl.getValueAt(selectedRow, 3);

                // Hiển thị hộp thoại sửa
                JTextField tfTenDangNhap = new JTextField(tenDangNhap);
                JTextField tfMatKhau = new JTextField(matKhau);
                JTextField tfMaQuyen = new JTextField(String.valueOf(maQuyen));

                Object[] message = {
                    "Tên Đăng Nhập:", tfTenDangNhap,
                    "Mật Khẩu:", tfMatKhau,
                    "Mã Quyền:", tfMaQuyen
                };

                int option = JOptionPane.showConfirmDialog(null, message, "Sửa Tài Khoản", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    TaiKhoanDTO tk = new TaiKhoanDTO(maNV, tfTenDangNhap.getText(), tfMatKhau.getText(), Integer.parseInt(tfMaQuyen.getText()));
                    if (taiKhoanBLL.updateTaiKhoan(tk)) {
                        JOptionPane.showMessageDialog(null, "Cập nhật thông tin tài khoản thành công!");
                        loadData();
                    } else {
                        JOptionPane.showMessageDialog(null, "Cập nhật thông tin thất bại!");
                    }
                }
            }
        });

        // Xóa tài khoản
        btn[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một tài khoản để xóa!");
                    return;
                }

                int maNV = (int) tbl.getValueAt(selectedRow, 0);

                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa tài khoản này?", "Xóa Tài Khoản", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (taiKhoanBLL.deleteTaiKhoan(maNV)) {
                        JOptionPane.showMessageDialog(null, "Xóa tài khoản thành công!");
                        loadData();
                    } else {
                        JOptionPane.showMessageDialog(null, "Xóa tài khoản thất bại!");
                    }
                }
            }
        });
    }

    private void loadData() {
        // Lấy dữ liệu từ BLL
        List<TaiKhoanDTO> taiKhoanList = taiKhoanBLL.getAllTaiKhoan();
        String[] columnNames = { "Mã NV", "Tên Đăng Nhập", "Mật Khẩu", "Mã Quyền" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Thêm dữ liệu vào bảng
        for (TaiKhoanDTO tk : taiKhoanList) {
            Object[] rowData = { tk.getMaNV(), tk.getTenDangNhap(), tk.getMatKhau(), tk.getMaQuyen() };
            model.addRow(rowData);
        }

        tbl.setModel(model);
    }
}
