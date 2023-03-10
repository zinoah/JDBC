package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc1.model.vo.Employee2;


public class JDBCExample5 {
	public static void main(String[] args) {
		
		// 입사일을 입력("2022-09-06") 받아
		// 입력 받은 값보다 먼저 입사한 사람의
		// 이름, 입사일, 성별(M,F) 조회
		
		// 01) 선동일 / 1990년 02월 06일 / M
		// 02) 송은희 / 1996년 05월 03일 / F
		// 03) 정중하 / 1999년 09월 09일 / M
		
		Scanner sc = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			System.out.print("입사일을 입력해 주세요(YYYY-MM-DD) : ");
			String hireDate1 = sc.next();
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String user = "kh";
			String pw = "kh1234";
			
			conn = DriverManager.getConnection(url, user, pw);
			
			String sql = "SELECT EMP_NAME , TO_CHAR(HIRE_DATE, 'YYYY\"년\" MM\"월\" DD\"일\"') HIRE_DATE , DECODE(SUBSTR(EMP_NO,8,1), 1 , 'M', 2 , 'F') GENDER "
						+ "FROM EMPLOYEE "
						+ "WHERE HIRE_DATE < TO_DATE('" + hireDate1 + "')";
			// 문자열 내부에 쌍따옴표 작성시 \" 로 작성해야한다! (Escape 문자)
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			List<Employee2> list = new ArrayList<>();
			
			while(rs.next()) {
				// 기본생성자 통한 set 
				// Employee emp = new Employee(); // 기본 생성자로 Employee 객체 생성
												// 필드 초기화 X
												// setter를 이용해서 하나씩 세팅 
				// emp.setEmpName(rs.getString("EMP_NAME"));
				// emp.setHireDate( rs.getString("HIRE_DATE")); // 조회 시 컬럼명이 "입사일"
				// 현재 행의 "입사일" 컬럼의 문자열 값을 얻어와 
				// emp가 참조하는 객체의 hireDate 필드에 세팅
				
				// emp.setGender(rs.getString("성별").charAt(0) );
				// -> char 자료형 매개변수 필요
				// Java의 char : 문자 1개 의미
				// DB의 CHAR : 고정길이 문자열 
				// DB 컬럼 값을 char 자료형에 저장하고 싶으면 
				// String.charAt(index)이용!
				
				// list에 emp 객체 추가
				// list.add(emp)
				
				
				String empName = rs.getString("EMP_NAME");
				String hireDate = rs.getString("HIRE_DATE");
				String gender = rs.getString("GENDER");
				list.add(new Employee2(empName, hireDate, gender ));
				
			} 
			// 조회 결과가 없는 경우 
			// if(list.size() == 0){
			// System.out.println("조회 결과가 없습니다.");
			// } else{
			// 		// for(int i = 0; i < list.size(); i++ ) {
							//System.out.printf("%02d) %s / %s / %c\n",
							//i+1, 
							//list.get(i).getEmpName(),
							//list.get(i).getHireDate(),
							//list.get(i).getGender() );
			//			}
			// }
			if(list.isEmpty()) {
				System.out.println("조회 결과 없음");
			}else {
				for(Employee2 emp : list) {
					System.out.println(emp);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
}
