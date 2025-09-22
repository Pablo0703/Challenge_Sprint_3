-- Inserindo admin padrão
INSERT INTO Mottu_users (username, password, enabled)
VALUES ('admin', '{bcrypt}$2a$10$Dow1rBlG9m93uEfbMmdOduz6vjO7VQYRtZpmjHtkobaN6D2PfYZ7i', 1);
-- senha = "admin123"

-- Relacionar admin com ROLE_ADMIN
INSERT INTO Mottu_user_perfil (user_id, perfil_id)
VALUES (
           (SELECT id FROM Mottu_users WHERE username = 'admin'),
           (SELECT id FROM Mottu_perfil WHERE nome = 'ADMIN')
       );
