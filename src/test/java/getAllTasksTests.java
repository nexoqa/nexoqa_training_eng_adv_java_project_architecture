import adapters.todoListSQLAdapter;
import interfaces.basicResponses;
import logger.LogUtils;
import model.taskModel;
import model.taskResponseModel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import useCases.createTasks;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class getAllTasksTests {

    private final basicResponses toListResponsesObject = new basicResponses();
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
    public void getAllTasksBasicTest() {

        given().
                when().
                get("http://localhost:5001/todo").
                then().
                spec(toListResponsesObject.basicResponseSpecification());
    }

    @Test
    public void validateAllTasksFormat(){
        taskResponseModel[] taskResponse = given().
                when().
                get("http://localhost:5001/todo").
                as(taskResponseModel[].class);
        for(taskResponseModel task: taskResponse){
            Assertions.assertFalse(task.isNotNull());
        }
    }

    @Test
    public void getAllTasksWithNoTasksCreated () throws SQLException {

        todoListAdapter.deleteAllTasks();
        given().
                when().
                get("http://localhost:5001/todo").
                then().body("", equalTo(Collections.emptyList()));
    }

    @Test
    public void getAllTasks() throws SQLException{

        Integer numItems = 10;
        todoListAdapter.deleteAllTasks();
        createTasks taskCreatorUserCase = new createTasks();
        List<taskModel> tasks = taskCreatorUserCase.createSeveralTasks(numItems);
        given().
                when().
                get("http://localhost:5001/todo").
                then().body("", hasSize(numItems));
    }
}