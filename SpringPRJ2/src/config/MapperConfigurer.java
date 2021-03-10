package config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;

// 오라클과 같은 데이터베이스를 연결하기 위한 자바객체를 생성하는 로직
// sqlSession이라는 이름으로 자바와 오라클을 연결함(DBConn과 같은 역할)
public class MapperConfigurer extends MapperScannerConfigurer {

	/**
	 * 기본 정보(anntationClass, sqlSessionFactoryBeanName)으로 설정한다.
	 */
	public MapperConfigurer() {
		super.setAnnotationClass(Mapper.class);
		super.setSqlSessionFactoryBeanName("sqlSession");
	}

}
