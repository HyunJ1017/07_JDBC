package edu.kh.employee.service;

import java.util.List;
import java.util.Map;

import edu.kh.employee.dto.Emp;

public interface EmployeeService {

	/** 전체조회
	 * @return
	 */
	List<Emp> selectAll() throws Exception;

	/** id 검색
	 * @param empId
	 * @return
	 * @throws Exception
	 */
	Emp selectId(String empId) throws Exception;

	/** 부서코드검색
	 * @param deptCode
	 * @return
	 */
	Map<String, String> getDepartment(String deptCode) throws Exception;

	/** 잡코드 검색
	 * @param jobCode
	 * @return
	 * @throws Exception
	 */
	String getJobName(String jobCode) throws Exception;

	/** 로케이션검색
	 * @param locationId
	 * @return
	 */
	Map<String, String> getLocation(String locationId) throws Exception;

}
