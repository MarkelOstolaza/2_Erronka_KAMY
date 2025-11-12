### Clase de Equivalencias (DataTest)

| Unidad | Entrada | Ejemplo | Resultado Esperado |
|---|---|---|---|
| Población tras `Data.init` | Después de inicializar | — | `WEEKDAYS.size() == WEEKDAY_MAP.size()`; `MOVIES.size() == MOVIE_MAP.size()` |
| Tamaño de `SCHEDULE` por días abiertos | Mezcla de abiertos/cerrados (mapa por defecto) | — | `SCHEDULE.size() ==` número de días `OPEN` |
| Nº de sesiones por día | Día abierto | — | `MIN_SHOWS_PER_DAY <= shows <= MAX_SHOWS_PER_DAY` |
| Películas únicas por día | Día abierto | — | Todas las películas de ese día son únicas |
| Límites de entradas | Sesiones generadas | — | `0 <= tickets <= MAX_TICKETS_PER_MOVIE` |
| Formato de hora | Sesiones generadas | — | `time` coincide con `^[0-2][0-9]:[0-5][0-9]$` |
