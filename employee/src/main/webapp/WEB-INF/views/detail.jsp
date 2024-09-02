<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>html문서 제목</title>
</head>
<body>
  
  <header>
  <h1>사원번호 ${emp.empId}</h1>
  
  </header>

  <main>
  
    <table border="1">
    
      <tr>
        <td>사번</td>
        <td>${emp.empId}</td>
      </tr>
      <tr>
        <td>이름</td>
        <td>${emp.empName}</td>
      </tr>
      <tr>
        <td>주민번호</td>
        <td>${emp.empNo}</td>
      </tr>
      <tr>
        <td>이메일</td>
        <td>${emp.email}</td>
      </tr>
      <tr>
        <td>휴대전화</td>
        <td>${emp.phone}</td>
      </tr>
      <tr>
        <td>직급코드</td>
        <td>${emp.jobCode}</td>
      </tr>
      <tr>
        <td>직급명</td>
        <td>${jobName}</td>
      </tr>
      <tr>
        <td>부서코드</td>
        <td>${emp.deptCode}</td>
      </tr>
      <tr>
        <td>부서명</td>
        <td>${deptTitle}</td>
      </tr>
      <tr>
        <td>지역코드</td>
        <td>${locationId}</td>
      </tr>
      <tr>
        <td>지역명</td>
        <td>${localName}</td>
      </tr>
      <tr>
        <td>국가명</td>
        <td>${nationalName}</td>
      </tr>
      <tr>
        <td>salaryLevel</td>
        <td>${emp.salaryLevel}</td>
      </tr>
      <tr>
        <td>급여</td>
        <td>${emp.salary}</td>
      </tr>
      <tr>
        <td>보너스</td>
        <td>${emp.bonus}</td>
      </tr>
      <tr>
        <td>사수</td>
        <td>${emp.managerId}</td>
      </tr>
      <tr>
        <td>입사일</td>
        <td>${emp.hireDate}</td>
      </tr>
      <tr>
        <td>퇴사일</td>
        <td>${emp.entDate}</td>
      </tr>
      <tr>
        <td>퇴사여부</td>
        <td>${emp.entYN}</td>
      </tr>
    
    </table>
  
  
  </main>




</body>
</html>