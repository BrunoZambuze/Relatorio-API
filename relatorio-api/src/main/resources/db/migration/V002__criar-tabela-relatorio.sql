CREATE TABLE relatorio (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    `status` VARCHAR(16),
    `data` DATETIME NOT NULL,
    id_usuario BIGINT NOT NULL
);

ALTER TABLE relatorio ADD CONSTRAINT fk_id_usuario
FOREIGN KEY (id_usuario) REFERENCES usuario(id);