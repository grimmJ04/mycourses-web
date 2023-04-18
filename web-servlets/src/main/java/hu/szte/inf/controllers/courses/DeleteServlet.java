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

@WebServlet(value = {"/course/delete", "/course/remove"})
public class DeleteServlet extends HttpServlet {

    private final IHibernateCourseRepository repository = new HibernateCourseRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        var model = repository.findById(id);
        if (model.isPresent()) {
            req.setAttribute("model", model.get());
            req.getRequestDispatcher(ViewResolver.resolve("course/delete")).forward(req, resp);
        }
        else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.sendRedirect("/course/read");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id = Long.parseLong(req.getParameter("id"));
        repository.deleteById(id);
        resp.sendRedirect("/course/read");
    }
}
