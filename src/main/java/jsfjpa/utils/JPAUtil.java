package jsfjpa.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Cache;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;

public class JPAUtil {
	
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
	
	public static EntityManager getEntityManager(){
		return emf.createEntityManager();
	}
	
	public static Query enableCache(Query query, String region) {
		query.setHint("org.hibernate.cacheable", true);
		query.setHint("org.hibernate.cacheRegion", region);
		return query;
	}
	
	public static void evictCache(EntityManager em, String region){
		try{
			Session session = (Session) em.getDelegate();
			Cache cache = session.getSessionFactory().getCache();
			cache.evictQueryRegion(region);
		}
		catch(Exception e){
			// 	provavelmente a implementação não é o Hibernate
		}
	}
	
	public static Statistics getStatistics(){
		EntityManager em = JPAUtil.getEntityManager();
		Session session = (Session) em.getDelegate();
		SessionFactory sf = session.getSessionFactory();
		Statistics stats = sf.getStatistics();
		return stats;
	}
	
	public static void printStatistics(){
		
		Statistics stats = JPAUtil.getStatistics();
		
		log.info("Qtde de entidades buscadas: " + 	stats.getEntityFetchCount());
		log.info("Qtde de entidades carregadas: " + 	stats.getEntityLoadCount());
		log.info("Qtde de listas buscadas: " + 	stats.getCollectionFetchCount());
		log.info("Qtde de listas carregadas: " + 	stats.getCollectionLoadCount());
		
		double queryCacheHitCount = stats.getQueryCacheHitCount();
		double queryCacheMissCount = stats.getQueryCacheMissCount();
		double totalQueries = queryCacheHitCount + queryCacheMissCount;
		double queryCacheHitRatio = (totalQueries == 0) ? 0 : queryCacheHitCount / totalQueries;
		
		log.info("Qtde de consultas encontradas no cache: " + 	queryCacheHitCount);
		log.info("Qtde de consultas fora do cache: " + 	queryCacheMissCount);
		log.info("Proporção de acerto do cache: " + queryCacheHitRatio);
		log.info("Qtde de consultas executadas: " +	stats.getQueryExecutionCount());
		
		String[] queries = stats.getQueries();
		for (int i = 0; i < queries.length; i++) {
			log.info("Consulta " + i + ": " + queries[i]);
		}
		
		log.info("Query + lenta: " +
		
		stats.getQueryExecutionMaxTimeQueryString());
		stats.clear();
	}
	
}
