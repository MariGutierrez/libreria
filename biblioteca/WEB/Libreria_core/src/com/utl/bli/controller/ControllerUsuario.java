/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utl.bli.controller;

import com.utl.bli.CQRS.UsuarioCQRS;
import com.utl.bli.appService.UsuariosAppService;
import com.utl.bli.model.Usuario;
import com.utl.bli.viewModel.UsuarioPublicViewModel;
import java.util.List;

public class ControllerUsuario {
    public String insertarUsuario(Usuario u) throws Exception {
       UsuariosAppService ups = new UsuariosAppService();
       String res = ups.registroUsuario(u);
       return res;
    }
    
    public String actuUsuario(Usuario u) throws Exception {
       UsuariosAppService ups = new UsuariosAppService();
       String res = ups.upUsuario(u);
       ups.reestablecerContrasenia();
       return res;
    }
    
    public Usuario buscUs(String nombre_usuario) throws Exception {

        UsuariosAppService cl = new UsuariosAppService();
        Usuario usu = cl.buscarPorCorreo(nombre_usuario);

        if (usu != null) {
                return usu;
        } else {
            return usu;
        }
    }
    
    public UsuarioPublicViewModel buscUs_vm(String nombre_usuario) throws Exception {

        UsuariosAppService cl = new UsuariosAppService();
        UsuarioPublicViewModel usu = cl.buscarPorPublic(nombre_usuario);
        if (usu != null) {
                return usu;
        } else {
            return usu;
        }
    }
    
    public List<UsuarioPublicViewModel> buscUs_vmAll() throws Exception {

        UsuariosAppService cl = new UsuariosAppService();
        List<UsuarioPublicViewModel> usu = cl.buscarPorPublicAll();
        if (usu != null) {
                return usu;
        } else {
            return usu;
        }
    }
}
