package io.catalyte.training.sportsproducts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
    value = HttpStatus.NOT_FOUND,
    reason = "No elements in the system match that query.")
public class Status404 extends RuntimeException {

}
