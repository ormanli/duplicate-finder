import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLExecuter {

	private static void executeQuery(String query) {
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite::memory:");
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static List<String> executeQuery(String query) {
	}

	public static void createTables() throws Exception {
		executeQuery("drop table if exists person");
		executeQuery("create table files (file string, path string)");
	}

	public static void insertToTables(String file, String path) throws Exception {
		executeQuery("insert into files values('" + file + ", '" + path + "')");
	}

	public static List<String> getFileList() throws Exception {
		Class.forName("org.sqlite.JDBC");
		Connection connection = DriverManager.getConnection("jdbc:sqlite::memory:");
		Statement statement = connection.createStatement();
		statement.setQueryTimeout(30);

		ArrayList<String> fileList = new ArrayList<String>();

		ResultSet rs = statement.executeQuery("select file from files group by file having count(file)>1");

		while (rs.next()) {
			fileList.add(rs.getString("file"));
		}

		connection.close();

		return fileList;
	}

	public static List<String> getPathList(String file) throws Exception {
		Class.forName("org.sqlite.JDBC");
		Connection connection = DriverManager.getConnection("jdbc:sqlite::memory:");
		Statement statement = connection.createStatement();
		statement.setQueryTimeout(30);

		ArrayList<String> pathList = new ArrayList<String>();

		ResultSet rs = statement.executeQuery("select path from files where file='" + file + "'");

		while (rs.next()) {
			pathList.add(rs.getString("path"));
		}

		connection.close();

		return pathList;
	}
}
