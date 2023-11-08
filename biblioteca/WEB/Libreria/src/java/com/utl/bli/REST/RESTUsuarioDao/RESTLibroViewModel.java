
package com.utl.bli.REST.RESTUsuarioDao;

import com.google.gson.Gson;
import com.utl.bli.controller.ControllerLibro;
import com.utl.bli.controller.ControllerUsuario;
import com.utl.bli.viewModel.LibroPublicViewModel;
import com.utl.bli.viewModel.UsuarioPublicViewModel;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("busc")
public class RESTLibroViewModel {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("LVMPublic")
    public Response libroPublic(@FormParam("nombre_libro") @DefaultValue("") String nombre_Libro) {

        String out = null;
        Gson gson = new Gson();
        ControllerLibro cl = new ControllerLibro();
        List<LibroPublicViewModel> lib;
        try {
            lib = cl.buscLib_vmAll(nombre_Libro);
            if (lib != null) {
                out = new Gson().toJson(lib);
            } else {
                out = "{\"error\": 'Usuario y/o contraseña incorrectos'}";
            }
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception\":\"Error interno del servidor.\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    
}
