package com.utl.bli.appService;

import com.utl.bli.CQRS.LibroCQRS;
import com.utl.bli.controller.usuarioDao.LibroDao;
import com.utl.bli.model.Libro;
import com.utl.bli.model.Universidad;
import com.utl.bli.viewModel.LibroPublicViewModel;
import java.util.ArrayList;
import java.util.List;

/* @author maria */
public class LIbrosAppService {

    public Libro registroLibro(int id_universidad, String titulo, String autor, String editorial,
            String idioma, String genero, int no_paginas, String libro, boolean derecho_autor) throws Exception {

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

    public Libro actualizarLibro(int id_universidad, String titulo, String autor, String editorial,
            String idioma, String genero, int no_paginas, String libro, boolean derecho_autor, int id_libro) throws Exception {

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
        nuevoLibro.setId_libro(id_libro);

        LibroCQRS cqrs = new LibroCQRS();
        Libro lib = cqrs.modificarLibro(nuevoLibro);

        return lib;
    }
    
     public List<Libro> buscarLibro2(String filtro) throws Exception {
        LibroDao libDao = new LibroDao();
        
        return libDao.buscar(filtro);
    }
    
    
    public List<Libro> getAll(String filtro) throws Exception {
        LibroDao libDao = new LibroDao();
        
        return libDao.getAll(filtro);
    }

    public List<LibroPublicViewModel> buscarPorPublic(String nombre_libro) throws Exception {
        LibroDao libroDao = new LibroDao();
        List<Libro> l = libroDao.buscar(nombre_libro);
        List<LibroPublicViewModel> usr = new ArrayList<>();

        for (Libro libro : l) {
            LibroPublicViewModel libroPublicViewModel = new LibroPublicViewModel(libro.getId_libro()+"", libro.getTitulo(), libro.getAutor(), libro.getGenero(), libro.getLibro());
            usr.add(libroPublicViewModel);
        }
        return usr;
    }

}
