package edu.kh.emp.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;

//import edu.kh.emp.model.dao.EmployeeDAO;
import edu.kh.emp.model.vo.Employee;

// 화면용 클래스( 입력(Scanner) / 출력(print()) )
public class EmployeeView {

	private Scanner sc = new Scanner(System.in);
	private List<Employee> empList = new ArrayList<>(); 
	// DAO 객체 생성
	//private EmployeeDAO dao = new EmployeeDAO();
	
	
	
	// 메인 메뉴
	public void displayMenu() {
		
		int input = 0;
		
		do {
			try {
				System.out.println("---------------------------------------------------------");
				System.out.println("----- 사원 관리 프로그램 -----");
				System.out.println("1. 새로운 사원 정보 추가");
				System.out.println("2. 전체 사원 정보 조회");
				System.out.println("3. 사번이 일치하는 사원 정보 조회");
				System.out.println("4. 사번이 일치하는 사원 정보 수정");
				System.out.println("5. 사번이 일치하는 사원 정보 삭제");
				
				System.out.println("6. 입력 받은 부서와 일치하는 모든 사원 정보 조회");
				// selectDeptEmp()
				
				System.out.println("7. 입력 받은 급여 이상을 받는 모든 사원 정보 조회");
				// selectSalaryEmp()
				
				System.out.println("8. 부서별 급여 합 전체 조회");
				// selectDeptTotalSalary()
				// DB 조회 결과를 HashMap<String, Integer>에 옮겨 담아서 반환
				// 부서코드, 급여 합 조회
				
				System.out.println("9. 주민등록번호가 일치하는 사원 정보 조회");
				
				System.out.println("10. 직급별 급여 평균 조회");
				// selectJobAvgSalary()
				// DB 조회 결과를 HashMap<String, Double>에 옮겨 담아서 반환 
				// 직급명, 급여 평균(소수점 첫째자리) 조회
				
				
				System.out.println("0. 프로그램 종료");
				
				System.out.print("메뉴 선택 >> ");
				input = sc.nextInt();
				sc.nextLine(); //  추가!
				
				
				System.out.println();				
				
				
				switch(input) {
				case 1:  insertEmployee();   break;
				case 2:  selectAll();  break;
				case 3:  selectEmpId();   break;
				case 4:  updateEmployee();   break;
				case 5:  deleteEmployee();   break;
				case 6:  selectDeptEmp();   break;
				case 7:  selectSalaryEmp();   break;
				case 8:  selectDeptTotalSalary();   break;
				case 9:  selectEmpNo();   break;
				case 10: selectJobAvgSalary();   break;
				
				case 0:  System.out.println("프로그램을 종료합니다...");   break;
				default: System.out.println("메뉴에 존재하는 번호만 입력하세요.");
				}
				
				
			}catch(InputMismatchException e) {
				System.out.println("정수만 입력해주세요.");
				input = -1; // 반복문 첫 번째 바퀴에서 잘못 입력하면 종료되는 상황을 방지
				sc.nextLine(); // 입력 버퍼에 남아있는 잘못 입력된 문자열 제거해서
							   // 무한 반복 방지
			}
			
		}while(input != 0);
		
	}
	

	
	/**
	 * 전체 사원 정보 조회
	 */
	public void selectAll() {
		System.out.println( "====== 전체 사원 정보 ======");
		
		if(empList.isEmpty()) {
			 System.out.println("사원 정보가 없습니다.");
			 	
		}else {
			for(Employee emp : empList) {
				System.out.println(emp);
			}
		}
	}
	
	
	/** 전달받은 사원 List 모두 출력
	 * @param empList
	 */
	public void printAll(List<Employee> empList) {
		
			for(Employee emp : empList) {
				System.out.println(emp);
			}
		
	}
	
	
	/**
	 * 사번이 일치하는 사원 정보 조회
	 */
	public void selectEmpId() {
		System.out.println("====== 사번 일치 사원 정보 조회 ======");
		
		System.out.print("사번을 입력하세요 : ");
		int inputEmpID = sc.nextInt();
		
		for(Employee e : empList) {
			if(e.getEmpId() == inputEmpID) {
				System.out.println(e);
			}else {
				System.out.println("일치하는 정보가 없습니다.");
			}
		}
	}
	
	
	/** 사번을 입력 받아 반환하는 메서드
	 * @return empId
	 */
	public int inputEmpId() {
	
			System.out.print("사번 입력 : ");
			int inputEmpNo = sc.nextInt();
			sc.nextLine();
			
			return 0;
		
	}
	
	
	/** 사원 1명 정보 출력
	 * @param emp
	 */
	public void printOne(Employee emp) {
		
	}
	
	
	/**
	 * 주민등록번호가 일치하는 사원 정보 조회
	 */
	public void selectEmpNo() {
		System.out.println("====== 주민등록번호 일치 사원 정보 조회 ======");
		
		System.out.print("주민등록번호를 입력하세요 (-)포함 : ");
		String inputEmpNo = sc.next();
		
		for(Employee e : empList) {
			if(e.getEmpNo() == inputEmpNo) {
				System.out.println(e);
			}else {
				System.out.println("일치하는 정보가 없습니다.");
			}
		}
	}
	
	
	/**
	 * 사원 정보 추가
	 */
	public void insertEmployee() throws InputMismatchException{
		
			System.out.println("=====사원 정보 추가=====");
			
			System.out.print("사원 번호 : ");
			int empId = sc.nextInt();
			sc.nextLine();
			
			System.out.print("사원 이름 : ");
			String empName = sc.next();
			
			System.out.print("주민등록번호 : ");
			String empNo = sc.next();
			
			System.out.print("이메일 : ");
			String email = sc.next();
			
			System.out.print("전화 번호 : ");
			String phone = sc.next();
			
			System.out.print("급여 : ");
			int salary = sc.nextInt();
			sc.nextLine();
			
			System.out.print("부서 코드 : ");
			String deptCode = sc.next();
			
			System.out.print("직급 코드 : ");
			String jobCode = sc.next();
			
			System.out.print("급여 등급 : ");
			String salLevel = sc.next();
			
			System.out.print("보너스 : ");
			double bonus = sc.nextDouble();
			sc.nextLine();
			
			System.out.print("사수 번호 : ");
			int managerId = sc.nextInt();
			
			if(empList.add(new Employee(empId, empName, empNo, email, phone, salary,
						deptCode, jobCode, salLevel, bonus, managerId))) {
				System.out.println("성공");
			} else {
				System.out.println("실패");
			}
			
		
	}
	
	
	/**
	 * 사번이 일치하는 사원 정보 수정(이메일, 전화번호, 급여)
	 */
	public void updateEmployee() throws InputMismatchException{
			
			System.out.println("=====사원 정보 수정=====");
			
			int empId = inputEmpId();
			
			boolean flag = true;
			
			for(Employee emp : empList) {
				if(empId == emp.getEmpId()) {
					System.out.print("수정할 이메일 : ");
					String email = sc.next();
					
					System.out.print("수정할 전화번호 : ");
					String phone = sc.next();
					
					System.out.print("수정할 급여 : ");
					int salary = sc.nextInt();
					
					emp.setEmail(email);
					emp.setPhone(phone);
					emp.setSalary(salary);
					
					System.out.println("사원 정보가 수정되었습니다.");
					flag = false;
				}
			}
			
			if(flag) {
				System.out.println("검색결과가 없습니다.");
			}
			
		
	}
	
	/**
	 * 사번이 일치하는 사원 정보 삭제
	 */
	public void deleteEmployee() {
		
	}
	
	
	/**
	 * 입력 받은 부서와 일치하는 모든 사원 정보 조회
	 */
	public void selectDeptEmp() throws InputMismatchException{
		System.out.println("=====부서 내 사원 정보 조회=====");
		
		System.out.print("부서 코드 : ");
		String inputdeptCode = sc.next();
		
		boolean flag = true;
		
		for(Employee emp : empList) {
			if(inputdeptCode.equals(emp.getDeptCode())) {
				printOne(emp);
				flag = false;
			}
		}
		
		if(flag) {
			System.out.println("잘못 입력하셨습니다.");
		}		
		
	
	}
	
	/**
	 * 입력 받은 급여 이상을 받는 모든 사원 정보 조회
	 */
	public void selectSalaryEmp() throws InputMismatchException{
		System.out.println("===== 입력 급여 이상 사원 조회=====");
		
		System.out.print("급여를 입력해주세요 : ");
		int inputSalary = sc.nextInt();
		sc.nextLine();
		
		boolean flag = true;
		
		System.out.println(inputSalary + "원 이상 급여를 받는 직원");
		for(Employee emp : empList) {
			if(emp.getSalary() >= inputSalary) {
				printOne(emp);
				flag = false;
			}
		}
		if(flag) {
			System.out.println("입력된 급여 이상을 받는 직원이 없습니다.");
		}
		
	}
	
	/**
	 * 부서별 급여 합 전체 조회
	 */
	public void selectDeptTotalSalary() {
		
	}
	
	/**
	 * 직급별 급여 평균 조회
	 */
	public void selectJobAvgSalary() {
		
		
	}
	
	
	
}