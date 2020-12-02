package com.appsdeveloperblog.app.ws.shared;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceAsyncClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.appsdeveloperblog.app.ws.shared.dto.UserDto;

public class AmazonSES {
	final String FROM = "leonardo.g.o.lrv@gmail.com";

	final String SUBJECT = "Ultimo passo para finalizar seu cadastro no sistema";

	final String HTMLBODY = "<h1>Por favor, verifique seu e-mail.</h1>"
			+ "<p>Obrigado por se cadastrar em nossa plataforma. Para completar seu registro e poder entrar em nossa aplicação clique no link a seguir </p>"
			+ "<a href='http://localhost:8000/verification-service/email-verification.html?token=$tokenValue'>"
			+ "Verificar email" + "</a> <br><br>" + "Obrigado, eperamos por você (:";

	final String TEXTBODY = "Por favor, verifique seu e-mail."
			+ "Obrigado por se cadastrar em nossa plataforma. Para completar seu registro e poder entrar em nossa aplicação"
			+ "abra o seguinte link em seu navegador: "
			+ "http://localhost:8000/verification-service/email-verification.html?token=$tokenValue"
			+ "Obrigado, esperamos por você (:";

	public void verifyEmail(UserDto userDto) {
		AmazonSimpleEmailService client = AmazonSimpleEmailServiceAsyncClientBuilder.standard()
				.withRegion(Regions.SA_EAST_1).build();

		String htmlBodyWithToken = HTMLBODY.replace("$tokenValue", userDto.getEmailVerificationToken());
		String textBodyWithToken = TEXTBODY.replace("$tokenValue", userDto.getEmailVerificationToken());

		SendEmailRequest request = new SendEmailRequest()
				.withDestination(new Destination().withToAddresses(userDto.getEmail()))
				.withMessage(new Message()
						.withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(htmlBodyWithToken))
								.withText(new Content().withCharset("UTF-8").withData(textBodyWithToken)))
						.withSubject(new Content().withCharset("UTF-8").withData(SUBJECT)))
				.withSource(FROM);
		
		client.sendEmail(request);
		
		System.out.println("Email sent!");
	}
}
