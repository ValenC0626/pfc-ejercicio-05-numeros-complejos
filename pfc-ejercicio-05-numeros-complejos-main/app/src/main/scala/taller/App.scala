package taller

object App {

  def main(args: Array[String]): Unit = {

    println("===================================")
    println("        PRUEBAS DEL TALLER")
    println("===================================")

    // PUNTO 1 - RECTANGULO
  
    println()
    println("---------- PUNTO 1: RECTANGULO ----------")

    val rect1 = new Rectangulo(3, 4)
    val rect2 = new Rectangulo(5, 5)

    println("Rectángulo 1: " + rect1)
    println("Base: " + rect1.base)
    println("Altura: " + rect1.altura)
    println("Área: " + rect1.area)
    println("Perímetro: " + rect1.perimetro)
    println("¿Es cuadrado?: " + rect1.esCuadrado)
    println("Rotado: " + rect1.rotar)
    println("Escalado x2: " + rect1.escalar(2))
    println("¿Cabe en rectángulo 2?: " + rect1.cabeEn(rect2))
    println("El de mayor área: " + rect1.elMayor(rect2))

    // PUNTO 2 - FECHA

    println()
    println("---------- PUNTO 2: FECHA ----------")

    val fecha1 = new Fecha(29, 2, 2024)

    println("Fecha: " + fecha1)
    println("Día del año: " + fecha1.diaDelAnio)
    println("Día siguiente: " + fecha1.siguiente)
    println("10 días después: " + fecha1.masDias(10))

    val fecha2 = new Fecha(15, 3, 2024)

    println("Otra fecha: " + fecha2)
    println("¿Fecha 1 es anterior a fecha 2?: " + fecha1.antesDe(fecha2))

    // PUNTO 3 - RACIONAL

    println()
    println("---------- PUNTO 3: RACIONAL ----------")

    val r1 = new Racional(1, 2)
    val r2 = new Racional(1, 4)

    println("Racional 1: " + r1)
    println("Racional 2: " + r2)

    println("Numerador de r1: " + r1.numer)
    println("Denominador de r1: " + r1.denom)

    println("Suma: " + (r1 + r2))
    println("Resta: " + (r1 - r2))
    println("Multiplicación: " + (r1 * r2))
    println("División: " + (r1 / r2))

    println("¿Son iguales?: " + (r1 == r2))
    println("¿r1 es menor que r2?: " + (r1 < r2))
    println("Mayor: " + r1.max(r2))


    // =====================================================
    // PUNTO 4 - COMPLEJOS
    // =====================================================

    println()
    println("---------- PUNTO 4: COMPLEJOS ----------")

    val c1 = new Complejos(3, 4)
    val c2 = new Complejos(1, 2)

    println("Complejo 1: " + c1)
    println("Complejo 2: " + c2)

    println("Suma: " + (c1 + c2))
    println("Resta: " + (c1 - c2))
    println("Multiplicación: " + (c1 * c2))
    println("División: " + (c1 / c2))


    println()
    println("===================================")
    println("       FIN DE LAS PRUEBAS")
    println("===================================")
  }
}