CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE usuario (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    role VARCHAR(30) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT uk_usuario_nome UNIQUE (nome),
    CONSTRAINT ck_usuario_role CHECK (role IN ('ADMIN', 'USUARIO'))
);

CREATE TABLE pedido (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome_cliente VARCHAR(255),
    observacao TEXT,
    status VARCHAR(30),
    timestamp TIMESTAMP,
    usuario_id UUID,
    CONSTRAINT fk_pedido_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuario (id)
);

CREATE TABLE arquivo_fotos (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tamanho VARCHAR(255),
    papel VARCHAR(255),
    path_arquivos VARCHAR(255),
    pedido_id UUID,
    CONSTRAINT fk_arquivo_fotos_pedido
        FOREIGN KEY (pedido_id) REFERENCES pedido (id)
        ON DELETE CASCADE
);

CREATE INDEX idx_pedido_usuario_id ON pedido (usuario_id);
CREATE INDEX idx_arquivo_fotos_pedido_id ON arquivo_fotos (pedido_id);
