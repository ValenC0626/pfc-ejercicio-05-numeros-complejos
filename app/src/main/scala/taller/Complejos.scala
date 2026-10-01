package taller

/** Punto 4. Un número complejo r + i·i, con r la parte real e i la
  * imaginaria. Los objetos no cambian: cada operación devuelve uno nuevo.
  *
  * Tal como está compila y las pruebas quedan en rojo.
  */
class Complejos(val r: Double, val i: Double) {

  def +(otro: Complejos): Complejos = new Complejos(0, 0) // Completar

  def -(otro: Complejos): Complejos = new Complejos(0, 0) // Completar

  def *(otro: Complejos): Complejos = new Complejos(0, 0) // Completar

  def /(otro: Complejos): Complejos = new Complejos(0, 0) // Completar

  // "a + bi" con las dos partes redondeadas a tres decimales; si la parte
  // imaginaria es negativa, "a - bi".
  override def toString: String = "" // Completar
}
