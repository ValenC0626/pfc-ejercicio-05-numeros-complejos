package taller

/** Punto 1. Un rectángulo dado por su base y su altura, ambas enteras. Los
  * objetos no cambian: rotar y escalar devuelven un rectángulo nuevo.
  *
  * Tal como está compila y las pruebas quedan en rojo.
  */
class Rectangulo(b: Int, h: Int) {

  // Selectoras: la base y la altura con que se construyó el rectángulo.
  def base: Int = 0 // Completar

  def altura: Int = 0 // Completar

  def area: Int = 0 // Completar

  def perimetro: Int = 0 // Completar

  def esCuadrado: Boolean = false // Completar

  // El rectángulo con base y altura intercambiadas.
  def rotar: Rectangulo = new Rectangulo(0, 0) // Completar

  // El rectángulo con los dos lados multiplicados por k.
  def escalar(k: Int): Rectangulo = new Rectangulo(0, 0) // Completar

  // Si este rectángulo entra dentro de otro, tal cual o rotado.
  def cabeEn(otro: Rectangulo): Boolean = false // Completar

  // El de mayor área entre este y otro; con áreas iguales, este.
  def elMayor(otro: Rectangulo): Rectangulo = new Rectangulo(0, 0) // Completar

  // La forma "3x4": base, la letra x y altura.
  override def toString: String = "" // Completar
}
