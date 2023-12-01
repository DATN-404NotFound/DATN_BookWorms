package com.poly.DATN_BookWorms.utils;

import org.springframework.stereotype.Service;

@Service
public class MailBody {

	
	public String mailBody(String fullnameOfAccount, int OTP) { 
		String body ="<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\" />\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width,  \r\n"
				+ "                         initial-scale=1.0\" />\r\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\" />\r\n"
				+ "    <title>Create new Password</title>\r\n"
				+ "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n"
				+ "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js\"></script>\r\n"
				+ "    <script src=\"https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js\"></script>\r\n"
				+ "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n"
				+ "    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css\">\r\n"
				+ "</head>\r\n"
				+ "\r\n"
				+ "<body>\r\n"
				+ "    <div style=\" width:100%;\">\r\n"
				+ "\r\n"
				+ "        <p>Kính gửi : <b>"+fullnameOfAccount+"</b> .</p>\r\n"
				+ "        <br>\r\n"
				+ "        <div class=\"mt-4 p-5\" style=\"border: 1px solid rgb(187, 187, 186); width: 95%; margin: auto; text-align: center;\">\r\n"
				+ "            <h4 class=\"p-3\">MÃ XÁC NHẬN</h4>\r\n"
				+ "            <p>Cảm ơn bạn rất nhiều vì đã sử dụng dịch vụ IBook</p>\r\n"
				+ "            <p>Chúng tôi đã nhận được yêu cầu và thông tin mà bạn cung cấp</p>\r\n"
				+ "            <p>Chúng tôi đã xem xét và kiểm tra thông tin bạn. Để tránh tiết lộ thông tin của bạn ra ngoài, chúng tôi sử\r\n"
				+ "                dụng mã xác nhận nhằm đảm bảo tính xác thực và bảo mật</p>\r\n"
				+ "            <p>Dưới đây là mã xác thực của bạn. Bạn vui lòng không cung cấp mã này cho bất kì ai nhằm tránh bị lộ thông\r\n"
				+ "                tin.</p>\r\n"
				+ "            <p>Mã xác thực : <strong\r\n"
				+ "                    style=\"padding: 10px; border: 1px solid rgb(124, 123, 123); background-color: rgb(26, 8, 1); color: white;\">"+OTP+" </strong></p>\r\n"
				+ "        </div>\r\n"
				+ "\r\n"
				+ "        <hr>\r\n"
				+ "        <p>Xin chân thành cảm ơn.</p>\r\n"
				+ "        <h3>IBook Welcome</h3>\r\n"
				+ "        <div style=\"color:#171818 ; \">\r\n"
				+ "            <b>\r\n"
				+ "                Thông tin về công ty\r\n"
				+ "            </b>\r\n"
				+ "            <p>\r\n"
				+ "                Công ty TNHH 404 Not Found <br>\r\n"
				+ "                - Địa chỉ đăng ký kinh doanh: Tòa T, Công viên phần mềm Quang Trung, Tô Kí- Quận 12 TP Hồ Chí Minh - Việt\r\n"
				+ "                Nam.\r\n"
				+ "                <br>\r\n"
				+ "                - Giấy chứng nhận Đăng ký Kinh doanh số 123456789 do Sở Kế hoạch và Đầu tư Thành phố Hồ Chí Minh cấp ngày\r\n"
				+ "                01/01/2023\r\n"
				+ "                <br>\r\n"
				+ "                *   Quý khách có nhu cầu liên lạc, trao đổi hoặc đóng góp ý kiến, vui lòng tham khảo các thông tin sau:\r\n"
				+ "                <br>\r\n"
				+ "                - Liên lạc qua điện thoại: 1900 0001 <br>\r\n"
				+ "                - Liên lạc qua email: Truy cập hotro.ibook.vn <br>\r\n"
				+ "                - Fanpage của Ibook: http://facebook.com/IBook.vn <br>\r\n"
				+ "                - Đối tác có nhu cầu hợp tác quảng cáo hoặc kinh doanh: marketing@IBook.vn <br>\r\n"
				+ "    <br>\r\n"
				+ "                - Văn phòng chính: Tòa T, Công viên phần mềm Quang Trung, Tô Kí- Quận 12 TP Hồ Chí Minh - Việt Nam. <br>\r\n"
				+ "                - Văn phòng: Tòa T, Công viên phần mềm Quang Trung, Tô Kí- Quận 12 TP Hồ Chí Minh - Việt Nam. <br>\r\n"
				+ "            </p>\r\n"
				+ "        </div>\r\n"
				+ "    </div>\r\n"
				+ "</body>\r\n"
				+ "\r\n"
				+ "</html>";
		return body;
	}
}