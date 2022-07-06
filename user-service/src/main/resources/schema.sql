create table person (
       id  varchar(255) NOT NULL,
        status boolean default true,
        address varchar(255),
        age integer not null,
        gender varchar(255),
        id_card varchar(255),
        name varchar(255),
        phone varchar(255),
        create_date timestamp,
        modify_date timestamp,
        primary key (id)
    );
    
create table customer (
	id  varchar(255) NOT NULL,
	status boolean default true,
	password varchar(255),
	person_id text,
    create_date timestamp,
	modify_date timestamp,
	primary key (id),
    CONSTRAINT FK_person_customer FOREIGN KEY (person_id)
    REFERENCES person(id)
);
