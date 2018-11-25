package uehara.daishin.sdtool.apireader;

import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import uehara.daishin.sdtool.design.DesignData;
import uehara.daishin.sdtool.design.DesignDataSheet;
import uehara.daishin.sdtool.design.DesignReader;
import uehara.daishin.stdtool.util.PoiUtil;

public class InterfaceSheetReader {
	public InterfaceSheetReader(){
	}

	public static DesignDataSheet readInterfaceSheet(Sheet sheet){
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
					case "IF":
						// ヘッダ読み込み
						DesignData ifDesignData=DesignReader.getDesignData("IF",++l,0,false,100);
						DesignReader.pushHeaderKey(ifDesignData, "名前");
						DesignReader.pushHeaderKey(ifDesignData, "リクエスト種別");
						DesignReader.pushHeaderKey(ifDesignData, "操作種別");
						DesignReader.pushHeaderKey(ifDesignData, "URI");
						DesignReader.readHeader(sheet, ifDesignData);
						designDataSheet.getDesignDataList().add(ifDesignData);
						break;
					case "呼出パラメータ":
						// 呼出パラメータ読み込み
						DesignData callParamDesignData=DesignReader.getDesignData("呼出パラメータ",++l,0,false,100);
						DesignReader.pushHeaderKey(callParamDesignData, "パラメータ名");
						DesignReader.pushHeaderKey(callParamDesignData, "物理名");
						DesignReader.pushHeaderKey(callParamDesignData, "型");
						DesignReader.pushHeaderKey(callParamDesignData, "桁");
						DesignReader.pushHeaderKey(callParamDesignData, "配列");
						DesignReader.readHeader(sheet, callParamDesignData);
						designDataSheet.getDesignDataList().add(callParamDesignData);
						break;
					case "戻値パラメータ":
						// 戻値パラメータ読み込み
						DesignData returnParamDesignData=DesignReader.getDesignData("戻値パラメータ",++l,0,false,100);
						DesignReader.pushHeaderKey(returnParamDesignData, "パラメータ名");
						DesignReader.pushHeaderKey(returnParamDesignData, "物理名");
						DesignReader.pushHeaderKey(returnParamDesignData, "型");
						DesignReader.pushHeaderKey(returnParamDesignData, "桁");
						DesignReader.pushHeaderKey(returnParamDesignData, "配列");
						DesignReader.readHeader(sheet, returnParamDesignData);
						designDataSheet.getDesignDataList().add(returnParamDesignData);
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
