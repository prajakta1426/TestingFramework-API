package com.ccst.data;

import com.ccst.utils.CsvReader;
import com.ccst.utils.ExcelReader;
import org.testng.annotations.DataProvider;
import java.util.List;
import java.util.Map;

    //record is a lightweight version of a class which only contains data
    public class TestData {
        private static final String EXCEL_FILE = "testdata/login.xlsx";
        private static final String EXCEL_SHEET = "ae-invalid";
        private static final String CSV_FILE = "testdata/login.csv";

//        @DataProvider(name = "invalidLogins")
//        public Object[][] invalidLogins()
//        {
//            return new Object[][]{
//                    {"xyz@gmail.com","xyz","Your email or password is incorrect!"},
//                    {"message.here0102@gmail.com","xyz","Your email or password is incorrect!"}
//            };
//        }

        @DataProvider(name = "invalidsLoginExcel")
        public static Object[][] invalidsLoginExcel()
        {
            return toTestUsers(ExcelReader.read(EXCEL_FILE,EXCEL_SHEET));
        }

        @DataProvider(name = "invalidsLoginCsv")
        public static Object[][] invalidsLoginCsv()
        {
            return toTestUsers(CsvReader.read(CSV_FILE));
        }

        public static Object[][] toTestUsers(List<Map<String,String>> rows)
        {
            Object[][] data = new Object[rows.size()][];
            for (int i = 0; i< rows.size(); i++)
            {
                data[i] = new Object[]{TestUser.fromRow(rows.get(i))};
            }
            return data;
        }
    }















