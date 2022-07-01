package persistence;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import domain.Conteiner;
import persistence.generics.GenericaDao;

@Named
@RequestScoped
public class ConteinerDao extends GenericaDao<Conteiner, Long> {
	
	@PersistenceContext
	protected EntityManager em;
	
	public List<Conteiner> consultaConteiner(Conteiner conteiner){
		List<Conteiner> listaConteiner = new ArrayList<Conteiner>();
		
		StringBuilder hql = new StringBuilder();
		hql.append("from Conteiner c where 1 > 0 ");
		if(conteiner.getCategoria() != null) {
			hql.append("and c.categoria = :paramCategoria ");
		}
		if(conteiner.getCliente() != null) {
			hql.append("and c.cliente = :paramCliente ");
		}
		if(conteiner.getNumero() != null) {
			hql.append("and c.numero = :paramNumero ");
		}
		if(conteiner.getStatus() != null) {
			hql.append("and c.status = :paramStatus ");
		}
		if(conteiner.getTipo() != null) {
			hql.append("and c.tipo = :paramTipo ");
		}
		
		TypedQuery q = em.createQuery(hql.toString(), Conteiner.class);
		
		if(conteiner.getCategoria() != null) {
			q.setParameter("paramCategoria", conteiner.getCategoria());
		}
		if(conteiner.getCliente() != null) {
			q.setParameter("paramCliente", conteiner.getCliente());
		}
		if(conteiner.getNumero() != null) {
			q.setParameter("paramNumero", conteiner.getNumero());
		}
		if(conteiner.getStatus() != null) {
			q.setParameter("paramStatus", conteiner.getStatus());
		}
		if(conteiner.getTipo() != null) {
			q.setParameter("paramTipo", conteiner.getTipo());
		}
		
		if(q.getResultList() != null && q.getResultList().size() > 0) {
			listaConteiner = q.getResultList();
		}
		
		return listaConteiner;
	}
	
	
}
