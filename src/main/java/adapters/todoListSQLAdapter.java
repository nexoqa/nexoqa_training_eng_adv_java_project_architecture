package adapters;

import interfaces.SqlInterface;
import model.taskModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Logger;

public class todoListSQLAdapter extends SqlInterface {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final String USERNAME = "root";
    private static final String PASSWORD = "demo";
    private static final String MAX_POOL = "250";
    private static final String DATABASEURL = "jdbc:sqlite:/home/nexoqa/repos/nexoqa_training_eng_adv_to_do_task/example.db";
    taskModel task = null;

    public todoListSQLAdapter() {

        Properties properties = new Properties();
        properties.setProperty("user", USERNAME);
        properties.setProperty("password", PASSWORD);
        properties.setProperty("MaxPooledStatements", MAX_POOL);
        super.setProperties(properties);
        super.setDataBaseURL(DATABASEURL);
    }

    public taskModel getTask(String id) throws SQLException {
        String getTaskQuery = "SELECT * from todo_list WHERE ID = " + id;
        ResultSet rs = super.sendQuery(getTaskQuery);
        while (rs.next()){
            this.task = new taskModel(rs.getString(2), rs.getString(3));
        }
        return this.task;
    }

    public ArrayList<taskModel> getAllTasks() throws SQLException{
        ArrayList<taskModel> taskList = new ArrayList<taskModel>();
        String getTaskQuery = "SELECT * from todo_list";
        ResultSet rs = super.sendQuery(getTaskQuery);
        while (rs.next()){
            task = new taskModel(rs.getString(2), rs.getString(3));
            taskList.add(task);
        }
        return taskList;
    }

    public void deleteAllTasks() throws SQLException{
        LOGGER.info("Deleting all tasks ");
        String deleteAllTasksQuery = "DELETE FROM todo_list;";
        super.sendUpdate(deleteAllTasksQuery);
    }

    public void createTask(String tittle, String description, Timestamp timestamp) throws SQLException{
        LOGGER.info("Creating tasks with title: " + tittle + " and description: " + description);

        String createTaskQuery = "INSERT INTO todo_list(title, description, created_at) VALUES (?, ?, ?)";

        PreparedStatement statement = super.connect().prepareStatement(createTaskQuery);
        statement.setString(1, tittle);
        statement.setString(2, description);
        statement.setTimestamp(3, timestamp);

        super.sendUpdateWithParameters(statement);
    }
}
