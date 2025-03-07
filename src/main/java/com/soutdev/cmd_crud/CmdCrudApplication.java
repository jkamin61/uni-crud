package com.soutdev.cmd_crud;

import com.soutdev.cmd_crud.dao.StudentDAO;
import com.soutdev.cmd_crud.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class CmdCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmdCrudApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        Scanner scan = new Scanner(System.in);
        String menu = """
                _________________________________
                |       Manage student data     |
                | ______________________________|
                | 1. Add student                |
                | 2. Update student information |
                | 3. Find student by ID         |
                | 4. Find student by last name  |
                | 5. Show all students          |
                | 6. Delete student info        |
                | 7. Delete all students info   |
                |_______________________________|
                |    Press 0 to exit program    |
                |_______________________________|
                """;
        return runner -> {
            while (true) {
                System.out.println(menu);
                int option = scan.nextInt();
                switch (option) {
                    case 0 -> {
                        System.out.println("Exiting program..");
                        System.exit(0);
                        return;
                    }
                    case 1 -> createStudent(studentDAO);
                    case 2 -> updateStudent(studentDAO);
                    case 3 -> findStudentById(studentDAO);
                    case 4 -> fetchStudentByLastName(studentDAO);
                    case 5 -> fetchStudents(studentDAO);
                    case 6 -> deleteStudent(studentDAO);
                    case 7 -> deleteAllStudents(studentDAO);
                    default -> {
                        System.out.println("Select appropriate option from menu.");
                        return;
                    }
                }
            }
        };
    }

    private void createStudent(StudentDAO studentDAO) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Provide information for student");
        System.out.println("First name: ");
        String firstName = scan.nextLine();
        System.out.println("Last name: ");
        String lastName = scan.nextLine();
        System.out.println("Email: ");
        String email = scan.nextLine();
        try {
            studentDAO.save(new Student(firstName, lastName, email));
            System.out.printf("Successfully added student %s", email);
        } catch (Exception e) {
            System.out.println("Error creating student");
            e.printStackTrace();
        }
    }

    private Student findStudentById(StudentDAO studentDAO) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Provide student's ID");
        int studentId = scan.nextInt();
        Student studentFoundById = studentDAO.findById(studentId);
        if (studentFoundById == null) {
            System.out.println("No student found of given ID");
            return null;
        }
        System.out.println(studentFoundById);
        return studentFoundById;
    }

    private List<Student> fetchStudents(StudentDAO studentDAO) {
        try {
            System.out.println("Fetching all students..");
            List<Student> listOfStudents = studentDAO.fetchStudents();
            if (listOfStudents == null) {
                System.out.println("Error while fetching students");
                return null;
            }
            System.out.println("Success fetching students\n");
            System.out.printf("%-5s | %-15s | %-15s | %-25s%n", "ID", "First Name", "Last Name", "Email");
            System.out.println("--------------------------------------------------------------");

            for (var student : listOfStudents) {
                System.out.printf("%-5d | %-15s | %-15s | %-25s%n",
                        student.getId(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getEmail());
            }
            return listOfStudents;
        } catch (Exception e) {
            System.out.println("Error fetching students");
            e.printStackTrace();
        }
        System.out.println("No students available");
        return null;
    }


    private Student fetchStudentByLastName(StudentDAO studentDAO) {
        Scanner scan = new Scanner(System.in);
        try {
            System.out.println("Enter student's last name:");
            String lastName = scan.nextLine();
            Student student = studentDAO.fetchByLastName(lastName);
            System.out.println(student);
            return student;
        } catch (Exception e) {
            System.out.println("Error fetching student");
            e.printStackTrace();
        }
        return null;
    }


    private void updateStudent(StudentDAO studentDAO) {
        String options = """
                __________________________________
                |       Update student data      |
                | ______________________________ |
                | 1. Change first name           |
                | 2. Change last name            |
                | 3. Change email                |
                |_______________________________ |
                |Press 0 to exit updating student|
                |________________________________|
                """;
        Scanner scan = new Scanner(System.in);
        try {
            Student student = fetchStudentByLastName(studentDAO);
            if (student == null) {
                return;
            }
            while (true) {
                System.out.println(options);
                int option = scan.nextInt();
                scan.nextLine();
                switch (option) {
                    case 0 -> {
                        return;
                    }
                    case 1 -> {
                        System.out.println("Enter new student's first name:");
                        String newFirstName = scan.nextLine();
                        student.setFirstName(newFirstName);
                    }
                    case 2 -> {
                        System.out.println("Enter new student's last name:");
                        String newLastName = scan.nextLine();
                        student.setLastName(newLastName);
                    }
                    case 3 -> {
                        System.out.println("Enter new student's email:");
                        String newEmail = scan.nextLine();
                        student.setLastName(newEmail);
                    }
                    default -> {
                        System.out.println("Select appropriate option from menu.");
                        return;
                    }
                }
                studentDAO.update(student);
            }

        } catch (Exception e) {
            System.out.println("Error fetching student");
            e.printStackTrace();
        }
    }

    private void deleteStudent(StudentDAO studentDAO) {
        Scanner scan = new Scanner(System.in);
        try {
            System.out.println("Provide student's ID to be deleted");
            int studentId = scan.nextInt();
            Student studentToBeDeleted = studentDAO.findById(studentId);
            if (studentToBeDeleted == null) {
                System.out.println("Student does not exist");
                return;
            }
            studentDAO.delete(studentToBeDeleted);
            System.out.printf("Student %d deleted successfully", studentId);
        } catch (Exception e) {
            System.out.println("Error fetching student");
            e.printStackTrace();
        }
    }

    private void deleteAllStudents(StudentDAO studentDAO) {
        Scanner scan = new Scanner(System.in);
        try {
            System.out.println("Are you sure you want to delete all students? If so type \"yes\"");
            String confirmation = scan.nextLine();
            String loweredConfirmation = confirmation.toLowerCase();
            if (loweredConfirmation.equals("yes")) {
                studentDAO.deleteAll();
                System.out.println("All students have been deleted from database");
            }
        } catch (Exception e) {
            System.out.println("Error fetching student");
            e.printStackTrace();
        }
    }
}
