/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utl.bli.REST.RESTUsuarioDao;

import com.google.gson.Gson;
import com.utl.bli.controller.ControllerUsuario;
import com.utl.bli.controller.ControllerLoginAdministrador;
import com.utl.bli.model.Alumno;
import com.utl.bli.model.Usuario;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("Usu")
public class RESTUsuario {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("insert")
    public Response inser(@FormParam("datosUsu") @DefaultValue("") String datoUsu) {
        String out = null;
        Gson gson = new Gson();
        Usuario usu = null;
        ControllerUsuario ca = new ControllerUsuario();

        try {
            usu = gson.fromJson(datoUsu, Usuario.class);
            String res = ca.insertarUsuario(usu);
            if (res.equals("0")) {
                out = "{\"error\": 'Ha faltado alguno de los campos'}";
            } else {
                out = gson.toJson(usu);
            }
        } catch (Exception e) {
            e.printStackTrace();
            out = """
                  {"result":"error"}
                  """;
        }

        return Response.status(Response.Status.OK)
                .entity(out).build();
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("update")
    public Response update(@FormParam("datosUsu") @DefaultValue("") String datoUsu) {
        String out = null;
        Gson gson = new Gson();
        Usuario usu = null;
        ControllerUsuario ca = new ControllerUsuario();

        try {
            usu = gson.fromJson(datoUsu, Usuario.class);
            String res = ca.actuUsuario(usu);
            if (res.equals("0")) {
                out = "{\"error\": 'Ha faltado alguno de los campos'}";
            } else {
                out = gson.toJson(usu);
            }
        } catch (Exception e) {
            e.printStackTrace();
            out = """
                  {"result":"error"}
                  """;
        }

        return Response.status(Response.Status.OK)
                .entity(out).build();
    }

}
