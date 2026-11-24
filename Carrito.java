import java.util.ArrayList;
import java.util.List;

/**
 * Carrito de compras: la lista de productos (bolsas de 250 g) que el cliente
 * lleva y los cálculos de la cuenta.
 *
 * Regla de la tienda: a partir de 4 bolsas en total se aplica un 5% de descuento.
 */
public class Carrito {

    public static final int BOLSAS_PARA_DESCUENTO = 4;
    public static final double PORCENTAJE_DESCUENTO = 0.05;

    private final List<ItemVenta> items = new ArrayList<ItemVenta>();

    /**
     * Agrega bolsas al carrito. Si el producto ya está en el carrito,
     * solo suma la cantidad en la misma línea en vez de repetir el producto.
     */
    public void agregar(Producto producto, int cantidad) {
        for (ItemVenta item : items) {
            if (item.getProducto() == producto) {
                item.setCantidad(item.getCantidad() + cantidad);
                return;
            }
        }
        items.add(new ItemVenta(producto, cantidad));
    }

    /** Cuántas bolsas de un producto ya van en el carrito. */
    public int bolsasEnCarrito(Producto producto) {
        for (ItemVenta item : items) {
            if (item.getProducto() == producto) {
                return item.getCantidad();
            }
        }
        return 0;
    }

    public void quitar(int indice) {
        items.remove(indice);
    }

    public void vaciar() {
        items.clear();
    }

    public boolean estaVacio() {
        return items.isEmpty();
    }

    public List<ItemVenta> getItems() {
        return items;
    }

    /** Total de bolsas de 250 g en el carrito. */
    public int totalBolsas() {
        int total = 0;
        for (ItemVenta item : items) {
            total = total + item.getCantidad();
        }
        return total;
    }

    public double subtotal() {
        double suma = 0;
        for (ItemVenta item : items) {
            suma = suma + item.subtotal();
        }
        return suma;
    }

    /** Descuento en dinero (0 si el cliente lleva menos de 4 bolsas). */
    public double descuento() {
        if (totalBolsas() >= BOLSAS_PARA_DESCUENTO) {
            return subtotal() * PORCENTAJE_DESCUENTO;
        }
        return 0;
    }

    /** Lo que el cliente debe pagar. */
    public double total() {
        return subtotal() - descuento();
    }
}
