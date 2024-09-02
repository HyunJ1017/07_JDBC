package edu.kh.employee.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import edu.kh.employee.dto.Emp;

public interface EmployeeDAO {

	/** 전체조회
	 * @return
	 * @throws Exception
	 */
	List<Emp> selectAll(Connection conn) throws Exception;

	/**아이디 검색
	 * @param conn
	 * @param empId
	 * @return
	 * @throws Exception
	 */
	Emp selectId(Connection conn, String empId) throws Exception;

	/** 부서코드검색
	 * @param conn
	 * @param deptCode
	 * @return
	 * @throws Exception
	 */
	Map<String, String> getDepartment(Connection conn, String deptCode) throws Exception;

	/** 잡코드 검색
	 * @param conn
	 * @param jobCode
	 * @return
	 * @throws Exception
	 */
	String getJobName(Connection conn, String jobCode) throws Exception;

	/** 로케이션코드검색
	 * @param conn
	 * @param locationId
	 * @return
	 */
	Map<String, String> getLocation(Connection conn, String locationId) throws Exception;

}
