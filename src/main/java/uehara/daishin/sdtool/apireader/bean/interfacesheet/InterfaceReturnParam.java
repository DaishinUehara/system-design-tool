package uehara.daishin.sdtool.apireader.bean.interfacesheet;

import lombok.Data;

@Data
public class InterfaceReturnParam
{
    private String logical_name;
    private String phisical_name;
    private String type;
    private int length;
    private boolean is_array;
}
