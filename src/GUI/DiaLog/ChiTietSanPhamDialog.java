package GUI.DiaLog;

import DTO.SanPhamDTO;

import javax.swing.*;
import java.awt.*;

public class ChiTietSanPhamDialog extends JDialog {
    private SanPhamDTO sp;

    public ChiTietSanPhamDialog(JFrame owner, SanPhamDTO sp) {
        super(owner, "Chi tiết sản phẩm", true);
        this.sp = sp;
        initComponent();
        this.setLocationRelativeTo(owner);
    }

    private void initComponent() {
        this.setSize(400, 300);
        this.setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Thông tin sản phẩm", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(new Color(22, 122, 198));
        this.add(title, BorderLayout.NORTH);

        JPanel content = new JPanel(new GridLayout(7, 2, 10, 10));
        content.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        content.add(new JLabel("Mã sản phẩm:"));
        content.add(new JLabel(String.valueOf(sp.getMaSP())));

        content.add(new JLabel("Tên sản phẩm:"));
        content.add(new JLabel(sp.getTenSP()));

        content.add(new JLabel("RAM:"));
        content.add(new JLabel(sp.getRam() + " GB"));

        content.add(new JLabel("ROM:"));
        content.add(new JLabel(sp.getRom() + " GB"));

        content.add(new JLabel("Chip:"));
        content.add(new JLabel(sp.getChip()));

        content.add(new JLabel("Màu:"));
        content.add(new JLabel(sp.getMauSac()));

        content.add(new JLabel("Thời gian BH:"));
        content.add(new JLabel(sp.getThoiGianBaoHanh() + " tháng"));

        this.add(content, BorderLayout.CENTER);

        JButton btnClose = new JButton("Đóng");
        btnClose.addActionListener(e -> dispose());

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footer.add(btnClose);
        this.add(footer, BorderLayout.SOUTH);
    }
}
