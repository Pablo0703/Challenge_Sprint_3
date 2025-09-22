-- Adiciona as colunas sem NOT NULL (para evitar erro de tabela já populada)
ALTER TABLE MOTTU_USERS ADD (NAME VARCHAR2(150));
ALTER TABLE MOTTU_USERS ADD (EMAIL VARCHAR2(150) UNIQUE);
