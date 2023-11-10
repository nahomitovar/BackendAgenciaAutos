package com.nahomi.tovar.backendagenciaautos.Controllers;

import org.springframework.web.bind.annotation.*;
import com.nahomi.tovar.backendagenciaautos.Controllers.Models.UsuarioModelo;
import com.nahomi.tovar.backendagenciaautos.Controllers.Methods.UsuarioMethods;

@CrossOrigin(origins = "*")
@RestController
public class UsuarioController {

    UsuarioMethods m = new UsuarioMethods();

    @PostMapping("/CrearUsuario")
    public boolean CrearUsuario(@RequestBody UsuarioModelo data){
        return m.CrearUsuario(data);
    }

}
