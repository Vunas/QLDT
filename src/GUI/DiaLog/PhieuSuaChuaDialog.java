package GUI.DiaLog;

import DTO.PhieuSuaChuaDTO;
import BLL.BUS.PhieuSuaChuaBLL;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class PhieuSuaChuaDialog extends JDialog {
    private JTextField txtMaPhieuBH, txtMaSP, txtIMEI;
    private JDateChooser dateChooser;
    private JComboBox<String> cbTrangThai;
    private JTextArea txtTinhTrang, txtXuLy, txtGhiChu;
    private JButton btnLuu, btnDong;

    public PhieuSuaChuaDialog(JFrame owner, int maPhieuBH, int maSP, String imei) {
        super(owner, "Phiếu sửa chữa / xử lý bảo hành", true);
        initComponent(maPhieuBH, maSP, imei);
        this.setSize(550, 500);
        this.setLocationRelativeTo(owner);
        this.setVisible(true);
    }
    public PhieuSuaChuaDialog(JFrame owner, int maPhieuBH, int maSP, String imei, boolean isUpdate) {
        super(owner, "Phiếu sửa chữa / xử lý bảo hành", true);
        initComponent2(maPhieuBH, maSP, imei);
        this.setSize(550, 500);
        this.setLocationRelativeTo(owner);
        this.setVisible(true);
    }
    private void initComponent(int maPhieuBH, int maSP, String imei) {
        this.setLayout(new BorderLayout(10, 10));

        // ==== Panel form ====
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 0, 15));

        txtMaPhieuBH = new JTextField(String.valueOf(maPhieuBH));
        txtMaPhieuBH.setEditable(false);
        txtMaSP = new JTextField(String.valueOf(maSP));
        txtMaSP.setEditable(false);
        txtIMEI = new JTextField(imei);
        txtIMEI.setEditable(false);

        dateChooser = new com.toedter.calendar.JDateChooser();
        dateChooser.setDate(new Date());

        cbTrangThai = new JComboBox<>(new String[]{"Đang xử lý", "Đã sửa xong", "Từ chối bảo hành"});

        formPanel.add(new JLabel("Mã phiếu bảo hành:"));
        formPanel.add(txtMaPhieuBH);
        formPanel.add(new JLabel("Mã sản phẩm:"));
        formPanel.add(txtMaSP);
        formPanel.add(new JLabel("IMEI:"));
        formPanel.add(txtIMEI);
        formPanel.add(new JLabel("Ngày nhận máy:"));
        formPanel.add(dateChooser);
        formPanel.add(new JLabel("Trạng thái xử lý:"));
        formPanel.add(cbTrangThai);

        // ==== Panel nội dung ====
        
        txtTinhTrang = new JTextArea(3, 30);
        txtXuLy = new JTextArea(3, 30);
        txtGhiChu = new JTextArea(2, 30);
        
        JPanel contentPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        contentPanel.add(createLabeledArea("Tình trạng lỗi:", txtTinhTrang));
        contentPanel.add(createLabeledArea("Hướng xử lý:", txtXuLy));
        contentPanel.add(createLabeledArea("Ghi chú:", txtGhiChu));

        // ==== Buttons ====
        
        btnLuu = new JButton("Lưu phiếu sửa chữa");
        
        btnLuu.addActionListener(e -> luuPhieu());
        btnDong = new JButton("Đóng");
        btnDong.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(btnLuu);
        buttonPanel.add(btnDong);

        // ==== Assemble ====
        this.add(formPanel, BorderLayout.NORTH);
        this.add(contentPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void initComponent2(int maPhieuBH, int maSP, String imei) {
        this.setLayout(new BorderLayout(10, 10));

        // ==== Panel form ====
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 0, 15));

        txtMaPhieuBH = new JTextField(String.valueOf(maPhieuBH));
        txtMaPhieuBH.setEditable(false);
        txtMaSP = new JTextField(String.valueOf(maSP));
        txtMaSP.setEditable(false);
        txtIMEI = new JTextField(imei);
        txtIMEI.setEditable(false);

        dateChooser = new com.toedter.calendar.JDateChooser();
        LocalDate localDate = new PhieuSuaChuaBLL().getByMaPhieuBHAndIMEI(maPhieuBH, imei).get(0).getNgayNhan();
        dateChooser.setDate(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        dateChooser.setEnabled(false);
        cbTrangThai = new JComboBox<>(new String[]{"Đang xử lý", "Đã sửa xong", "Từ chối bảo hành"});

        formPanel.add(new JLabel("Mã phiếu bảo hành:"));
        formPanel.add(txtMaPhieuBH);
        formPanel.add(new JLabel("Mã sản phẩm:"));
        formPanel.add(txtMaSP);
        formPanel.add(new JLabel("IMEI:"));
        formPanel.add(txtIMEI);
        formPanel.add(new JLabel("Ngày nhận máy:"));
        formPanel.add(dateChooser);
        formPanel.add(new JLabel("Trạng thái xử lý:"));
        formPanel.add(cbTrangThai);

        // ==== Panel nội dung ====
        
        txtTinhTrang = new JTextArea(3, 30);
        txtXuLy = new JTextArea(3, 30);
        txtGhiChu = new JTextArea(2, 30);
        
        txtTinhTrang.setText(new PhieuSuaChuaBLL().getByMaPhieuBHAndIMEI(maPhieuBH, imei).get(0).getTinhTrang());
        txtTinhTrang.setEditable(false);
        txtXuLy.setText(new PhieuSuaChuaBLL().getByMaPhieuBHAndIMEI(maPhieuBH, imei).get(0).getXuLy());
        txtXuLy.setEditable(false);
        txtGhiChu.setText(new PhieuSuaChuaBLL().getByMaPhieuBHAndIMEI(maPhieuBH, imei).get(0).getGhiChu());
        txtGhiChu.setEditable(false);
        
        JPanel contentPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        contentPanel.add(createLabeledArea("Tình trạng lỗi:", txtTinhTrang));
        contentPanel.add(createLabeledArea("Hướng xử lý:", txtXuLy));
        contentPanel.add(createLabeledArea("Ghi chú:", txtGhiChu));

        // ==== Buttons ====
        
        btnLuu = new JButton("Cập nhật sửa chữa");
        btnLuu.addActionListener(e -> luuPhieuCapNhat());
        btnDong = new JButton("Đóng");
        btnDong.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(btnLuu);
        buttonPanel.add(btnDong);

        // ==== Assemble ====
        this.add(formPanel, BorderLayout.NORTH);
        this.add(contentPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createLabeledArea(String label, JTextArea area) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder(label));
        JScrollPane scroll = new JScrollPane(area);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    private void luuPhieu() {
        try {
            int maPhieuBH = Integer.parseInt(txtMaPhieuBH.getText());
            int maSP = Integer.parseInt(txtMaSP.getText());
            String imei = txtIMEI.getText();
            Date date = dateChooser.getDate();
            LocalDate ngayNhan = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String tinhTrang = txtTinhTrang.getText();
            String xuLy = txtXuLy.getText();
            String trangThai = (String) cbTrangThai.getSelectedItem();
            String ghiChu = txtGhiChu.getText();

            if (tinhTrang.isEmpty() || xuLy.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tình trạng lỗi và hướng xử lý.");
                return;
            }

            PhieuSuaChuaDTO dto = new PhieuSuaChuaDTO(0, maPhieuBH, maSP, imei, ngayNhan, tinhTrang, xuLy, trangThai, ghiChu);
            boolean success = new PhieuSuaChuaBLL().add(dto);

            if (success) {
                JOptionPane.showMessageDialog(this, "Lưu phiếu sửa chữa thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Lưu phiếu thất bại!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi lưu phiếu.");
        }
    }
    
    private void luuPhieuCapNhat() {
        try {  
            int maPhieuBH = Integer.parseInt(txtMaPhieuBH.getText());
            String imei = txtIMEI.getText();
            String trangThai = (String) cbTrangThai.getSelectedItem();     
            boolean success = new PhieuSuaChuaBLL().updateTrangThai(new PhieuSuaChuaBLL().getByMaPhieuBHAndIMEI(maPhieuBH, imei).get(0).getMaPhieuSC() , trangThai);
            if (success) {
                JOptionPane.showMessageDialog(this, "Lưu phiếu sửa chữa thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Lưu phiếu thất bại!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi lưu phiếu.");
        }
    }
}
