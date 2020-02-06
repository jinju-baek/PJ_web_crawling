package mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlMapConfig {
	private static SqlSessionFactory sqlSessionFactory;
	
	static { // 정적블럭, 클래스 로딩시 1회만 실행됨
		String resource = "mybatis/Configuration.xml";
		try {
			Reader reader = Resources.getResourceAsReader(resource); // resource변수 경로에 있는 파일을 한 줄씩 읽어서 reader에 넣음
			
			if(sqlSessionFactory == null) {
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader); // 빌드 패턴
			}
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static SqlSessionFactory getSqlSession() {
		return sqlSessionFactory;
	}
}
