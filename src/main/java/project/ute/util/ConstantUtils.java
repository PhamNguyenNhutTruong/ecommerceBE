package project.ute.util;

public class ConstantUtils {
	public static final String SUCCESS = "SUCCESS";
	public static final String WRANING = "WRANING";
	public static final String ERROR = "ERROR";
	
	public static final String GEN_TOKEN = "GEN";
	public static final String REF_TOKEN = "REF";
	
	public static final String USERNAME_GEN = "TAT Coffe Shop App - Generate Token";
	public static final String USERNAME_REF = "TAT Coffe Shop App - Refresh Token";
	public static final String PASSWORD = "TAT Coffe Shop App";
	public static final String SECRET_KEY = "11111111111111111111111111111111";

	public static final int EXPIRE_TIME_GEN = 3600000;
	public static final int EXPIRE_TIME_REF = 864000000;
	
	public static final String BEARER_PREFIX = "Bearer ";
	
	public static final String TOKEN_HEADER = "Authorization";
	public static final String TOKEN_HEADER_REFRESH = "Refresh-Token"; 
	
	public final static String URL_LOAD_IMAGE_FROM_IMAGE_TABLE = "http://localhost:8080/api/load-image/image/";
	public final static String URL__LOAD_IMAGE_FROM_PRODUCT_TABLE = "http://localhost:8080/api/load-image/product/";
	public final static String URL__LOAD_IMAGE_FROM_SLIDE_TABLE = "http://localhost:8080/api/load-image/slide/";
	public final static String URL__LOAD_IMAGE_FROM_CUSTOMER_TABLE = "http://localhost:8080/api/load-image/profile/";
}
