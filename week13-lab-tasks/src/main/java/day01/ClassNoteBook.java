package day01;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class ClassNoteBook {
    private TreeMap<Student, ArrayList<Integer>> students = new TreeMap<>();

    public void addStudent(Student student) {
        students.put(student, new ArrayList<>());
    }

    public void addMark(int id, int mark) {
        for (Student student: students.keySet()) {
            if (student.getId().equals(id)) {
                ArrayList<Integer> marks = students.get(student);
                marks.add(mark);
                students.put(student, marks);
            }
        }
    }
}
