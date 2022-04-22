import adapters.todoListSQLAdapter;
import logger.LogUtils;
import model.taskModel;
import model.taskResponseModel;
import useCases.createTasks;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class setAllTaskAsDoneTests {

    public static todoListSQLAdapter todoListAdapter = new todoListSQLAdapter();
    public List<taskModel> tasks;

    @BeforeAll
    public static void setup() throws SQLException {

        createTasks taskCreatorUserCase = new createTasks();
        List<taskModel> tasks = taskCreatorUserCase.createSeveralTasks(5);
    }


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
    public void setAllTasksAsDone() throws SQLException {


        taskResponseModel[] tasks  = given().
                when().
                get("http://localhost:5001/todo").
                as(taskResponseModel[].class);

        for(taskResponseModel task: tasks){
            given().when().get("http://localhost:5001/todo/" + task.getId() +"/check");
        } 

        ArrayList<taskResponseModel> updatedTasks = todoListAdapter.getAllTasksWithIsDone();

        for (taskResponseModel task: updatedTasks){
            Assertions.assertEquals(task.getIsDone(), true);
        } 

    }
}