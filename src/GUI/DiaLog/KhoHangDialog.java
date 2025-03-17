package GUI.DiaLog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import DTO.KhoHangDTO;
import GUI.Panel.InputType.InputText;

public class KhoHangDialog extends JDialog {
    private InputText tfMaKho;
    private InputText tfTenKho;
    private InputText tfDiaChi;
    private boolean isSaved = false;

    public KhoHangDialog(JFrame owner, KhoHangDTO khoHang, String titleString) {
        super(owner, titleString, true);
        initComponents(khoHang, titleString);
        setLocationRelativeTo(owner); // Center the dialog relative to the parent frame
    }

    private void initComponents(KhoHangDTO khoHang, String titleString) {
        setSize(400, 400); // Set a reasonable size
        setLayout(new BorderLayout(10, 10)); // Add spacing between sections

        JLabel lblContent = new JLabel(titleString);
        lblContent.setFont(new Font(getName(), Font.BOLD, 20));
        JPanel pnlContent = new JPanel();
        pnlContent.add(lblContent, CENTER_ALIGNMENT);

        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
        pnlMain.setBorder(new EmptyBorder(5, 10, 5, 10));
        pnlMain.setPreferredSize(new Dimension(400, 250));

        tfTenKho = new InputText("Tên Kho");
        tfDiaChi = new InputText("Địa Chỉ");

        if (khoHang != null) {
            tfMaKho = new InputText("Mã Kho");
            tfMaKho.setText(String.valueOf(khoHang.getMaKho()));
            tfTenKho.setText(khoHang.getTenKho());
            tfDiaChi.setText(khoHang.getDiaChi());

            // Set fields to read-only if "Xem chi tiết"
            if (titleString.equals("Xem chi tiết")) {
                pnlMain.add(tfMaKho);
                tfMaKho.getTxtForm().setEditable(false);
                tfTenKho.getTxtForm().setEditable(false);
                tfDiaChi.getTxtForm().setEditable(false);

                JPanel pnlThongTinKH = new JPanel();
                pnlThongTinKH.add(new JTabbedPane());

                add(pnlThongTinKH,BorderLayout.EAST);
                setSize(800,400);
            }
        }

        pnlMain.add(tfTenKho);
        pnlMain.add(tfDiaChi);

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
                    validateKhoHang();
                    if (isSaved) {
                        if (khoHang != null) {
                            khoHang.setTenKho(tfTenKho.getText());
                            khoHang.setDiaChi(tfDiaChi.getText());
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

    public void validateKhoHang() {
        if (tfTenKho.getText() == null || tfTenKho.getText().isEmpty()) {
            isSaved = false;
            tfTenKho.setLblError("Tên kho không được để trống.");
        } else {
            tfTenKho.setLblError("");
        }

        if (tfDiaChi.getText() == null || tfDiaChi.getText().isEmpty()) {
            isSaved = false; // Check if the address is empty
            tfDiaChi.setLblError("Địa chỉ không được để trống.");
        } else {
            tfDiaChi.setLblError("");
        }
    }

    public boolean isSaved() {
        return isSaved;
    }

    public KhoHangDTO getKhoHangData(int maKho) {
        return new KhoHangDTO(maKho, tfTenKho.getText(), tfDiaChi.getText());
    }
}
