/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.gsoftdev.ecommerce.presentation.services;

import com.gsoftdev.ecommerce.presentation.errors.MissingParamError;
import com.gsoftdev.ecommerce.presentation.protocols.Controller;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet(
        name = "SignUpServlet",
        description = "Client sign up service",
        urlPatterns = {"/SignUp"}
)
public class SignUpService extends Controller {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getMethod() == "GET") {
            doGet(request, response);
        }

        if (request.getMethod() == "POST") {
            doPost(request, response);
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
