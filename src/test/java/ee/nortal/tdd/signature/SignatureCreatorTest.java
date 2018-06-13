package ee.nortal.tdd.signature;

import ee.nortal.tdd.dao.MobileUser;
import ee.nortal.tdd.dao.MobileUserDao;
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
    MobileUser user = new MobileUser();
    user.setFullName("John Matrix");
    when(mobileUserDao.findUser("301020304050601")).thenReturn(user);
    SignatureRequest request = new SignatureRequest();
    request.setPersonIdCode("301020304050601");
    MobileSignature signature = signatureCreator.createSignature(request);
    assertNotNull(signature);
    assertEquals("John Matrix", signature.getUserFullName());
    assertEquals("48656C6C6F20576F726C64", signature.getSignature());
  }

  @Test(expected = UserNotFoundException.class)
  public void createSignature_userNotFound_shouldThrowException() {
    when(mobileUserDao.findUser("301020304050732")).thenThrow(new UserNotFoundException());
    SignatureRequest request = new SignatureRequest();
    request.setPersonIdCode("301020304050732");
    request.setDocument("SGVsbG8gV29ybGQ=");
    signatureCreator.createSignature(request);
  }
}
