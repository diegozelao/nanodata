package com.example.server.domain.xml;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Table(name = "notafiscal")
@Entity(name = "notafiscal")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class NotaFiscal {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String infNFeId;
    private String dhEmi;
    private String nNF;
    private String cUF;
    private String emitCNPJ;
    private String emitxFant;
    private String destCNPJ;
    private String destxFant;
    private BigDecimal vTotTrib;
    private BigDecimal vNF;


    public NotaFiscal(
            String infNFeId,
            String dhEmi,
            String nNF,
            String cUF,
            String emitCNPJ,
            String emitxFant,
            String destCNPJ,
            String destxFant,
            BigDecimal vTotTrib,
            BigDecimal vNF
    ) {
        this.infNFeId = infNFeId;
        this.dhEmi = dhEmi;
        this.nNF = nNF;
        this.cUF = cUF;
        this.emitCNPJ = emitCNPJ;
        this.emitxFant = emitxFant;
        this.destCNPJ = destCNPJ;
        this.destxFant = destxFant;
        this.vTotTrib = vTotTrib;
        this.vNF = vNF;

    }
}
