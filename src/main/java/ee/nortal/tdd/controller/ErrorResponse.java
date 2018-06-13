package ee.nortal.tdd.controller;

public class ErrorResponse {

  private String errorCode;

  public ErrorResponse(String errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

}
