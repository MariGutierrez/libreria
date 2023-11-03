package com.utl.bli.appService;

import com.utl.bli.CQRS.LibroCQRS;
import com.utl.bli.model.Libro;
import com.utl.bli.model.Universidad;

/* @author maria */
public class LIbrosAppService {
    
    public Libro registroLibro(int id_universidad, String titulo, String autor, String editorial, 
             String idioma, String genero, int no_paginas, String libro, boolean derecho_autor) throws Exception{
        
        Libro nuevoLibro = new Libro();
        Universidad universidad = new Universidad(); 
        universidad.setId_universidad(id_universidad);
        nuevoLibro.setUniversidad(universidad); 
        nuevoLibro.setTitulo(titulo);
        nuevoLibro.setAutor(autor);
        nuevoLibro.setEditorial(editorial);
        nuevoLibro.setIdioma(idioma);
        nuevoLibro.setGenero(genero);
        nuevoLibro.setNo_paginas(no_paginas);
        nuevoLibro.setLibro(libro);
        nuevoLibro.setDerecho_autor(derecho_autor);

        LibroCQRS cqrs = new LibroCQRS();
        Libro lib = cqrs.registrarLibro(nuevoLibro);
        
        return lib;
    }
}
