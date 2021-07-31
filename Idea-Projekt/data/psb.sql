CREATE TABLE IF NOT EXISTS User
(
	ID INTEGER not null
		constraint User_pk
			primary key autoincrement,
	Username varchar(255) not null,
	EMail varchar(255) not null,
	Password varchar(255) not null
);

CREATE TABLE IF NOT EXISTS Note
(
	ID INTEGER
		constraint Note_pk
			primary key autoincrement,
	UserID INTEGER
		references User
			on delete cascade,
	Title VARCHAR(255),
	Content TEXT,
	LastEditedOn INTEGER NOT NULL DEFAULT CURRENT_TIMESTAMP,
	CreatedOn INTEGER NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS NoteShare
(
	ID INTEGER
		constraint NoteShare_pk
			primary key autoincrement,
	UserID INTEGER
		references User
			on delete cascade,
	NoteID INTEGER
		references Note
			on delete cascade,
    UNIQUE (UserID, NoteID)
);

CREATE TABLE IF NOT EXISTS Pet
(
	ID INTEGER
		constraint Pet_pk
			primary key autoincrement,
	Name Varchar(255) not null,
	Emotion VARCHAR(255) DEFAULT 'Happy' not null,
	UserID INTEGER
		references User
			on delete cascade
);

CREATE TABLE IF NOT EXISTS ToDoList
(
	ID INTEGER
		constraint ToDoList_pk
			PRIMARY KEY AUTOINCREMENT,
	UserID INTEGER
		REFERENCES User
			ON DELETE CASCADE,
	Title VARCHAR(255) UNIQUE,
	Flagged BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS ToDoListShare
(
	ID INTEGER
		constraint ToDoListShare_pk
			primary key autoincrement,
	UserID INTEGER not null
		references User
			on delete cascade,
	ToDoListID integer
		references ToDoList
			on delete cascade,
	UNIQUE (UserID, ToDoListID)
);

CREATE TABLE IF NOT EXISTS Task
(
	ID INTEGER
		CONSTRAINT Task_pk
			PRIMARY KEY AUTOINCREMENT,
	ToDoListID INTEGER
		REFERENCES ToDoList
			ON DELETE CASCADE,
	Content TEXT NOT NULL,
	Until INTEGER NOT NULL,
	AssignedTo INTEGER DEFAULT NULL
		references User
			ON DELETE CASCADE
);

CREATE UNIQUE INDEX User_Username_uindex
	ON User (Username);


-- Insert test data
INSERT INTO User (ID, Username, EMail, Password) VALUES (101, 't', 't', '8efd86fb78a56a5145ed7739dcb00c78581c5375');
INSERT INTO User (ID, Username, EMail, Password) VALUES (102, 'e', 'e', '58e6b3a414a1e090dfc6029add0f3555ccba127f');

INSERT INTO ToDoList (ID, UserID, Title) VALUES (101, 101, 'Uni');
INSERT INTO ToDoList (ID, UserID, Title, Flagged) VALUES (102, 101, 'Arbeit', true);

INSERT INTO Task (ToDoListID, Content, Until) VALUES (101, 'Mathe Übungsblatt 4', CURRENT_DATE);
INSERT INTO Task (ToDoListID, Content, Until) VALUES (101, 'Chinesisch Lektion 13', CURRENT_DATE);
INSERT INTO Task (ToDoListID, Content, Until) VALUES (102, 'Geld verdienen', CURRENT_DATE);

INSERT INTO Note (UserID, Title, Content) VALUES (101, 'Notiz', 'Hier könnte Ihre Werbung stehen!');
INSERT INTO Note (UserID, Title, Content) VALUES (101, 'Notion', 'Switch to Notion');
INSERT INTO Note (UserID, Title, Content) VALUES (101, 'Lorem ipsum', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.');
INSERT INTO Note (ID, UserID, Title, Content) VALUES (200, 102, 'Shared note', 'This is a shared note.');

INSERT INTO NoteShare (UserID, NoteID) VALUES (101, 200);

INSERT INTO Pet (Name, UserID) VALUES ('Peach', 101);
