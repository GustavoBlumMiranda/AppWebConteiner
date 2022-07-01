package view.conteiner;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import domain.Conteiner;
import persistence.ConteinerDao;

@ManagedBean
@ViewScoped
public class CadastroConteiner {
	
	public CadastroConteiner() {
		
	}
	
	private List<Conteiner> listaConteiners = new ArrayList<Conteiner>();
	private String idLetras;
	private String idNumeros;
	private Conteiner conteiner = new Conteiner();
	
	@Inject
	ConteinerDao conteinerDao;
	
	public void salvar() {
		if(idLetras != null && idNumeros !=null) {
			conteiner.setNumero(idLetras + idNumeros);
		}
		if(this.criticaConteiner(conteiner)) {
			try {
				conteinerDao.insertComTransacao(conteiner);
				addMessage(FacesMessage.SEVERITY_INFO, "Sucesso. ", "Conteiner Cadastrado");
				showMessage(FacesMessage.SEVERITY_INFO, "Erro. ", "Conteiner Cadastrado");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public boolean criticaConteiner(Conteiner conteiner) {
		if(conteiner.getCategoria() == null || conteiner.getCliente() == null || 
				conteiner.getNumero() == null || conteiner.getStatus() == null || conteiner.getTipo() == null) {
			
			addMessage(FacesMessage.SEVERITY_ERROR, "Erro. ", "Campos em branco");
			showMessage(FacesMessage.SEVERITY_ERROR, "Erro. ", "Campos em branco");
			return false;	
		}
		
		if(conteiner.getNumero().length() < 11) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Erro. ", "Numero de conteiner invalido");
			showMessage(FacesMessage.SEVERITY_ERROR, "Erro. ", "Numero de conteiner invalido");
			return false;
		}
	
		
		Conteiner conteinerNumero = new Conteiner();
		conteinerNumero.setNumero(conteiner.getNumero());
		listaConteiners = conteinerDao.consultaConteiner(conteinerNumero);
		
		if(listaConteiners != null && listaConteiners.size() > 0) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Erro. ", "Numero de conteiner em uso");
			showMessage(FacesMessage.SEVERITY_ERROR, "Erro. ", "Numero de conteiner em uso");
			return false;
		}
		
		return true;
	}
	
	// menu padrao
	public String exibirCadastroConteiner() {
		return "cadastroConteiner.xhtml?faces-redirect=true";
	}
	public String exibirFiltroConteiner() {
		return "filtroConteiner.xhtml?faces-redirect=true";
	}
	public String exibirCadastroMovimentacao() {
		return "cadastroMovimentacao.xhtml?faces-redirect=true";
	}
	public String exibirFiltroMovimentacao() {
		return "filtroMovimentacao.xhtml?faces-redirect=true";
	}

	public String getIdLetras() {
		return idLetras;
	}

	public void setIdLetras(String idLetras) {
		this.idLetras = idLetras;
	}

	public String getIdNumeros() {
		return idNumeros;
	}

	public void setIdNumeros(String idNumeros) {
		this.idNumeros = idNumeros;
	}

	public Conteiner getConteiner() {
		return conteiner;
	}

	public void setConteiner(Conteiner conteiner) {
		this.conteiner = conteiner;
	}

	
	
	private void showMessage(FacesMessage.Severity severidade, String titulo, String msg) {
		FacesMessage message = new FacesMessage(severidade, titulo, msg);
		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}

	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}
	
}
