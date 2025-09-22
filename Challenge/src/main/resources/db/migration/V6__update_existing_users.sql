-- Atualiza o admin com valores válidos
UPDATE MOTTU_USERS
SET NAME = 'Administrador', EMAIL = 'admin@admin.com'
WHERE USERNAME = 'admin';

-- Atualiza o usuário comum com valores válidos
UPDATE MOTTU_USERS
SET NAME = 'Usuário Padrão', EMAIL = 'user@user.com'
WHERE USERNAME = 'user';
