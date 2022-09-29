
import com.gsoftdev.ecommerce.presentation.services.SignUpServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class SignUpServletTest {

    private final HttpServlet mainServlet = new SignUpServlet();
    private final String mainServletUrl = "/" + SignUpServlet.class;
    private MockHttpServletRequest postRequest;
    private MockHttpServletRequest getRequest;
    private MockHttpServletResponse response;

    @Before
    public void setUpTest() {
        postRequest = new MockHttpServletRequest("POST", mainServletUrl);
        getRequest = new MockHttpServletRequest("GET", mainServletUrl);
        response = new MockHttpServletResponse();
    }

    @Test
    public void shouldReturn400IfNoNameIsProvided() throws ServletException, IOException {
        postRequest.setParameter("email", "any_email@mail.com");
        postRequest.setParameter("password", "any_password");
        postRequest.setParameter("passwordConfirmation", "any_password");

        mainServlet.service(postRequest, response);

        Assert.assertEquals("Should send 400 status code to client.", 400, response.getStatus());
        Assert.assertEquals("Should send error message \"no name provided\" to client.", "\"NAME\" parameter is missing", response.getErrorMessage());
    }

    @Test
    public void shouldReturn400IfNoEmailIsProvided() throws ServletException, IOException {
        postRequest.setParameter("name", "any_name");
        postRequest.setParameter("password", "any_password");
        postRequest.setParameter("passwordConfirmation", "any_password");

        mainServlet.service(postRequest, response);

        Assert.assertEquals("Should send 400 status code to client.", 400, response.getStatus());
        Assert.assertEquals("Should send error message \"no email provided\" to client.", "\"EMAIL\" parameter is missing", response.getErrorMessage());
    }
}
