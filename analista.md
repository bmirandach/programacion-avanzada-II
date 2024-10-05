La solución empresarial elegida fue Aprovisionamiento y tareas logísticas
abastecimiento: solicitud de compra


a) creacion  de carritos de compra


b) aprobacion de un pedido o carrito de compra

## casos de uso

~ agregar un producto al carrito (2ptos de esfuerzo)

El usuario selecciona un producto y lo agrega al carrito, esto agrega un elemento con los detalles del producto a la lista que maneja el contenido del carrito y se envia un mensaje de producto agregado
- definir el formato de la lista y el contenido de un Producto:  1pto
- implementar y manejar una lista de carritos con productos: 1pto

~ editar un producto del carrito (1pto de esfuerzo)

El usuario cambia la cantidad de un producto ya existente en el carrito, se envia un mensaje al cliente
- manejar la busqueda del carrito y producto dentro del mismo: 1pto

~ consultar un carrito (1pto de esfuerzo)

Se puede ver el contenido del carrito
- definir la manera de acceder a un carrito especifico de la lista de carritos y a los productos de este: 1pto

<!-- ## caso de uso: aprobacion de un pedido (1pto de esfuerzo)-->

~ preparacion de un pedido (1pto de esfuerzo)

Cuando se va a procesar el pedido se envia al cliente un mensaje de pedido listo y uno al vendedor para informarle sobre el pedido realizado
- definir el formato de los mensajes e implementar el standard de AMQP: 1pto
