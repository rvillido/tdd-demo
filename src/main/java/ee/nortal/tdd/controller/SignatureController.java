package ee.nortal.tdd.controller;

import ee.nortal.tdd.signature.MobileSignature;
import ee.nortal.tdd.signature.SignatureCreator;
import ee.nortal.tdd.signature.SignatureRequest;
import ee.nortal.tdd.signature.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController("signature")
public class SignatureController {

  @Resource
  private SignatureCreator signatureCreator;

  @RequestMapping(name = "create", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
  public MobileSignature createSignature() {
    return signatureCreator.createSignature(new SignatureRequest());
  }

  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponse handleUserNotFound() {
    return new ErrorResponse("USER_NOT_FOUND");
  }

}
