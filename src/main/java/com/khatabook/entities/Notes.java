package com.khatabook.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

@Entity
public class Notes {
	@Id
	 @GeneratedValue(generator = "uuid2")
	 @GenericGenerator(name = "uuid2", strategy = "uuid2")
	 @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
	 @Type(type = "uuid-char")
	private UUID id;
	
	@Column(columnDefinition = "varchar(255)", nullable=false)
	private String title;
	
	@Column(columnDefinition = "varchar(255)", nullable=false)
	private String description;
	
	@Column(columnDefinition = "varchar(255) default 'General'")
	private String tag;
	
	@Column(columnDefinition = "boolean default false")
	private Boolean important;
	
	@Column(columnDefinition = "boolean default false")
	private Boolean completed;
	
	@CreationTimestamp
	@Column (nullable = false, updatable = false,columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP" )
	private LocalDateTime createdDate;
	
	@ManyToOne( optional = false)
	@JoinColumn(name = "user", nullable = false,referencedColumnName = "id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Boolean getImportant() {
		return important;
	}

	public void setImportant(Boolean important) {
		this.important = important;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public LocalDateTime getDate() {
		return createdDate;
	}

	public void setDate(LocalDateTime date) {
		this.createdDate = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Notes(UUID id, String title, String description, String tag, Boolean important, Boolean completed,
		LocalDateTime date, User user) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.tag = tag;
		this.important = important;
		this.completed = completed;
		this.createdDate = date;
		this.user = user;
	}

	public Notes() {
		super();
	}
}
