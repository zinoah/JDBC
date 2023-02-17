package edu.kh.jdbc.member.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.member.vo.Member;

import static edu.kh.jdbc.common.JDBCTemplate.*;

public class MemberDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt ;
	private ResultSet rs ;
	
	private Properties prop ;
	
	// 기본생성자  
	public MemberDAO() {
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("member-query.xml"));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	/** 회원 정보 조회 DAO
	 * @param conn
	 * @return memberList
	 * @throws Exception
	 */
	public List<Member> selectAll(Connection conn) throws Exception{
		
		// 결과 저장용 변수 선
		List<Member> memberList = new ArrayList<>();
		
		try {
			// SQL 얻어오기 
			String sql = prop.getProperty("selectAll");
			
			// Statement 객체 생성 
			stmt = conn.createStatement();
			
			// SQL(SELECT) 수행 후 결과(ResultSet) 반환 받기
			rs = stmt.executeQuery(sql);
			
			// 반복문(while)을 이용해서 조회 결과의 각 행에 접근
			while(rs.next()) {
				// 컬럼값을 얻어와 Member 객체 저장 후 List에 추가
				
				String memberId = rs.getString("MEMBER_ID");
				String memberName = rs.getString("MEMBER_NM");
				String memberGender = rs.getString("MEMBER_GENDER");
				
				Member member = new Member(memberId, memberName, memberGender);
				
				memberList.add(member);
				
			}
			
			
		} finally {
			// JDBC 객체자원 반환 
			close(rs);
			close(stmt);
		}
		// 조회 결과를 옮겨 담은 List 반환
		return memberList;
	}

	/**회원 정보 수정 DAO
	 * @param conn
	 * @param member
	 * @return result
	 */
	public int updateMember(Connection conn, Member member) throws Exception{
		// 결과 저장용 변수 생성
		int result = 0;
		
		try {
			// SQL 얻어오기
			String sql = prop.getProperty("updateMember");
			
			// PreparedStatement 객체 생성 
			pstmt = conn.prepareStatement(sql);
			
			// ? 알맞은 값 대입 
			pstmt.setString(1, member.getMemberName());
			pstmt.setString(2, member.getMemberGender());
			pstmt.setInt(3, member.getMemberNo());
			
			// SQL 수행 후 결과 반환 받기 (행의 개수)
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 비밀번호 변경 DAO
	 * @param conn
	 * @param currentPw
	 * @param newPw1
	 * @param memberNo
	 * @return
	 */
	public int updatePw(Connection conn, String currentPw, String newPw1, int memberNo) throws Exception {
		// 결과 저장용 변수 생성
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("updatePw");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, newPw1);
			pstmt.setInt(2, memberNo);
			pstmt.setString(3, currentPw);
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		return 0;
	}


	/**회원 탈퇴 DAO
	 * @param conn
	 * @param memberPw
	 * @param memberNo
	 * @return result;
	 * @throws Exception
	 */
	public int secession(Connection conn, String memberPw, int memberNo) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("secession");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			pstmt.setString(2, memberPw);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt); 
		}
		
		return result;
	}
	



	
	

}