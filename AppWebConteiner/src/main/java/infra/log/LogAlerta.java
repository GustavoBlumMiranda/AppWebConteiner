package infra.log;

import java.lang.reflect.Method;

import commons.util.DataUtil;

public class LogAlerta {

	public static void setLogTransactionErro(Method method, Object[] entities, String number, String error) {
		System.out.println("ERRO[" + number + "] - " + DataUtil.getCurrentDateAndTime() + " - " + method);
		System.out.println(error);
	}

	public static void setLogTransactionSuccess(Method method, Object[] entities) {
		System.out.println("SUCCESS - " + DataUtil.getCurrentDateAndTime() + " - " + method);
	}

	public static void setLogSuccess(String msg, String metodo) {
		System.out.println("SUCCESS - " + DataUtil.getCurrentDateAndTime() + " - " + metodo + " - " + msg);
	}



}
