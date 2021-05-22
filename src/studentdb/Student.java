package studentdb;

import java.util.Comparator;

public class Student implements Comparable<Student> {

    private String id;
    private String name;
    private double gpax;
    private String faculty;
    private int year;

    public Student(String id, String name, double gpax, String faculty, int year) {
        this.id = id;
        this.name = name;
        this.gpax = gpax;
        this.faculty = faculty;
        this.year = year;
    }

    public Student() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getGpax() {
        return gpax;
    }

    public String getFaculty() {
        return faculty;
    }

    public int getYear() {
        return year;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGpax(double gpax) {
        this.gpax = gpax;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    
    
    @Override
    public String toString() {
        return "Student{" + id + ", " + name + ", " + gpax + ", " + faculty + ", " + year + '}';
    }

    @Override
    public int compareTo(Student o) {
        return this.id.compareTo(o.id);
    }

    public Comparator compareName() {
        return new NameComparator();
    }

    public class NameComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o1.name.compareToIgnoreCase(o2.name);
        }
    }

    public static Comparator compareYear() {
        return new YearComparator();
    }

    public static class YearComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o1.year - o2.year;
        }
    }

    public static Comparator compareGpax() {
        class GpaxComparator implements Comparator<Student> {

            @Override
            public int compare(Student o1, Student o2) {
                return Double.compare(o1.gpax, o2.gpax);
            }
        }
        return new GpaxComparator();
    }

    public static Comparator compareFaculty() {
        return new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.faculty.compareToIgnoreCase(o2.faculty);
            }
        };
    }

//    public static Comparator compareFaculty() {
//        return (Comparator<Student>) (Student o1, Student o2) -> o1.faculty.compareToIgnoreCase(o2.faculty);
//    }
    public static Comparator compareAll() {
        return new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (Student.compareFaculty().compare(o1, o2) != 0) {
                    return Student.compareFaculty().compare(o1, o2);
                } else if (Student.compareYear().compare(o1, o2) != 0) {
                    return Student.compareYear().compare(o1, o2);
                } else if (Student.compareGpax().compare(o1, o2) != 0) {
                    return Student.compareGpax().compare(o1, o2);
                } else if (new Student().compareName().compare(o1, o2) != 0) {
                    return new Student().compareName().compare(o1, o2);
                } else {
                    return o1.compareTo(o2);
                }
            }
        };
    }
}
