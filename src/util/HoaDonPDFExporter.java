package util;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.UnitValue;

import BLL.BUS.SanPhamBLL;
import DTO.HoaDonDTO;
import DTO.ChiTietHoaDonDTO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

public class HoaDonPDFExporter {

    public static void xuatHoaDonPDF(HoaDonDTO hoaDon, List<ChiTietHoaDonDTO> chiTietHoaDonDTO) throws IOException {
        try {
            // Tạo tên file tự động dựa trên mã hóa đơn
            String downloadPath = System.getProperty("user.home") + "/Downloads";
            String filePath = downloadPath + "/hoadon_" + hoaDon.getMaHoaDon() + ".pdf";
            File file = new File(filePath);

            PdfWriter writer = new PdfWriter(file);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            try {
                PdfFont font = PdfFontFactory.createFont("c:/windows/fonts/times.ttf", "Identity-H", true);
                document.setFont(font);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Lỗi tải font, sử dụng font mặc định.");
            }

            // Tiêu đề hóa đơn
            document.add(new Paragraph("HÓA ĐƠN MUA HÀNG").setBold().setFontSize(16));
            document.add(new Paragraph("Mã hóa đơn: " + hoaDon.getMaHoaDon()));
            document.add(new Paragraph("Ngày xuất: " + hoaDon.getNgayXuat()));
            document.add(new Paragraph("Mã khách hàng: " + hoaDon.getMaKH()));
            document.add(new Paragraph("Mã nhân viên: " + hoaDon.getMaNhanVien()));
            document.add(new Paragraph("Mã khuyến mãi: " + hoaDon.getMakhuyenmai()));

            // Thêm khoảng cách trước bảng
            document.add(new Paragraph("\n"));

            // Tạo bảng chi tiết hóa đơn
            Table table = new Table(UnitValue.createPercentArray(new float[] { 10, 20, 15, 15, 15 }));
            table.setWidth(UnitValue.createPercentValue(100));

            // Thêm tiêu đề cột
            table.addCell(new Cell().add(new Paragraph("Mã SP").setBold()));
            table.addCell(new Cell().add(new Paragraph("Tên SP").setBold()));
            table.addCell(new Cell().add(new Paragraph("Số lượng").setBold()));
            table.addCell(new Cell().add(new Paragraph("Đơn giá").setBold()));
            table.addCell(new Cell().add(new Paragraph("Thành tiền").setBold()));

            // Thêm dữ liệu sản phẩm
            int tongTien = 0;
            for (ChiTietHoaDonDTO chiTiet : chiTietHoaDonDTO) {
                int thanhTien = chiTiet.getSoLuong() * chiTiet.getDonGia();
                tongTien += thanhTien;

                table.addCell(new Cell().add(new Paragraph(String.valueOf(chiTiet.getMaSanPham()))));
                table.addCell(new Cell()
                        .add(new Paragraph(new SanPhamBLL().getSanPhamById(chiTiet.getMaSanPham()).getTenSP())));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(chiTiet.getSoLuong()))));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(chiTiet.getDonGia()))));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(thanhTien))));
            }

            document.add(table);
            document.add(new Paragraph("\nTổng tiền: " + tongTien + " VND").setBold());

            document.close();
            JOptionPane.showMessageDialog(null, "Xuất Hóa đơn thành công");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}