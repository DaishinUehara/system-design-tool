package uehara.daishin.sdtool.apireader.bean.interfacesheet;

import java.util.List;

import lombok.Data;
import uehara.daishin.sdtool.apireader.bean.DocSheetInterface;

@Data
public class InterfaceSheet extends DocSheetInterface
{
    private int runno;
    private String process_type;


	private String interface_name;
    private String request_type;
    private String manipulate_type;
    private String uri;

    private List<InterfaceCallParam> call_param;

    private List<InterfaceReturnParam> return_param;

}
