/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.DiaLog;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import DTO.SanPhamDTO;
import GUI.Panel.InputType.InputText;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.filechooser.FileNameExtensionFilter;
public class SanPhamDiaLog extends JDialog{
    private InputText tfMaSP;
    private InputText tfTenSP;
    private InputText tfImg;
    private InputText tfSoLuong;
    private InputText tfGiaNhap;
    private InputText tfGiaBan;
    private InputText tfMauSac;
    private InputText tfThuongHieu;
    private InputText tfRam;
    private InputText tfRom;
    private InputText tfChip;
    private InputText tfThoiGianBaoHanh;
    private boolean isSaved = false;

    public SanPhamDiaLog(JFrame owner, SanPhamDTO sanPham, String titleString) {
        super(owner, titleString, true);
        initComponents(sanPham, titleString);
        setLocationRelativeTo(owner); // Căn giữa so với cửa sổ cha
    }

    private void initComponents(SanPhamDTO sanPham, String titleString) {
        setSize(400, 450); // Kích thước hợp lý
        setLayout(new BorderLayout(10, 10)); // Tạo khoảng cách giữa các phần

        JLabel lblContent = new JLabel(titleString);
        lblContent.setFont(new Font(getName(), Font.BOLD, 20));
        JPanel pnlContent = new JPanel();
        pnlContent.add(lblContent, CENTER_ALIGNMENT);

        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
        pnlMain.setBorder(new EmptyBorder(5, 10, 5, 10));
        pnlMain.setPreferredSize(new Dimension(380, 1000)); // Chiều cao lớn hơn cửa sổ


        tfMaSP = new InputText("Mã sản phẩm");
        tfTenSP = new InputText("Tên sản phẩm");
        
        
        
        JButton btnChooseImage = new JButton("Chọn Ảnh"); 
        
        tfImg = new InputText("");
        tfImg.getTxtForm().setEditable(false);
        btnChooseImage.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn ảnh sản phẩm");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setFileFilter(new FileNameExtensionFilter("Hình ảnh", "jpg", "png", "jpeg"));

            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();

                // Define the destination path in your project
                String destFolder = "src/resources/img/";
                File destFile = new File(destFolder + selectedFile.getName());

                try {
                    // Copy the file to the project folder
                    Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                    // Set the relative path to the text field
                    tfImg.setText(destFolder + selectedFile.getName()); 
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Lỗi khi sao chép ảnh!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        
        tfSoLuong = new InputText("Số lượng");
        tfGiaNhap = new InputText("Giá nhập");
        tfGiaBan = new InputText("Giá bán");
        tfMauSac = new InputText("Màu sắc");
        tfThuongHieu = new InputText("Thương hiệu");
        tfRam = new InputText("Ram");
        tfRom = new InputText("Rom");
        tfChip = new InputText("Chip");
        tfThoiGianBaoHanh = new InputText("Thời gian bảo hành");
        
        if (sanPham != null) {
            tfMaSP = new InputText("Mã sản phẩm");
            tfMaSP.setText(String.valueOf(sanPham.getMaSP()));
            tfTenSP.setText(sanPham.getTenSP());
            tfImg.setText(sanPham.getImg());
            tfSoLuong.setText(sanPham.getSoLuong()+"");
            tfGiaNhap.setText(sanPham.getGiaNhap()+"");
            tfGiaBan.setText(sanPham.getGiaBan()+"");
            tfMauSac.setText(sanPham.getMauSac());
            tfThuongHieu.setText(sanPham.getThuongHieu());
            tfRam.setText(sanPham.getRam()+"");
            tfRom.setText(sanPham.getRom()+"");
            tfChip.setText(sanPham.getChip());
            tfThoiGianBaoHanh.setText(sanPham.getThoiGianBaoHanh()+"");

            // Set fields to read-only if "Xem chi tiết"
            if (titleString.equals("Xem chi tiết")) {
                pnlMain.add(tfMaSP);
                tfMaSP.getTxtForm().setEditable(false);
                tfTenSP.getTxtForm().setEditable(false);
                tfImg.getTxtForm().setEditable(false);
                btnChooseImage.setEnabled(false);
                tfSoLuong.getTxtForm().setEditable(false);
                tfGiaNhap.getTxtForm().setEditable(false);
                tfGiaBan.getTxtForm().setEditable(false);
                tfMauSac.getTxtForm().setEditable(false);
                tfThuongHieu.getTxtForm().setEditable(false);
                tfRam.getTxtForm().setEditable(false);
                tfRom.getTxtForm().setEditable(false);
                tfChip.getTxtForm().setEditable(false);
                tfThoiGianBaoHanh.getTxtForm().setEditable(false);
            }
        }

        pnlMain.add(tfTenSP);
        pnlMain.add(tfImg);
        pnlMain.add(btnChooseImage);
        pnlMain.add(tfSoLuong);
        pnlMain.add(tfGiaNhap);
        pnlMain.add(tfGiaBan);
        pnlMain.add(tfMauSac);
        pnlMain.add(tfThuongHieu);
        pnlMain.add(tfRam);
        pnlMain.add(tfRom);
        pnlMain.add(tfChip);
        pnlMain.add(tfThoiGianBaoHanh);

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
                    validateSanPham();
                    if (isSaved) {
                        if (sanPham != null) {
                            sanPham.setTenSP(tfTenSP.getText());
                            sanPham.setImg(tfImg.getText());
                            sanPham.setSoLuong(Integer.parseInt(tfSoLuong.getText()));
                            sanPham.setGiaNhap(Integer.parseInt(tfGiaNhap.getText()));
                            sanPham.setGiaBan(Integer.parseInt(tfGiaBan.getText()));
                            sanPham.setMauSac(tfMauSac.getText());
                            sanPham.setThuongHieu(tfThuongHieu.getText());
                            sanPham.setRam(Integer.parseInt(tfRam.getText()));
                            sanPham.setRom(Integer.parseInt(tfRom.getText()));
                            sanPham.setChip(tfChip.getText());
                            sanPham.setThoiGianBaoHanh(Float.parseFloat(tfThoiGianBaoHanh.getText()));
                        }
                        dispose();
                    }

                }
            });
            pnlButtons.add(btnSave);
        }
        JScrollPane scrollPane = new JScrollPane(pnlMain);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getVerticalScrollBar().setBlockIncrement(50);
        
        // Add components to dialog
        add(pnlContent, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        add(pnlButtons, BorderLayout.SOUTH);

        // Configure the dialog
        setResizable(false); // Không cho phép thay đổi kích thước
    }

    public void validateSanPham() {
        if (tfTenSP.getText() == null || tfTenSP.getText().isEmpty()) {
            isSaved = false;
            tfTenSP.setLblError("Tên sản phẩm không được để trống.");
        } else
            tfTenSP.setLblError("");
        if (tfGiaNhap.getText() == null || tfGiaNhap.getText().isEmpty()) {
            isSaved = false; // Kiểm tra số điện thoại có đúng 10 số
            tfGiaNhap.setLblError("Giá nhập sản phẩm không được để trống.");
        } else
            tfGiaNhap.setLblError("");
        if (tfGiaBan.getText() == null || tfGiaBan.getText().isEmpty()) {
            isSaved = false; // Kiểm tra số điện thoại có đúng 10 số
            tfGiaBan.setLblError("Giá bán sản phẩm không được để trống.");
        } else
            tfGiaBan.setLblError("");
    }

    public boolean isSaved() {
        return isSaved;
    }

    public SanPhamDTO getSanPhamData(int maKH) {
        return new SanPhamDTO(maKH, tfTenSP.getText(), tfImg.getText(), Integer.parseInt(tfSoLuong.getText()),
                Integer.parseInt(tfGiaNhap.getText()),Integer.parseInt(tfGiaBan.getText()) , tfMauSac.getText() , tfThuongHieu.getText() ,
                Integer.parseInt(tfRam.getText()) , Integer.parseInt(tfRom.getText()) , tfChip.getText(), Float.parseFloat(tfThoiGianBaoHanh.getText()));
    }
}
