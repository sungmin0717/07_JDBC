package edu.kh.todolist.service;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kh.todolist.dao.TodoListDao;
import edu.kh.todolist.dao.TodoListDaoImpl;
import edu.kh.todolist.dto.Todo;

public class TodoListServiceImpl implements TodoListService{

	private TodoListDao dao = new TodoListDaoImpl();
	
	
		
	
	
	
		
		

	
	
	@Override
	public Todo todoDetailView(int index) {
		Todo todo = dao.todoDetailView(index);
		return todo;
	}
	
	//-------------------------------------------------------------------------------------------------
	
	
	@Override
	public int todoAdd(String title, String detail) throws FileNotFoundException, IOException {
		Connection conn = getConnection();
		int result = dao.todoAdd(conn, title, detail);
		
		if(result > 0)	commit(conn);
		else			rollback(conn);
		close(conn);
		
		return result;
	}

	
	//-------------------------------------------------------------------------------------------------
	
	
	@Override
	public boolean todoComplete(int index) throws FileNotFoundException, IOException {
		return dao.todoComplete(index);
	}
	
	
	//-------------------------------------------------------------------------------------------------
		
	
	@Override
	public boolean todoUpdate(int index, String title, String detail) throws FileNotFoundException, IOException {
		
		return dao.todoUpdate(index, title, detail);
	}
	
	
	//-------------------------------------------------------------------------------------------------
	
	@Override
	public String todoDelete(int index) throws FileNotFoundException, IOException {
		
		Todo deleteTarget = dao.todoDelete(index);
		
		if(deleteTarget != null) 	return deleteTarget.getTitle();
		return null;
	}


	@Override
	public String dateFormat(LocalDateTime regDate) {
		
		
		return null;
	}

	@Override
	public Map<String, Object> todoListFullView()throws Exception{
		
		Connection conn = getConnection();
		
		return null;
	}




}
