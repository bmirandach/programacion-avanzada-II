package com.programacionavanzadaii.trabajoPractico1.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class VendedorMensaje extends MensajeDTO {

  protected String groupId; // mensajes del tipo: pagado, listo para ser creado

  public VendedorMensaje(String subject, String groupId) {
    super(UUID.randomUUID().toString(), subject, LocalDateTime.now());
    this.groupId = groupId;
  }

}
