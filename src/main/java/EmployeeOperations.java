import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmployeeOperations {

    private Connection connection;

    EmployeeOperations(DatabaseConnection dbConnection) {

        //Getting the Connection object
        this.connection = dbConnection.getConnection();
    }
    //Method to insert employee
    public void insertEmployee(int id, String name, int age) {
        String query = "Insert into emp (id, name, age) Values (?, ?, ?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.setInt(3,age);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0){
                System.out.println("\nData Inserted Successfully");
            }else {
                System.out.println("Data insertion failed");
            }

        } catch (Exception e) {
            System.out.println("Error during inserting: "+e.getMessage());
            e.printStackTrace();
        }
    }
    //Method to retrieve all employees
    public void getAllEmployees() {
        String query = "Select * from emp";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");

                System.out.println("ID: "+id+ ", Name: " + name + ", Age: "+age);
            }

        } catch (Exception e) {
            System.out.println("Error during retrieving data "+e.getMessage());
            e.printStackTrace();
        }
    }
    //Method to delete data
    public void deleteEmployee(int id) {
        String query = "Delete from emp WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("\nData deleted successfully");
            }else {
                System.out.println("No employee found with given id");
            }

        }catch (Exception e) {
            System.out.println("Error while deleting "+e.getMessage());
            e.printStackTrace();
        }
    }
}
