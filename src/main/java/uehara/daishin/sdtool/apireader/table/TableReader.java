package uehara.daishin.sdtool.apireader.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class TableReader {

	public static TableData getTableData(int start_row, int start_col, boolean header_virtical, int max_header_search){
		TableData td = new TableData();
		td.setHeader_string(new ArrayList<String>());
		td.setHeader_pos(new HashMap<String,Integer>());

		td.setTable(new ArrayList<Map<String,String>>());

		td.setStart_row(start_row);
		td.setStart_col(start_col);
		td.setMax_header_search(max_header_search);
		td.setHeader_virtical(header_virtical);

		return td;
	}

	public static void pushHeaderKey(TableData td, String header_string){
		td.getHeader_string().add(header_string);
//		td.getHeader_pos().put(header_key, -1);
	}

	public static void readHeader(Sheet sheet, TableData td){
		if ( td.isHeader_virtical() ){
			// 通常とは異なり列をヘッダとして読む場合
			int maxrow=0; // サーチ範囲を覚えるためループの外で定義
			Iterator<String> his = td.getHeader_string().iterator();
			while(his.hasNext()){
				String hstring=his.next();
				for(int rowpos=td.getStart_row(); rowpos <= sheet.getLastRowNum(); rowpos++){
					Row row = sheet.getRow(td.getStart_row());
					if ( null == row ) break; // 縦に読み込む場合、ヘッダが途切れたらbreak;
					Cell cell = row.getCell(td.getStart_col());
					if ( null == cell ) break; //  縦に読み込む場合、ヘッダが途切れたらbreak;
					String cell_string = cell.getStringCellValue();
					// キーの追加
					if (hstring.equals(cell_string)){
						td.getHeader_pos().put( hstring,cell.getRowIndex());
						if (maxrow < cell.getRowIndex()) maxrow=cell.getRowIndex();
					}
				}
			}

			// 縦読み開始
			HashMap<String,String> data;
			boolean is_blank=false;
			for(int colpos = td.getStart_col()+1;is_blank==false;colpos++){
				data=new HashMap<String,String>();
				is_blank=true;

				Iterator<String> his2 = td.getHeader_string().iterator();
				while(his.hasNext()){
					String hstring=his.next();
					int rowpos = td.getHeader_pos().get(hstring);
					Row row = sheet.getRow(rowpos);
					Cell cell = row.getCell(colpos);
					if ( null == cell ){
						data.put(hstring, "");
					} else {
						data.put(hstring, cell.getStringCellValue());
						is_blank=false;
					}
				}
				if (is_blank == false ){
					td.getTable().add(data);
				}
			}
			// 縦読み完了

		} else {
			// 通常同様、行をヘッダとして読む場合
			int maxcol=0; // サーチ範囲を覚えるためループの外で定義
			Iterator<String> his = td.getHeader_string().iterator();
			while(his.hasNext()){
				String hstring=his.next();

				Row row = sheet.getRow(td.getStart_row());
				Iterator<Cell> cellit=row.cellIterator(); // 横に読み込む場合はiteratorで読み込む(breakしない)
				while(cellit.hasNext()){
					Cell cell=cellit.next();
					if(null == cell) break;
					String header_string = cell.getStringCellValue();
					if(hstring.equals(header_string)){
						td.getHeader_pos().put(hstring,cell.getColumnIndex());
						if (maxcol < cell.getColumnIndex()) maxcol=cell.getColumnIndex();
					}
				}

			}


			// ここからデータの読み込み
			HashMap<String,String> data;
			boolean is_blank=false;
			for(int rowpos = td.getStart_row()+1;is_blank==false;rowpos++){
				data=new HashMap<String,String>();
				is_blank=true;

				Iterator<String> his2 = td.getHeader_string().iterator();
				while(his.hasNext()){
					String hstring=his.next();
					int colpos = td.getHeader_pos().get(hstring);
					Row row = sheet.getRow(rowpos);
					Cell cell = row.getCell(colpos);
					if ( null == cell ){
						data.put(hstring, "");
					} else {
						data.put(hstring, cell.getStringCellValue());
						is_blank=false;
					}
				}
				if (is_blank == false ){
					td.getTable().add(data);
				}
			}

			// ここまでデータの読み込み

		}
	}

}
