package com.example.examination.helper;

import com.example.examination.exception.EAException;
import com.example.examination.exception.ErrorMessage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    private static final Logger logger = LoggerFactory.getLogger(ExcelHelper.class);

    private static CellStyle createStyleForTitle(Workbook workbook) {
        Font font = workbook.createFont();
        font.setBold(true);
        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    private static CellStyle createStyleForTitle(SXSSFWorkbook workbook) {
        Font font = workbook.createFont();
        font.setBold(true);
        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    private static CellStyle createNumberCellStyle(SXSSFWorkbook workbook) {
        Font font = workbook.createFont();
        DataFormat fmt = workbook.createDataFormat();
        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setDataFormat(fmt.getFormat("#,##0.00"));
        return style;
    }

//    public static <TData> byte[] createExcelData(Iterable<TData> listData, Class<TData> dataType) throws TMSException {
//        ByteArrayOutputStream fileOut = null;
//        List<String> excludeFields = null;
//        if (dataType.equals(com.tms.dto.GetDoNewResp.class)) {
//            excludeFields = java.util.Arrays.asList("orgId", "warehouseId", "carrierId", "status", "updateby", "createby", "soId", "approvedTime", "expectedDeliveryTime",
//                    "expectedArrivalTime", "ffmReturnCode", "ffmReason", "ffmReasonDetail", "lastmileReturnCode", "lastmileReason", "lastmileReasonDetail", "rescueId", "cpName", "category");
//        } else if (dataType.equals(ShippingExportResponseDTOV2.class)) {
//            excludeFields = getExcludeFieldsExportShipping((Iterable<ShippingExportResponseDTOV2>) listData);
//        }
//
//        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
//            Sheet sheet = workbook.createSheet("sheet1");
//            int rownum = 0;
//            Cell cell;
//            Row row;
//            row = sheet.createRow(rownum);
//
//            Field[] fields = dataType.getDeclaredFields();
//            CellStyle style = createStyleForTitle(workbook);
//            CellStyle numberStyle = createNumberCellStyle(workbook);
//            int cellnum = 0;
//            for (Field field : fields) {
//                if (!CollectionUtils.isEmpty(excludeFields) && excludeFields.contains(field.getName()))
//                    continue;
//
//                cell = row.createCell(cellnum, CellType.STRING);
//                cell.setCellStyle(style);
//                cell.setCellValue(field.getName());
//                field.setAccessible(true);
//                cellnum++;
//
//            }
//
//            java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
//
//            for (TData data : listData) {
//                if (data == null) {
//                    continue;
//                }
//
//                rownum++;
//                row = sheet.createRow(rownum);
//                cellnum = 0;
//
//                for (Field field : fields) {
//                    if (excludeFields != null && excludeFields.contains(field.getName()))
//                        continue;
//
//                    if (field.getType().equals(String.class)) {
//                        cell = row.createCell(cellnum, CellType.STRING);
//                    } else if (field.getType().equals(java.util.Date.class)) {
//                        cell = row.createCell(cellnum, CellType.STRING);
//                    }
//                    else if (field.getType().equals(Double.class)) {
//                        cell = row.createCell(cellnum, CellType.NUMERIC);
//                        cell.setCellStyle(numberStyle);
//                    } else {
//                        cell = row.createCell(cellnum, CellType.NUMERIC);
//                    }
//                    if (field.get(data) != null) {
//                        if (field.get(data) instanceof String) {
//                            cell.setCellValue((String) field.get(data));
//                        } else if (field.get(data) instanceof Long) {
//                            cell.setCellValue((Long) field.get(data));
//                        } else if (field.get(data) instanceof Integer) {
//                            cell.setCellValue((Integer) field.get(data));
//                        } else if (field.get(data) instanceof Double) {
//                            cell.setCellValue((Double) field.get(data));
//                        } else if (field.get(data) instanceof java.util.Date) {
//                            cell.setCellValue(dateFormat.format(field.get(data)));
//                        } else {
//                            cell.setCellValue(field.get(data).toString());
//                        }
//                    } else {
//                        //cell.setCellValue("");
//                    }
//                    cellnum++;
//                }
//            }
//            fileOut = new ByteArrayOutputStream();
//            workbook.write(fileOut);
//            return fileOut.toByteArray();
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            throw new TMSException(ErrorMessage.REPORT_ERROR);
//        } finally {
//            close(fileOut);
//        }
//    }

//    private static List<String> getExcludeFieldsExportShipping(Iterable<ShippingExportResponseDTOV2> listData) {
//        Iterator<ShippingExportResponseDTOV2> iterator = listData.iterator();
//        if (!iterator.hasNext()) {
//            return Collections.emptyList();
//        }
//
//        int orgIdIndo = 9;
//        ShippingExportResponseDTOV2 first = iterator.next();
//        if (first.getOrgId() == orgIdIndo) {
//            return Collections.emptyList();
//        }
//        return Arrays.asList("product5", "qty5", "price5", "product6", "qty6", "price6",
//                "product7", "qty7", "price7", "product8", "qty8", "price8", "product9", "qty9", "price9",
//                "product10", "qty10", "price10", "discountPercentage", "discountAmount", "netAfterDiscount");
//    }

    public static <TData> byte[] createExcelDataNew(Iterable<TData> listData, Class<TData> dataType) throws EAException {
        ByteArrayOutputStream fileOut = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            Sheet sheet = workbook.createSheet("sheet1");
            int rownum = 0;
            Cell cell;
            Row row;
            row = sheet.createRow(rownum);
            Field[] fields = dataType.getDeclaredFields();
            CellStyle style = createStyleForTitle(workbook);
            int cellnum = 0;
            int type = 0;
            for (Field field : fields) {
                cell = row.createCell(cellnum, CellType.STRING);
                cell.setCellValue(field.getName());
                cell.setCellStyle(style);
                field.setAccessible(true);
                cellnum++;
            }
            for (TData data : listData) {
                if (data == null) {
                    continue;
                }

                rownum++;
                row = sheet.createRow(rownum);
                cellnum = 0;

                for (Field field : fields) {
                    type = 0;
                    if (field.getType().equals(String.class)) {
                        cell = row.createCell(cellnum, CellType.STRING);
                    } else if (field.getType().equals(java.util.Date.class)) {
                        cell = row.createCell(cellnum, CellType.STRING);
                    } else {
                        type = 1;
                        cell = row.createCell(cellnum, CellType.NUMERIC);
                    }
                    if (field.get(data) != null) {
                        if (field.get(data) instanceof String) {
                            cell.setCellValue((String) field.get(data));
                        } else if (field.get(data) instanceof Long) {
                            cell.setCellValue((Long) field.get(data));
                        } else if (field.get(data) instanceof Integer) {
                            cell.setCellValue((Integer) field.get(data));
                        } else if (field.get(data) instanceof Double) {
                            cell.setCellValue((Double) field.get(data));
                        } else {
                            cell.setCellValue(field.get(data).toString());
                        }
                    } else {
                        cell.setCellValue("");
                    }
                    cellnum++;
                }
            }
            sheet.autoSizeColumn(0);
            fileOut = new ByteArrayOutputStream();
            workbook.write(fileOut);
            return fileOut.toByteArray();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new EAException(ErrorMessage.REPORT_ERROR);
        } finally

        {
            close(fileOut);
        }

    }

    public static void close(Closeable c) {
        if (c == null) return;
        try {
            c.close();
        } catch (IOException e) {
            //log the exception
        }
    }
}
