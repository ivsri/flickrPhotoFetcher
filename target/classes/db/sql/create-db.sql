CREATE TABLE users (
  id         INTEGER PRIMARY KEY,
  name VARCHAR(30),
  email  VARCHAR(50)
);

CREATE TABLE PHOTO (
	id NUMBER,
	owner VARCHAR(30),
	secret VARCHAR(30),
	server VARCHAR(30),
	farm VARCHAR(30),
	title VARCHAR(2000),
	ispublic VARCHAR(30),
	isfriend VARCHAR(30),
	isfamily VARCHAR(30),
	searchtag VARCHAR(200)
);