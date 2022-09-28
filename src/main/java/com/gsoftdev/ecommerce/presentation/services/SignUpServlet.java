/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.gsoftdev.ecommerce.presentation.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Map;
import javax.el.MethodInfo;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

/**
 *
 * @author User
 */
public class SignUpServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        Map<String, String[]> parameterMapping = req.getParameterMap();
        
        if(parameterMapping.containsKey("name")) {
            String name = req.getParameter("name");
        }
        
        resp.sendError(400, "no name provided");
        
//        resp.sendRedirect("/invalidAccount");
    }
    
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Content-Type", "text/plain");
        PrintWriter writer = response.getWriter();
        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements()) {
            String parameter = String.valueOf(e.nextElement());
            String[] values = request.getParameterValues(parameter);
            for (int i = 0; i < values.length; i++) {
                writer.write(parameter + "=" + values[i]);
                writer.write("\n");
            }
        }
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        writeSelectMessage(req.getParameter("color"), resp.getWriter());
        setColor(req, req.getParameter("color"));
    }

    void writeSelectMessage(String color, PrintWriter pw) throws IOException {
        pw.print("You selected " + color);
        pw.close();
    }

    void setColor(HttpServletRequest req, String color) throws ServletException {
        req.getSession().setAttribute("color", color);
    }

}
