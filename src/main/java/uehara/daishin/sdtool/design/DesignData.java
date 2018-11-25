package uehara.daishin.sdtool.design;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class DesignData {

	private String name;

	private List<String> headerString;
	private Map<String,Integer> headerPosition;
	//private Map<String,String> data;
	private List<Map<String,String>> table;

	private boolean headerVirtical;

	int maxHeaderSearch;

	int startRow;
	int startCol;

}
