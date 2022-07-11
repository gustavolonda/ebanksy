create table person (
       id  varchar(255) NOT NULL,
        status boolean default true,
        active boolean default true,
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
    active boolean default true,
	password varchar(255),
	person_id text,
    create_date timestamp,
	modify_date timestamp,
	primary key (id),
    CONSTRAINT FK_person_customer FOREIGN KEY (person_id)
    REFERENCES person(id)
);

    create table account (
       id varchar(255) not null,
        status boolean default true,
        active boolean default true,
        account_num varchar(255),
        account_type varchar(1),
        available_balance DOUBLE PRECISION,
        customer_id varchar(255),
        initial_balance DOUBLE PRECISION,
        create_date timestamp,
        modify_date timestamp,
        primary key (id),
    CONSTRAINT FK_customer_account FOREIGN KEY (customer_id)
    REFERENCES customer(id)
    );

    create table transaction (
       id varchar(255) not null,
        status boolean default true,
        active boolean default true,
        account_id varchar(255),
        available_balance DOUBLE PRECISION,
        transaction_type varchar(1),
        transaction_value DOUBLE PRECISION ,
        create_date timestamp,
        modify_date timestamp,
        primary key (id),
    CONSTRAINT FK_account_transaction FOREIGN KEY (account_id)
    REFERENCES account(id)
    ) ;
