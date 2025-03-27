package GUI.Panel.InputType;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class InputText extends JPanel {
    JLabel lblTitle, lblError;
    JTextField txtForm;

    public InputText(String title) {  
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(10, 10, 10, 10)); // Khoảng cách padding

        // Tiêu đề
        lblTitle = new JLabel(title);
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT); // Căn trái tiêu đề
        lblTitle.setFont(lblTitle.getFont().deriveFont(14f)); // Tùy chỉnh kích cỡ font chữ

        // Trường nhập
        txtForm = new JTextField();
        txtForm.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40)); // Chiều rộng linh hoạt, chiều cao cố định
        txtForm.setAlignmentX(Component.LEFT_ALIGNMENT); // Căn trái trường nhập

        // Nhãn lỗi
        lblError = new JLabel();
        lblError.setAlignmentX(Component.LEFT_ALIGNMENT); // Căn trái nhãn lỗi
        lblError.setFont(lblError.getFont().deriveFont(12f)); // Font chữ nhỏ hơn
        lblError.setForeground(java.awt.Color.RED); // Màu lỗi đỏ

        // Thêm các thành phần với khoảng cách
        this.add(lblTitle);
        this.add(Box.createRigidArea(new Dimension(0, 5))); // Khoảng cách giữa các thành phần
        this.add(txtForm);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(lblError);
    }

    public JLabel getLblTitle() {
        return lblTitle;
    }

    public void setLblTitle(JLabel lblTitle) {
        this.lblTitle = lblTitle;
    }

    public JLabel getLblError() {
        return lblError;
    }

    public void setLblError(String errorMessage) {
        lblError.setText(errorMessage);
    }

    public JTextField getTxtForm() {
        return txtForm;
    }
    
     public void setEditable(boolean value) {
        txtForm.setEditable(value);
    }
    public void setTxtForm(JTextField txtForm) {
        this.txtForm = txtForm;
    }

    public String getText() {
        return txtForm.getText();
    }

    public void setText(String text) {
        this.txtForm.setText(text);
    }

    public void setText(int maphieunhap) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
