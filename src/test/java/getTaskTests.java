import adapters.todoListSQLAdapter;
import interfaces.basicResponses;
import logger.LogUtils;
import model.taskResponseModel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class getTaskTests {

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
    public void getTask() throws SQLException {

        String description = "Test Description1";
        String tittle = "Test Tittle";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        todoListAdapter.deleteAllTasks();
        todoListAdapter.createTask(tittle, description, timestamp);
        taskResponseModel[] taskResponse = given().
                when().
                get("http://localhost:5001/todo").
                as(taskResponseModel[].class);

        Assertions.assertEquals(taskResponse[0].getTittle(), tittle);
        Assertions.assertEquals(taskResponse[0].getDescription(), description);
    }
}