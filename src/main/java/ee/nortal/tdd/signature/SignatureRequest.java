package ee.nortal.tdd.signature;

public class SignatureRequest {

  private String personIdCode;
  private String document;

  public void setPersonIdCode(String personIdCode) {
    this.personIdCode = personIdCode;
  }

  public String getPersonIdCode() {
    return personIdCode;
  }

  public void setDocument(String document) {
    this.document = document;
  }

  public String getDocument() {
    return document;
  }
}
