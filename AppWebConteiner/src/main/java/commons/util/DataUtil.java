package commons.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.Seconds;



public class DataUtil {

	/* CONVERSORES */

	public static Date stringToDate(String date) throws Exception {
		date = date.replace("-", "/");
		Integer ind = date.indexOf("/"); // Identificando se o ano veio na frente da data (formato ano/mes/dia)
		if (ind > 2) {
			date = date.substring(8, 10) + "/" + date.substring(5, 7) + "/" + date.substring(0, 4);
		}
		return stringToDate(date, "dd/MM/yyyy");
	}

	public static Date stringToDate(String date, String format) throws Exception {
		DateFormat formatter = new SimpleDateFormat(format);
		return formatter.parse(date);
	}

	public static Date stringToTime(String hora, String format) throws Exception {
		DateFormat formatter = new SimpleDateFormat(format);
		return formatter.parse(hora);
	}
	
	public static Timestamp stringToTimeStamp(String data) throws Exception {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    Date parsedDate = dateFormat.parse(data);
	    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

		return timestamp;
	}

	public static String dateToString(Date date) {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return formatter.format(date);
	}

	public static String dateToStringSemHora(Date date) {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(date);
	}
	
	public static String dateToStringAAAAMMDD(Date date) {
		DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(date);
	}
	
	public static String dateToStringAAAAMMDDformatado(Date date) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}

	public static Integer obterAnoAtual() {
		Date date = new Date();
		DateFormat formatter = new SimpleDateFormat("yyyy");
		return Integer.parseInt(formatter.format(date));
	}

	public static String obterDia(Date date) {
		DateFormat formatter = new SimpleDateFormat("dd");
		return formatter.format(date);
	}
	
	public static String obterMes(Date date) {
		DateFormat formatter = new SimpleDateFormat("MM");
		return formatter.format(date);
	}
	
	public static String obterAnoMes(Date date) {
		return obterAno(date)+obterMes(date);
	}

	public static String obterAno(Date date) {
		DateFormat formatter = new SimpleDateFormat("yyyy");
		return formatter.format(date);
	}
	public static String dateToString(Date date, String format) {
		DateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}

	public static String dateToStringExtended(Date date) {
		DateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");
		return formatter1.format(date) + " Ã s " + formatter2.format(date);
	}

	public static String timeToString(Date date) {
		DateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");
		return formatter2.format(date);
	}

	public static DateTime dateToDateTime(Date data) {
		return new DateTime(data);
	}

	public static java.sql.Date dateUtilToDateSql(java.util.Date data) {
		java.sql.Date sqlDate = new java.sql.Date(data.getTime());
		return sqlDate;
	}

	public static Timestamp dateUtilToTimeStamp(java.util.Date data) {
		Timestamp timestamp = new Timestamp(data.getTime());
		return timestamp;
	}

	public static Date obterDataInicioDoDia(Date dataFim) {
		Calendar inicioDoDia = Calendar.getInstance();
		inicioDoDia.setTime(dataFim);
		inicioDoDia.add(Calendar.HOUR, 00);
		inicioDoDia.add(Calendar.MINUTE, 00);
		Timestamp timestamp = new Timestamp(inicioDoDia.getTimeInMillis());
		return timestamp;
	}
	
	public static Date ajustaDataInicioDoDia(Date dataFim) {
		Calendar inicioDoDia = Calendar.getInstance();
		inicioDoDia.setTime(dataFim);
		inicioDoDia.set(Calendar.HOUR_OF_DAY, 0); //zerando as horas, minuots e segundos..
		inicioDoDia.set(Calendar.MINUTE, 0);
		inicioDoDia.set(Calendar.SECOND, 0);
		Timestamp timestamp = new Timestamp(inicioDoDia.getTimeInMillis());
		return timestamp;
	}

	public static Date obterDataFinalDoDia(Date dataFim) {
		Calendar inicioDoDia = Calendar.getInstance();
		inicioDoDia.setTime(dataFim);
		inicioDoDia.add(Calendar.HOUR, 23);
		inicioDoDia.add(Calendar.MINUTE, 59);
		Timestamp timestamp = new Timestamp(inicioDoDia.getTimeInMillis());
		return timestamp;
	}
	
	public static Date ajustaDataFinalDoDia(Date dataFim) {
		Calendar inicioDoDia = Calendar.getInstance();
		inicioDoDia.setTime(dataFim);
		inicioDoDia.set(Calendar.HOUR_OF_DAY, 23); //zerando as horas, minuots e segundos..
		inicioDoDia.set(Calendar.MINUTE, 59);
		inicioDoDia.set(Calendar.SECOND, 59);
		Timestamp timestamp = new Timestamp(inicioDoDia.getTimeInMillis());
		return timestamp;
	}
	
	
	public static Boolean isFinalDeSemana(Date dataSelecionada) {
		Boolean retorno = false;
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dataSelecionada);
        int diaDaSemana = gc.get(GregorianCalendar.DAY_OF_WEEK);
        if ((diaDaSemana == 1) || (diaDaSemana == 7)) {
        	retorno = true; // É sábado ou domingo;
        }
		return retorno;
	}

	public static Timestamp dataFinalDoDia(Date dataFim) {
		Calendar finalDoDia = Calendar.getInstance();
		finalDoDia.setTime(dataFim);
		finalDoDia.add(Calendar.HOUR, 23);
		finalDoDia.add(Calendar.MINUTE, 59);
		Timestamp timestamp = new Timestamp(finalDoDia.getTimeInMillis());
		return timestamp;
	}

	/* FUNCOES */

	public static boolean dataMaiorQueAtual(Date date) {
		DateTime data = new DateTime(date);
		return data.isAfterNow();
	}

	public static boolean dataMaiorOuIgualAAtual(Date date) {
		DateTime data = new DateTime(date);
		return data.isAfterNow() || dataIgualAtual(date);
	}

	public static boolean dataMenorQueAtual(Date date) {
		DateTime data = new DateTime(date);
		return data.isEqualNow();
	}

	public static boolean dataIgualAtual(Date date) {
		String dataAtual = dateToStringSemHora(new Date());
		String data = dateToStringSemHora(date);
		return data.equals(dataAtual);
	}

	public static int diferencaEmDias(Date firstDate, Date lastDate) {
		DateTime dataInicio = new DateTime(firstDate);
		DateTime dataFim = new DateTime(lastDate);

		return Days.daysBetween(dataInicio, dataFim).getDays();
	}

	public static int diferencaEmMeses(Date firstDate, Date lastDate) {
		DateTime dataInicio = new DateTime(firstDate);
		DateTime dataFim = new DateTime(lastDate);

		return Months.monthsBetween(dataInicio, dataFim).getMonths();
	}
	
	public static Boolean compararDataPorMes(Date dataMaior, Date dataMenor) {
		SimpleDateFormat formatador = new SimpleDateFormat ("yyyyMM");
		String data01 = formatador.format(dataMaior);
		String data02 = formatador.format(dataMenor);
		Integer dataMaiorn = Integer.parseInt(data01);
		Integer dataMenorn = Integer.parseInt(data02);
		return (dataMaiorn > dataMenorn);
	}

	public static int diferencaEmHoras(Date firstDate, Date lastDate) {
		DateTime dataInicio = new DateTime(firstDate);
		DateTime dataFim = new DateTime(lastDate);

		return Hours.hoursBetween(dataInicio, dataFim).getHours();
	}

	public static int diferencaEmMinutos(Date firstDate, Date lastDate) {
		DateTime dataInicio = new DateTime(firstDate);
		DateTime dataFim = new DateTime(lastDate);

		return Minutes.minutesBetween(dataInicio, dataFim).getMinutes();
	}

	public static String diferencaDeHorario(DateTime firstDate, DateTime lastDate) {
		String horas = Hours.hoursBetween(firstDate, lastDate).getHours() % 24 + "h:";
		while (horas.length() < 4) {
			horas = "0" + horas;
		}
		String minutos = Minutes.minutesBetween(firstDate, lastDate).getMinutes() % 60 + "m:";
		while (minutos.length() < 4) {
			minutos = "0" + minutos;
		}
		String segundos = Seconds.secondsBetween(firstDate, lastDate).getSeconds() % 60 + "s";
		while (segundos.length() < 4) {
			segundos = "0" + segundos;
		}
		return horas + minutos + segundos;
	}

	public static int diferencaEmSegundos(Date firstDate, Date lastDate) {
		DateTime dataInicio = new DateTime(firstDate);
		DateTime dataFim = new DateTime(lastDate);

		return Seconds.secondsBetween(dataInicio, dataFim).getSeconds();
	}

	public static Date somarDias(Date date, int dias) {
		return new DateTime(date).plusDays(dias).toDate();
	}
	
	public static Date somarHoras(Date date, int horas) {
		return new DateTime(date).plusHours(horas).toDate();
	}

	public static String getCurrentDateAndTime() {
		return dateToString(new Date());
	}

	public static String getCurrentHour() {
		return DateTime.now().toString("HH:mm:ss");
	}
	
	public static String getCurrentHourSemFormatacao() {
		return DateTime.now().toString("HHmmss");
	}

	public static String getCurrentHourWithMiliSeg() {
		return DateTime.now().toString("HH:mm:ss:ms");
	}

	public static DateTime getCurrentDateTime() {
		return DateTime.now();
	}

	public static Date getCurrentDate() {
		return DateTime.now().toDate();
	}

	public static Timestamp GetCurrentTimeStamp() {
		return new Timestamp(DateTime.now().getMillis());
	}

	public static Integer compareDate(Date dateToCompare, Date anotherDate) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date dateToCompareAux = format.parse(dateToString(dateToCompare, "dd/MM/yyyy"));

		Date anotherDateAux = format.parse(dateToString(anotherDate, "dd/MM/yyyy"));

		return dateToCompareAux.compareTo(anotherDateAux);
	}

	

	public static Date decrementarDias(Date dataPar, int qtdDias) {
		Calendar c = Calendar.getInstance();
		c.setTime(dataPar);
		c.add(Calendar.DATE, -qtdDias);

		return c.getTime();
	}

	public static Date incrementaDias(Date dataPar, int qtdDias) {
		Calendar c = Calendar.getInstance();
		c.setTime(dataPar);
		c.add(Calendar.DATE, qtdDias);

		return c.getTime();
	}

	public static Date decrementarMeses(Date dataPar, int qtdMeses) {
		Calendar c = Calendar.getInstance();
		c.setTime(dataPar);
		c.add(Calendar.MONTH, -qtdMeses);

		return c.getTime();
	}
	
	
	
	public static Date incrementaMinuto(Date dataPar, int qtdMinutos) {
		Calendar c = Calendar.getInstance();
		c.setTime(dataPar);
		c.add(Calendar.MINUTE, qtdMinutos);

		return c.getTime();
	}

	public static boolean verificaHoraEntre(Date pHoraIn, Date pHoraIni, Date pHoraFim) {
		boolean dtok = false;

		// Hora entre
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(pHoraIn);
		int horaIn = calendar.get(Calendar.HOUR_OF_DAY);
		int minutoIn = calendar.get(Calendar.MINUTE);

		// Hora Inicial
		Calendar calendarI = Calendar.getInstance();
		calendarI.setTime(pHoraIni);
		int horaIni = calendarI.get(Calendar.HOUR_OF_DAY);
		int minutoIni = calendarI.get(Calendar.MINUTE);

		// Hora Final
		Calendar calendarF = Calendar.getInstance();
		calendarF.setTime(pHoraFim);
		int horaFim = calendarF.get(Calendar.HOUR_OF_DAY);
		int minutoFim = calendarF.get(Calendar.MINUTE);

		if (horaIni > horaIn) {
			dtok = false;
		} else if (horaIni < horaIn) {
			dtok = true;
		} else if (horaIni == horaIn) {
			if (minutoIni <= minutoIn) {
				dtok = true;
			} else {
				dtok = false;
			}
		}

		if (dtok) {
			if (horaIn > horaFim) {
				dtok = false;
			} else if (horaIn < horaFim) {
				dtok = true;
			} else if (horaIn == horaFim) {
				if (minutoIn <= minutoFim) {
					dtok = true;
				} else {
					dtok = false;
				}
			}
		}

		return dtok;

	}



}
