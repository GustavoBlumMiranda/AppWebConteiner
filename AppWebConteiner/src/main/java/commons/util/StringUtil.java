package commons.util;

import java.text.ParseException;

public class StringUtil {
	public static String removerMascaraCpf (String numero) throws ParseException{
		if(numero == null){
			return "";
		}else{
			return numero.replaceAll("[.]", "").replaceAll("-", "");
		}
    	
    }
	

}
