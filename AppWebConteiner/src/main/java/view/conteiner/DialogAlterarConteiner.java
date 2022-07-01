package view.conteiner;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
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
public class DialogAlterarConteiner {
	
	public DialogAlterarConteiner() {
		
	}
	
	private List<Conteiner> listaConteiners = new ArrayList<Conteiner>();
	private String idLetras;
	private String idNumeros;
	private Conteiner conteiner = new Conteiner();
	
	@Inject
	ConteinerDao conteinerDao;
	
	@PostConstruct
	public void iniciar(){
		conteiner = (Conteiner)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("conteinerSelecionado");
		idLetras = conteiner.getNumero().substring(0, 4);
		idNumeros = conteiner.getNumero().substring(4, 11);
	}
	
	public void atualizar() {
		if(idLetras != null && idNumeros !=null) {
			conteiner.setNumero(idLetras + idNumeros);
		}
		if(this.criticaConteiner(conteiner)) {
			try {
				conteinerDao.updateComTransacao(conteiner);
				this.fecharTelaAposConcluir1();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public String fecharTelaAposConcluir1() {
		RequestContext.getCurrentInstance().closeDialog(conteiner);
		return "SUCESSO";
	}
	
	public void excluir() {
		try {
			conteinerDao.removerComTransacao(conteiner);
			this.fecharTelaAposConcluir1();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		return true;
	}
	
	// menu padrao
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
	//

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
