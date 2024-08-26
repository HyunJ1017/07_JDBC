package edu.kh.jdbc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EMPLOYEE {
	
	private String empId;
	private String empName;
	private String empNo;
	private String deptCode;
	private int    salary;
	
}
