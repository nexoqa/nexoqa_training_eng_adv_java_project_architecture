package useCases;

import adapters.todoListSQLAdapter;
import model.taskModel;
import org.apache.commons.lang3.RandomStringUtils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class createTasks {

    public todoListSQLAdapter todoListAdapter = new todoListSQLAdapter();
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public  List<taskModel> createSeveralTasks(int numTasks) throws SQLException {

        List<taskModel> tasks = new ArrayList<taskModel>();
        LOGGER.info("Creating " + numTasks + " tasks");
        for (int i = 0; i < numTasks; ++i) {
            String randomtitle = RandomStringUtils.randomAlphanumeric(8).toUpperCase();
            String randomDescription = RandomStringUtils.randomAlphanumeric(15).toUpperCase();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            taskModel task = new taskModel(randomtitle, randomDescription);
            tasks.add(task);
            todoListAdapter.createTask(randomtitle, randomDescription, timestamp);
        }
        LOGGER.info(tasks.size() + " tasks created");
        return tasks;
    }
}
