package com.programacionavanzadaii.trabajoPractico1.controller;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programacionavanzadaii.trabajoPractico1.model.CarritoDTO;
import com.programacionavanzadaii.trabajoPractico1.model.CarritoMensaje;
import com.programacionavanzadaii.trabajoPractico1.model.ProductoDTO;

@RestController
@RequestMapping("/apicarrito")
public class CarritoController {

  // @GetMapping("/hello") //lo pongo como un endpoint
  // public String holaMundo() {
  //   return "Hola mundo";
  // }

  // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html

  //se agrega el producto en el carrito especificado
  @PostMapping("/agregarProducto/{idCarrito}")
  public ResponseEntity<String> agregarProducto(@PathVariable String idCarrito, @RequestBody ProductoDTO producto) {
    //TODO: pasr el idCarrito!! dentro del producto?  en el path??
      CarritoDTO carrito = new CarritoDTO();
      carrito.setIdCarrito(idCarrito);
      carrito.setProductos(new ArrayList<>());
      carrito.getProductos().add(producto);
      //mensaje
      System.out.println("Producto agregado: " + producto.getNombre() + " Cantidad: " + producto.getCantidad());
      return ResponseEntity.ok("Producto agregado al carrito " + idCarrito);
  }

  //se ve contenido del carrito especificado
  @GetMapping("/{idCarrito}")
  public ResponseEntity<CarritoDTO> verCarrito(@PathVariable String idCarrito) {
    // y aca buscaria el carrito si estuviera almacenado en algun lugar

    //TODO: comentar, es para ver si funciona
    CarritoDTO carrito = new CarritoDTO();
    carrito.setIdCarrito(idCarrito);
    carrito.setProductos(new ArrayList<>()); 

    // y se ve un carrito vacio
    return ResponseEntity.ok(carrito);
  }

  //se crea un pedido, aca se maneja lo de los estados
  @PostMapping("/crearPedido")
  public ResponseEntity<String> crearPedido(@RequestBody CarritoDTO carrito) {
    CarritoMensaje carritoMensaje = new CarritoMensaje(carrito);
    String idCarrito = carrito.getIdCarrito();
    //simulacion porque no hay nodos:
    // empieza con estado available
    System.out.println("Estado inicial del mensaje: " + carritoMensaje.getEstado());
    // aca se recibe al carrito porque ya paso por el pago y demas
    carritoMensaje.adquirir();
    System.out.println("Carrito adquirido. Estado: " + carritoMensaje.getEstado());
    //se crea el pedido
    carritoMensaje.procesarMensaje();
    //y se archiva el mensaje
    carritoMensaje.archivar();
    System.out.println("Carrito archivado. Estado: " + carritoMensaje.getEstado());

    return ResponseEntity.ok("Pedido creado para el carrito de ID " + idCarrito + " y con estado actualizado a " + carritoMensaje.getEstado());
  }

}
