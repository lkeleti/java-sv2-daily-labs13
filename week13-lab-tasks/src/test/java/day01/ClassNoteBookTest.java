package day01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClassNoteBookTest {
    ClassNoteBook classNoteBook;
    Student student1;
    Student student2;

    @BeforeEach
    void setUp() {
        classNoteBook = new ClassNoteBook();
        student1 = new Student("0000002","John Doe");
        student2 = new Student("0000001","Jane Doe");
        classNoteBook.addStudent(student1);
        classNoteBook.addStudent(student2);
    }

    @Test
    void addStudentTest() {
        assertEquals(2, classNoteBook.getStudents().size());
        assertEquals(List.of(student2, student1), new ArrayList<>(classNoteBook.getStudents().keySet()));
    }

    @Test
    void addMarkByIdTest() {
        classNoteBook.addMark("0000001",5);
        classNoteBook.addMark("0000001",4);

        assertEquals(2, classNoteBook.getStudents().get(student2).size());
    }
}
