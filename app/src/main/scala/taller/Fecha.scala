package taller

/** Punto 2. Una fecha del calendario gregoriano: día, mes y año. El
  * constructor rechaza las fechas que no existen, así que todo objeto Fecha
  * es una fecha real.
  *
  * Tal como está compila y las pruebas quedan en rojo.
  */
class Fecha(val dia: Int, val mes: Int, val anio: Int) {

  // Precondiciones del constructor: el mes va de 1 a 12 y el día existe en
  // ese mes de ese año. Una fecha que no cumple lanza
  // IllegalArgumentException.
  // Completar

  // Si el año es bisiesto en el calendario gregoriano.
  private def esBisiesto: Boolean = false // Completar

  // Cuántos días tiene el mes m de este año.
  private def diasDelMes(m: Int): Int = 0 // Completar

  // Qué número de día es esta fecha dentro de su año: el 1 de enero es 1.
  def diaDelAnio: Int = 0 // Completar

  // La fecha del día siguiente.
  def siguiente: Fecha = new Fecha(1, 1, 1) // Completar

  // La fecha n días después de esta, con n mayor o igual que 0.
  def masDias(n: Int): Fecha = new Fecha(1, 1, 1) // Completar

  // Si esta fecha es anterior a otra.
  def antesDe(otra: Fecha): Boolean = false // Completar

  // La forma "29/2/2024": día, mes y año separados por barras.
  override def toString: String = "" // Completar
}
