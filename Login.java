import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {

	public static void main(String[] args) {
		Connection conn = null; // DB연결된 상태(세션)을 담은 객체
	    PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
	    PreparedStatement spstm=null;
	    ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
	    String sql="insert into 회원 values(?,?,?)";
	    String select="select ID,PW from 회원";
		String user = "sys as sysdba"; 
        String pw = "Gksmf1238";
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";	 
        String did=null;
        String dpw=null;
        try {
        Class.forName("oracle.jdbc.driver.OracleDriver");      
        conn = DriverManager.getConnection(url, user, pw);	       
        System.out.println("DB 연결성공");
       
        String id,pwd,phone;
        Scanner sc=new Scanner(System.in);
        System.out.println("아이디를 입력해주세요");
        id=sc.nextLine();
        System.out.println("비밀번호를 입력해주세요");
        pwd=sc.nextLine();
    	spstm=conn.prepareStatement(select);
    	rs=spstm.executeQuery();
    	while(rs.next()) {
    		did= rs.getString("ID");
    		dpw= rs.getString("PW");
    		if(did.equals(id)&&dpw.equals(pwd)) {
    			System.out.println("로그인에 성공했습니다");
    			break;
    		}
    	}
    	if(!did.equals(id)||!dpw.equals(pwd)) {
			System.out.println("아이디나 비밀번호를 잘못입력하셨습니다");
		}
         } catch (SQLException e) {
        	 System.out.println("sql오류");
        	 e.printStackTrace();
        }catch(Exception e) {
        	System.out.println("오류");
        }
        finally{
            // DB 연결을 종료한다.
            try{
                if ( rs != null ){rs.close();}   
                if ( pstm != null ){pstm.close();}   
                if ( conn != null ){conn.close(); }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
		   
            }
            
        }
    }
}
