package GUI.DiaLog;
import BLL.BUS.BrandBLL;
import BLL.BUS.ColorBLL;
import BLL.BUS.RamBLL;
import BLL.BUS.RomBLL;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import DTO.SanPhamDTO;
import GUI.Panel.Component.SelectForm;
import GUI.Panel.InputType.InputText;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
public class SanPhamDiaLog extends JDialog{
    private InputText tfTenSP;
    private InputText tfImg;
    private InputText tfGiaNhap;
    private InputText tfGiaBan;
    private InputText tfChip;
    private InputText tfThoiGianBaoHanh;
    SelectForm cbxRam , cbxRom , cbxColor , cbxBrand;
    private boolean isSaved = false;
    private ImageIcon resizedIcon;
    private JLabel lblIcon;
    BrandBLL brandBLL = new BrandBLL();
    ColorBLL colorBLL = new ColorBLL();
    RomBLL romBLL = new RomBLL();
    RamBLL ramBLL = new RamBLL();
    
    public SanPhamDiaLog(JFrame owner, SanPhamDTO sanPham, String titleString) {
        super(owner, titleString, true);
        initComponents(sanPham, titleString);
        setLocationRelativeTo(owner); // Căn giữa so với cửa sổ cha
   
    }

    private void initComponents(SanPhamDTO sanPham, String titleString) {
        
        // Main container to hold pnlMain (left) and panelRight (right)
        JPanel pnlContainer = new JPanel();
        pnlContainer.setLayout(new BoxLayout(pnlContainer, BoxLayout.X_AXIS));
        pnlContainer.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding
        pnlContainer.setPreferredSize(new Dimension(600, 700));
 
        setSize(900, 600); // Kích thước hợp lý
        setLayout(new BorderLayout(10, 10)); // Tạo khoảng cách giữa các phần

        JLabel lblContent = new JLabel(titleString);
        lblContent.setFont(new Font(getName(), Font.BOLD, 20));
        JPanel pnlContent = new JPanel();
        pnlContent.add(lblContent, CENTER_ALIGNMENT);

        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
//        pnlMain.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlMain.setPreferredSize(new Dimension(380, 500)); // Chiều cao lớn hơn cửa sổ
        // Set pnlMain to take more width
//        pnlMain.setPreferredSize(new Dimension(500, 600)); // Adjust width
        pnlMain.setMaximumSize(new Dimension(500, Integer.MAX_VALUE)); // Expand height
        
        
        JPanel panelRight = new JPanel();
        panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
//        panelRight.setMaximumSize(new Dimension(500, 200));
        // Set panelRight (ComboBox group) to take up less width
        panelRight.setPreferredSize(new Dimension(400, 500)); // Adjust width
        panelRight.setMaximumSize(new Dimension(300, Integer.MAX_VALUE)); // Expand height
        
        
        tfTenSP = new InputText("Tên sản phẩm");
        
        cbxBrand = new SelectForm("Thương hiệu",brandBLL.getArrTenThuongHieu());
        cbxBrand.setMaximumSize(new Dimension(Integer.MAX_VALUE , 60));
        cbxRom = new SelectForm("ROM", romBLL.getArrKichThuoc());
        cbxRom.setMaximumSize(new Dimension(Integer.MAX_VALUE , 60));
        cbxRam = new SelectForm("RAM", ramBLL.getArrKichThuoc());
        cbxRam.setMaximumSize(new Dimension(Integer.MAX_VALUE , 60));
        cbxColor = new SelectForm("Màu sắc", colorBLL.getArrTenMauSac());
        cbxColor.setMaximumSize(new Dimension(Integer.MAX_VALUE , 60));
        JButton btnChooseImage = new JButton("Chọn Ảnh"); 
        
        tfImg = new InputText("");
        tfImg.getTxtForm().setEditable(false);
        tfImg.getTxtForm().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                onTextChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                onTextChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                onTextChanged();  // This is usually for styled text, not plain text.
            }

            private void onTextChanged() {
                    File file = new File(tfImg.getText());
                    if (!file.exists()) {
                        JOptionPane.showMessageDialog(null, "Tệp không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    } else {     
                                
                        try {
                            BufferedImage originalImage = ImageIO.read(file);
                            BufferedImage resizedImg = resizeImage(originalImage, 300, 300);
                            lblIcon.setIcon(new ImageIcon(resizedImg));
                        } catch (IOException ex) {
                            Logger.getLogger(SanPhamDiaLog.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                
                            }
            }
        });
        ImageIcon icon = new ImageIcon("scr/resources/img/1619198933.jpg");
        Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Resize
        resizedIcon = new ImageIcon(img);
        lblIcon = new JLabel(resizedIcon);
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
                    File file = new File(destFolder + selectedFile.getName());
                            if (!file.exists()) {
                                JOptionPane.showMessageDialog(null, "Tệp không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            } else {     
                                
                                try {
                                    BufferedImage originalImage = ImageIO.read(file);
                                    BufferedImage resizedImg = resizeImage(originalImage, 300, 300);
                                    lblIcon.setIcon(new ImageIcon(resizedImg));
                                } catch (IOException ex) {
                                    Logger.getLogger(SanPhamDiaLog.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                            }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Lỗi khi sao chép ảnh!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        
        
        tfGiaNhap = new InputText("Giá nhập");
        tfGiaBan = new InputText("Giá bán");
      
        
        tfChip = new InputText("Chip");
        tfThoiGianBaoHanh = new InputText("Thời gian bảo hành");
        
        if (sanPham != null) {
            
            tfTenSP.setText(sanPham.getTenSP());
            ImageIcon newIcon = new ImageIcon(sanPham.getImg()); // Load image               
            Image newImg = newIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Resize
            lblIcon.setIcon(new ImageIcon(newImg));     
            tfImg.setText(sanPham.getImg());
            
            tfGiaNhap.setText(sanPham.getGiaNhap()+"");
            tfGiaBan.setText(sanPham.getGiaBan()+"");
            
            cbxBrand.getCbb().setSelectedItem(sanPham.getThuongHieu());
            cbxColor.getCbb().setSelectedItem(sanPham.getMauSac());
            cbxRam.getCbb().setSelectedItem(sanPham.getRam()+"");
            cbxRom.getCbb().setSelectedItem(sanPham.getRom()+"");
                
            tfChip.setText(sanPham.getChip());
            tfThoiGianBaoHanh.setText(sanPham.getThoiGianBaoHanh()+"");

            // Set fields to read-only if "Xem chi tiết"
            if (titleString.equals("Xem chi tiết")) {
               
                
                tfTenSP.getTxtForm().setEditable(false);
                tfImg.getTxtForm().setEditable(false);
                btnChooseImage.setEnabled(false);
                
                tfGiaNhap.getTxtForm().setEditable(false);
                tfGiaBan.getTxtForm().setEditable(false);
                
                cbxColor.getCbb().setEnabled(false);
                cbxBrand.getCbb().setEnabled(false);
                cbxRam.getCbb().setEnabled(false);
                cbxRom.getCbb().setEnabled(false);
                
                tfChip.getTxtForm().setEditable(false);
                tfThoiGianBaoHanh.getTxtForm().setEditable(false);
            }
        }
        pnlMain.add(Box.createHorizontalGlue());
        pnlMain.add(tfTenSP);
        pnlMain.add(tfImg);
//        pnlMain.add(lblIcon);
//        pnlMain.add(btnChooseImage);
        
        pnlMain.add(tfGiaNhap);
        pnlMain.add(tfGiaBan);
        pnlMain.add(tfChip);
        pnlMain.add(tfThoiGianBaoHanh);
        
        
        panelRight.add(cbxColor);
        panelRight.add(Box.createRigidArea(new Dimension(0,10)));
        panelRight.add(cbxBrand);
        panelRight.add(Box.createRigidArea(new Dimension(0,10)));
        panelRight.add(cbxRam);
        panelRight.add(Box.createRigidArea(new Dimension(0,10)));
        panelRight.add(cbxRom);
        panelRight.add(Box.createRigidArea(new Dimension(0,10)));
        panelRight.add(tfImg);
        panelRight.add(lblIcon);
        panelRight.add(btnChooseImage);
        pnlMain.add(panelRight);

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
                            

//                          Image newImg = newIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Resize            
//                          ImageIcon newIcon = new ImageIcon(tfImg.getText());

                            File file = new File(tfImg.getText());
                            if (!file.exists()) {
                                JOptionPane.showMessageDialog(null, "Tệp không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            } else {     
                                
                                try {
                                    BufferedImage originalImage = ImageIO.read(file);
                                    BufferedImage resizedImg = resizeImage(originalImage, 300, 300);
                                    lblIcon.setIcon(new ImageIcon(resizedImg));
                                } catch (IOException ex) {
                                    Logger.getLogger(SanPhamDiaLog.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                            }

                            
                            sanPham.setImg(tfImg.getText());
                            
                            sanPham.setGiaNhap(Integer.parseInt(tfGiaNhap.getText()));
                            sanPham.setGiaBan(Integer.parseInt(tfGiaBan.getText()));
                            
                            sanPham.setMauSac(cbxColor.getValue());
                            sanPham.setThuongHieu(cbxBrand.getValue());
                            sanPham.setRam(Integer.parseInt(cbxRam.getValue()));
                            sanPham.setRom(Integer.parseInt(cbxRom.getValue()));
                            
                            sanPham.setChip(tfChip.getText());
                            sanPham.setThoiGianBaoHanh(Float.parseFloat(tfThoiGianBaoHanh.getText()));
                        }
                        dispose();
                    }

                }
            });
            pnlButtons.add(btnSave);
        }
        
//        JScrollPane mainScrollPane = new JScrollPane(pnlMain);
//        mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        
//        mainScrollPane.getVerticalScrollBar().setUnitIncrement(16);
//        mainScrollPane.getVerticalScrollBar().setBlockIncrement(50);
        
        
        
        // Add components horizontally
        pnlContainer.add(pnlMain);
        pnlContainer.add(Box.createHorizontalStrut(10)); // Spacing between panels
        pnlContainer.add(panelRight);

        // Add pnlContainer to the JFrame
//        add(pnlContainer, BorderLayout.CENTER);
        
//        JScrollPane scrollPane = new JScrollPane(pnlMain);
        JScrollPane scrollPane = new JScrollPane(pnlContainer);
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
    
    public static BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();

        // Kích hoạt chế độ khử răng cưa (anti-aliasing)
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ ảnh với chất lượng cao
        g2d.drawImage(originalImage, 0, 0, width, height, null);
        g2d.dispose();

        return resizedImage;
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
    
    public SanPhamDTO getSanPhamData(int maSP) {
        return new SanPhamDTO(maSP, tfTenSP.getText(), tfImg.getText(),0,
                Integer.parseInt(tfGiaNhap.getText()),Integer.parseInt(tfGiaBan.getText()) , cbxColor.getValue() , cbxBrand.getValue(),
                Integer.parseInt(cbxRam.getValue()) , Integer.parseInt(cbxRom.getValue()) , tfChip.getText(), Float.parseFloat(tfThoiGianBaoHanh.getText()),1);
    }
    
}


