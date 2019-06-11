package ee.nortal.tdd.signature;

import ee.nortal.tdd.dao.MobileUser;
import ee.nortal.tdd.dao.MobileUserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Base64;

@Service
public class SignatureCreator {

  @Resource
  private MobileUserDao mobileUserDao;

  public MobileSignature createSignature(SignatureRequest request) throws UserNotFoundException {
    MobileUser user = mobileUserDao.findUser(request.getPersonIdCode());
    MobileSignature signature = new MobileSignature();
    signature.setUserFullName(user.getFullName());
    signature.setSignature(generateSignature(request));
    return signature;
  }

  private String generateSignature(SignatureRequest request) {
    String document = request.getDocument();
    return Base64.getEncoder().encodeToString(document.getBytes());
  }
}
