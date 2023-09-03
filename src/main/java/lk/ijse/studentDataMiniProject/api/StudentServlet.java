package lk.ijse.studentDataMiniProject.api;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.studentDataMiniProject.dto.StudentDTO;
import lk.ijse.studentDataMiniProject.model.StudentModel;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 7/15/2023
 * Time : 1:12 PM
 */

@WebServlet(urlPatterns = "/student")
public class StudentServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        try {
            InitialContext initialContext = new InitialContext();
//            java:comp/env/ + Resource name
            DataSource pool = (DataSource) initialContext.lookup("java:comp/env/jdbc/student");
            StudentModel.connection = pool.getConnection();
        } catch (NamingException | SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<StudentDTO> studentDTOS = StudentModel.getAllStudent();
        res.setContentType("Application/json");
        Jsonb jsonb = JsonbBuilder.create();
        jsonb.toJson(studentDTOS, res.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if(req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")){
            res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }else {
            Jsonb jsonb = JsonbBuilder.create();
            StudentDTO studentDTO = jsonb.fromJson(req.getReader(), StudentDTO.class);

            String name = studentDTO.getName();
            String email = studentDTO.getEmail();
            String city = studentDTO.getCity();
            int level = studentDTO.getLevel();

//            if (name == null || !name.matches("[A-Za-z]+")) {
//                throw new RuntimeException("Invalid name");
//            } else if (email == null) {
//                throw new RuntimeException("Invalid email");
//            } else if (city == null || !city.matches("[A-Za-z]+")) {
//                throw new RuntimeException("Invalid city");
//            } else if (level <= 0) {
//                throw new RuntimeException("Invalid level");
//            }

            studentDTO = StudentModel.saveStudent(studentDTO);
            if (studentDTO != null){
                res.setStatus(HttpServletResponse.SC_CREATED);
                res.setContentType("application/json");
                jsonb.toJson(studentDTO, res.getWriter());
            }else {
                throw new RuntimeException("Fail to save student..!");
            }
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        res.addHeader("Access-Control-Allow-Origin", "*");
//        res.addHeader("Access-Control-Allow-Headers", "content-type");
//        res.addHeader("Access-Control-Allow-Methods", "DELETE,PUT");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if(req.getContentType() == null || ! req.getContentType().toLowerCase().startsWith("application/json")){
            res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }else {
            Jsonb jsonb = JsonbBuilder.create();
            StudentDTO studentDTO = jsonb.fromJson(req.getReader(), StudentDTO.class);

            int id = studentDTO.getStudentId();
            String name = studentDTO.getName();
            String email = studentDTO.getEmail();
            String city = studentDTO.getCity();
            int level = studentDTO.getLevel();

//            if (name == null || !name.matches("[A-Za-z]+")) {
//                throw new RuntimeException("Invalid name");
//            } else if (email == null) {
//                throw new RuntimeException("Invalid email");
//            } else if (city == null || !city.matches("[A-Za-z]+")) {
//                throw new RuntimeException("Invalid city");
//            } else if (level <= 0) {
//                throw new RuntimeException("Invalid level");
//            }

            boolean isUpdated = StudentModel.updateStudent(studentDTO);
            if (!isUpdated) {
                throw new RuntimeException("Fail to update Student..!");
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        } else {
            JsonReader reader = Json.createReader(req.getReader());
            JsonObject jsonObject = reader.readObject();
            int studentId = jsonObject.getInt("studentId");

            boolean isDeleted = StudentModel.deleteStudent(studentId);
            if (!isDeleted)
                throw new RuntimeException("Fail to delete Student..!");
        }
    }
}
