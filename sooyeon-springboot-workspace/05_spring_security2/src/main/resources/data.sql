INSERT INTO tbl_user(user_id, user_pwd, user_name, user_role)
VALUES ('admin', '$2a$10$oSBnusekTD/MphvnZMKiqesdRQBlHIrGxmHT0XOdMY3msERCfYTAi', '관리자', 'ADMIN');

INSERT INTO tbl_user(user_id, user_pwd, user_name, user_role)
VALUES ('user01', '$2a$10$EiNlRIhSYlvXSHZGY3XEE.dqb5wdHjfnVqvHtnVJ6C/EY15xYv4ne', '일반인1', 'USER');

INSERT INTO tbl_user(user_id, user_pwd, user_name, user_role)
VALUES ('user02', '$2a$10$m7o.PlxS32cas6y8WzXOH.aIL0uFxrCH4hBgfffgzzSNtme6cf/Qe', '일반인2', 'USER');

COMMIT;