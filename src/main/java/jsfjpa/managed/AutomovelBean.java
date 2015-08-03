package jsfjpa.managed;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transaction;

import jsfjpa.entity.Automovel;
import jsfjpa.utils.JPAUtil;

@ManagedBean
public class AutomovelBean {
	
	private Automovel automovel = new Automovel();
	private List<Automovel> automoveis;
	private Marca marca;
	
	public void salvar(Automovel automovel){
		
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(automovel);
		em.getTransaction().commit();
		em.close();
	}
	
	public void exclui(Automovel automovel){
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		automovel = em.merge(automovel);
		em.remove(automovel);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Automovel> getAutomoveis(){
		
		if(this.automoveis == null){
			EntityManager em = JPAUtil.getEntityManager();
			Query query = em.createQuery("from Automovel", Automovel.class);
			this.automoveis = query.getResultList();
			em.close();
		}
				
		return this.automoveis;
	}
	

	public Automovel getAutomovel() {
		return automovel;
	}
	
	

	

}

