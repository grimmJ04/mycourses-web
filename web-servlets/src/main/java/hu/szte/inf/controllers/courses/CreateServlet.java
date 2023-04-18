package hu.szte.inf.controllers.courses;

import hu.szte.inf.models.Course;
import hu.szte.inf.models.Grade;
import hu.szte.inf.models.Semester;
import hu.szte.inf.repositories.HibernateCourseRepository;
import hu.szte.inf.repositories.IHibernateCourseRepository;
import hu.szte.inf.utils.ViewResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value = {"/course/create", "/course/new"})
public class CreateServlet extends HttpServlet {

    private final IHibernateCourseRepository repository = new HibernateCourseRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ViewResolver.resolve("course/create")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        int credit = Integer.parseInt(req.getParameter("credit"));
        Semester semester = Semester.valueOf(req.getParameter("semester"));
        Grade grade = Grade.valueOf(req.getParameter("grade"));
        repository.save(new Course(name, credit, semester, grade));
        resp.sendRedirect("/course/read");
    }
}

