package com.utl.bli.CQRS;

import com.utl.bli.controller.usuarioDao.LibroDao;
import com.utl.bli.model.Libro;

/*@author marias*/
public class LibroCQRS {
    public Libro registrarLibro(Libro l) throws Exception {

        Libro li = new Libro();
        
        LibroDao libDao = new LibroDao();   
        
        l.setEstatus(true);     
          
        li = libDao.insert(l);
        
        return li;
    }
}


