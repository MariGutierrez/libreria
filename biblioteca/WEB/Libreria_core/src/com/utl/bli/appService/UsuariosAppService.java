package com.utl.bli.appService;

import com.utl.bli.CQRS.UsuarioCQRS;
import com.utl.bli.controller.usuarioDao.UsuarioDao;
import com.utl.bli.model.Usuario;
import com.utl.bli.viewModel.UsuarioPublicViewModel;
import java.util.ArrayList;
import java.util.List;

public class UsuariosAppService {

    public String registroUsuario(Usuario u) throws Exception {
        UsuarioCQRS cqrs = new UsuarioCQRS();
        Usuario us = cqrs.insert(u);
        EmailService es = new EmailService();
        if (us.getId_usuario() != 0) {
            es.email(us.getNombre_usuario(), "Confirma tu cuenta");
            return "1";
        } else {
            return "0";
        }
    }
    
    public String upUsuario (Usuario u) throws Exception {
        UsuarioCQRS cqrs = new UsuarioCQRS();
        Usuario us = cqrs.update(u);
        EmailService es = new EmailService();
        if (us.getId_usuario() != 0) {
            es.email(us.getNombre_usuario(), "Confirma los cambios en tu cuenta");
            return "1";
        } else {
            return "0";
        }
    }

    public void recuperarContrasenia(String nombre_usuario) throws Exception {
        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario usu = usuarioDao.BuscarUser(nombre_usuario);
        EmailService es = new EmailService();

        if (usu != null) {
            es.email(nombre_usuario, "Recuperar contraseña");
        } else {
            throw new Exception("El usuario no existe");
        }
    }

    public void reestablecerContrasenia() {

    }

    public Usuario buscarPorCorreo(String nombre_usuario) throws Exception {
        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario u = usuarioDao.BuscarUser(nombre_usuario);
        return u;
    }

    public UsuarioPublicViewModel buscarPorPublic(String nombre_usuario) throws Exception {
        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario u = usuarioDao.BuscarUser(nombre_usuario);
        UsuarioPublicViewModel usr = new UsuarioPublicViewModel(u.getId_usuario() + "", u.getNombre_usuario());
        return usr;
    }

    public List<UsuarioPublicViewModel> buscarPorPublicAll() throws Exception {
        UsuarioDao usuarioDao = new UsuarioDao();
        List<Usuario> usuarios = usuarioDao.BuscarUserAll();

        List<UsuarioPublicViewModel> usuariosPublic = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            UsuarioPublicViewModel usr = new UsuarioPublicViewModel(
                    String.valueOf(usuario.getId_usuario()),
                    usuario.getNombre_usuario()
            );
            usuariosPublic.add(usr);
        }

        return usuariosPublic;
    }
}
