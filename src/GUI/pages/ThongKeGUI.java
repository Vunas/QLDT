package GUI.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.category.DefaultCategoryDataset;

import DAO.ThongKeDao;
import DTO.ThongKeDTO;
import GUI.Panel.TopNav;

public class ThongKeGUI extends JPanel {
    private JComponent pnlBot;
    private JTable tbl;
    TopNav topNav;
    ThongKeDTO thongKeDTO;
    List<JButton> itemButtons;
    public int tong_doanh_thu;
    public int tong_san_pham;
    public int tong_khach_hang;
    public int tong_nhan_vien;
    public ChartPanel chartPanelCurrent;

    public ThongKeGUI(TopNav topNav) {
        this.setLayout(new BorderLayout());
        // initComponent(topNav);
        // initComponent();
        itemButtons = new ArrayList<>();
        DisplayDashboardCards();
    }

    // public void initComponent(){
    // this.setLayout(new BorderLayout());
    // Font font = new Font("Arial", Font.BOLD, 20);
    // // Header
    // JPanel jPanelHeader = new JPanel();
    // JLabel lbHeader = new JLabel("TONG HOP - THONG KE", SwingConstants.LEFT);
    // lbHeader.setFont(new Font("Arial", Font.BOLD, 40));
    // lbHeader.setForeground(new Color(0, 153, 153));
    // jPanelHeader.add(lbHeader);

    // JPanel jPanelCenter = new JPanel();
    // jPanelCenter.setLayout(new BorderLayout());

    // JPanel jPanelCenterNorth = new JPanel();
    // jPanelCenterNorth.setLayout(new GridLayout(1, 2));

    // JPanel jPanelDoanhThu = new JPanel();
    // jPanelDoanhThu.setLayout(new BorderLayout());
    // jPanelDoanhThu.setBackground(new Color(36, 167, 224));
    // jPanelDoanhThu.setPreferredSize(new Dimension(200, 400));

    // JLabel lbDoanhThuHomNay = new JLabel("DOANH THU", SwingConstants.CENTER);
    // lbDoanhThuHomNay.setFont(font);
    // lbDoanhThuHomNay.setForeground(Color.WHITE);

    // JLabel lbValueDoanhThu = new JLabel("0", SwingConstants.CENTER);
    // lbValueDoanhThu.setFont(font);
    // lbValueDoanhThu.setForeground(Color.BLACK);

    // jPanelDoanhThu.add(lbDoanhThuHomNay, BorderLayout.NORTH);
    // jPanelDoanhThu.add(lbValueDoanhThu, BorderLayout.CENTER);

    // JPanel jPanelLoiNhuan = new JPanel();
    // jPanelLoiNhuan.setLayout(new BorderLayout());
    // jPanelLoiNhuan.setBackground(new Color(36, 167, 224));
    // jPanelLoiNhuan.setPreferredSize(new Dimension(200, 400));

    // JLabel lbLoiNhuan = new JLabel("LOI NHUAN", SwingConstants.CENTER);
    // lbLoiNhuan.setFont(font);
    // lbLoiNhuan.setForeground(Color.WHITE);

    // JLabel lbValueLoiNhuan = new JLabel("0", SwingConstants.CENTER);
    // lbValueLoiNhuan.setFont(font);
    // lbValueLoiNhuan.setForeground(Color.BLACK);

    // JPanel jPanelFillter = new JPanel(new GridLayout(2, 3, 5, 5));
    // String[] time = {"Hom nay", "Tuan nay", "Thang Nay"};
    // JComboBox<String> comboBox = new JComboBox<>(time);

    // JTextField startDay = new JTextField();
    // JTextField endDay = new JTextField();
    // JButton btnFillter = new JButton("Loc");
    // btnFillter.setBackground(Color.BLACK);
    // btnFillter.setForeground(Color.WHITE);

    // JLabel lbStartDay = new JLabel("Thoi gian bat dau: ");
    // lbStartDay.setForeground(Color.GRAY);
    // JLabel lbEndDay = new JLabel("Thoi gian ket thuc: ");
    // lbEndDay.setForeground(Color.GRAY);

    // jPanelFillter.add(comboBox);
    // jPanelFillter.add(new JLabel(""));
    // jPanelFillter.add(new JLabel(""));
    // jPanelFillter.add(lbStartDay);
    // jPanelFillter.add(lbEndDay);
    // jPanelFillter.add(new JLabel(""));
    // jPanelFillter.add(startDay);
    // jPanelFillter.add(endDay);
    // jPanelFillter.add(btnFillter);

    // jPanelLoiNhuan.add(lbLoiNhuan, BorderLayout.NORTH);
    // jPanelLoiNhuan.add(lbValueLoiNhuan, BorderLayout.CENTER);
    // jPanelLoiNhuan.add(jPanelFillter, BorderLayout.SOUTH);

    // jPanelCenterNorth.add(jPanelDoanhThu);
    // jPanelCenterNorth.add(jPanelLoiNhuan);

    // jPanelCenter.add(jPanelCenterNorth, BorderLayout.NORTH);

    // this.add(jPanelHeader, BorderLayout.NORTH);
    // this.add(jPanelCenter, BorderLayout.CENTER);

    // }

    // this.setLayout(new BorderLayout());

    // // Tiêu đề và khu vực số liệu
    // JPanel topPanel = new JPanel(new GridLayout(1, 2, 20, 10));
    // topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    // // Doanh thu hôm nay
    // JPanel revenuePanel = new JPanel(new BorderLayout());
    // revenuePanel.setBackground(new Color(0, 153, 204));
    // revenuePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // JLabel revenueLabel = new JLabel("DOANH THU HÔM NAY", SwingConstants.CENTER);
    // revenueLabel.setForeground(Color.WHITE);
    // revenueLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
    // JLabel revenueValue = new JLabel("564.000 đ", SwingConstants.CENTER);
    // revenueValue.setForeground(Color.WHITE);
    // revenueValue.setFont(new Font("SansSerif", Font.BOLD, 24));

    // revenuePanel.add(revenueLabel, BorderLayout.NORTH);
    // revenuePanel.add(revenueValue, BorderLayout.CENTER);

    // // Lợi nhuận hôm nay
    // JPanel profitPanel = new JPanel(new BorderLayout());
    // profitPanel.setBackground(new Color(0, 128, 128));
    // profitPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // JLabel profitLabel = new JLabel("Lợi Nhuận", SwingConstants.CENTER);
    // profitLabel.setForeground(Color.WHITE);
    // profitLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
    // JLabel profitValue = new JLabel("190.500 đ", SwingConstants.CENTER);
    // profitValue.setForeground(Color.WHITE);
    // profitValue.setFont(new Font("SansSerif", Font.BOLD, 24));

    // profitPanel.add(profitLabel, BorderLayout.NORTH);
    // profitPanel.add(profitValue, BorderLayout.CENTER);

    // topPanel.add(revenuePanel);
    // topPanel.add(profitPanel);

    // // Biểu đồ doanh thu (giả lập)
    // // JPanel chartPanel = new JPanel() {
    // // @Override
    // // protected void paintComponent(Graphics g) {
    // // super.paintComponent(g);
    // // // Vẽ trục
    // // g.setColor(Color.LIGHT_GRAY);
    // // for (int i = 0; i <= 6; i++) {
    // // g.drawLine(50, 300 - i * 40, 500, 300 - i * 40);
    // // }
    // // // Vẽ cột dữ liệu
    // // g.setColor(Color.RED);
    // // g.fillRect(100, 290, 50, 10); // ngày 08
    // // g.fillRect(200, 250, 50, 50); // ngày 09
    // // g.fillRect(300, 80, 50, 220); // ngày 10
    // // }
    // // };
    // // chartPanel.setPreferredSize(new Dimension(600, 320));
    // // chartPanel.setBorder(BorderFactory.createTitledBorder("BIỂU ĐỒ THỐNG KÊ
    // DOANH THU"));

    // // Thêm các thành phần vào panel chính
    // add(topPanel, BorderLayout.NORTH);
    // // add(chartPanel, BorderLayout.CENTER);
    // }

    // Tạo JFrame để chạy thử
    // rpublic static void main(String[] args) {
    // JFrame frame = new JFrame("TỔNG HỢP - THỐNG KÊ");
    // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // frame.setSize(700, 600);
    // fame.setLocationRelativeTo(null);
    // frame.setContentPane(new ThongKePanel());
    // frame.setVisible(true);
    // }
    // }

    // private void initComponent(TopNav topNav) {
    // this.topNav = topNav;
    // String[] itemFindFor = { "Tất Cả" };
    // topNav.setItemComboBox(itemFindFor);

    // // Bottom Panel
    // pnlBot = new JPanel(new BorderLayout());
    // pnlBot.setPreferredSize(new Dimension(0, 500));

    // // Create JTable
    // tbl = new JTable();
    // tbl.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    // tbl.setRowHeight(35);
    // tbl.setFocusable(false);

    // // Style the table header
    // JTableHeader header = tbl.getTableHeader();
    // header.setPreferredSize(new Dimension(0, 40));
    // header.setBackground(new Color(100, 149, 237)); // Cornflower Blue
    // header.setForeground(Color.WHITE);
    // header.setFont(new Font("Segoe UI", Font.BOLD, 14));

    // // Add JScrollPane containing the table
    // JScrollPane scrollPane = new JScrollPane(tbl);
    // scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    // scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    // pnlBot.setBorder(new EmptyBorder(10, 15, 10, 15));
    // pnlBot.add(scrollPane, BorderLayout.CENTER);

    // // Set layout for TaiKhoanGUI
    // this.setLayout(new BorderLayout());
    // // this.add(pnlBot, BorderLayout.CENTER);
    // }

    public void DisplayDashboardCards() {
        ThongKeDao thongKeDao = new ThongKeDao();
        List<ThongKeDTO> thongKeDoanhThu = thongKeDao.ThongKeDoanhThu();
        tong_doanh_thu = 0;
        List<ThongKeDTO> thongkeSanPham = thongKeDao.ThongKeSanPham();
        tong_san_pham = 0;
        tong_khach_hang = thongKeDao.ThongKeTongKhachHang();
        tong_nhan_vien = thongKeDao.ThongKeTongNhanVien();
        for (ThongKeDTO dto : thongKeDoanhThu) {
            tong_doanh_thu += dto.gettongGiaTri();
        }
        for (ThongKeDTO dto : thongkeSanPham) {
            tong_san_pham += dto.getSoluong();
        }

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.setBackground(new java.awt.Color(250, 250, 250));// Màu nền nhạt toàn giao diện

        JLabel lbTitle = new JLabel("TỔNG HỢP - THỐNG KÊ", JLabel.CENTER);
        lbTitle.setFont(new Font("Arial", Font.BOLD, 40));
        lbTitle.setBorder(new EmptyBorder(20, 0, 20, 0));

        JPanel jPanelDashboardCards = new JPanel();
        jPanelDashboardCards.setLayout(new GridLayout(1, 4, 15, 0));
        jPanelDashboardCards.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        jPanelDashboardCards.setBackground(new java.awt.Color(250, 250, 250));

        JPanel jPanelTongDoanhThu = createDashboardCards("Tổng Doanh Thu", tong_doanh_thu);
        JPanel jPanelTongKhachHang = createDashboardCards("Tổng Khach Hang", tong_khach_hang);
        JPanel jPanelTongSanPham = createDashboardCards("Tổng Sản Phẩm", tong_san_pham);
        JPanel jPanelTongNhanVien = createDashboardCards("Tổng nhân viên", tong_nhan_vien);

        jPanelDashboardCards.add(jPanelTongDoanhThu);
        jPanelDashboardCards.add(jPanelTongKhachHang);
        jPanelDashboardCards.add(jPanelTongSanPham);
        jPanelDashboardCards.add(jPanelTongNhanVien);

        ChartPanel chartPanelDoanhThu = createChartDoanhThu();
        ChartPanel chartPanelKhachHang = createChartKhachHang();
        ChartPanel chartPanelSanPham = createChartSanPham();
        ChartPanel chartPanelNhanVien = createChartNhanVien();
        chartPanelCurrent = chartPanelDoanhThu;
        this.add(chartPanelDoanhThu, BorderLayout.CENTER);
        itemButtons.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThongKeGUI.this.remove(chartPanelCurrent);
                ThongKeGUI.this.add(chartPanelDoanhThu, BorderLayout.CENTER);
                chartPanelCurrent = chartPanelDoanhThu;
                ThongKeGUI.this.revalidate(); // Cập nhật lại bố cục giao diện
                ThongKeGUI.this.repaint();
            }
        });

        itemButtons.get(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThongKeGUI.this.remove(chartPanelCurrent);
                ThongKeGUI.this.add(chartPanelKhachHang, BorderLayout.CENTER);
                chartPanelCurrent = chartPanelKhachHang;
                ThongKeGUI.this.revalidate(); // Cập nhật lại bố cục giao diện
                ThongKeGUI.this.repaint();
            }
        });

        itemButtons.get(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThongKeGUI.this.remove(chartPanelCurrent);
                ThongKeGUI.this.add(chartPanelSanPham, BorderLayout.CENTER);
                chartPanelCurrent = chartPanelSanPham;
                ThongKeGUI.this.revalidate(); // Cập nhật lại bố cục giao diện
                ThongKeGUI.this.repaint();
            }
        });

        itemButtons.get(3).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThongKeGUI.this.remove(chartPanelCurrent);
                ThongKeGUI.this.add(chartPanelNhanVien, BorderLayout.CENTER);
                chartPanelCurrent = chartPanelNhanVien;
                ThongKeGUI.this.revalidate(); // Cập nhật lại bố cục giao diện
                ThongKeGUI.this.repaint();
            }
        });
        jPanelDashboardCards.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel jPanelFilter = new JPanel();
        jPanelFilter.setBorder(new EmptyBorder(10, 20, 10, 20));
        jPanelFilter.setBackground(new java.awt.Color(250, 250, 250));

        JLabel lbStartTime = new JLabel("Thời Gian Bắt Đầu");
        lbStartTime.setFont(new Font("Arial", Font.PLAIN, 16));
        JDatePickerImpl startDatePicker = createDatePicker();
        JLabel lbEndTime = new JLabel("Thời Gian Kết Thúc");
        lbEndTime.setFont(new Font("Arial", Font.PLAIN, 16));
        JDatePickerImpl endDatePicker = createDatePicker();

        JButton btnFilter = new JButton("Lọc");
        btnFilter.setFont(new Font("Arial", Font.BOLD, 16));
        btnFilter.setBackground(new Color(100, 149, 237));
        btnFilter.setForeground(java.awt.Color.WHITE);
        btnFilter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // LocalDate start = (LocalDate) startDatePicker.getModel().getValue();
                // LocalDate end = (LocalDate) endDatePicker.getModel().getValue();
                java.util.Date startUtilDate = (java.util.Date) startDatePicker.getModel().getValue();
                java.util.Date endUtilDate = (java.util.Date) endDatePicker.getModel().getValue();

                // Chuyển đổi từ java.util.Date thành LocalDate
                LocalDate start = startUtilDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                LocalDate end = endUtilDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                ThongKeDTO thongKeDTO = new ThongKeDTO(start, end);
                if (chartPanelCurrent.equals(chartPanelDoanhThu)) {
                    ChartPanel chartPanelDoanhThuByFilter = createChartDoanhThuByFilter(thongKeDTO);
                    ThongKeGUI.this.add(chartPanelDoanhThuByFilter, BorderLayout.CENTER);
                    ThongKeGUI.this.remove(chartPanelCurrent);
                    chartPanelCurrent = chartPanelDoanhThuByFilter;
                } else if (chartPanelCurrent.equals(chartPanelKhachHang)) {
                    ChartPanel chartPanelKhachHangByFilter = createChartKhachHangByFilter(thongKeDTO);
                    ThongKeGUI.this.add(chartPanelKhachHangByFilter, BorderLayout.CENTER);
                    ThongKeGUI.this.remove(chartPanelCurrent);
                    chartPanelCurrent = chartPanelKhachHangByFilter;
                } else if (chartPanelCurrent.equals(chartPanelSanPham)) {
                    ChartPanel chartPanelSanPhamByFilter = createChartSanPhamByFilter(thongKeDTO);
                    ThongKeGUI.this.add(chartPanelSanPhamByFilter, BorderLayout.CENTER);
                    ThongKeGUI.this.remove(chartPanelCurrent);
                    chartPanelCurrent = chartPanelSanPhamByFilter;
                } else {
                    ChartPanel chartPanelNhanVienByFilter = createChartNhanVienByFilter(thongKeDTO);
                    ThongKeGUI.this.add(chartPanelNhanVienByFilter, BorderLayout.CENTER);
                    ThongKeGUI.this.remove(chartPanelCurrent);
                    chartPanelCurrent = chartPanelNhanVienByFilter;
                }
                ThongKeGUI.this.revalidate(); // Cập nhật lại bố cục giao diện
                ThongKeGUI.this.repaint();
            }

        });

        jPanelFilter.add(lbStartTime);
        jPanelFilter.add(startDatePicker);
        jPanelFilter.add(lbEndTime);
        jPanelFilter.add(endDatePicker);
        jPanelFilter.add(btnFilter);

        jPanel.add(lbTitle, BorderLayout.NORTH);
        jPanel.add(jPanelDashboardCards, BorderLayout.CENTER);
        jPanel.add(jPanelFilter, BorderLayout.SOUTH);

        this.add(jPanel, BorderLayout.NORTH);
        this.revalidate();
        this.repaint();
    }

    private JDatePickerImpl createDatePicker() {
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.day", "Hôm Nay");
        p.put("text.month", "Tháng");
        p.put("text.year", "Năm");

        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        return new JDatePickerImpl(datePanel, new DateLabelFormatter());
    }

    public class DateLabelFormatter extends AbstractFormatter {
        private final String datePattern = "dd-MM-yyyy";
        private final SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }
            return "";
        }
    }

    public JPanel createDashboardCards(String title, int value) {
        JPanel jPanelDashboardCard = new JPanel();
        // jPanelDashboardCard.setSize(150, 150);
        jPanelDashboardCard.setLayout(new BorderLayout());
        jPanelDashboardCard.setBackground(new Color(100, 149, 237));
        jPanelDashboardCard.setForeground(Color.WHITE);
        jPanelDashboardCard.setPreferredSize(new Dimension(120, 120));
        // jPanelDashboardCard.setBorder(BorderFactory.createCompoundBorder(
        // BorderFactory.createEmptyBorder(10, 10, 10, 10),
        // BorderFactory.createLineBorder(new Color(255, 70, 130, 30), 5)));
        jPanelDashboardCard.setOpaque(true);
        JLabel lbTitle = new JLabel(title, SwingConstants.CENTER);
        lbTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
        lbTitle.setForeground(Color.WHITE);
        // lbTitle.setBackground(Color.WHITE);

        JLabel lbValue = new JLabel(String.valueOf(value), SwingConstants.CENTER);
        lbValue.setFont(new Font("SansSerif", Font.BOLD, 28));
        lbValue.setForeground(Color.WHITE);
        // lbValue.setBackground(Color.WHITE);

        JButton btnDetail = new JButton();
        btnDetail.setText("Xem Chi Tiết");
        btnDetail.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnDetail.setForeground(Color.BLACK);
        // btnDetail.setBackground(Color.WHITE);
        btnDetail.setFocusPainted(false);
        btnDetail.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnDetail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDetail.setPreferredSize(new Dimension(120, 40));
        itemButtons.add(btnDetail);

        jPanelDashboardCard.add(lbTitle, BorderLayout.NORTH);
        jPanelDashboardCard.add(lbValue, BorderLayout.CENTER);
        jPanelDashboardCard.add(btnDetail, BorderLayout.SOUTH);

        return jPanelDashboardCard;
    }

    // Khách Hàng Mua Hàng Nhiều Nhất
    // Column
    public ChartPanel createChartKhachHang() {
        ThongKeDao thongKeDao = new ThongKeDao();
        List<ThongKeDTO> list = thongKeDao.ThongKeKhachHang();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (ThongKeDTO thongKeDTO : list) {
            dataset.addValue(thongKeDTO.getSoluong(), "Tống Giá Trị Mua Hàng", thongKeDTO.getTen());
        }
        JFreeChart chart = ChartFactory.createBarChart3D("Thống Kê Khách Hàng Mua Nhiều Nhất", "Tên Khách Hàng",
                "Tống Giá Trị Mua Hàng", dataset, PlotOrientation.VERTICAL, // Chiều biểu đồ
                true, // Hiển thị chú thích
                true,
                false);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setOutlinePaint(Color.DARK_GRAY); // Viền của biểu đồ
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY); // Đường lưới ngang
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY); // Đường lưới dọc
        plot.setBackgroundPaint(Color.WHITE);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(248, 113, 113));
        renderer.setShadowXOffset(4);
        renderer.setShadowYOffset(4);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 300));
        chartPanel.setBorder(new EmptyBorder(10, 15, 10, 15));

        return chartPanel;
    }

    public ChartPanel createChartKhachHangByFilter(ThongKeDTO thongke) {
        ThongKeDao thongKeDao = new ThongKeDao();
        List<ThongKeDTO> list = thongKeDao.ThongKeKhachHangByFilter(thongke);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (ThongKeDTO thongKeDTO : list) {
            dataset.addValue(thongKeDTO.getSoluong(), "Tống Giá Trị Mua Hàng", thongKeDTO.getTen());
        }
        JFreeChart chart = ChartFactory.createBarChart3D("Thống Kê Khách Hàng Mua Nhiều Nhất", "Tên Khách Hàng",
                "Tống Giá Trị Mua Hàng", dataset, PlotOrientation.VERTICAL, // Chiều biểu đồ
                true, // Hiển thị chú thích
                true,
                false);
                CategoryPlot plot = chart.getCategoryPlot();
        plot.setOutlinePaint(Color.DARK_GRAY); // Viền của biểu đồ
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY); // Đường lưới ngang
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY); // Đường lưới dọc
        plot.setBackgroundPaint(Color.WHITE);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(248, 113, 113));
        renderer.setShadowXOffset(4);
        renderer.setShadowYOffset(4);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 300));
        chartPanel.setBorder(new EmptyBorder(10, 15, 10, 15));

        return chartPanel;
    }

    // Sản Phẩm Bán Chạy Nhất
    // Pie
    public ChartPanel createChartSanPham() {
        ThongKeDao thongKeDao = new ThongKeDao();
        List<ThongKeDTO> list = thongKeDao.ThongKeSanPham();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (ThongKeDTO dto : list) {
            dataset.addValue(dto.getSoluong(), "Số Lượng Bán Ra", dto.getTen());
        }

        JFreeChart chart = ChartFactory.createBarChart3D("Thóng Kê Sản Phẩm Bán Chạy", "Tên Sản Phẩm", "Số Lượng",
                dataset, PlotOrientation.VERTICAL, // Chiều biểu đồ
                true, // Hiển thị chú thích
                true,
                false);
                CategoryPlot plot = chart.getCategoryPlot();
                plot.setOutlinePaint(Color.DARK_GRAY); // Viền của biểu đồ
                plot.setRangeGridlinePaint(Color.LIGHT_GRAY); // Đường lưới ngang
                plot.setDomainGridlinePaint(Color.LIGHT_GRAY); // Đường lưới dọc
                plot.setBackgroundPaint(Color.WHITE);
        
                BarRenderer renderer = (BarRenderer) plot.getRenderer();
                renderer.setSeriesPaint(0, new Color(248, 113, 113));
                renderer.setShadowXOffset(4);
                renderer.setShadowYOffset(4);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 300));
        chartPanel.setBorder(new EmptyBorder(10, 15, 10, 15));

        return chartPanel;
    }

    public ChartPanel createChartSanPhamByFilter(ThongKeDTO thongke) {
        ThongKeDao thongKeDao = new ThongKeDao();
        List<ThongKeDTO> list = thongKeDao.ThongKeSanPhamByFilter(thongke);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (ThongKeDTO dto : list) {
            dataset.addValue(dto.getSoluong(), "Số Lượng Bán Ra", dto.getTen());
        }

        JFreeChart chart = ChartFactory.createBarChart3D("Thóng Kê Sản Phẩm Bán Chạy", "Tên Sản Phẩm", "Số Lượng",
                dataset, PlotOrientation.VERTICAL, // Chiều biểu đồ
                true, // Hiển thị chú thích
                true,
                false);
                CategoryPlot plot = chart.getCategoryPlot();
                plot.setOutlinePaint(Color.DARK_GRAY); // Viền của biểu đồ
                plot.setRangeGridlinePaint(Color.LIGHT_GRAY); // Đường lưới ngang
                plot.setDomainGridlinePaint(Color.LIGHT_GRAY); // Đường lưới dọc
                plot.setBackgroundPaint(Color.WHITE);
        
                BarRenderer renderer = (BarRenderer) plot.getRenderer();
                renderer.setSeriesPaint(0, new Color(248, 113, 113));
                renderer.setShadowXOffset(4);
                renderer.setShadowYOffset(4);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 300));
        chartPanel.setBorder(new EmptyBorder(10, 15, 10, 15));

        return chartPanel;
    }

    // Nhân Viên Bán Được Hàng Nhiều Nhất
    public ChartPanel createChartNhanVien() {
        ThongKeDao thongKeDao = new ThongKeDao();
        List<ThongKeDTO> thongKeDTO = thongKeDao.ThongKeNhanVien();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (ThongKeDTO dto : thongKeDTO) {
            dataset.addValue(dto.getSoluong(), "Tống Đơn Hàng Của Nhân Viên Bán Được", dto.getTen());
        }
        JFreeChart chart = ChartFactory.createBarChart3D("Thống Kê Nhân Viên Bán Được Hàng Nhiều Nhất", "Tên Nhân Viên",
                "Số Lượng Bán Ra", dataset);
                CategoryPlot plot = chart.getCategoryPlot();
        plot.setOutlinePaint(Color.DARK_GRAY); // Viền của biểu đồ
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY); // Đường lưới ngang
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY); // Đường lưới dọc
        plot.setBackgroundPaint(Color.WHITE);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(248, 113, 113));
        renderer.setShadowXOffset(4);
        renderer.setShadowYOffset(4);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 300));
        chartPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        
        return chartPanel;
    }

    public ChartPanel createChartNhanVienByFilter(ThongKeDTO thongke) {
        ThongKeDao thongKeDao = new ThongKeDao();
        List<ThongKeDTO> thongKeDTO = thongKeDao.ThongKeNhanVienByFilter(thongke);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (ThongKeDTO dto : thongKeDTO) {
            dataset.addValue(dto.getSoluong(), "Tống Đơn Hàng Của Nhân Viên Bán Được", dto.getTen());
        }
        JFreeChart chart = ChartFactory.createBarChart3D("Thống Kê Nhân Viên Bán Được Hàng Nhiều Nhất", "Tên Nhân Viên",
                "Số Lượng Sản Phẩm", dataset);
                CategoryPlot plot = chart.getCategoryPlot();
        plot.setOutlinePaint(Color.DARK_GRAY); // Viền của biểu đồ
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY); // Đường lưới ngang
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY); // Đường lưới dọc
        plot.setBackgroundPaint(Color.WHITE);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(248, 113, 113));
        renderer.setShadowXOffset(4);
        renderer.setShadowYOffset(4);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 300));
        chartPanel.setBorder(new EmptyBorder(10, 15, 10, 15));

        return chartPanel;
    }

    // Tổng Doanh Thu Theo Ngày ( Tháng, Năm )
    public ChartPanel createChartDoanhThu() {
        ThongKeDao thongKeDao = new ThongKeDao();
        List<ThongKeDTO> thongKeDTO = thongKeDao.ThongKeDoanhThu();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (ThongKeDTO dto : thongKeDTO) {
            dataset.addValue(dto.gettongGiaTri(), "Tổng Doanh Thu", dto.getNgayXuat());
        }

        JFreeChart lineChart = ChartFactory.createLineChart("Thống Kê Doanh Thu", "Ngày", "Doanh Thu", dataset);
CategoryPlot plot = lineChart.getCategoryPlot();
plot.setOutlinePaint(Color.DARK_GRAY);               // Viền của biểu đồ
plot.setRangeGridlinePaint(Color.LIGHT_GRAY);        // Đường lưới ngang
// plot.setDomainGridlinePaint(Color.LIGHT_GRAY);       // Đường lưới dọc
plot.setBackgroundPaint(Color.WHITE); 
LineAndShapeRenderer renderer = new LineAndShapeRenderer();
renderer.setSeriesPaint(0, new Color(248, 113, 113));
plot.setRenderer(renderer);
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new Dimension(600, 300));
        chartPanel.setBorder(new EmptyBorder(10, 15, 10, 15));

        return chartPanel;
    }

    public ChartPanel createChartDoanhThuByFilter(ThongKeDTO thongke) {
        ThongKeDao thongKeDao = new ThongKeDao();
        List<ThongKeDTO> thongKeDTO = thongKeDao.ThongKeDoanhThuByFilter(thongke);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (ThongKeDTO dto : thongKeDTO) {
            dataset.addValue(dto.gettongGiaTri(), "Tổng Doanh Thu", dto.getNgayXuat());
        }

        JFreeChart lineChart = ChartFactory.createLineChart("Thống Kê Doanh Thu", "Ngày", "Doanh Thu", dataset);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new Dimension(600, 300));
        chartPanel.setBorder(new EmptyBorder(10, 15, 10, 15));

        return chartPanel;
    }

}
