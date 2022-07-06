    create table account (
       id varchar(255) not null,
        status boolean default true,
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

