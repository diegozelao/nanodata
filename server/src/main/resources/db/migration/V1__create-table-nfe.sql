CREATE TABLE notafiscal (
    id SERIAL PRIMARY KEY,
    infnfe_id VARCHAR(47) UNIQUE,
    dh_emi VARCHAR(200),
    nNF VARCHAR(14),
    cUF VARCHAR(2),
    emitCNPJ VARCHAR(14),
    emitx_fant VARCHAR(200),
    destCNPJ VARCHAR(14),
    destx_fant VARCHAR(200),
    v_tot_trib DECIMAL(13,2) NOT NULL,
    vNF DECIMAL(13,2) NOT NULL
);

CREATE TABLE xml_info (
    id SERIAL PRIMARY KEY,
    id_nfe VARCHAR(47),
    xml BYTEA
);

ALTER TABLE xml_info
ADD FOREIGN KEY (id_nfe) REFERENCES notafiscal (infnfe_id); -- Use FOREIGN KEY clause directly