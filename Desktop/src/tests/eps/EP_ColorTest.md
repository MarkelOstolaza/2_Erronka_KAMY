### Clase de Equivalencias (ColorTest)

| Unidad | Entrada | Ejemplo | Resultado Esperado |
|---|---|---|---|
| Color.paint(color, content) | color=null, content!=null | null, "Kaixo" | Devuelve "Kaixo" |
| Color.paint(color, content) | color!=null, content=null | RED, null | Devuelve `null` |
| Color.paint(color, content) | color!=null, content!=null | RED, "Kaixo" | Devuelve `RED.unicode + "Kaixo" + RESET` |
| Color.paint(color, content) | color=null, content=null | null, null | Devuelve `null` |
