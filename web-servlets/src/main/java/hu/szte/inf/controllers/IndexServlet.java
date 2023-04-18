package hu.szte.inf.controllers;

import hu.szte.inf.repositories.HibernateCourseRepository;
import hu.szte.inf.repositories.IHibernateCourseRepository;
import hu.szte.inf.utils.ViewResolver;
import hu.szte.inf.utils.db.DbInitializer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/", "/index"})
public class IndexServlet extends HttpServlet {

    @Override
    public void init() {
        ViewResolver.setPrefix("/WEB-INF/jsp/");
        ViewResolver.setPostfix(".jsp");
        IHibernateCourseRepository repository = new HibernateCourseRepository();
        repository.saveAll(DbInitializer.getDefaultCourses(20));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ViewResolver.resolve("index")).forward(req, resp);
    }
}
