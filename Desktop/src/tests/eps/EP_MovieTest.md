### Clase de Equivalencias (MovieTest)

| Unidad | Entrada | Ejemplo | Resultado Esperado |
|---|---|---|---|
| Movie(String id, String title, Room room) | Cadenas cualquiera; `room` pertenece a `Room` | id="M01", title="Pelikula Adibidea", room=COMEDY | Getters devuelven entradas; `tickets` por defecto 0; `toString`="(M01) Pelikula Adibidea" |
| Movie.setTickets(int t) | Asignación dentro de rango lógico | t=3 | `tickets` pasa a 3 |
| Movie.tickets (contexto de datos) | Restricción de negocio en horario | 0 <= t <= `MAX_TICKETS_PER_MOVIE` | Siempre respetado en datos generados |
