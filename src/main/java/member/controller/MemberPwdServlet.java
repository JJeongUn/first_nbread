package member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/findpwd")
public class MemberPwdServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static Map<String, String> userDatabase = new HashMap<>();
    private static Map<String, String> emailDatabase = new HashMap<>();
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String EMAIL_USERNAME = "skyjo95@gmail.com";
    private static final String EMAIL_PASSWORD = "skwjd1004!";

    public MemberPwdServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");

        // 여기에서 데이터베이스 연결 및 쿼리 수행
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM member WHERE me_name = ? AND me_email = ?";
            conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.77:1521:xe", "c##comidb", "comidb"); // 데이터베이스 연결 설정을 추가
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);
            
            resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                // 일치하는 사용자가 있는 경우
                String temporaryPassword = generateTemporaryPassword();

                sendEmail(email, temporaryPassword);
                out.println("임시 비밀번호가 이메일로 전송되었습니다.");
            } else {
                out.println("입력한 이름과 이메일이 일치하지 않습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 연결, statement 및 결과셋 닫기
            try {
                if (resultSet != null) resultSet.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
        
        
        
		/*
		 * out.println("<html>");
		 * out.println("<head><title>Password Reset</title></head>");
		 * out.println("<body>"); out.println("<h1>Password Reset</h1>");
		 * out.println("<form action='findpwd' method='post'>");
		 * out.println("<label for='name'>이름:</label>");
		 * out.println("<input type='text' id='name' name='name' required><br>");
		 * out.println("<label for='email'>이메일:</label>");
		 * out.println("<input type='email' id='email' name='email' required><br>");
		 * out.println("<input type='submit' value='비밀번호 재설정'>");
		 * out.println("</form>"); out.println("</body>"); out.println("</html>");
		 */


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	doGet(request, response);
    }
    private static String generateTemporaryPassword() {
        Random random = new Random();
        StringBuilder temporaryPassword = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            temporaryPassword.append((char) (random.nextInt(26) + 'a'));
        }
        return temporaryPassword.toString();
    }

    private static void sendEmail(String toEmail, String temporaryPassword) {
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        // 직접 Properties 객체 내에서 Authenticator를 설정합니다.
        props.put("mail.smtp.user", EMAIL_USERNAME);
        props.put("mail.smtp.password", EMAIL_PASSWORD);

        // Properties를 사용하여 Session 객체를 생성합니다.
        Session session = Session.getDefaultInstance(props);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("임시 비밀번호 발급");
            message.setText("임시 비밀번호: " + temporaryPassword);

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}