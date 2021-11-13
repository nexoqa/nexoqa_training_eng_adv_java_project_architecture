import adapters.todoListSQLAdapter;
import model.taskModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class main {
    public static void main(String args[]) throws SQLException {
        //Properties properties = new Properties();
        //properties.setProperty("user", "root");
        //properties.setProperty("password", "demo");
        //properties.setProperty("MaxPooledStatements", "250");
        //interfaces.SqlInterface sqlInstance = new interfaces.SqlInterface();
        //sqlInstance.setDataBaseURL("jdbc:mysql://192.168.0.15:3306/demo");
        //sqlInstance.setProperties(properties);
        //sqlInstance.setUser("root");
        //sqlInstance.setPassword("demo");
        //sqlInstance.setMaxPool("250");
        //String query = "SELECT * from todo_list";
        //ResultSet rs = sqlInstance.sendQuery(query);
        //while(rs.next()){
        //    System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
        //
        //}
        todoListSQLAdapter todoListAdapter = new todoListSQLAdapter();
        taskModel task = todoListAdapter.getTask("1");

        ArrayList<taskModel> taskList = todoListAdapter.getAllTasks();
        for (taskModel taskObject:taskList)
        {
            System.out.println(taskObject.getTittle()+"  "+taskObject.getDescription());
        }
        todoListAdapter.deleteAllTasks();

    }
}

//private static final String USERNAME = "root";
//private static final String PASSWORD = "demo";
//private static final String MAX_POOL = "250";