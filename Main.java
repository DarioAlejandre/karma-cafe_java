import java.util.Locale;
import java.util.Scanner;

/**
 * SIMULADOR BÁSICO DE VENTA - Morning Bell Coffee Roasters
 * Bolsas de café de 250 g.
 *
 * Proyecto para el ordinario de Introducción a los Sistemas Computacionales.
 *
 * El programa muestra un menú en la consola para:
 *   1. Ver el catálogo de cafés (todos se venden en bolsas de 250 g).
 *   2. Agregar bolsas al carrito.
 *   3. Ver el carrito con los totales.
 *   4. Quitar un producto del carrito.
 *   5. Pagar: recibe el dinero del cliente, calcula el cambio y saca el recibo.
 *   6. Salir.
 */
public class Main {

    private static final Scanner teclado = new Scanner(System.in);
    private static final String LINEA = "------------------------------------------------------------";

    /** El catálogo de la tienda: 3 cafés, siempre en bolsas de 250 g. */
    private static Producto[] catalogo;

    private static final Carrito carrito = new Carrito();

    /** Número de ventas cobradas, para numerar los recibos. */
    private static int ventasRealizadas = 0;

    public static void main(String[] args) {
        cargarCatalogo();
        mostrarBienvenida();

        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            int opcion = leerEntero("Elige una opción: ");

            switch (opcion) {
                case 1:
                    mostrarCatalogo();
                    break;
                case 2:
                    agregarAlCarrito();
                    break;
                case 3:
                    mostrarCarrito();
                    break;
                case 4:
                    quitarDelCarrito();
                    break;
                case 5:
                    pagar();
                    break;
                case 6:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Escribe un número del 1 al 6.");
            }
        }

        System.out.println();
        System.out.println("Gracias por usar el simulador. ¡Buen café!");
        teclado.close();
    }

    /* ==================== DATOS INICIALES ==================== */

    private static void cargarCatalogo() {
        catalogo = new Producto[3];
        catalogo[0] = new Producto("House Blend", "Medio", "Chocolate, nuez, panela", 12.50, 20);
        catalogo[1] = new Producto("Guji Etiopía", "Claro", "Durazno, jazmín, miel", 14.00, 15);
        catalogo[2] = new Producto("Huila Colombia", "Medio oscuro", "Cacao, caramelo, naranja", 13.50, 18);
    }

    private static void mostrarBienvenida() {
        System.out.println();
        System.out.println(LINEA);
        System.out.println("   MORNING BELL COFFEE ROASTERS");
        System.out.println("   Simulador de venta - bolsas de 250 g");
        System.out.println(LINEA);
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("--- MENÚ ---");
        System.out.println("1. Ver catálogo");
        System.out.println("2. Agregar bolsas de 250 g al carrito");
        System.out.println("3. Ver carrito y totales");
        System.out.println("4. Quitar un producto del carrito");
        System.out.println("5. Pagar");
        System.out.println("6. Salir");
    }

    /* ==================== OPCIONES DEL MENÚ ==================== */

    private static void mostrarCatalogo() {
        System.out.println();
        System.out.println(LINEA);
        System.out.println(" CATÁLOGO (todas las bolsas son de 250 g)");
        System.out.println(LINEA);
        for (int i = 0; i < catalogo.length; i++) {
            Producto p = catalogo[i];
            System.out.println((i + 1) + ". " + p.getNombre() + " - tueste " + p.getTueste());
            System.out.println("   Notas: " + p.getNotas());
            System.out.println("   Precio: " + formato(p.getPrecio()) + " / 250 g"
                    + "   |   Disponibles: " + p.getStock() + " bolsas");
        }
        System.out.println(LINEA);
        System.out.println("A partir de " + Carrito.BOLSAS_PARA_DESCUENTO
                + " bolsas en total: 5% de descuento.");
    }

    private static void agregarAlCarrito() {
        mostrarCatalogo();

        int numero = leerEntero("Número de producto (0 para cancelar): ");
        if (numero == 0) {
            System.out.println("No se agregó nada.");
            return;
        }
        if (numero < 1 || numero > catalogo.length) {
            System.out.println("Ese producto no existe.");
            return;
        }

        Producto producto = catalogo[numero - 1];

        // Lo que se puede vender = stock real menos lo que ya va en el carrito.
        int disponibles = producto.getStock() - carrito.bolsasEnCarrito(producto);
        if (disponibles <= 0) {
            System.out.println("No quedan bolsas disponibles de " + producto.getNombre() + ".");
            return;
        }

        int cantidad = leerEntero("¿Cuántas bolsas de 250 g? (1 a " + disponibles + ", 0 para cancelar): ");
        if (cantidad == 0) {
            System.out.println("No se agregó nada.");
            return;
        }
        if (cantidad < 1 || cantidad > disponibles) {
            System.out.println("Cantidad no válida. Intenta de nuevo.");
            return;
        }

        carrito.agregar(producto, cantidad);
        System.out.println("Agregadas " + cantidad + " bolsa(s) de " + producto.getNombre()
                + " (" + (cantidad * 250) + " g en total).");
        System.out.println("Total del carrito hasta ahora: " + formato(carrito.total()));
    }

    private static void mostrarCarrito() {
        if (carrito.estaVacio()) {
            System.out.println();
            System.out.println("El carrito está vacío. Usa la opción 2 para agregar bolsas de 250 g.");
            return;
        }

        System.out.println();
        System.out.println(LINEA);
        System.out.println(" CARRITO");
        System.out.println(LINEA);

        int i = 1;
        for (ItemVenta item : carrito.getItems()) {
            System.out.println(i + ". " + item.getCantidad() + " x " + item.getProducto().getNombre()
                    + " (250 g) a " + formato(item.getProducto().getPrecio())
                    + "  =  " + formato(item.subtotal()));
            i++;
        }

        System.out.println(LINEA);
        System.out.println("Bolsas en total: " + carrito.totalBolsas()
                + " (" + (carrito.totalBolsas() * 250) + " g)");
        System.out.println("Subtotal:   " + formato(carrito.subtotal()));
        if (carrito.descuento() > 0) {
            System.out.println("Descuento: -" + formato(carrito.descuento()) + " (5%)");
        } else {
            System.out.println("Descuento:  " + formato(0) + " (desde "
                    + Carrito.BOLSAS_PARA_DESCUENTO + " bolsas)");
        }
        System.out.println("TOTAL:      " + formato(carrito.total()));
        System.out.println(LINEA);
    }

    private static void quitarDelCarrito() {
        if (carrito.estaVacio()) {
            System.out.println("El carrito está vacío, no hay nada que quitar.");
            return;
        }

        mostrarCarrito();
        int numero = leerEntero("Número del producto a quitar (0 para cancelar): ");
        if (numero == 0) {
            System.out.println("No se quitó nada.");
            return;
        }
        if (numero < 1 || numero > carrito.getItems().size()) {
            System.out.println("Ese número no está en el carrito.");
            return;
        }

        ItemVenta quitado = carrito.getItems().get(numero - 1);
        carrito.quitar(numero - 1);
        System.out.println("Quitado: " + quitado.getCantidad() + " x " + quitado.getProducto().getNombre() + ".");
    }

    private static void pagar() {
        if (carrito.estaVacio()) {
            System.out.println("El carrito está vacío. No hay nada que cobrar.");
            return;
        }

        mostrarCarrito();

        double total = carrito.total();
        double pagado = 0;

        // Se repite hasta que el cliente pague lo suficiente o cancele con 0.
        while (pagado < total) {
            pagado = leerDecimal("¿Con cuánto paga el cliente? (0 para cancelar): ");
            if (pagado == 0) {
                System.out.println("Venta cancelada. El carrito sigue igual.");
                return;
            }
            if (pagado < total) {
                System.out.println("Falta dinero: se necesitan " + formato(total)
                        + " y llegaron " + formato(pagado) + ".");
            }
        }

        double cambio = pagado - total;

        // La venta se cerró: ahora sí se descuenta del inventario.
        for (ItemVenta item : carrito.getItems()) {
            item.getProducto().descontarStock(item.getCantidad());
        }

        ventasRealizadas++;

        System.out.println();
        System.out.println("=================== RECIBO ===================");
        System.out.println(" Morning Bell Coffee Roasters");
        System.out.println(" Venta #" + ventasRealizadas);
        System.out.println(LINEA);
        for (ItemVenta item : carrito.getItems()) {
            System.out.println(" " + item.getCantidad() + " x " + item.getProducto().getNombre()
                    + " 250 g .......... " + formato(item.subtotal()));
        }
        System.out.println(LINEA);
        System.out.println(" Subtotal:   " + formato(carrito.subtotal()));
        if (carrito.descuento() > 0) {
            System.out.println(" Descuento: -" + formato(carrito.descuento()));
        }
        System.out.println(" TOTAL:      " + formato(total));
        System.out.println(" Pagado:     " + formato(pagado));
        System.out.println(" Cambio:     " + formato(cambio));
        System.out.println("==============================================");

        carrito.vaciar();
        System.out.println("Venta registrada y carrito vacío. ¡Gracias!");
    }

    /* ==================== AYUDAS DE ENTRADA Y FORMATO ==================== */

    /**
     * Pide un número entero y no deja avanzar hasta que se escriba bien.
     * Así el programa no se cae si alguien escribe "dos" o deja el espacio vacío.
     */
    private static int leerEntero(String mensaje) {
        while (true) {
            verificarEntrada();
            System.out.print(mensaje);
            String texto = teclado.nextLine().trim();
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("  -> Escribe solo números enteros, por ejemplo 2.");
            }
        }
    }

    private static double leerDecimal(String mensaje) {
        while (true) {
            verificarEntrada();
            System.out.print(mensaje);
            String texto = teclado.nextLine().trim().replace(",", ".");
            try {
                return Double.parseDouble(texto);
            } catch (NumberFormatException e) {
                System.out.println("  -> Escribe un monto, por ejemplo 20 o 20.50");
            }
        }
    }

    /**
     * Si ya no hay más líneas para leer (por ejemplo cuando la entrada se
     * redirige desde un archivo), el programa se despide en vez de fallar.
     */
    private static void verificarEntrada() {
        if (!teclado.hasNextLine()) {
            System.out.println();
            System.out.println("Se terminó la entrada de datos. Fin del programa.");
            System.exit(0);
        }
    }

    /** Da formato al dinero: 12.50 € */
    private static String formato(double valor) {
        return String.format(Locale.US, "%.2f", valor) + " €";
    }
}
