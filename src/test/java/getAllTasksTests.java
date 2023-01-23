import adapters.todoListSQLAdapter;
import interfaces.basicResponses;
import logger.LogUtils;
import model.taskModel;
import model.taskResponseModel;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import useCases.createTasks;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@FixMethodOrder(MethodSorters.JVM)
public class getAllTasksTests {

    private final basicResponses toListResponsesObject = new basicResponses();
    public static todoListSQLAdapter todoListAdapter = new todoListSQLAdapter();

    @BeforeAll
    @AfterAll
    @After
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
    public void getAllTasksBasicTest() throws SQLException {
        given().
                when().
                get("http://localhost:5001/todo").
                then().
                spec(toListResponsesObject.basicResponseSpecification());
    }

    @Test
    public void validateAllTasksFormat() throws SQLException{
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
        given().
                when().
                get("http://localhost:5001/todo").
                then().body("", equalTo(Collections.emptyList()));
    }

    @Test
    public void getAllTasks() throws SQLException{
        Integer numItems = 10;
        createTasks taskCreatorUserCase = new createTasks();
        List<taskModel> tasks = taskCreatorUserCase.createSeveralTasks(numItems);
        given().
                when().
                get("http://localhost:5001/todo").
                then().body("", hasSize(tasks.size()));
        todoListAdapter.deleteAllTasks();
    }
}