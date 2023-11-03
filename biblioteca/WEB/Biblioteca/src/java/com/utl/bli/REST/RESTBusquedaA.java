package com.utl.bli.REST;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.utl.bli.controller.ControllerLibro;
import com.utl.bli.controller.ControllerUniversidad;
import com.utl.bli.model.Libro;
import com.utl.bli.model.Universidad;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/* @author maria*/
@Path("alumnoLibro")
public class RESTBusquedaA {    
    @GET
    @Path("buscar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscar(@QueryParam("filtro") @DefaultValue("") String filtro) {

        String out = null;
        ControllerLibro cl = null;
        List<Libro> libros = null;

        try {
            cl = new ControllerLibro();
            libros = cl.buscarA(filtro);
            out = new Gson().toJson(libros);
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception\":\"Error interno del servidor.\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @GET
    @Path("buscarB")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscar2(@QueryParam("filtro") @DefaultValue("") String filtro) {

        String out = null;
        ControllerLibro cl = null;
        List<Libro> libros = null;

        try {
            cl = new ControllerLibro();
            libros = cl.buscarB(filtro);
            out = new Gson().toJson(libros);
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception\":\"Error interno del servidor.\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @POST
    @Path("buscar3")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarId(@FormParam("universidad_libro_id") @DefaultValue("") String datos) {

        String out = null;
        ControllerLibro cl = null;
        List<Libro> libros = null;

        try {
            cl = new ControllerLibro();
            libros = cl.buscarA2(datos);
            Libro librob64 = libros.get(0);
            out = "{\"base64\":\""+librob64.getLibro()+"\"}";
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception\":\"Error interno del servidor.\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
