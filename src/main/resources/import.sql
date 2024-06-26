-- 학생 초기 데이터
insert into student(user_id, user_name) values (1, '홍길동');
insert into student(user_id, user_name) values (2, '임꺽정');
insert into student(user_id, user_name) values (3, '김유신');

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