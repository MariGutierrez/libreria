package com.utl.bli.CQRS;

import com.utl.bli.controller.usuarioDao.LibroDao;
import com.utl.bli.model.Libro;
import java.util.ArrayList;
import java.util.List;

/*@author marias*/
public class LibroCQRS {
    public Libro registrarLibro(Libro l) throws Exception {

        Libro li = new Libro();        
        LibroDao libDao = new LibroDao();   
        
        l.setEstatus(true);     
          
        li = libDao.insert(l);
        
        return li;
    }
    
    
    /*public Libro buscarLibro(String filtro) throws Exception{
        LibroDao libDao = new LibroDao();  
        Libro li = libDao.buscar(filtro);
        return li;    
    }*/
    
    public List<Libro> buscarLibro2(String filtro) throws Exception {
        LibroDao libDao = new LibroDao();
        
        return libDao.buscar(filtro);
    }
    
    
    public List<Libro> getAll(String filtro) throws Exception {
        LibroDao libDao = new LibroDao();
        
        return libDao.getAll(filtro);
    }
    
}


