package GUI.DiaLog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import DTO.KhachHangDTO;
import GUI.Panel.InputType.InputText;

public class KhachHangDiaLog extends JDialog {
    private InputText tfMaKH;
    private InputText tfHoTen;
    private InputText tfDiaChi;
    private InputText tfSdt;
    private boolean isSaved = false;

    public KhachHangDiaLog(JFrame owner, KhachHangDTO khachHang, String titleString) {
        super(owner, titleString, true);
        initComponents(khachHang, titleString);
        setLocationRelativeTo(owner); // Căn giữa so với cửa sổ cha
    }

    private void initComponents(KhachHangDTO khachHang, String titleString) {
        setSize(400, 450); // Kích thước hợp lý
        setLayout(new BorderLayout(10, 10)); // Tạo khoảng cách giữa các phần

        JLabel lblContent = new JLabel(titleString);
        lblContent.setFont(new Font(getName(), Font.BOLD, 20));
        JPanel pnlContent = new JPanel();
        pnlContent.add(lblContent, CENTER_ALIGNMENT);

        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
        pnlMain.setBorder(new EmptyBorder(5, 10, 5, 10));
        pnlMain.setPreferredSize(new Dimension(400, 250));

        tfHoTen = new InputText("Họ tên");
        tfDiaChi = new InputText("Địa Chỉ");
        tfSdt = new InputText("Số Điện Thoại");

        if (khachHang != null) {
            tfMaKH = new InputText("Mã Khách Hàng");
            tfMaKH.setText(String.valueOf(khachHang.getMaKH()));
            tfHoTen.setText(khachHang.getHoTen());
            tfDiaChi.setText(khachHang.getDiaChi());
            tfSdt.setText(khachHang.getSdt());

            // Set fields to read-only if "Xem chi tiết"
            if (titleString.equals("Xem chi tiết")) {
                pnlMain.add(tfMaKH);
                tfMaKH.getTxtForm().setEditable(false);
                tfHoTen.getTxtForm().setEditable(false);
                tfDiaChi.getTxtForm().setEditable(false);
                tfSdt.getTxtForm().setEditable(false);
            }
        }

        pnlMain.add(tfHoTen);
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
                    validateKhachHang();
                    if (isSaved) {
                        if (khachHang != null) {
                            khachHang.setHoTen(tfHoTen.getText());
                            khachHang.setDiaChi(tfDiaChi.getText());
                            khachHang.setSdt(tfSdt.getText());
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
        setResizable(false); // Không cho phép thay đổi kích thước
    }

    public void validateKhachHang() {
        if (tfHoTen.getText() == null || tfHoTen.getText().isEmpty()) {
            isSaved = false;
            tfHoTen.setLblError("Họ tên không được để trống.");
        } else
            tfHoTen.setLblError("");
        if (!tfSdt.getText().matches("\\d{10}")) {
            isSaved = false; // Kiểm tra số điện thoại có đúng 10 số
            tfSdt.setLblError("Số điện thoại không hợp lệ. Phải có 10 chữ số.");
        } else
            tfSdt.setLblError("");
    }

    public boolean isSaved() {
        return isSaved;
    }

    public KhachHangDTO getKhachHangData(int maKH) {
        return new KhachHangDTO(maKH, tfHoTen.getText(), tfDiaChi.getText(), tfSdt.getText(),1);
    }
}
