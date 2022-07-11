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

