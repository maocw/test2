package com.maocw.util;

import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExportExcelUtil {

	 public static void writeExcel(String fileName){    
	        WritableWorkbook wwb = null; 
	        double Array[][] = new double[10][10];
	        for(int i = 0; i < 10; i ++){
	        	for(int j = 0; j < 10; j ++){
	        		Array[i][j] = Math.random()%10;
	        	}
	        }
	        try {    
	            //首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象    
	            wwb = Workbook.createWorkbook(new File(fileName));
	           
	        } catch (IOException e) {    
	            e.printStackTrace();    
	        }    
	        if(wwb!=null){    
	            //创建一个可写入的工作表    
	            //Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置    
	            WritableSheet ws = wwb.createSheet("工作表名称", 0);    
	               
	            Label label0 = new Label(0,0,"词项|主题");
	            try {    
                 //将生成的单元格添加到工作表中    
                 ws.addCell(label0);    
             } catch (RowsExceededException e) {    
                 e.printStackTrace();    
             } catch (WriteException e) {    
                 e.printStackTrace();    
             }    
	            for(int i=1;i<=10;i++){     
	                	Label labelC = new Label(i, 0, "T"+i);
	                	try {    
	                        //将生成的单元格添加到工作表中    
	                        ws.addCell(labelC);    
	                    } catch (RowsExceededException e) {    
	                        e.printStackTrace();    
	                    } catch (WriteException e) {    
	                        e.printStackTrace();    
	                    }    
	            }
	            for(int j = 1; j <= 10; j ++){
	            	Label labelC = new Label(0, j, "W"+j);
	            	try {    
                     //将生成的单元格添加到工作表中    
                     ws.addCell(labelC);    
                 } catch (RowsExceededException e) {    
                     e.printStackTrace();    
                 } catch (WriteException e) {    
                     e.printStackTrace();    
                 }    
	            }
	           
	      for(int j = 1; j <= 10; j ++){
	    	  for(int i = 1; i <= 10; i ++){
	    		  String str = Double.toString(Array[j-1][i-1]);
	    		  Label label = new Label(j, i , str);
	    		  try {
					ws.addCell(label);
				} catch (RowsExceededException e) {
					e.printStackTrace();
				} catch (WriteException e) {
					e.printStackTrace();
				}
	    	  }
	      }
	   
	            try {    
	                //从内存中写入文件中    
	                wwb.write();    
	                //关闭资源，释放内存    
	                wwb.close();    
	            } catch (IOException e) {    
	                e.printStackTrace();    
	            } catch (WriteException e) {    
	                e.printStackTrace();    
	            }    
	        }  
	        //循环对每一个单元格进行赋值     
	        //定位行   
	        
	    } 
	
}
