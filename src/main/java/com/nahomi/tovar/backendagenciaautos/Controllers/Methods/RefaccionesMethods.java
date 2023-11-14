package com.nahomi.tovar.backendagenciaautos.Controllers.Methods;

import com.nahomi.tovar.backendagenciaautos.BaseDatos.Conexion;
import com.nahomi.tovar.backendagenciaautos.Controllers.Models.RefaccionesModelo;
import com.nahomi.tovar.backendagenciaautos.Controllers.Models.ServiciosModelo;
import com.nahomi.tovar.backendagenciaautos.Servicios.SQLService;
import org.springframework.http.ResponseEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class RefaccionesMethods {

    Connection c = Conexion.getConnection();
    SQLService<RefaccionesModelo> sql = new SQLService<>(RefaccionesModelo.class);

    public ResponseEntity<List<RefaccionesModelo>> ObtenerRefacciones(){
        List<RefaccionesModelo> list = new ArrayList<>();
        try{
            String query = "Select * from refacciones";
            list = sql.DynamicGetListMethod(query, c);
            return ResponseEntity.ok(list);
        }catch(Exception ex){
            return ResponseEntity.ok(list);
        }
    }

    public ResponseEntity<Boolean> CrearRefaccionNueva(RefaccionesModelo data) {
        try{
            String query = "Insert into refacciones(nombre, descripcion) values(?,?)";
            PreparedStatement statement = c.prepareStatement(query);
            statement.setString(1, data.getNombre());
            statement.setString(2, data.getDescripcion());
            statement.executeUpdate();
            return ResponseEntity.ok(true);
        }catch(Exception ex){
            return ResponseEntity.ok(false);
        }
    }

    public ResponseEntity<Boolean> AgregarRefaccionServicio(RefaccionesModelo data) {
        try{
            String query = "Insert into serviciorefacciones(idServicio, idRefaccion) values(?,?)";
            PreparedStatement statement = c.prepareStatement(query);
            statement.setInt(1, data.getIdServicio());
            statement.setInt(2, data.getId());
            statement.executeUpdate();
            return ResponseEntity.ok(true);
        }catch(Exception ex){
            return ResponseEntity.ok(false);
        }
    }
}
