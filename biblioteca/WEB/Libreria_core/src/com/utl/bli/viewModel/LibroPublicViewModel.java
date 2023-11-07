
package com.utl.bli.viewModel;

public class LibroPublicViewModel {
    private String id;
    private String titulo_libro;
    private String autor_libro;
    private String tema_libro;
    private String libroPDF;

    public LibroPublicViewModel(String id, String titulo_libro, String autor_libro, String tema_libro, String libroPDF) {
        this.id = id;
        this.titulo_libro = titulo_libro;
        this.autor_libro = autor_libro;
        this.tema_libro = tema_libro;
        this.libroPDF = libroPDF;
    }
    
    
}
