package taller

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FechaTest extends AnyFunSuite {

  test("las selectoras y toString con la forma día/mes/año") {
    assert(new Fecha(5, 3, 2024).dia == 5)
    assert(new Fecha(5, 3, 2024).mes == 3)
    assert(new Fecha(5, 3, 2024).anio == 2024)
    assert(new Fecha(29, 2, 2024).toString == "29/2/2024")
    assert(new Fecha(1, 12, 1999).toString == "1/12/1999")
  }

  test("el constructor rechaza meses fuera de 1 a 12") {
    assertThrows[IllegalArgumentException] { new Fecha(1, 13, 2024) }
    assertThrows[IllegalArgumentException] { new Fecha(1, 0, 2024) }
  }

  test("el constructor rechaza días que el mes no tiene") {
    assertThrows[IllegalArgumentException] { new Fecha(0, 5, 2024) }
    assertThrows[IllegalArgumentException] { new Fecha(32, 1, 2024) }
    assertThrows[IllegalArgumentException] { new Fecha(31, 4, 2024) }
    assertThrows[IllegalArgumentException] { new Fecha(30, 2, 2024) }
    assertThrows[IllegalArgumentException] { new Fecha(29, 2, 2023) }
    assertThrows[IllegalArgumentException] { new Fecha(29, 2, 1900) }
  }

  test("el constructor acepta el último día de cada tipo de mes") {
    assert(new Fecha(31, 1, 2024).toString == "31/1/2024")
    assert(new Fecha(30, 4, 2024).toString == "30/4/2024")
    assert(new Fecha(29, 2, 2024).toString == "29/2/2024")
    assert(new Fecha(29, 2, 2000).toString == "29/2/2000")
    assert(new Fecha(28, 2, 2023).toString == "28/2/2023")
  }

  test("diaDelAnio cuenta desde el 1 de enero y distingue los bisiestos") {
    assert(new Fecha(1, 1, 2024).diaDelAnio == 1)
    assert(new Fecha(1, 3, 2023).diaDelAnio == 60)
    assert(new Fecha(1, 3, 2024).diaDelAnio == 61)
    assert(new Fecha(15, 8, 2026).diaDelAnio == 227)
    assert(new Fecha(31, 12, 2023).diaDelAnio == 365)
    assert(new Fecha(31, 12, 2024).diaDelAnio == 366)
  }

  test("siguiente avanza un día dentro del mes") {
    assert(new Fecha(15, 8, 2026).siguiente.toString == "16/8/2026")
    assert(new Fecha(28, 2, 2024).siguiente.toString == "29/2/2024")
    assert(new Fecha(28, 2, 2000).siguiente.toString == "29/2/2000")
  }

  test("siguiente cambia de mes y de año cuando el mes se acaba") {
    assert(new Fecha(29, 2, 2024).siguiente.toString == "1/3/2024")
    assert(new Fecha(28, 2, 2023).siguiente.toString == "1/3/2023")
    assert(new Fecha(28, 2, 1900).siguiente.toString == "1/3/1900")
    assert(new Fecha(30, 4, 2024).siguiente.toString == "1/5/2024")
    assert(new Fecha(31, 12, 2023).siguiente.toString == "1/1/2024")
  }

  test("masDias repite siguiente y con 0 devuelve la misma fecha") {
    assert(new Fecha(25, 12, 2023).masDias(10).toString == "4/1/2024")
    assert(new Fecha(27, 2, 2024).masDias(3).toString == "1/3/2024")
    assert(new Fecha(1, 1, 2023).masDias(365).toString == "1/1/2024")
    assert(new Fecha(1, 1, 2024).masDias(366).toString == "1/1/2025")
    assert(new Fecha(15, 8, 2026).masDias(0).toString == "15/8/2026")
  }

  test("antesDe compara el año y luego la posición en el año, no el día suelto") {
    assert(new Fecha(5, 3, 2024).antesDe(new Fecha(1, 4, 2024)))
    assert(!new Fecha(1, 4, 2024).antesDe(new Fecha(5, 3, 2024)))
    assert(!new Fecha(5, 3, 2024).antesDe(new Fecha(5, 3, 2024)))
    assert(new Fecha(31, 12, 2023).antesDe(new Fecha(1, 1, 2024)))
    assert(!new Fecha(1, 1, 2025).antesDe(new Fecha(31, 12, 2024)))
  }
}
