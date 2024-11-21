package com.programacionavanzadaii.trabajoPractico1.controller;

// import java.io.IOException;
// import java.io.Reader;
// import java.util.Date;

// import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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

  // private CarritoMapper carritoMapper;
  // private SqlSessionFactory sqlSessionFactory;
  // private SqlSession session;

  //@Autowired
  private CarritoMapper carritoMapper;

  // @Autowired
  private SqlSession sqlSession;
  // public CarritoDBController() {
  //   try {
  //     // this.carritoMapper = carritoMapper;
  //     Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
  //     this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
  //     this.session = sqlSessionFactory.openSession();
  //   } catch (IOException e) {
  //     System.err.println("Error: " + e.getMessage());
  //   }
  //@Autowired
  // public CarritoDBController(CarritoMapper carritoMapper) {
  //   this.carritoMapper = carritoMapper;
  // }
  // }
  // public CarritoDBController(SqlSessionFactory sqlSessionFactory) {
  //   this.sqlSessionFactory = sqlSessionFactory;
  // }
  
  

  @PostMapping("/crearCarrito")
  public ResponseEntity<String> crearCarrito(@RequestBody Carrito carrito) {
    try (SqlSession session = sqlSession) {
      //carritoMapper = session.getMapper(CarritoMapper.class);
      carritoMapper.crearCarrito(carrito);
      session.commit();
      return ResponseEntity.ok("Carrito creado con ID " + carrito.getIdCarrito());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR!! no se pudo crear el carrito. " + e.getMessage());
    }
  }


  @GetMapping("/{idCarrito}")
  public ResponseEntity<Carrito> consultarCarrito(@PathVariable int idCarrito) {
    //try (SqlSession session = sqlSessionFactory.openSession()) {
    try (SqlSession session = sqlSession) {
      CarritoMapper carritoMapper = session.getMapper(CarritoMapper.class); //probar
      Carrito carrito = carritoMapper.consultarCarrito(idCarrito);
      if (carrito == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      } 
      return ResponseEntity.ok(carrito);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
  
}