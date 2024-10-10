package com.programacionavanzadaii.trabajoPractico1.model;
//no es DTO

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;

public class Exchange {

  private Map<String, LinkedList<MensajeDTO>> colasMensajes; 

  public Exchange() {
    this.colasMensajes = new HashMap<>();
    this.colasMensajes.put("mensajesClientes", new LinkedList<>());
    this.colasMensajes.put("mensajesVendedores", new LinkedList<>());
  }

  public void encolarMensaje(String nombreCola, MensajeDTO mensaje) {
    LinkedList<MensajeDTO> cola = colasMensajes.get(nombreCola);
    if (cola != null) {
      System.out.println("Encolando el mensaje de ID " + mensaje.getMessageId() + " en la cola " + nombreCola);
      cola.add(mensaje);
    } else {
      System.out.println("ERROR! La cola " + nombreCola + " no existe");
    }
  }

  // el getter pero de una cola especifica para que lo use el consumidor
  public LinkedList<MensajeDTO> obtenerCola(String nombreCola) {
    return colasMensajes.get(nombreCola);
  }

}
