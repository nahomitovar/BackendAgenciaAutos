package com.nahomi.tovar.backendagenciaautos.Controllers.Methods;

import com.nahomi.tovar.backendagenciaautos.Controllers.Models.UsuarioModelo;
import com.nahomi.tovar.backendagenciaautos.Servicios.SQLService;
import com.nahomi.tovar.backendagenciaautos.BaseDatos.Conexion;
import org.springframework.http.ResponseEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class UsuarioMethods {

    Connection c = Conexion.getConnection();
    SQLService<UsuarioModelo> sql = new SQLService<>(UsuarioModelo.class);

    public ResponseEntity<List<UsuarioModelo>> GetUsers(){
        try{
            return ResponseEntity.ok(sql.DynamicGetListMethod("Select id, nombre, telefono, RolID as rol from usuarios where RolID = 1", c));
        }catch(Exception ex){
            return null;
        }
    }

    public ResponseEntity<UsuarioModelo> IniciarSesion(UsuarioModelo data){
        UsuarioModelo usuarioModelo = new UsuarioModelo();
        try {
            String query = "Select id, nombre, telefono, RolID as rol from usuarios where telefono = '" + data.getTelefono() + "' and pass = '" + data.getPass() + "'";
            List<UsuarioModelo> resultado = sql.DynamicGetListMethod(query, c);
            return !resultado.isEmpty() ? ResponseEntity.ok(resultado.get(0)) : ResponseEntity.ok(usuarioModelo);
        }catch(Exception ex){
            return ResponseEntity.ok(usuarioModelo);
        }
    }

    public ResponseEntity<Boolean> RegistrarUsuarioNuevo(UsuarioModelo data){
        try{
            String query = "Insert into usuarios (nombre, telefono, pass, rolID) values(?,?,?,?)";
            PreparedStatement statement = c.prepareStatement(query);
            statement.setString(1, data.getNombre());
            statement.setString(2, data.getTelefono());
            statement.setString(3, data.getPass());
            statement.setInt(4, data.getRol());
            statement.executeUpdate();
            return  ResponseEntity.ok(true);
        }catch (Exception ex){
            return ResponseEntity.ok(false);
        }
    }

}
