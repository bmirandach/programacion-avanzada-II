package com.programacionavanzadaii.trabajoPractico1.model;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;

public class Exchange {

  private Map<String, LinkedList<Mensaje>> colasMensajes; 

  public Exchange() {
    this.colasMensajes = new HashMap<>();
    this.colasMensajes.put("mensajesClientes", new LinkedList<>());
    this.colasMensajes.put("mensajesVendedores", new LinkedList<>());
  }

  public void encolarMensaje(String nombreCola, Mensaje mensaje) {
    LinkedList<Mensaje> cola = colasMensajes.get(nombreCola);
    if (cola != null) {
      System.out.println("Encolando el mensaje de ID " + mensaje.getMessageId() + " en la cola " + nombreCola);
      cola.add(mensaje);
    } else {
      System.out.println("ERROR! La cola " + nombreCola + " no existe");
    }
  }

  public Mensaje desencolarMensaje(String nombreCola) {
    LinkedList<Mensaje> cola = colasMensajes.get(nombreCola);
    if (cola != null) {
      Mensaje mensaje = cola.poll(); // uso poll porque removeFirst devuelve NoSuchElementException si esta vacia
      if (mensaje != null) {
        System.out.println("Desencolando el mensaje de ID " + mensaje.getMessageId() + " de la cola " + nombreCola);
      } else {
        System.out.println("No hay mensajes en la cola " + nombreCola);
      }
      return mensaje;
    } else {
      System.out.println("ERROR! La cola " + nombreCola + " no existe");
      return null;
    }
  }
}
