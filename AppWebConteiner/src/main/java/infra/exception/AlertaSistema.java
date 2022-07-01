package infra.exception;


import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;



public class AlertaSistema extends Throwable{

	private static final long serialVersionUID = 1L;

	private final String number = String.valueOf(GregorianCalendar.getInstance().getTimeInMillis());
	
	private final List<String> mensagens=new ArrayList<String>(); 

	private TipoAlertaSistema tipoAlertaSistema= TipoAlertaSistema.ERROR;
	
	//Informando apenas a mensagem
	public AlertaSistema(String mensagem){
		tipoAlertaSistema= TipoAlertaSistema.ERROR;
	}
	
	//Informando a mensagem + causa| lança exception no console: utilizar para ERRO NA APLICAÇÂO
	public AlertaSistema(String mensagem,Throwable causa){
		super(mensagem,causa);
		tipoAlertaSistema= TipoAlertaSistema.ERROR;
	}
		
		
	//Tipo Alerta + mensagens: Utilizar para críticas de negócio
	public AlertaSistema(TipoAlertaSistema tipoException, List<String> msg, String msgException){
		super(msgException==null?"":msgException);
		mensagens.addAll(msg);
		tipoAlertaSistema= tipoException;		
	}
	
	
	public AlertaSistema(TipoAlertaSistema tipoException, String mensagem){
		super(mensagem);
		tipoAlertaSistema= tipoException;
		
	}
	
	//Tratar esse formato depois: remover da aplicação!!!
	public AlertaSistema(String mensagem,TipoAlertaSistema tipoException){
		super(mensagem);
		tipoAlertaSistema= tipoException;
		
	}
	
	

	
	
	
	
	@Override
	public String getMessage() {
		return super.getMessage()+(getCause()!=null?("\n\n"+number+" - Entre em contato com o Administrador do Sistema!"):"");
	}

	public String getNumber() {
		return number;
	}

	public TipoAlertaSistema getTipoAlertaSistema() {
		return tipoAlertaSistema;
	}

	public List<String> getMensagens() {
		return mensagens;
	}


	public void setTipoAlertaSistema(TipoAlertaSistema tipoAlertaSistema) {
		this.tipoAlertaSistema = tipoAlertaSistema;
	}
	
}
