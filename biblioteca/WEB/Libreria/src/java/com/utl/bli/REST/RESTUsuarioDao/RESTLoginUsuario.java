package com.utl.bli.REST.RESTUsuarioDao;

import com.google.gson.Gson;
import com.utl.bli.controller.usuarioDao.ControllerLoginUsuario;
import com.utl.bli.model.Usuario;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/* @author maria*/
@Path("Busc")
public class RESTLoginUsuario {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Us")
    public Response logIn(@FormParam("nombre_usuario") @DefaultValue("") String nombre_usuario,
            @FormParam("contrasenia") @DefaultValue("") String contrasenia) {

        String out = null;
        Gson gson = new Gson();
        ControllerLoginUsuario cla = new ControllerLoginUsuario();
        String usu;
        try {
            usu = cla.login(nombre_usuario, contrasenia);
            if (usu.equals("0")) {
                out = "{\"error\": \"Usuario incorrecto\"}";
            } else if (usu.equals("1")) {
                out = "{\"error\": \"Contraseña incorrecta\"}";
            } else if (usu.equals("Usuario")) {
                out = "{\"Rol\": \"" + usu + "\"}";
            } else {
                out = "{\"error\": \"Tipo de usuario incorrecto\"}";
            }
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception\":\"Error interno del servidor.\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();
    }
}
