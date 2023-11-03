package com.utl.bli.controller.usuarioDao;

import com.utl.bli.bd.ConexionMySQL;
import com.utl.bli.model.Libro;
import com.utl.bli.model.Universidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*@author maria */
public class LibroDao {
    public Libro insert(Libro l) throws Exception {
        String sql = "INSERT INTO libro (id_universidad, titulo, autor, editorial, idioma, genero, no_paginas, libro, derecho_autor) VALUES (?,?,?,?,?,?,?,?,?);" ;

        ConexionMySQL connMySQL = new ConexionMySQL();
        Connection conn = connMySQL.open();
        
        PreparedStatement ps = conn.prepareStatement(sql);  

        ps.setInt(1, l.getUniversidad().getId_universidad());
        ps.setString(2,l.getTitulo());        
        ps.setString(3, l.getAutor());
        ps.setString(4, l.getEditorial());
        ps.setString(5, l.getIdioma());
        ps.setString(6, l.getGenero());
        ps.setInt(7, l.getNo_paginas());
        ps.setString(8, l.getLibro());
        ps.setBoolean(9, l.isDerecho_autor());
        ps.executeUpdate();
        // Cerrar la conexion 
        ps.close();
        connMySQL.close();
        
        return l;
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
