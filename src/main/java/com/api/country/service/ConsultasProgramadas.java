package com.api.country.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.codehaus.plexus.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ConsultasProgramadas  {
	private static final Logger LOGGER = Logger.getLogger(ConsultasProgramadas.class.getName());
	private static final String ERRO = "ERRO: {0}";
	private static final String NOME_CSV = "ceps.csv";

	@Value("${countries.target}")
	private String targetCountries;

	@Value("${countries.path}")
	private String pathCountries;

	@Autowired
	private PaisServico paisServico;

	@Autowired
	private RestServico restServico;

	@EventListener(ApplicationReadyEvent.class)
	@Scheduled(cron = "0 0 * 1 * ?", zone = "America/Recife")
	public void sincronizarPaises() {
		try {
			LOGGER.info("Consultando países na Countries API");
			restServico.buscarPaises(targetCountries, pathCountries).stream()
					.filter(pais -> paisServico.naoExistePorId(pais.getId())).forEach((pais -> {
						pais.setNome(pais.getTranslations().getBr());
						if (pais.getId() != null)
							paisServico.salvar(pais);
					}));
			LOGGER.info("Consulta aos países da Countries API finalizada");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, ERRO, ExceptionUtils.getStackTrace(e));
		}
	}
}
