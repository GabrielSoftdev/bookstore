
import com.gsoftdev.ecommerce.presentation.protocols.Validator;
import com.gsoftdev.ecommerce.presentation.services.SignUpService;
import java.io.IOException;
import javax.servlet.ServletException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class SignUpServletTest {

    private SignUpService sut;
    @Spy
    @InjectMocks
    private Validator emailValidatorStub;
    private MockHttpServletRequest postRequest;
    private MockHttpServletRequest getRequest;
    private MockHttpServletResponse response;
    private final String mainServletUrl = "/" + SignUpService.class;

    public void makeSut() {
        // creating a stub for testing validation before a official production class
        class EmailValidatorStub implements Validator {

            public boolean isValid(String email) {
                return true;
            }
        }
        emailValidatorStub = new EmailValidatorStub();
        sut = new SignUpService(emailValidatorStub);
    }

    @Before
    public void init() {
        postRequest = new MockHttpServletRequest("POST", mainServletUrl);
        getRequest = new MockHttpServletRequest("GET", mainServletUrl);
        response = new MockHttpServletResponse();
        makeSut();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldRedirectToSignUpPageWhenGetRequested() throws ServletException, IOException {
        sut.handleRequest(getRequest, response);
        Assert.assertEquals("/SignUp", response.getRedirectedUrl());
        Assert.assertEquals(302, response.getStatus());
    }

    @Test
    public void shouldReturn400IfNoNameIsProvided() throws ServletException, IOException {
        postRequest.setParameter("email", "any_email@mail.com");
        postRequest.setParameter("password", "any_password");
        postRequest.setParameter("passwordConfirmation", "any_password");

        sut.handleRequest(postRequest, response);

        Assert.assertEquals("Should send 400 status code to client.", 400, response.getStatus());
        Assert.assertEquals("Should send error message \"no name provided\" to client.", "\"NAME\" parameter is missing", response.getErrorMessage());
    }

    @Test
    public void shouldReturn400IfNoEmailIsProvided() throws ServletException, IOException {
        postRequest.setParameter("name", "any_name");
        postRequest.setParameter("password", "any_password");
        postRequest.setParameter("passwordConfirmation", "any_password");

        sut.handleRequest(postRequest, response);

        Assert.assertEquals("Should send 400 status code to client.", 400, response.getStatus());
        Assert.assertEquals("Should send error message \"no email provided\" to client.", "\"EMAIL\" parameter is missing", response.getErrorMessage());
    }

    @Test
    public void shouldReturn400IfAnInvalidEmailIsProvided() throws ServletException, IOException {

        postRequest.setParameter("name", "any_name");
        postRequest.setParameter("email", "invalid_email@mail");
        postRequest.setParameter("password", "any_password");
        postRequest.setParameter("passwordConfirmation", "any_password");

        Mockito.when(emailValidatorStub.isValid(postRequest.getParameter("email"))).thenReturn(true);
        sut.handleRequest(postRequest, response);

        Assert.assertEquals("Should send 400 status code to client.", 400, response.getStatus());
        Assert.assertEquals("Should send error message to client.", "the \"EMAIL\" parameter provided is invalid", response.getErrorMessage());
    }
}
