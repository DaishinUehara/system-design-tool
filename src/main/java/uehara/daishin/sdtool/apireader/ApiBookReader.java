package uehara.daishin.sdtool.apireader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import uehara.daishin.sdtool.design.DesignDataBook;
import uehara.daishin.sdtool.design.DesignDataSheet;
import uehara.daishin.stdtool.util.PoiUtil;

public class ApiBookReader
{


	public static DesignDataBook ReadExcel(File file, String shortFileName )throws IOException{
		Workbook workbook=null;

		try{
			workbook = WorkbookFactory.create(file, null, true);

			return readWorkBook(workbook, shortFileName);
		}catch(IOException e){
			throw(e);
		}finally{
			workbook.close();
		}
	}

	private static DesignDataBook readWorkBook(Workbook workbook, String shortFileName){
		DesignDataBook designDataBook = new DesignDataBook();
		designDataBook.setName(shortFileName);
		designDataBook.setDesignDataSheetList(new ArrayList<DesignDataSheet>());

		System.out.println("[INFO][開始]BOOK読込:"+shortFileName);

		for (int i = 0; i<workbook.getNumberOfSheets();i++){
			Sheet sheet = workbook.getSheetAt(i);
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
						acell=PoiUtil.getCellString(c0);
						// acell = c0.getStringCellValue();
						if (acell.equals("処理種別")){
							// 処理種別のセット
							Cell c1 = row.getCell(1);
							if (null == c1){

							} else {
								process_type=c1.getStringCellValue();
								switch(process_type){
								case "画面デザイン":
									break;
								case "IF呼出":
									designDataBook.getDesignDataSheetList().add(InterfaceSheetReader.readInterfaceSheet(sheet));
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
			}
		}

		System.out.println("[INFO][終了]BOOK読込:"+shortFileName);

		return designDataBook;
	}



}

