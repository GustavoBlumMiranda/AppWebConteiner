package commons.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class ValidacaoUtil {

	public static boolean isStringVazia(String valor) {
		return valor == null || valor.trim().equals("");
	}

	public static <T> boolean isListaVazia(List<T> lista) {
		return lista == null || lista.size() == 0;
	}
	
	public static <T> boolean isListaVazia(Set<T> lista) {
		return lista == null || lista.size() == 0;
	}

	public static boolean isDataPreenchida(Calendar data) {
		return data != null;
	}

	public static boolean isNumero(String valor){
		 return valor.matches("-?\\d+(\\.\\d+)?");
	}
	
	public static boolean isNumeroPreenchido(BigDecimal valor) {
		return valor != null && !valor.equals(new BigDecimal("0")) && !valor.equals(new BigDecimal("0.00")) ;
	}
	
	public static boolean isNumeroPreenchido(Double valor) {
		return valor != null && !valor.equals(0d) && !valor.equals(0d);
	}

	public static boolean isNumeroPreenchido(Long valor) {
		return valor != null  && !valor.equals(0l);
	}

	public static boolean isNumeroPreenchido(Integer valor) {
		return valor != null && !valor.equals(0);
	}
	
	public static boolean isNumeroPreenchido(BigInteger valor) {
		return valor != null && !valor.equals(new BigInteger("0"));
	}

	public static boolean isCpfValido(String cpf) {
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");
		if (cpf.length() != 11)
			return false;

		String numDig = cpf.substring(0, 9);
		return calcDigVerif(numDig).equals(cpf.substring(9, 11));
	}

	private static String calcDigVerif(String num) {
		Integer primDig, segDig;
		int soma = 0, peso = 10;
		for (int i = 0; i < num.length(); i++)
			soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;

		if (soma % 11 == 0 | soma % 11 == 1)
			primDig = new Integer(0);
		else
			primDig = new Integer(11 - (soma % 11));

		soma = 0;
		peso = 11;
		for (int i = 0; i < num.length(); i++)
			soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;

		soma += primDig.intValue() * 2;
		if (soma % 11 == 0 | soma % 11 == 1)
			segDig = new Integer(0);
		else
			segDig = new Integer(11 - (soma % 11));

		return primDig.toString() + segDig.toString();
	}

	public static boolean isCnpjValido(String cnpj) {
		String cnpjSemMascara = MaskUtil.removerMascaraCnpj(cnpj);
		if (cnpjSemMascara.length() != 14) {
			return false;
		}

		cnpj = cnpj.replace(".", "");
		cnpj = cnpj.replace("/", "");
		cnpj = cnpj.replace("-", "");
		if (cnpj.length() == 14) {
			int soma = 0, dig;
			String cnpj_calc = cnpj.substring(0, 12);
			char[] chr_cnpj = cnpj.toCharArray();
			// --------- Primeira parte
			for (int i = 0; i < 4; i++)
				if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
					soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
			for (int i = 0; i < 8; i++)
				if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9)
					soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
			dig = 11 - (soma % 11);
			cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);
			// --------- Segunda parte
			soma = 0;
			for (int i = 0; i < 5; i++)
				if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
					soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
			for (int i = 0; i < 8; i++)
				if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9)
					soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
			dig = 11 - (soma % 11);
			cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);
			return cnpj.equals(cnpj_calc);
		} else {
			return false;
		}
	}

	

}
