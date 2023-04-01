package project.ute.sbjwt.service;

import java.util.Date;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.Payload;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.JWEDecryptionKeySelector;
import com.nimbusds.jose.proc.JWEKeySelector;
import com.nimbusds.jose.proc.SimpleSecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;

import project.ute.util.ConstantUtils;

@Service
public class JwtService {
	private String stripBearerToken(String token) {
		if (token != null)
			token = token.startsWith(ConstantUtils.BEARER_PREFIX) ? token.substring(ConstantUtils.BEARER_PREFIX.length()) : token;
		return token;
	}

	//JWE
//	Tạo Token
	public String generateTokenLogin(String username, String action) {
	    String token = null;
	    try {
	      // Trình tạo để xây dựng các bộ xác nhận quyền sở hữu JSON Web Token (JWT).
	      JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
	      if(action.equals("GEN")) {
	    	  builder.claim(ConstantUtils.USERNAME_GEN, username);
	      } else if(action.equals("REF")) {
	    	  builder.claim(ConstantUtils.USERNAME_REF, username);
	      }
	      builder.expirationTime(generateExpirationDate(action));
	      JWTClaimsSet claimsSet = builder.build();
	      JWEHeader header = new JWEHeader(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256);
	      DirectEncrypter encrypter = new DirectEncrypter(generateShareSecret());
	      Payload payload = new Payload(claimsSet.toJSONObject());
//	      System.out.println("Payload" + payload);
	      JWEObject jweObject = new JWEObject(header, payload);
	      jweObject.encrypt(encrypter);
	      token = jweObject.serialize();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return token;
	}
	
	//JWE
	private JWTClaimsSet getClaimsFromToken(String token) {
		ConfigurableJWTProcessor<SimpleSecurityContext> jwtProcessor = new DefaultJWTProcessor<SimpleSecurityContext>();
		JWKSource<SimpleSecurityContext> jweKeySource = new ImmutableSecret<SimpleSecurityContext>(generateShareSecret());
		JWEKeySelector<SimpleSecurityContext> jweKeySelector =
		    new JWEDecryptionKeySelector<SimpleSecurityContext>(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256, jweKeySource);
		jwtProcessor.setJWEKeySelector(jweKeySelector);
		token = stripBearerToken(token);
		JWTClaimsSet claims = null;
		try {
			claims = jwtProcessor.process(token, null);
//			System.out.println("Claims: " + claims);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return claims;
	}

	// Gen thời hạn cho SecretKey
	private Date generateExpirationDate(String action) {
		if(action.equals("REF")) {
			return new Date(System.currentTimeMillis() + ConstantUtils.EXPIRE_TIME_REF);
	      } 
		return new Date(System.currentTimeMillis() + ConstantUtils.EXPIRE_TIME_GEN);
	}

	// Từ token lấy ra Date
	private Date getExpirationDateFromToken(String token) {
		token = stripBearerToken(token);
		Date expiration = null;
		JWTClaimsSet claims = getClaimsFromToken(token);
		expiration = claims.getExpirationTime();
		return expiration;
	}

	// Từ token lấy ra Username
	public String getUsernameFromToken(String token, String action) {
		token = stripBearerToken(token);
		String username = null;
		try {
			JWTClaimsSet claims = getClaimsFromToken(token);
			if(action.equals("GEN")) {
				username = claims.getStringClaim(ConstantUtils.USERNAME_GEN);
			} else if(action.equals("REF")) {
				username = claims.getStringClaim(ConstantUtils.USERNAME_REF);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return username;
	}

	// Gen khóa secret thành 32byte
	private byte[] generateShareSecret() {
		// Generate 256-bit (32-byte) shared secret
		byte[] sharedSecret = new byte[32];
		sharedSecret = ConstantUtils.SECRET_KEY.getBytes();
		return sharedSecret;
	}

	// Kiểm tra token có hết hạn hay không
	public Boolean isTokenExpired(String token) {
		token = stripBearerToken(token);
		Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	// Kiểm tra token có hợp lệ không
	public Boolean validateTokenLogin(String token, String action) {
		token = stripBearerToken(token);
	    if (token == null || token.trim().length() == 0) {
//	    	System.out.println("------------ Rong ---------------");
	      return false;
	    }
	    String username = getUsernameFromToken(token, action);
	    if (username == null || username.isEmpty()) {
	      return false;
	    }
	    if (isTokenExpired(token)) {
	      return false;
	    }
	    return true;
	}
}