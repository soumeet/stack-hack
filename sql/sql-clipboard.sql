DELETE 
FROM v2.task
WHERE task_id in (24)

SELECT *
FROM v2.task

INSERT INTO v2.task(task_name, due_date, label_code, status_code, t_created, t_updated) 
VALUES('task_name5', '2020-06-03', 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);