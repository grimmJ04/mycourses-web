package hu.szte.inf.controllers.courses;

import hu.szte.inf.repositories.HibernateCourseRepository;
import hu.szte.inf.repositories.IHibernateCourseRepository;
import hu.szte.inf.utils.ViewResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/course/read", "/course/view"})
public class ReadServlet extends HttpServlet {

    private final IHibernateCourseRepository repository = new HibernateCourseRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("models", repository.findAll());
        req.getRequestDispatcher(ViewResolver.resolve("course/read")).forward(req, resp);
    }
}
