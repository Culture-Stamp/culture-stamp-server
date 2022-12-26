package com.culturestamp.back.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "todo_id")
	private Long id;
	private String content;
	private LocalDateTime date;

	@Column(name = "done_flag")
	@ColumnDefault("0") //default 0
	private int doneFlag;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Builder
	public Todo(Long id, String content, LocalDateTime date, int doneFlag, User user) {
		this.id = id;
		this.content = content;
		this.date = date;
		this.doneFlag = doneFlag;
		this.user = user;
	}


}
