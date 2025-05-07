package GUI.DiaLog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import DTO.KhuyenMaiDTO;
import GUI.Panel.InputType.InputDate;
import GUI.Panel.InputType.InputText;

public class KhuyenMaiDialog extends JDialog {
    private InputText maKM, tenKM, soLuong, apDungChoHoaDonTu, giaTri;
    private JComboBox<String> hinhThuc;
    private JTextArea mota;
    private InputDate ngayBD, ngayKT;
    private boolean isSaved = false;

    public KhuyenMaiDialog(Frame owner, KhuyenMaiDTO khuyenMaiDTO, String title) {
        super(owner, title, true);
        initComponents(khuyenMaiDTO, title);
        setLocationRelativeTo(owner);
    }

    private void initComponents(KhuyenMaiDTO khuyenMaiDTO, String title) {
        setSize(550, 800);
        setLayout(new BorderLayout(10, 10));

        JLabel lbContent = new JLabel(title);
        lbContent.setFont(new Font(getName(), Font.BOLD, 20));
        lbContent.setOpaque(true);
        lbContent.setBackground(new Color(100, 149, 237));
        lbContent.setForeground(Color.WHITE);
        lbContent.setBorder(new EmptyBorder(10, 10, 10, 10));
        JPanel pnlContent = new JPanel();
        pnlContent.add(lbContent, BorderLayout.CENTER);

        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
        pnlMain.setBorder(new EmptyBorder(20, 30, 20, 30));
        pnlMain.setPreferredSize(new Dimension(400, 400));
        pnlMain.setBackground(Color.WHITE);

        maKM = new InputText("Mã khuyến Mãi");
        tenKM = new InputText("Tên Khuyến Mãi");
        soLuong = new InputText("Số Lượng");
        ngayBD = new InputDate("Ngày Bắt đầu");
        ngayKT = new InputDate("Ngày Kết Thúc");

        JPanel panelDate = new JPanel();
        panelDate.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelDate.setPreferredSize(new Dimension(400, 40));
        panelDate.add(ngayBD);
        panelDate.add(ngayKT);
        apDungChoHoaDonTu = new InputText("Áp Dụng Cho Hóa Đơn Từ");
        giaTri = new InputText("Giá Trị");
        mota = new JTextArea();
        mota.setLineWrap(true);
        mota.setWrapStyleWord(true);
        mota.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        mota.setPreferredSize(new Dimension(400, 25));
        JScrollPane scroll = new JScrollPane(mota);
        scroll.setPreferredSize(new Dimension(400, 25));

        String[] option = { "Phần Trăm Giảm", "Giá Cố Định Giảm" };
        hinhThuc = new JComboBox<>(option);
        hinhThuc.setPreferredSize(new Dimension(400, 15));
        hinhThuc.setFont(new Font("segoe UI", Font.PLAIN, 14));
        hinhThuc.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        hinhThuc.setBorder(new LineBorder(Color.GRAY, 1));

        if (khuyenMaiDTO != null) {
            maKM.setText(String.valueOf(khuyenMaiDTO.getMaKM()));
            tenKM.setText(khuyenMaiDTO.getTenKM());
            soLuong.setText(String.valueOf(khuyenMaiDTO.getSoLuong()));
            ngayBD.setDate(new java.sql.Date(khuyenMaiDTO.getNgayBD().getTime()));
            System.out.println(ngayBD);
            ngayKT.setDate(new java.sql.Date(khuyenMaiDTO.getNgayKT().getTime()));
            System.out.println(ngayKT);
            apDungChoHoaDonTu.setText(String.valueOf(khuyenMaiDTO.getApDungChoHoaDonTu()));
            giaTri.setText(String.valueOf(khuyenMaiDTO.getGiaTri()));
            mota.setText(khuyenMaiDTO.getMota());
            if (khuyenMaiDTO.getHinhThuc() == 1) {
                hinhThuc.setSelectedIndex(0);
            } else if (khuyenMaiDTO.getHinhThuc() == 2) {
                hinhThuc.setSelectedIndex(1);
            }
            if (title.equals("Xem Chi Tiết")) {
                pnlMain.add(maKM);
                maKM.getTxtForm().setEditable(false);
                tenKM.getTxtForm().setEditable(false);
                soLuong.getTxtForm().setEditable(false);
                ngayBD.getDate();
                ngayKT.getDate();
                apDungChoHoaDonTu.getTxtForm().setEditable(false);
                giaTri.getTxtForm().setEditable(false);
                mota.setEditable(false);
            }
        }
        pnlMain.add(tenKM);
        pnlMain.add(soLuong);
        pnlMain.add(panelDate);
        pnlMain.add(apDungChoHoaDonTu);
        pnlMain.add(giaTri);
        pnlMain.add(new JLabel("Hình Thức"));
        pnlMain.add(hinhThuc);
        pnlMain.add(new JLabel("Mô Tả"));
        pnlMain.add(scroll);

        JButton btnSave = new JButton("Lưu");
        btnSave.setFont(new Font("Segoe", Font.BOLD, 14));
        btnSave.setBackground(new Color(33, 150, 243));
        btnSave.setForeground(Color.WHITE);
        btnSave.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);

        buttonPanel.add(btnSave);
        if (title.equals("Xem Chi Tiết")) {
            buttonPanel.setVisible(false);
        } else {
            btnSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    isSaved = true;
                    validateKhuyenMai();
                    if (isSaved) {
                        if (khuyenMaiDTO != null) {
                            pnlMain.add(maKM);

                            khuyenMaiDTO.setTenKM(tenKM.getText());
                            khuyenMaiDTO.setSoLuong(Integer.valueOf(soLuong.getText()));
                            khuyenMaiDTO.setNgayBD(new java.sql.Date(khuyenMaiDTO.getNgayBD().getTime()));
                            khuyenMaiDTO.setNgayKT(new java.sql.Date(khuyenMaiDTO.getNgayKT().getTime()));
                            khuyenMaiDTO.setApDungChoHoaDonTu(Integer.valueOf(apDungChoHoaDonTu.getText()));
                            khuyenMaiDTO.setGiaTri(Integer.valueOf(giaTri.getText()));
                            if (hinhThuc.getSelectedIndex() == 0) {
                                khuyenMaiDTO.setHinhThuc(1);
                            } else if (hinhThuc.getSelectedIndex() == 1) {
                                khuyenMaiDTO.setHinhThuc(2);
                            }
                            khuyenMaiDTO.setMota(mota.getText());
                            System.out.println("Ngày bắt đầu: " + khuyenMaiDTO.getNgayBD());
                            System.out.println("Ngày kết thúc: " + khuyenMaiDTO.getNgayKT());

                        }
                        dispose();
                    }
                }

            });
            buttonPanel.add(btnSave);
        }

        JScrollPane scrollPane = new JScrollPane(pnlMain);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(400, 400));
        add(pnlContent, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setResizable(false);
    }

    public JButton createButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }

    public void validateKhuyenMai() {
        if (tenKM.getText() == null || tenKM.getText().trim().isEmpty()) {
            isSaved = false;
            tenKM.setLblError("Tên Khuyến Mãi Không Được Để Trống");
        } else {
            tenKM.setLblError("");
        }

        if (soLuong.getText() == null || soLuong.getText().trim().isEmpty()) {
            isSaved = false;
            soLuong.setLblError("Số Lượng Không Được Để Trống !");
        } else {
            try {
                int soluong = Integer.parseInt(soLuong.getText());
                if (soluong <= 0) {
                    isSaved = false;
                    soLuong.setLblError("Số Lượng Phải Lớn Hơn 0");
                } else {
                    soLuong.setLblError("");
                }
            } catch (NumberFormatException e) {
                // TODO: handle exception
                isSaved = false;
                soLuong.setLblError("Số Lượng Phải Là Số Hợp Lệ!");
            }
        }
        if (apDungChoHoaDonTu.getText() == null ||
                apDungChoHoaDonTu.getText().trim().isEmpty()) {
            isSaved = false;
            apDungChoHoaDonTu.setLblError("Áp Dụng Cho Hóa Đơn Từ Không Được Để Trống!");
        } else {
            try {
                int hoadontu = Integer.parseInt(apDungChoHoaDonTu.getText());
                if (hoadontu < 0) {
                    isSaved = false;
                    apDungChoHoaDonTu.setLblError("Giá Trị Hóa Đơn Phải >= 0!");
                } else {
                    apDungChoHoaDonTu.setLblError("");
                }
            } catch (NumberFormatException e) {
                // TODO: handle exception
                isSaved = false;
                apDungChoHoaDonTu.setLblError("Giá Trị Phải Là Số!");
            }
        }

        if (giaTri.getText() == null || giaTri.getText().isEmpty()) {
            isSaved = false;
            giaTri.setLblError("Giá Trị Không Được Để Trống !");
        } else {
            try {
                int gt = Integer.parseInt(giaTri.getText());
                if (gt <= 0) {
                    isSaved = false;
                    giaTri.setLblError("Giá Trị Phải Lớn Hơn 0!");
                } else {
                    giaTri.setLblError("");
                }
            } catch (NumberFormatException e) {
                isSaved = false;
                giaTri.setLblError("Giá Trị Khuyến Mãi Phải Là Số!");
            }
        }

        if (hinhThuc.getSelectedItem() == null) {
            isSaved = false;
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Hình Thức Khuyến Mãi!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);

        }

        if (ngayBD.getDate() == null) {
            isSaved = false;
            ngayBD.setLblError("Ngày Bắt Đầu Không Được Để Trống!");
        } else {
            ngayBD.setLblError("");
        }

        if (ngayKT.getDate() == null) {
            isSaved = false;
            ngayBD.setLblError("Ngày Kết Thúc Không Được Để Trống!");
        } else if (ngayBD.getDate() != null && ngayKT.getDate().before(ngayBD.getDate())) {
            isSaved = false;
            ngayBD.setLblError("Ngày Kết Thúc Phải Sau Ngày Bắt Đầu!");
        } else {
            ngayKT.setLblError("");
        }
        if (mota.getText().trim().isEmpty() || mota.getText() == null) {
            isSaved = false;
            JOptionPane.showMessageDialog(this, "Mô Tả Không Được Để Trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }

    public boolean isSaved() {
        return isSaved;
    }

    public KhuyenMaiDTO getKhuyenMaiData(int maKM) {
        byte hinhthuc = 0;
        if (hinhThuc.getSelectedIndex() == 0) {
            hinhthuc = 1;
        } else if (hinhThuc.getSelectedIndex() == 1) {
            hinhthuc = 2;
        }
        return new KhuyenMaiDTO(maKM, tenKM.getText(), Integer.valueOf(soLuong.getText()),
                new java.sql.Date(ngayBD.getDate().getTime()),
                new java.sql.Date(ngayKT.getDate().getTime()), Integer.valueOf(apDungChoHoaDonTu.getText()),
                Integer.valueOf(giaTri.getText()), hinhthuc, 1, mota.getText());
    }
}