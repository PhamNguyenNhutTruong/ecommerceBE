package project.ute.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import project.ute.dto.AccountDto;
import project.ute.dto.MessageDto;
import project.ute.model.Customer;
import project.ute.service.CustomerService;
import project.ute.service.ForgotPasswordService;
import project.ute.util.BcryptUtils;
import project.ute.util.ConstantUtils;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService{
	@Autowired
	CustomerService customerService;
	
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public MessageDto checkEmailBeforeResetPassword(String email) throws MessagingException {
		Optional<Customer> cuOptional = customerService.checkCustomerAccount(email);
//		Nếu không có tài khoản
		if(cuOptional.isEmpty()) {
			return new MessageDto("Check account", "Account does not exist", ConstantUtils.ERROR, null, null, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
//		Đã có tài khoản --> Tiến hành kiểm tra loại tài khoản trước khi tiến hành cho reset mật khẩu
		else {
			if(cuOptional.get().getIsGoogleLogin()) {
				return new MessageDto("Check account", "Google account can not reset password", ConstantUtils.WRANING, null, null, HttpStatus.BAD_REQUEST);
			} else {
//		Cho reset password
				this.sendMailToForgotPassword(email);
				return new MessageDto("Check account", "Check mail to reset password", ConstantUtils.ERROR, null, null, HttpStatus.OK);
			}
		}
	}

	@Override
	public void sendMailToForgotPassword(String email) throws MessagingException {
		String formResetPassword = "http://localhost:8080/api/login/forgot-password/reset-password-form/" + email;
		String subject = "Reset Password";
		String htmlContent = "<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"utf-8\">\r\n"
				+ "    <meta http-equiv=\"x-ua-compatible\" content=\"ie=edge\">\r\n"
				+ "    <title>Password Reset</title>\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
				+ "    <style type=\"text/css\">\r\n"
				+ "        @media screen {\r\n"
				+ "            @font-face {\r\n"
				+ "                font-family: 'Source Sans Pro';\r\n"
				+ "                font-style: normal;\r\n"
				+ "                font-weight: 400;\r\n"
				+ "                src: local('Source Sans Pro Regular'), local('SourceSansPro-Regular'), url(https://fonts.gstatic.com/s/sourcesanspro/v10/ODelI1aHBYDBqgeIAH2zlBM0YzuT7MdOe03otPbuUS0.woff) format('woff');\r\n"
				+ "            }\r\n"
				+ "\r\n"
				+ "            @font-face {\r\n"
				+ "                font-family: 'Source Sans Pro';\r\n"
				+ "                font-style: normal;\r\n"
				+ "                font-weight: 700;\r\n"
				+ "                src: local('Source Sans Pro Bold'), local('SourceSansPro-Bold'), url(https://fonts.gstatic.com/s/sourcesanspro/v10/toadOcfmlt9b38dHJxOBGFkQc6VGVFSmCnC_l7QZG60.woff) format('woff');\r\n"
				+ "            }\r\n"
				+ "        }\r\n"
				+ "        body,\r\n"
				+ "        table,\r\n"
				+ "        td,\r\n"
				+ "        a {\r\n"
				+ "            -ms-text-size-adjust: 100%;\r\n"
				+ "            -webkit-text-size-adjust: 100%;\r\n"
				+ "        }\r\n"
				+ "        table,\r\n"
				+ "        td {\r\n"
				+ "            mso-table-rspace: 0pt;\r\n"
				+ "            mso-table-lspace: 0pt;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        img {\r\n"
				+ "            -ms-interpolation-mode: bicubic;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        a[x-apple-data-detectors] {\r\n"
				+ "            font-family: inherit !important;\r\n"
				+ "            font-size: inherit !important;\r\n"
				+ "            font-weight: inherit !important;\r\n"
				+ "            line-height: inherit !important;\r\n"
				+ "            color: inherit !important;\r\n"
				+ "            text-decoration: none !important;\r\n"
				+ "        }\r\n"
				+ "        div[style*=\"margin: 16px 0;\"] {\r\n"
				+ "            margin: 0 !important;\r\n"
				+ "        }\r\n"
				+ "        body {\r\n"
				+ "            width: 100% !important;\r\n"
				+ "            height: 100% !important;\r\n"
				+ "            padding: 0 !important;\r\n"
				+ "            margin: 0 !important;\r\n"
				+ "        }\r\n"
				+ "        table {\r\n"
				+ "            border-collapse: collapse !important;\r\n"
				+ "        }\r\n"
				+ "        a {\r\n"
				+ "            color: #1a82e2;\r\n"
				+ "        }\r\n"
				+ "        img {\r\n"
				+ "            height: auto;\r\n"
				+ "            line-height: 100%;\r\n"
				+ "            text-decoration: none;\r\n"
				+ "            border: 0;\r\n"
				+ "            outline: none;\r\n"
				+ "        }\r\n"
				+ "    </style>\r\n"
				+ "</head>\r\n"
				+ "<body style=\"background-color: #e9ecef;\">\r\n"
				+ "    <div class=\"preheader\"\r\n"
				+ "        style=\"display: none; max-width: 0; max-height: 0; overflow: hidden; font-size: 1px; line-height: 1px; color: #fff; opacity: 0;\">\r\n"
				+ "        A preheader is the short summary text that follows the subject line when an email is viewed in the inbox.\r\n"
				+ "    </div>\r\n"
				+ "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n"
				+ "        <tr>\r\n"
				+ "            <td align=\"center\" bgcolor=\"#e9ecef\">\r\n"
				+ "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td align=\"left\" bgcolor=\"#ffffff\"\r\n"
				+ "                            style=\"padding: 36px 24px 0; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; border-top: 3px solid #d4dadf;\">\r\n"
				+ "                            <h1\r\n"
				+ "                                style=\"margin: 0; font-size: 32px; font-weight: 700; letter-spacing: -1px; line-height: 48px;\">\r\n"
				+ "                                Reset Your Password</h1>\r\n"
				+ "                        </td>\r\n"
				+ "                    </tr>\r\n"
				+ "                </table>\r\n"
				+ "            </td>\r\n"
				+ "        </tr>\r\n"
				+ "        <tr>\r\n"
				+ "            <td align=\"center\" bgcolor=\"#e9ecef\">\r\n"
				+ "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td align=\"left\" bgcolor=\"#ffffff\"\r\n"
				+ "                            style=\"padding: 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px;\">\r\n"
				+ "                            <p style=\"margin: 0;\">Tap the button below to reset your customer account password. If you\r\n"
				+ "                                didn't request a new password, you can safely delete this email.</p>\r\n"
				+ "                        </td>\r\n"
				+ "                    </tr>\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td align=\"left\" bgcolor=\"#ffffff\">\r\n"
				+ "                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <td align=\"center\" bgcolor=\"#ffffff\" style=\"padding: 12px;\">\r\n"
				+ "                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
				+ "                                            <tr>\r\n"
				+ "                                                <td align=\"center\" bgcolor=\"#1a82e2\" style=\"border-radius: 6px;\">\r\n"
				+ "                                                    <a href=\"" + formResetPassword + "\" target=\"_blank\"\r\n"
				+ "                                                        style=\"display: inline-block; padding: 16px 36px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 16px; color: #ffffff; text-decoration: none; border-radius: 6px;\">Click here to reset password</a>\r\n"
				+ "                                                </td>\r\n"
				+ "                                            </tr>\r\n"
				+ "                                        </table>\r\n"
				+ "                                    </td>\r\n"
				+ "                                </tr>\r\n"
				+ "                            </table>\r\n"
				+ "                        </td>\r\n"
				+ "                    </tr>\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td align=\"left\" bgcolor=\"#ffffff\"\r\n"
				+ "                            style=\"padding: 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px;\">\r\n"
				+ "                            <p style=\"margin: 0;\">If that doesn't work, copy and paste the following link in your\r\n"
				+ "                                browser:</p>\r\n"
				+ "                            <p style=\"margin: 0;\"><a href=\"" + formResetPassword + "\"\r\n"
				+ "                                    target=\"_blank\">https://same-link-as-button.url/xxx-xxx-xxxx</a></p>\r\n"
				+ "                        </td>\r\n"
				+ "                    </tr>\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td align=\"left\" bgcolor=\"#ffffff\"\r\n"
				+ "                            style=\"padding: 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px; border-bottom: 3px solid #d4dadf\">\r\n"
				+ "                            <p style=\"margin: 0;\">TAT Coffee Shop</p>\r\n"
				+ "                        </td>\r\n"
				+ "                    </tr>\r\n"
				+ "                </table>\r\n"
				+ "            </td>\r\n"
				+ "        </tr>\r\n"
				+ "    </table>\r\n"
				+ "    </td>\r\n"
				+ "    </tr>\r\n"
				+ "    </table>\r\n"
				+ "</body>\r\n"
				+ "</html>";
		
		MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        mailSender.send(message);
	}

	@Override
	public String formResetPassword(String email) {
		String contentHtml = "<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "<head>\r\n"
				+ "  <meta charset=\"UTF-8\">\r\n"
				+ "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
				+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "  <title>Reset password</title>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "  <style>\r\n"
				+ "    * {\r\n"
				+ "      padding: 0;\r\n"
				+ "      margin: 0;\r\n"
				+ "      box-sizing: border-box;\r\n"
				+ "    }\r\n"
				+ "    html {\r\n"
				+ "      color: #333;\r\n"
				+ "      font-size: 62.5%;\r\n"
				+ "      font-family: \"Open Sans\", sans-serif;\r\n"
				+ "    }\r\n"
				+ "    .main {\r\n"
				+ "      background: #f1f1f1;\r\n"
				+ "      min-height: 100vh;\r\n"
				+ "      display: flex;\r\n"
				+ "      justify-content: center;\r\n"
				+ "    }\r\n"
				+ "    .form {\r\n"
				+ "      width: 360px;\r\n"
				+ "      min-height: 100px;\r\n"
				+ "      padding: 32px 24px;\r\n"
				+ "      text-align: center;\r\n"
				+ "      background: #fff;\r\n"
				+ "      border-radius: 2px;\r\n"
				+ "      margin: 24px;\r\n"
				+ "      align-self: center;\r\n"
				+ "      box-shadow: 0 2px 5px 0 rgba(51, 62, 73, 0.1);\r\n"
				+ "    }\r\n"
				+ "    .form .heading {\r\n"
				+ "      font-size: 2rem;\r\n"
				+ "    }\r\n"
				+ "    .form .desc {\r\n"
				+ "      text-align: center;\r\n"
				+ "      color: #636d77;\r\n"
				+ "      font-size: 1.6rem;\r\n"
				+ "      font-weight: lighter;\r\n"
				+ "      line-height: 2.4rem;\r\n"
				+ "      margin-top: 16px;\r\n"
				+ "      font-weight: 300;\r\n"
				+ "    }\r\n"
				+ "    .form-group {\r\n"
				+ "      display: flex;\r\n"
				+ "      margin-bottom: 16px;\r\n"
				+ "      flex-direction: column;\r\n"
				+ "    }\r\n"
				+ "    .form-label,\r\n"
				+ "    .form-message {\r\n"
				+ "      text-align: left;\r\n"
				+ "    }\r\n"
				+ "    .form-label {\r\n"
				+ "      font-weight: 700;\r\n"
				+ "      padding-bottom: 6px;\r\n"
				+ "      line-height: 1.8rem;\r\n"
				+ "      font-size: 1.4rem;\r\n"
				+ "    }\r\n"
				+ "    .form-control {\r\n"
				+ "      height: 40px;\r\n"
				+ "      padding: 8px 12px;\r\n"
				+ "      border: 1px solid #b3b3b3;\r\n"
				+ "      border-radius: 3px;\r\n"
				+ "      outline: none;\r\n"
				+ "      font-size: 1.4rem;\r\n"
				+ "    }\r\n"
				+ "    .form-control:hover {\r\n"
				+ "      border-color: #1dbfaf;\r\n"
				+ "    }\r\n"
				+ "    .form-group.invalid .form-control {\r\n"
				+ "      border-color: #f33a58;\r\n"
				+ "    }\r\n"
				+ "    .form-group.invalid .form-message {\r\n"
				+ "      color: #f33a58;\r\n"
				+ "    }\r\n"
				+ "    .form-message {\r\n"
				+ "      font-size: 1.2rem;\r\n"
				+ "      line-height: 1.6rem;\r\n"
				+ "      padding: 4px 0 0;\r\n"
				+ "    }\r\n"
				+ "    .form-submit {\r\n"
				+ "      outline: none;\r\n"
				+ "      background-color: #1dbfaf;\r\n"
				+ "      margin-top: 12px;\r\n"
				+ "      padding: 12px 16px;\r\n"
				+ "      font-weight: 600;\r\n"
				+ "      color: #fff;\r\n"
				+ "      border: none;\r\n"
				+ "      width: 100%;\r\n"
				+ "      font-size: 14px;\r\n"
				+ "      border-radius: 8px;\r\n"
				+ "      cursor: pointer;\r\n"
				+ "    }\r\n"
				+ "    .form-submit:hover {\r\n"
				+ "      background-color: #1ac7b6;\r\n"
				+ "    }\r\n"
				+ "    .spacer {\r\n"
				+ "      margin-top: 36px;\r\n"
				+ "    }\r\n"
				+ "  </style>\r\n"
				+ "  <div class=\"main\">\r\n"
				+ "    <div class=\"form\" id=\"form-reset-password\">\r\n"
				+ "      <h3 class=\"heading\">RESET PASSWORD</h3>\r\n"
				+ "      <p class=\"desc\">TAT Coffee Shop xin chào quý khách ❤️</p>\r\n"
				+ "      <div class=\"spacer\"></div>\r\n"
				+ "      <div class=\"form-group\">\r\n"
				+ "        <label for=\"email\" class=\"form-label\">Email</label>\r\n"
				+ "        <input id=\"email\" name=\"email\" type=\"text\" placeholder=\"VD: email@domain.com\"\r\n"
				+ "          value=\"" + email + "\" class=\"form-control\" disabled>\r\n"
				+ "      </div>\r\n"
				+ "      <div class=\"form-group\">\r\n"
				+ "        <label for=\"password\" class=\"form-label\">Mật khẩu</label>\r\n"
				+ "        <input id=\"password\" name=\"password\" type=\"password\" placeholder=\"Nhập mật khẩu\" class=\"form-control\">\r\n"
				+ "      </div>\r\n"
				+ "      <div class=\"form-group\">\r\n"
				+ "        <label for=\"password_confirmation\" class=\"form-label\">Nhập lại mật khẩu</label>\r\n"
				+ "        <input id=\"password_confirmation\" name=\"passwordConfirmation\" placeholder=\"Nhập lại mật khẩu\" type=\"password\"\r\n"
				+ "          class=\"form-control\">\r\n"
				+ "      </div>\r\n"
				+ "      <button class=\"form-submit\">Reset password</button>\r\n"
				+ "    </div>\r\n"
				+ "  </div>\r\n"
				+ "  <script src=\"https://unpkg.com/sweetalert/dist/sweetalert.min.js\"></script>\r\n"
				+ "  <script>\r\n"
				+ "    var emailTag = document.querySelector('#email');\r\n"
				+ "    var passwordTag = document.querySelector('#password');\r\n"
				+ "    var passwordConfirmation = document.querySelector('#password_confirmation');\r\n"
				+ "    var buttonRessetPasswordTag = document.querySelector('.form-submit');\r\n"
				+ "    buttonRessetPasswordTag.onclick = () => {\r\n"
				+ "      if (passwordTag.value.length >= 8) {\r\n"
				+ "        if (passwordTag.value === passwordConfirmation.value) {\r\n"
				+ "          var data = { \r\n"
				+ "            \"email\": emailTag.value, \r\n"
				+ "            \"password\": passwordTag.value, \r\n"
				+ "            \"role\": 1, \r\n"
				+ "            \"isGoogleLogin\": false \r\n"
				+ "          }\r\n"
				+ "          var message = fetch(\"http://localhost:8080/api/login/forgot-password/reset-password\", {\r\n"
				+ "            method: 'POST',\r\n"
				+ "            body: JSON.stringify(data),\r\n"
				+ "            headers: {\r\n"
				+ "              \"Content-Type\": \"application/json\"\r\n"
				+ "            }\r\n"
				+ "          })\r\n"
				+ "          .then(response => response.json())\r\n"
				+ "          .then(result => {\r\n"
				+ "            if(result.status === \"SUCCESS\") {\r\n"
				+ "              swal(result.title, result.message, \"success\")\r\n"
				+ "            } else {\r\n"
				+ "              swal(result.title, result.message, \"error\")\r\n"
				+ "            }\r\n"
				+ "          })\r\n"
				+ "          .catch(error => console.log(error));\r\n"
				+ "        } else {\r\n"
				+ "          swal(\"WARNING\", \"Password and password confirmation must be the same\", \"warning\");\r\n"
				+ "        }\r\n"
				+ "      } else {\r\n"
				+ "        swal(\"WARNING\", \"Password must be at least 8 characters\", \"warning\");\r\n"
				+ "      }\r\n"
				+ "    }\r\n"
				+ "  </script>\r\n"
				+ "</body>\r\n"
				+ "</html>";
		return contentHtml;
	}

	@Override
	public MessageDto setResetPassword(AccountDto accountDto) {
		try {
			Customer customer = customerService.getCustomerByEmail(accountDto.getEmail());
			customer.setPassword(BcryptUtils.hashpwd(accountDto.getPassword()));
			
			customerService.addNewCutomer(customer);
			return new MessageDto("Reset password", "Reset password successfull", ConstantUtils.SUCCESS, null, null, HttpStatus.OK);
		} catch (Exception e) {
			return new MessageDto("Login", "Incorrect password", ConstantUtils.ERROR, null, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
