package ee.nortal.tdd.signature;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SignatureCreatorTest {

  private final SignatureCreator signatureCreator = new SignatureCreator();

  @Test
  public void createSignature() {
    MobileSignature signature = signatureCreator.createSignature();
    assertNotNull(signature);
    assertEquals("John Matrix", signature.getUserFullName());
    assertEquals("48656C6C6F20576F726C64", signature.getSignature());
  }
}
