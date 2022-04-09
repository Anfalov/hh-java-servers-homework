package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import counter.Counter;

@WebServlet(urlPatterns = "/counter")
public class CounterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print(Counter.getCounter());
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print("Counter value increased to " + Counter.increaseAndGetCounter());
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int subtractionValue;
        try{
            subtractionValue = Integer.parseInt(req.getHeader("Subtraction-Value"));
        }
        catch (NumberFormatException e)
        {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        resp.getWriter().print("Counter value decreased to " +
                Counter.decreaseAndGetCounterByValue(subtractionValue));
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
