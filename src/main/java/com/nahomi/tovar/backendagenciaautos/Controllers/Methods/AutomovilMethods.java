package com.nahomi.tovar.backendagenciaautos.Controllers.Methods;

import com.nahomi.tovar.backendagenciaautos.BaseDatos.Conexion;
import com.nahomi.tovar.backendagenciaautos.Controllers.Models.AutomovilModelo;
import com.nahomi.tovar.backendagenciaautos.Controllers.Models.UsuarioModelo;
import com.nahomi.tovar.backendagenciaautos.Servicios.SQLService;
import org.springframework.http.ResponseEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class AutomovilMethods {

    Connection c = Conexion.getConnection();
    SQLService<AutomovilModelo> sql = new SQLService<>(AutomovilModelo.class);

    public ResponseEntity<List<AutomovilModelo>> ObtenerAutos(){
        List<AutomovilModelo> list = new ArrayList<>();
        try {
            return ResponseEntity.ok(sql.DynamicGetListMethod("Select id, concat(marca, '-',modelo) as marca from automoviles", c));
        }catch(Exception ex){
            return ResponseEntity.ok(list);
        }
    }

    public ResponseEntity<List<AutomovilModelo>> ObtenerTodosAutos(){
        List<AutomovilModelo> list = new ArrayList<>();
        try {
            return ResponseEntity.ok(sql.DynamicGetListMethod("Select id, marca, modelo from automoviles", c));
        }catch(Exception ex){
            return ResponseEntity.ok(list);
        }
    }

    public ResponseEntity<Boolean> CrearAutomovil(AutomovilModelo data) {
        try{
            String query = "Insert into automoviles(marca, modelo) values(?,?)";
            PreparedStatement statement = c.prepareStatement(query);
            statement.setString(1, data.getMarca());
            statement.setString(2, data.getModelo());
            statement.executeUpdate();
            return ResponseEntity.ok(true);
        }catch (Exception ex){
            return ResponseEntity.ok(false);
        }
    }
}
