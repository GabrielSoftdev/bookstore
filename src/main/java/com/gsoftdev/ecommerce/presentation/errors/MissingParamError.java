/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gsoftdev.ecommerce.presentation.errors;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
public class MissingParamError {

    private String param;

    private MissingParamError() {
        //
    }

    public static void sendToClient(HttpServletResponse response, String parameter) throws IOException {
        response.sendError(400, String.format("\"%s\" parameter is missing", parameter.toUpperCase()));
    }
}
