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

DROP TABLE tasks;

CREATE TABLE tasks(
	task_id SERIAL PRIMARY KEY,
	task_name VARCHAR(255) NOT NULL,
	due_date DATE NOT NULL DEFAULT CURRENT_DATE,
	t_created TIMESTAMP,
	t_updated TIMESTAMP
);
--insert sql
INSERT INTO tasks(task_name, due_date, t_created, t_updated) 
VALUES('task_name0', '2020-05-30', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
--insert sql without duedate
INSERT INTO tasks(task_name, t_created, t_updated) 
VALUES('task_name2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
--insert sql without duedate, next day default
INSERT INTO tasks(task_name, due_date, t_created, t_updated) 
VALUES('task_name3', CURRENT_DATE+1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
--update sql
UPDATE tasks
SET task_name = 'TASK_NAME', due_date = 'DUE_DATE', t_updated = CURRENT_TIMESTAMP
WHERE task_id = 'TASK_ID';