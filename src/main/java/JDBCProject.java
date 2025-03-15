public class JDBCProject {

    public static void main(String[] args) {

        //Properties file path
        String propertiesfilepath = "src/main/resources/db.properties";

        DatabaseConnection dbConnection = new DatabaseConnection(propertiesfilepath);

        EmployeeOperations employeeOperations = new EmployeeOperations(dbConnection);

        try {
            //Insert new employee
            System.out.println("\nInserting data...");
            employeeOperations.insertEmployee(5, "John Doe", 40);

            //Retrieve all employees
            System.out.println("\nAll Employees:");
            employeeOperations.getAllEmployees();

            //Delete an employee by ID
            System.out.println("\nDeleting employee with ID....");
            employeeOperations.deleteEmployee(5);

            //Retrieve again after all operations
            System.out.println("\nAll Employees after operations");
            employeeOperations.getAllEmployees();

        } catch (Exception e) {
            System.out.println("Error during operations: "+e.getMessage());
            e.printStackTrace();
        }
        finally {
            //Close the database Connection after operations
            dbConnection.closeConnection();
        }
    }
}
