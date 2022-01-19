package com.neosoft.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max = 65)
	private String firstName;
	
	@NotNull
	@Size(max = 65)
	private String lastName;
	
	@NotNull
	@Size(max = 10)
	private String mobile;
	
	@NotNull
	@Size(max=50)
	private String email;
	
	

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="projid",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Project project;

}
