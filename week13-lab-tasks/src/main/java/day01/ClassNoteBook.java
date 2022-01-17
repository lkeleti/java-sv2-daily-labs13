package day01;

import java.util.*;

public class ClassNoteBook {
    private Map<Student, List<Integer>> students = new TreeMap<>();

    public Map<Student, List<Integer>> getStudents() {
        return new TreeMap<>(students);
    }

    public void addStudent(Student student) {
        List<Integer> marks = new ArrayList<>();
        students.put(student, marks);
    }

    public void addMark(String id, int mark) {
        for (Student student: students.keySet()) {
            if (student.getId().equals(id)) {
                List<Integer> marks = students.get(student);
                marks.add(mark);
                students.put(student, marks);
            }
        }
    }
}
