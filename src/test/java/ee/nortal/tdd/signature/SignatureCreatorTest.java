package ee.nortal.tdd.signature;

import ee.nortal.tdd.dao.MobileUser;
import ee.nortal.tdd.dao.MobileUserDao;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SignatureCreatorTest {

  @Mock
  private MobileUserDao mobileUserDao;

  @InjectMocks
  private final SignatureCreator signatureCreator = new SignatureCreator();

  @Test
  public void createSignature() {
    mockUser("John Matrix", "301020304050601");
    MobileSignature signature = createSignature("301020304050601", "Hello World Document!");
    assertValidMobileSignature(signature, "John Matrix", "SGVsbG8gV29ybGQgRG9jdW1lbnQh");
  }

  @Test(expected = UserNotFoundException.class)
  public void createSignature_userNotFound_shouldThrowException() {
    mockUserNotFound("301020304050732");
    createSignature("301020304050732", "SGVsbG8gV29ybGQ=");
  }

  @Test(expected = InvalidDocumentException.class)
  @Ignore
  public void createSignature_withoutDocument_shouldThrowException() {
    mockUser("John Matrix", "301020304050601");
    createSignature("301020304050601", null);
  }

  @Test(expected = InvalidDocumentException.class)
  @Ignore
  public void createSignature_withEmptyDocument_shouldThrowException() {
    mockUser("John Matrix", "301020304050601");
    createSignature("301020304050601", "");
  }

  @Test(expected = UserNotFoundException.class)
  @Ignore
  public void createSignature_withoutUser_shouldThrowException() {
    createSignature(null, "Hello world");
  }

  private MobileSignature createSignature(String personIdCode, String document) {
    SignatureRequest request = new SignatureRequest();
    request.setPersonIdCode(personIdCode);
    request.setDocument(document);
    return signatureCreator.createSignature(request);
  }

  private void mockUser(String fullName, String personIdCode) {
    MobileUser user = new MobileUser();
    user.setFullName(fullName);
    when(mobileUserDao.findUser(personIdCode)).thenReturn(user);
  }

  private void mockUserNotFound(String personIdCode) {
    when(mobileUserDao.findUser(personIdCode)).thenThrow(new UserNotFoundException());
  }

  private void assertValidMobileSignature(MobileSignature signature, String expectedFullName, String expectedDocument) {
    assertNotNull(signature);
    assertEquals(expectedFullName, signature.getUserFullName());
    assertEquals(expectedDocument, signature.getSignature());
  }
}
