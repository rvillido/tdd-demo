package ee.nortal.tdd.controller;

import ee.nortal.tdd.signature.MobileSignature;
import ee.nortal.tdd.signature.SignatureCreator;
import ee.nortal.tdd.signature.SignatureRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController("signature")
public class SignatureController {

  @Resource
  private SignatureCreator signatureCreator;

  @RequestMapping(name = "create", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
  public MobileSignature createSignature() {
    return signatureCreator.createSignature(new SignatureRequest());
  }


}
