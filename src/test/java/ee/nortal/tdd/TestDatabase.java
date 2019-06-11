package ee.nortal.tdd;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class TestDatabase {

  private static EmbeddedDatabase dataSource;
  private static SimpleJdbcInsert userInsert;

  public static EmbeddedDatabase setUp() {
    dataSource = buildTestDatabase();
    userInsert = new SimpleJdbcInsert(dataSource).withTableName("MOBILE_USER");
    generateTestData();
    return dataSource;
  }

  public static void tearDown() {
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

  public static void addUser(String name, String idCode) {
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("PERSON_ID_CODE", idCode);
    params.addValue("FULL_NAME", name);
    userInsert.execute(params);
  }

  private static void generateTestData() {
    addUser("Natalie Jones", "4020203040992582");
    addUser("Ben Richards", "301020304023592");
    addUser("Mikaela Banes", "423020304023592");
    addUser("Julius Benedict", "301020304012053");
    addUser("Jack Slater", "3010203040954124");
    addUser("Harry Tasker", "3010203040992582");
  }
}
