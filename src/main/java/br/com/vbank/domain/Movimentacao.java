package br.com.vbank.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacao")
public class Movimentacao  extends BaseEntity implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "cliente_id",
        nullable = false,
        updatable = false,
        insertable = false,
        referencedColumnName = "id"
    )
    private Cliente remetente;

    @Column(name = "valor", precision = 14, scale = 2, nullable = false)
    private BigDecimal valor;

    @Column(name = "efetuada_em", nullable = false)
    private LocalDateTime efetuadaEm;

    @Column(name = "destino", length = 100, nullable = false)
    private String destino;
}
