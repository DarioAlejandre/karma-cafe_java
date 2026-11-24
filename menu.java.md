# Simulador de venta — Morning Bell Coffee Roasters (Java)

Programa de consola que simula la caja de la cafetería: catálogo de cafés
**en bolsas de 250 g**, carrito de compras, pago con cálculo de cambio,
recibo e inventario que baja con cada venta.

Proyecto para el ordinario de **Introducción a los Sistemas Computacionales**.

---

## 1. Qué hace el programa

| Opción del menú | Qué pasa |
|---|---|
| 1. Ver catálogo | Muestra los 3 cafés, su tueste, notas, precio por **250 g** y bolsas disponibles |
| 2. Agregar bolsas | Se elige el número de producto y la cantidad de bolsas de 250 g |
| 3. Ver carrito | Líneas del carrito, total de bolsas y de gramos, subtotal, descuento y total |
| 4. Quitar producto | Saca una línea completa del carrito |
| 5. Pagar | Pide con cuánto paga el cliente, calcula el cambio, imprime el recibo y descuenta el inventario |
| 6. Salir | Cierra el programa |

Reglas incluidas:

- Todo se vende **en bolsas de 250 g**; el carrito también muestra los gramos totales.
- **Descuento del 5%** cuando el cliente lleva **4 bolsas o más** en total.
- El inventario empieza con 20, 15 y 18 bolsas y **solo se descuenta al pagar**
  (si se cancela la venta, el stock queda igual).
- Si el dinero entregado no alcanza, el programa avisa y vuelve a preguntar.
- Acepta montos con punto o con coma: `20.50` y `20,50` funcionan igual.
- Si alguien escribe letras donde va un número, el programa lo corrige sin cerrarse.

---

## 2. Archivos del proyecto

```
simulador-ventas/
├── Main.java        menú, entradas por teclado y recibos
├── Producto.java    un café en bolsa de 250 g (nombre, tueste, notas, precio, stock)
├── ItemVenta.java   una línea del carrito (producto + cantidad de bolsas)
├── Carrito.java     la lista de compra y los cálculos (subtotal, 5%, total)
└── README.md        este archivo
```

---

## 3. Cómo ejecutarlo

### Con NetBeans (lo más común en clase)

1. `File > New Project > Java with Ant > Java Application`.
2. Nombre del proyecto: `SimuladorVentas`. Quita la marca de "Create Main Class".
3. Clic derecho sobre el paquete `simuladorventas` > `New > Java Class` y crea
   `Producto`, `ItemVenta`, `Carrito` y `Main`.
4. Copia y pega el contenido de cada archivo `.java` en su clase.
5. Clic derecho sobre `Main.java` > `Run File` (o `F6`).

### Con Eclipse

1. `File > New > Java Project`, nombre `SimuladorVentas`.
2. Clic derecho en `src` > `New > Class`, y crea las cuatro clases con el mismo nombre.
3. Pega el contenido de cada archivo y ejecuta `Main.java` con `Run`.

### Por terminal (sin IDE)

Se necesita tener el JDK instalado (`javac` y `java` disponibles).

```bash
cd simulador-ventas
javac -encoding UTF-8 *.java
java Main
```

> Si al compilar aparecen errores con las tildes o la ñ (pasa en Windows con
> JDK anteriores al 18), compila así: `javac -encoding UTF-8 *.java`.
> En NetBeans/Eclipse normalmente no hay que hacer nada.

---

## 4. Guion corto para probarlo en clase

1. Opción **1** para mostrar el catálogo: 3 cafés, todos de 250 g.
2. Opción **2**: agregar 2 bolsas de Guji Etiopía (28.00 €).
3. Opción **2**: agregar 2 bolsas de House Blend; conviene enseñar que al llegar
   a 4 bolsas aparece el descuento del 5%.
4. Opción **3**: ver subtotal, descuento y total.
5. Opción **5**: pagar con 100 € y mostrar el recibo con el cambio.
6. Opción **1** otra vez: el stock de esos cafés ya bajó (se ve el inventario).
7. Opción **6**: salir.

---

## 5. Qué se puede explicar del código

- **Clases y objetos**: `Producto`, `ItemVenta` y `Carrito` son clases; cada café
  del catálogo es un objeto con sus datos.
- **Encapsulamiento**: los datos son `private` y se leen con `get...()`.
- **Arreglos**: `catalogo` es un `Producto[]` de 3 posiciones.
- **Colecciones**: el carrito usa `ArrayList` porque el número de compras cambia.
- **Ciclos**: `while` para el menú y para pedir el pago; `for` para recorrer el carrito.
- **Condicionales**: validar cantidades, stock, descuento y dinero insuficiente.
- **Métodos**: el programa está dividido en métodos cortos, uno por opción del menú.
- **Manejo de errores**: `try/catch` con `NumberFormatException` evita que el
  programa se caiga si el usuario escribe letras.

---

## 6. Datos fáciles de cambiar

- **Productos, precios y stock**: método `cargarCatalogo()` en `Main.java`.
- **Descuento**: constantes `BOLSAS_PARA_DESCUENTO` y `PORCENTAJE_DESCUENTO`
  en `Carrito.java`.
- **Nombre de la tienda**: textos `mostrarBienvenida()` y el recibo en `pagar()`.
