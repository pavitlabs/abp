package solutions.willow.abp.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import solutions.willow.abp.model.Broker;

/**
 * 
 */
@Stateless
@Path("/brokers")
public class BrokerEndpoint
{
   @PersistenceContext(unitName = "forge-default")
   private EntityManager em;

   @POST
   @Consumes("application/json")
   public Response create(Broker entity)
   {
      em.persist(entity);
      return Response.created(UriBuilder.fromResource(BrokerEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
   }

   @DELETE
   @Path("/{id:[0-9][0-9]*}")
   public Response deleteById(@PathParam("id") Long id)
   {
      Broker entity = em.find(Broker.class, id);
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      em.remove(entity);
      return Response.noContent().build();
   }

   @GET
   @Path("/{id:[0-9][0-9]*}")
   @Produces("application/json")
   public Response findById(@PathParam("id") Long id)
   {
      TypedQuery<Broker> findByIdQuery = em.createQuery("SELECT DISTINCT b FROM Broker b LEFT JOIN FETCH b.geographicalAddresses WHERE b.id = :entityId ORDER BY b.id", Broker.class);
      findByIdQuery.setParameter("entityId", id);
      Broker entity;
      try
      {
         entity = findByIdQuery.getSingleResult();
      }
      catch (NoResultException nre)
      {
         entity = null;
      }
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      return Response.ok(entity).build();
   }

   @GET
   @Produces("application/json")
   public List<Broker> listAll()
   {
      final List<Broker> results = em.createQuery("SELECT DISTINCT b FROM Broker b LEFT JOIN FETCH b.geographicalAddresses ORDER BY b.id", Broker.class).getResultList();
      return results;
   }

   @PUT
   @Path("/{id:[0-9][0-9]*}")
   @Consumes("application/json")
   public Response update(Broker entity)
   {
      entity = em.merge(entity);
      return Response.noContent().build();
   }
}