package yiyi;

import java.sql.DriverManager;


/**
 * @ClassName TestConnaction
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author HuGuangJun
 * @date 2016年4月28日 上午10:20:26
 */
public class TestConnaction {

	// 测试连接
	public static void main(String[] args) throws Exception {
		
		//连接数据必不可少的四个信息：
		  String url="jdbc:oracle:thin:@10.31.56.113:1521:orcl";
		  String user="ybtbibs";
		  String password="oracle";
		  String driverClass="oracle.jdbc.driver.OracleDriver";
		  //加载驱动
		  Class.forName(driverClass);
		  java.sql.Connection connection = DriverManager.getConnection(url, user, password);
		  System.out.println(connection);
	}
}
