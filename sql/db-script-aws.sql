-- Database: stack-hack

-- DROP DATABASE "stack-hack";

CREATE DATABASE "stack-hack"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

COMMENT ON DATABASE "stack-hack"
    IS 'stack-hack database';

CREATE SCHEMA v3;

DROP TABLE v3.user;
CREATE TABLE v3.user(
	user_id SERIAL PRIMARY KEY,
	user_name VARCHAR(10) NOT NULL,
	password VARCHAR(8) NOT NULL UNIQUE,
	t_created TIMESTAMP,
	t_updated TIMESTAMP
);

INSERT INTO v3.user(user_name, password, t_created, t_updated) 
VALUES('admin', 'root', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO v3.user(user_name, password, t_created, t_updated) 
VALUES('user1', 'pass1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

DROP TABLE v3.task;
CREATE TABLE v3.task(
	task_id SERIAL PRIMARY KEY,
	task_name VARCHAR(255) NOT NULL,
	due_date DATE NOT NULL DEFAULT CURRENT_DATE,
	label_code INTEGER NOT NULL DEFAULT 4,
	status_code INTEGER NOT NULL DEFAULT 0,
	t_created TIMESTAMP,
	t_updated TIMESTAMP,
	user_id SERIAL REFERENCES v3.user(user_id)
);

--insert sql
INSERT INTO v3.task(task_name, due_date, label_code, status_code, t_created, t_updated, user_id) 
VALUES('task_name0', '2020-06-04', 4, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);
--insert sql without duedate, label_code, status_code
INSERT INTO v3.task(task_name, t_created, t_updated, user_id) 
VALUES('task_name1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2);
--insert sql without duedate, next day default
INSERT INTO v3.task(task_name, due_date, t_created, t_updated, user_id) 
VALUES('task_name2', CURRENT_DATE+1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2);
--update sql
UPDATE v3.task
SET task_name = '<TASK_NAME>', due_date = '<DUE_DATE>', label_code = '<LABEL_CODE>', status_code = '<STATUS_CODE>', t_updated = CURRENT_TIMESTAMP, 
WHERE task_id = '<TASK_ID>'