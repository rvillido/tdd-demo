package ee.nortal.tdd.dao;

import ee.nortal.tdd.signature.UserNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MobileUserDaoTest {

  private final MobileUserDaoImpl mobileUserDao = new MobileUserDaoImpl();

  private EmbeddedDatabase dataSource;
  private SimpleJdbcInsert userInsert;

  @Before
  public void setUp() {
    dataSource = buildTestDatabase();
    userInsert = new SimpleJdbcInsert(dataSource).withTableName("MOBILE_USER");
    generateTestData();
    mobileUserDao.setDataSource(dataSource);
  }

  @After
  public void tearDown() {
    dataSource.shutdown();
  }

  private static EmbeddedDatabase buildTestDatabase() {
    EmbeddedDatabase embeddedDatabase = new EmbeddedDatabaseBuilder()
            .generateUniqueName(true)
            .setType(EmbeddedDatabaseType.HSQL)
            .setScriptEncoding("UTF-8")
            .ignoreFailedDrops(true)
            .addScript("sql-scripts/user-schema.sql")
            .build();
    return embeddedDatabase;
  }

  public void addUser(String name, String idCode) {
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("PERSON_ID_CODE", idCode);
    params.addValue("FULL_NAME", name);
    userInsert.execute(params);
  }

  private void generateTestData() {
    addUser("Natalie Jones ", "4020203040992582");
    addUser("Ben Richards", "301020304023592");
    addUser("Mikaela Banes ", "423020304023592");
    addUser("Julius Benedict", "301020304012053");
    addUser("Jack Slater ", "3010203040954124");
    addUser("Harry Tasker", "3010203040992582");
  }

  @Test(expected = UserNotFoundException.class)
  public void findUser_notFound_shouldThrowException() {
    mobileUserDao.findUser("301020304050732");
  }

  @Test
  public void findExistingUser() {
    addUser("John Matrix", "301020304050601");
    MobileUser user = mobileUserDao.findUser("301020304050601");
    assertNotNull(user);
    assertEquals("John Matrix", user.getFullName());
  }
}
