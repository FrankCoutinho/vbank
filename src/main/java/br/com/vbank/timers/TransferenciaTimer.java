package br.com.vbank.timers;


import br.com.vbank.domain.Transferencia;
import br.com.vbank.services.HorarioDeTransacoesService;
import br.com.vbank.services.TransferenciaService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import javax.jms.*;

import static java.time.LocalDateTime.now;

public  class TransferenciaTimer  {

	private static final String TEMPO_PARA_VERIFICAO_DE_TRANSFERENCIAS = "5";
	
	@Resource 
	private TimerService timerService;

	@Inject
	@JMSConnectionFactory("jms/QueueConnectionFactory")
	private JMSContext jmsContext;

	@Resource(name = "jms/EfetivacaoTransferenciaQueue")
	private Destination destination;

	@EJB
	private TransferenciaService transferenciaService;

	@EJB
	private HorarioDeTransacoesService horarioDeTransacoesService;

	@PostConstruct
	public void init() {
		
		ScheduleExpression se = new ScheduleExpression();
		se.minute(TEMPO_PARA_VERIFICAO_DE_TRANSFERENCIAS);
		TimerConfig tc = new TimerConfig();
		timerService.createCalendarTimer(se, tc);
	}
	
	@Timeout
	public void enviarTransferenciasParaSeremEfetivadas() {

		if (!horarioDeTransacoesService.recuperar().isAbertoParaTransacoes())
			return;

		JMSProducer jmsProducer = jmsContext.createProducer();

		transferenciaService
			.getTransferenciasPendentes()
			.stream()
			.filter(it -> it.getDtAgendamento().isBefore(now()))
			.map(this::convertToObjectMessage)
			.forEach(it -> jmsProducer.send(destination, it));
	}

	private ObjectMessage convertToObjectMessage(Transferencia transferencia) {

		try {
			ObjectMessage message = jmsContext.createObjectMessage();
			message.setObject(transferencia);
			return message;
		}
		catch (JMSException exception) {
			throw new IllegalThreadStateException();
		}
	}
}
