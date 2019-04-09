package com.pdata.until;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
/**
 * excel的读取与写入操作
 * @author mulming
 * @time 2018年5月25日
 * @descript ExcelOfData
 */
public class ExcelOfData {
	
	/**
	 * mulming
	 * creatExcelForPro 生成Excel模版----发布项目时生成模版
	 * @param alias 项目别名
	 * @param args  项目的字段名
	 * void
	 * 下午10:31:48
	 */
	public long creatExcelForPro(String alias,List<String> args){
		String path=null;
		long excelName=0;
		Workbook workbook = new XSSFWorkbook();  
	    Sheet sheet = workbook.createSheet(alias);  //项目名存储到第一
	    Row headRow=sheet.createRow(0);
	    for(int i=0;i<args.size();i++) {
	    	headRow.createCell(i).setCellValue(args.get(i));
	    }
       try {
    	   excelName=System.currentTimeMillis();
    	 // path="/Users/mulming/code/all_Java/AO/src/excel/"+excelName+".xlsx";
    	  path="C://excel//"+excelName+".xlsx";
           File file = new File(path);  
           FileOutputStream fileoutputStream = new FileOutputStream(file);  
           workbook.write(fileoutputStream);  
           fileoutputStream.close();  
           workbook.close();
       } catch (Exception e) {  
           e.printStackTrace();  
       }  
		return excelName;
	}

	/**
	 * mulming
	 * ExportData:把数据加载到workBook工作布中去
	 * @param data 输入数据：必须是List<List<String>>类型的数据
	 * @param head 表的第一行，即表头。list中第一个参数为sheet名， 后面一次为表头的参数，即项目的属性名称
	 * @return 返回导出文件后的地址
	 * String
	 * 下午5:24:09
	 */
	public void ExportDataToWorkbook(List<List<String>> data,List<String> head,Workbook workbook) {
	    Sheet sheet = workbook.createSheet(head.get(0));  //项目名存储到第一,表单名
	    Row headRow=sheet.createRow(0);
	    for(int i=0;i<head.size()-1;i++) {
	    	headRow.createCell(i).setCellValue(head.get(i+1));
	    }
		for(int i=0;i<data.size();i++) {
			Row row = sheet.createRow(i+1); 
			for(int j=0;j<data.get(i).size();j++) {//如果不需要导出学校、学院姓名id，j取值从5开始
			      row.createCell(j).setCellValue(data.get(i).get(j));  
			  }
		}
       workbook.setSheetName(0, head.get(0));  
	}
	
	
	/**
	 * 读取Excel数据到数据库,通过文件域
	 * mulming
	 * readExcelByFile
	 * @param file  文件域
	 * @param tableName 获取要上传的表名，即项目的别名
	 * @param aliasOfPro 项目字段的别名集合，学校ID，姓名，学院ID，姓名 用作表头
	 * @param idAndName  学校ID，姓名，学院ID，姓名 的值
	 * 下午10:40:09
	 */
	 public void  readExcelByFile(MultipartFile file,String tableName,List<String> aliasOfPro,List<String> idAndName) throws FileNotFoundException, IOException {
		JDBCTools jdbc=new JDBCTools();
	    InputStream inputStream = null;  
	    Workbook workbook = null;  
	    StringBuffer field=new StringBuffer();
	    int len=aliasOfPro.size();//获取项目别名的长度
	    for(int i=0;i<len;i++) {
	    	if(i<len-1)
	    		field.append(aliasOfPro.get(i)+",");
	    	else
	    		field.append(aliasOfPro.get(i));
	    }
	    try {  
	        inputStream = file.getInputStream();  
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
	       // System.out.println("行数：" + rowLength + ",列数：" + colLength);  
	        for (int i = 1; i < rowLength; i++) {  
	        	StringBuffer fieldValue=new StringBuffer();//存储每次读取的值
	        	fieldValue.append("'"+idAndName.get(0)+"'"+",");
	        	fieldValue.append("'"+idAndName.get(1)+"'"+",");
	        	fieldValue.append("'"+idAndName.get(2)+"'"+",");
	        	//fieldValue.append("'"+idAndName.get(3)+"'");//把学校等的值先放进去
	            row = sheet.getRow(i);  
	            for (int j = 0; j < colLength; j++) {  
	                cell = row.getCell(j);  
	                //Excel数据Cell有不同的类型，当我们试图从一个数字类型的Cell读取出一个字符串时就有可能报异常：  
	                //Cannot get a STRING value from a NUMERIC cell  
	                //将所有的需要读的Cell表格设置为String格式  
	                if (cell != null)  
	                    cell.setCellType(CellType.STRING);
	                if(j<colLength-1)
	                	fieldValue.append("'"+cell.getStringCellValue()+"'"+",");
	                else 
	                	fieldValue.append("'"+cell.getStringCellValue()+"'");
	            } 
	           String sql="insert into "+tableName+"("+field.toString()+") values("+fieldValue.toString()+");";
	           //执行sql语句
	           jdbc.insertDateToTabel(sql);
	        }  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	 }
		 
	 /**
	  * 导出数据到本地
	  * mulming
	  * export
	  * @param response
	  * @param wb 工作布
	  * @param fileName 文件命名
	  * @throws Exception
	  * void
	  * 下午8:14:43
	  */
	 public void export(HttpServletResponse response,Workbook wb,String fileName)throws Exception{
		 response.setHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("utf-8"),"iso8859-1"));
			response.setContentType("application/ynd.ms-excel;charset=UTF-8");
			OutputStream out=response.getOutputStream();
			wb.write(out);
			out.flush();
			out.close();
	 }
	 
	 /**
	 * 读取Excel数据到数据库 ,通过文件的路径
	 * mulming
	 * readExcelByFilePath
	 * @param fileName  获取上传的文件的名称
	 * @param tableName 获取要上传的表名
	 * @param aliasOfPro 项目字段的别名集合，包含表的id,学校ID，姓名，学院ID，姓名 用作表头
	 * @param idAndName  表的id,学校ID，姓名，学院ID，姓名 的值
	 * 下午10:40:09
	 */
	 public void  readExcelByFilePath(String fileName,String tableName,List<String> aliasOfPro,List<String> idAndName) {
		JDBCTools jdbc=new JDBCTools();
		File file = new File(fileName);  
        InputStream inputStream = null;  
        Workbook workbook = null;  
        StringBuffer field=new StringBuffer();
        int len=aliasOfPro.size();//获取项目别名的长度
        for(int i=0;i<len;i++) {
        	if(i<len-1)
        		field.append(aliasOfPro.get(i)+",");
        	else
        		field.append(aliasOfPro.get(i));
        }
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
            	StringBuffer fieldValue=new StringBuffer();//存储每次读取的值
//            	fieldValue.append(idAndName.get(0));
//            	fieldValue.append(idAndName.get(1));
//            	fieldValue.append(idAndName.get(2));
//            	fieldValue.append(idAndName.get(3));
//            	fieldValue.append(idAndName.get(4));//把学校等的值先放进去
                row = sheet.getRow(i);  
                for (int j = 0; j < colLength; j++) {  
                    cell = row.getCell(j);  
                    //Excel数据Cell有不同的类型，当我们试图从一个数字类型的Cell读取出一个字符串时就有可能报异常：  
                    //Cannot get a STRING value from a NUMERIC cell  
                    //将所有的需要读的Cell表格设置为String格式  
                    if (cell != null)  
                        cell.setCellType(CellType.STRING);
                    if(j<colLength-1)
	                	fieldValue.append("'"+cell.getStringCellValue()+"'"+",");
	                else 
	                	fieldValue.append("'"+cell.getStringCellValue()+"'");
                    System.out.print(cell.getStringCellValue() + "\t");  
                } 
               String sql="insert into "+tableName+"("+field.toString()+") values("+fieldValue.toString()+");";
               //执行sql语句
               jdbc.insertDateToTabel(sql);
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	 }
}
