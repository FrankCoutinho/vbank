package br.com.vbank.domain;

import br.com.vbank.dtos.EditarHorarioDeTransacoesRequest;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Entity
@Getter
@Table(name = "horario_de_transacoes")
public class HorarioDeTransacoes extends BaseEntity implements Serializable {
	
	@Column(name = "horario_de_inicio", nullable = false)
	private LocalDateTime horarioDeInicio;
	
	@Column(name = "horario_de_termino", nullable = false)
	private LocalDateTime horarioDeTermino;

    public void atualizar(EditarHorarioDeTransacoesRequest request) {
        this.horarioDeInicio = request.horarioDeInicio;
        this.horarioDeTermino = request.horarioDeTermino;
    }

    public boolean isAbertoParaTransacoes() {

        if (now().isAfter(horarioDeTermino))
            return false;

        if (now().isBefore(horarioDeInicio))
            return false;

        return true;
    }
}
