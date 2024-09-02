package edu.kh.todolist.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.todolist.common.JDBCTemplate;
import edu.kh.todolist.dto.Todo;

public class TodoListDaoImpl implements TodoListDao{

	
	private List<Todo> todoList = null;
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	
	
	
	
		
		
		
		
		
	
	
	
	public TodoListDaoImpl() {
		
		try {
			
			String filePath = 
					JDBCTemplate.class.getResource
						("edu\\kh\\todolist\\sql\\sql.xml").getPath(); 
			
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	
	
	
	
	//-------------------------------------------------------------------------------------------------
	
	
	@Override
	public Todo todoDetailView(int index) {
		
		// TodoList 범위 초과 시 null 반환
		if(index < 0 || index >= todoList.size()) return null;
		
		return todoList.get(index);
	}
	

	
	//-------------------------------------------------------------------------------------------------
	
	
	@Override
	public int todoAdd(Todo todo) throws FileNotFoundException, IOException {
		
		// 객체 출력 스트림 생성
		
		if(todoList.add(todo)) {
			saveFile();
			return todoList.size() - 1; // 마지막 인덱스 번호 반환
		}
		
		return -1;
	}

	
	//-------------------------------------------------------------------------------------------------
	
	
	@Override
	public boolean todoComplete(int index) throws FileNotFoundException, IOException {
		// TodoList 범위 초과 시 false 반환
		if(index < 0 || index >= todoList.size()) return false;
		
		String complete = todoList.get(index).getComplete();
		
		saveFile();
		
		return true;
	}
	
	
	//-------------------------------------------------------------------------------------------------
	
	
	@Override
	public boolean todoUpdate(int index, String title, String detail) throws FileNotFoundException, IOException {
	
		
		// 수정된 내용 + 이전 Todo의 완료 여부, 등록일을 담은 Todo 객체 생성 
		Todo newTodo = new Todo();
		
		newTodo.setTitle(title);
		newTodo.setDetail(detail);
		newTodo.setComplete(todoList.get(index).getComplete());
		newTodo.setRegDate(todoList.get(index).getRegDate());

		// index 번째 Todo를 새로운 Todo로 변경
		if( todoList.set(index, newTodo) != null ) {
			
			
			return true;
		}
		return false;
	}
	
	
	//-------------------------------------------------------------------------------------------------
	
	
	@Override
	public Todo todoDelete(int index) throws FileNotFoundException, IOException{
		
		if(index < 0 || index >= todoList.size()) return null;
		
		Todo deletedTarget = todoList.remove(index);
		
		saveFile();
		
		return deletedTarget;
	}


	@Override
	public void saveFile() throws FileNotFoundException, IOException {
		
	}






	@Override
	public List<Todo> todoListFullView() {
		// TODO Auto-generated method stub
		return null;
	}
}
