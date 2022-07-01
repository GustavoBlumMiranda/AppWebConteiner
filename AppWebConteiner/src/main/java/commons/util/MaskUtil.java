package commons.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.bean.NoneScoped;
import javax.inject.Named;

@Named
@NoneScoped
public class MaskUtil {

	public static String removerMascaraCep(String cep) {
		return cep.replaceAll("\\.", "").replaceAll("-", "");
	}

	public static String aplicarMascaraCep(String cep) {
		if (ValidacaoUtil.isStringVazia(cep)) {
			return "";
		}
		return cep.substring(0, 2) + cep.substring(2, 5) + "-" + cep.substring(5, 8);
	}

	public static String removerMascaraCnpj(String cnpj) {
		return cnpj.replaceAll("\\.", "").replaceAll("/", "").replaceAll("-", "");
	}

	public static String aplicarMascaraCnpj(String cnpj) {
		if (ValidacaoUtil.isStringVazia(cnpj)) {
			return "";
		}
		if (cnpj.length() < 14) {
			return cnpj;
		}
		Pattern pattern = Pattern.compile("^[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}/[0-9]{4}-[0-9]{2}");
		Matcher matcher = pattern.matcher(cnpj);
		boolean matches = matcher.matches();
		if (matches) {
			return cnpj;
		}
		StringBuilder sBuilder = new StringBuilder(cnpj).insert(2, ".").insert(6, ".").insert(10, "/").insert(15, "-");
		return sBuilder.toString();
	}

	public static String removerMascaraCpf(String cpf) {
		return cpf.replaceAll("\\.", "").replaceAll("-", "");
	}

	public static String aplicarMascaraCpf(String cpf) {
		if (cpf.length() < 11) {
			return cpf;
		}
		Pattern pattern = Pattern.compile("^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}");
		Matcher matcher = pattern.matcher(cpf);
		boolean matches = matcher.matches();
		if (matches) {
			return cpf;
		}

		StringBuilder sBuilder = new StringBuilder(cpf).insert(3, ".").insert(7, ".").insert(11, "-");
		return sBuilder.toString();
	}

	public static String aplicarMascaraDinheiro(BigDecimal valor) {
		DecimalFormat decimalFormat = new DecimalFormat("#,###,##0.00");
		return decimalFormat.format(valor);
	}

	public static String removerMascaraTelefone(String tel) {
		return tel.replaceAll("\\.", "").replaceAll("-", "").replaceAll("\\(", "").replaceAll("\\)", "");
	}

}
