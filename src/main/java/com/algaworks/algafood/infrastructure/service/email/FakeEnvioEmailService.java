package com.algaworks.algafood.infrastructure.service.email;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FakeEnvioEmailService extends SMTPEnvioEmailService {

	@Override
	public void enviar(Mensagem mensagem) {
		String corpo = processarTemplate(mensagem);
		log.info("[FAKE EMAIL] Para: {}\n{}", mensagem.getDestinatarios(), corpo);
	}
	
}
