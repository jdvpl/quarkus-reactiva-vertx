package org.jdvpl.services;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jdvpl.entities.Customer;
import org.jdvpl.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Controller {

    @Inject
    CustomerRepository customerRepository;

    @POST
    public Response create(Customer customer) {
        try{
            Customer savedCustomer = customerRepository.save(customer);
            System.out.println(savedCustomer);
            return Response.status(Response.Status.CREATED).entity(savedCustomer).build();
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }

    }

    @PUT
    public Response update(Customer customer) {
        customerRepository.update(customer);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        customerRepository.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return customerRepository.findById(id)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @GET
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @GET
    @Path("/code/{code}")
    public Response findByCode(@PathParam("code") String code) {
        return customerRepository.findByCode(code)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }
}
