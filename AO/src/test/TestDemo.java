package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import com.ndktools.javamd5.Mademd5;
import com.pdata.until.ExcelOfData;
import com.pdata.until.JDBCTools;

import cn.itcast.commons.CommonUtils;

public class TestDemo {
	
	@Test
	public void testDate() {
		String alias="a";
		String cId="cId";
		String ff="ff";
		String gg="gg";
		//select * from t_taskOfSch where cid=#{condition} and tpstate=2 order by pdate desc limit #{startIndex},#{pageSize}
		String sql="select * from "+alias+" where colId="+cId+" limit "+ff+","+gg;
		System.out.println(sql);
	}
	
	@Test
	public void testTime() {
		Date  date=new Date();

		Date  data1=new java.sql.Date(date.getTime());
		System.out.println(data1);
		System.out.println(date);
	}

	
	//测试生成Excel模版
	@Test
	public void testCreatExcel() {
		ExcelOfData tool=new ExcelOfData();
		List<String> info=new ArrayList<String>();
		info.add("id");
		info.add("schId");
		info.add("schName");
		info.add("colId");
		info.add("colName");
		info.add("state");
		tool.creatExcelForPro("test", info);
	}
	
	//测试建立数据表
	@Test
	public void testCreatTable() {
		JDBCTools jdbc=new JDBCTools();
		List<String> info=new ArrayList<String>();
		info.add("id");
		info.add("schId");
		info.add("schName");
		info.add("colId");
		info.add("colName");
		info.add("state");
		List<String> alias=new ArrayList<String>();
		alias.add("high");
		alias.add("weight");
		alias.add("age");
		jdbc.creatDataTable("testMySQl", info, alias);
	}

	//测试uuid
	@Test
	public void test() {
		String s=CommonUtils.uuid();
		System.out.println(s);
	}
	
	//测试MD5
	@Test
	public void TestMd5() {
		Mademd5 md=new Mademd5();
		System.out.println(md.toMd5("dd").length());
	}
	
	//读取excel表
	@Test
	public void testReadExcel() {
		File file = new File("/Users/mulming/Desktop/info.xlsx");  
		Jdbcs jd=new Jdbcs();
        InputStream inputStream = null;  
        Workbook workbook = null;  
        try {  
            inputStream = new FileInputStream(file);  
            workbook = WorkbookFactory.create(inputStream);  
            inputStream.close();  
            //工作表对象  
            Sheet sheet = workbook.getSheetAt(0);  
            //总行数  
            int rowLength = sheet.getLastRowNum()+1;  
            //工作表的列  
            Row row = sheet.getRow(0);  
            //总列数  
            int colLength = row.getLastCellNum();  
            //得到指定的单元格  
            Cell cell = row.getCell(0);  
            //得到单元格样式  
         //   CellStyle cellStyle = cell.getCellStyle();  
            System.out.println("行数：" + rowLength + ",列数：" + colLength);  
            for (int i = 0; i < rowLength; i++) {  
            	String eId=CommonUtils.uuid();
            	StringBuffer fieldValue=new StringBuffer();//存储每次读取的值
                row = sheet.getRow(i);  
                fieldValue.append("'"+eId+"',");
                for (int j = 0; j < colLength; j++) {  
                    cell = row.getCell(j);  
                    //Excel数据Cell有不同的类型，当我们试图从一个数字类型的Cell读取出一个字符串时就有可能报异常：  
                    //Cannot get a STRING value from a NUMERIC cell  
                    //将所有的需要读的Cell表格设置为String格式  
                    if (cell != null)  
                        cell.setCellType(CellType.STRING);
                    if(j<colLength-1)
                    	fieldValue.append("'"+cell.getStringCellValue()+"',");
                    else 
                    	fieldValue.append("'"+cell.getStringCellValue()+"'");
                    //System.out.print(cell.getStringCellValue() + "\t");  
                } 
               String sql="insert into t_sch(sid,saccount,spswd,scid,srole,sname,sdepartment,suname,sphone,semail) values("+fieldValue.toString()+");";
               System.out.print(sql);
               jd.insertDateToTabel(sql);
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  

}
