<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>html문서 제목</title>
</head>
<body>
  
  <header>
    <h1>EMPLYOEE 테이블</h1>
  
  </header>



  <main>

    <div>
      <a href="/nationalView">nationalView</a>
    </div>

    <table border="1">

      <thead>

        <tr>
          <td>empId</td>
          <td>empName</td>
          <td>empNo</td>
          <td>email</td>
          <td>phone</td>
          <td>deptCode</td>
          <td>jobCode</td>
          <td>salaryLevel</td>
          <td>salary</td>
          <td>bonus</td>
          <td>managerId</td>
          <td>hireDate</td>
          <td>entDate</td>
          <td>entYN</td>
        </tr>

      </thead>
    
      <tbody>
        <c:forEach items="${empList}" var="emp">

          <tr>

          <td>${emp.empId}</td>
          <td><a href="/detail?empId=${emp.empId}">${emp.empName}</a></td>
          <td>${emp.empNo}</td>
          <td>${emp.email}</td>
          <td>${emp.phone}</td>
          <td>${emp.deptCode}</td>
          <td>${emp.jobCode}</td>
          <td>${emp.salaryLevel}</td>
          <td>${emp.salary}</td>
          <td>${emp.bonus}</td>
          <td>${emp.managerId}</td>
          <td>${emp.hireDate}</td>
          <td>${emp.entDate}</td>
          <td>${emp.entYN}</td>
          
          </tr>

        </c:forEach>
      
      </tbody>
    
    
    </table>




















  
  </main>





</body>
</html>