package studentdb;

import java.util.Iterator;
import java.util.Map;

public class TestStudentDB {

    public static void main(String[] args) {
        DBStudent.createTable();

        DBStudent.insertStudent(new Student("1001", "aa", 4.00, "IT", 1));
        DBStudent.insertStudent(new Student("1007", "cc", 4.00, "IT", 1));
        DBStudent.insertStudent(new Student("1003", "ba", 3.50, "CS", 3));
        DBStudent.insertStudent(new Student("1005", "cd", 3.00, "IT", 3));
        DBStudent.insertStudent(new Student("1002", "ca", 2.00, "IT", 2));
        DBStudent.insertStudent(new Student("1004", "bb", 2.50, "CS", 2));
        DBStudent.insertStudent(new Student("1006", "bc", 4.00, "CS", 1));

        Map<String, Student> stmap = DBStudent.selectAllStudent();
        printMap(stmap);

        String idu = "1002";
        Student stu = stmap.get(idu);
        stu.setName("kb");
        DBStudent.updateStudent(stu);
        stmap.put(stu.getId(), DBStudent.selectStudentByID(stu.getId()));
        printMap(stmap);
        
        DBStudent.deleteStudent(stu);
        stmap = DBStudent.selectAllStudent();
        printMap(stmap);

//DBStudent.dropTable();
//printMap(stmap);
    }

    private static void printMap(Map stmap) {
        Iterator itr = stmap.values().iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
        System.out.println();
    }

}
