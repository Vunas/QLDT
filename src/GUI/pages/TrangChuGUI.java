package GUI.pages;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import java.awt.*;

public class TrangChuGUI extends JPanel {
    private JPanel pnlHeader, pnlContent, pnlLeft, pnlRight, pnlCustomer, pnlSecurity, pnlStore, pnlFooter;

    public TrangChuGUI() {
        initComponents();
    }

    // Phương thức tạo panel với ảnh ở giữa và chữ bên dưới
    private JPanel createPanel(String title, String imagePath, Color backgroundColor, String description,
            float size, int heightContent, int widthContent) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(backgroundColor);

        // Tiêu đề (đặt ở phía trên của panel)
        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));
        panel.add(lblTitle, BorderLayout.NORTH);

        // Nội dung chính (ảnh ở giữa, chữ ở dưới)
        JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 2));
        contentPanel.setBackground(backgroundColor);

        // Hình ảnh
        FlatSVGIcon svgIcon = new FlatSVGIcon(imagePath, size);
        JLabel lblImage = new JLabel(svgIcon);
        lblImage.setPreferredSize(new Dimension(svgIcon.getIconWidth(), svgIcon.getIconHeight()));
                                                                                                
        contentPanel.add(lblImage);

        // Mô tả
        JTextArea txtDescription = new JTextArea(description);
        txtDescription.setWrapStyleWord(true);
        txtDescription.setLineWrap(true);
        txtDescription.setEditable(false);
        txtDescription.setFont(new Font("Fira Code", Font.BOLD, 17)); // Phông chữ hiện đại, in đậm.
        txtDescription.setBackground(backgroundColor);
        txtDescription.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));

        // Không cần đặt preferredSize chiều cao cố định, để nó tự điều chỉnh
        txtDescription
                .setPreferredSize(new Dimension(widthContent,heightContent));
        contentPanel.add(txtDescription);

        // Thêm nội dung chính vào panel
        panel.add(contentPanel, BorderLayout.CENTER);

        return panel;
    }

    protected void initComponents() {
        setLayout(new BorderLayout());

        // Header Panel
        pnlHeader = new JPanel();
        pnlHeader.setPreferredSize(new Dimension(Integer.MAX_VALUE, 120)); // Tăng chiều cao header
        JLabel lblHeaderTitle = new JLabel("Phone Store - Trang Chủ");
        lblHeaderTitle.setFont(new Font("Poppins", Font.BOLD, 32));
        pnlHeader.setBorder(new EmptyBorder(25, 10, 10, 10));
        pnlHeader.setBackground(Color.WHITE);
        pnlHeader.add(lblHeaderTitle);

        // Content Panel
        pnlContent = new JPanel(new GridLayout(1, 2, 20, 20)); // Khoảng cách giữa các panel
        pnlContent.setBackground(Color.WHITE);

        // Panel bên trái (Cửa hàng)
        pnlLeft = new JPanel(new BorderLayout());
        pnlLeft.setBackground(Color.WHITE);
        pnlStore = createPanel(
                "Cửa Hàng",
                "./resources/img/store.svg",
                Color.WHITE,
                "Cửa hàng Phone Store cung cấp các sản phẩm công nghệ cao với giá cả hợp lý và dịch vụ chuyên nghiệp.",
                1f,80,550);
        pnlLeft.add(pnlStore, BorderLayout.CENTER);

        // Panel bên phải (Khách hàng & Bảo mật)
        pnlRight = new JPanel(new GridLayout(2, 1, 10, 10)); // Khoảng cách giữa các mục
        pnlRight.setBackground(Color.WHITE);
        pnlCustomer = createPanel(
                "Khách Hàng",
                "./resources/img/customer.svg",
                Color.WHITE,
                "Chúng tôi luôn tận tâm hỗ trợ khách hàng với các chương trình ưu đãi tuyệt vời.",
                0.4f,
                180,120);
        pnlSecurity = createPanel(
                "Bảo Mật",
                "./resources/img/security.svg",
                Color.WHITE,
                "Thông tin khách hàng được bảo vệ an toàn với công nghệ bảo mật hàng đầu.",
                0.4f,160,180);
        pnlRight.add(pnlCustomer);
        pnlRight.add(pnlSecurity);

        pnlContent.add(pnlLeft);
        pnlContent.add(pnlRight);

        // Footer Panel
        pnlFooter = new JPanel();
        pnlFooter.setBackground(Color.WHITE);
        pnlFooter.setPreferredSize(new Dimension(Integer.MAX_VALUE, 60)); // Chiều cao footer
        JLabel lblFooter = new JLabel("© 2025 Phone Store Inc. All Rights Reserved");
        lblFooter.setFont(new Font("Roboto", Font.ITALIC, 14)); // Font lớn hơn cho footer
        pnlFooter.add(lblFooter);

        add(pnlHeader, BorderLayout.NORTH);
        add(pnlContent, BorderLayout.CENTER);
        add(pnlFooter, BorderLayout.SOUTH);
    }
}