package edu.kh.employee.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Emp {

	private String empId;
	private String empName;
	private String empNo;
	private String email;
	private String phone;
	private String deptCode;
	private String jobCode;
	private String salaryLevel;
	private int    salary;
	private float  bonus;
	private String managerId;
	private String hireDate;
	private String entDate;
	private String entYN;		// 퇴직여부

}
