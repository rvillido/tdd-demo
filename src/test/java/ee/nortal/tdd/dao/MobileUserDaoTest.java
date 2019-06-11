package ee.nortal.tdd.dao;

import ee.nortal.tdd.TestDatabase;
import ee.nortal.tdd.signature.UserNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;

import static ee.nortal.tdd.TestDatabase.addUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MobileUserDaoTest {

  private final MobileUserDaoImpl mobileUserDao = new MobileUserDaoImpl();

  private EmbeddedDatabase dataSource;

  @Before
  public void setUp() {
    dataSource = TestDatabase.setUp();
    mobileUserDao.setDataSource(dataSource);
  }

  @After
  public void tearDown() {
    TestDatabase.tearDown();
  }

  @Test(expected = UserNotFoundException.class)
  public void findUser_notFound_shouldThrowException() {
    mobileUserDao.findUser("301020304050732");
  }

  @Test
  public void findUser() {
    addUser("John Matrix", "301020304050601");
    MobileUser user = mobileUserDao.findUser("301020304050601");
    assertNotNull(user);
    assertEquals("John Matrix", user.getFullName());
  }

  @Test
  @Ignore
  public void findExistingUser() {
    MobileUser user = mobileUserDao.findUser("4020203040992582");
    assertNotNull(user);
    assertEquals("Natalie Jones", user.getFullName());
  }
}
