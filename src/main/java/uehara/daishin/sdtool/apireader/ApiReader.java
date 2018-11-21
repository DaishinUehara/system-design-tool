package uehara.daishin.sdtool.apireader;

import java.io.File;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import uehara.daishin.sdtool.apireader.bean.interfacesheet.InterfaceSheet;


public class ApiReader
{

    /**
     *
     */
    public ApiReader(){

    }

    public static String getCellString(Cell c){
    	if (null == c){
    		return "";
    	} else {
    		return c.getStringCellValue();
    	}
    }

    public static void ReadExcel(File file)throws IOException {
        // java.io.Fileから
        Workbook workbook;
        InterfaceSheet isheet;

        try{
            workbook = WorkbookFactory.create(file);
            try{
            	for (int i = 0; i<workbook.getNumberOfSheets();i++){
            		Sheet sheet = workbook.getSheetAt(i);
            		//System.out.println("シート名:"+sheet.getSheetName());
            		for(int l = 0; l <= sheet.getLastRowNum();l++){
            			String acell;
            			String process_type;
            			Row row = sheet.getRow(l);
            			if ( null == row){
            				acell="";
            			} else{
                			Cell c0 = row.getCell(0);
                			if (null == c0){
                				acell="";
                			} else {
                    			acell = c0.getStringCellValue();
                    			if (acell.equals("処理種別")){
                    				// 処理種別のセット
                    				Cell c1 = row.getCell(1);
                    				if (null == c1){

                    				} else {
                        				process_type=c1.getStringCellValue();
                        				switch(process_type){
                        				case "IF呼出":
                        					isheet = InterfaceSheetReader.readInterfaceSheet(sheet);
                        					break;
                        				case "IF⇒内部データ":
                        					break;
                        				case "内部⇒内部データ":
                        					break;
                        				case "SQL発行":
                        					break;
                        				case "内部データ⇒IF":
                        					break;
                        				case "任意処理":
                        					break;
                        				default:
                        					break;
                        				}
                    				}
                    			}

                			}

            			}
            			System.out.println(acell);

            		}

            	}
            }finally{
                workbook.close();
            }

        }catch(IOException e){
            throw(e);
        }
    }



}

