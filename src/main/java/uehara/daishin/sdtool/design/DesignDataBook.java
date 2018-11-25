package uehara.daishin.sdtool.design;

import java.util.List;

import lombok.Data;

@Data
public class DesignDataBook {
	private String name;

	private List<DesignDataSheet> designDataSheetList;

}
