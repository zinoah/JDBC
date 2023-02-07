package edu.kh.jdbc.member.view;

import java.util.Scanner;

import edu.kh.jdbc.member.vo.Member;

public class MemberView {
	
	// 로그인 회원 정보 저장용 변수 
	
	private Member loginMember = null;
	
	public void memberMenu(Member loginMember) {
		
		// 전달 받은 로그인 회원 정보를 필드에 저장
		this.loginMember = loginMember;
		
		Scanner sc = new Scanner(System.in);
		
		int input = -1;
		do {
			
			System.out.println("[회원 기능]");
			System.out.println("1. 내 정보 조회");
			System.out.println("2. 회원 목록 조회");
			System.out.println("3. 내 정보 수정");
			System.out.println("4. 비밀번호 변경");
			System.out.println("5. 회원 탈퇴");
			System.out.println("0. 뒤로가기");

			System.out.println("\n메뉴 선택 : ");
			input = sc.nextInt();
			System.out.println();
			
			switch(input) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 0: 
			default: System.out.println("메뉴에 작성된 번호만 입력해주세요.");
			}
			/* 회원기능 (Member View, Service, DAO, member-query.xml)
			 * 
			 * 1. 내 정보 조회(   selectMyInfo()   )
			 * 2. 회원 목록 조회(아이디, 이름, 성별) (  selectAll()  )
			 * 3. 내 정보 수정(이름, 성별)   (  updateMember()  )
			 * 4. 비밀번호 변경(현재 비밀번호, 새 비밀번호, 새 비밀번호 확인)  
				(  updatePw()  )
			 * 5. 회원 탈퇴  (  secession() )
			 */
		}while(input != 0);
	}

}
