package taller

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ComplejosTest extends AnyFunSuite {
  val q1 = new Complejos(1.0, 2.0)
  val q2 = new Complejos(4.0, 3.0)
  val q3 = new Complejos(3.0, 6.0)

  test("toString escribe a + bi con las dos partes") {
    assert(q1.toString == "1.0 + 2.0i")
    assert(q2.toString == "4.0 + 3.0i")
    assert(q3.toString == "3.0 + 6.0i")
  }

  test("suma de complejos") {
    assert((q1 + q2).toString == "5.0 + 5.0i")
    assert((q2 + q3).toString == "7.0 + 9.0i")
  }

  test("resta de complejos y la parte imaginaria negativa se escribe con menos") {
    assert((q1 - q2).toString == "-3.0 - 1.0i")
    assert((q2 - q3).toString == "1.0 - 3.0i")
  }

  test("multiplicación de complejos") {
    assert((q1 * q2).toString == "-2.0 + 11.0i")
    assert((q2 * q3).toString == "-6.0 + 33.0i")
  }

  test("división de complejos con redondeo a tres decimales") {
    assert((q1 / q2).toString == "0.4 + 0.2i")
    assert((q2 / q3).toString == "0.667 - 0.333i")
  }

  test("casos que fuerzan el redondeo y la parte imaginaria cero") {
    assert((q1 / q1).toString == "1.0 + 0.0i")
    assert((q1 - q1).toString == "0.0 + 0.0i")
    assert((new Complejos(2.0, 0.0) / new Complejos(3.0, 0.0)).toString == "0.667 + 0.0i")
    assert((new Complejos(1.0, 1.0) / new Complejos(8.0, 0.0)).toString == "0.125 + 0.125i")
    assert((new Complejos(0.0, 1.0) * new Complejos(0.0, 1.0)).toString == "-1.0 + 0.0i")
  }

  test("los operadores de Complejos siguen la precedencia de los enteros") {
    assert((q1 + q2 * q3).toString == "-5.0 + 35.0i")
    assert(((q1 + q2) * q3).toString == "-15.0 + 45.0i")
  }
}
