package GUI.DiaLog;

import DTO.PhieuSuaChuaDTO;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class ChiTietPhieuSuaChuaDialog extends JDialog {

    public ChiTietPhieuSuaChuaDialog(JFrame owner, PhieuSuaChuaDTO sc) {
        super(owner, "Chi tiết phiếu sửa chữa", true);
        initComponent(sc);
        this.setSize(500, 450);
        this.setLocationRelativeTo(owner);
        this.setVisible(true);
    }

    private void initComponent(PhieuSuaChuaDTO sc) {
        this.setLayout(new BorderLayout(10, 10));
        JLabel title = new JLabel("THÔNG TIN PHIẾU SỬA CHỮA", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(22, 122, 198));
        this.add(title, BorderLayout.NORTH);

        JPanel content = new JPanel(new GridLayout(0, 2, 10, 10));
        content.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        content.add(new JLabel("Mã sửa chữa:"));
        content.add(new JLabel(String.valueOf(sc.getMaPhieuSC())));

        content.add(new JLabel("Mã phiếu BH:"));
        content.add(new JLabel(sc.getMaPhieuBH()));

        content.add(new JLabel("Mã SP:"));
        content.add(new JLabel(String.valueOf(sc.getMaSanPham())));

        content.add(new JLabel("IMEI:"));
        content.add(new JLabel(sc.getMaIMEI()));

        content.add(new JLabel("Ngày nhận:"));
        content.add(new JLabel(sc.getNgayNhan().format(fmt)));

        content.add(new JLabel("Trạng thái:"));
        content.add(new JLabel(sc.getTrangThai()));

        content.add(new JLabel("Tình trạng lỗi:"));
        content.add(new JLabel(sc.getTinhTrang()));

        content.add(new JLabel("Xử lý:"));
        content.add(new JLabel(sc.getXuLy()));

        content.add(new JLabel("Ghi chú:"));
        content.add(new JLabel(sc.getGhiChu() != null ? sc.getGhiChu() : ""));

        this.add(content, BorderLayout.CENTER);

        JButton btnClose = new JButton("Đóng");
        btnClose.addActionListener(e -> dispose());
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footer.add(btnClose);
        this.add(footer, BorderLayout.SOUTH);
    }
}
