package GUI.DiaLog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import DTO.KhuyenMaiDTO;
import GUI.Panel.InputType.InputDate;
import GUI.Panel.InputType.InputText;

public class KhuyenMaiDialog extends JDialog {
    private InputText maKM, tenKM, soLuong, apDungChoHoaDonTu, giaTri, hinhThuc;
    private InputDate ngayBD, ngayKT;
    private boolean isSaved = false;

    public KhuyenMaiDialog(Frame owner, KhuyenMaiDTO khuyenMaiDTO, String title) {
        super(owner, title, true);
        initComponents(khuyenMaiDTO, title);
        setLocationRelativeTo(owner);
    }

    private void initComponents(KhuyenMaiDTO khuyenMaiDTO, String title) {
        setSize(550, 750);
        setLayout(new BorderLayout(10, 10));

        JLabel lbContent = new JLabel(title);
        lbContent.setFont(new Font(getName(), Font.BOLD, 20));
        lbContent.setOpaque(true);
        lbContent.setBackground(new Color(100, 149, 237));
        lbContent.setForeground(Color.WHITE);
        lbContent.setBorder(new EmptyBorder(10, 10, 10, 10));
        JPanel pnlContent = new JPanel();
        pnlContent.add(lbContent, CENTER_ALIGNMENT);

        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
        pnlMain.setBorder(new EmptyBorder(5, 10, 5, 10));
        pnlMain.setPreferredSize(new Dimension(400, 400));
        pnlMain.setBackground(Color.WHITE);

        tenKM = new InputText("Tên Khuyến Mãi");
        soLuong = new InputText("Số Lượng");
        ngayBD = new InputDate("Ngày Bắt đầu");
        ngayKT = new InputDate("Ngày Kết Thúc");
        apDungChoHoaDonTu = new InputText("Áp Dụng Cho Hóa Đơn Từ");
        giaTri = new InputText("Giá Trị");
        hinhThuc = new InputText("Hình Thức");

        if (khuyenMaiDTO != null) {
            maKM = new InputText("Mã Khuyến Mãi");
            maKM.setText(String.valueOf(khuyenMaiDTO.getMaKM()));
            tenKM.setText(khuyenMaiDTO.getTenKM());
            soLuong.setText(String.valueOf(khuyenMaiDTO.getSoLuong()));
            ngayBD.setDate(new java.sql.Date(khuyenMaiDTO.getNgayBD().getTime()));
            ngayKT.setDate(new java.sql.Date(khuyenMaiDTO.getNgayKT().getTime()));
            apDungChoHoaDonTu.setText(String.valueOf(khuyenMaiDTO.getApDungChoHoaDonTu()));
            giaTri.setText(String.valueOf(khuyenMaiDTO.getGiaTri()));
            hinhThuc.setText(String.valueOf(khuyenMaiDTO.getHinhThuc()));

            if (title.equals("Xem Chi Tiết")) {
                pnlMain.add(maKM);
                maKM.getTxtForm().setEditable(false);
                tenKM.getTxtForm().setEditable(false);
                soLuong.getTxtForm().setEditable(false);
                ngayBD.getDate();
                ngayKT.getDate();
                apDungChoHoaDonTu.getTxtForm().setEditable(false);
                giaTri.getTxtForm().setEditable(false);
                hinhThuc.getTxtForm().setEditable(false);

            }
        }
        pnlMain.add(tenKM);
        pnlMain.add(soLuong);
        pnlMain.add(ngayBD);
        pnlMain.add(ngayKT);
        pnlMain.add(apDungChoHoaDonTu);
        pnlMain.add(giaTri);
        pnlMain.add(hinhThuc);

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlButtons.setBackground(this.getBackground());
        JButton btnSave = new JButton("Lưu");
        btnSave.setPreferredSize(new Dimension(90, 60));
        btnSave.setBackground(new Color(100, 149, 237)); // ForestGreen
        btnSave.setFont(new Font(getName(), Font.PLAIN, 20));
        btnSave.setFocusPainted(true);
        btnSave.setForeground(Color.WHITE);
        if (title.equals("Xem Chi Tiết")) {
            btnSave.setVisible(false);
        } else {
            btnSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    isSaved = true;
                    validateKhuyenMai();
                    if (isSaved) {
                        if (khuyenMaiDTO != null) {
                            khuyenMaiDTO.setTenKM(tenKM.getText());
                            khuyenMaiDTO.setSoLuong(Integer.valueOf(soLuong.getText()));
                            khuyenMaiDTO.setNgayBD(new java.sql.Date(khuyenMaiDTO.getNgayBD().getTime()));
                            khuyenMaiDTO.setNgayKT(new java.sql.Date(khuyenMaiDTO.getNgayKT().getTime()));
                            khuyenMaiDTO.setApDungChoHoaDonTu(Integer.valueOf(apDungChoHoaDonTu.getText()));
                            khuyenMaiDTO.setGiaTri(Integer.valueOf(giaTri.getText()));
                            if (hinhThuc.getText().equals("%") || hinhThuc.getText().equals("1")) {
                                khuyenMaiDTO.setHinhThuc(1);
                            } else if (hinhThuc.getText().equals("tien") || hinhThuc.getText().equals("2")) {
                                khuyenMaiDTO.setHinhThuc(2);
                            }
                        }
                        dispose();
                    }
                }

            });
            pnlButtons.add(btnSave);
        }

        JScrollPane scrollPane = new JScrollPane(pnlMain, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(pnlContent, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(pnlButtons, BorderLayout.SOUTH);

        setResizable(false);
    }

    public void validateKhuyenMai() {
        if (tenKM.getText() == null || tenKM.getText().isEmpty()) {
            isSaved = false;
            tenKM.setLblError("Tên Khuyến Mãi Không Được Để Trống");
        } else {
            tenKM.setLblError("");
        }

        if (soLuong.getText() == null || soLuong.getText().isEmpty()) {
            isSaved = false;
            soLuong.setLblError("Số Lượng Không Được Để Trống !");
        } else {
            soLuong.setLblError("");
        }

        if (apDungChoHoaDonTu.getText() == null || apDungChoHoaDonTu.getText().isEmpty()) {
            isSaved = false;
            apDungChoHoaDonTu.setLblError("Áp Dụng Cho Hóa Đơn Từ Không Được Để Trống !");
        } else {
            apDungChoHoaDonTu.setLblError("");
        }

        if (giaTri.getText() == null || giaTri.getText().isEmpty()) {
            isSaved = false;
            giaTri.setLblError("Giá Trị Không Được Để Trống !");
        } else {
            giaTri.setLblError("");
        }

        if (hinhThuc.getText() == null || hinhThuc.getText().isEmpty()) {
            isSaved = false;
            hinhThuc.setLblError("Hình Thức Không Được Để Trống !");
        } else {
            hinhThuc.setLblError("");
        }

        if (ngayBD.getDate() == null) {
            isSaved = false;
            ngayBD.setLblError("Ngày Bắt Đầu Không Được Để Trống !");
        } else {
            ngayBD.setLblError("");
        }

        if (ngayKT.getDate() == null) {
            isSaved = false;
            ngayKT.setLblError("Ngày Kết Thúc Không Được Để Trống !");
        } else {
            ngayKT.setLblError("");
        }
    }

    public boolean isSaved() {
        return isSaved;
    }

    public KhuyenMaiDTO getKhuyenMaiData(int maKM) {
        byte hinhthuc = 0;
        if (hinhThuc.getText().equals("%")) {
            hinhthuc = 1;
        } else if (hinhThuc.getText().equals("tiền")) {
            hinhthuc = 2;
        }
        return new KhuyenMaiDTO(maKM, tenKM.getText(), Integer.valueOf(soLuong.getText()),
                new java.sql.Date(ngayBD.getDate().getTime()),
                new java.sql.Date(ngayKT.getDate().getTime()), Integer.valueOf(apDungChoHoaDonTu.getText()),
                Integer.valueOf(giaTri.getText()), hinhthuc, 1);
    }
}
