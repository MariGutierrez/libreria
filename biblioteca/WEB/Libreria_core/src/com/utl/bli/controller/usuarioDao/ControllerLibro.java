
package com.utl.bli.controller.usuarioDao;

import com.utl.bli.appService.LIbrosAppService;
import com.utl.bli.viewModel.LibroPublicViewModel;
import java.util.List;

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

}
