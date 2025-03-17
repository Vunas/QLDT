package GUI.DiaLog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import DTO.NhaCungCapDTO;
import GUI.Panel.InputType.InputText;

public class NhaCungCapDiaLog extends JDialog {
    private InputText tfMaNCC;
    private InputText tfTen;
    private InputText tfDiaChi;
    private InputText tfSdt;
    private boolean isSaved = false;

    public NhaCungCapDiaLog(JFrame owner, NhaCungCapDTO nhaCungCap, String titleString) {
        super(owner, titleString, true);
        initComponents(nhaCungCap, titleString);
        setLocationRelativeTo(owner); // Center the dialog relative to the parent window
    }

    private void initComponents(NhaCungCapDTO nhaCungCap, String titleString) {
        setSize(400, 450); // Reasonable size
        setLayout(new BorderLayout(10, 10)); // Add spacing between sections

        JLabel lblContent = new JLabel(titleString);
        lblContent.setFont(new Font(getName(), Font.BOLD, 20));
        JPanel pnlContent = new JPanel();
        pnlContent.add(lblContent, CENTER_ALIGNMENT);

        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
        pnlMain.setBorder(new EmptyBorder(5, 10, 5, 10));
        pnlMain.setPreferredSize(new Dimension(400, 250));

        tfTen = new InputText("Tên Nhà Cung Cấp");
        tfDiaChi = new InputText("Địa Chỉ");
        tfSdt = new InputText("Số Điện Thoại");

        if (nhaCungCap != null) {
            tfMaNCC = new InputText("Mã Nhà Cung Cấp");
            tfMaNCC.setText(String.valueOf(nhaCungCap.getMaNhaCungCap()));
            tfTen.setText(nhaCungCap.getTen());
            tfDiaChi.setText(nhaCungCap.getDiaChi());
            tfSdt.setText(nhaCungCap.getsDT());

            // Set fields to read-only if "Xem chi tiết"
            if (titleString.equals("Xem chi tiết")) {
                pnlMain.add(tfMaNCC);
                tfMaNCC.getTxtForm().setEditable(false);
                tfTen.getTxtForm().setEditable(false);
                tfDiaChi.getTxtForm().setEditable(false);
                tfSdt.getTxtForm().setEditable(false);
            }
        }

        pnlMain.add(tfTen);
        pnlMain.add(tfDiaChi);
        pnlMain.add(tfSdt);

        // Buttons
        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnSave = new JButton("Lưu");
        btnSave.setPreferredSize(new Dimension(90, 60));
        btnSave.setFont(new Font(getName(), Font.PLAIN, 20));

        if (titleString.equals("Xem chi tiết")) {
            btnSave.setVisible(false); // Hide the Save button
        } else {
            btnSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    isSaved = true;
                    validateNhaCungCap();
                    if (isSaved) {
                        if (nhaCungCap != null) {
                            nhaCungCap.setTen(tfTen.getText());
                            nhaCungCap.setDiaChi(tfDiaChi.getText());
                            nhaCungCap.setsDT(tfSdt.getText());
                        }
                        dispose();
                    }
                }
            });
            pnlButtons.add(btnSave);
        }

        // Add components to dialog
        add(pnlContent, BorderLayout.NORTH);
        add(pnlMain, BorderLayout.CENTER);
        add(pnlButtons, BorderLayout.SOUTH);

        // Configure the dialog
        setResizable(false); // Disable resizing
    }

    public void validateNhaCungCap() {
        if (tfTen.getText() == null || tfTen.getText().isEmpty()) {
            isSaved = false;
            tfTen.setLblError("Tên nhà cung cấp không được để trống.");
        } else {
            tfTen.setLblError("");
        }
        if (!tfSdt.getText().matches("\\d{10}")) {
            isSaved = false; // Check if the phone number has exactly 10 digits
            tfSdt.setLblError("Số điện thoại không hợp lệ. Phải có 10 chữ số.");
        } else {
            tfSdt.setLblError("");
        }
    }

    public boolean isSaved() {
        return isSaved;
    }

    public NhaCungCapDTO getNhaCungCapData(int maNCC) {
        return new NhaCungCapDTO(maNCC, tfTen.getText(), tfDiaChi.getText(), tfSdt.getText());
    }
}
