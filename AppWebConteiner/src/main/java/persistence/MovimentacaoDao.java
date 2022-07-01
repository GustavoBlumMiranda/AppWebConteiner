package persistence;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import domain.Conteiner;
import domain.Movimentacao;
import persistence.generics.GenericaDao;

@Named
@RequestScoped
public class MovimentacaoDao extends GenericaDao<Movimentacao, Long> {
	
	@PersistenceContext
	protected EntityManager em;
	
	public List<Movimentacao> consultaMovimentacao(Movimentacao movimentacao){
		List<Movimentacao> listaMovimentacao = new ArrayList<Movimentacao>();
		
		StringBuilder hql = new StringBuilder();
		hql.append(" from Movimentacao m where 1 > 0 ");
		
		if(movimentacao.getConteiner() != null) {
			hql.append(" and m.conteiner = :paramConteiner ");
		}
		if(movimentacao.getDataHoraInicio() != null) {
			hql.append(" and m.dataHoraInicio = :paramDataHoraInicio ");
		}
		if(movimentacao.getDataHoraFim() != null) {
			hql.append(" and m.dataHoraInicio = :paramDataHoraInicio ");
		}
		
		if(movimentacao.getTipo() != null) {
			hql.append(" and m.tipo = :paramTipo ");
		}
		
		TypedQuery q = em.createQuery(hql.toString(), Movimentacao.class);
		
		if(movimentacao.getConteiner() != null) {
			q.setParameter("paramConteiner", movimentacao.getConteiner());
		}
		if(movimentacao.getDataHoraInicio() != null) {
			q.setParameter("paramDataHoraInicio", movimentacao.getDataHoraInicio());
		}
		if(movimentacao.getDataHoraFim() != null) {
			q.setParameter("paramDataHoraFimo", movimentacao.getDataHoraFim());
		}
		
		if(movimentacao.getTipo() != null) {
			q.setParameter("paramTipo", movimentacao.getTipo());
		}
		
		if(q.getResultList() != null && q.getResultList().size() > 0) {
			listaMovimentacao = q.getResultList();
		}
		
		return listaMovimentacao;
	}
	
	
}
