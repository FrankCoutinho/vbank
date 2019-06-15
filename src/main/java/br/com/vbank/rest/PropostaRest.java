package br.com.vbank.rest;

import br.com.vbank.domain.Proposta;
import br.com.vbank.dtos.AprovarPropostaDto;
import br.com.vbank.dtos.RejeitarPropostaDto;
import br.com.vbank.services.PropostaService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/propostas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PropostaRest {

    @EJB
    private PropostaService propostaService;

    @POST
    public Proposta criar(Proposta proposta) {
        return propostaService.save(proposta);
    }

    @GET
    @Path("/{id}")
    public Proposta getById(@PathParam("id") Long id) {
        return propostaService.getById(id);
    }

    @GET
    public List<Proposta> getAll(@DefaultValue("") @QueryParam("regiao") String regiao) {
        return propostaService.findPropostasAbertasByRegiao(regiao);
    }

    @PUT
    @Path("/{id}/rejeitar")
    public Proposta rejeitarProposta(
        @PathParam("id") Long idProposta,
        RejeitarPropostaDto rejeitarPropostaDto
    ) {
        return propostaService.rejeitarProposta(idProposta, rejeitarPropostaDto);
    }

    @PUT
    @Path("/{id}/aprovar")
    public Proposta aprovarProposta(
        @PathParam("id") Long idProposta,
        AprovarPropostaDto aprovarPropostaDto
    ) {
        return propostaService.aprovarProposta(idProposta, aprovarPropostaDto);
    }
}
