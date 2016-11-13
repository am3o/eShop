package de.hska.iwi.microservice.catalog.client.api;

import de.hska.iwi.microservice.catalog.entity.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by ameo on 13.11.16.
 */
@Path(value = "/")
public interface ProductService {
    @POST
    @Path(value = "/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Product createProduct(Product product);

    @PUT
    @Path(value = "/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Product updateProduct(@PathParam(value = "id")int id, Product product);

    @DELETE
    @Path(value = "/{id}")
    boolean deleteProduct(@PathParam(value = "id") int id);

    @GET
    @Path(value = "/")
    @Produces(MediaType.APPLICATION_JSON)
    List<Product> getProducts();

    @GET
    @Path(value = "/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Product getProduct(@PathParam(value = "id") int id);

    @GET
    @Path(value = "/category/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    List<Product> getProductsByCategorieId(@PathParam(value = "id") int id);
}
