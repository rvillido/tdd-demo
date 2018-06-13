package ee.nortal.tdd.dao;

import ee.nortal.tdd.signature.UserNotFoundException;

public interface MobileUserDao {

  MobileUser findUser(String personIdCode) throws UserNotFoundException;

}
