package studentdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

public class DBStudent {

    public static void createTable() {
        try (Connection conn = DBConnection.getConnection();
                Statement stm = conn.createStatement()) {
            dropTable();
            try {
                stm.executeUpdate("CREATE TABLE Students (ST_ID VarChar(10) NOT NULL,Name VarChar(100),GPA DECIMAL(3,2),faculty varchar(100),years int,PRIMARY KEY(ST_ID))");
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public static void dropTable() {
        try (Connection conn = DBConnection.getConnection();
                Statement stm = conn.createStatement()) {
            stm.executeUpdate("DROP TABLE Students");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public static void insertStudent(Student st) {
        String sqlStudents = "INSERT INTO Students VALUES(?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stm = conn.prepareStatement(sqlStudents)) {
            stm.setString(1, st.getId());
            stm.setString(2, st.getName());
            stm.setDouble(3, st.getGpax());
            stm.setString(4, st.getFaculty());
            stm.setInt(5, st.getYear());
            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public static void updateStudent(Student st) {
        try (Connection conn = DBConnection.getConnection();
                Statement stm = conn.createStatement()) {
            stm.executeUpdate("UPDATE Students Set Name = '"+st.getName()+"' ,GPA = "+ st.getGpax()+", faculty = '"+st.getFaculty()+"', years = "+st.getYear()+" Where ST_ID = '" +st.getId()+"'");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

     public static void deleteStudent(Student st) {
        try (Connection conn = DBConnection.getConnection();
                Statement stm = conn.createStatement()) {
            stm.executeUpdate("DELETE FROM Students Where ST_ID = '" +st.getId()+"'");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public static Map<String, Student> selectAllStudent() {
        Map<String, Student> stmap = new TreeMap<>();
        try (Connection conn = DBConnection.getConnection();
                Statement stm = conn.createStatement()) {
            ResultSet rs = stm.executeQuery("SELECT * FROM Students");
            while (rs.next()) {
                Student st = new Student(rs.getString("ST_ID"),rs.getString("Name"),rs.getDouble("GPA"),rs.getString("faculty"),rs.getInt("years"));
                stmap.put(st.getId(), st);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return stmap;
    }
    
    public static Student selectStudentByID(String id) {
        Student st = null;
        try (Connection conn = DBConnection.getConnection();
                Statement stm = conn.createStatement()) {
            ResultSet rs = stm.executeQuery("SELECT * FROM Students Where ST_ID = '" +id+"'");
             if (rs.next()) {
                st =  new Student(rs.getString("ST_ID"),rs.getString("Name"),rs.getDouble("GPA"),rs.getString("faculty"),rs.getInt("years"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return st;
    }
}
