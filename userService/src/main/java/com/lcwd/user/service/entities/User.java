package com.lcwd.user.service.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.lang.NonNullFields;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="micro_users")
public class User {

	@Id
	@Column(name="ID")
	private String userId;
	@Column(name = "NAME",length = 20)
	private String name;
	@Column(name = "EMAIL")
//	@JsonIgnore
	private String email;
	@Column(name = "ABOUT")
//	@JsonInclude(Include.NON_NULL)
	private String about;
	
	@Transient     //used for not saving into the databases
	private List<Rating> ratings=new ArrayList<>(); //without arraylist we will get "null" but if we use then get "[]"
   
	

}
