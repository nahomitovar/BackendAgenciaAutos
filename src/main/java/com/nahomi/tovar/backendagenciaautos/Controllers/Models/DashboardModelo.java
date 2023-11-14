package com.nahomi.tovar.backendagenciaautos.Controllers.Models;

import java.sql.Date;
import java.time.LocalDateTime;

public class DashboardModelo {

    private long total;
    private Date ingreso;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Date getIngreso() {
        return ingreso;
    }

    public void setIngreso(Date ingreso) {
        this.ingreso = ingreso;
    }
}
