import java.sql.*;
import java.util.UUID;

public class StudentDatabase {

    // Connect to PostgreSQL database
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/student_db",
                    "admin",
                    "admin"
            );
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }
        return conn;
    }

    // Insert student data into the database
    public void insertStudent(String name, int age, String grade) {
        // Generate UUID for the id
        UUID studentId = UUID.randomUUID();

        // Construct the SQL query with the UUID and other parameters directly
        String SQL = "INSERT INTO students(id, name, age, grade) VALUES('"
                + studentId.toString() + "', '"
                + name + "', "
                + age + ", '"
                + grade + "')";

        try (Connection conn = connect();  // Establish connection
             Statement stmt = conn.createStatement()) {

            // Execute the insert query
            stmt.executeUpdate(SQL);  // Execute the query
            System.out.println("Student added successfully with ID: " + studentId);

        } catch (SQLException e) {
            System.out.println("Error inserting data.");
            e.printStackTrace();
        }
    }

    // Get all student records from the database
    public void getAllStudents() {
        String SQL = "SELECT * FROM students";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getString("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Grade: " + rs.getString("grade"));
                System.out.println();
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving data.");
            e.printStackTrace();
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        StudentDatabase db = new StudentDatabase();

        // Insert student data
        db.insertStudent("John Doe", 20, "A");

        // Retrieve all students
        db.getAllStudents();
    }
}