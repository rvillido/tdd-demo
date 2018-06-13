package ee.nortal.tdd.dao;

import ee.nortal.tdd.signature.UserNotFoundException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MobileUserDaoTest {

  private final MobileUserDaoImpl mobileUserDao = new MobileUserDaoImpl();

  @Test(expected = UserNotFoundException.class)
  public void findUser_notFound_shouldThrowException() {
    mobileUserDao.findUser("301020304050732");
  }

  @Test
  public void findExistingUser() {
    MobileUser user = mobileUserDao.findUser("301020304050601");
    assertNotNull(user);
    assertEquals("John Matrix", user.getFullName());
  }
}
