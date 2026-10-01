package taller

/** Punto 3. Un número racional x/y guardado en su forma normalizada:
  * numerador y denominador sin factores comunes, el signo en el numerador y
  * el denominador siempre positivo. El cero se guarda como 0/1.
  *
  * Tal como está compila y las pruebas quedan en rojo.
  */
class Racional(x: Int, y: Int) {

  // Precondición del constructor: el denominador no es cero.
  // Completar

  // El máximo común divisor de dos enteros no negativos.
  private def mcd(a: Int, b: Int): Int = 0 // Completar

  // Numerador y denominador ya normalizados.
  def numer: Int = 0 // Completar

  def denom: Int = 1 // Completar

  def +(r: Racional): Racional = new Racional(0, 1) // Completar

  def -(r: Racional): Racional = new Racional(0, 1) // Completar

  def *(r: Racional): Racional = new Racional(0, 1) // Completar

  def /(r: Racional): Racional = new Racional(0, 1) // Completar

  // Si los dos racionales representan el mismo número.
  def ==(r: Racional): Boolean = false // Completar

  def <(r: Racional): Boolean = false // Completar

  // El mayor de los dos.
  def max(r: Racional): Racional = new Racional(0, 1) // Completar

  // "n/d", o solo "n" cuando el denominador es 1.
  override def toString: String = "" // Completar
}
