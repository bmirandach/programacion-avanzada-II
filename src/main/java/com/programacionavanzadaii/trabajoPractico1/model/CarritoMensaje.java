package com.programacionavanzadaii.trabajoPractico1.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class CarritoMensaje extends Mensaje {

  private CarritoDTO carrito;

  public CarritoMensaje(CarritoDTO carrito) {
    super(UUID.randomUUID().toString(), "Carrito Pedido", LocalDateTime.now());
    this.carrito = carrito;
  }

  // implemento el metodo abstracto
  @Override
  public void procesarMensaje() {
    System.out.println("Procesando al carrito con ID: " + carrito.getIdCarrito());
  }

  public CarritoDTO getCarrito() {
    return carrito;
  }

  public void setCarrito(CarritoDTO carrito) {
    this.carrito = carrito;
  }

}
