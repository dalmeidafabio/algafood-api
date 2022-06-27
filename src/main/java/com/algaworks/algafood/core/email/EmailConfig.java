package com.algaworks.algafood.core.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafood.domain.service.EnvioEmailService;
import com.algaworks.algafood.infrastructure.service.email.FakeEnvioEmailService;
import com.algaworks.algafood.infrastructure.service.email.SMTPEnvioEmailService;
import com.algaworks.algafood.infrastructure.service.email.SandBoxEnvioEmailService;

@Configuration
public class EmailConfig {

	@Autowired
	private EmailProperties emailProperties;
	
	@Bean
	public EnvioEmailService envioEmailService() {
		
		switch (emailProperties.getImpl()) {
		
		case FAKE:
			return new FakeEnvioEmailService();
			
		case SANDBOX:
			return new SandBoxEnvioEmailService();
			
		case SMTP:
			return new SMTPEnvioEmailService();
			
		default:
			return null;
		}
	}
}
