package taller

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class RacionalTest extends AnyFunSuite {
  val r1 = new Racional(1, 2)
  val r2 = new Racional(2, 3)

  test("el constructor simplifica con el mcd") {
    assert(new Racional(6, 4).numer == 3)
    assert(new Racional(6, 4).denom == 2)
    assert(new Racional(6, 4).toString == "3/2")
    assert(new Racional(1, 3).toString == "1/3")
    assert(new Racional(10, 15).toString == "2/3")
  }

  test("el signo queda en el numerador y el denominador es positivo") {
    assert(new Racional(3, -4).numer == -3)
    assert(new Racional(3, -4).denom == 4)
    assert(new Racional(3, -4).toString == "-3/4")
    assert(new Racional(-3, -4).toString == "3/4")
    assert(new Racional(6, -4).toString == "-3/2")
    assert(new Racional(-6, 4).toString == "-3/2")
  }

  test("los enteros se imprimen sin denominador y el cero es 0") {
    assert(new Racional(4, 2).toString == "2")
    assert(new Racional(-6, 3).toString == "-2")
    assert(new Racional(7, 1).toString == "7")
    assert(new Racional(0, -5).numer == 0)
    assert(new Racional(0, -5).denom == 1)
    assert(new Racional(0, -5).toString == "0")
  }

  test("el denominador cero no construye un racional") {
    assertThrows[IllegalArgumentException] { new Racional(1, 0) }
    assertThrows[IllegalArgumentException] { new Racional(0, 0) }
  }

  test("las cuatro operaciones devuelven racionales ya simplificados") {
    assert((r1 + r2).toString == "7/6")
    assert((r1 - r2).toString == "-1/6")
    assert((r1 * r2).toString == "1/3")
    assert((r1 / r2).toString == "3/4")
    assert((new Racional(1, -2) + r1).toString == "0")
    assert((new Racional(3, -4) * new Racional(-4, 3)).toString == "1")
    assert((new Racional(1, 3) / new Racional(-2, 3)).toString == "-1/2")
  }

  test("dividir por cero cae en la precondición del constructor") {
    assertThrows[IllegalArgumentException] { r1 / new Racional(0, 1) }
  }

  test("los operadores respetan la precedencia de la aritmética") {
    assert((r1 * r1 + r2 * r2).toString == "25/36")
    assert((r1 + r2 * new Racional(3, 4)).toString == "1")
  }

  test("== compara valores, no representaciones") {
    assert(r1 == new Racional(2, 4))
    assert(!(r1 == new Racional(1, 3)))
    assert(new Racional(-1, 2) == new Racional(1, -2))
  }

  test("< y max también con racionales negativos") {
    assert(new Racional(1, 3) < r1)
    assert(!(r1 < new Racional(1, 3)))
    assert(new Racional(-1, 2) < new Racional(1, -3))
    assert((r1 max new Racional(1, 3)).toString == "1/2")
    assert((new Racional(1, 3) max r1).toString == "1/2")
    assert((new Racional(-1, 2) max new Racional(-1, 3)).toString == "-1/3")
    assert((r1 max r2).toString == "2/3")
  }
}
