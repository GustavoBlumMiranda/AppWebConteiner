package view.movimentacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
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
public class RelatorioMovimentacao {
	private Movimentacao movimentacao = new Movimentacao() ;
	
	private Movimentacao movimentacaoSelecionada = new Movimentacao() ;
	
	private String codigoConteiner;
	
	private List<Movimentacao> listaMovimentacao = new ArrayList<Movimentacao>();
	private List<Conteiner> listaConteiner = new ArrayList<Conteiner>();
	
	@Inject
	MovimentacaoDao movimentacaoDao;
	
	@Inject
	ConteinerDao conteinerDao;
	
	@PostConstruct
	public void filtrar() {
		listaMovimentacao = movimentacaoDao.consultaMovimentacao(movimentacao);	
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
		//

	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
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
