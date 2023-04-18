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

@WebServlet(value = {"/course/update", "/course/edit"})
public class UpdateServlet extends HttpServlet {

    private final IHibernateCourseRepository repository = new HibernateCourseRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        var model = repository.findById(id);
        if (model.isPresent()) {
            req.setAttribute("model", model.get());
            req.getRequestDispatcher(ViewResolver.resolve("course/update")).forward(req, resp);
        }
        else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.sendRedirect("/course/read");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        int credit = Integer.parseInt(req.getParameter("credit"));
        Semester semester = Semester.valueOf(req.getParameter("semester"));
        Grade grade = Grade.valueOf(req.getParameter("grade"));
        repository.save(new Course(id, name, credit, semester, grade));
        resp.sendRedirect("/course/read");
    }
}
