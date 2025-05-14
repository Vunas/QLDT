package GUI.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
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

import BLL.BUS.ThongKeBLL;
import DTO.ThongKeDTO;
import GUI.Panel.TopNav;

public class ThongKeGUI extends JPanel {
    TopNav topNav;
    List<JButton> itemButtons;
    public int tong_doanh_thu;
    public int tong_san_pham;
    public int tong_khach_hang;
    public int tong_nhan_vien;
    public ChartPanel chartPanelCurrent;
    ThongKeBLL thongKeBLL;
    public ChartPanel chartPanelSanPhamByFilter;
    public ChartPanel chartPanelKhachHangByFilter;
    public ChartPanel chartPanelDoanhThuByFilter;
    public ChartPanel chartPanelNhanVienByFilter;

    public ThongKeGUI(TopNav topNav) {
        this.setLayout(new BorderLayout());
        itemButtons = new ArrayList<>();
        this.thongKeBLL = new ThongKeBLL();
        initThongKe();
    }

    public void initThongKe() {
        DisplayDashboardCards();
    }

    public void DisplayDashboardCards() {
        List<ThongKeDTO> thongKeDoanhThu = thongKeBLL.thongKeDoanhThu();
        tong_doanh_thu = 0;
        List<ThongKeDTO> thongkeSanPham = thongKeBLL.thongKeSanPham();
        tong_san_pham = 0;
        tong_khach_hang = thongKeBLL.thongKeTongKhachHang();
        tong_nhan_vien = thongKeBLL.thongKeTongNhanVien();
        for (ThongKeDTO dto : thongKeDoanhThu) {
            tong_doanh_thu += dto.gettongGiaTri();
        }
        for (ThongKeDTO dto : thongkeSanPham) {
            tong_san_pham += dto.getSoluong();
        }

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.setBackground(new java.awt.Color(250, 250, 250));

        JLabel lbTitle = new JLabel("TỔNG HỢP - THỐNG KÊ", JLabel.CENTER);
        lbTitle.setFont(new Font("Arial", Font.BOLD, 40));
        lbTitle.setBorder(new EmptyBorder(20, 0, 20, 0));

        JPanel jPanelDashboardCards = new JPanel();
        jPanelDashboardCards.setLayout(new GridLayout(1, 4, 15, 0));
        jPanelDashboardCards.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        jPanelDashboardCards.setBackground(new java.awt.Color(250, 250, 250));

        JPanel jPanelTongDoanhThu = createDashboardCards("Tổng Doanh Thu", tong_doanh_thu);
        JPanel jPanelTongKhachHang = createDashboardCards("Tổng Khách Hàng", tong_khach_hang);
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
                ThongKeGUI.this.revalidate();
                ThongKeGUI.this.repaint();
            }
        });

        itemButtons.get(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThongKeGUI.this.remove(chartPanelCurrent);
                ThongKeGUI.this.add(chartPanelKhachHang, BorderLayout.CENTER);
                chartPanelCurrent = chartPanelKhachHang;
                ThongKeGUI.this.revalidate();
                ThongKeGUI.this.repaint();
            }
        });

        itemButtons.get(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThongKeGUI.this.remove(chartPanelCurrent);
                ThongKeGUI.this.add(chartPanelSanPham, BorderLayout.CENTER);
                chartPanelCurrent = chartPanelSanPham;
                ThongKeGUI.this.revalidate();
                ThongKeGUI.this.repaint();
            }
        });

        itemButtons.get(3).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThongKeGUI.this.remove(chartPanelCurrent);
                ThongKeGUI.this.add(chartPanelNhanVien, BorderLayout.CENTER);
                chartPanelCurrent = chartPanelNhanVien;
                ThongKeGUI.this.revalidate();
                ThongKeGUI.this.repaint();
            }
        });

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
        JButton btnReset = new JButton("Làm Mới");
        btnFilter.setFont(new Font("Arial", Font.BOLD, 16));
        btnFilter.setBackground(new Color(100, 149, 237));
        btnFilter.setForeground(java.awt.Color.WHITE);
        btnReset.setFont(new Font("Arial", Font.BOLD, 16));
        btnReset.setBackground(new Color(100, 149, 237));
        btnReset.setForeground(java.awt.Color.WHITE);
        btnFilter.addActionListener(new ActionListener() {

            

            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.Date startUtilDate = (java.util.Date) startDatePicker.getModel().getValue();
                java.util.Date endUtilDate = (java.util.Date) endDatePicker.getModel().getValue();

                LocalDate start = startUtilDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                LocalDate end = endUtilDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                ThongKeDTO thongKeDTO = new ThongKeDTO(start, end);
                if (chartPanelCurrent.equals(chartPanelDoanhThu)) {
                    chartPanelDoanhThuByFilter = createChartDoanhThuByFilter(thongKeDTO);
                    ThongKeGUI.this.add(chartPanelDoanhThuByFilter, BorderLayout.CENTER);
                    ThongKeGUI.this.remove(chartPanelCurrent);
                    chartPanelCurrent = chartPanelDoanhThuByFilter;
                } else if (chartPanelCurrent.equals(chartPanelKhachHang)) {
                    chartPanelKhachHangByFilter = createChartKhachHangByFilter(thongKeDTO);
                    ThongKeGUI.this.add(chartPanelKhachHangByFilter, BorderLayout.CENTER);
                    ThongKeGUI.this.remove(chartPanelCurrent);
                    chartPanelCurrent = chartPanelKhachHangByFilter;
                } else if (chartPanelCurrent.equals(chartPanelSanPham)) {
                    chartPanelSanPhamByFilter = createChartSanPhamByFilter(thongKeDTO);
                    ThongKeGUI.this.add(chartPanelSanPhamByFilter, BorderLayout.CENTER);
                    ThongKeGUI.this.remove(chartPanelCurrent);
                    chartPanelCurrent = chartPanelSanPhamByFilter;
                } else {
                    chartPanelNhanVienByFilter = createChartNhanVienByFilter(thongKeDTO);
                    ThongKeGUI.this.add(chartPanelNhanVienByFilter, BorderLayout.CENTER);
                    ThongKeGUI.this.remove(chartPanelCurrent);
                    chartPanelCurrent = chartPanelNhanVienByFilter;
                }
                ThongKeGUI.this.revalidate();
                ThongKeGUI.this.repaint();
            }

        });
        Map<ChartPanel, ChartPanel> mapFilter = new HashMap<>();
        mapFilter.put(chartPanelDoanhThuByFilter, chartPanelDoanhThu);
        mapFilter.put(chartPanelSanPhamByFilter, chartPanelSanPham);
        mapFilter.put(chartPanelKhachHangByFilter, chartPanelKhachHang);
        mapFilter.put(chartPanelNhanVienByFilter, chartPanelNhanVien);
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                startDatePicker.getModel().setValue(null);
                endDatePicker.getModel().setValue(null);
                ThongKeGUI.this.remove(chartPanelCurrent);
                chartPanelCurrent = mapFilter.getOrDefault(chartPanelCurrent, chartPanelDoanhThu);
                ThongKeGUI.this.add(chartPanelCurrent, BorderLayout.CENTER);
                ThongKeGUI.this.revalidate();
                ThongKeGUI.this.repaint();
            }

        });

        jPanelFilter.add(lbStartTime);
        jPanelFilter.add(startDatePicker);
        jPanelFilter.add(lbEndTime);
        jPanelFilter.add(endDatePicker);
        jPanelFilter.add(btnFilter);
        jPanelFilter.add(btnReset);

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
        jPanelDashboardCard.setLayout(new BorderLayout());
        jPanelDashboardCard.setBackground(new Color(100, 149, 237));
        jPanelDashboardCard.setForeground(Color.WHITE);
        jPanelDashboardCard.setPreferredSize(new Dimension(120, 120));

        jPanelDashboardCard.setOpaque(true);
        JLabel lbTitle = new JLabel(title, SwingConstants.CENTER);
        lbTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
        lbTitle.setForeground(Color.WHITE);

        JLabel lbValue = new JLabel(String.valueOf(value), SwingConstants.CENTER);
        lbValue.setFont(new Font("SansSerif", Font.BOLD, 28));
        lbValue.setForeground(Color.WHITE);

        JButton btnDetail = new JButton();
        btnDetail.setText("Xem Chi Tiết");
        btnDetail.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnDetail.setForeground(Color.BLACK);
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

    public ChartPanel createChartKhachHang() {
        List<ThongKeDTO> list = thongKeBLL.thongKeKhachHang();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (ThongKeDTO thongKeDTO : list) {
            dataset.addValue(thongKeDTO.getSoluong(), "Tổng Giá Trị Mua Hàng", thongKeDTO.getTen());
        }
        JFreeChart chart = ChartFactory.createBarChart3D("Thống Kê Khách Hàng Mua Nhiều Nhất", "Tên Khách Hàng",
                "Tổng Giá Trị Mua Hàng", dataset, PlotOrientation.VERTICAL,
                true,
                true,
                false);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setOutlinePaint(Color.DARK_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
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
        List<ThongKeDTO> list = thongKeBLL.thongKeKhachHangByFilter(thongke);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (ThongKeDTO thongKeDTO : list) {
            dataset.addValue(thongKeDTO.getSoluong(), "Tổng Giá Trị Mua Hàng", thongKeDTO.getTen());
        }
        JFreeChart chart = ChartFactory.createBarChart3D("Thống Kê Khách Hàng Mua Nhiều Nhất", "Tên Khách Hàng",
                "Tổng Giá Trị Mua Hàng", dataset, PlotOrientation.VERTICAL,
                true,
                true,
                false);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setOutlinePaint(Color.DARK_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
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

    public ChartPanel createChartSanPham() {

        List<ThongKeDTO> list = thongKeBLL.thongKeSanPham();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (ThongKeDTO dto : list) {
            dataset.addValue(dto.getSoluong(), "Số Lượng Bán Ra", dto.getTen());
        }

        JFreeChart chart = ChartFactory.createBarChart3D("Thống Kê Sản Phẩm Bán Chạy", "Tên Sản Phẩm", "Số Lượng",
                dataset, PlotOrientation.VERTICAL, // Chiều biểu đồ
                true, // Hiển thị chú thích
                true,
                false);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setOutlinePaint(Color.DARK_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
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

        List<ThongKeDTO> list = thongKeBLL.thongKeSanPhamByFilter(thongke);
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
        plot.setOutlinePaint(Color.DARK_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
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

    public ChartPanel createChartNhanVien() {

        List<ThongKeDTO> thongKeDTO = thongKeBLL.thongKeNhanVien();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (ThongKeDTO dto : thongKeDTO) {
            dataset.addValue(dto.getSoluong(), "Tống Đơn Hàng Của Nhân Viên Bán Được", dto.getTen());
        }
        JFreeChart chart = ChartFactory.createBarChart3D("Thống Kê Nhân Viên Bán Được Hàng Nhiều Nhất", "Tên Nhân Viên",
                "Số Lượng Bán Ra", dataset);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setOutlinePaint(Color.DARK_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
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

        List<ThongKeDTO> thongKeDTO = thongKeBLL.thongKeNhanVienByFilter(thongke);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (ThongKeDTO dto : thongKeDTO) {
            dataset.addValue(dto.getSoluong(), "Tống Đơn Hàng Của Nhân Viên Bán Được", dto.getTen());
        }
        JFreeChart chart = ChartFactory.createBarChart3D("Thống Kê Nhân Viên Bán Được Hàng Nhiều Nhất", "Tên Nhân Viên",
                "Số Lượng Sản Phẩm", dataset);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setOutlinePaint(Color.DARK_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
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

    public ChartPanel createChartDoanhThu() {

        List<ThongKeDTO> thongKeDTO = thongKeBLL.thongKeDoanhThu();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (ThongKeDTO dto : thongKeDTO) {
            dataset.addValue(dto.gettongGiaTri(), "Tổng Doanh Thu", dto.getNgayXuat());
        }

        JFreeChart lineChart = ChartFactory.createLineChart("Thống Kê Doanh Thu", "Ngày", "Doanh Thu", dataset);
        CategoryPlot plot = lineChart.getCategoryPlot();
        plot.setOutlinePaint(Color.DARK_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);

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
        List<ThongKeDTO> thongKeDTO = thongKeBLL.thongKeDoanhThuByFilter(thongke);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (ThongKeDTO dto : thongKeDTO) {
            dataset.addValue(dto.gettongGiaTri(), "Tổng Doanh Thu", dto.getNgayXuat());
        }

        JFreeChart lineChart = ChartFactory.createLineChart("Thống Kê Doanh Thu", "Ngày", "Doanh Thu", dataset);
        CategoryPlot plot = lineChart.getCategoryPlot();
        plot.setOutlinePaint(Color.DARK_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);

        plot.setBackgroundPaint(Color.WHITE);
        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        renderer.setSeriesPaint(0, new Color(248, 113, 113));
        plot.setRenderer(renderer);
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new Dimension(600, 300));
        chartPanel.setBorder(new EmptyBorder(10, 15, 10, 15));

        return chartPanel;
    }
}
