CREATE TABLE Person
(
    id            integer generated by default as identity primary key,
    username      varchar(100) not null,
    year_of_birth integer      not null,
    password      varchar      not null,
    role          varchar(100) not null
);

CREATE TABLE Facility
(
    id           int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    country      varchar(100) NOT NULL,
    city         varchar(100) NOT NULL,
    postcode     varchar(100),
    street       varchar(100) NOT NULL,
    house_number varchar(100),
    notes        varchar(10000)
);

CREATE TABLE Client
(
    id           int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name         varchar(100) NOT NULL,
    lastname     varchar(100),
    add_info     varchar(100),
    phone_number varchar(15)  NOT NULL,
    email        varchar (100)
);

CREATE TABLE Facility_Client
(
    facility_id int REFERENCES Facility (id),
    client_id   int REFERENCES Client (id),
    PRIMARY KEY (facility_id, client_id)
);


CREATE TABLE Premise
(
    id          int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    facility_id int REFERENCES Facility (id) ON DELETE CASCADE,
    name        varchar(100) NOT NULL,
    notes       varchar(10000)
);


CREATE TABLE Task
(
    id          int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    facility_id int REFERENCES Facility (id) ON DELETE CASCADE,
    premises_id int REFERENCES Premise (id) ON DELETE CASCADE,
    text        varchar(1000) NOT NULL,
    is_done     boolean       NOT NULL
);


CREATE TABLE Photo
(
    id          int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    facility_id int REFERENCES Facility (id) ON DELETE CASCADE,
    premises_id int REFERENCES Premise (id) ON DELETE CASCADE,
    link        varchar(1000) NOT NULL
);