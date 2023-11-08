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
        
        if(l.getTitulo() != null){
            li = libDao.insert(l);
        }
        else {
             throw new Exception(" No se inserto el libro correctamente");
        }
        return li;
    }
    
    public Libro modificarLibro(Libro l) throws Exception {
        Libro li = new Libro();        
        LibroDao libDao = new LibroDao();   
        
        l.setEstatus(true); 
        
        if(l.getTitulo() != null){
            li = libDao.update(l);
        }
        else {
             throw new Exception(" No se logro actualizar el libro ");
        }
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
  
}


