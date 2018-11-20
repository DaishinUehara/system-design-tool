package uehara.daishin.sdtool.apireader.table;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class TableData {
	private List<String> header_string;
	private Map<String,Integer> header_pos;
	//private Map<String,String> data;
	private List<Map<String,String>> table;

	private boolean header_virtical;

	int max_header_search;

	int start_row;
	int start_col;

}
