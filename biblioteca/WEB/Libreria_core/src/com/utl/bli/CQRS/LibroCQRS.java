package com.utl.bli.CQRS;

import com.utl.bli.controller.usuarioDao.LibroDao;
import com.utl.bli.model.Libro;
import java.sql.SQLException;
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
    
    public Libro modificarLibro(Libro l) throws Exception {
        Libro li = new Libro();        
        LibroDao libDao = new LibroDao();   
        
        l.setEstatus(true);     
          
        li = libDao.update(l);
        
        return li;
    }
    
    public List<Libro> buscarLibro2(String filtro) throws Exception {
        LibroDao libDao = new LibroDao();
        
        return libDao.buscar(filtro);
    }
    
    
    public List<Libro> getAll(String filtro) throws Exception {
        LibroDao libDao = new LibroDao();
        
        return libDao.getAll(filtro);
    }
    
    public Libro actualizar(Libro l) throws Exception{
    
        Libro li = new Libro();        
        LibroDao libDao = new LibroDao();
        
        if (l.getTitulo() != null) {
            
            li = (Libro) libDao.buscar(l.getTitulo());
            
            if(!li.getLibro().equals(l.getLibro())){                
                li = libDao.update(l);
            }            
            
        } else {
          throw new Exception("El libro es incorrecto");
          }
        return li;
    }
  
}


