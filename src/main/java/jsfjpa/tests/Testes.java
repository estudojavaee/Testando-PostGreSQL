package jsfjpa.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jsfjpa.entity.Automovel;

public class Testes {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("default");
		
		EntityManager em = emf.createEntityManager();
		
		Automovel auto = new Automovel();
		auto.setAnoFabricacao(2010);
		auto.setModelo("m41");
		auto.setObservacoes("Nunca foi batido");
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.persist(auto);
		
		tx.commit();
		em.close();
		emf.close();
	}

}
