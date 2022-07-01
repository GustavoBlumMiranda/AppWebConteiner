package view.movimentacao;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import commons.util.DataUtil;
import domain.Conteiner;
import domain.Movimentacao;
import persistence.ConteinerDao;
import persistence.MovimentacaoDao;

@ManagedBean
@ViewScoped
public class ControleMovimentacao {
	private Movimentacao movimentacao = new Movimentacao() ;
	
	private String codigoConteiner;
	
	private List<Conteiner> listaConteiner = new ArrayList<Conteiner>();
	
	@Inject
	MovimentacaoDao movimentacaoDao;
	
	@Inject
	ConteinerDao conteinerDao;
	
	public void salvar() {
		if(this.criticaMovimentacao(movimentacao)){
			try {
				movimentacaoDao.insertComTransacao(movimentacao);
				addMessage(FacesMessage.SEVERITY_INFO, "Sucesso. ", "Movimentação Cadastrada");
				showMessage(FacesMessage.SEVERITY_INFO, "Erro. ", "Movimentação Cadastrada");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public boolean criticaMovimentacao(Movimentacao movimentacao) {
		if(codigoConteiner == null || movimentacao.getDataHoraFim() == null || 
				movimentacao.getDataHoraInicio() == null || movimentacao.getTipo() == null) {
			
			addMessage(FacesMessage.SEVERITY_ERROR, "Erro. ", "Campos em branco");
			showMessage(FacesMessage.SEVERITY_ERROR, "Erro. ", "Campos em branco");
			return false;	
		}
		
		
		Conteiner conteinerSoComNumero = new Conteiner();
		conteinerSoComNumero.setNumero(codigoConteiner);
		listaConteiner = conteinerDao.consultaConteiner(conteinerSoComNumero);
		if(listaConteiner == null || listaConteiner.size() < 1) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Erro. ", "Conteiner Não Cadastrado");
			showMessage(FacesMessage.SEVERITY_ERROR, "Erro. ", "Conteiner Não Cadastrado");
			return false;
		}
		if(listaConteiner != null && listaConteiner.size() > 1) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Erro. ", "Conteiner Duplicado");
			showMessage(FacesMessage.SEVERITY_ERROR, "Erro. ", "Conteiner Duplicado");
			return false;
		}
		if(movimentacao.getDataHoraInicio().after(movimentacao.getDataHoraFim())) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Erro. ", "O inicio de uma movimentação não pode ocorrer após seu fim");
			showMessage(FacesMessage.SEVERITY_ERROR, "Erro. ", "O inicio de uma movimentação não pode ocorrer após seu fim");
			return false;
		}
	
		movimentacao.setConteiner(listaConteiner.get(0));
		
		
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
}
