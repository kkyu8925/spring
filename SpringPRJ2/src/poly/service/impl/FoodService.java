package poly.service.impl;

import static poly.util.CmmUtil.nvl;

import java.util.Iterator;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import poly.dto.FoodDTO;
import poly.persistance.mapper.IFoodMapper;
import poly.service.IFoodService;
import poly.util.DateUtil;

@Service("FoodService")
public class FoodService implements IFoodService {

	@Resource(name = "FoodMapper")
	private IFoodMapper foodMapper;

	// 로그 파일 생성 및 로그 출력을 위한 Log4j 프레임워크의 자바 객체
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * JSOUP 라이브러리를 통한 CGV 영화 순위 정보가져오기
	 */

	@Override
	public int getFoodInfoFromWEB() throws Exception {
		// 로그찍기 ( 추후 찍은 로그를 통해 이함수에 접근했는지 파악하기 용이하다.)
		log.info(this.getClass().getName() + ".getFoodInfoFromWEB start!");

		int res = 0; // 크롤링 결과 (0보다 크면 크롤링성공)

		// 식단 홈페이지
		String url = "http://www.kopo.ac.kr/kangseo/content.do?menu=262";

		// JSOUP 라이브러리를 통해 사이트 접속되면, 그사이트의 전체 HTML소스 저장할 변수
		Document doc = null;

		// 사이트 접속(http 프로토콜만 가능, https 프로토콜은 보안상 안됨)
		doc = Jsoup.connect(url).get();

		// 웹페이지의 전체 소스 중 일부 태그를 선택하기 위해 사용
		Elements element = doc.select("tbody tr");

		// Iterator을 사용하여 정보를 가져오기
		Iterator<Element> day = element.select("td:nth-child(1)").iterator(); // 요일
		Iterator<Element> food_nm = element.select("td:nth-child(3)").iterator(); // 식단

		FoodDTO pDTO = null;

		// 수집된 데이터 DB에 저장하기
		while (day.hasNext()) {
			pDTO = new FoodDTO(); // 수집된 정보를 DTO에 저장하기 위해 메모리에 올리기

			// 수집시간을 기본키(pk)로 사용
			pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMdd24hmmss"));

			// 요일
			pDTO.setDay(nvl(day.next().text()).trim());

			// 식단표
			pDTO.setFood_nm(nvl(food_nm.next().text(),"0").trim());

			// 등록자
			pDTO.setReg_id("admin");
			log.info(this.getClass().getName()+".getFood_nm : "+pDTO.getFood_nm());

			if (pDTO.getFood_nm().equals("0")) {
				continue;
			} else {
				res += foodMapper.InsertFoodInfo(pDTO);
			}
		}

		// 로그찍기
		log.info(this.getClass().getName() + ".getFoodInfoFromWEB end!!");

		return res;

	}
}