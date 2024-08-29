package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Practice1 {
	public static void main(String[] args) {

		/* 1. JDBC 객체 참조 변수 선언 */
		Connection conn = null;// 객체를 저장할 객체
		PreparedStatement pstmt = null; //준비된 Statement 값을 편하게 넣을수잇음
		ResultSet rs = null;

		/* 2. DriverManger를 이용해서 Connection 객체 생성 */
		try {
			// 내 주소
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String userName = "KH_KSM";
			String password = "KH1234";

			conn = DriverManager.getConnection(url, userName, password);
			//객체를 저장하는것에 url userName passowrd 를 가져옴
			
			/* 3. SQL 작성 */

			Scanner sc = new Scanner(System.in);

			System.out.print("조회할 성별(M/F) : ");
			String gender = sc.nextLine().toUpperCase();
			System.out.print("급여 범위(최소, 최대 순서로 작성) : ");
			int min = sc.nextInt();
			int max = sc.nextInt();

			System.out.print("급여 정렬(1.ASC, 2.DESC)  : ");
			int sort = sc.nextInt();
			


			String sql = """

					SELECT
						EMP_ID ,
						EMP_NAME ,
						DECODE(SUBSTR(EMP_NO, 8 , 1), '1', 'M', '2', 'F') gender,
						SALARY ,
						JOB_NAME ,
						NVL(DEPT_TITLE, '없음') DEPT_TITLE

					FROM
						EMPLOYEE JOIN JOB USING (JOB_CODE)
						JOIN DEPARTMENT ON (DEPT_ID = DEPT_CODE)

					WHERE
						SALARY BETWEEN ? AND ?
					AND
						DECODE(SUBSTR(EMP_NO, 8 , 1), '1', 'M', '2', 'F') = ?
					ORDER BY SALARY 


					""";
			//입력 받은 정렬 (array) 값에 따라서 sql에 
			// 오름 / 내림 차순 sql추가하기
			if (sort == 1) sql += "ASC";
			else            sql += "DESC";
				
			
			
			
			// 4. PreparesStatement 생성
			pstmt = conn.prepareStatement(sql);

			
			pstmt.setInt(1, min);
			pstmt.setInt(2, max);
			pstmt.setString(3, gender);
			
			rs = pstmt.executeQuery();
			
			System.out.println("사번 | 이름   | 성별 | 급여    | 직급명 | 부서명");
			System.out.println("--------------------------------------------------------");
			
			boolean flag = true;
			
			while(rs.next()) {
				flag = false; // while이 1회 이상 반복함 == 조회 결과가 있음
				String empId     = rs.getString("EMP_ID");
				String empName   = rs.getString("EMP_NAME");
				String gen   	 = rs.getString("GENDER");
				int    salary 	 = rs.getInt("SALARY");
				String jobName   = rs.getString("JOB_NAME");
				String deptTitle = rs.getString("DEPT_TITLE");
				
				System.out.printf(
						"%-5s | %3s | %-4s | %7d | %-3s  | %s \n",
						empId, empName, gen, salary, jobName, deptTitle);
			}
			
			if(flag) { // flag == true 인 경우 -> 조회 결과 없ㅇ므
				System.out.println("조회 결과 없음");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
				
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
