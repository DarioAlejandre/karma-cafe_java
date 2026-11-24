import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Carrito {

    public static final int BOLSAS_PARA_GRATIS = 4;

    private final List<ItemVenta> items = new ArrayList<ItemVenta>();

    public void agregar(Producto producto, int cantidad) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
        }
        for (ItemVenta item : items) {
            if (item.getProducto() == producto) {
                item.setCantidad(item.getCantidad() + cantidad);
                return;
            }
        }
        items.add(new ItemVenta(producto, cantidad));
    }

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

    public int totalBolsas() {
        int total = 0;
        for (ItemVenta item : items) {
            total += item.getCantidad();
        }
        return total;
    }

    public int bolsasGratis() {
        return totalBolsas() / (BOLSAS_PARA_GRATIS + 1);
    }

    public double subtotal() {
        double suma = 0;
        for (ItemVenta item : items) {
            suma += item.subtotal();
        }
        return suma;
    }

    public double bonificacion() {
        int gratis = bolsasGratis();
        if (gratis <= 0) {
            return 0;
        }

        List<Double> precios = new ArrayList<Double>();
        for (ItemVenta item : items) {
            for (int i = 0; i < item.getCantidad(); i++) {
                precios.add(item.getProducto().getPrecio());
            }
        }

        Collections.sort(precios);

        double ahorro = 0;
        for (int i = 0; i < gratis && i < precios.size(); i++) {
            ahorro += precios.get(i);
        }
        return ahorro;
    }

    public double total() {
        return subtotal() - bonificacion();
    }
}
