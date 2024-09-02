package edu.kh.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Todo {
	
	private String listNo;
	private String title;
	private String detail;
	private boolean complete;
	private String regDate;
	private String color;
	
}
