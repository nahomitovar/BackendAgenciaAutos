package com.nahomi.tovar.backendagenciaautos.Controllers.Methods;

import com.nahomi.tovar.backendagenciaautos.BaseDatos.Conexion;
import com.nahomi.tovar.backendagenciaautos.Controllers.Models.ServiciosModelo;
import com.nahomi.tovar.backendagenciaautos.Controllers.Models.UsuarioModelo;
import com.nahomi.tovar.backendagenciaautos.Servicios.SQLService;
import org.springframework.http.ResponseEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;
import com.nahomi.tovar.backendagenciaautos.Controllers.Models.ServiciosModelo;

public class ServiciosMethods {

    Connection c = Conexion.getConnection();
    SQLService<ServiciosModelo> sql = new SQLService<>(ServiciosModelo.class);

    /**
     * Agrega una cantidad específica de días y meses a una fecha.
     *
     * @param fecha   La fecha a la que se le agregarán días para el proximo servicio.
     * @param dias    El número de días a agregar.
     * @return        La nueva fecha con los días y meses agregados.
     */
    public static Date AddDays(Date fecha, int dias) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        calendario.add(Calendar.DAY_OF_MONTH, dias);
        return new Date(calendario.getTimeInMillis());
    }

    public ResponseEntity<Boolean> CrearNuevoServicio(ServiciosModelo data){
        try {
            String query = "insert into servicios (idUsuario, idAutomovil, folio, estatus, ingreso, salida, proximo) values(?,?,?,?,?,?,?)";
            Date fecha = new Date(Calendar.getInstance().getTimeInMillis());
            String folio = "S" + "-" + fecha.toString() + data.getIdUsuario() + "-" + "-" + data.getIdEmpleado();
            PreparedStatement statement = c.prepareStatement(query);
            statement.setInt(1, data.getIdUsuario());
            statement.setInt(2, data.getIdAutomovil());
            statement.setString(3, folio);
            statement.setString(4, "Ingresó al taller");
            statement.setDate(5, fecha);
            statement.setDate(6, null);
            statement.setDate(7, AddDays(fecha, 90));
            statement.executeUpdate();
            return ResponseEntity.ok(true);
        }catch(Exception ex){
            return ResponseEntity.ok(false);
        }
    }

    public ResponseEntity<List<ServiciosModelo>> ObtenerServiciosPorUsuario(UsuarioModelo data){
        List<ServiciosModelo> list = new ArrayList<>();
        try {
            String query = "select s.folio as folio, s.estatus as estatus, s.ingreso as ingreso, s.salida as salida, s.proximo as proximo, u.nombre as nombre from servicios as s inner join usuarios as u on s.idUsuario = u.id" +
                    " where u.id = " + data.getId() + " and u.telefono = '" + data.getTelefono() + "' and u.pass = '" + data.getPass() + "'";
            return ResponseEntity.ok(sql.DynamicGetListMethod(query, c));
        }catch(Exception ex){
            return ResponseEntity.ok(list);
        }
    }

    public ResponseEntity<List<ServiciosModelo>> ObtenerServicios() {
        List<ServiciosModelo> list = new ArrayList<>();
        try {
            String query = "Select id as idServicio, folio, estatus, ingreso, salida, proximo from servicios where estatus != 'Terminado'";
            return ResponseEntity.ok(sql.DynamicGetListMethod(query, c));
        }catch(Exception ex){
            return ResponseEntity.ok(list);
        }
    }

    public ResponseEntity<Boolean> ActualizarEstatus(ServiciosModelo data) {
        try{
            String query = "Update servicios set Estatus = '" + data.getEstatus() + "' where id = " + data.getIdServicio();
            PreparedStatement statement = c.prepareStatement(query);
            statement.executeUpdate();
            return ResponseEntity.ok(true);
        }catch(Exception ex){
            return ResponseEntity.ok(false);
        }
    }
}
