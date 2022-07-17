DROP TABLE IF EXISTS patients;

create table patients (
	id INT,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	age INT,
	last_visit_date DATE,
	created_at DATE,
	updated_at DATE
);
