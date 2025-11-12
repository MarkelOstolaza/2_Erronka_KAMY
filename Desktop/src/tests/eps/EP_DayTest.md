### Clase de Equivalencias (DayTest)

| Unidad | Entrada | Ejemplo | Resultado Esperado |
|---|---|---|---|
| Day(String name, boolean open) | Nombre de día válido en `WEEKDAY_MAP`; `open` cualquiera | name="Astelehena", open=true/false | Se crea `Day`; getters devuelven los valores |
| Day(String name, boolean open) | Nombre inválido: `null` | name=null | Lanza `IllegalArgumentException` |
| Day(String name, boolean open) | Nombre inválido: no está en `WEEKDAY_MAP` | name="Foo", "" | Lanza `IllegalArgumentException` |
| Day.getDayNameFromNumber(int n) | En rango | n=1, n=5 | "Astelehena", "Ostirala" |
| Day.getDayNameFromNumber(int n) | Fuera de rango (bajo/alto) | n=0, n=8 | `null` |
