insert into app_user(id, created_at, last_modified_at, username, password, email, real_name, nickname, role, introduction)
values (1, CURRENT_TIMESTAMP(6) - 10, CURRENT_TIMESTAMP(6) - 10, 'admin1', 'AdminTest1!', 'admin1@test.com', '운영자1', '운영자1', 'ADMIN', '운영자입니다.'),
       (2, CURRENT_TIMESTAMP(6) - 5, CURRENT_TIMESTAMP(6) - 5, 'common1', 'CommonTest1!', 'common1@test.com', '실명인', '유저1', 'COMMON', '일반 사용자1입니다.'),
       (3, CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6), 'common2', 'CommonTest1!', 'common2@test.com', '가명인', '유저2', 'COMMON', '일반 사용자1입니다.');
