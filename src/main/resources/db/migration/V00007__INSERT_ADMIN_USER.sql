CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Создаем корзину и сохраняем ее id
WITH inserted_basket AS (
    INSERT INTO basket (price, id)
    VALUES (0, uuid_generate_v4())
    RETURNING id
),
-- Создаем изображение профиля и сохраняем его id
inserted_image AS (
    INSERT INTO image (id, path)
    VALUES (uuid_generate_v4(), 'images/user/profile/default_profile_image.png')
    RETURNING id
)
-- Вставляем пользователя, используя сохраненные id корзины и изображения профиля
INSERT INTO users (date_of_birthday, date_of_create, basket_id, id, profile_image_id, city, email, login, name, password, surname)
SELECT
    NOW() - INTERVAL '30 years', -- дата рождения (30 лет назад)
    NOW(), -- текущая дата и время
    ib.id, -- id корзины
    uuid_generate_v4(), -- сгенерированный UUID для пользователя
    ii.id, -- id изображения профиля
    'New York', -- город
    'admin@admin.com', -- email
    'admin', -- логин
    'admin', -- имя
    '$2a$05$3yeyk.yhPhHPuifqjEAL6e1mxsVaoUOjDPpemvVOKpatrhWBlbJ6y', -- хешированный пароль
    'admin' -- фамилия
FROM inserted_basket ib, inserted_image ii;


-- Вставляем записи для ролей пользователя
INSERT INTO user_role (role, users_id)
SELECT role_value, id
FROM (VALUES (1), (2)) AS role_values(role_value),
     (SELECT id FROM users ORDER BY date_of_create DESC LIMIT 1) AS last_user;
