package commons.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class ValorUtil {
	public static String formataValorBDparaValorTela (Double numero) throws ParseException{
    	String valorTela = null;
    	Locale brasil = new Locale ("pt","BR");
		NumberFormat formato = NumberFormat.getInstance(brasil);
		valorTela = formato.format(numero);
		if (valorTela.indexOf(",") == -1){
			valorTela = valorTela + ",00";
		} else {
			String casasDecimais = valorTela.substring(valorTela.indexOf(","), valorTela.length());
			if (casasDecimais.length() == 2){
				valorTela = valorTela + "0";
			}
		}
		return valorTela;
    }
	
	public static String formataValorComPontoNoDecimal(Double numero) throws ParseException{
		return formataValorBDparaValorTela(numero).replaceAll(",", ".");
	}
	
	public static String formataValorComPontoNoDecimalSemMilhar(Double numero) throws ParseException{
		return formataValorBDparaValorTela(numero).replaceAll("[.]", "").replaceAll("[,]", ".");
	}
	
	
	
	public static BigDecimal ajustarPrecisaoDecimal(int casas, BigDecimal valor)  
	   {  
	       String quantCasas = "%."+casas+"f", textoValor = "0";  
	       try  
	       {  
	           textoValor = String.format(Locale.getDefault(), quantCasas, valor);  
	       }catch(java.lang.IllegalArgumentException e)  
	       {  
	           // Quando os digitos com 2 casas decimais forem Zeros, exemplo: 0.000001233888.  
	           // Nao existe valor 0,00 , logo entra na java.lang.IllegalArgumentException.  
	           if(e.getMessage().equals("Digits < 0"))  
	               textoValor = "0";  
	           System.out.println(e.getMessage());  
	       }  
	       return new BigDecimal(textoValor.replace(",", "."));  
	   }
	
	public static Boolean isValor(String valor){
		Boolean retorno = true;
		try{
			valor = valor.replace(".", "");
			valor = valor.replace(",", ".");
			@SuppressWarnings("unused")
			BigDecimal valorBigDecimal = new BigDecimal(valor);
			valorBigDecimal = null;
		} catch (Exception erro){
			retorno = false;
		}
		return retorno;
	}
	
	public static String deixaSoNumero(String valor){
		String novoValor = "";
		if (valor != null){
			valor = valor.trim();
			try{
				String teste = null;
				for(int i=0; i < valor.length() ; i++){
		              teste = String.valueOf(valor.charAt(i));
		              if ((teste.equalsIgnoreCase("0"))
		              || (teste.equalsIgnoreCase("1"))
		              || (teste.equalsIgnoreCase("2"))
		              || (teste.equalsIgnoreCase("3"))
		              || (teste.equalsIgnoreCase("4"))
		              || (teste.equalsIgnoreCase("5"))
		              || (teste.equalsIgnoreCase("6"))
		              || (teste.equalsIgnoreCase("7"))
		              || (teste.equalsIgnoreCase("8"))
		              || (teste.equalsIgnoreCase("9"))){
		            	  novoValor = novoValor + teste;
		              }
		         }
				
			} catch (Exception erro){
				novoValor="";
			}
			novoValor = novoValor.trim();
		}
		return novoValor;
	}
	
	public static Boolean isInteger(String valor){
		Boolean retorno = true;
		try{
			@SuppressWarnings("unused")
			Integer inteiro = Integer.parseInt(valor);
			inteiro = null;
		} catch (Exception erro){
			retorno = false;
		}
		return retorno;
	}
	
	public static Boolean isLong(String valor){
		Boolean retorno = true;
		try{
			@SuppressWarnings("unused")
			Long numero = Long.parseLong(valor);
			numero = null;
		} catch (Exception erro){
			retorno = false;
		}
		return retorno;
	}
	
	public static Long converteStringParaLong(String valor) throws Exception{

		valor = valor.trim();
		Long valorLong = null;
		try {
			valorLong = Long.parseLong(valor);
		} catch (Exception e) {
			throw new Exception("[converteStringParaLong] - Valor recebido: " + valor + " ==> " + e.getMessage());
		}
        

		return valorLong;
	}
	
	public static BigDecimal converteStringParaBigDecimal(String valor) throws ParseException{
		/* metodo antigo Inibido por FH em 2015/08/20
		 * 
		valor = valor.trim();
		BigDecimal valorBigDecimal = null;
		Locale brasil = new Locale ("pt", "BR");  
	    DecimalFormat df = new DecimalFormat ("#,##0.00", new DecimalFormatSymbols (brasil));  
	    df.setParseBigDecimal (true);
 
	    valorBigDecimal = (BigDecimal) df.parseObject(valor);
	    valorBigDecimal = ajustarPrecisaoDecimal(2, valorBigDecimal);

		return valorBigDecimal;
		*/
		// Metodo novo
		valor = valor.trim();
		BigDecimal valorBigDecimal = null; 
		if (valor.indexOf(',') < 1){                   
			valorBigDecimal = new BigDecimal(valor);    // Se possui apenas pontos esta convers�o � v�lida. 
														// se tirar n�o vai formatar o valor corretamente.
		} else {
			Locale brasil = new Locale ("pt", "BR");  
		    DecimalFormat df = new DecimalFormat ("#,##0.00", new DecimalFormatSymbols (brasil));  
		    df.setParseBigDecimal (true);
	 
		    valorBigDecimal = (BigDecimal) df.parseObject(valor);
		    valorBigDecimal = ajustarPrecisaoDecimal(2, valorBigDecimal);
		}
        

		return valorBigDecimal;
	}
	
	public static String converteBigDecimalParaString(BigDecimal valor) throws ParseException{
		
		String valorRetorno = null;
        if (valor != null){
        	valorRetorno = String.valueOf(valor.doubleValue());
        }

		return valorRetorno;
	}
}
