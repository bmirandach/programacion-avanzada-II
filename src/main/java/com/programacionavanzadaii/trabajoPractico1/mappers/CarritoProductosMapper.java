package com.programacionavanzadaii.trabajoPractico1.mappers;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.programacionavanzadaii.trabajoPractico1.model.Producto;

@Mapper
public interface CarritoProductosMapper {

  void agregarProductoEnCarrito(Producto producto);

  Producto consultarProductosPorCarrito(int idCarrito);

  void eliminarProductoDelCarrito(Map<String, Object> ids); // va a ser un HashMap con los ids del producto y carrito

}
