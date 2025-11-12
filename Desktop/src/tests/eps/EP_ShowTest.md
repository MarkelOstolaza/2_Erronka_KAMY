### Clase de Equivalencias (ShowTest)

| Unidad | Entrada | Ejemplo | Resultado Esperado |
|---|---|---|---|
| Show(String id, String time, Movie movie) | Campos válidos | id="S01", time="12:30", movie=Movie(...) | Getters devuelven los valores |
| Show.time (contexto de datos) | Formato horario HH:mm en programación | "00:00", "23:59" | Coincide con `^[0-2][0-9]:[0-5][0-9]$` |
