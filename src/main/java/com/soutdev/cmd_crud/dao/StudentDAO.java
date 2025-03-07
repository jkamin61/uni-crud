package com.soutdev.cmd_crud.dao;

import com.soutdev.cmd_crud.entity.Student;

import java.util.List;

public interface StudentDAO {
    void save(Student student);

    Student findById(Integer id);

    List<Student> fetchStudents();

    Student fetchByLastName(String lastName);

    void update(Student student);

    void delete(Student student);

    int deleteAll();
}
