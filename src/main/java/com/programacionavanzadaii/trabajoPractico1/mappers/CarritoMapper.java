package com.programacionavanzadaii.trabajoPractico1.mappers;

import java.util.List;
//import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.programacionavanzadaii.trabajoPractico1.model.Carrito;

@Mapper
public interface CarritoMapper {
  
  void crearCarrito(Carrito carrito);

  void eliminarCarrito(int idCarrito);

  Carrito consultarCarrito(int idCarrito);

  List<Carrito> consultarTodosLosCarritos(); // devuelve una lista donde cada registro es un Map -> YA NO

}
