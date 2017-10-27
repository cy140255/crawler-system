package com.util.dao;

import com.util.entity.AggregateRoot;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface IBaseDao {
	void persist(AggregateRoot entity);

	void remove(AggregateRoot entity);

	<T extends AggregateRoot> T findById(Class<T> entityClass, long primaryKey);

	<T extends AggregateRoot> T findById(Class<T> entityClass,
                                         String primaryKey);
	<T extends AggregateRoot> T find(Class<T> entityClass, String field, Object value);

    <T extends AggregateRoot> List<T> finds(Class<T> entityClass, String field, Object value);

    <T extends AggregateRoot> List<T> findLike(Class<T> entityClass, String field, Object value);

    <T extends AggregateRoot> List<T> findByIds(Class<T> entityClass, List primaryKeys);

	<T extends AggregateRoot> List<T> findAll(Class<T> clazz);

	<T extends AggregateRoot> T getById(Class<T> entityClass, long primaryKey);

	<T extends AggregateRoot> T getById(Class<T> entityClass,
                                        String primaryKey);

	void executeUpdate(String sql);

	void detach(AggregateRoot entity);

	JdbcTemplate getJdbcTemplate();
}
