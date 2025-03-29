package resteasyjackson;

import entities.Product;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import repositories.ProductRepository;

import java.util.List;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductApi {
    @Inject
    ProductRepository pr;

    @GET
    public List<Product> list() {
        return pr.listProduct();
    }

    @GET
    @Path("/{id}")
    public Product getById(@PathParam("id") Long id) {
        return pr.findProduct(id);
    }

    @POST
    @Path("create")
    public Response add(Product p) {
        pr.createdProduct(p);
        return Response.status(Response.Status.CREATED).entity(p).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        Product product = pr.findProduct(id);
        if (product == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Producto no encontrado").build();
        }
        pr.deleteProduct(product);
        return Response.ok().entity("Producto eliminado").build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Product updatedProduct) {
        Product product = pr.updateProduct(id, updatedProduct);
        if (product == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Producto no encontrado").build();
        }
        return Response.ok(product).build();
    }


}
