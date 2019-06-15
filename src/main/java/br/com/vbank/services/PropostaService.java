package br.com.vbank.services;

import br.com.vbank.domain.*;
import br.com.vbank.enums.SituacaoProposta;
import br.com.vbank.repository.PropostaRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Stateless
public class PropostaService {

    private static final int TEMPO_MINIMO_PARA_NOVA_PROPOSTA = 30;

    @EJB
    private EmailService emailService;

    @EJB
    private ContaService contaService;

    @EJB
    private ClienteService clienteService;
    @EJB
    private PropostaRepository propostaRepository;

    @EJB
    private UsuarioClienteService usuarioClienteService;

    @EJB
    private UsuarioFuncionarioService usuarioFuncionarioService;

    public List<Proposta> getAll() {
        return propostaRepository.getAll();
    }

    public Proposta save(Proposta proposta) {

        verificaSeExisteUmaPropostaRecente(proposta);
        verificaSeExisteClienteCadastradoComCpf(proposta.getCpf());

        return propostaRepository.save(proposta);
    }

    public Proposta findById(Long id) {
        return propostaRepository.findById(id);
    }

    public void remove(Long id) {
        propostaRepository.remove(id);
    }

    public Proposta aprovarProposta(Long idProposta, Long idUsuarioQueAnalisou) {

        Proposta proposta = propostaRepository.getById(idProposta);
        UsuarioFuncionario usuarioFuncionarioAnalise = usuarioFuncionarioService.findById(idUsuarioQueAnalisou);

        proposta.aprovar(usuarioFuncionarioAnalise);
        save(proposta);

        Cliente cliente = new Cliente(proposta);
        clienteService.save(cliente);

        Conta conta = new Conta(cliente);
        contaService.save(conta);

        UsuarioCliente usuarioCliente = new UsuarioCliente(cliente);
        usuarioClienteService.save(usuarioCliente);

        String assunto = "Proposta de abertura de conta Aprovada";

        String descricao = "Prezada(a) Sr(a) " + cliente.getNome() + ".\n\n"
                + "Informamos que o sua proposta de abertura de conta foi aprovada.\n\n"
                + "O número da sua conta e login é: " + cliente.getConta().getNrConta()
                + "\n\nPor favor altere sua senha acessando o seguinte endereço: \n\n" + "http://vbank.com.br/"
                + cliente.getNome() + "/alterarSenha \n\n" + "Atenciosamente,\nEquipe Vbank";

        String destinatario = cliente.getEmail();

        String remetente = usuarioFuncionarioAnalise.getFuncionario().getEmail();

        Email email = new Email(remetente, destinatario, assunto, descricao);
        emailService.save(email);
        emailService.enviarEmail(email);

        return proposta;
    }

    public Proposta rejeitarProposta(Long idProposta, String mensagemRejeicao, Long idUsuarioAnalise) {
        Proposta proposta = propostaRepository.getById(idProposta);
        UsuarioFuncionario usuarioFuncionario = usuarioFuncionarioService.getById(idUsuarioAnalise);

        proposta.rejeitar(mensagemRejeicao, usuarioFuncionario);
        save(proposta);

        String assunto = "Proposta de abertura de conta rejeitada";
        String descricao = "Prezada(a) Sr(a) " + proposta.getNome() + ".\n\n"
                + "Informamos que o sua proposta de abertura de conta foi rejeitada pelo seguinte motivo: "
                + mensagemRejeicao + ".\n\n"
                + "Aguarde 30 dias para realizar uma nova proposta.\n\nAtenciosamente, \nEquipe VBank";

        String destinatario = proposta.getEmail();
        String remetente = usuarioFuncionario.getFuncionario().getEmail();

        Email email = new Email(remetente, destinatario, assunto, descricao);
        emailService.save(email);
        emailService.enviarEmail(email);
        return proposta;
    }

    public List<Proposta> findPropostasAbertasByRegiao(String regiao) {
        return propostaRepository.findByRegiaoAndSituacaoProposta(regiao, SituacaoProposta.ABERTA);
    }

    private void verificaSeExisteClienteCadastradoComCpf(String cpf) {
        if (clienteService.existeClienteComCpf(cpf))
            throw new RuntimeException("Já existe um cliente cadastrado com este CPF.");
    }

    private void verificaSeExisteUmaPropostaRecente(Proposta proposta) {
        Optional<Proposta> ultimaProposta = propostaRepository.findUltimaProposta(proposta.getCpf());

        if (!ultimaProposta.isPresent())
            return;

        boolean isDepoisDoTempoMinimoParaNovaProposta1 = ultimaProposta
                .get()
                .getDataProposta()
                .isBefore(LocalDate.now().minusDays(TEMPO_MINIMO_PARA_NOVA_PROPOSTA));

        if (!isDepoisDoTempoMinimoParaNovaProposta1)
            throw new RuntimeException(
                    String.format("Há uma proposta para este CPF nos últimos %d dias.", TEMPO_MINIMO_PARA_NOVA_PROPOSTA));
    }
}
