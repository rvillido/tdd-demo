package ee.nortal.tdd.signature;

import ee.nortal.tdd.dao.MobileUser;
import ee.nortal.tdd.dao.MobileUserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SignatureCreator {

  @Resource
  private MobileUserDao mobileUserDao;

  public MobileSignature createSignature(SignatureRequest signatureRequest) throws UserNotFoundException {
    MobileUser user = mobileUserDao.findUser(signatureRequest.getPersonIdCode());
    MobileSignature signature = new MobileSignature();
    signature.setUserFullName(user.getFullName());
    signature.setSignature("48656C6C6F20576F726C64");
    return signature;
  }
}
