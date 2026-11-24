/**
 * Producto = una bolsa de café de 250 g que la tienda vende.
 */
public class Producto {

    private final String nombre;
    private final String tueste;
    private final String notas;
    private final double precio;
    private int stock;

    public Producto(String nombre, String tueste, String notas, double precio, int stock) {
        this.nombre = nombre;
        this.tueste = tueste;
        this.notas = notas;
        this.precio = precio;
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTueste() {
        return tueste;
    }

    public String getNotas() {
        return notas;
    }

    /** Precio de UNA bolsa de 250 g. */
    public double getPrecio() {
        return precio;
    }

    /** Bolsas de 250 g que quedan en el inventario. */
    public int getStock() {
        return stock;
    }

    /** Llama este método cuando una venta se cobra: baja las bolsas del inventario. */
    public void descontarStock(int bolsas) {
        stock = stock - bolsas;
    }
}
