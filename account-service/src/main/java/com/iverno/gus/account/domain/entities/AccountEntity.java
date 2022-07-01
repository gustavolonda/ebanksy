package com.iverno.gus.account.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.iverno.gus.commons.general.domain.entities.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class AccountEntity extends BaseEntity {
		@Id
	    @GeneratedValue(generator = "uuid2")
	    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	    @Column(name = "id", columnDefinition = "text")
	    private String id;
		@Column(name = "customer_id")
	    private String customerId;
		@Column(name = "account_num")
		private String accountNum;
		@Column(name = "account_type" , length = 1)
		private String accountType;
		@Column(name = "initial_balance")
		private String initialBalance;
}
