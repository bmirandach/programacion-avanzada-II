En este trabajo se simulan las operaciones de agregar un producto al carrito de compras, consultarlo y realizar el pedido.

Usando como base el patrón MVC y el framework de Spring se crea un CarritoController que va a realizar las tres operaciones. Las clases CarritoDTO y ProductoDTO definen la estructura del estos dos objetos y las clases Mensaje y CarritoMensaje se usan para manejar los estados de los mensajes y las propiedades detalladas en AMQP.

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


- verCarrito
GET http://localhost:8080/apicarrito/123


- crearPedido
POST http://localhost:8080/apicarrito/crearPedido

{
  "idCarrito": "123",
  "productos": [
    {
      "idProducto": "LNTJ0022",
      "nombre": "lentejas",
      "cantidad": 3
    },
    {
      "idProducto": "AZCR0343",
      "nombre": "azucar",
      "cantidad": 1
    }
  ]
}

