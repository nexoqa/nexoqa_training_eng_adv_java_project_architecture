# java-project-architecture
Project in Java with Ports Adapters architecture

# Ejercicio
El servicio TO DO TASK tiene los siguientes enpoints definidos:

- GET /todo
- GET /todo/<int:todo_id>
- POST /todo {title, description}
- PUT /todo/<int:todo_id> {title, description}
- GET /todo/<int:todo_id>/check
- GET todo/<int:todo_id>/uncheck

## Reglas:

- Cuando se crea una tarea por medio del servicio, por defecto la tarea esta en estado 'uncheck'
- Cuando se crea o actualiza una tarea el endpoint nos devuelve un objecto con la siguiente estructura
```JSON
{
    "id": 1,
    "title": "tests",
    "description": "tests",
    "is_done": false
}
```
- Cuando se actualiza una tarea, solo se puede modificar el titulo y la descripción

## Pruebas a implementar:

- Validar que la actualización de una tarea sea realice satisfactoriamente
- Validar que 2 tareas pueden tener el mismo titulo y/o descripción
- Marcar todas las tareas preexistentes como terminadas y validar que se hayan actualizado correctamente
- Marcar todas las tareas preexistentes como no terminadas y validar que se hayan actualizado correctamente





