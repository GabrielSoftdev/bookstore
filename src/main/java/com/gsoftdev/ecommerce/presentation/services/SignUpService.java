/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.gsoftdev.ecommerce.presentation.services;

import com.gsoftdev.ecommerce.presentation.errors.InvalidParamError;
import com.gsoftdev.ecommerce.presentation.errors.MissingParamError;
import com.gsoftdev.ecommerce.presentation.protocols.Controller;
import com.gsoftdev.ecommerce.presentation.protocols.Validator;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet(
        name = "SignUpService",
        description = "Client sign up service",
        urlPatterns = {"/SignUp"}
)
public class SignUpService extends Controller {

    private final Validator emailValidator;

    public SignUpService(Validator emailValidator) {
        this.emailValidator = emailValidator;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if ("GET".equals(request.getMethod())) {
            doGet(request, response);
        }

        if ("POST".equals(request.getMethod())) {

            String[] requiredFields = {"name", "email", "password", "passwordConfirmation"};
            Map<String, String[]> parameterMapping = request.getParameterMap();

            for (String requiredField : requiredFields) {
                if (!parameterMapping.containsKey(requiredField)) {
                    MissingParamError.sendToClient(response, requiredField);
                    return; // to avoid IllegalStateException: Response already committed
                }
            }

            String email = request.getParameter("email");
            final boolean isValidEmail = emailValidator.isValid(email);
            if (! !isValidEmail) {
                InvalidParamError.sendToClient(response, "email");
                return; // to avoid IllegalStateException: Response already committed
            }

            doPost(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/SignUp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
    }

}
