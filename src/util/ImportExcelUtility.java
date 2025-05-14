package util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;

public class ImportExcelUtility {

    public static <T> void importFromExcelAndSave(
            File file,
            DataHandler<T> dataHandler,
            DataRowMapper<T> rowMapper) throws Exception {

        try (FileInputStream fileInputStream = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            // Lặp qua tất cả các sheet
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                Sheet sheet = workbook.getSheetAt(sheetIndex);

                // Đọc dữ liệu từ sheet
                for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                    Row row = sheet.getRow(rowIndex);
                    if (row != null) {
                        Object[] rowData = new Object[row.getLastCellNum() - 1];
                        for (int colIndex = 1; colIndex < row.getLastCellNum(); colIndex++) {
                            Cell cell = row.getCell(colIndex);
                            rowData[colIndex - 1] = (cell != null) ? cell.toString() : "";
                        }

                        T dto = rowMapper.mapRow(rowData);
                        if (!dataHandler.handleData(dto)) {
                            throw new Exception("Lỗi khi lưu tại sheet " + sheet.getSheetName() + ", dòng " + (rowIndex + 1));
                        }
                    }
                }
            }
        }
    }

    public static <T> void openAndImportExcel(
            DataHandler<T> dataHandler,
            DataRowMapper<T> rowMapper) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn file Excel");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files", "xlsx"));

        int userSelection = fileChooser.showOpenDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                if (!selectedFile.exists() || !selectedFile.getName().endsWith(".xlsx")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn file Excel hợp lệ (.xlsx).");
                    return;
                }

                importFromExcelAndSave(selectedFile, dataHandler, rowMapper);
                JOptionPane.showMessageDialog(null, "Nhập dữ liệu từ tất cả các sheet thành công!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu: " + ex.getMessage());
            }
        }
    }
}