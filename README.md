En este trabajo se simulan las operaciones de agregar un producto al carrito de compras, editar la cantidad del producto, consultar el carrito y realizar el pedido. Se trabaja en español salvo por los términos tomados de la especificación de AMQP.

Usando como base el patrón MVC y el framework de Spring se crea un CarritoController que va a realizar las cuatro operaciones. Las clases CarritoDTO y ProductoDTO definen la estructura de estos dos objetos. Exchange se encarga del manejo de los mensajes, en este caso estos mensajes pueden ir a una de dos colas (hay 2 consumidores de mensajes: los clientes y el vendedor). Los mensajes tienen algunas de las propiedades detalladas en AMQP pero cuentan con datos diferenciales por lo que se usan las clases concretas ClienteMensaje y VendedorMensaje.

Las peticiones se probaron con Bruno y para poder probar la consulta de un carrito se agrega una lista dentro de CarritoController.

Ejemplos de invocación

- agregarProducto
POST http://localhost:8080/apicarrito/agregarProducto/123

ejemplo de producto1
{
  "idProducto": "LNTJ0022",
  "nombre": "lentejas",
  "cantidad": 3
}

ejemplo de producto2
{
  "idProducto": "AZCR0343",
  "nombre": "azucar",
  "cantidad": 1
}

- actualizarProducto
PUT http://localhost:8080/apicarrito/actualizarProducto/123

{
  "idProducto": "AZCR0343",
  "nombre": "azucar",
  "cantidad": 5
}

- verCarrito
GET http://localhost:8080/apicarrito/123


- crearPedido
POST http://localhost:8080/apicarrito/crearPedido/123


