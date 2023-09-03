package lk.ijse.studentDataMiniProject.model;

import lk.ijse.studentDataMiniProject.dto.StudentDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 7/22/2023
 * Time : 7:35 PM
 */

public class StudentModel {
    public static Connection connection;

    public static List<StudentDTO> getAllStudent() {
        List<StudentDTO> studentDTOS = new ArrayList<>();
        try {
            PreparedStatement pst = connection.prepareStatement("Select * from student");
            ResultSet rst = pst.executeQuery();
            while (rst.next()){
                studentDTOS.add(new StudentDTO(
                        rst.getInt(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getInt(5)
                ));
            }
            return studentDTOS;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static StudentDTO saveStudent(StudentDTO studentDTO) {
        try {
            PreparedStatement ptm = connection.prepareStatement("INSERT INTO student (name, email, city, level) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ptm.setString(1, studentDTO.getName());
            ptm.setString(2, studentDTO.getEmail());
            ptm.setString(3, studentDTO.getCity());
            ptm.setInt(4, studentDTO.getLevel());
            ptm.executeUpdate();
            ResultSet rst = ptm.getGeneratedKeys();
            if (rst.next()) {
                int studentId = rst.getInt(1);
                studentDTO.setStudentId(studentId);
                return studentDTO;
            }
            throw new SQLException();
        } catch (SQLException e) {
            return null;
        }
    }

    public static boolean updateStudent(StudentDTO studentDTO) {
        try {
            PreparedStatement ptm = connection.prepareStatement("UPDATE student SET name = ?, email = ?, city = ?, level = ? WHERE studentId=?");
            ptm.setString(1, studentDTO.getName());
            ptm.setString(2, studentDTO.getEmail());
            ptm.setString(3, studentDTO.getCity());
            ptm.setInt(4, studentDTO.getLevel());
            ptm.setInt(5, studentDTO.getStudentId());

            return ptm.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean deleteStudent(int studentId) {
        try {
            PreparedStatement ptm = connection.prepareStatement("DELETE FROM student WHERE studentId=?");
            ptm.setInt(1, studentId);

            return ptm.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }
}
