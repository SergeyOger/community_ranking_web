package net.broscorp.community_app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Course already exist")
public class CourseAlreadyExistException extends RuntimeException {

}
