package persistence.generics;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public  class GenericaDao<T extends Serializable, PK extends Serializable> {
	
	protected Class<T> persistentClass = null;


	private EntityManager entityManager;
	
	
	@SuppressWarnings("unchecked")
	public GenericaDao() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("siniex-pu");
		entityManager  = factory.createEntityManager();
		persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	
	

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public EntityManager getEntityManager() {	
		return entityManager;
	}

	public T insertComTransacao(T entity) throws Exception {
		try{
			
			getEntityManager().getTransaction().begin();
			getEntityManager().persist(entity);
			getEntityManager().getTransaction().commit();
			getEntityManager().clear();
		}catch (Exception erro){
			getEntityManager().getTransaction().rollback();
			throw erro;
		}

		return entity;
	}
	
	
	public T insert(T entity) throws Exception {
		getEntityManager().persist(entity);
		return entity;
	}

	public void remover(T entity) throws Exception {
		getEntityManager().remove(getEntityManager().contains(entity) ? entity : getEntityManager().merge(entity));
	}
	
	public void removerComTransacao(T entity) throws Exception {
		getEntityManager().getTransaction().begin();
		getEntityManager().remove(getEntityManager().contains(entity) ? entity : getEntityManager().merge(entity));
		getEntityManager().getTransaction().commit();
		getEntityManager().clear();
	}

	public T update(T entity) throws Exception {
		entity = getEntityManager().merge(entity);
		return entity;
	}
	
	public T updateComTransacao(T entity) throws Exception {
		try{
			getEntityManager().getTransaction().begin();
			entity = getEntityManager().merge(entity);
			getEntityManager().getTransaction().commit();
			getEntityManager().clear();
		}catch (Exception erro){
			getEntityManager().getTransaction().rollback();
			throw erro;
		}
		return entity;
	}
	
	public T updateAssincrono(EntityManager emg, T entity) throws Exception {
		try{
			emg.getTransaction().begin();
			entity = emg.merge(entity);
			emg.getTransaction().commit();
			emg.clear();
			emg = null;
		}catch (Exception erro){
			erro.printStackTrace();
			emg.getTransaction().rollback();
			emg = null;
			throw erro;
		}
		return entity;
	}
	
	

	public T consultar(PK id) {
		T entity = getEntityManager().find(persistentClass, id);
		return entity;
	}
	
	

	
}








