import adapters.todoListSQLAdapter;
import interfaces.basicResponses;
import io.restassured.http.ContentType;
import logger.LogUtils;
import model.createTaskRequestModel;
import model.taskModel;
import model.taskResponseModel;
import useCases.createTasks;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class updateTaskTests {
    static int numTasks;
    static List<taskModel> taskList;

    @BeforeAll
    public static void setUp() throws SQLException {
        numTasks = 5;
        createTasks tasks = new createTasks();
        taskList = tasks.createSeveralTasks(numTasks);
    }

    @AfterAll
    public static void clean() throws SQLException {

        todoListSQLAdapter todo = new todoListSQLAdapter();
        todo.deleteAllTasks();
        try {
            LogUtils.setup();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Problems with creating the log files");
        }
    }


    @Test
    public void updateTask() throws SQLException {

        taskModel task = taskList.get(0);
        todoListSQLAdapter todo = new todoListSQLAdapter();
        taskResponseModel taskOrigin = todo.getTaskByTitle(task.getTittle());

        // update object 
        taskResponseModel taskUpdated = new taskResponseModel();
        taskUpdated.setDescription("new Desc");
        taskUpdated.setTittle(taskOrigin.getTittle());
        taskUpdated.setIsDone(taskOrigin.getIsDone());
        taskUpdated.setId(taskOrigin.getId());

        createTaskRequestModel reqTask = new createTaskRequestModel(taskUpdated.getDescription(), taskUpdated.getTittle());

        taskResponseModel obtainedTask = given().
                contentType(ContentType.JSON).
                body(reqTask).
                log().body().
                when().
                put("http://localhost:5001/todo/"+taskUpdated.getId()).
                as(taskResponseModel.class);

        // evaluation
        Assertions.assertEquals(taskUpdated.getDescription(), obtainedTask.getDescription());
        Assertions.assertEquals(taskOrigin.getTittle(), obtainedTask.getTittle());
    }

}
