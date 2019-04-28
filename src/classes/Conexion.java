package classes;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class Conexion {
 
	public static Connection conectar() {
		Connection con = null;
		
		try {
			String url = "jdbc:mysql://localhost/dentista?useUnicode=true&use"
					+ "JDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
					+ "serverTimezone=America/Mexico_City";
			con = DriverManager.getConnection(url,"root","dentista");
			if (con != null) {
				System.out.println("Conexion Satisfactoria");
			}
 
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return con;
	}
}