package uehara.daishin.sdtool.apireader;

import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import uehara.daishin.sdtool.design.DesignData;
import uehara.daishin.sdtool.design.DesignDataSheet;
import uehara.daishin.sdtool.design.DesignReader;
import uehara.daishin.stdtool.util.PoiUtil;

public class FormDesignSheetReader {
	public FormDesignSheetReader(){
	}
	public static DesignDataSheet readFormDesignSheet(Sheet sheet){
		DesignDataSheet designDataSheet=new DesignDataSheet();
		designDataSheet.setName(sheet.getSheetName());
		designDataSheet.setDesignDataList(new ArrayList<DesignData>());
		System.out.println("[INFO][開始]シート読込:"+sheet.getSheetName());
		for(int l = 0; l <= sheet.getLastRowNum();l++){
			String acell;
			Row row = sheet.getRow(l);
			if ( null == row){
				acell="";
			} else{
				Cell c0 = row.getCell(0);
				if (null == c0){
					acell="";
				} else {
					acell = PoiUtil.getCellString(c0);
					// acell = c0.getStringCellValue();
					switch(acell){
					case "業務":
						// 業務読み込み
						DesignData jobDesignData=DesignReader.getDesignData("業務",++l,0,false,100);
						DesignReader.pushHeaderKey(jobDesignData, "業務id");
						DesignReader.pushHeaderKey(jobDesignData, "業務名");
						DesignReader.pushHeaderKey(jobDesignData, "物理パス");
						DesignReader.pushHeaderKey(jobDesignData, "初期画面id");
						DesignReader.readHeader(sheet, jobDesignData);
						designDataSheet.getDesignDataList().add(jobDesignData);
						break;
					case "画面":
						// 画面読み込み
						DesignData displayDesignData=DesignReader.getDesignData("画面",++l,0,false,100);
						DesignReader.pushHeaderKey(displayDesignData, "画面id");
						DesignReader.pushHeaderKey(displayDesignData, "名前");
						DesignReader.pushHeaderKey(displayDesignData, "物理ファイル名");
						DesignReader.readHeader(sheet, displayDesignData);
						designDataSheet.getDesignDataList().add(displayDesignData);
						break;
					case "項目":
						// 項目読み込み
						DesignData itemDesignData=DesignReader.getDesignData("項目",++l,0,false,100);
						DesignReader.pushHeaderKey(itemDesignData, "項目id");
						DesignReader.pushHeaderKey(itemDesignData, "項目名");
						DesignReader.pushHeaderKey(itemDesignData, "コントロール種別");
						DesignReader.pushHeaderKey(itemDesignData, "属性");
						DesignReader.pushHeaderKey(itemDesignData, "桁数");
						DesignReader.pushHeaderKey(itemDesignData, "変数名");
						DesignReader.pushHeaderKey(itemDesignData, "明細");
						DesignReader.readHeader(sheet, itemDesignData);
						designDataSheet.getDesignDataList().add(itemDesignData);
						break;
					case "明細":
						// 明細読み込み
						DesignData detailDesignData=DesignReader.getDesignData("明細",++l,0,false,100);
						DesignReader.pushHeaderKey(detailDesignData, "明細id");
						DesignReader.pushHeaderKey(detailDesignData, "明細項目id");
						DesignReader.pushHeaderKey(detailDesignData, "明細項目名");
						DesignReader.pushHeaderKey(detailDesignData, "明細項目コントロール");
						DesignReader.pushHeaderKey(detailDesignData, "明細項目属性");
						DesignReader.pushHeaderKey(detailDesignData, "明細項目桁数");
						DesignReader.pushHeaderKey(detailDesignData, "明細項目変数名");
						DesignReader.readHeader(sheet, detailDesignData);
						designDataSheet.getDesignDataList().add(detailDesignData);
						break;
					default:
						break;
					}
				}
			}
		}
		System.out.println("[INFO][終了]シート読込:"+sheet.getSheetName());
		return designDataSheet;
	}

}
