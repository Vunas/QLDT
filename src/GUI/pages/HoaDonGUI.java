/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.pages;

import BLL.BUS.ChiTietHoaDonBLL;
import BLL.BUS.HoaDonBLL;
import BLL.BUS.KhachHangBLL;
import BLL.BUS.NhanVienBLL;
import DTO.ChiTietHoaDonDTO;
import DTO.HoaDonDTO;
import DTO.KhachHangDTO;
import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
import GUI.DiaLog.HoaDonDiaLog;
import GUI.Frame.Main;
import GUI.Panel.TaoHoaDon;
import GUI.Panel.TopNav;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import util.ExportExcelUtility;

/**
 *
 * @author nguyen
 */
public class HoaDonGUI extends JPanel {
    private TopNav topNav;
    private JPanel pnlBot;
    private JTable tbl,tb2;
    private DefaultTableModel tbmtb1,tbmtb2;
    private JScrollPane scrtb1;
    private Main main;
    HoaDonBLL hoaDonBLL = new HoaDonBLL();

    public HoaDonGUI(Main main, TopNav topNav) {
        initComponent(main, topNav);
        loaddata();
        loaddatacthd();
        chucNang();
    }

    private void initComponent(Main main, TopNav topNav) {
        this.main = main;
        this.topNav = topNav;
        String[] itemFindFor = { "Tất Cả", "Mã Hóa Đơn", "Khách Hàng", "Nhân Viên Lập" };

        topNav.setItemComboBox(itemFindFor);

        JButton XuatPdf = new JButton("xuat pdf");
        XuatPdf = new JButton("Xuất PDF",
                new FlatSVGIcon("./resources/icon/pdf.svg", 0.35f));
        XuatPdf.setToolTipText("Xuất PDF");
        XuatPdf.setVerticalTextPosition(SwingConstants.BOTTOM);
        XuatPdf.setHorizontalTextPosition(SwingConstants.CENTER);
        XuatPdf.putClientProperty(FlatClientProperties.STYLE, "arc: 12");
        XuatPdf.setOpaque(false);
        XuatPdf.setMaximumSize(new Dimension(9, 6));
        XuatPdf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Chọn 1 dòng để xuất hóa đơn pdf");
                } else {
                    int maHD = Integer.parseInt(tbl.getValueAt(selectedRow, 0).toString());

                    HoaDonDTO hoaDonDTO = hoaDonBLL.getHoaDonById(maHD);
                    try {
                        hoaDonBLL.xuatHoaDonPDF(hoaDonDTO);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

            }
        });
        topNav.add(XuatPdf);

        pnlBot = new JPanel(new BorderLayout());
        pnlBot.setPreferredSize(new Dimension(0, 500));
        pnlBot.setBorder(new EmptyBorder(10, 15, 10, 15));

        tbl = new JTable();
        tbl.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbl.setRowHeight(35);
        tbl.setFocusable(false);

        tbmtb1 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String[] headtable = { "Mã Hóa Đơn", "Khách Hàng", "Nhân Viên Nhập", "Thời Gian", "Tổng Tiền" };
        tbmtb1.setColumnIdentifiers(headtable);
        tbl.setModel(tbmtb1);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tbl.getColumnCount(); i++) {
            tbl.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JTableHeader header = tbl.getTableHeader();
        header.setPreferredSize(new Dimension(0, 40));
        header.setBackground(new Color(100, 149, 237));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));

        scrtb1 = new JScrollPane(tbl);
        scrtb1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrtb1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        pnlBot.add(scrtb1, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(topNav, BorderLayout.NORTH);
        add(pnlBot, BorderLayout.CENTER);
        
          tb2 = new JTable();
         String[] headtable2 = {"maCTHoaDon", "mahoadon", "mabaohanh", "masanpham", "soluong","dongia"};
         tbmtb2 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
         tbmtb2.setColumnIdentifiers(headtable2);
         tb2.setModel(tbmtb2);
    }
    
      public void loaddatacthd(){
        List<ChiTietHoaDonDTO> list = new ChiTietHoaDonBLL().getAllChiTietHoaDon();
        for(ChiTietHoaDonDTO cthd : list){
            tbmtb2.addRow(new Object[]{cthd.getMaChiTietHoaDon(),cthd.getMaHoaDon(),cthd.getMaBaoHanh(),cthd.getMaSanPham(),cthd.getSoLuong(),cthd.getDonGia()});
        }
    }
    private void chucNang() {
        JButton[] btn = topNav.getBtn();
        JButton reFresh = topNav.getBtnRefresh();
        JTextField textSearch = topNav.getTextSearch();
        btn[1].setVisible(false);
        btn[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setPanel(new TaoHoaDon(main, topNav));
            }
        });
        btn[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Chọn 1 dòng để xem chi tiết");
                } else {
                    int maHD = Integer.parseInt(tbl.getValueAt(selectedRow, 0).toString());

                    // Nếu dialog chưa tạo hoặc đã đóng thì tạo mới

                    HoaDonDiaLog hddialog = new HoaDonDiaLog(main, maHD);
                    hddialog.setVisible(true);
                }

            }
        });
        btn[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if (selectedRow != -1) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa chứ", "Xác ",
                            JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        int maHD = Integer.parseInt(tbmtb1.getValueAt(selectedRow, 0).toString());
                        new ChiTietHoaDonBLL().deleteChiTietHoaDon(maHD);
                        new HoaDonBLL().deleteHoaDon(maHD);
                        loaddata();
                        JOptionPane.showMessageDialog(null, "Xóa thành công");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Chọn 1 dòng để xóa");
                }
            }
        });

        btn[4].setVisible(false);

        btn[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ExportExcelUtility.save2TableToExcel(tbl, "Hóa Đơn", tb2, "Chi Tiết Hóa Đơn");
            }
        });

        reFresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                topNav.getFindFor().setSelectedIndex(0);
                topNav.getTextSearch().setText("");
                loaddata();
            }
        });

        textSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String type = topNav.getFindFor().getSelectedItem().toString().toLowerCase();
                String keyword = textSearch.getText().trim();

                List<HoaDonDTO> list = new HoaDonBLL().getAllHoaDon();

                // Tạo danh sách kết quả để lưu khách hàng phù hợp
                List<HoaDonDTO> filteredList = new ArrayList<>();

                // Lọc dữ liệu theo tiêu chí tìm kiếm
                for (HoaDonDTO hd : list) {
                    KhachHangDTO tenKH = new KhachHangBLL().getKhachHangById(hd.getMaKH());
                    NhanVienDTO nhanvien = new NhanVienBLL().getNhanVienById(hd.getMaNhanVien());

                    boolean isMatch = false;
                    switch (type.toLowerCase()) {
                        case "tất cả":
                            // Lọc tất cả các tiêu chí
                            if (String.valueOf(hd.getMaHoaDon()).toLowerCase().contains(keyword.toLowerCase()) ||
                                    tenKH.getHoTen().toLowerCase().contains(keyword.toLowerCase()) ||
                                    nhanvien.getHoTen().toLowerCase().contains(keyword.toLowerCase())) {
                                isMatch = true;
                            }
                            break;
                        case "mã hóa đơn":
                            if (String.valueOf(hd.getMaHoaDon()).toLowerCase().contains(keyword.toLowerCase())) {
                                isMatch = true;
                            }
                            break;
                        case "khách hàng":
                            if (tenKH.getHoTen().toLowerCase().contains(keyword.toLowerCase())) {
                                isMatch = true;
                            }
                            break;
                        case "nhân viên nhập":
                            if (nhanvien.getHoTen().toLowerCase().contains(keyword.toLowerCase())) {
                                isMatch = true;
                            }
                            break;
                    }

                    // Nếu có kết quả phù hợp, thêm vào danh sách lọc
                    if (isMatch) {
                        filteredList.add(hd);
                    }
                }

                // Cập nhật lại bảng với dữ liệu đã lọc
                tbmtb1.setRowCount(0); // Xóa dữ liệu cũ trên bảng
                for (HoaDonDTO hd : filteredList) {
                    KhachHangDTO kh = new KhachHangBLL().getKhachHangById(hd.getMaKH());
                    NhanVienDTO nhanvien = new NhanVienBLL().getNhanVienById(hd.getMaNhanVien());

                    int tongtien = 0;
                    List<ChiTietHoaDonDTO> cthd = new ChiTietHoaDonBLL().getChiTietTheoMaHoaDon(hd.getMaHoaDon());
                    for (ChiTietHoaDonDTO ct : cthd) {
                        tongtien += ct.getDonGia() * ct.getSoLuong();
                    }
                    tbmtb1.addRow(new Object[] { hd.getMaHoaDon(), kh.getHoTen(), nhanvien.getHoTen(),
                            hd.getNgayXuat(), tongtien });
                }
            }
        });

    }

    private void loaddata() {
        List<HoaDonDTO> list = new HoaDonBLL().getAllHoaDon();
        tbmtb1.setRowCount(0);
        for (HoaDonDTO pn : list) {
            KhachHangDTO kh = new KhachHangBLL().getKhachHangById(pn.getMaKH());
            NhanVienDTO nhanvien = new NhanVienBLL().getNhanVienById(pn.getMaNhanVien());

            int tongtien = 0;
            List<ChiTietHoaDonDTO> ctpn = new ChiTietHoaDonBLL().getChiTietTheoMaHoaDon(pn.getMaHoaDon());
            for (ChiTietHoaDonDTO ct : ctpn) {
                tongtien += ct.getDonGia() * ct.getSoLuong();
            }
            tbmtb1.addRow(new Object[] { pn.getMaHoaDon(), kh.getHoTen(), nhanvien.getHoTen(), pn.getNgayXuat(),
                    tongtien });
        }
    }

}
