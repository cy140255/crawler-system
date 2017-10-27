package com.util.dao;

import com.util.entity.AggregateRoot;
import jersey.repackaged.com.google.common.collect.Lists;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class BaseDao implements IBaseDao {

	@PersistenceContext
	public EntityManager em;

	public void persist(AggregateRoot entity) {
		em.persist(entity);
	}

	public void remove(AggregateRoot entity) {
		em.remove(entity);
	}


	public <T extends AggregateRoot> T findById(Class<T> entityClass,
                                                long primaryKey) {
		try {
			return em.find(entityClass, primaryKey);
		} catch (NoResultException e) {
			return null;
		}
	}

	public <T extends AggregateRoot> T find(Class<T> entityClass, String field,
                                            Object value) {
		try {
			String jpql = String.format("SELECT a FROM %s a WHERE a.%s = '%s'",
					entityClass.getSimpleName(), field, value);
			return em.createQuery(jpql, entityClass).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public <T extends AggregateRoot> List<T> finds(Class<T> entityClass,
                                                   String field, Object value) {
		String jpql = String.format("SELECT a FROM %s a WHERE %s = '%s'",
				entityClass.getSimpleName(), field, value);
		return em.createQuery(jpql, entityClass).getResultList();
	}

	public <T extends AggregateRoot> List<T> findLike(Class<T> entityClass,
                                                      String field, Object value) {
		String jpql = String.format("SELECT a FROM %s a WHERE %s LIKE '%s'",
				entityClass.getSimpleName(), field, "%" + value + "%");
		TypedQuery<T> query = em.createQuery(jpql, entityClass);
		query.setParameter("value", "%" + value + "%");
		return query.getResultList();
	}


	public <T extends AggregateRoot> T findById(Class<T> entityClass,
                                                String primaryKey) {
		try {
			return em.find(entityClass, primaryKey);
		} catch (NoResultException e) {
			return null;
		}
	}


	public <T extends AggregateRoot> List<T> findByIds(Class<T> entityClass,
                                                       List primaryKeys) {
		List<T> list = Lists.newArrayList();
		for (Object pk : primaryKeys) {
			T entity = null;
			if (pk instanceof Long) {
				entity = findById(entityClass, (Long) pk);
			} else if (pk instanceof String) {
				entity = findById(entityClass, (String) pk);
			}
			if (entity != null) {
				list.add(entity);
			}
		}
		return list;
	}


	public <T extends AggregateRoot> List<T> findAll(Class<T> clazz) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(clazz);
		cq.from(clazz);
		TypedQuery<T> tq = em.createQuery(cq);
		return tq.getResultList();
	}


	public <T extends AggregateRoot> T getById(Class<T> entityClass,
                                               long primaryKey) {
		return em.find(entityClass, primaryKey);
	}


	public <T extends AggregateRoot> T getById(Class<T> entityClass,
                                               String primaryKey) {
		return em.find(entityClass, primaryKey);
	}


	public void executeUpdate(String sql) {
		em.createNativeQuery(sql).executeUpdate();
	}


	public void detach(AggregateRoot entity) {
		em.detach(entity);
	}


	public JdbcTemplate getJdbcTemplate() {
		EntityManagerFactoryInfo info = (EntityManagerFactoryInfo) em
				.getEntityManagerFactory();
		return new JdbcTemplate(info.getDataSource());
	}

}
