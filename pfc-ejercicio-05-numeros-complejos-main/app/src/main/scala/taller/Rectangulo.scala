package taller

/** Punto 1. Un rectángulo dado por su base y su altura, ambas enteras. Los
  * objetos no cambian: rotar y escalar devuelven un rectángulo nuevo.
  *
  * Tal como está compila y las pruebas quedan en rojo.
  */
class Rectangulo(b: Int, h: Int) {

  // Selectoras: la base y la altura con que se construyó el rectángulo.
  def base: Int = b // Completar

  def altura: Int = h 

  def area: Int = base * altura 

  def perimetro: Int = 2 * (base + altura) 

  def esCuadrado: Boolean = base == altura 

  // El rectángulo con base y altura intercambiadas.
  def rotar: Rectangulo = new Rectangulo(altura, base) 

  // El rectángulo con los dos lados multiplicados por k.
  def escalar(k: Int): Rectangulo = new Rectangulo(base * k, altura * k) 

  // Si este rectángulo entra dentro de otro, tal cual o rotado.
  def cabeEn(otro: Rectangulo): Boolean = {
    (base <= otro.base && altura <= otro.altura) || (base <= otro.altura && altura <= otro.base)
  } 

  // El de mayor área entre este y otro; con áreas iguales, este.
  def elMayor(otro: Rectangulo): Rectangulo = {
    if (area >= otro.area) this else otro
  } 

 // La forma "3x4": base, la letra x y altura.
override def toString: String = s"${base}x${altura}"

}

