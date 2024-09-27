package com.programacionavanzadaii.trabajoPractico1.model;

public class ProductoDTO {

  private String idProducto;
  private String nombre;
  private int cantidad;

  // defino los getters y setters
  public String getIdProducto() {
    return idProducto;
  }

  public void setIdProducto(String idProducto) {
    this.idProducto = idProducto;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getCantidad() {
    return cantidad;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }

}