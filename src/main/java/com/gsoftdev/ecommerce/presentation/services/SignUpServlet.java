/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.gsoftdev.ecommerce.presentation.services;

import com.gsoftdev.ecommerce.presentation.errors.MissingParamError;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Map;
import javax.el.MethodInfo;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

/**
 *
 * @author User
 */
@WebServlet(
        name = "SignUpServlet",
        description = "Client sign up service",
        urlPatterns = {"/SignUp"}
)
public class SignUpServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod() == "GET") {
            doGet(req, resp);
        }

        if (req.getMethod() == "POST") {
            doPost(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/SignUp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMapping = req.getParameterMap();

        if (!parameterMapping.containsKey("name")) {
            MissingParamError.sendToClient(resp, "name");
        }

        if (!parameterMapping.containsKey("email")) {
            MissingParamError.sendToClient(resp, "email");
        }

        String name = req.getParameter("name");
        String email = req.getParameter("email");
    }

}
