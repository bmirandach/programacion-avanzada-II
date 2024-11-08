import spock.lang.Specification
import com.programacionavanzadaii.trabajoPractico1.controller.CarritoController
import com.programacionavanzadaii.trabajoPractico1.model.ProductoDTO
import com.programacionavanzadaii.trabajoPractico1.model.Exchange
import com.programacionavanzadaii.trabajoPractico1.model.MensajeDTO
import com.programacionavanzadaii.trabajoPractico1.model.ClienteMensaje

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

    //TODO: probar exchange.encolarMensaje que se guarde el mensaje
    // mismo subject?
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
    }
}
//https://spockframework.org/spock/docs/2.3/spock_primer.html
//https://www.tutorialspint.com/groovy/groovy_find.htm
//nota-2: que los unit test sean lo mas segmentados posibles