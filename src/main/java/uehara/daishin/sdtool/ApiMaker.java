package uehara.daishin.sdtool;

import java.io.File;
import java.io.IOException;

import uehara.daishin.sdtool.apireader.ApiReader;



/**
 * Hello world!
 *
 */
public class ApiMaker
{
    public static void main( String[] args )
    {
    	String filepath="exceldoc/ApiDoc01.xlsx";
        File file = new File(filepath);
        try{
            ApiReader.ReadExcel(file);
        } catch (IOException e){
            System.err.println(filepath);
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println( "Hello World!" );
    }
}
