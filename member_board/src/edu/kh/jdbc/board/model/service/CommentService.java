package edu.kh.jdbc.board.model.service;

import edu.kh.jdbc.board.model.dao.CommentDAO;
import edu.kh.jdbc.board.model.vo.Comment;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;

public class CommentService {
	
	private CommentDAO dao = new CommentDAO();
	
	/** 댓글 등록 서비스 
	 * @param comment
	 * @return
	 */
	public int insertComment(Comment comment) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.insertComment(conn, comment);
		
		if(result > 0) commit(conn);
		else 		   rollback(conn);
		
		close(conn);
		
		return result;
	}

}
