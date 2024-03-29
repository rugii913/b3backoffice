insert into app_user
    (created_at, deleted_at, last_modified_at, password, email, introduction, nickname, real_name, role, username, id)
values
    ('2024-01-23T15:17:35.242610400', null, '2024-01-23T15:17:35.243574700',
        '$2a$10$FztQozehoZx4SySGf/ZequuMZhCmNqVVJZRUgRolQwCjUNtkLNwfe', 'string', 'string', 'string', 'string',
        'ADMIN', 'test1111', default), -- id 1번, ADMIN 역할, username: test1111, password: test111!
    ('2024-01-24T15:17:35.242610400', null, '2024-01-24T15:17:35.243574700',
        '$2a$10$/FcMF/KKrK6.rjfvVn7oROfsFbvj96.JOWGt5O.wvxpxQUJJBl3tm', 'string', 'string', 'string', 'string',
        'COMMON', 'test2222', default), -- id 2번, COMMON 역할, username: test2222, password: test222@
    ('2024-01-25T15:17:35.242610400', null, '2024-01-25T15:17:35.243574700',
        '$2a$10$gWc9DyM61k8OXPt8/qOzI.3zOm2CGh3FWEANOKOYuQwLbYs5uP8a2', 'string', 'string', 'string', 'string',
        'COMMON', 'test3333', default); -- id 3번, COMMON 역할, username: test3333, password: test333#


insert into past_password
    (id, user_id, past_password_latest, past_password_midst, past_password_oldest)
values
    (default, 1, '$2a$10$FztQozehoZx4SySGf/ZequuMZhCmNqVVJZRUgRolQwCjUNtkLNwfe', '', ''),
    (default, 2, '$2a$10$/FcMF/KKrK6.rjfvVn7oROfsFbvj96.JOWGt5O.wvxpxQUJJBl3tm', '', ''),
    (default, 3, '$2a$10$gWc9DyM61k8OXPt8/qOzI.3zOm2CGh3FWEANOKOYuQwLbYs5uP8a2', '', '');

insert into review
    (address, cleanliness, content, count_heart, created_at, cuisine_category, deleted_at, last_modified_at, store_name, store_size, user_id, visited_at, waiting_time, id)
values
    ('주소1', 3, '내용1', 0, '2024-01-15T15:35:22.371317', 'KOREAN', null, '2024-01-25T15:35:22.371317', '가게 이름1', 3, 1, '2024-01-15T15:35:22.371317', 10, default),
    ('주소2', 3, '내용2', 0, '2024-01-16T15:35:22.371317', 'KOREAN', null, '2024-01-25T15:35:22.371317', '가게 이름1', 3, 1, '2024-01-15T15:35:22.371317', 10, default),
    ('주소3', 3, '내용3', 0, '2024-01-17T15:35:22.371317', 'KOREAN', null, '2024-01-25T15:35:22.371317', '가게 이름1', 3, 1, '2024-01-15T15:35:22.371317', 10, default),
    ('주소4', 3, '내용4', 0, '2024-01-18T15:35:22.371317', 'KOREAN', null, '2024-01-25T15:35:22.371317', '가게 이름1', 3, 1, '2024-01-15T15:35:22.371317', 10, default),
    ('주소5', 3, '내용5', 0, '2024-01-19T15:35:22.371317', 'KOREAN', null, '2024-01-25T15:35:22.371317', '가게 이름1', 3, 1, '2024-01-15T15:35:22.371317', 10, default),
    ('주소6', 3, '내용6', 0, '2024-01-20T15:35:22.371317', 'KOREAN', null, '2024-01-25T15:35:22.371317', '가게 이름1', 3, 1, '2024-01-15T15:35:22.371317', 10, default),
    ('주소7', 3, '내용7', 0, '2024-01-21T15:35:22.371317', 'KOREAN', null, '2024-01-25T15:35:22.371317', '가게 이름1', 3, 1, '2024-01-15T15:35:22.371317', 10, default),
    ('주소8', 3, '내용8', 0, '2024-01-22T15:35:22.371317', 'KOREAN', null, '2024-01-25T15:35:22.371317', '가게 이름1', 3, 1, '2024-01-15T15:35:22.371317', 10, default),
    ('주소9', 3, '내용9', 0, '2024-01-23T15:35:22.371317', 'KOREAN', null, '2024-01-25T15:35:22.371317', '가게 이름1', 3, 1, '2024-01-15T15:35:22.371317', 10, default),
    ('주소10', 3, '내용10', 0, '2024-01-24T15:35:22.371317', 'KOREAN', null, '2024-01-25T15:35:22.371317', '가게 이름1', 3, 1, '2024-01-15T15:35:22.371317', 10, default),
    ('주소11', 3, '내용11', 0, '2024-01-25T15:35:22.371317', 'KOREAN', null, '2024-01-25T15:35:22.371317', '가게 이름1', 3, 1, '2024-01-15T15:35:22.371317', 10, default);

insert into comment
    (content, created_at, deleted_at, last_modified_at, review_id, user_id, id)
values
    ('첫번째 글 댓글 내용1', '2024-01-25T15:20:28.274541', null, '2024-01-25T15:46:28.274541', 1, 1, default),
    ('첫번째 글 댓글 내용2', '2024-01-25T15:21:28.274541', null, '2024-01-25T15:46:28.274541', 1, 2, default),
    ('첫번째 글 댓글 내용3', '2024-01-25T15:22:28.274541', null, '2024-01-25T15:46:28.274541', 1, 1, default),
    ('첫번째 글 댓글 내용4', '2024-01-25T15:23:28.274541', null, '2024-01-25T15:46:28.274541', 1, 3, default),
    ('첫번째 글 댓글 내용5', '2024-01-25T15:24:28.274541', null, '2024-01-25T15:46:28.274541', 1, 1, default),
    ('첫번째 글 댓글 내용6', '2024-01-25T15:25:28.274541', null, '2024-01-25T15:46:28.274541', 1, 1, default),
    ('첫번째 글 댓글 내용7', '2024-01-25T15:26:28.274541', null, '2024-01-25T15:46:28.274541', 1, 2, default),
    ('첫번째 글 댓글 내용8', '2024-01-25T15:27:28.274541', null, '2024-01-25T15:46:28.274541', 1, 1, default),
    ('첫번째 글 댓글 내용9', '2024-01-25T15:28:28.274541', null, '2024-01-25T15:46:28.274541', 1, 3, default),
    ('첫번째 글 댓글 내용10', '2024-01-25T15:29:28.274541', null, '2024-01-25T15:46:28.274541', 1, 1, default),
    ('첫번째 글 댓글 내용11', '2024-01-25T15:30:28.274541', null, '2024-01-25T15:46:28.274541', 1, 1, default),
    ('두번째 글 댓글 내용1', '2024-01-25T15:40:28.274541', null, '2024-01-25T15:46:28.274541', 2, 1, default),
    ('두번째 글 댓글 내용2', '2024-01-25T15:41:28.274541', null, '2024-01-25T15:46:28.274541', 2, 1, default),
    ('두번째 글 댓글 내용3', '2024-01-25T15:42:28.274541', null, '2024-01-25T15:46:28.274541', 2, 2, default),
    ('두번째 글 댓글 내용4', '2024-01-25T15:43:28.274541', null, '2024-01-25T15:46:28.274541', 2, 3, default),
    ('두번째 글 댓글 내용5', '2024-01-25T15:44:28.274541', null, '2024-01-25T15:46:28.274541', 2, 1, default);

insert into notice
    (content, created_at, deleted_at, last_modified_at, title, user_id, id)
values
    ('공지 내용1', '2024-01-25T15:40:28.274541', null, '2024-01-25T15:40:28.274541', '공지 제목1', 1, default),
    ('공지 내용2', '2024-01-25T15:41:28.274541', null, '2024-01-25T15:41:28.274541', '공지 제목2', 1, default),
    ('공지 내용3', '2024-01-25T15:42:28.274541', null, '2024-01-25T15:42:28.274541', '공지 제목3', 1, default);
