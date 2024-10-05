package com.programacionavanzadaii.trabajoPractico1.controller;

import java.util.ArrayList;
import java.util.HashMap; // para probar con Bruno
import java.util.Map; // las cosultas de verCarrito
import java.util.List; // para buscar el producto

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programacionavanzadaii.trabajoPractico1.model.CarritoDTO;
import com.programacionavanzadaii.trabajoPractico1.model.ClienteMensaje;
import com.programacionavanzadaii.trabajoPractico1.model.Exchange;
import com.programacionavanzadaii.trabajoPractico1.model.ProductoDTO;
import com.programacionavanzadaii.trabajoPractico1.model.VendedorMensaje;

@RestController
@RequestMapping("/apicarrito")
public class CarritoController {

  private Map<String, CarritoDTO> carritos = new HashMap<>(); // para probar la consulta de productos de un carrito en verCarrito
  private Exchange exchange = new Exchange(); // para manejar los mensajes

  //se agrega el producto en el carrito especificado
  @PostMapping("/agregarProducto/{idCarrito}")
  public ResponseEntity<String> agregarProducto(@PathVariable String idCarrito, @RequestBody ProductoDTO producto) {
      CarritoDTO carrito = carritos.getOrDefault(idCarrito, new CarritoDTO()); // getOrDefault para que si no existe lo cree
      carrito.setIdCarrito(idCarrito);
      //solo si no hay productos crea una nueva lista
      if (carrito.getProductos() == null) {
        carrito.setProductos(new ArrayList<>());
      }
      carrito.getProductos().add(producto);
      carritos.put(idCarrito, carrito); // guardo el carrito en la lista de carritos
      String mensaje = "Se agregó " + producto.getNombre() + " x " + producto.getCantidad() + " unidad(es)";
      //encola mensaje para clientes
      exchange.encolarMensaje("mensajesClientes", new ClienteMensaje(mensaje, idCarrito));
      return ResponseEntity.ok("Producto agregado al carrito " + idCarrito);
  }

  //se ve contenido del carrito especificado
  @GetMapping("/{idCarrito}")
  public ResponseEntity<CarritoDTO> verCarrito(@PathVariable String idCarrito) {
    // para probar
    CarritoDTO carrito = carritos.get(idCarrito);
    if (carrito == null) {
      return ResponseEntity.notFound().build(); //no devuelvo mensaje si no esta
    }
    return ResponseEntity.ok(carrito);
  }

  // se edita la cantidad del producto del carrito especificado
  @PutMapping("/actualizarProducto/{idCarrito}")
  public ResponseEntity<String> actualizarProducto(@PathVariable String idCarrito, @RequestBody ProductoDTO productoEditado) {
    // para probar
    CarritoDTO carrito = carritos.get(idCarrito);
    if (carrito == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrito no encontrado"); // devuelvo mensaje si no esta
    }
    List<ProductoDTO> productos = carrito.getProductos();
    for (ProductoDTO producto : productos) {
        if (producto.getIdProducto().equals(productoEditado.getIdProducto())) {
          producto.setCantidad(productoEditado.getCantidad());
          // encola mensaje para clientes
          exchange.encolarMensaje("mensajesClientes", new ClienteMensaje("Producto actualizado con éxito", idCarrito));

          return ResponseEntity.ok("Producto actualizado en el carrito " + idCarrito);
        }
    }
    // llega aca si no lo encontro
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado en el carrito " + idCarrito);
}

  //se crea un pedido que ya se pago, aca se manejan los mensajes para ambos consumidores (clientes y vendedores)
  @PostMapping("/crearPedido/{idCarrito}")
  public ResponseEntity<String> crearPedido(@PathVariable String idCarrito) {
    CarritoDTO carrito = carritos.get(idCarrito);
    if (carrito == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrito no encontrado"); // devuelvo mensaje si no esta
    }
    // encola mensaje para clientes
    exchange.encolarMensaje("mensajesClientes", new ClienteMensaje("Se realizó el pedido, en los próximos días lo estará recibiendo", idCarrito));
    // encola mensaje para vendedores
    exchange.encolarMensaje("mensajesVendedores", new VendedorMensaje("Pedido listo para ser armado del carrito " + idCarrito, "Procesado"));
    return ResponseEntity.ok("Pedido creado para el carrito de ID " + idCarrito);
  }

}