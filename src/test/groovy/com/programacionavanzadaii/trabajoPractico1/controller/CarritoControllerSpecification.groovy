import spock.lang.Specification
import com.programacionavanzadaii.trabajoPractico1.controller.CarritoController
import com.programacionavanzadaii.trabajoPractico1.model.ProductoDTO
import com.programacionavanzadaii.trabajoPractico1.model.Exchange
import com.programacionavanzadaii.trabajoPractico1.model.MensajeDTO
import com.programacionavanzadaii.trabajoPractico1.model.ClienteMensaje
import com.programacionavanzadaii.trabajoPractico1.model.CarritoDTO
import org.springframework.http.HttpStatus

class CarritoControllerSpecification extends Specification {

    // def setup() {
    //     //before
    //     def carritoController = new CarritoController()
    // }

    def "agregar un producto al carrito"() {
        given: "un carrito vacio y un producto"
        def carritoController = new CarritoController()
        def idCarrito = "123"
        def producto = new ProductoDTO(idProducto: "LNTJ0022", nombre: "lentejas", cantidad: 3)

        when: "se agrega el producto al carrito"
        carritoController.agregarProducto(idCarrito, producto)
        //tomo el carrito de id 123
        def carrito = carritoController.carritos.get(idCarrito)

        then: "el carrito tiene el producto que se agrego"
        carrito.productos.size() == 1
        carrito.productos[0].idProducto == "LNTJ0022"
        carrito.productos[0].nombre == "lentejas"
        carrito.productos[0].cantidad == 3
    }

    def "encolar mensaje de producto agregado en la cola mensajesClientes"() {
        given: "el Exchange y un mensaje para encolar"
        def exchange = new Exchange()
        def mensaje = new ClienteMensaje("Se agregó azucar x 1 unidad(es)", "123")

        when: "se encola el mensaje en mensajesClientes"
        exchange.encolarMensaje("mensajesClientes", mensaje)

        then: "el mensaje tendria que estar en mensajesClientes"
        def colaClientes = exchange.obtenerCola("mensajesClientes")
        colaClientes.size() == 1
        colaClientes[0].subject == "Se agregó azucar x 1 unidad(es)"
        colaClientes[0].idCarrito == "123"
    }

    //TODO: si da tiempo validar response? aca puede tirar un NOT_FOUND
    def "actualizar cantidad del producto de un carrito"() {
        given: "un carrito con un producto ya agregado"
        def carritoController = new CarritoController()
        def producto = new ProductoDTO(idProducto: "AZCR0343", nombre: "azucar", cantidad: 1)
        carritoController.agregarProducto("123", producto)

        and: "un producto con los mismos datos pero una nueva cantidad"
        def productoActualizado = new ProductoDTO(idProducto: "AZCR0343", nombre: "azucar", cantidad: 5)

        when: "se actualiza el producto de ese carrito"
        carritoController.actualizarProducto("123", productoActualizado)

        then: "la cantidad del producto en el carrito debe ser la nueva cantidad"
        //tomo el carrito de id 123
        def carrito = carritoController.carritos.get("123")
        // como las arrow functions             closure
        carrito.getProductos().find { productoBuscado -> productoBuscado.idProducto == "AZCR0343" }.cantidad == 5
        //validar que no sea null si no encuentra el producto?
        // def productoExistente = carrito.getProductos().find { productoBuscado -> productoBuscado.idProducto == "prod1" }
        // assert productoExistente != null : "El producto con idProducto 'prod1' no se encontró en el carrito"
        // assert productoExistente.cantidad == 5
    }

    def "devolver Not Found cuando se busca actualizar un producto que no existe"() {
        given: "un carrito con un producto"
        def carritoController = new CarritoController()
        def producto = new ProductoDTO(idProducto: "LNTJ0022", nombre: "lentejas", cantidad: 3)
        carritoController.agregarProducto("123", producto)

        and: "un producto que tiene un dato que reemplaza"
        def productoActualizado = new ProductoDTO(idProducto: "AZCR0343", nombre: "azucar", cantidad: 5)

        when: "se actualiza el producto de ese carrito (se intenta)"
        def response = carritoController.actualizarProducto("123", productoActualizado)

        then: "la respuesta debe ser Not Found con un mensaje de error"
        response.statusCode == HttpStatus.NOT_FOUND
        response.body == "Producto no encontrado en el carrito 123"
    }

    def "devolver Not Found si el carrito al que se le crea el pedido no existe"() {
        given: "un controlador sin carritos"
        def carritoController = new CarritoController()

        when: "se crea un pedido con un idCarrito que no existe"
        def response = carritoController.crearPedido("123")

        then: "la respuesta debe ser Not Found con un mensaje de error"
        response.statusCode == HttpStatus.NOT_FOUND
        response.body == "Carrito no encontrado"
    }

    //el de crear pedido valida que los mensajes esten
    // devolver Ok si el pedido se crea y encolar mensajes
    def "encolar mensajes de pedido creado y listo para ser armado a las colas de mensajesClientes y mensajesVendedores"() {
        given: "un carrito con un producto"
        def exchange = new Exchange()
        def carritoController = new CarritoController()
        carritoController.exchange = exchange
        carritoController.agregarProducto("123", new ProductoDTO(idProducto: "LNTJ0022", nombre: "lentejas", cantidad: 3))

        when: "se crea el pedido del carrito"
        def response = carritoController.crearPedido("123")

        then: "la respuesta debe ser OK con un mensaje de exito"
        response.statusCode == HttpStatus.OK
        response.body == "Pedido creado para el carrito de ID 123"

        and: "debe haber un mensaje en mensajesClientes"
        def colaClientes = exchange.obtenerCola("mensajesClientes")
        //el primer mensaje es el de producto agregado
        colaClientes.size() == 2
        colaClientes[1].subject == "Se realizó el pedido, en los próximos días lo estará recibiendo"
        colaClientes[1].idCarrito == "123"

        and: "debe haber un mensaje en mensajesVendedores"
        def colaVendedores = exchange.obtenerCola("mensajesVendedores")
        colaVendedores.size() == 1
        colaVendedores[0].subject == "Pedido listo para ser armado del carrito 123"
        colaVendedores[0].groupId == "Procesado"
    }


}
//https://spockframework.org/spock/docs/2.3/spock_primer.html
//https://www.tutorialspint.com/groovy/groovy_find.htm
//nota-2: que los unit test sean lo mas segmentados posibles