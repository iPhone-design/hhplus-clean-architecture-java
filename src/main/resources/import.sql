-- 학생 초기 데이터
insert into student(user_id, user_name) values (1, '하준');
insert into student(user_id, user_name) values (2, '지우');
insert into student(user_id, user_name) values (3, '서연');
insert into student(user_id, user_name) values (4, '민준');
insert into student(user_id, user_name) values (5, '수아');
insert into student(user_id, user_name) values (6, '예준');
insert into student(user_id, user_name) values (7, '지민');
insert into student(user_id, user_name) values (8, '하윤');
insert into student(user_id, user_name) values (9, '서준');
insert into student(user_id, user_name) values (10, '유나');
insert into student(user_id, user_name) values (11, '도윤');
insert into student(user_id, user_name) values (12, '다인');
insert into student(user_id, user_name) values (13, '서진');
insert into student(user_id, user_name) values (14, '채원');
insert into student(user_id, user_name) values (15, '윤호');
insert into student(user_id, user_name) values (16, '지안');
insert into student(user_id, user_name) values (17, '현우');
insert into student(user_id, user_name) values (18, '나윤');
insert into student(user_id, user_name) values (19, '건우');
insert into student(user_id, user_name) values (20, '아린');
insert into student(user_id, user_name) values (21, '준호');
insert into student(user_id, user_name) values (22, '예린');
insert into student(user_id, user_name) values (23, '시우');
insert into student(user_id, user_name) values (24, '서율');
insert into student(user_id, user_name) values (25, '은서');
insert into student(user_id, user_name) values (26, '지호');
insert into student(user_id, user_name) values (27, '하린');
insert into student(user_id, user_name) values (28, '수현');
insert into student(user_id, user_name) values (29, '민서');
insert into student(user_id, user_name) values (30, '연우');
insert into student(user_id, user_name) values (31, '주원');
insert into student(user_id, user_name) values (32, '세연');

-- 강의 초기 데이터
insert into lecture(lecture_no, lecture_name) values (1, '수학');
insert into lecture(lecture_no, lecture_name) values (2, '영어');
insert into lecture(lecture_no, lecture_name) values (3, '국어');
insert into lecture(lecture_no, lecture_name) values (4, '한국사');
insert into lecture(lecture_no, lecture_name) values (5, '과학');
insert into lecture(lecture_no, lecture_name) values (6, '일본어');

-- 강의 스케쥴 초기 데이터
insert into lecture_schedule(schedule_no, lecture_no, open_date) values (1, 1, '2024-06-29 09:00:00');
insert into lecture_schedule(schedule_no, lecture_no, open_date) values (2, 2, '2024-06-29 13:00:00');