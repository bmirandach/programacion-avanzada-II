package com.programacionavanzadaii.trabajoPractico1.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class ClienteMensaje extends MensajeDTO {

  private String idCarrito;

  public ClienteMensaje(String subject, String idCarrito) {
    super(UUID.randomUUID().toString(), subject, LocalDateTime.now());
    this.idCarrito = idCarrito;
  }

  public String getIdCarrito() {
    return idCarrito;
  }
}
