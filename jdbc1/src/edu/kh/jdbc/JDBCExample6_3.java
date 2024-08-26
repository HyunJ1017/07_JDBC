package edu.kh.jdbc;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class JDBCExample6_3 {
	
	/** views */
	
	JDBCExample6_2 service;
	Scanner sc = new Scanner(System.in);
	
	// 기본생성자
	public JDBCExample6_3() {
		
		try {
			service = new JDBCExample6_2();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
		
	public void selector() {
		int input = 0;
		try {
			do {
				System.out.print("번호입력 : ");
				input = sc.nextInt();
				sc.nextLine();
				
				System.out.println("입력번호 : " + input);
				
				switch(input) {
					case 1 : allSearch(); break;
					case 2 : searchName(); break;
					case 3 : searchBox(); break;
					case 0 : System.out.println("끝"); break;
					default : System.out.println("?");
				}
			} while (input != 0);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(sc != null) sc.close();
				if(service.conn != null) service.conn.close();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/** 전체조회결과조회
	 */
	private void allSearch() throws SQLException {
		String str = service.allSearch();
		System.out.println(str);
	}
	
	/** 이름 검색해서 리스트로 받기
	 */
	private void searchName() throws SQLException {
		System.out.print("이름입력 : ");
		String searchName = sc.nextLine();
		
		List<EMPLOYEE> empList = service.searchName(searchName);
		if(empList == null) {
			System.out.println("입력하신이름을찾을수없습니다");
			return;
		}
		
		for(EMPLOYEE emp : empList) {
			System.out.println(emp.toString());
		}
		
	}
	
	/** 선택검색
	 */
	private void searchBox() throws SQLException {
		int input=0;
		do {
			System.out.println("1 ~ 4 사이의 숫자를 입력해 주세요");
			System.out.println("1. 사번검색");
			System.out.println("2. 이름검색");
			System.out.println("3. 주민번호 검색");
			System.out.println("4. 부서코드 검색");
			System.out.print("검색종류 : ");
			input = sc.nextInt();
			sc.nextLine();
			
		} while(input <1 || input > 4);
		
		switch(input) {
			case 1 : System.out.println(">> 정확한 사번을 입력해주세요."); break;
			case 2 : System.out.println(">> 검색할 이름을 입력해주세요."); break;
			case 3 : System.out.println(">> 정확한 주민등록번호를 입력해주세요."); break;
			case 4 : System.out.println(">> 검색할 부서코드를 입력해주세요."); break;
		}
		
		System.out.print("검색내용 : ");
		String search = sc.nextLine();
		
		
		List<EMPLOYEE> empList = service.searchBox(input, search);
		if(empList == null) {
			System.out.println("검색하신내용을찾을수없습니다");
			return;
		}
		
		for(EMPLOYEE emp : empList) {
			System.out.println(emp.toString());
		}
		
	}
		
		
	

}
