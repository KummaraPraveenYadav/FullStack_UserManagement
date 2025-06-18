package com.example.simple.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "todos")
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@Column
	private String name;

	
	@Column() 
	private boolean isCompleted;

	
	@Column() 
	private String description;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column() 
	private Date startTime;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column() 
	private Date endTime;

	
	//@ManyToOne
	//@JoinColumn(name="userId", nullable = false)
	//private UserEntity userEntity;

	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public boolean getIsCompleted() {
		return isCompleted;
	}


	public void setIsCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getStartTime() {
		return startTime;
	}


	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}


	public Date getEndTime() {
		return endTime;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


//	public UserEntity getUserEntity() {
//		return userEntity;
//	}
//
//
//	public void setUserEntity(UserEntity userEntity) {
//		this.userEntity = userEntity;
//	}


	@Override
	public String toString() {
		return "TodoEntity [id=" + id + ", name=" + name + ", isCompleted=" + isCompleted + ", description="
				+ description + ", startTime=" + startTime + ", endTime=" + endTime 
				+ "]";
	}


	public Todo(long id, String name, boolean isCompleted, String description, Date startTime, Date endTime) {
		super();
		this.id = id;
		this.name = name;
		this.isCompleted = isCompleted;
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
//		this.userEntity = userEntity;
	}


	public Todo() {
		super();
	}

}
