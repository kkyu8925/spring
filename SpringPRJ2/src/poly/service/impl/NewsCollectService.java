package poly.service.impl;


import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import poly.service.INewsCollectService;

@Service("NewsCollectService")
public class NewsCollectService implements INewsCollectService {
	
	private Logger log = Logger.getLogger(this.getClass()); 

	@Override
	public String doNaverNewsContents(String url) throws Exception {
		
		log.info(this.getClass().getName()+".doNaverNewsContents start");
		
		// JSOUP 라이브러리를 통해 사이트 접속, 그사이트의 전체 HTML 저장할 변수
		Document doc = null;
		
		// 사이트 접속(http 프로토콜만 가능, https 프로토콜은 보안상 안됨)
		doc = Jsoup.connect(url).get();
		
		// 네이버 뉴스 본문 내용에 대한 div소스를 가져옴
		// <div id="articleBodtContents" class="_article_body_contents">
		Elements newsContent = doc.select("div._article_body_contents");
		
		// 태그 내 텍스트 문구만 가져오기
		String res = newsContent.text();
		
		log.info(res);
		log.info(this.getClass().getName()+".doNaverNewsContents end");
		
		doc = null;
		
		return res;
	}

}
