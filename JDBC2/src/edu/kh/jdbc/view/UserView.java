package edu.kh.jdbc.view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.dto.User;
import edu.kh.jdbc.service.UserService;

public class UserView {
	
	// 필드
	private UserService service = new UserService();
	private Scanner     sc      = new Scanner(System.in);
	
	/**
	 * JDBCTempltate 사용 테스트
	 */
	public void test() {
		// 입력된 ID와 일치하는 유저 정보 조회하기
		System.out.print("ID 입력 : ");
		String input = sc.nextLine();
		
		//서비스 호출 후 결과 반환받기
		User user = service.selectId(input);
		
		// 결과 출력
		System.out.println(user);
		
	}
	
	
	
	public void mainMenu() {
		
		int input = 0;
		
		do {
			
			try {
				
				System.out.println("===== User 관리 프로그램 =====");
				System.out.println("1. User 등록(ISERT)"           );
				System.out.println("2. User 전체조회(SELECT)"      );
				System.out.println("3. User 이름검색(SELECT)"      );
				System.out.println("4. User 조회(USER_NO, SELECT)" );
				// 번호 입력받고 User정보 띄워주기
				// PK니까 중복 X
				System.out.println("5. User 삭제(USER_NO, DELETE)" );
				// 4번 실행하고, 삭제할건지 물어봐서
				// 삭제한다고하면 삭제할 User정보 전달
				System.out.println("6. User 이름수정(ID, PW, UPDATE)" );
				// 수정할 ID, PW 입력받고
				// PW 불일치시 return
				// 일치하면 정보띄워주고
				// 수정할 이름값 입력받기
				System.out.println("7. User 등록(ID중복검사)"      );
				System.out.println("8. 여러 User 등록하기"         );
				// 그만 입력하려는 문구를 입력할때까지
				// 계속해서 ID, PW, NAME을 입력받고
				// 다받으면 넘겨서 입력받기
				System.out.println("9. 여러 User 등록하기2"              );
				System.out.println("0. 프로그램 종료"              );
				System.out.print  (">>> 메뉴선택 : ");
				input = sc.nextInt();
				sc.nextLine(); // 버퍼에 남은 개행문자 제거
				
				switch(input) {
				case 1 : insertUser(); break;
				case 2 : selectAll(); break;
				case 3 : selectName(); break;
				case 4 : searchNo(); break;
				case 5 : searchNoDelete(); break;
				case 6 : updateUser(); break;
				case 7 : insertAfterCheckId(); break;
				case 8 : insetMiltyUser(); break;
				case 9 : multiInsertUser(); break;
				case 0 : System.out.println("\n[프로그램 종료]\n"); break;
				default : System.out.println("\n> 잘못 입력 하셨습니다.\n");
				}
				
				System.out.println("\n==============================");
				
			} catch (InputMismatchException e) {
				// 스케너에 잘못 입력했을때
				System.out.println("\n>>> 잘못 입력 하셨습니다.");
				input = -1;		// while문 반복을 위한 세팅
				sc.nextLine();	// 입력버퍼 초기화
				
			} catch (Exception e) {
				// 발생되는 예외를 모두 해당 catch 구문으로 모아서(throws) 처리
				e.printStackTrace();
				
			} finally {
				try {
					
				} catch (Exception e) {
					e.printStackTrace();
					
				}
			}
			
		} while(input!=0);
		if(sc != null) sc.close();
	}





	/**
	 *  User 등록 
	 * @throws SQLException
	 */
	private void insertUser() throws SQLException {
		System.out.println("\n=== 1. User 등록 ====\n");
		
		System.out.print("ID : ");
		String userId = sc.next();
		System.out.print("PW : ");
		String userPw = sc.next();
		System.out.print("Name : ");
		String userName = sc.next();
		
		// 입력받은 값 3개를 한번에 묶어서 전달 할 수 있도록
		// User DTO 객체를 생성한 후 필드에 값을 세팅
		User user = new User();
		
		user.setUserId(userId);
		user.setUserPw(userPw);
		user.setUserName(userName);
		
		int result = service.insertUser(user);
		
		// 반환된 결과에 따라 출력할 내용 선택
		if(result > 0) {
			System.out.println("\n" + userId + " 사용자가 등록 되었습니다\n");
		} else {
			System.out.println("\n*** 등록 실패 ***\n");
		}
		
		
	}
	
	
	/**
	 * 2. User 전체 조회(SELECT)
	 */
	private void selectAll() throws SQLException, InputMismatchException {
		
		List<User> userList = service.selectAll();
		
		if(userList.isEmpty()) {
			System.out.println(">> 조화결과 없음");
			return;
		}
		
		for(User user : userList) {
			System.out.printf("%d | %s | %s | %s | %s\n",
					user.getUserNo(), user.getUserId(), user.getUserPw(), user.getUserName(), user.getEnrollDate());
		}
		
	}
	
	
	/**
	 * 3. User 중 이름에 검색어가 포함된 회원 조회
	 */
	private void selectName() throws SQLException {
		System.out.println("\n=== 3. User 중 이름에 검색어가 포함된 회원 조회 ===\n");
		
		System.out.print("검색어 입력 : ");
		String keyword = sc.nextLine();
		
		// 서비스 호출 후 결과 반환받기
		List<User> searchList = service.selectName(keyword);
		
		if(searchList.isEmpty()) {
			System.out.println(">> 조화결과 없음");
			return;
		}
		
		for(User user : searchList) {
			System.out.printf("%d | %s | %s | %s | %s\n",
					user.getUserNo(), user.getUserId(), user.getUserPw(), user.getUserName(), user.getEnrollDate());
		}
	}
	
	/**
	 * 4. USER_NO를 입력 받아 일치하는 User 조회(SELECT)
	 */
	private void searchNo() throws SQLException {
		System.out.println("\n=== 4. USER_NO를 입력 받아 일치하는 User 조회(SELECT) ===\n");
		
		System.out.print("User 번호 입력 : ");
		int userNo = sc.nextInt();
		sc.nextLine();
		
		// 서비스 호출
		User user = service.selectNo(userNo);
		
		if(user == null) {
			System.out.println(">> 조화결과 없음");
			return;
		}
		
		System.out.println(user.toString());
		
	}
	
	/** 5. USER_NO를 입력 받아 일치하는 User 삭제(DELETE)
	 * 4번 실행하고, 삭제할건지 물어봐서
	 * 삭제한다고하면 삭제할 User정보 전달
	 * @throws SQLException
	 */
	private void searchNoDelete() throws SQLException {
		System.out.println("\n===== 5. USER_NO를 입력 받아 일치하는 User 삭제(DELETE) ===\n");

		System.out.print("User 번호 입력 : ");
		int userNo = sc.nextInt();
		sc.nextLine();
		
		// 서비스 호출
		User user = service.selectNo(userNo);
		
		if(user == null) {
			System.out.println(">> 조화결과 없음");
			return;
		}
		
		boolean flag = true;
		int input = 0;
		while(flag) {
			try {
				System.out.print("\n정말로 삭제 하시겠습니까?\n(1.Yes / 2.No) : ");
				input = sc.nextInt();
				sc.nextLine();
				
				if(input == 2 || input == 1) flag=false;
				else System.out.println("올바른 숫자만 입력해 주세요");
			} catch (InputMismatchException e) {
				System.out.println("올바른 숫자만 입력해 주세요");
				flag = true;
			}
		}
		
		
		if(input ==2) {
			System.out.println(">> 취소하셨습니다.");
			return;
		}
		
		// 서비스호출2
		int result = service.deleteUser(user);
		
		// 반환된 결과에 따라 출력할 내용 선택
		if(result > 0) {
			System.out.println("\n" + user.getUserName() + "사용자가 삭제 되었습니다\n");
		} else {
			System.out.println("\n*** 삭제 실패 ***\n");
		}
	}
	
	
	/**6. ID, PW가 일치하는 회원이 있을 경우 이름 수정(UPDATE)
	 * 수정할 ID, PW 입력받고 PW 불일치시 return
	 * 일치하면 정보띄워주고 수정할 이름값 입력받기
	 */
	private void updateUser() throws SQLException {
		System.out.println("\n===== 6. ID, PW가 일치하는 회원이 있을 경우 이름 수정(UPDATE) ===\n");
		
		// 아이디 입력
		System.out.print("User ID 입력 : ");
		String inputId = sc.nextLine();
		User user = service.selectId(inputId);
		
		if(user == null) {
			System.out.println(">> 일치하는 아이디가 없습니다.");
			return;
		}
		
		// 비밀번호 입력
		// 비밀번호가 잘못되면 다시입력 or 뒤로가기
		String inputPw = "";
		boolean flag = false;
		while(!flag) {
			//// flag == false일때 반복
			
			System.out.print("User PW 입력 : ");
			inputPw = sc.nextLine();
			
			// ID, PW가 계정에 있는값과 일치하는가
			
			if(user.getUserPw().equals(inputPw)) {
				flag = true;
			} else {
				flag = false;
			}
			// 일치하면 true, while문 종료
			// 불일치시 false while문 반복
			
			int input = 0;
			if(!flag) { // 불일치할때 진입
				while(!flag) {
					// flag == false일때 반복
					try {
						System.out.println("\n>> 비밀번호가 일치하지 않습니다.");
						System.out.println(">> 계속입력 : 1");
						System.out.println(">> 돌아가기 : 2");
						System.out.print("입력 : ");
						input = sc.nextInt();
						sc.nextLine();
						
						if(input == 2 || input == 1) flag=true; // 입력 종료
						else System.out.println("올바른 숫자만 입력해 주세요");
						
					} catch (InputMismatchException e) {
						System.out.println("올바른 숫자만 입력해 주세요");
						flag = false; // 입력 반복
					}
					
				} //end while - input int
			} // end if
			if(input == 1) {
				flag = false;
			} else if(input == 2) {
				return;
			}
		} // end while - pw확인
		
		// 이름 띄우기
		System.out.println("\n변경전 : [" + user.getUserName() + "]");
		// 바뀔이름 입력받기
		System.out.print("변경할 이름 : ");
		String inputName = sc.nextLine();
		
		// 서비스호출
		int result = service.updateUserName(user.getUserNo(), inputName);
		
		// 수정결과 출력
		if(result > 0) {
			System.out.println("\n[" + user.getUserName() + "] -> [" + inputName + "] 변경완료\n");
		} else {
			System.out.println("\n*** 변경 실패 ***\n");
		}
		
	}
	
	
	
	
	
	
	
	
	/**
	 * 7. User 등록(ID중복검사)
	 * @throws SQLException 
	 */
	private void insertAfterCheckId() throws SQLException {
		System.out.println("\n=== 7. User 등록(아이디 중복 검사) ===\n");
		
		boolean flag=true;
		String userId = "";
		while (flag) {
			System.out.print("ID : ");
			userId = sc.next();
			
			flag = service.checkId(userId);
			if(flag) System.out.println(">> 중복된 아이디가 존재합니다");
		}
		System.out.print("PW : ");
		String userPw = sc.next();
		System.out.print("Name : ");
		String userName = sc.next();
		
		// 입력받은 값 3개를 한번에 묶어서 전달 할 수 있도록
		// User DTO 객체를 생성한 후 필드에 값을 세팅
		User user = new User();
		
		user.setUserId(userId);
		user.setUserPw(userPw);
		user.setUserName(userName);
		
		int result = service.insertUser(user);
		
		// 반환된 결과에 따라 출력할 내용 선택
		if(result > 0) {
			System.out.println("\n" + userId + " 사용자가 등록 되었습니다\n");
		}  else {
			System.out.println("\n*** 등록 실패 ***\n");
		}
	}
	
	
	 /**그만 입력하려는 문구를 입력할때까지
	  * 계속해서 ID, PW, NAME을 입력받고
	  * 다받으면 넘겨서 입력받기
	 * @throws SQLException */
	private void insetMiltyUser() throws SQLException {
		System.out.println("\n=== 8. 여러 User 등록하기 ===\n");
		
		// 적재할 리스트
		List<User> insertList = new ArrayList<User>();
		List<String> nameList = new ArrayList<String>();
		// 리스트길이... 필요없음x 필요함
		int count = 0;
		// 입력받을 정지 명령어
		String stop = "ESCAPE";
		String overLap = ">> 중복된 ID 입니다.";
		
		System.out.println(">> 다음 양식에 맞춰 입력해 주세요.");
		System.out.println("ID.. PW.. NAME..");
		
		while(true) {
			
			count ++;
			System.out.println("\n>" + count + " - (그만하려면 \"ESCAPE\" 입력)");
			String userId = sc.next();
			if(userId.equals(stop)) break;
			String userPw = sc.next();
			if(userPw.equals(stop)) break;
			String userName = sc.next();
			if(userName.equals(stop)) break;
			
			//중복검사
			if(nameList.contains(userId)) {
				System.out.println(overLap);
				count--;
			}else {
				nameList.add(userId);
				if(!service.checkId(userId)) {
					User user = new User();
					user.setUserId(userId);
					user.setUserPw(userPw);
					user.setUserName(userName);
					insertList.add(user);
				} else {
					System.out.println(overLap);
					count--;
				}
			}// 중복검사, 입력정보 저장
		}// USER정보 반복입력
		
		count = 0;
		int result = 0;
		for(User user : insertList) {
			
			// 중복검사 선시행 해놨음
			result = service.insertUser(user);
			
			count++;
			// 반환된 결과에 따라 출력할 내용 선택
			if(result > 0) {
				System.out.println(count + " ... success ヾ(≧▽≦*)o");
			} else {
				System.out.println(count + " ... fail ヾ(￣▽￣) Bye~Bye~");
			}
		}
		
		System.out.println(">> 작업이 완료되었습니다.");
	}
	
	//=============================================================
	/** 8. 여러 User 등록하기
	 * 강사님꺼
	 * @throws Exception
	 */
	private void multiInsertUser() throws Exception{
		
		// 등록할 User 수 : 2
		
		// 1번째 userId : user100
		// -> 사용가능한 ID 입니다
		// 1번째 userPw : pass100
		// 1번째 userName : 유저백
		// -----------------------
		// 2번째 userId : user200
		// -> 사용가능한 ID 입니다
		// 2번째 userPw : pass200
		// 2번째 userName : 유저이백
		
		// -- 전체 삽입 성공   /  삽입 실패
		
		System.out.println("\n=== 8. 여러 User 등록하기 ===\n");
		
		System.out.print("등록할 User 수 :");
		int input = sc.nextInt();
		sc.nextLine(); // 버퍼 개행문자 제거
		
		
		// 입력 받은 회원 정보를 저장할 List 객체 생성
		List<User> userList = new ArrayList<User>();
		
		for(int i = 0 ; i < input ; i++ ) {
			
			String userId = null; // 입력된 아이디를 저장할 변수
			
			while(true) {
				System.out.print((i+1) + "번째 userId : ");
				userId = sc.nextLine();
				
				int count = service.idCheck(userId);
				
				if(count == 0) { // 중복이 아닌 경우
					System.out.println("사용 가능한 아이디 입니다");
					break;
				}
				
				System.out.println("이미 사용중인 아이디 입니다. 다시 입력 해주세요");
			}
			
			System.out.print((i+1) + "번째 userPw : ");
			String userPw = sc.nextLine();
			
			System.out.print((i+1) + "번째 userName : ");
			String userName = sc.nextLine();
			
			User user = new User();
			user.setUserId(userId);
			user.setUserPw(userPw);
			user.setUserName(userName);
			
			// userList에 user 추가
			userList.add(user);
		
		} // for문 종료
		
		
		// 입력 받은 모든 사용자 insert하는 서비스 호출
		int result = service.multiInsertUser(userList);
		
		if(result == userList.size()) {
			System.out.println(">> 성공");
		} else {
			System.out.println(">> 실패");
		}
		
		
		
	}
}
