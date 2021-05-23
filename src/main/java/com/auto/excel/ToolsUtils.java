package com.auto.excel;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;

/**
 * @author : jihai
 * @date : 2021/1/6
 * @description :
 */
public class ToolsUtils {

    public static void main(String[] args) throws IOException {

        XSSFWorkbook xssfSheets = new XSSFWorkbook();
        XSSFSheet sheet = xssfSheets.createSheet();

        int rowCount = 0;
        XSSFRow row = sheet.createRow(rowCount);
        XSSFCell cell = row.createCell(0);
        cell.setCellValue("订单下单时间");
        XSSFCell cell2 = row.createCell(1);
        cell2.setCellValue("订单编号");
        XSSFCell cell3 = row.createCell(2);
        cell3.setCellValue("sku名称");
        XSSFCell cell4 = row.createCell(3);
        cell4.setCellValue("卡密");
        XSSFCell cell5 = row.createCell(4);
        cell5.setCellValue("卡密发送时间");


        File file = FileUtils.getFile("/Users/jihai/gejia/auto-earn/src/main/resources/draft.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.contains("扫描")) continue;

            String[] split = line.split(",");


            XSSFRow r = sheet.createRow(++rowCount);
            XSSFCell c1 = r.createCell(0);
            c1.setCellValue(split[0].split("订单下单时间:")[1]);

            XSSFCell c2 = r.createCell(1);
            c2.setCellValue(split[1].split(":")[1]);

            XSSFCell c3 = r.createCell(2);
            c3.setCellValue(split[2].split(":")[1]);

            XSSFCell c4 = r.createCell(3);
            if (split[3].split(":").length > 1) c4.setCellValue(split[3].split(":")[1]);

            XSSFCell c5 = r.createCell(4);
            if (line.split("卡密发送时间:").length > 1) c5.setCellValue(line.split("卡密发送时间:")[1]);
        }

        xssfSheets.write(new FileOutputStream(new File("/Users/jihai/gejia/auto-earn/src/main/resources/output.xls")));
    }
}
