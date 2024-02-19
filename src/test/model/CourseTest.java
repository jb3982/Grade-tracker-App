package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.*;

class CourseTest {
    private Course course;
    private Student student1;
    private Student student2;
    private Student student3;
    private Student student4;
    private Student student5;
    private Student student6;
    private Student student7;
    private Student student8;
    private Student student9;
    private Student student10;

    @BeforeEach
    public void setUp() {
        course = new Course("JAVA", "CS210", "Intro to Java", 210_01, 4,
                0.0);
        student1 = new Student("Jack", 1);
        student2 = new Student("Jones", 2);
        student3 = new Student("Jim", 3);
        student4 = new Student("Jerry", 4);
        student5 = new Student("Tom", 5);
        student6 = new Student("Chris", 6);
        student7 = new Student("Peter", 7);
        student8 = new Student("Ron", 8);
        student9 = new Student("Vin", 9);
        student10 = new Student("Shin", 10);

    }

    @Test
    public void testEnrollAndRemoveStudent() {
        course.enrollStudent(student1);
        assertTrue(course.getEnrolledStudentsID().contains(student1.getStudentID()));
        course.removeStudent(student1);
        assertNull(course.getEnrolledStudentsID());
    }

    @Test
    public void testAddAndUpdateGrade() {
        course.enrollStudent(student1);
        course.addGrade(student1, 90.0);

        double addedGrade = course.getGrade(student1);
        assertEquals(90.0, addedGrade, 0.01);

        course.updateGrade(student1, 95.0);
        double updatedGrade = course.getGrade(student1);
        assertEquals(95.0, updatedGrade, 0.01);
    }

    @Test
    public void testGetGrade_ValidGrade() {
        course.enrollStudent(student1);
        course.enrollStudent(student2);
        course.addGrade(student1, 90.0);

        assertEquals(90.0, course.getGrade(student1));
    }

    @Test
    public void testGetGrade_NoGradeAssigned() {
        Double grade = course.getGrade(student1);
        assertNull(grade);
    }

    @Test
    public void testRemoveGrade() {
            course.enrollStudent(student1);
            course.addGrade(student1, 85.0);

            assertEquals(85.0, course.getGrade(student1));


            course.removeGrade(student1);
            assertEquals(0.0, course.getGrade(student1));

    }

    @Test
    public void testCalculateAverageGrade() {
        course.enrollStudent(student1);
        course.addGrade(student1, 90.0);
        course.enrollStudent(student2);
        course.addGrade(student2, 80.0);
        double average = course.calculateAverageGrade();

        assertEquals(85.0, average);
    }

    @Test
    public void testIsCourseActive() {
        LocalDate startDate = LocalDate.now().minusDays(10);
        LocalDate endDate = LocalDate.now().plusDays(10);
        course.setGetStartDate(startDate);
        course.setGetEndDate(endDate);
        assertTrue(course.isCourseActive(LocalDate.now()));
    }

    @Test
    public void testCalculateMedianGrade() {
        course.enrollStudent(student1);
        course.enrollStudent(student2);
        course.enrollStudent(student3);
        course.enrollStudent(student4);
        course.addGrade(student1, 90);
        course.addGrade(student2, 80);
        course.addGrade(student3, 70);
        course.addGrade(student4, 60);

        assertEquals(70.5, course.calculateMedianGrade());

        course.enrollStudent(student5);
        course.addGrade(student5, 100);

        assertEquals(70, course.calculateMedianGrade());

    }

    @Test
    public void testCalculateStandardDeviation() {
        course.enrollStudent(student1);
        course.enrollStudent(student2);
        course.enrollStudent(student3);
        course.enrollStudent(student4);
        course.enrollStudent(student5);
        course.addGrade(student1, 90);
        course.addGrade(student2, 80);
        course.addGrade(student3, 70);
        course.addGrade(student4, 60);
        course.addGrade(student5, 100);

        assertEquals(sqrt(250), course.calculateStandardDeviation());

    }

    @Test
    public void testCalculateGradeDistribution() {
        course.enrollStudent(student1);
        course.enrollStudent(student2);
        course.enrollStudent(student3);
        course.enrollStudent(student4);
        course.enrollStudent(student5);
        course.enrollStudent(student6);
        course.enrollStudent(student7);
        course.enrollStudent(student8);
        course.enrollStudent(student9);
        course.enrollStudent(student10);
        course.addGrade(student1, 95);
        course.addGrade(student2, 88);
        course.addGrade(student3, 82);
        course.addGrade(student4, 78);
        course.addGrade(student5, 73);
        course.addGrade(student6, 67);
        course.addGrade(student7, 65);
        course.addGrade(student8, 59);
        course.addGrade(student9, 55);
        course.addGrade(student10, 45);

        List<String> expectedLetterGrades = Arrays.asList("A+", "A", "A-", "B+", "B", "C+", "C+", "D", "D", "F");
        List<String> actualLetterGrades = course.calculateGradeDistribution(course.getStudentGrades());

        assertEquals(expectedLetterGrades, actualLetterGrades);
    }

    @Test
    public void testPercentageToGradePoints() {
        course.enrollStudent(student1);
        course.enrollStudent(student2);
        course.enrollStudent(student3);
        course.enrollStudent(student4);
        course.enrollStudent(student5);
        course.addGrade(student1, 95);
        course.addGrade(student2, 85);
        course.addGrade(student3, 75);
        course.addGrade(student4, 65);
        course.addGrade(student5, 55);


        double expectedAverage = (4.0 + 3.7 +  3.0 + 2.3 +  1.3)/5;

        assertEquals(expectedAverage, course.percentageToGradePoints(course.getStudentGrades()));
    }

}