package com.programacionavanzadaii.trabajoPractico1.model;

import java.util.LinkedList;

public class Client {

  private Exchange exchange;

  public Client(Exchange exchange) {
    this.exchange = exchange;
  }

  // se simula el consumir(desencolar y procesar) el mensaje
  public void consumirMensajes(String nombreCola) {
    LinkedList<MensajeDTO> cola = exchange.obtenerCola(nombreCola);
    // solo si exise la cola y no esta vacia saca el mensaje (el primer elementode la cola)
    if (cola != null && !cola.isEmpty()) {
      MensajeDTO mensaje = cola.poll(); // uso poll porque removeFirst devuelve NoSuchElementException si esta vacia
      if (mensaje != null) {
        System.out.println("Desencolando el mensaje de ID " + mensaje.getMessageId() + " de la cola " + nombreCola);
      } else {
        System.out.println("No hay mensajes en la cola " + nombreCola);
      }
      procesarMensaje(mensaje);
    } else {
      System.out.println("No hay mensajes en la cola " + nombreCola);
    }
  }

  // se "procesa" un mensaje
  private void procesarMensaje(MensajeDTO mensaje) {
    // aca iria el procesamiento si hubiera algo para hacer
    System.out.println("Procesando el mensaje '" + mensaje.getSubject() + "'");
  }

}
