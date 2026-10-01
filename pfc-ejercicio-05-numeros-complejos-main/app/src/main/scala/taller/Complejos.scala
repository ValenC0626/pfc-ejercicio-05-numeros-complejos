package taller

/** Punto 4. Un número complejo r + i·i, con r la parte real e i la
  * imaginaria. Los objetos no cambian: cada operación devuelve uno nuevo.
  *
  * Tal como está compila y las pruebas quedan en rojo.
  */
class Complejos(val r: Double, val i: Double) {

  def +(otro: Complejos): Complejos =
    new Complejos(r + otro.r, i + otro.i)

  def -(otro: Complejos): Complejos =
    new Complejos(r - otro.r, i - otro.i)

  def *(otro: Complejos): Complejos =
    new Complejos(
      r * otro.r - i * otro.i,
      r * otro.i + i * otro.r
    )

  def /(otro: Complejos): Complejos = {
    val denominador = otro.r * otro.r + otro.i * otro.i

    new Complejos(
      (r * otro.r + i * otro.i) / denominador,
      (i * otro.r - r * otro.i) / denominador
    )
  }

  // "a + bi" con las dos partes redondeadas a tres decimales; si la parte
  // imaginaria es negativa, "a - bi".
  override def toString: String = {
    val real = BigDecimal(r).setScale(3, BigDecimal.RoundingMode.HALF_UP)
    val imag = BigDecimal(i).setScale(3, BigDecimal.RoundingMode.HALF_UP)

    if (i < 0)
      s"$real - ${imag.abs}i"
    else
      s"$real + ${imag}i"
  }
}