package ee.nortal.tdd.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("signature")
public class SignatureController {

  @RequestMapping(name = "create", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
  public String createSignature() {
    return "{\n" +
            "  \"userFullName\": \"John Matrix\",\n" +
            "  \"signature\": \"48656C6C6F20576F726C64\"\n" +
            "}";
  }


}
