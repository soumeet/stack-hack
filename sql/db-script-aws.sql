-- Database: stack-hack

-- DROP DATABASE "stack-hack";

CREATE DATABASE "stack-hack"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

COMMENT ON DATABASE "stack-hack"
    IS 'stack-hack database';

CREATE SCHEMA v1;

DROP TABLE v1.task;

CREATE TABLE v1.task(
	task_id SERIAL PRIMARY KEY,
	task_name VARCHAR(255) NOT NULL,
	due_date DATE NOT NULL DEFAULT CURRENT_DATE,
	t_created TIMESTAMP,
	t_updated TIMESTAMP
);
--insert sql
INSERT INTO v1.task(task_name, due_date, t_created, t_updated) 
VALUES('task_name0', '2020-05-30', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
--insert sql without duedate
INSERT INTO v1.task(task_name, t_created, t_updated) 
VALUES('task_name1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
--insert sql without duedate, next day default
INSERT INTO v1.task(task_name, due_date, t_created, t_updated) 
VALUES('task_name2', CURRENT_DATE+1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
--update sql
UPDATE tasks
SET task_name = 'TASK_NAME', due_date = 'DUE', t_updated = CURRENT_TIMESTAMP
WHERE task_id = 'TASK_ID'