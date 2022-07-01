package view.movimentacao;

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
import domain.Movimentacao;
import persistence.ConteinerDao;
import persistence.MovimentacaoDao;

@ManagedBean
@ViewScoped
public class FiltroMovimentacao {
	private Movimentacao movimentacao = new Movimentacao() ;
	
	private Movimentacao movimentacaoSelecionada = new Movimentacao() ;
	
	private String codigoConteiner;
	
	private List<Movimentacao> listaMovimentacao = new ArrayList<Movimentacao>();
	private List<Conteiner> listaConteiner = new ArrayList<Conteiner>();
	
	@Inject
	MovimentacaoDao movimentacaoDao;
	
	@Inject
	ConteinerDao conteinerDao;
	
	public void filtrar() {
		if(this.criticaMovimentacao(movimentacao)){
			
			try {
				listaMovimentacao = movimentacaoDao.consultaMovimentacao(movimentacao);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public boolean criticaMovimentacao(Movimentacao movimentacao) {
		
		if(codigoConteiner !=null) {
		Conteiner conteinerSoComNumero = new Conteiner();
		conteinerSoComNumero.setNumero(codigoConteiner);
		listaConteiner = conteinerDao.consultaConteiner(conteinerSoComNumero);
		if(listaConteiner !=null && listaConteiner.size() == 1) {	
			movimentacao.setConteiner(listaConteiner.get(0));
			}
		}
		
		if(codigoConteiner == null && movimentacao.getDataHoraFim() == null && 
				movimentacao.getDataHoraInicio() == null && movimentacao.getTipo() == null) {
			
			addMessage(FacesMessage.SEVERITY_ERROR, "Erro. ", "Preencha algum campo");
			showMessage(FacesMessage.SEVERITY_ERROR, "Erro. ", "Preencha algum campo");
			return false;	
		}
	
		
		
		
		return true;
	}
	
	public void exibirAlteracaoMovimentacao() {
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", false);
        options.put("width", 900);
        options.put("height", 500);
        options.put("contentWidth", 880);
        options.put("contentHeight", 480);
		try {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("movimentacaoSelecionada", movimentacaoSelecionada);
			RequestContext.getCurrentInstance().openDialog("dialogAlterarMovimentacao", options, null);
			this.filtrar();
			
			
		} catch (Exception erro) {
			System.out.println("Exception: " + erro.getMessage());
		}	
	
	
}
	
	public String exibirRelatorio() {
		return "relatorioMovimentacao.xhtml?faces´redirect=true";
	}
	
	public void atualizaResultadoMovimentacao() {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterar Movimentação", "Movimentação alterada com sucesso");
		filtrar();
		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}
	
	// menu padrao
		public String exibirCadastroConteiner() {
			return "cadastroConteiner.xhtml?faces-redirect+true";
		}
		public String exibirFiltroConteiner() {
			return "filtroConteiner.xhtml?faces-redirect+true";
		}
		public String exibirCadastroMovimentacao() {
			return "cadastroMovimentacao.xhtml?faces-redirect+true";
		}
		public String exibirFiltroMovimentacao() {
			return "filtroMovimentacao.xhtml?faces-redirect+true";
		}
		//

	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}
	
	private void showMessage(FacesMessage.Severity severidade, String titulo, String msg) {
		FacesMessage message = new FacesMessage(severidade, titulo, msg);
		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}

	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}


	public String getCodigoConteiner() {
		return codigoConteiner;
	}


	public void setCodigoConteiner(String codigoConteiner) {
		this.codigoConteiner = codigoConteiner;
	}


	public Movimentacao getMovimentacaoSelecionada() {
		return movimentacaoSelecionada;
	}


	public void setMovimentacaoSelecionada(Movimentacao movimentacaoSelecionada) {
		this.movimentacaoSelecionada = movimentacaoSelecionada;
	}


	public List<Movimentacao> getListaMovimentacao() {
		return listaMovimentacao;
	}


	public void setListaMovimentacao(List<Movimentacao> listaMovimentacao) {
		this.listaMovimentacao = listaMovimentacao;
	}


	
}
