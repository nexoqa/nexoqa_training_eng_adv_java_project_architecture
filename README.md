# java-project-architecture
Project in Java with Ports Adapters architecture

# Excercise
The service TO DO TASK has the following endpoints defined:

- GET /todo
- GET /todo/<int:todo_id>
- POST /todo {title, description}
- PUT /todo/<int:todo_id> {title, description}
- GET /todo/<int:todo_id>/check
- GET todo/<int:todo_id>/uncheck

## Rules:

- When a task is created, the task is mark as 'uncheck' by default
- When a task is created or updated the endpoints response a object with the following structure
```JSON
{
    "id": 1,
    "title": "tests",
    "description": "tests",
    "is_done": false
}
```
- When a task is updated, you can only change the title and description

## Tests to implement:

- Validate the update of a task is carried out satisfactorily
- Validate two tasks can have the same title and/or description
- Mark all pre-existing tasks as finished and validate that they have been updated correctly
- Mark all pre-existing tasks as not finished and validate that they have been updated correctly





