package com.ridonit.alm.factory;

import com.ridonit.alm.controller.AlmODataController;
import lombok.extern.slf4j.Slf4j;
import org.apache.olingo.odata2.api.processor.ODataContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAServiceFactory;
import org.apache.olingo.odata2.jpa.processor.api.exception.ODataJPARuntimeException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class AlmODataJPAServiceFactory extends ODataJPAServiceFactory {

    public AlmODataJPAServiceFactory() {
        setDetailErrors(true);
    }

    @Override
    public ODataJPAContext initializeODataJPAContext() throws ODataJPARuntimeException {
        log.info(">>> initializeODataJPAContext()");

        ODataJPAContext ctx = getODataJPAContext();
        ODataContext otx = ctx.getODataContext();
        HttpServletRequest request = (HttpServletRequest) otx.getParameter(ODataContext.HTTP_SERVLET_REQUEST_OBJECT);
        EntityManager em = (EntityManager) request.getAttribute(AlmODataController.EntityManagerFilter.EM_REQUEST_ATTRIBUTE);

        ctx.setEntityManager(new EntityManagerWrapper(em));
        ctx.setPersistenceUnitName("default");
        ctx.setContainerManaged(Boolean.TRUE);
        return ctx;
    }

    static class EntityManagerWrapper implements EntityManager {
        private EntityManager entityManager;

        public EntityManagerWrapper(EntityManager entityManager) {
            this.entityManager = entityManager;
        }

        @Override
        public void persist(Object entity) {
            entityManager.persist(entity);
        }

        @Override
        public <T> T merge(T entity) {
            return entityManager.merge(entity);
        }

        @Override
        public void remove(Object entity) {
            entityManager.remove(entity);
        }

        @Override
        public <T> T find(Class<T> entityClass, Object primaryKey) {
            return entityManager.find(entityClass, primaryKey);
        }

        @Override
        public <T> T find(Class<T> entityClass, Object primaryKey, Map<String, Object> properties) {
            return entityManager.find(entityClass, primaryKey, properties);
        }

        @Override
        public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockModeType) {
            return entityManager.find(entityClass, primaryKey, lockModeType);
        }

        @Override
        public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockModeType, Map<String, Object> properties) {
            return entityManager.find(entityClass, primaryKey, lockModeType, properties);
        }

        @Override
        public <T> T getReference(Class<T> entityClass, Object primaryKey) {
            return entityManager.getReference(entityClass, primaryKey);
        }

        @Override
        public void flush() {
            entityManager.flush();
        }

        @Override
        public void setFlushMode(FlushModeType flushModeType) {
            entityManager.setFlushMode(flushModeType);
        }

        @Override
        public FlushModeType getFlushMode() {
            return entityManager.getFlushMode();
        }

        @Override
        public void lock(Object entity, LockModeType lockModeType) {
            entityManager.lock(entity, lockModeType);
        }

        @Override
        public void lock(Object entity, LockModeType lockModeType, Map<String, Object> properties) {
            entityManager.lock(entity, lockModeType, properties);
        }

        @Override
        public void refresh(Object entity) {
            entityManager.refresh(entity);
        }

        @Override
        public void refresh(Object entity, Map<String, Object> properties) {
            entityManager.refresh(entity, properties);
        }

        @Override
        public void refresh(Object entity, LockModeType lockModeType) {
            entityManager.refresh(entity, lockModeType);
        }

        @Override
        public void refresh(Object entity, LockModeType lockModeType, Map<String, Object> properties) {
            entityManager.refresh(entity, lockModeType, properties);
        }

        @Override
        public void clear() {
            entityManager.clear();
        }

        @Override
        public void detach(Object entity) {
            entityManager.detach(entity);
        }

        @Override
        public boolean contains(Object entity) {
            return entityManager.contains(entity);
        }

        @Override
        public LockModeType getLockMode(Object entity) {
            return entityManager.getLockMode(entity);
        }

        @Override
        public void setProperty(String propertyName, Object value) {
            entityManager.setProperty(propertyName, value);
        }

        @Override
        public Map<String, Object> getProperties() {
            return entityManager.getProperties();
        }

        @Override
        public Query createQuery(String queryString) {
            return entityManager.createQuery(queryString);
        }

        @Override
        public <T> TypedQuery<T> createQuery(CriteriaQuery<T> criteriaQuery) {
            return entityManager.createQuery(criteriaQuery);
        }

        @Override
        public Query createQuery(CriteriaUpdate criteriaUpdate) {
            return entityManager.createQuery(criteriaUpdate);
        }

        @Override
        public Query createQuery(CriteriaDelete criteriaDelete) {
            return entityManager.createQuery(criteriaDelete);
        }

        @Override
        public <T> TypedQuery<T> createQuery(String queryString, Class<T> resultClass) {
            return entityManager.createQuery(queryString, resultClass);
        }

        @Override
        public Query createNamedQuery(String sqlString) {
            return entityManager.createNamedQuery(sqlString);
        }

        @Override
        public <T> TypedQuery<T> createNamedQuery(String sqlString, Class<T> entityClass) {
            return entityManager.createNamedQuery(sqlString, entityClass);
        }

        @Override
        public Query createNativeQuery(String sqlString) {
            return entityManager.createNativeQuery(sqlString);
        }

        @Override
        public Query createNativeQuery(String sqlString, Class entityClass) {
            return entityManager.createNativeQuery(sqlString, entityClass);
        }

        @Override
        public Query createNativeQuery(String sqlString, String sqlString1) {
            return entityManager.createNativeQuery(sqlString, sqlString1);
        }

        @Override
        public StoredProcedureQuery createNamedStoredProcedureQuery(String sqlString) {
            return entityManager.createNamedStoredProcedureQuery(sqlString);
        }

        @Override
        public StoredProcedureQuery createStoredProcedureQuery(String sqlString) {
            return entityManager.createStoredProcedureQuery(sqlString);
        }

        @Override
        public StoredProcedureQuery createStoredProcedureQuery(String sqlString, Class... entityClasses) {
            return entityManager.createStoredProcedureQuery(sqlString, entityClasses);
        }

        @Override
        public StoredProcedureQuery createStoredProcedureQuery(String sqlString, String... sqlStrings) {
            return createStoredProcedureQuery(sqlString, sqlStrings);
        }

        @Override
        public void joinTransaction() {
            entityManager.joinTransaction();
        }

        @Override
        public boolean isJoinedToTransaction() {
            return entityManager.isJoinedToTransaction();
        }

        @Override
        public <T> T unwrap(Class<T> entityClass) {
            return entityManager.unwrap(entityClass);
        }

        @Override
        public Object getDelegate() {
            return entityManager.getDelegate();
        }

        @Override
        public void close() {
            entityManager.close();
        }

        @Override
        public boolean isOpen() {
            return entityManager.isOpen();
        }

        @Override
        public EntityTransaction getTransaction() {
            return entityManager.getTransaction();
        }

        @Override
        public EntityManagerFactory getEntityManagerFactory() {
            return entityManager.getEntityManagerFactory();
        }

        @Override
        public CriteriaBuilder getCriteriaBuilder() {
            return entityManager.getCriteriaBuilder();
        }

        @Override
        public Metamodel getMetamodel() {
            return entityManager.getMetamodel();
        }

        @Override
        public <T> EntityGraph<T> createEntityGraph(Class<T> entityClass) {
            return entityManager.createEntityGraph(entityClass);
        }

        @Override
        public EntityGraph<?> createEntityGraph(String graphName) {
            return entityManager.createEntityGraph(graphName);
        }

        @Override
        public EntityGraph<?> getEntityGraph(String graphName) {
            return entityManager.getEntityGraph(graphName);
        }

        @Override
        public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> entityClass) {
            return entityManager.getEntityGraphs(entityClass);
        }
    }
}
