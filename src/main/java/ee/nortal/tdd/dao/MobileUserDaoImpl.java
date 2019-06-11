package ee.nortal.tdd.dao;

import ee.nortal.tdd.signature.UserNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Repository
public class MobileUserDaoImpl implements MobileUserDao {

  private NamedParameterJdbcOperations jdbcTemplate;

  @Resource
  public void setDataSource(DataSource dataSource) {
    this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }

  @Override
  public MobileUser findUser(String personIdCode) throws UserNotFoundException {
    try {
      String query = "Select * from MOBILE_USER where PERSON_ID_CODE = :personIdCode and FULL_NAME like '%John%'";
      MapSqlParameterSource params = new MapSqlParameterSource();
      params.addValue("personIdCode", personIdCode);
      return jdbcTemplate.queryForObject(query, params, new BeanPropertyRowMapper<>(MobileUser.class));
    } catch (DataAccessException e) {
      throw new UserNotFoundException();
    }

  }
}
