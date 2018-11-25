package uehara.daishin.stdtool.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;

public class PoiUtil {

	/**
	 * ExcelのCell値を文字列に変換するフォーマッタ
	 */
	public static DataFormatter formatter;

	/**
	 * Cellの文字列化
	 * @param cell Cellのインスタンス
	 * @return cellの値の文字列化
	 *
	 * Cellの値をフォーマットに従い、文字列化して返す。
	 */
	public static String getCellString(Cell cell){
		if ( formatter == null ){
			formatter = new DataFormatter();
		}

		String ret="";
		if ( null == cell ){
			ret= "";
		} else {
			switch(cell.getCellType()){
			case NUMERIC:
				ret = formatter.formatCellValue(cell);
				if (ret.endsWith("_ ")) {
					ret = ret.substring(0, ret.length() -2);
				}
				break;
			case STRING:
				ret= cell.getStringCellValue();
				break;
			case BLANK:
				ret= "";
				break;
			case BOOLEAN:
				ret = formatter.formatCellValue(cell);
				if (ret.endsWith("_ ")) {
					ret = ret.substring(0, ret.length() -2);
				}
				break;
			case FORMULA:
		        Workbook book = cell.getSheet().getWorkbook();
		        CreationHelper helper = book.getCreationHelper();
		        FormulaEvaluator evaluator = helper.createFormulaEvaluator();
				ret = formatter.formatCellValue(cell,evaluator);
				if (ret.endsWith("_ ")) {
					ret = ret.substring(0, ret.length() -2);
				}
				break;
			case ERROR:
				ret="##ERROR="+String.valueOf(cell.getErrorCellValue())+"##";
				break;
			default:
				ret= "";
				break;
			}
		}
		return ret;
	}

}
