package com.utl.bli.controller;

import com.utl.bli.CQRS.LibroCQRS;
import com.utl.bli.appService.LIbrosAppService;
import com.utl.bli.bd.ConexionMySQL;
import com.utl.bli.model.Libro;
import com.utl.bli.model.Universidad;
import com.utl.bli.viewModel.LibroPublicViewModel;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/*@author Maria*/
public class ControllerLibro {
    
    public List<LibroPublicViewModel> buscLib_vmAll(String nombre_libro) throws Exception {

        LIbrosAppService laps = new LIbrosAppService();
        List<LibroPublicViewModel> lib = laps.buscarPorPublic(nombre_libro);
        if (lib != null) {
                return lib;
        } else {
            return lib;
        }
    }
    
    public Libro insertarLibro(int id_universidad, String titulo, String autor, String editorial, 
             String idioma, String genero, int no_paginas, String libro, boolean derecho_autor ) throws SQLException, Exception {
        
        LIbrosAppService appLibro = new LIbrosAppService();
        Libro lib = appLibro.registroLibro(id_universidad, titulo, autor, editorial, idioma, 
                genero, no_paginas, libro, derecho_autor);
        return lib;
    }
    
    public Libro actualizarLibro(int id_universidad, String titulo, String autor, String editorial, 
             String idioma, String genero, int no_paginas, String libro, boolean derecho_autor, int id_libro ) throws SQLException, Exception {
        
        LIbrosAppService appLibro = new LIbrosAppService();
        Libro lib = appLibro.actualizarLibro(id_universidad, titulo, autor, editorial, idioma, 
                genero, no_paginas, libro, derecho_autor, id_libro);
        return lib;
    }
        
    public List<Libro> buscar(String filtro) throws Exception {
        LIbrosAppService appLibro = new LIbrosAppService();
        List<Libro> li = appLibro.buscarLibro2(filtro);
        if (li == null) {
            throw new Exception("No se encontraron considencias con: " + filtro);
        }else{
            return li;
        }
    }
    
    public List<Libro> getAll(String filtro) throws Exception {
        LIbrosAppService appLibro = new LIbrosAppService();
        return appLibro.getAll(filtro);
    } 
}
 