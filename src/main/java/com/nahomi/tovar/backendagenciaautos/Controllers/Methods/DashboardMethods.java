package com.nahomi.tovar.backendagenciaautos.Controllers.Methods;

import com.nahomi.tovar.backendagenciaautos.BaseDatos.Conexion;
import com.nahomi.tovar.backendagenciaautos.Controllers.Models.DashboardModelo;
import com.nahomi.tovar.backendagenciaautos.Controllers.Models.RefaccionesModelo;
import com.nahomi.tovar.backendagenciaautos.Servicios.SQLService;
import org.springframework.http.ResponseEntity;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class DashboardMethods {

    Connection c = Conexion.getConnection();
    SQLService<DashboardModelo> sql = new SQLService<>(DashboardModelo.class);

    public ResponseEntity<List<DashboardModelo>> ObtenerDatos(){
        List<DashboardModelo> list = new ArrayList<>();
        try{
            String query = "select count(*) as total, cast(ingreso as date) as ingreso from servicios group by cast(ingreso as date)";
            return ResponseEntity.ok(sql.DynamicGetListMethod(query, c));
        }catch(Exception ex){
            return ResponseEntity.ok(list);
        }
    }

}
