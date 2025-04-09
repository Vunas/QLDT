package GUI.Panel;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import GUI.Panel.InputType.InputImage;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;

public class itemTaskbar extends JPanel implements MouseListener {

    Color FontColor = new Color(96, 125, 139);
    Color ColorBlack = new Color(26, 26, 26);
    Color DefaultColor = new Color(255, 255, 255);
    JLabel lblIcon, pnlContent, pnlSoLuong, pnlContent1;
    JPanel right;
    JLabel img;
    public boolean isSelected;

    public itemTaskbar(String linkIcon, String content) {
        this.setLayout(new FlowLayout(1, 10, 7));
        this.setPreferredSize(new Dimension(225, 45));
        this.setBackground(DefaultColor);
        this.putClientProperty(FlatClientProperties.STYLE, "arc: 15");
        this.addMouseListener(this);
        lblIcon = new JLabel();
        lblIcon.setBorder(new EmptyBorder(0, 10, 0, 0));
        lblIcon.setPreferredSize(new Dimension(45, 30));
        lblIcon.setIcon(new FlatSVGIcon("./icon/" + linkIcon));
        this.add(lblIcon);

        pnlContent = new JLabel(content);
        pnlContent.setPreferredSize(new Dimension(155, 30));
        pnlContent.putClientProperty("FlatLaf.style", "font: 145% $medium.font");
        pnlContent.setForeground(ColorBlack);
        this.add(pnlContent);
    }

    public itemTaskbar(String linkIcon, String content1, String content2) {
        // Sử dụng BorderLayout để dễ dàng canh giữa
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(250, 150)); // Kích thước lớn hơn
        this.setBackground(DefaultColor);
        this.putClientProperty(FlatClientProperties.STYLE, "arc: 15");
        this.addMouseListener(this);

        // Tạo phần chứa icon, đặt ở trung tâm phía trên
        lblIcon = new JLabel();
        lblIcon.setHorizontalAlignment(SwingConstants.CENTER); // Canh giữa icon
        lblIcon.setIcon(new FlatSVGIcon("./resources/icon/" + linkIcon + ".svg", 4.0f)); // Phóng to icon
        this.add(lblIcon, BorderLayout.NORTH);

        // Tạo phần chứa nội dung text, đặt ở trung tâm phía dưới
        JPanel textPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        textPanel.setBackground(DefaultColor);
        textPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Dòng chữ đầu tiên (content1)
        JLabel lblContent1 = new JLabel(content1);
        lblContent1.setHorizontalAlignment(SwingConstants.CENTER); // Canh giữa text
        lblContent1.putClientProperty("FlatLaf.style", "font: 300% $semibold.font"); // Phóng to chữ
        lblContent1.setForeground(FontColor);

        // Dòng chữ thứ hai (content2)
        JLabel lblContent2 = new JLabel(content2);
        lblContent2.setHorizontalAlignment(SwingConstants.CENTER); // Canh giữa text
        lblContent2.putClientProperty("FlatLaf.style", "font: 200% $medium.font");
        lblContent2.setForeground(FontColor);

        // Thêm các label vào textPanel
        // textPanel.add(lblContent1);
        // textPanel.add(lblContent2);

        // Thêm textPanel vào panel chính
        this.add(lblContent1, BorderLayout.CENTER);
    }

    public itemTaskbar(String linkImg, String tenSP, int soLuong) {

        this.setLayout(new BorderLayout(0, 0));
        this.setPreferredSize(new Dimension(380, 60));
        this.setBackground(Color.white);

        img = new JLabel("");
        img.setIcon(InputImage.resizeImage(new ImageIcon("src/resources/img/" + linkImg), 38));
        this.add(img, BorderLayout.WEST);

        right = new JPanel();
        right.setLayout(new FlowLayout(0, 0, 0));
        right.setBorder(new EmptyBorder(10, 10, 0, 0));
        right.setOpaque(false);
        this.add(right, BorderLayout.CENTER);

        pnlContent = new JLabel(tenSP);
        pnlContent.putClientProperty("FlatLaf.style", "font: 120% $semibold.font");
        pnlContent.setForeground(Color.black);
        right.add(pnlContent);

        pnlSoLuong = new JLabel("Số lượng: " + soLuong);
        pnlSoLuong.setPreferredSize(new Dimension(350, 20));
        pnlSoLuong.putClientProperty("FlatLaf.style", "font: 100% $medium.font");
        pnlSoLuong.setForeground(Color.gray);
        right.add(pnlSoLuong);

    }

    public itemTaskbar(String linkIcon, String content, String content2, int n) {
        this.setLayout(new BorderLayout(0, 0));
        this.setBackground(DefaultColor);
        this.addMouseListener(this);
        lblIcon = new JLabel();
        lblIcon.setPreferredSize(new Dimension(100, 100));
        lblIcon.setBorder(new EmptyBorder(0, 20, 0, 0));

        lblIcon.setIcon(new FlatSVGIcon("src/resources/icon/" + linkIcon));
        this.add(lblIcon, BorderLayout.WEST);

        JPanel center = new JPanel();
        center.setLayout(new FlowLayout(0, 10, 0));
        center.setBorder(new EmptyBorder(20, 0, 0, 0));
        center.setOpaque(false);
        this.add(center);

        pnlContent = new JLabel(content);
        pnlContent.setPreferredSize(new Dimension(250, 30));
        pnlContent.putClientProperty("FlatLaf.style", "font: 300% $semibold.font");
        pnlContent.setForeground(FontColor);
        center.add(pnlContent);

        pnlContent1 = new JLabel(content2);
        pnlContent1.setPreferredSize(new Dimension(250, 30));
        pnlContent1.putClientProperty("FlatLaf.style", "font: 150% $medium.font");
        pnlContent1.setForeground(FontColor);
        center.add(pnlContent1);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated
        // from
        // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated
        // from
        // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated
        // from
        // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (!isSelected) {
            setBackground(new Color(235, 237, 240));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (!isSelected) {
            setBackground(new Color(255, 255, 255));
        }
    }
}
