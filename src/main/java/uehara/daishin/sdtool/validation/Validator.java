package uehara.daishin.sdtool.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Validator {
//	public static Pattern pDateYYYYMMDD;
	public static SimpleDateFormat pDateYYYYMMDD;
	public static SimpleDateFormat pDateYYMMDD;
	public static SimpleDateFormat pDateTimeYYYYMMDDHHMM;
	public static SimpleDateFormat pDateTimeYYYYMMDDHHMMSS;
	public static SimpleDateFormat pDateTimeYYYYMMDDHHMMSSsss;

	public static boolean validation(String str,ValidationType vt) {
		boolean ret_val=false;
		switch(vt) {
		case DateYYYYMMDD1:
			if (null == pDateYYYYMMDD) {
				pDateYYYYMMDD = new SimpleDateFormat("yyyyMMdd");
			}
			try {
				pDateYYYYMMDD.parse(str);
				ret_val= true;
			} catch (ParseException e) {
				ret_val= false;
			}
			break;
		case DateYYMMDD1:
			if (null == pDateYYMMDD) {
				pDateYYMMDD = new SimpleDateFormat("yyMMdd");
			}
			try {
				pDateYYMMDD.parse(str);
				ret_val= true;
			} catch (ParseException e) {
				ret_val= false;
			}
			break;
		case DateTimeYYYYMMDDHHMM1:
			if (null == pDateTimeYYYYMMDDHHMM) {
				pDateTimeYYYYMMDDHHMM = new SimpleDateFormat("yyyyMMddHHmm");
			}
			try {
				pDateTimeYYYYMMDDHHMM.parse(str);
				ret_val= true;
			} catch (ParseException e) {
				ret_val= false;
			}
			break;
		case DateTimeYYYYMMDDHHMMSS1:
			if (null == pDateTimeYYYYMMDDHHMMSS) {
				pDateTimeYYYYMMDDHHMMSS = new SimpleDateFormat("yyyyMMddHHmmss");
			}
			try {
				pDateTimeYYYYMMDDHHMMSS.parse(str);
				ret_val= true;
			} catch (ParseException e) {
				ret_val= false;
			}
			break;
		case DateTimeYYYYMMDDHHMMSSsss1:
			if (null == pDateTimeYYYYMMDDHHMMSSsss) {
				pDateTimeYYYYMMDDHHMMSSsss = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			}
			try {
				pDateTimeYYYYMMDDHHMMSSsss.parse(str);
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
