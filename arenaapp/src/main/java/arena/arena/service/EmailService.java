package arena.arena.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmailService {

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Environment env;

	@Async
	public void sendVerificationMail(String email, Long id) {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Please verify your email address");
		mail.setText("http://localhost:4200/activateAccount?id=" + id);
		javaMailSender.send(mail);

	}

	@Async
	public void testEmail() throws MailException, MessagingException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

		helper.setTo("verakovacevic98@gmail.com");
		helper.setFrom(env.getProperty("spring.mail.username"));
		helper.setSubject("Arena");
		helper.setSentDate(new Date());
		helper.setText("<h1>" + "Arena" + "</h1>" + "\n\n" + "<p>" + "Reservation" + "</p>", true);
		javaMailSender.send(mimeMessage);
	}

	@Async
	public void makeReservationEmail(String email, String eventName, Date startDate, List<Double> prices,
			List<String> seats) throws MailException, MessagingException {

		StringBuilder sb = new StringBuilder();
		for (String seat : seats)
			sb.append(" " + seat + ",");
		String seatsStr = sb.toString();
		StringBuilder sbPrices = new StringBuilder();
		for (Double price : prices) {
			sbPrices.append(" " + price + ",");
		}
		String pricesStr = sbPrices.toString();

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

		helper.setTo(email);
		// helper.setTo("verakovacevic98@gmail.com");
		helper.setFrom(env.getProperty("spring.mail.username"));
		helper.setSubject("Arena");
		helper.setSentDate(new Date());
		helper.setText("<h1>" + "Arena" + "</h1>" + "\n\n" + "<p>" + "You have successfully made a reservation for "
				+ "<b>" + eventName + "</b>" + " at " + "<b>" + sdf.format(startDate) + "</b>"
				+ ". Your seats are numbered:" + "<b>" + seatsStr.substring(0, seatsStr.length() - 1) + "</b>"
				+ ". Prices, respectively : " + "<b>" + pricesStr.substring(0, pricesStr.length() - 1) + "</b>"
				+ ". We look forward to welcoming you!" + "</p>", true);
		javaMailSender.send(mimeMessage);
	}

	@Async
	public void cancelReservationEmail(String email, String eventName, Date startDate, List<String> seats)
			throws MailException, MessagingException {

		StringBuilder sb = new StringBuilder();
		for (String seat : seats)
			sb.append(" " + seat + ",");
		String seatsStr = sb.toString();

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

		helper.setTo(email);
		// helper.setTo("verakovacevic98@gmail.com");
		helper.setFrom(env.getProperty("spring.mail.username"));
		helper.setSubject("Arena");
		helper.setSentDate(new Date());
		helper.setText(
				"<h1>" + "Arena" + "</h1>" + "\n\n" + "<p>" + "You have successfully cancelled your reservation for "
						+ "<b>" + eventName + "</b>" + " at " + "<b>" + sdf.format(startDate) + "</b>"
						+ ". You have cancelled seats:" + "<b>" + seatsStr.substring(0, seatsStr.length() - 1) + "</b>"
						+ ". We look forward to welcoming you next time!" + "</p>",
				true);
		javaMailSender.send(mimeMessage);
	}

}
