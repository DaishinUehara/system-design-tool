package uehara.daishin.sdtool.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import uehara.daishin.stdtool.util.PoiUtil;

public class DesignReader {

	/**
	 * ExcelのCell値を文字列に変換するフォーマッタ
	 */
//	public static DataFormatter formatter;


	/**
	 * 設計データ初期化処理
	 * @param start_row 開始行
	 * @param start_col 開始列
	 * @param header_virtical ヘッダが縦方向のときtrue、横方向のときfalse
	 * @param max_header_search ヘッダのサーチ上限回数(現在未使用)
	 * @return 取得した設計情報の入れ物
	 */
	public static DesignData getDesignData(String name, int start_row, int start_col, boolean header_virtical, int max_header_search){
		DesignData td = new DesignData();
		td.setName(name);
		td.setHeaderString(new ArrayList<String>());
		td.setHeaderPosition(new HashMap<String,Integer>());

		td.setTable(new ArrayList<Map<String,String>>());

		td.setStartRow(start_row);
		td.setStartCol(start_col);
		td.setMaxHeaderSearch(max_header_search);
		td.setHeaderVirtical(header_virtical);

		return td;
	}

	/**
	 * ヘッダ文字列挿入
	 * @param td 設定データ
	 * @param header_string ヘッダー文字列
	 *
	 * 設計情報の取得キーとなるヘッダ情報を追加する。
	 */
	public static void pushHeaderKey(DesignData td, String header_string){
		td.getHeaderString().add(header_string);
	}

	/**
	 * ヘッダの読込
	 * @param sheet Excelのシート
	 * @param td 設計データ
	 *
	 * pushHeaderKeyで追加したヘッダ情報でサーチし設計データにExcelから設計情報を読み取る。
	 */
	public static void readDesignData(Sheet sheet, DesignData td){
		if ( td.isHeaderVirtical() ){
			System.out.println("[INFO][開始]設計情報読込:"+td.getName());
			// 通常とは異なり列をヘッダとして読む場合
			int maxrow=0; // サーチ範囲を覚えるためループの外で定義
			Iterator<String> his = td.getHeaderString().iterator();
			while(his.hasNext()){
				String hstring=his.next();
				for(int rowpos=td.getStartRow(); rowpos <= sheet.getLastRowNum(); rowpos++){
					Row row = sheet.getRow(td.getStartRow());
					if ( null == row ) break; // 縦に読み込む場合、ヘッダが途切れたらbreak;
					Cell cell = row.getCell(td.getStartCol());
					if ( null == cell ) break; //  縦に読み込む場合、ヘッダが途切れたらbreak;
					String cell_string = PoiUtil.getCellString(cell);
					// キーの追加
					if (hstring.equals(cell_string)){
						td.getHeaderPosition().put( hstring,cell.getRowIndex());
						if (maxrow < cell.getRowIndex()) maxrow=cell.getRowIndex();
					}
				}
			}

			// 縦読み開始
			HashMap<String,String> data;
			boolean is_blank=false;
			for(int colpos = td.getStartCol()+1;is_blank==false;colpos++){
				data=new HashMap<String,String>();
				is_blank=true;

				Iterator<String> his2 = td.getHeaderString().iterator();
				while(his2.hasNext()){
					String hstring=his2.next();
					int rowpos = td.getHeaderPosition().get(hstring);
					Row row = sheet.getRow(rowpos);
					Cell cell = row.getCell(colpos);// headerがあるから必ずrowはnot null
					if ( null == cell ){
						data.put(hstring, "");
					} else {
						data.put(hstring, PoiUtil.getCellString(cell));
						is_blank=false;
					}
				}
				if (is_blank == false ){
					td.getTable().add(data);
				}
			}
			System.out.println("[INFO][終了]設計情報読込:"+td.getName());
			// 縦読み完了

		} else {
			System.out.println("[INFO][開始]設計情報読込:"+td.getName());
			// 通常同様、行をヘッダとして読む場合
			int maxcol=0; // サーチ範囲を覚えるためループの外で定義
			Iterator<String> his1 = td.getHeaderString().iterator();
			while(his1.hasNext()){
				String hstring=his1.next();

				Row row = sheet.getRow(td.getStartRow());
				Iterator<Cell> cellit=row.cellIterator(); // 横に読み込む場合はiteratorで読み込む(breakしない)
				while(cellit.hasNext()){
					Cell cell=cellit.next();
					if(null == cell) break;
					String header_string = PoiUtil.getCellString(cell);
					if(hstring.equals(header_string)){
						td.getHeaderPosition().put(hstring,cell.getColumnIndex());
						if (maxcol < cell.getColumnIndex()) maxcol=cell.getColumnIndex();
					}
				}

			}


			// ここからデータの読み込み
			HashMap<String,String> data;
			boolean is_blank=false;
			for(int rowpos = td.getStartRow()+1;is_blank==false;rowpos++){
				data=new HashMap<String,String>();
				is_blank=true;

				Iterator<String> his2 = td.getHeaderString().iterator();
				while(his2.hasNext()){
					String hstring=his2.next();
					int colpos = td.getHeaderPosition().get(hstring);
					Row row2 = sheet.getRow(rowpos);
					if (null == row2) {
						break; // データがない場合 breakしてループを抜ける
					} else {
						Cell cell = row2.getCell(colpos);
						if (null != cell ){
							is_blank=false;
						}
						data.put(hstring, PoiUtil.getCellString(cell));
					}
				}
				if (is_blank == false ){
					td.getTable().add(data);
				}
			}
			System.out.println("[INFO][終了]設計情報読込:"+td.getName());

			// ここまでデータの読み込み

		}
	}

	/**
	 * Cellの文字列化
	 * @param cell Cellのインスタンス
	 * @return cellの値の文字列化
	 *
	 * Cellの値をフォーマットに従い、文字列化して返す。
	 */
/*
	private static String getCellString(Cell cell){
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
*/
}

