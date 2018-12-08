package uehara.daishin.sdtool.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Validator {
//	public static Pattern pDateYYYYMMDD;
	public static SimpleDateFormat pDateYYYYMMDD;

	public static boolean validation(String str,ValidationType vt) {
		boolean ret_val=false;
		switch(vt) {
		case DateYYYYMMDD1:
			if (null == pDateYYYYMMDD) {
				pDateYYYYMMDD = new SimpleDateFormat("yyyyMMdd");
			}
			try {
				Date format = pDateYYYYMMDD.parse(str);
				ret_val= true;
			} catch (ParseException e) {
				ret_val= false;
			}
			break;
		default:

		}
		return ret_val;
	}

}
