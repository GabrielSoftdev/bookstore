/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import com.gsoftdev.ecommerce.presentation.services.SignUpServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 *
 * @author User
 */
public class SignUpServletTest {

    @Test
    public void shouldReturn400IfNoNameIsProvided() throws ServletException, IOException {
        HttpServlet servlet = new SignUpServlet();

        MockHttpServletRequest postRequest = new MockHttpServletRequest("POST", "/" + SignUpServlet.class);

        postRequest.setParameter("email", "any_email@mail.com");
        postRequest.setParameter("password", "any_password");
        postRequest.setParameter("passwordConfirmation", "any_password");

        MockHttpServletResponse response = new MockHttpServletResponse();
        servlet.service(postRequest, response);

        Assert.assertEquals("Should send 400 status code to client.", 400, response.getStatus());
        Assert.assertEquals("Should send error message \"no name provided\" to client.", "no name provided", response.getErrorMessage());
    }
    
    @Test
    public void shouldReturn400IfNoEmailIsProvided() throws ServletException, IOException {
        HttpServlet servlet = new SignUpServlet();

        MockHttpServletRequest postRequest = new MockHttpServletRequest("POST", "/" + SignUpServlet.class);

        postRequest.setParameter("name", "any_name");
        postRequest.setParameter("password", "any_password");
        postRequest.setParameter("passwordConfirmation", "any_password");

        MockHttpServletResponse response = new MockHttpServletResponse();
        servlet.service(postRequest, response);

        Assert.assertEquals("Should send 400 status code to client.", 400, response.getStatus());
        Assert.assertEquals("Should send error message \"no email provided\" to client.", "no email provided", response.getErrorMessage());
    }
}
