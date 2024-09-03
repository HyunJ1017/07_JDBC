<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>html문서 제목</title>
</head>
<body>
  


  <main>

  
    <h1>L1 location</h1>
    <table border="1">

      <thead>

        <tr>
          <td>empId</td>
          <td>empName</td>
          <td>email</td>
          <td>phone</td>
          <td>deptCode</td>
          <td>jobCode</td>
          <td>salary</td>
          <td>hireDate</td>
          <td>entDate</td>
          <td>entYN</td>
        </tr>

      </thead>
    
      <tbody>
        <c:forEach items="${empListL1}" var="emp">

          <tr>

          <td>${emp.empId}</td>
          <td>${emp.empName}</td>
          <td>${emp.email}</td>
          <td>${emp.phone}</td>
          <td>${emp.deptCode}</td>
          <td>${emp.jobCode}</td>
          <td>${emp.salary}</td>
          <td>${emp.hireDate}</td>
          <td>${emp.entDate}</td>
          <td>${emp.entYN}</td>
          
          </tr>

        </c:forEach>
      
      </tbody>
    
    
    </table>


    <h1>L2 location</h1>
    <table border="1">

      <thead>

        <tr>
          <td>empId</td>
          <td>empName</td>
          <td>email</td>
          <td>phone</td>
          <td>deptCode</td>
          <td>jobCode</td>
          <td>salary</td>
          <td>hireDate</td>
          <td>entDate</td>
          <td>entYN</td>
        </tr>

      </thead>
    
      <tbody>
        <c:forEach items="${empListL2}" var="emp">

          <tr>

          <td>${emp.empId}</td>
          <td>${emp.empName}</td>
          <td>${emp.email}</td>
          <td>${emp.phone}</td>
          <td>${emp.deptCode}</td>
          <td>${emp.jobCode}</td>
          <td>${emp.salary}</td>
          <td>${emp.hireDate}</td>
          <td>${emp.entDate}</td>
          <td>${emp.entYN}</td>
          
          </tr>

        </c:forEach>
      
      </tbody>
    
    
    </table>

    <h1>L3 location</h1>
    <table border="1">

      <thead>

        <tr>
          <td>empId</td>
          <td>empName</td>
          <td>email</td>
          <td>phone</td>
          <td>deptCode</td>
          <td>jobCode</td>
          <td>salary</td>
          <td>hireDate</td>
          <td>entDate</td>
          <td>entYN</td>
        </tr>

      </thead>
    
      <tbody>
        <c:forEach items="${empListL3}" var="emp">

          <tr>

          <td>${emp.empId}</td>
          <td>${emp.empName}</td>
          <td>${emp.email}</td>
          <td>${emp.phone}</td>
          <td>${emp.deptCode}</td>
          <td>${emp.jobCode}</td>
          <td>${emp.salary}</td>
          <td>${emp.hireDate}</td>
          <td>${emp.entDate}</td>
          <td>${emp.entYN}</td>
          
          </tr>

        </c:forEach>
      
      </tbody>
    
    
    </table>

    <h1>L4 location</h1>
    <table border="1">

      <thead>

        <tr>
          <td>empId</td>
          <td>empName</td>
          <td>email</td>
          <td>phone</td>
          <td>deptCode</td>
          <td>jobCode</td>
          <td>salary</td>
          <td>hireDate</td>
          <td>entDate</td>
          <td>entYN</td>
        </tr>

      </thead>
    
      <tbody>
        <c:forEach items="${empListL4}" var="emp">

          <tr>

          <td>${emp.empId}</td>
          <td>${emp.empName}</td>
          <td>${emp.email}</td>
          <td>${emp.phone}</td>
          <td>${emp.deptCode}</td>
          <td>${emp.jobCode}</td>
          <td>${emp.salary}</td>
          <td>${emp.hireDate}</td>
          <td>${emp.entDate}</td>
          <td>${emp.entYN}</td>
          
          </tr>

        </c:forEach>
      
      </tbody>
    
    
    </table>

    <h1>L5 location</h1>
    <table border="1">

      <thead>

        <tr>
          <td>empId</td>
          <td>empName</td>
          <td>email</td>
          <td>phone</td>
          <td>deptCode</td>
          <td>jobCode</td>
          <td>salary</td>
          <td>hireDate</td>
          <td>entDate</td>
          <td>entYN</td>
        </tr>

      </thead>
    
      <tbody>
        <c:forEach items="${empListL5}" var="emp">

          <tr>

          <td>${emp.empId}</td>
          <td>${emp.empName}</td>
          <td>${emp.email}</td>
          <td>${emp.phone}</td>
          <td>${emp.deptCode}</td>
          <td>${emp.jobCode}</td>
          <td>${emp.salary}</td>
          <td>${emp.hireDate}</td>
          <td>${emp.entDate}</td>
          <td>${emp.entYN}</td>
          
          </tr>

        </c:forEach>
      
      </tbody>
    
    
    </table>

    <h1>미지정 location</h1>
    <table border="1">

      <thead>

        <tr>
          <td>empId</td>
          <td>empName</td>
          <td>email</td>
          <td>phone</td>
          <td>deptCode</td>
          <td>jobCode</td>
          <td>salary</td>
          <td>hireDate</td>
          <td>entDate</td>
          <td>entYN</td>
        </tr>

      </thead>
    
      <tbody>
        <c:forEach items="${empListL0}" var="emp">

          <tr>

          <td>${emp.empId}</td>
          <td>${emp.empName}</td>
          <td>${emp.email}</td>
          <td>${emp.phone}</td>
          <td>${emp.deptCode}</td>
          <td>${emp.jobCode}</td>
          <td>${emp.salary}</td>
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