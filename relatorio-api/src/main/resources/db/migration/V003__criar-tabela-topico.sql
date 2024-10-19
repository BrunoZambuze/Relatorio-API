CREATE TABLE topico (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    descricao TEXT NOT NULL,
    `data` DATETIME NOT NULL,
    id_relatorio BIGINT NOT NULL
);

ALTER TABLE topico ADD CONSTRAINT fk_id_relatorio
FOREIGN KEY (id_relatorio) REFERENCES relatorio (id);