package com.example.usermongodb.exception;

import com.example.usermongodb.models.User;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class ExceptionHandlerTest {

    // create an instance of GlobalExceptionHandler
    GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();
    // mocking errors of validation
    private Map<String, String> expectedErreur = new HashMap<>();

    private ResourceNotFoundException exceptions ;

    // create bindingResult to insert errors in fields
    private BindingResult bindingResult = new BeanPropertyBindingResult(User.class , "user");
    // create MethodArgumentNotValidException to use binding result
    private MethodArgumentNotValidException methodArgumentNotValidException ;

    /**
     * initialise expected values
     * insert error in binding Result
     */
    @BeforeEach
    public void setUp() {
        // set expected errors of validation
        expectedErreur.put("country" , "user must be from france");
        expectedErreur.put("age" , "user must have an age bigger than 18");

        // create object BindingResulat and push errors in it
        bindingResult.addError(new FieldError("user", "country", "user must be from france"));
        bindingResult.addError(new FieldError("user", "age", "user must have an age bigger than 18"));

        exceptions = new ResourceNotFoundException("not found user");

        // inject bindingResult in methodArgumentNotValidException
        methodArgumentNotValidException = new MethodArgumentNotValidException(null , bindingResult);
    }

    /**
     * testing resourceNotFound Exception
     * when there is no resource saved for the passed id
     */
    @Test
    @DisplayName(" test resourceNotFoundException success")
    void resourceNotFoundExceptionTest(){
        ResponseEntity<?> response = exceptionHandler.resourceNotFoundException(exceptions, null);
        Assertions.assertEquals(response.getStatusCode() , HttpStatus.NOT_FOUND);
        Assertions.assertEquals(((ErrorMessage) response.getBody()).getMessage() , "not found user");
    }

    /**
     * testing handleMethodArgumentNotValid Exception
     * when the user insert invalid value in the fields
     */
    @Test
    @DisplayName(" test handleMethodArgumentNotValid success")
    void handleMethodArgumentNotValidTest(){

        ResponseEntity<?> response = exceptionHandler.handleMethodArgumentNotValid(methodArgumentNotValidException, null , null , null);

        Assertions.assertEquals(response.getStatusCode() , HttpStatus.BAD_REQUEST);
        Assertions.assertEquals(response.getBody(), expectedErreur);
    }
}
