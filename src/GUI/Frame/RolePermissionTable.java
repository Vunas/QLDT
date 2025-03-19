// package GUI.Frame;

// import javax.swing.*;
// import java.awt.*;

// public class RolePermissionTable {
//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(() -> {
//             JFrame frame = new JFrame("Bảng Phân Quyền");
//             frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//             frame.setSize(600, 400);
            
//             // Tạo dữ liệu cho bảng
//             String[] columnNames = {"Tính năng", "Read", "Write", "Delete", "Execute"};
//             Object[][] data = {
//                 {"Quản lý người dùng", false, false, false, false},
//                 {"Quản lý dự án", false, false, false, false},
//                 {"Quản lý tài chính", false, false, false, false}
//             };

//             // Tạo bảng
//             JTable table = new JTable(data, columnNames) {
//                 @Override
//                 public Class<?> getColumnClass(int column) {
//                     return column == 0 ? String.class : Boolean.class;
//                 }
//             };

//             // Thiết lập bảng cuộn
//             JScrollPane scrollPane = new JScrollPane(table);
//             frame.add(scrollPane, BorderLayout.CENTER);

//             // Hiển thị giao diện
//             frame.setVisible(true);
//         });
//     }
// }