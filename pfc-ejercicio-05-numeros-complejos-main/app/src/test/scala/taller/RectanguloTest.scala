package taller

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class RectanguloTest extends AnyFunSuite {
  val r34 = new Rectangulo(3, 4)
  val r55 = new Rectangulo(5, 5)
  val r17 = new Rectangulo(1, 7)

  test("las selectoras devuelven lo que recibió el constructor y toString los une con x") {
    assert(r34.base == 3)
    assert(r34.altura == 4)
    assert(r34.toString == "3x4")
    assert(r55.toString == "5x5")
  }

  test("área, perímetro y esCuadrado") {
    assert(r34.area == 12)
    assert(r34.perimetro == 14)
    assert(!r34.esCuadrado)
    assert(r55.area == 25)
    assert(r55.perimetro == 20)
    assert(r55.esCuadrado)
    assert(r17.area == 7)
    assert(r17.perimetro == 16)
  }

  test("rotar intercambia base y altura, y dos rotaciones vuelven al original") {
    assert(r34.rotar.toString == "4x3")
    assert(r34.rotar.rotar.toString == "3x4")
    assert(r55.rotar.toString == "5x5")
  }

  test("escalar multiplica los dos lados por el mismo factor") {
    assert(r34.escalar(3).toString == "9x12")
    assert(r34.escalar(1).toString == "3x4")
    assert(r34.escalar(2).area == 48)
    assert(r17.escalar(0).toString == "0x0")
  }

  test("cabeEn con el rectángulo tal cual") {
    assert(r34.cabeEn(new Rectangulo(3, 4)))
    assert(r34.cabeEn(new Rectangulo(4, 5)))
    assert(!r34.cabeEn(new Rectangulo(3, 3)))
    assert(!r34.cabeEn(new Rectangulo(2, 2)))
  }

  test("cabeEn también vale cuando el rectángulo entra rotado") {
    assert(r34.cabeEn(new Rectangulo(4, 3)))
    assert(new Rectangulo(5, 1).cabeEn(new Rectangulo(2, 6)))
    assert(r17.cabeEn(new Rectangulo(8, 1)))
    assert(!new Rectangulo(5, 1).cabeEn(new Rectangulo(4, 4)))
    assert(!new Rectangulo(2, 6).cabeEn(new Rectangulo(5, 1)))
  }

  test("elMayor compara por área y con empate se queda con el receptor") {
    val r27 = new Rectangulo(2, 7)
    val r26 = new Rectangulo(2, 6)
    assert(r34.elMayor(r27).toString == "2x7")
    assert(r27.elMayor(r34).toString == "2x7")
    assert(r34.elMayor(r26).toString == "3x4")
    assert(r26.elMayor(r34).toString == "2x6")
  }
}
