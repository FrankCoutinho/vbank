package br.com.vbank.timers;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.ScheduleExpression;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

import br.com.vbank.domain.Transferencia;
import br.com.vbank.services.TransferenciaService;

public  class TransferenciaTimer  {

	private static final String TEMPO_PARA_VERIFICAO_DE_TRANSFERENCIAS = "5";
	
	@Resource 
	private TimerService timerService;
	
	@EJB
	private TransferenciaService transferenciaService;
		
	@PostConstruct
	public void init() {
		
		ScheduleExpression se = new ScheduleExpression();
		se.minute(TEMPO_PARA_VERIFICAO_DE_TRANSFERENCIAS);
		TimerConfig tc = new TimerConfig();
		timerService.createCalendarTimer(se, tc);
	}
	
	@Timeout
	public List<Transferencia> listarTransferenciasPendentes(){
		List<Transferencia> transferencias = transferenciaService.getTransferenciasPendentes();
		return transferencias;
	}
}
