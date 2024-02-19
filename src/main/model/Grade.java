package model;

import java.util.List;

public class Grade {

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public static String percentageToLetterGrade(double percentage) {
        if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 85) {
            return "A";
        } else if (percentage >= 80) {
            return "A-";
        } else if (percentage >= 75) {
            return "B+";
        } else if (percentage >= 70) {
            return "B";
        } else if (percentage >= 65) {
            return "C+";
        } else if (percentage >= 60) {
            return "C";
        } else if (percentage >= 55) {
            return "D";
        } else if (percentage >= 50) {
            return "E";
        } else {
            return "F";
        }
    }

    public static double letterGradeToGradePoints(String letterGrade) {
        switch (letterGrade) {
            case "A+": return 4.0;
            case "A": return 3.7;
            case "A-": return 3.3;
            case "B+": return 3.0;
            case "B": return 2.7;
            case "C+": return 2.3;
            case "C": return 2.0;
            case "D+": return 1.7;
            case "D": return 1.3;
            case "E": return 1.0;
            case "F": return 0.0;
            default: return 0.0;
        }
    }

    public static double calculateGPA(List<Course> courses) {
        double totalPoints = 0.0;
        int totalCredits = 0;
        for (Course course : courses) {
            double gradePoints = course.percentageToGradePoints(course.getStudentGrades());
            int credits = course.getCredits();
            totalPoints += gradePoints * credits;
            totalCredits += credits;

            if (totalCredits > 0) {
                return totalPoints / totalCredits;
            }
        }
        return 0.0;
    }

//    public static double calculateGPA(List<Course> courses) {
//        double totalPoints = 0.0;
//        int totalCredits = 0;
//        for (Course course : courses) {
//            double gradePoints = course.percentageToGradePoints(course.getStudentGrades());
//            int credits = course.getCredits();
//            totalPoints += gradePoints * credits;
//            totalCredits += credits;
//        }
//        // Return the calculated GPA after processing all courses
//        return totalCredits > 0 ? totalPoints / totalCredits : 0.0;
//    }


//
//    public static double applyGradeWeighting(double grade, int credits) {
//        // This method can be adjusted based on how your institution applies weighting
//        // Here's a simple example that just multiplies the grade by the credits
//        return (grade / 100) * credits;
//    }

}
