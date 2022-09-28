/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import com.gsoftdev.ecommerce.presentation.services.SignUpServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 *
 * @author User
 */
public class SignUpServletTest {

    private static String servletName ;
    private static String servletUrl ;
    private static HttpServlet servlet;

    ;

    @BeforeClass
    public static void setUpBerforeClass() {
        servletName = servlet.getServletName();
        servletUrl = "http://localhost/" + servletName;
        servlet = new SignUpServlet();
    }

    @Test
    public void shouldReturn400IfNoNameIsProvided() throws ServletException, IOException {
        HttpServlet servlet = new SignUpServlet();
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/" + servletName);
        request.setParameter("name", "any_name");
        request.setParameter("email", "any_email@mail.com");
        request.setParameter("password", "any_password");
        request.setParameter("passwordConfirmation", "any_password");

        MockHttpServletResponse response = new MockHttpServletResponse();
        servlet.service(request, response);

        Assert.assertEquals(
                "Should send error message \"No Name provided\" to client.",
                "no name provided",
                response.getErrorMessage());
        Assert.assertEquals(
                "Should send 400 status code to client.",
                400,
                response.getStatus());
    }
}
