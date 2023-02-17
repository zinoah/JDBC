package edu.kh.jdbc.board.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.board.model.vo.Board;
import static edu.kh.jdbc.common.JDBCTemplate.*;

public class BoardDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public BoardDAO() {
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("board-query.xml"));
			
		}catch(Exception e) {
			
		}
		
	}
	
	/** 게시글 목록 조회 DAO
	 * @param conn
	 * @return
	 */
	public List<Board> selectAllBoard(Connection conn) throws Exception{
		
		List<Board> boardList = new ArrayList<>();
		
		try {
			// SQL 얻어오기
			String sql = prop.getProperty("selectAllBoard");
			
			// Statement 객체 생성 
			stmt = conn.createStatement();
			
			// SQL 수행 후 REsultSet결과 반환 받기
			rs = stmt.executeQuery(sql);
			
			// ResultSet에 저장된 값을 List 옮겨 담
			while(rs.next()) {
				int boardNo = rs.getInt("BOARD_NO");
				String boardTitle = rs.getString("BOARD_TITLE");
				String memberName = rs.getString("MEMBER_NM");
				int readCount = rs.getInt("READ_COUNT");
				String createDate = rs.getString("CREATE_DT");
				int commentCount = rs.getInt("COMMENT_COUNT");
				
				Board board = new Board();
				board.setBoardNo(boardNo);
				board.setBoardTitle(boardTitle);
				board.setMemberName(memberName);
				board.setReadCount(readCount);
				board.setCreateDate(createDate);
				board.setCommentCount(commentCount);
				
				
				boardList.add(board);
			}
		}finally {
			close(rs);
			close(stmt);
		}
		return boardList;
	}

	/** 다음 게시글 번호 생성 DAO
	 * @param conn
	 * @return boardNo
	 * @throws Exception
	 */
	public int nextBoardNo(Connection conn) throws Exception{
		
		int boardNo = 0;
		
		try {
			String sql = prop.getProperty("nextBoardNo");
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery(); // select 수행
			
			if(rs.next()) { // 조회 결과 1행 밖에 없음
				boardNo = rs.getInt(1); // 첫 번째 컬럼값을 얻어와 boardNo 저장
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return boardNo;
	}

	/** 게시글 등록 DAO
	 * @param conn
	 * @param board
	 * @return
	 * @throws Exception
	 */
	public int insertBaord(Connection conn, Board board) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertBoard");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1 , board.getBoardNo());
			pstmt.setString(2 , board.getBoardTitle());
			pstmt.setString(3, board.getBoardContent());
			pstmt.setInt(4, board.getMemberNo());
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public Board selectBoard(Connection conn, int boardNo) {
		// TODO Auto-generated method stub
		return null;
	}

	/** 조회 수 증가 DAO
	 * @param conn
	 * @param boardNo
	 * @return result
	 */
	public int increaseReadCount(Connection conn, int boardNo) throws Exception{
		
		int result = 0; 
		
		try {
			String sql = prop.getProperty("increaseReadCount");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}

	/** 게시글 수정
	 * @param conn
	 * @param board
	 * @return
	 */
	public int updateBoard(Connection conn, Board board) throws Exception{
		
		int result = 0;
		try {
			
			String sql = prop.getProperty("updateBoard");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setInt(3, board.getBoardNo());
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

}
