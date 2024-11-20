package com.programacionavanzadaii.trabajoPractico1.controller;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programacionavanzadaii.trabajoPractico1.mappers.CarritoMapper;
import com.programacionavanzadaii.trabajoPractico1.model.Carrito;

@RestController
@RequestMapping("/carrito-db")
public class CarritoDBController {

  private CarritoMapper carritoMapper;

  @Autowired
  public CarritoDBController(CarritoMapper carritoMapper) {
    this.carritoMapper = carritoMapper;
  }
  // public CarritoDBController(SqlSessionFactory sqlSessionFactory) {
  //   this.sqlSessionFactory = sqlSessionFactory;
  // }

  @PostMapping("/crearCarrito")
  public ResponseEntity<String> crearCarrito(@RequestBody Carrito carrito) {
    //try (SqlSession session = sqlSessionFactory.openSession()) {
      //CarritoMapper carritoMapper = session.getMapper(CarritoMapper.class);

      carritoMapper.crearCarrito(carrito);

      return ResponseEntity.ok("Carrito creado con ID " + carrito.getIdCarrito());
    // } catch (Exception e) {
    //   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR!! no se pudo crear el carrito " + e.getMessage());
    // }
  }


  @GetMapping("/carrito/{idCarrito}")
  public Carrito getCarritoById(@PathVariable int idCarrito) {
    //try (SqlSession session = sqlSessionFactory.openSession()) {

      return carritoMapper.consultarCarrito(idCarrito);
    //}
  }
  
}
