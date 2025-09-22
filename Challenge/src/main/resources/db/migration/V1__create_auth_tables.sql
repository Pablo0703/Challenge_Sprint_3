-- ======================================
-- Tabela de usuários
-- ======================================
CREATE TABLE Mottu_users (
                             id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                             username VARCHAR2(100) NOT NULL UNIQUE,
                             password VARCHAR2(200) NOT NULL,
                             enabled NUMBER(1) DEFAULT 1 NOT NULL
);

-- ======================================
-- Tabela de perfis (roles)
-- ======================================
CREATE TABLE Mottu_perfil (
                              id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                              nome VARCHAR2(50) NOT NULL UNIQUE
);

-- ======================================
-- Tabela de associação User x Perfil (N:N)
-- ======================================
CREATE TABLE Mottu_user_perfil (
                                   user_id NUMBER NOT NULL,
                                   perfil_id NUMBER NOT NULL,
                                   CONSTRAINT pk_user_perfil PRIMARY KEY (user_id, perfil_id),
                                   CONSTRAINT fk_user_perfil_user FOREIGN KEY (user_id) REFERENCES Mottu_users(id) ON DELETE CASCADE,
                                   CONSTRAINT fk_user_perfil_perfil FOREIGN KEY (perfil_id) REFERENCES Mottu_perfil(id) ON DELETE CASCADE
);
