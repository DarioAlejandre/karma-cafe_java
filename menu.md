# Simulador de venta — Karma Coffee Roasters

Programa en Java para consola que simula la caja de la tienda vendiendo **bolsas de 250 g**. Permite armar un carrito, aplicar la promo de la quinta bolsa gratis al juntar 4 bolsas pagadas, cobrar con cambio, sacar recibo y descontar inventario solo cuando se cierra la venta.

Proyecto para el ordinario de **Introducción a los Sistemas Computacionales**.

---

## 1. Qué hace el programa

| Opción del menú | Qué pasa |
|---|---|
| 1. Ver catálogo | Muestra los 3 cafés, su tueste, notas, precio por **250 g** y bolsas disponibles |
| 2. Agregar bolsas | Eliges el número de café y cuántas bolsas de 250 g vas a meter |
| 3. Ver carrito | Lista de lo que llevas, total de bolsas (pagadas y de regalo) y gramos, subtotal, bonificación y total |
| 4. Quitar producto | Borra una línea completa del carrito |
| 5. Pagar | Pide con cuánto paga el cliente, calcula el cambio, imprime el recibo y baja el inventario |
| 6. Salir | Cierra el programa |

Reglas que ya trae:

- Todo se maneja en **bolsas de 250 g**; en el carrito también se ven los gramos totales.
- **Promo 4 + 1 gratis:** por cada **4 bolsas** que lleve el cliente, **la 5.ª bolsa sale gratis** (se bonifica el precio de esa bolsa en el cálculo del total y se entrega en el pedido).
- El inventario arranca con 20, 15 y 18 bolsas y **solo se descuenta al pagar** (si cancelas o no cierras la compra, el stock no se mueve).
- Si no alcanza el dinero que dan, avisa y vuelve a pedir el pago.
- Acepta punto o coma en montos (`20.50` y `20,50` funcionan igual).
- Si alguien mete letras donde toca número, lo maneja sin que se trabe el programa.

---

## 2. Archivos del proyecto

```text
simulador-ventas/
├── Main.java        menú, lectura por teclado y recibos
├── Producto.java    café en bolsa de 250 g (nombre, tueste, notas, precio, stock)
├── ItemVenta.java   renglón del carrito (producto + cantidad de bolsas)
├── Carrito.java     lista de compra y cálculos (subtotal, 5.ª gratis, total)
└── README.md        este archivo
