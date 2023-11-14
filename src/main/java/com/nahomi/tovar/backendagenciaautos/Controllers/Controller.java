package com.nahomi.tovar.backendagenciaautos.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nahomi.tovar.backendagenciaautos.Controllers.Models.*;
import com.nahomi.tovar.backendagenciaautos.Controllers.Methods.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class Controller {

    UsuarioMethods m = new UsuarioMethods();
    ServiciosMethods s = new ServiciosMethods();
    AutomovilMethods a = new AutomovilMethods();
    RefaccionesMethods r = new RefaccionesMethods();
    DashboardMethods d = new DashboardMethods();

    @PostMapping("/Usuarios/CrearUsuario")
    public ResponseEntity<Boolean> CrearUsuario(@RequestBody UsuarioModelo data){
        return m.RegistrarUsuarioNuevo(data);
    }

    @PostMapping("/Usuarios/IniciarSesion")
    public ResponseEntity<UsuarioModelo> IniciarSesion(@RequestBody UsuarioModelo data){
        return m.IniciarSesion(data);
    }
    @GetMapping("/Usuarios/ObtenerUsuarios")
    public ResponseEntity<List<UsuarioModelo>> ObtenerUsuarios(){
        return m.GetUsers();
    }

    //Servicios
    @PostMapping("/Servicios/ObtenerServiciosPorUsuario")
    public ResponseEntity<List<ServiciosModelo>> ObtenerServiciosPorUsuario(@RequestBody UsuarioModelo data){
        return s.ObtenerServiciosPorUsuario(data);
    }

    @GetMapping("/Servicios/ObtenerServicios")
    public ResponseEntity<List<ServiciosModelo>> ObtenerServicios(){
        return s.ObtenerServicios();
    }

    @PostMapping("/Servicios/CrearNuevoServicio")
    public ResponseEntity<Boolean> CrearNuevoServicio(@RequestBody ServiciosModelo data){
        return s.CrearNuevoServicio(data);
    }
    @PostMapping("/Servicios/ActualizarEstatus")
    public ResponseEntity<Boolean> ActualizarEstatus(@RequestBody ServiciosModelo data){
        return s.ActualizarEstatus(data);
    }
    //Automoviles
    @GetMapping("/Automoviles/ObtenerAutos")
    public ResponseEntity<List<AutomovilModelo>> ObtenerAutos(){
        return a.ObtenerAutos();
    }

    @GetMapping("/Automoviles/ObtenerTodosAutos")
    public ResponseEntity<List<AutomovilModelo>> ObtenerTodosAutos(){
        return a.ObtenerTodosAutos();
    }
    @PostMapping("/Automoviles/CrearAutomovil")
    public  ResponseEntity<Boolean> CrearAutomovil(@RequestBody AutomovilModelo data){
        return a.CrearAutomovil(data);
    }
    //Refacciones
    @GetMapping("/Refacciones/ObtenerRefacciones")
    public ResponseEntity<List<RefaccionesModelo>> ObtenerRefacciones(){
        return r.ObtenerRefacciones();
    }

    @PostMapping("/Refacciones/CrearRefaccion")
    public ResponseEntity<Boolean> CrearRefaccionNueva(@RequestBody RefaccionesModelo data){
        return r.CrearRefaccionNueva(data);
    }
    //RefaccionesServicio
    @PostMapping("/RefaccionesServicios/AgregarRefaccionServicio")
    public ResponseEntity<Boolean> AgregarRefaccionServicio(@RequestBody RefaccionesModelo data){
        return r.AgregarRefaccionServicio(data);
    }
    //Dashboard
    @GetMapping("/Dashboard/ObtenerServicios")
    public ResponseEntity<List<DashboardModelo>> ObtenerServiciosDashboard(){
        return d.ObtenerDatos();
    }

}
