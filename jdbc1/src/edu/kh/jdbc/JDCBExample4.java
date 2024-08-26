package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDCBExample4 {

	public static void main(String[] args) {
		
		
		// 부서명을 입력받아 
		// 해당 부서에 근무하는 사원의 
		// 사번, 이름,부서명, 직급명을
		// 직급 코드 오름 차순으로 조회.
		
		
		Connection conn = null; //DB 연결정보를 저장.
		Statement stmt = null; // sql을 실행하고반환 받는 객체.
		ResultSet rs = null; // select 수행 결과 저장용.
		
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String type = "jdbc:oracle:thin:@"; // 드라이버의 종류
			String host = "localhost"; // DB 서버 컴퓨터의 IP 또는 도메인 주소
			String port = ":1521"; // 프로그램 연결을 위한 구분 번호
			String dbName = ":XE"; // DBMS 이름(XE == eXpress Edition)
			String userName = "KH_KSM"; // 사용자 계정명
			String password = "KH1234"; // 계정 비밀번호
			
			conn = DriverManager.getConnection(type + host + port + dbName,userName,password );
			
			Scanner sc = new Scanner(System.in);
			System.out.println("부서명을 작성해주세요 : ");
			String name =sc.next();
			
			
			String sql = """
				SELECT
				EMP_ID,
				EMP_NAME,
				DEPT_TITLE,
				JOB_NAME
				FROM
				EMPLOYEE JOIN DEPARTMENT ON (DEPT_ID = DEPT_CODE)
				JOIN JOB USING (JOB_CODE)
				WHERE DEPT_TITLE = '""" + name  +
				"'  ORDER BY JOB_CODE ASC" ; 
					
			
			//객체 생성
			stmt = conn.createStatement();
			// 연결된 DB(Connection)에 SQL을 전달하고 결과를 반환 받을
			// Statement 객체를 생성함
			
			rs = stmt.executeQuery(sql);
			
			
			boolean flag = true; // 조회 결과가 없으면 true 있으면 false
			
			while(rs.next()){
				flag = false;
				String empId = rs.getString("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String deptTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				
				
				System.out.printf("%s / %s / %s / %s \n", empId, empName, deptTitle,
						jobName);
			}
			
			
			if(flag) {
				System.out.println("일치하는 부서 읍다");
			}
			try {
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
			
			
			
		
		
		
		
	}
}
