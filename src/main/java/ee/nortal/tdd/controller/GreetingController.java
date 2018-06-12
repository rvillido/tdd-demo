package ee.nortal.tdd.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

  @RequestMapping(name = "/")
  public String greeting() {
    return "Hello World";
  }

}
