package main.java.com.muntian.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CalcServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("utf8");
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println(getHtmlForm());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf8");
        resp.setContentType("text/html");
        double a = Double.valueOf(req.getParameter("a"));
        String operation = req.getParameter("operation");
        double b = Double.valueOf(req.getParameter("b"));
        double result = calculate(a, b, operation);

        PrintWriter writer = resp.getWriter();
        writer.println(getFilledHtmlForm(a,b,operation,result));
    }

    double calculate(double a, double b, String operation) {
        switch (operation) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
        }
        return -1;
    }

    String getHtmlForm() {
        String form = "<form method='post' style='display:inline'>\n" +
                "<input name='a' type='number'/>\n" +
                "<select name='operation'>\n" +
                "<option>+</option>\n" +
                "<option>-</option>\n" +
                "<option>*</option>\n" +
                "<option>/</option>\n" +
                "</select>\n" +
                "<input name='b' type='number'/>\n" +
                "<button type='submit'>=</button>\n" +
                "</form>";
        return form;
    }

    String getFilledHtmlForm(double a, double b, String operation, double result){
        String form = "<form method='post' style='display:inline'>\n" +
                "<input name='a' value='" + a +"' type='number'/>\n" +
                "<select name='operation' value='" + operation +"'>\n" +
                "<option " + (operation.equals("+") ? "selected": "") + " >+</option>\n" +
                "<option " + (operation.equals("-") ? "selected": "") + ">-</option>\n" +
                "<option " + (operation.equals("*") ? "selected": "") + ">*</option>\n" +
                "<option " + (operation.equals("/") ? "selected": "") + ">/</option>\n" +
                "</select>\n" +
                "<input name='b' value='" + b +"' type='number'/>\n" +
                "<button type='submit'>=</button>\n" +
                "<input value='" + result + "'/>" +
                "</form>";
        return form;
    }
}
