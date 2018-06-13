package ee.nortal.tdd.signature;

public class SignatureCreator {

  public MobileSignature createSignature() {
    MobileSignature signature = new MobileSignature();
    signature.setUserFullName("John Matrix");
    signature.setSignature("48656C6C6F20576F726C64");
    return signature;
  }
}
