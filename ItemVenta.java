/**
 * ItemVenta = una línea del carrito: un producto + cuántas bolsas de 250 g.
 */
public class ItemVenta {

    private final Producto producto;
    private int cantidad;

    public ItemVenta(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /** Lo que cuesta esta línea: precio de la bolsa x número de bolsas. */
    public double subtotal() {
        return producto.getPrecio() * cantidad;
    }
}
