-- Database: stack-hack

-- DROP DATABASE "stack-hack";

CREATE DATABASE "stack-hack"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

COMMENT ON DATABASE "stack-hack"
    IS 'stack-hack database';

CREATE SCHEMA v2;

DROP TABLE v2.task;

CREATE TABLE v2.task(
	task_id SERIAL PRIMARY KEY,
	task_name VARCHAR(255) NOT NULL,
	due_date DATE NOT NULL DEFAULT CURRENT_DATE,
	label_code INTEGER NOT NULL DEFAULT 4,
	status_code INTEGER NOT NULL DEFAULT 0,
	t_created TIMESTAMP,
	t_updated TIMESTAMP
);

--insert sql
INSERT INTO v2.task(task_name, due_date, label_code, status_code, t_created, t_updated) 
VALUES('task_name0', '2020-06-04', 4, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
--insert sql without duedate, label_code, status_code
INSERT INTO v2.task(task_name, t_created, t_updated) 
VALUES('task_name1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
--insert sql without duedate, next day default
INSERT INTO v2.task(task_name, due_date, t_created, t_updated) 
VALUES('task_name2', CURRENT_DATE+1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
--update sql
UPDATE v2.task
SET task_name = '<TASK_NAME>', due_date = '<DUE_DATE>', label_code = '<LABEL_CODE>', status_code = '<STATUS_CODE>', t_updated = CURRENT_TIMESTAMP, 
WHERE task_id = '<TASK_ID>'