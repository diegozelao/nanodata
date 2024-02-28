package com.example.server.domain.xml;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "xml_info")
@Entity(name = "xml_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class XmlInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idNfe;
    private byte[] xml;

    public XmlInfo(String infNFeId, byte[] bytes) {
        this.idNfe = infNFeId;
        this.xml = bytes;
    }
}
