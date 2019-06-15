package br.com.vbank.queue;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.vbank.domain.Conta;
import br.com.vbank.domain.Movimentacao;
import br.com.vbank.domain.Transferencia;
import br.com.vbank.enums.SituacaoTransferencia;
import br.com.vbank.repository.ContaRepository;
import br.com.vbank.services.TransferenciaService;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType",
        propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destinationLookup",
        propertyValue = "jms/EfetivacaoTransferenciaQueue")
})
public class EfetivacaoTransferenciaQueue implements MessageListener {

    @EJB
    private ContaRepository contaRepository;

    @EJB
    private TransferenciaService transferenciaService;

    @PersistenceContext()
    EntityManager entityManager;

    @Override
    public void onMessage(Message message) {
        try {

            ObjectMessage om = (ObjectMessage) message;
            Object o = om.getObject();
            Transferencia sr = (Transferencia) o;
            Transferencia persisted = transferenciaService.getById(sr.getId());
            Conta contaDebitada = contaRepository.findByNrConta(sr.getNrContaRemetente());
            Conta contaCreditada = contaRepository.findByNrConta(sr.getNrContaFavorecido());
            contaDebitada.setVlSaldoContaCorrente(contaDebitada.getVlSaldoContaCorrente().subtract(sr.getVlTransferencia()));
            contaCreditada.setVlSaldoContaCorrente(contaCreditada.getVlSaldoContaCorrente().add(sr.getVlTransferencia()));
            persisted.setSituacaoTransferencia(SituacaoTransferencia.EFETIVADA);
            transferenciaService.salvar(persisted);
        }
        catch (JMSException ex) {
            Logger.getLogger(Transferencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}	
