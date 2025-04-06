package GUI.DiaLog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import DTO.QuyenDTO;
import GUI.Panel.InputType.InputText;

public class QuyenDiaLog extends JDialog {
    private InputText tf;
    private JTable tbl;
    private String danhSachChucNang;
    private boolean isSaved;

    public QuyenDiaLog(JFrame owner, QuyenDTO quyen, String titleString) {
        super(owner, titleString, true);
        initComponents(quyen, titleString);
        setLocationRelativeTo(owner); // Hiển thị dialog ở giữa cửa sổ cha
    }

    private void initComponents(QuyenDTO quyen, String titleString) {
        setSize(800, 500); // Kích thước dialog
        setLayout(new BorderLayout(10, 10)); // Khoảng cách giữa các phần

        // Tiêu đề
        JLabel lblContent = new JLabel(titleString);
        lblContent.setFont(new Font(getName(), Font.BOLD, 20));
        JPanel pnlContent = new JPanel();
        pnlContent.add(lblContent);

        // Khu vực nhập và bảng
        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
        pnlMain.setBorder(new EmptyBorder(5, 10, 5, 10));
        pnlMain.setPreferredSize(new Dimension(800, 300));

        tf = new InputText("Nhập tên quyền");

        String[] chucNang = { "Tên Chức Năng", "Đọc", "Thêm", "Sửa", "Xóa" };
        Object[][] data = {
                { "Sản Phẩm", false, false, false, false },
                // { "Kho Hàng", false, false, false, false },
                { "Phiếu Nhập", false, false, false, false },
                { "Phiếu Xuất", false, false, false, false },
                { "Khách Hàng", false, false, false, false },
                { "Nhà Cung Cấp", false, false, false, false },
                { "Nhân Viên", false, false, false, false },
                { "Tài Khoản", false, false, false, false },
                { "Thống kê", false, false, false, false },
                { "Phân Quyền", false, false, false, false },
                { "Thống kê", false,false, false, false}
        };

        tbl = new JTable(data, chucNang) {
            @Override
            public Class<?> getColumnClass(int column) {
                return column == 0 ? String.class : Boolean.class;
            }
        };
        tbl.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbl.setRowHeight(35);
        tbl.setFocusable(false);
        tbl.setAutoCreateRowSorter(true);

        JTableHeader header = tbl.getTableHeader();
        header.setPreferredSize(new Dimension(0, 40));
        header.setBackground(new Color(100, 149, 237)); // Màu xanh Cornflower Blue
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(tbl);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        pnlMain.add(tf);
        pnlMain.add(scrollPane);

        if (quyen != null) {
            tf.setText(quyen.getTenQuyen());
            updateTableWithPermissions(quyen.getDanhSachChucNang());
        }

        // Các nút điều khiển
        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnSave = new JButton("Lưu");
        btnSave.setPreferredSize(new Dimension(90, 60));
        btnSave.setFont(new Font(getName(), Font.PLAIN, 20));

        if (titleString.equals("Xem chi tiết quyền")) {
            btnSave.setVisible(false); // Ẩn nút lưu
            tf.getTxtForm().setEditable(false); // Vô hiệu hóa trường nhập
            tbl.setEnabled(false); // Vô hiệu hóa bảng
        } else {
            btnSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    isSaved = true;
                    validateQuyen();
                    if (isSaved) {
                        setChucNang();
                        dispose();
                    }
                }
            });
            pnlButtons.add(btnSave);
        }

        add(pnlContent, BorderLayout.NORTH);
        add(pnlMain, BorderLayout.CENTER);
        add(pnlButtons, BorderLayout.SOUTH);

        setResizable(false); // Khóa kích thước dialog
    }

    public void setChucNang() {
        StringBuilder stringBuilder = new StringBuilder("");
        for (int row = 0; row < tbl.getRowCount(); row++) {
            if (Boolean.TRUE.equals(tbl.getValueAt(row, 1))) {
                stringBuilder.append("r");
            }
            if (Boolean.TRUE.equals(tbl.getValueAt(row, 2))) {
                stringBuilder.append("c");
            }
            if (Boolean.TRUE.equals(tbl.getValueAt(row, 3))) {
                stringBuilder.append("f");
            }
            if (Boolean.TRUE.equals(tbl.getValueAt(row, 4))) {
                stringBuilder.append("d");
            }
            stringBuilder.append(" ").append("/");
        }
        danhSachChucNang = stringBuilder.toString();
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void validateQuyen() {
        if (tf.getText() == null || tf.getText().isEmpty()) {
            isSaved = false;
            tf.setLblError("Tên quyền không được để trống.");
            tf.requestFocus();
        } else {
            tf.setLblError("");
        }
    }

    public QuyenDTO getDataQuyenDTO() {
        return new QuyenDTO(tf.getText(), danhSachChucNang,1);
    }

    private void updateTableWithPermissions(String danhSachCN) {
        String[] chucNangList = danhSachCN.split("/"); // Tách chuỗi quyền theo dấu "/"
        for (int row = 0; row < tbl.getRowCount(); row++) {
            if (row < chucNangList.length) {
                String permissions = chucNangList[row];
                tbl.setValueAt(permissions.contains("r"), row, 1); // Quyền "Đọc"
                tbl.setValueAt(permissions.contains("c"), row, 2); // Quyền "Thêm"
                tbl.setValueAt(permissions.contains("f"), row, 3); // Quyền "Sửa"
                tbl.setValueAt(permissions.contains("d"), row, 4); // Quyền "Xóa"
            }
        }
    }

}
