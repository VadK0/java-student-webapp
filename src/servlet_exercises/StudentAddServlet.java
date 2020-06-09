package servlet_exercises;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import data_access.StudentDAO;
import model.Student;

@WebServlet("/addStudent")
public class StudentAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		StudentDAO studentDAO = new StudentDAO();
		Student student = new Student();
		
		int id = Integer.parseInt(request.getParameter("studentId")); 
		student.setId(id);
		String lName = request.getParameter("lastName");
		student.setlName(lName);
		String fName = request.getParameter("firstName");
		student.setfName(fName);
		String address = request.getParameter("address");
		student.setAddress(address);
		String postCode = request.getParameter("postCode");
		student.setPostCode(postCode);
		String postOffice = request.getParameter("postOffice");
		student.setPostOffice(postOffice);
		
		int errorCode = studentDAO.insertStudent(student);
		
		JsonObject jsonObj = new JsonObject();
		jsonObj.addProperty("errorCode", errorCode);
		
		out.print(jsonObj);

	}

}
