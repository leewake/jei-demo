package com.pangpang.jei.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.read.context.AnalysisContext;
import com.alibaba.excel.read.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pangpang.jei.util.GenericResponseEntity;

import io.swagger.annotations.ApiOperation;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.apache.tomcat.util.file.ConfigFileLoader.getInputStream;


@RestController
public class IoController {

    @ApiOperation("excel读取")
    @GetMapping("/excel/read")
    public GenericResponseEntity<Void> readExcel() throws IOException {
        InputStream inputStream = getInputStream("classpath:file/2007NoModelMultipleSheet.xlsx");
//		InputStream inputStream = getInputStream("/Users/lijingwei/github/jei-demo/target/classes/file/2007NoModelMultipleSheet.xlsx");
        try {
            ExcelReader reader = new ExcelReader(inputStream, ExcelTypeEnum.XLSX, null,
                    new AnalysisEventListener<List<String>>() {
                        @Override
                        public void invoke(List<String> object, AnalysisContext context) {
                            System.out.println(
                                    "当前sheet:" + context.getCurrentSheet().getSheetNo() + " 当前行：" + context.getCurrentRowNum()
                                            + " data:" + object);
                        }

                        @Override
                        public void doAfterAllAnalysed(AnalysisContext context) {

                        }
                    });

            reader.read();

            for (Sheet sheet : reader.getSheets()) {
                System.out.println(sheet.getSheetName());
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return GenericResponseEntity.ok().build();

    }

    /**
     * <B>Description:</B> 用easyexcel导出excel <br>
     * <B>Create on:</B> 2018/6/5 上午10:53 <br>
     *
     * 使用url: http://localhost:8070/jei-demo/excel/export
     * SWAGGER中打开乱码
     * @author leewake
     */
    @ApiOperation("excel导出")
    @GetMapping("/excel/export")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {

        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
        try {

            String fileName = new String(("UserInfo " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                    .getBytes(), "UTF-8");
            Sheet sheet1 = new Sheet(1, 0);
            sheet1.setSheetName("第一个sheet");
            writer.write0(getListString(), sheet1);
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.finish();
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private List<List<String>> getListString() {
        List<String> list = new ArrayList();
        list.add("ooo1");
        list.add("ooo2");
        list.add("ooo3");
        list.add("ooo4");

        List<String> list1 = new ArrayList();
        list1.add("ooo1");
        list1.add("ooo2");
        list1.add("ooo3");
        list1.add("ooo4");

        List<List<String>> ll = new ArrayList<>();
        ll.add(list);
        ll.add(list1);

        return ll;
    }

}
