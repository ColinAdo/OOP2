import java.sql.*;

public class JdbcCrudOperation {
    public static void main(String[] args) {
        try {
            // Load the JDBC driver
            Class.forName("org.postgresql.Driver");

            // Establish the database connection
            String url = "jdbc:postgresql://localhost:5432/database_name";
            String username = "your_username";
            String password = "your_password";
            Connection connection = DriverManager.getConnection(url, username, password);

            // Get database metadata
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println("Connected to: " + metaData.getDatabaseProductName() + " " + metaData.getDatabaseProductVersion());

            // Insert student
            String insertQuery = "INSERT INTO students (name, student_number) VALUES (?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, "Mercy");
            insertStatement.setString(2, "BSCLMRT29292");
            insertStatement.executeUpdate();
            System.out.println("Student inserted successfully.");

//             Update student
            String updateQuery = "UPDATE students SET name = ? WHERE student_number = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, "Coin");
            updateStatement.setString(2, "12345");
            updateStatement.executeUpdate();
            System.out.println("Student updated successfully.");

            // Delete student
            String deleteQuery = "DELETE FROM students WHERE student_number = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.setString(1, "12345");
            deleteStatement.executeUpdate();
            System.out.println("Student deleted successfully.");

            // Display students
            String selectQuery = "SELECT name, student_number FROM students";
            Statement selectStatement = connection.createStatement();
            ResultSet resultSet = selectStatement.executeQuery(selectQuery);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String registrationNumber = resultSet.getString("student_number");
                System.out.println("Name: " + name + ", Registration Number: " + registrationNumber);
            }

            // Close the database connection
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}