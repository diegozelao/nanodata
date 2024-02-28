CREATE TABLE notafiscal (
    id SERIAL PRIMARY KEY,
    infnfe_id NCHAR(200) UNIQUE,
    dh_emi NCHAR(200),
    nNF NCHAR(200),
    cUF NCHAR(200),
    emitCNPJ NCHAR(200),
    emitx_fant NCHAR(200),
    destCNPJ NCHAR(200),
    destx_fant NCHAR(200),
    v_tot_trib DECIMAL(13,2) NOT NULL,
    vNF DECIMAL(13,2) NOT NULL
);

CREATE TABLE xml_info (
    id SERIAL PRIMARY KEY,
    id_nfe NCHAR(200),
    xml BYTEA
);

ALTER TABLE xml_info
ADD FOREIGN KEY (id_nfe) REFERENCES notafiscal (infnfe_id); -- Use FOREIGN KEY clause directly