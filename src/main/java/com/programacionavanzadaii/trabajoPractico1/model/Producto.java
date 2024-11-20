package com.programacionavanzadaii.trabajoPractico1.model;

public class Producto {
  
  private int idProducto; // tambien de la base
  private String nombre;
  private double precio;

  // defino los getters y setters
  public int getIdProducto() {
    return idProducto;
  }

  public void setIdProducto(int idProducto) {
    this.idProducto = idProducto;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public double getPrecio() {
    return precio;
  }

  public void setPrecio(double precio) {
    this.precio = precio;
  }

}
