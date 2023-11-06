package com.utl.bli.controller;

import com.utl.bli.CQRS.LibroCQRS;
import com.utl.bli.appService.LIbrosAppService;
import com.utl.bli.bd.ConexionMySQL;
import com.utl.bli.model.Libro;
import com.utl.bli.model.Universidad;
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
    
    public Libro insertarLibro(int id_universidad, String titulo, String autor, String editorial, 
             String idioma, String genero, int no_paginas, String libro, boolean derecho_autor ) throws SQLException, Exception {
        
        LIbrosAppService appLibro = new LIbrosAppService();
        Libro lib = appLibro.registroLibro(id_universidad, titulo, autor, editorial, idioma, 
                genero, no_paginas, libro, derecho_autor);
        return lib;
    }
    
    /*public Libro buscar(String filtro) throws Exception {
        LibroCQRS lib = new LibroCQRS();
        Libro li = lib.buscarLibro(filtro);
        if (li == null) {
            throw new Exception("No se encontraron considencias con: " + filtro);
        }
        return li;
    }*/
    
    public List<Libro> buscar(String filtro) throws Exception {
        LibroCQRS lib = new LibroCQRS();
        if (lib == null) {
            throw new Exception("No se encontraron considencias con: " + filtro);
        }
        return lib.buscarLibro2(filtro);
    }
    
    public List<Libro> getAll(String filtro) throws Exception {
        LibroCQRS lib = new LibroCQRS();
        return lib.getAll(filtro);
    }
    
    
    
    public void delete(int id) throws Exception {

        String sql = "UPDATE libro SET estatus = 0 WHERE id_libro= " + id;

        ConexionMySQL connMySQL = new ConexionMySQL();

        Connection conn = connMySQL.open();

        Statement cstmt = conn.createStatement();
        cstmt.executeUpdate(sql);
        cstmt.close();
        connMySQL.close();

    }
    
    
    
    public List<Libro> buscarA(String filtro) throws Exception {
        String sql = "SELECT l.*, u.id_universidad, u.nombre_universidad, u.pais FROM libro l INNER JOIN universidad u ON l.id_universidad = u.id_universidad WHERE l.estatus = 1 AND titulo LIKE '%"+filtro+"%'";
        ConexionMySQL connMySQL = new ConexionMySQL();
        Connection conn = connMySQL.open();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        List<Libro> libros = new ArrayList<>();

        while (rs.next()) {
            libros.add(fill(rs));
        }

        rs.close();
        pstmt.close();
        connMySQL.close();

        return libros;
    }
    
    public List<Libro> buscarA2(String filtro) throws Exception {
        String sql = "SELECT libro from Libro WHERE id_libro"+filtro+"";
        ConexionMySQL connMySQL = new ConexionMySQL();
        Connection conn = connMySQL.open();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Libro> libros = new ArrayList<>();

        while (rs.next()) {
            libros.add(fill(rs));
        }

        rs.close();
        pstmt.close();
        connMySQL.close();

        return libros;
    }
    
    public List<Libro> buscarB(String filtro) throws Exception {
        String sql = "SELECT l.*, u.id_universidad, u.nombre_universidad, u.pais FROM libro l INNER JOIN universidad u ON l.id_universidad = u.id_universidad WHERE l.estatus = 1 AND titulo LIKE '%"+filtro+"%' AND derecho_autor=0";
        ConexionMySQL connMySQL = new ConexionMySQL();
        Connection conn = connMySQL.open();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        List<Libro> libros = new ArrayList<>();

        while (rs.next()) {
            libros.add(fill(rs));
        }

        rs.close();
        pstmt.close();
        connMySQL.close();

        return libros;
    }

    
    private Libro fill(ResultSet rs) throws Exception {
        Libro l = new Libro();
        
        l.setId_libro(rs.getInt("id_libro"));
        l.setTitulo(rs.getString("titulo"));
        l.setAutor(rs.getString("autor"));
        l.setEditorial(rs.getString("editorial"));
        l.setIdioma(rs.getString("idioma"));
        l.setGenero(rs.getString("genero"));
        l.setNo_paginas(rs.getInt("no_paginas"));
        l.setLibro(rs.getString("libro"));
        l.setEstatus(rs.getBoolean("estatus"));
        l.setDerecho_autor(rs.getBoolean("derecho_autor"));
        
        
        l.setUniversidad(new Universidad());
        l.getUniversidad().setId_universidad(rs.getInt("id_universidad"));
        l.getUniversidad().setNombre_universidad(rs.getString("nombre_universidad"));
        
       

        
        return l;
    }
    
}
 