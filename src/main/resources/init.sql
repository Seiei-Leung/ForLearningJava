SET NAMES utf8mb4;

DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS classes;

CREATE TABLE classes (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(10) NOT NULL,
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE students (
	id BIGINT NOT NULL AUTO_INCREMENT,
	class_id BIGINT NOT NULL,
	name VARCHAR(10) NOT NULL,
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO classes (name) VALUES ('一班');
INSERT INTO classes (name) VALUES ('二班');
INSERT INTO classes (name) VALUES ('三班');
INSERT INTO classes (name) VALUES ('四班');

INSERT INTO students (class_id, name) VALUES (1, '小明');
INSERT INTO students (class_id, name) VALUES (1, '小红');
INSERT INTO students (class_id, name) VALUES (1, '小刚');
INSERT INTO students (class_id, name) VALUES (2, '小智');
INSERT INTO students (class_id, name) VALUES (1, '小明');
INSERT INTO students (class_id, name) VALUES (1, '小红');
INSERT INTO students (class_id, name) VALUES (1, '小刚');
INSERT INTO students (class_id, name) VALUES (2, '小智');
INSERT INTO students (class_id, name) VALUES (1, '小明');
INSERT INTO students (class_id, name) VALUES (1, '小红');
INSERT INTO students (class_id, name) VALUES (1, '小刚');
INSERT INTO students (class_id, name) VALUES (2, '小智');
INSERT INTO students (class_id, name) VALUES (1, '小明');
INSERT INTO students (class_id, name) VALUES (1, '小红');
INSERT INTO students (class_id, name) VALUES (1, '小刚');
INSERT INTO students (class_id, name) VALUES (2, '小智');
INSERT INTO students (class_id, name) VALUES (1, '小明');
INSERT INTO students (class_id, name) VALUES (1, '小红');
INSERT INTO students (class_id, name) VALUES (1, '小刚');
INSERT INTO students (class_id, name) VALUES (2, '小智');
INSERT INTO students (class_id, name) VALUES (1, '小明');
INSERT INTO students (class_id, name) VALUES (1, '小红');
INSERT INTO students (class_id, name) VALUES (1, '小刚');
INSERT INTO students (class_id, name) VALUES (2, '小智');
INSERT INTO students (class_id, name) VALUES (1, '小明');
INSERT INTO students (class_id, name) VALUES (1, '小红');
INSERT INTO students (class_id, name) VALUES (1, '小刚');
INSERT INTO students (class_id, name) VALUES (2, '小智');
INSERT INTO students (class_id, name) VALUES (1, '小明');
INSERT INTO students (class_id, name) VALUES (1, '小红');
INSERT INTO students (class_id, name) VALUES (1, '小刚');
INSERT INTO students (class_id, name) VALUES (2, '小智');
INSERT INTO students (class_id, name) VALUES (1, '小明');
INSERT INTO students (class_id, name) VALUES (1, '小红');
INSERT INTO students (class_id, name) VALUES (1, '小刚');
INSERT INTO students (class_id, name) VALUES (2, '小智');
INSERT INTO students (class_id, name) VALUES (1, '小明');
INSERT INTO students (class_id, name) VALUES (1, '小红');
INSERT INTO students (class_id, name) VALUES (1, '小刚');
INSERT INTO students (class_id, name) VALUES (2, '小智');
INSERT INTO students (class_id, name) VALUES (1, '小明');
INSERT INTO students (class_id, name) VALUES (1, '小红');
INSERT INTO students (class_id, name) VALUES (1, '小刚');
INSERT INTO students (class_id, name) VALUES (2, '小智');
INSERT INTO students (class_id, name) VALUES (1, '小明');
INSERT INTO students (class_id, name) VALUES (1, '小红');
INSERT INTO students (class_id, name) VALUES (1, '小刚');
INSERT INTO students (class_id, name) VALUES (2, '小智');
INSERT INTO students (class_id, name) VALUES (1, '小明');
INSERT INTO students (class_id, name) VALUES (1, '小红');
INSERT INTO students (class_id, name) VALUES (1, '小刚');
INSERT INTO students (class_id, name) VALUES (2, '小智');
INSERT INTO students (class_id, name) VALUES (1, '小明');
INSERT INTO students (class_id, name) VALUES (1, '小红');
INSERT INTO students (class_id, name) VALUES (1, '小刚');
INSERT INTO students (class_id, name) VALUES (2, '小智');
INSERT INTO students (class_id, name) VALUES (1, '小明');
INSERT INTO students (class_id, name) VALUES (1, '小红');
INSERT INTO students (class_id, name) VALUES (1, '小刚');
INSERT INTO students (class_id, name) VALUES (2, '小智');
