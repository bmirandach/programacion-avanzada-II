package com.programacionavanzadaii.trabajoPractico1.model;
//no es DTO

import java.time.LocalDateTime;

//clase para manejarr los estados y las propiedades de la especificacion AMQP
public abstract class Mensaje {

  protected String messageId;
  //protected String userId;
  protected String subject;
  protected LocalDateTime  creationTime;
  //protected String absoluteExpiryTime;
  protected String estado; // los de AMQP: available, acquiredy archived

  public Mensaje(String messageId, String subject, LocalDateTime creationTime) {
    this.messageId = messageId;
    this.subject = subject;
    this.creationTime = creationTime;
    this.estado = "available"; // estado inicial
  }

  // defino los getter (y setter para el estado)
  public String getMessageId() {
    return messageId;
  }

  public String getSubject() {
    return subject;
  }

  public LocalDateTime getCreationTime() {
    return creationTime;
  }

  public String getEstado() {
    return estado;
  }

  // para cambiar el estado, empiezan con available asi que solo pasan a estos 2
  public void adquirir() {
    this.estado = "acquired";
  }

  public void archivar() {
    this.estado = "archived";
  }

  // el metodo abstracto que van a implementar las subclases
  public abstract void procesarMensaje();

}
