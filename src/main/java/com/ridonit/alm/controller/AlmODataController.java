package com.ridonit.alm.controller;

import com.ridonit.alm.factory.AlmODataJPAServiceFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.core.rest.ODataRootLocator;
import org.apache.olingo.odata2.core.rest.app.ODataApplication;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Component
@ApplicationPath("/odata")
@Slf4j
public class AlmODataController extends ResourceConfig {
    public AlmODataController(AlmODataJPAServiceFactory serviceFactory, EntityManagerFactory emf) {
        ODataApplication app = new ODataApplication();

        app.getClasses().forEach(c -> {
            if(!ODataRootLocator.class.isAssignableFrom(c)) {
                register(c);
            }
        });

        register(new FmsRootLocator(serviceFactory));
        register(new EntityManagerFilter(emf));
    }

    @Provider
    @Slf4j
    public static class EntityManagerFilter implements ContainerRequestFilter, ContainerResponseFilter {
        public static final String EM_REQUEST_ATTRIBUTE = EntityManagerFilter.class.getName() + "ENTITY_MANAGER";

        public final EntityManagerFactory emf;

        @Context
        private HttpServletRequest httpRequest;

        public EntityManagerFilter(EntityManagerFactory emf) {
            this.emf = emf;
        }

        @Override
        public void filter(ContainerRequestContext ctx) throws IOException {
            log.info(">>> filter(ctx)");
            EntityManager em = this.emf.createEntityManager();
            httpRequest.setAttribute(EM_REQUEST_ATTRIBUTE, em);

            if("GET".equalsIgnoreCase(ctx.getMethod()) == Boolean.FALSE) {
                em.getTransaction().begin();
            }
        }

        @Override
        public void filter(
            ContainerRequestContext requestContext,
            ContainerResponseContext responseContext) throws IOException {
            log.info(">>> filter(requestContext, responseContext)");
            EntityManager em = (EntityManager) httpRequest.getAttribute(EM_REQUEST_ATTRIBUTE);

            if("GET".equalsIgnoreCase(requestContext.getMethod()) == Boolean.FALSE) {
                EntityTransaction transaction = em.getTransaction();
                if(transaction.isActive() == Boolean.TRUE) {
                    if(transaction.getRollbackOnly() == Boolean.FALSE) {
                        transaction.commit();
                    }
                }
            }

            em.close();
        }
    }

    @Path("/")
    @Slf4j
    public static class FmsRootLocator extends ODataRootLocator {
        private AlmODataJPAServiceFactory serviceFactory;

        public FmsRootLocator(AlmODataJPAServiceFactory serviceFactory) {
            this.serviceFactory = serviceFactory;
        }

        @Override
        public ODataServiceFactory getServiceFactory() {
            log.info(">>> getServiceFactory()");
            return this.serviceFactory;
        }
    }
}
