import adapters.todoListSQLAdapter;
import io.restassured.http.ContentType;
import model.createTaskRequestModel;
import model.taskModel;
import model.taskResponseModel;
import logger.LogUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class createTaskTests {

    public static todoListSQLAdapter todoListAdapter = new todoListSQLAdapter();

    @BeforeAll
    @AfterAll
    public static void clean() throws SQLException {

        todoListAdapter.deleteAllTasks();
        try {
            LogUtils.setup();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Problems with creating the log files");
        }
    }

    @Test
    public void createTask() throws SQLException{
        String description = "Test Description";
        String title = "Test title";
        createTaskRequestModel task = new createTaskRequestModel(description, title);

        taskResponseModel obtainedTask = given().
                contentType(ContentType.JSON).
                body(task).
                log().body().
                when().
                post("http://localhost:5001/todo").
                as(taskResponseModel.class);

        Assertions.assertEquals(obtainedTask.getTitle(), task.getTitle());
        Assertions.assertEquals(obtainedTask.getDescription(), task.getDescription());

        taskModel taskPersistance = todoListAdapter.getTask(obtainedTask.getId());

        Assertions.assertEquals(taskPersistance.getTitle(), task.getTitle());
        Assertions.assertEquals(taskPersistance.getDescription(), task.getDescription());
    }

}
