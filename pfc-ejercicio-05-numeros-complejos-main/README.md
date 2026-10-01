# Ejercicio 5 — Funciones y datos

Fundamentos de Programación Funcional y Concurrente
Escuela de Ingeniería de Sistemas y Computación, Universidad del Valle
Carlos Andrés Delgado Saavedra

Cuatro tipos propios que se usan como si fueran del lenguaje: un rectángulo,
una fecha, un racional y un complejo. Cada uno es una clase con sus datos y
sus operaciones juntos, y en cada punto entra una pieza más: primero los
métodos, después las precondiciones y los miembros privados, después un
invariante que el constructor sostiene y por último los operadores.

## De las funciones a las clases

Una clase junta datos y funciones en una sola abstracción, e introduce dos
cosas con el mismo nombre: un **tipo** y un **constructor**.

```scala
class Racional(x: Int, y: Int) {
  def numer = x
  def denom = y
}

val r = new Racional(1, 2)    // r es del tipo Racional
r.numer                       // 1
```

Las operaciones pueden quedar afuera, como funciones que reciben dos
racionales:

```scala
def sumaRacional(r: Racional, s: Racional): Racional =
  new Racional(r.numer * s.denom + r.denom * s.numer, r.denom * s.denom)
```

o adentro, como **métodos**. Un método recibe un argumento menos, porque el
primer operando es el objeto sobre el que se llama, `this`; dentro del
cuerpo, `numer` a secas quiere decir `this.numer`:

```scala
class Racional(x: Int, y: Int) {
  def numer = x
  def denom = y
  def suma(r: Racional) =
    new Racional(numer * r.denom + denom * r.numer, denom * r.denom)
  override def toString = numer + "/" + denom
}
```

Dos piezas más completan la clase. `require` al comienzo del cuerpo impone
una precondición a quien construye el objeto y lanza
`IllegalArgumentException` cuando no se cumple; `private` deja un método o un
valor para uso interno de la clase, invisible desde afuera:

```scala
class Racional(x: Int, y: Int) {
  require(y > 0, "El denominador debe ser positivo")
  private def mcd(a: Int, b: Int): Int = if (b == 0) a else mcd(b, a % b)
  private val m = mcd(math.abs(x), y)
  def numer = x / m
  def denom = y / m
}
```

Por último, los símbolos son nombres de método válidos, y todo método de un
parámetro se puede llamar sin punto ni paréntesis. Con `def +(r: Racional)`
en la clase, `r1 + r2` es `r1.+(r2)`, y `r1 max r2` es `r1.max(r2)`. Estos
operadores conservan la precedencia de la aritmética: `*` y `/` se evalúan
antes que `+` y `-`.

## Lo que hay que resolver

Cada punto es una clase y va en su propio archivo de
`app/src/main/scala/taller/`.

### Punto 1: `Rectangulo`, de funciones externas a métodos

```scala
class Rectangulo(b: Int, h: Int) {
  def base: Int
  def altura: Int
  def area: Int
  def perimetro: Int
  def esCuadrado: Boolean
  def rotar: Rectangulo
  def escalar(k: Int): Rectangulo
  def cabeEn(otro: Rectangulo): Boolean
  def elMayor(otro: Rectangulo): Rectangulo
  override def toString: String
}
```

Con la clase reducida a sus dos selectoras, las operaciones quedarían por
fuera:

```scala
def areaRectangulo(r: Rectangulo): Int = r.base * r.altura

def escalarRectangulo(r: Rectangulo, k: Int): Rectangulo =
  new Rectangulo(r.base * k, r.altura * k)

def convertirEnCadena(r: Rectangulo): String = r.base + "x" + r.altura
```

Lo que se pide es escribirlas adentro, como métodos, y agregar las que
faltan. `rotar` intercambia base y altura; `escalar(k)` multiplica los dos
lados por `k`; `cabeEn(otro)` dice si este rectángulo entra en `otro`, tal
cual o rotado; `elMayor(otro)` devuelve el de mayor área y, con áreas
iguales, el receptor, `this`. `toString` escribe la base, la letra `x` y la
altura: `3x4`.

| Llamada | Resultado |
|---|---|
| `new Rectangulo(3, 4).base` | 3 |
| `new Rectangulo(3, 4).altura` | 4 |
| `new Rectangulo(3, 4).toString` | `3x4` |
| `new Rectangulo(3, 4).area` | 12 |
| `new Rectangulo(3, 4).perimetro` | 14 |
| `new Rectangulo(3, 4).esCuadrado` | false |
| `new Rectangulo(5, 5).esCuadrado` | true |
| `new Rectangulo(1, 7).perimetro` | 16 |
| `new Rectangulo(3, 4).rotar.toString` | `4x3` |
| `new Rectangulo(3, 4).rotar.rotar.toString` | `3x4` |
| `new Rectangulo(3, 4).escalar(3).toString` | `9x12` |
| `new Rectangulo(3, 4).escalar(2).area` | 48 |
| `new Rectangulo(1, 7).escalar(0).toString` | `0x0` |
| `new Rectangulo(3, 4).cabeEn(new Rectangulo(4, 5))` | true |
| `new Rectangulo(3, 4).cabeEn(new Rectangulo(4, 3))` | true |
| `new Rectangulo(5, 1).cabeEn(new Rectangulo(2, 6))` | true |
| `new Rectangulo(3, 4).cabeEn(new Rectangulo(3, 3))` | false |
| `new Rectangulo(5, 1).cabeEn(new Rectangulo(4, 4))` | false |
| `new Rectangulo(2, 6).cabeEn(new Rectangulo(5, 1))` | false |
| `new Rectangulo(3, 4).elMayor(new Rectangulo(2, 7)).toString` | `2x7` |
| `new Rectangulo(3, 4).elMayor(new Rectangulo(2, 6)).toString` | `3x4` |
| `new Rectangulo(2, 6).elMayor(new Rectangulo(3, 4)).toString` | `2x6` |

`5x1` entra en `2x6` solo rotado, y `2x6` no entra en `5x1` de ninguna
forma: `cabeEn` no es simétrica. Si el caso rotado se resuelve llamando a
`cabeEn` sobre el rectángulo rotado, conviene revisar que la llamada
termine: rotar dos veces devuelve el original. Los dos últimos casos de
`elMayor` tienen la misma área, 12, y cada uno devuelve su receptor.

### Punto 2: `Fecha`, precondiciones y métodos privados

```scala
class Fecha(val dia: Int, val mes: Int, val anio: Int) {
  private def esBisiesto: Boolean
  private def diasDelMes(m: Int): Int
  def diaDelAnio: Int
  def siguiente: Fecha
  def masDias(n: Int): Fecha
  def antesDe(otra: Fecha): Boolean
  override def toString: String
}
```

Los tres parámetros van con `val`, así que `dia`, `mes` y `anio` son
selectoras sin escribir nada más. Lo que la clase garantiza es que toda
`Fecha` existe: el constructor lleva dos `require`, uno para el mes, de 1 a
12, y otro para el día, de 1 al último día de ese mes en ese año.
`new Fecha(30, 2, 2024)` lanza `IllegalArgumentException`. Los `require`
van al comienzo del cuerpo, antes de cualquier `val`, porque se evalúan al
construir el objeto.

Para eso hacen falta dos métodos privados. `esBisiesto` dice si el año lo
es: divisible por 4, salvo los divisibles por 100 que no lo sean por 400;
2024 y 2000 son bisiestos, 2023 y 1900 no. `diasDelMes(m)` devuelve cuántos
días tiene el mes `m` de este año. Son privados porque son detalle de la
clase: quien usa una fecha no los necesita.

`diaDelAnio` es la posición dentro del año, con el 1 de enero en 1;
`siguiente` es la fecha del día después, cambiando de mes y de año cuando
toca; `masDias(n)` avanza `n` días y con `n = 0` devuelve la misma fecha,
`this`; `antesDe(otra)` dice si esta fecha es anterior a `otra`. `toString`
escribe `dia/mes/anio` sin ceros a la izquierda: `29/2/2024`.

| Llamada | Resultado |
|---|---|
| `new Fecha(29, 2, 2024).toString` | `29/2/2024` |
| `new Fecha(1, 12, 1999).toString` | `1/12/1999` |
| `new Fecha(1, 13, 2024)` | `IllegalArgumentException` |
| `new Fecha(31, 4, 2024)` | `IllegalArgumentException` |
| `new Fecha(29, 2, 2023)` | `IllegalArgumentException` |
| `new Fecha(29, 2, 1900)` | `IllegalArgumentException` |
| `new Fecha(29, 2, 2000).toString` | `29/2/2000` |
| `new Fecha(1, 1, 2024).diaDelAnio` | 1 |
| `new Fecha(1, 3, 2023).diaDelAnio` | 60 |
| `new Fecha(1, 3, 2024).diaDelAnio` | 61 |
| `new Fecha(15, 8, 2026).diaDelAnio` | 227 |
| `new Fecha(31, 12, 2024).diaDelAnio` | 366 |
| `new Fecha(28, 2, 2024).siguiente.toString` | `29/2/2024` |
| `new Fecha(28, 2, 1900).siguiente.toString` | `1/3/1900` |
| `new Fecha(31, 12, 2023).siguiente.toString` | `1/1/2024` |
| `new Fecha(25, 12, 2023).masDias(10).toString` | `4/1/2024` |
| `new Fecha(1, 1, 2024).masDias(366).toString` | `1/1/2025` |
| `new Fecha(15, 8, 2026).masDias(0).toString` | `15/8/2026` |
| `new Fecha(5, 3, 2024).antesDe(new Fecha(1, 4, 2024))` | true |
| `new Fecha(1, 4, 2024).antesDe(new Fecha(5, 3, 2024))` | false |
| `new Fecha(5, 3, 2024).antesDe(new Fecha(5, 3, 2024))` | false |
| `new Fecha(31, 12, 2023).antesDe(new Fecha(1, 1, 2024))` | true |

El 5 de marzo es anterior al 1 de abril aunque 5 sea mayor que 1:
`antesDe` compara primero el año y después la posición dentro del año, no
el día suelto. Y 1900 separa la regla completa de los bisiestos de la regla
corta: con solo el múltiplo de 4, `new Fecha(29, 2, 1900)` se construiría.

### Punto 3: `Racional`, un invariante que el constructor sostiene

```scala
class Racional(x: Int, y: Int) {
  private def mcd(a: Int, b: Int): Int
  def numer: Int
  def denom: Int
  def +(r: Racional): Racional
  def -(r: Racional): Racional
  def *(r: Racional): Racional
  def /(r: Racional): Racional
  def ==(r: Racional): Boolean
  def <(r: Racional): Boolean
  def max(r: Racional): Racional
  override def toString: String
}
```

La clase `Racional` de la sesión exige `y > 0`. Esta acepta cualquier
denominador distinto de cero, `require(y != 0, ...)`, y se encarga de dejar
el número en una sola forma, la **normalizada**: `numer` y `denom` sin
factores comunes, el signo en el numerador y el denominador positivo.
`new Racional(6, -4)` es `-3/2`, `new Racional(-3, -4)` es `3/4` y el cero
es siempre `0/1`, venga de donde venga. Como todo racional nace
normalizado, las operaciones no simplifican nada: construyen con la fórmula
y el constructor hace el resto.

`mcd` es privado, igual que el valor del máximo común divisor que se
calcula una sola vez al construir el objeto. `==` compara valores, no
representaciones: `1/2 == 2/4`. `<` compara por productos cruzados,
`numer * r.denom < denom * r.numer`, y eso vale porque los dos
denominadores son positivos; con uno negativo la desigualdad cambiaría de
sentido, y ese es un motivo del invariante. `max` devuelve el mayor de los
dos, `this` o `r`. `toString` escribe `n/d`, o solo `n` cuando el
denominador es 1.

El modelo de sustitución sigue valiendo con clases. Con
`r1 = new Racional(1, 2)` y `r2 = new Racional(2, 3)`:

| Paso | Expresión | Qué se hizo |
|---|---|---|
| 1 | `r1 max r2` | notación infija de `r1.max(r2)` |
| 2 | `if (r1 < r2) r2 else r1` | cuerpo de `max`, con `r2` en lugar de `r` y `r1` en lugar de `this` |
| 3 | `if (1 * 3 < 2 * 2) r2 else r1` | cuerpo de `<`, con las selectoras evaluadas |
| 4 | `if (true) r2 else r1` | aritmética |
| 5 | `r2`, que es `2/3` | resultado |

| Llamada | Resultado |
|---|---|
| `new Racional(6, 4).toString` | `3/2` |
| `new Racional(10, 15).toString` | `2/3` |
| `new Racional(3, -4).numer` | -3 |
| `new Racional(3, -4).denom` | 4 |
| `new Racional(-3, -4).toString` | `3/4` |
| `new Racional(6, -4).toString` | `-3/2` |
| `new Racional(-6, 4).toString` | `-3/2` |
| `new Racional(4, 2).toString` | `2` |
| `new Racional(-6, 3).toString` | `-2` |
| `new Racional(0, -5).toString` | `0` |
| `new Racional(0, -5).denom` | 1 |
| `new Racional(1, 0)` | `IllegalArgumentException` |
| `(new Racional(1, 2) + new Racional(2, 3)).toString` | `7/6` |
| `(new Racional(1, 2) - new Racional(2, 3)).toString` | `-1/6` |
| `(new Racional(1, 2) * new Racional(2, 3)).toString` | `1/3` |
| `(new Racional(1, 2) / new Racional(2, 3)).toString` | `3/4` |
| `(new Racional(1, -2) + new Racional(1, 2)).toString` | `0` |
| `(new Racional(3, -4) * new Racional(-4, 3)).toString` | `1` |
| `(new Racional(1, 3) / new Racional(-2, 3)).toString` | `-1/2` |
| `new Racional(1, 2) / new Racional(0, 1)` | `IllegalArgumentException` |
| `(r1 * r1 + r2 * r2).toString` | `25/36` |
| `(r1 + r2 * new Racional(3, 4)).toString` | `1` |
| `new Racional(1, 2) == new Racional(2, 4)` | true |
| `new Racional(-1, 2) == new Racional(1, -2)` | true |
| `new Racional(1, 3) < new Racional(1, 2)` | true |
| `new Racional(-1, 2) < new Racional(1, -3)` | true |
| `(new Racional(1, 2) max new Racional(1, 3)).toString` | `1/2` |
| `(new Racional(-1, 2) max new Racional(-1, 3)).toString` | `-1/3` |
| `(r1 max r2).toString` | `2/3` |

Dividir por cero no necesita un `require` propio: `1/2` entre `0/1`
construye `new Racional(1 * 1, 2 * 0)` y la precondición del constructor la
detiene. `-1/2 < 1/-3` es `-1/2 < -1/3`, verdadero; sin normalizar el
signo, los productos cruzados dirían lo contrario. Y `r1 + r2 * 3/4` es
`1/2 + 1/2`, no `7/6 * 3/4`: la precedencia viene con el símbolo.

### Punto 4: `Complejos`, operadores sobre reales

```scala
class Complejos(val r: Double, val i: Double) {
  def +(otro: Complejos): Complejos
  def -(otro: Complejos): Complejos
  def *(otro: Complejos): Complejos
  def /(otro: Complejos): Complejos
  override def toString: String
}
```

Un número complejo tiene la forma `a + bi`, donde `a` y `b` son reales e `i`
cumple que `i` al cuadrado es -1. `r` es la parte real e `i` la imaginaria.
Con `q1 = a1 + b1·i` y `q2 = a2 + b2·i`:

| Operación | Parte real | Parte imaginaria |
|---|---|---|
| `q1 + q2` | a1 + a2 | b1 + b2 |
| `q1 - q2` | a1 - a2 | b1 - b2 |
| `q1 * q2` | a1·a2 - b1·b2 | a1·b2 + a2·b1 |
| `q1 / q2` | (a1·a2 + b1·b2) / (a2² + b2²) | (a2·b1 - a1·b2) / (a2² + b2²) |

`toString` devuelve el número en la forma `a + bi`, con un detalle: cuando
la parte imaginaria es negativa no se escribe `+ -3.0i` sino `- 3.0i`. Las
dos partes van redondeadas a tres decimales:

```scala
Math.round(a * 1000.0) / 1000.0
```

Los casos usan `q1 = 1.0 + 2.0i`, `q2 = 4.0 + 3.0i` y `q3 = 3.0 + 6.0i`:

| Llamada | Resultado |
|---|---|
| `q1.toString` | `1.0 + 2.0i` |
| `(q1 + q2).toString` | `5.0 + 5.0i` |
| `(q2 + q3).toString` | `7.0 + 9.0i` |
| `(q1 - q2).toString` | `-3.0 - 1.0i` |
| `(q2 - q3).toString` | `1.0 - 3.0i` |
| `(q1 * q2).toString` | `-2.0 + 11.0i` |
| `(q2 * q3).toString` | `-6.0 + 33.0i` |
| `(q1 / q2).toString` | `0.4 + 0.2i` |
| `(q2 / q3).toString` | `0.667 - 0.333i` |
| `(q1 / q1).toString` | `1.0 + 0.0i` |
| `(q1 - q1).toString` | `0.0 + 0.0i` |
| `(new Complejos(2.0, 0.0) / new Complejos(3.0, 0.0)).toString` | `0.667 + 0.0i` |
| `(new Complejos(1.0, 1.0) / new Complejos(8.0, 0.0)).toString` | `0.125 + 0.125i` |
| `(new Complejos(0.0, 1.0) * new Complejos(0.0, 1.0)).toString` | `-1.0 + 0.0i` |
| `(q1 + q2 * q3).toString` | `-5.0 + 35.0i` |
| `((q1 + q2) * q3).toString` | `-15.0 + 45.0i` |

`q2 / q3` es el caso que obliga al redondeo: sin él la parte real sería
`0.6666666666666666`. `i` por `i` da `-1.0 + 0.0i`, que es la definición de
`i`. Y los dos últimos casos muestran que `*` se evalúa antes que `+`
también entre complejos.

## Cómo está organizado el proyecto

```
app/src/main/scala/taller/
    App.scala           programa de arranque
    Rectangulo.scala    punto 1
    Fecha.scala         punto 2
    Racional.scala      punto 3
    Complejos.scala     punto 4

app/src/test/scala/taller/
    AppSuite.scala        comprueba que el entorno quedó bien
    RectanguloTest.scala  los casos del punto 1
    FechaTest.scala       los casos del punto 2
    RacionalTest.scala    los casos del punto 3
    ComplejosTest.scala   los casos del punto 4
```

Su código va en `main`. Las pruebas viven aparte y no se tocan.

## Cómo se ejecuta

```bash
./gradlew test    # corre las pruebas
```

Las pruebas arrancan en rojo y el trabajo es ponerlas en verde. El informe
completo queda en `app/build/reports/tests/test/index.html`.

## Cómo se trabaja

1. Haga fork de este repositorio.
2. En su fork, abra la pestaña **Actions** y habilítelas. GitHub las deja
   desactivadas en las copias hasta que el dueño lo confirme.
3. Clone, resuelva, haga commit y suba a `main`.
4. Verifique en **Actions** que la última ejecución quedó en verde.

## Restricciones

Este curso trabaja sin estado mutable: nada de `var`, `while`, `return` ni
variables que cambien. El resultado correcto por el camino equivocado no
cuenta como resultado correcto.
