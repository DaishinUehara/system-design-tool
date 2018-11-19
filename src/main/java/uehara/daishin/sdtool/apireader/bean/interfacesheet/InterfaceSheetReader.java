package uehara.daishin.sdtool.apireader.bean.interfacesheet;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class InterfaceSheetReader {
	public InterfaceSheetReader(){
	}

	public static InterfaceSheet readInterfaceSheet(Sheet sheet){
		System.out.println("シート名:"+sheet.getSheetName());
		InterfaceSheet isheet = new InterfaceSheet();
		for(int l = 0; l <= sheet.getLastRowNum();l++){
			String acell;
//			String process_type;
			Row row = sheet.getRow(l);
			if ( null == row){
				acell="";
			} else{
    			Cell c0 = row.getCell(0);
    			if (null == c0){
    				acell="";
    			} else {
        			acell = c0.getStringCellValue();
        			switch(acell){
        				case "IF":
        					// ヘッダ読み込み
        					l=ifHeaderRead(++l,sheet,isheet);
        					break;
        				case "呼出パラメータ":
        					// 呼出パラメータ読み込み
        					l=ifCallParamRead(++l,sheet,isheet);
        					break;
        				case "戻値パラメータ":
        					// 戻値パラメータ読み込み
        					l=ifReturnParamRead(++l,sheet,isheet);
        					break;
       					default:
    						// TODO エラー処理
       						break;
        			}

    			}

			}
			System.out.println(acell);

		}
		return isheet;

	}

	private static int ifReturnParamRead(int rowno, Sheet sheet, InterfaceSheet isheet){
		// ↓ヘッダ読み込み
		Row row;
		Cell cell;

		int param_name_col=-1;
		int phy_name_col=-1;
		int type_col=-1;
		int digit_col=-1;
		int isarray_col=-1;

		row=sheet.getRow(rowno);
		if (null == row){
			// TODO エラー処理
			return rowno;
		} else {
			for(int i=0;null != row.getCell(i); i++ ){
				cell=row.getCell(i);
				String cvalue = cell.getStringCellValue();
				switch(cvalue){
				case "パラメータ名":
					param_name_col=i;
					break;
				case "物理名":
					phy_name_col=i;
					break;
				case "型":
					type_col=i;
					break;
				case "桁":
					digit_col=i;
					break;
				case "配列":
					isarray_col=i;
					break;
				default:
					break;
				}

			}

		}
		// ↑ヘッダ読み込み
		// ↓値読み込み
		rowno++;
		row=sheet.getRow(rowno);
		List<InterfaceReturnParam> list=new ArrayList<InterfaceReturnParam>();
		while ( null != row){
			InterfaceReturnParam irp = new InterfaceReturnParam();

			if (param_name_col>=0){
				cell=row.getCell(param_name_col);
				if ( null != cell ){
					irp.setLogical_name(cell.getStringCellValue());
				} else {
					// TODO エラー処理
				}
			}

			if (phy_name_col>=0){
				cell=row.getCell(phy_name_col);
				if ( null != cell ){
					irp.setPhisical_name(cell.getStringCellValue());
				} else {
					// TODO エラー処理
				}
			}

			if (type_col>=0){
				cell=row.getCell(type_col);
				if ( null != cell ){
					irp.setType(cell.getStringCellValue());
				} else {
					// TODO エラー処理
				}
			}

			if (isarray_col>=0){
				cell=row.getCell(isarray_col);
				if ( null != cell ){
					if (cell.getStringCellValue().equals("○")){
						irp.set_array(true);

					} else {
						irp.set_array(false);
					}
				} else {
					// TODO エラー処理
				}
			}
			list.add(irp);
			rowno++;
			row=sheet.getRow(rowno);
		}
		isheet.setReturn_param(list);
		// ↑値読み込み


		return rowno;
	}

	private static int ifCallParamRead(int rowno, Sheet sheet, InterfaceSheet isheet){
		// ↓ヘッダ読み込み
		Row row;
		Cell cell;

		int param_name_col=-1;
		int phy_name_col=-1;
		int type_col=-1;
		int digit_col=-1;
		int isarray_col=-1;

		row=sheet.getRow(rowno);
		if (null == row){
			// TODO エラー処理
			return rowno;
		} else {
			for(int i=0;null != row.getCell(i); i++ ){
				cell=row.getCell(i);
				String cvalue = cell.getStringCellValue();
				switch(cvalue){
				case "パラメータ名":
					param_name_col=i;
					break;
				case "物理名":
					phy_name_col=i;
					break;
				case "型":
					type_col=i;
					break;
				case "桁":
					digit_col=i;
					break;
				case "配列":
					isarray_col=i;
					break;
				default:
					break;
				}

			}

		}
		// ↑ヘッダ読み込み
		// ↓値読み込み
		rowno++;
		row=sheet.getRow(rowno);
		List<InterfaceCallParam> list=new ArrayList<InterfaceCallParam>();
		while ( null != row){
			InterfaceCallParam icp = new InterfaceCallParam();

			if (param_name_col>=0){
				cell=row.getCell(param_name_col);
				if ( null != cell ){
					icp.setLogical_name(cell.getStringCellValue());
				} else {
					// TODO エラー処理
				}
			}

			if (phy_name_col>=0){
				cell=row.getCell(phy_name_col);
				if ( null != cell ){
					icp.setPhisical_name(cell.getStringCellValue());
				} else {
					// TODO エラー処理
				}
			}

			if (type_col>=0){
				cell=row.getCell(type_col);
				if ( null != cell ){
					icp.setType(cell.getStringCellValue());
				} else {
					// TODO エラー処理
				}
			}

			if (isarray_col>=0){
				cell=row.getCell(isarray_col);
				if ( null != cell ){
					if (cell.getStringCellValue().equals("○")){
						icp.set_array(true);

					} else {
						icp.set_array(false);
					}
				} else {
					// TODO エラー処理
				}
			}
			list.add(icp);
			rowno++;
			row=sheet.getRow(rowno);
		}
		isheet.setCall_param(list);
		// ↑値読み込み
		return rowno;
	}

	private static int ifHeaderRead(int rowno, Sheet sheet, InterfaceSheet isheet){
		// ↓ヘッダ読み込み
		Row row;
		Cell cell;

		int interface_name_col=-1;
		int request_type_col=-1;
		int manipulate_type_col=-1;
		int uri_col=-1;

		row=sheet.getRow(rowno);
		if (null == row){
			// TODO エラー処理
			return rowno;
		} else {


			for(int i=0;null != row.getCell(i); i++ ){
				cell=row.getCell(i);
				String cvalue = cell.getStringCellValue();
				switch(cvalue){
				case "名前":
					interface_name_col=i;
					break;
				case "リクエスト種別":
					request_type_col=i;
					break;
				case "操作種別":
					manipulate_type_col=i;
					break;
				case "URI":
					uri_col=i;
					break;
				default:
					break;
				}

			}
		}
		// ↑ヘッダ読み込み
		// ↓値読み込み
		rowno++;
		row=sheet.getRow(rowno);

		if (interface_name_col >=0 ){
			cell=row.getCell(interface_name_col);
			if ( null != cell ){
				isheet.setInterface_name(cell.getStringCellValue());
			} else {
				// TODO エラー処理
			}
		}

		if (request_type_col >=0 ){
			cell=row.getCell(request_type_col);
			if ( null != cell ){
				isheet.setRequest_type(cell.getStringCellValue());
			} else {
				// TODO エラー処理
			}
		}

		if (manipulate_type_col >=0 ){
			cell=row.getCell(manipulate_type_col);
			if ( null != cell ){
				isheet.setManipulate_type(cell.getStringCellValue());
			} else {
				// TODO エラー処理
			}
		}

		if (uri_col >=0 ){
			cell=row.getCell(uri_col);
			if ( null != cell ){
				isheet.setUri(cell.getStringCellValue());
			} else {
				// TODO エラー処理
			}
		}
		// ↑値読み込み

		return rowno;

	}
}
