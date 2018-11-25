package uehara.daishin.sdtool.design;

import java.util.List;

import lombok.Data;

@Data
public class DesignDataSheet {

	private String name;

	private List<DesignData> designDataList;

}
