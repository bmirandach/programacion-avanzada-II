package com.programacionavanzadaii.trabajoPractico1.model;

import java.util.List;

public class CarritoDTO {

  private String idCarrito;
  private List<ProductoDTO> productos;
  //deberia haber un idCliente?

  // defino los getters y setters
  public String getIdCarrito() {
      return idCarrito;
  }

  public void setIdCarrito(String idCarrito) {
      this.idCarrito = idCarrito;
  }

  public List<ProductoDTO> getProductos() {
      return productos;
  }

  public void setProductos(List<ProductoDTO> productos) {
      this.productos = productos;
  }
  
}