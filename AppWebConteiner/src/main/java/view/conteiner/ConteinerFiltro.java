package view.conteiner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class ConteinerFiltro {
	
	public ConteinerFiltro() {
		
	}
	
	private List<Conteiner> listaConteiners = new ArrayList<Conteiner>();
	private Conteiner conteiner = new Conteiner();
	private Conteiner conteinerSelecionado = new Conteiner();
	
	@Inject
	ConteinerDao conteinerDao;
	
	public void filtrar() {
		if(this.criticaConteiner(conteiner)) {
			try {
				listaConteiners = conteinerDao.consultaConteiner(conteiner);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public boolean criticaConteiner(Conteiner conteiner) {
		if(conteiner.getCategoria() == null && conteiner.getCliente() == null && 
				conteiner.getNumero() == null && conteiner.getStatus() == null && conteiner.getTipo() == null) {
			
			addMessage(FacesMessage.SEVERITY_ERROR, "Erro. ", "Informe pelo menos um campo");
			showMessage(FacesMessage.SEVERITY_ERROR, "Erro. ", "Informe pelo menos um campo");
			return false;	
		}
		
		return true;
	}
	
	public void exibirAlteracaoConteiner() {
			Map<String,Object> options = new HashMap<String, Object>();
	        options.put("modal", true);
	        options.put("resizable", false);
	        options.put("width", 900);
	        options.put("height", 500);
	        options.put("contentWidth", 880);
	        options.put("contentHeight", 480);
			try {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("conteinerSelecionado", conteinerSelecionado);
				RequestContext.getCurrentInstance().openDialog("dialogAlterarConteiner", options, null);
				this.filtrar();
				
				
			} catch (Exception erro) {
				System.out.println("Exception: " + erro.getMessage());
			}	
		
		
	}
	
	public void atualizaResultadoConteiner() {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterar conteiner", "Conteiner alterado com sucesso");
		filtrar();
		RequestContext.getCurrentInstance().showMessageInDialog(message);
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

	public Conteiner getConteiner() {
		return conteiner;
	}

	public void setConteiner(Conteiner conteiner) {
		this.conteiner = conteiner;
	}

	
	
	public List<Conteiner> getListaConteiners() {
		return listaConteiners;
	}

	public void setListaConteiners(List<Conteiner> listaConteiners) {
		this.listaConteiners = listaConteiners;
	}

	private void showMessage(FacesMessage.Severity severidade, String titulo, String msg) {
		FacesMessage message = new FacesMessage(severidade, titulo, msg);
		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}

	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}

	public Conteiner getConteinerSelecionado() {
		return conteinerSelecionado;
	}

	public void setConteinerSelecionado(Conteiner conteinerSelecionado) {
		this.conteinerSelecionado = conteinerSelecionado;
	}
	
}
