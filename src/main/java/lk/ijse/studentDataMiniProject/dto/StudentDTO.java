package lk.ijse.studentDataMiniProject.dto;

import java.io.Serializable;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 7/15/2023
 * Time : 1:17 PM
 */

public class StudentDTO implements Serializable {
    private int studentId;
    private String name;
    private String email;
    private String city;
    private int level;

    public StudentDTO() {
    }

    public StudentDTO(int studentId, String name, String email, String city, int level) {
        this.studentId = studentId;
        this.name = name;
        this.city = city;
        this.email = email;
        this.level = level;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", level=" + level +
                '}';
    }
}
