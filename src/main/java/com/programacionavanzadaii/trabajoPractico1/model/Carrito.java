package com.programacionavanzadaii.trabajoPractico1.model;

import java.util.Date;

public class Carrito {

  private int idCarrito; // de la base -> usar useGeneratedKeys y keyProperty!!!
  private Date fechaCreacion;

  // public Carrito(Date fechaCreacion) {
  //   this.fechaCreacion = fechaCreacion;
  // }

  public Carrito() {
  }
  
  // defino los getters y setters
  public int getIdCarrito() {
    return idCarrito;
  }

  public void setIdCarrito(int idCarrito) {
    this.idCarrito = idCarrito;
  }

  public Date getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(Date fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }
  
}
