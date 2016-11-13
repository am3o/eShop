package de.hska.iwi.microservice.catalog.client.api;

import de.hska.iwi.microservice.catalog.entity.Category;
import feign.Headers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by ameo on 13.11.16.
 */
@Path(value = "/")
public interface CategoryService {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<Category> getAllCategories();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Headers("Content-Type: application/json")
    Category createCategory(Category category);

    @PUT
    @Path("/{categoryId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Category updateCategory(Category category);

    @GET
    @Path("/{categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    Category getCategory(@PathParam("categoryId") int categoryId);

    @DELETE
    @Path("/{categoryId}")
    boolean deleteCategory(@PathParam("categoryId") int categoryId);
}
