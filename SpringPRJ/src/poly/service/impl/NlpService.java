package poly.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import poly.dto.NlpDTO;
import poly.persistance.mapper.INlpMapper;
import poly.service.INlpService;
import poly.util.CmmUtil;
import poly.util.StringUtil;

@Service("NlpService")
public class NlpService implements INlpService {

	@Resource(name = "NlpMapper")
	private INlpMapper nlpMapper;

	// 로그 파일 생성 및 로그 출력을 위한 log4j 프레임워크의 자바 객체
	private Logger log = Logger.getLogger(this.getClass());

	// NLP_DIC를 ㄱㄴㄷㄹ 순으로 나눠서 저장한 이유는 전체 약 몇만건의 데이터를 조회하는 것보다
	// 일정한 범위를 지정하여 데이터 조회 횟수를 감소하기 위해서 나눔
	// 가나다 순으로 저장할 데이터 사전을(가~하까지)
	private Map<String, List<NlpDTO>> NLP_DIC = new HashMap<String, List<NlpDTO>>();

	/**
	 * PostConstruct은 처음 한번만 실행되도록 하는 어노테이션
	 * 
	 * 데이터 사전 변수 생성을 위한 함수로 최초 스프링 호출이 발행할 때 함수가 실행 실행 이후 더이상 실행 안함.
	 * 
	 * NLP_DIC 변수에 값을 저장함
	 */
	@Override
	@PostConstruct
	public void getWord() throws Exception {

		// 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
		log.info(this.getClass().getName() + ".getWord start");

		// 데이터 사전 조회하기 위한 변수를 저장할 DTO(ㄱ,ㄴ,ㄷ,ㄹ... 저장)
		NlpDTO pDTO = new NlpDTO();

		// 가나다 별 데이터가 저장될 변수
		List<NlpDTO> rList = null;

		// ㄱ실행
		pDTO.setWord("ㄱ");
		rList = nlpMapper.getWord(pDTO); // DB 조회

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㄱ", rList);

		// ㄲ실행
		pDTO.setWord("ㄲ");
		rList = nlpMapper.getWord(pDTO); // DB 조회

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㄲ", rList);

		// ㄴ실행
		pDTO.setWord("ㄴ");
		rList = nlpMapper.getWord(pDTO); // DB 조회

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㄴ", rList);

		// ㄷ실행
		pDTO.setWord("ㄷ");
		rList = nlpMapper.getWord(pDTO); // DB 조회

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㄷ", rList);

		// ㄸ실행
		pDTO.setWord("ㄸ");
		rList = nlpMapper.getWord(pDTO); // DB 조회

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㄸ", rList);

		// ㄹ실행
		pDTO.setWord("ㄹ");
		rList = nlpMapper.getWord(pDTO); // DB 조회

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㄹ", rList);

		// ㅁ실행
		pDTO.setWord("ㅁ");
		rList = nlpMapper.getWord(pDTO); // DB 조회

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㅁ", rList);

		// ㅂ실행
		pDTO.setWord("ㅂ");
		rList = nlpMapper.getWord(pDTO); // DB 조회

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㅂ", rList);

		// ㅃ실행
		pDTO.setWord("ㅃ");
		rList = nlpMapper.getWord(pDTO); // DB 조회

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㅃ", rList);

		// ㅅ실행
		pDTO.setWord("ㅅ");
		rList = nlpMapper.getWord(pDTO); // DB 조회

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㅅ", rList);

		// ㅆ실행
		pDTO.setWord("ㅆ");
		rList = nlpMapper.getWord(pDTO); // DB 조회

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㅆ", rList);

		// ㅇ실행
		pDTO.setWord("ㅇ");
		rList = nlpMapper.getWord(pDTO); // DB 조회

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㅇ", rList);

		// ㅈ실행
		pDTO.setWord("ㅈ");
		rList = nlpMapper.getWord(pDTO); // DB 조회

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㅈ", rList);

		// ㅉ실행
		pDTO.setWord("ㅉ");
		rList = nlpMapper.getWord(pDTO); // DB 조회

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㅉ", rList);

		// ㅊ실행
		pDTO.setWord("ㅊ");
		rList = nlpMapper.getWord(pDTO); // DB 조회

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㅊ", rList);

		// ㅋ실행
		pDTO.setWord("ㅋ");
		rList = nlpMapper.getWord(pDTO); // DB 조회

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㅋ", rList);

		// ㅌ실행
		pDTO.setWord("ㅌ");
		rList = nlpMapper.getWord(pDTO); // DB 조회

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㅌ", rList);

		// ㅍ실행
		pDTO.setWord("ㅍ");
		rList = nlpMapper.getWord(pDTO); // DB 조회

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㅍ", rList);

		// ㅎ실행
		pDTO.setWord("ㅎ");
		rList = nlpMapper.getWord(pDTO); // DB 조회

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㅎ", rList);

		// 로그 찍기(추후 찍은 로그를 통해 이함수에 접근했는지 파악하기 용이)
		log.info(this.getClass().getName() + ".getWord End");
	}

	/**
	 * 감정 분석을 위한 문장 나누는 전처리 단계
	 * 
	 * @param 분석할 문장 정보
	 * @return 감정 분석 평가 결과
	 */
	@Override
	public int preProcessWordAnalysisForMind(NlpDTO pDTO) throws Exception {

		// 로그 찍기
		log.info(this.getClass().getName() + ".WordAnalysisForMind start");

		int res = 0;

		// 분석할 문장(특수 문자 모두 제거) 
		String text = CmmUtil.nvl(pDTO.getWord()).replaceAll("[^\\uAC00-\\uD7A3xfe0-9a-zA-Z\\\\s]", " "); // [^가-힣0-9A-Za-z]

		// 연속된 스페이스(공백) 제거
		text = text.replaceAll("\\s{2,}", " ");

		log.info("text : " + text);

		// 공백으로 단어 나누기
		String[] textArr = text.split(" ");

		log.info("textArr.length : " + textArr.length);

		// 데이터 사전의 단어의 수가 최대 3으로 정의
		if (textArr.length < 4) {

			// 문장의 첫글자
			String firstWord = textArr[0].substring(0, 1);

			// 데이터 분석 진행
			res = WordAnalysisForMind(firstWord, text);
		} else {

			// 최대 반복 횟수
			int maxCnt = textArr.length - 2;

			log.info("###textArr.length : " + textArr.length);
			log.info("###maxCnt : " + maxCnt);

			for (int i = 0; i < maxCnt; i++) {

				// 문장의 첫글자
				String firstWord2 = textArr[i].substring(0, 1);

				log.info("###반복횟수 : " + i);
				String text2 = textArr[i] + " " + textArr[i + 1] + " " + textArr[i + 2];

				res += WordAnalysisForMind(firstWord2, text2);
			}
		}

		log.info("res : " + res);

		// 로그 찍기
		log.info(this.getClass().getName() + ".WordAnalysisForMind ENd!");

		return res;
	}

	/**
	 * 감정 분석 처리 함수
	 * 
	 * @param 분석할 문장의 첫글자
	 * @param 분석할 문장
	 * @return 감정 분석 평가 결과
	 */
	@Override
	public int WordAnalysisForMind(String firstWord, String text) throws Exception {

		int res = 0;

		log.info("firstWord : " + firstWord);
		log.info("text : " + text);

		// 데이터 사전 종류
		String dicType = StringUtil.getFirstWord(firstWord);

		log.info("DIC type : " + dicType);

		// 데이터 사전에 존재하는 것만 분석 수행
		if (dicType.length() > 0) {

			// 문장의 첫글자를 통해 해당되는 데이터 사전 가져오기
			List<NlpDTO> rList = NLP_DIC.get(StringUtil.getFirstWord(firstWord));

			if (rList == null) {
				rList = new ArrayList<NlpDTO>();
			}

			Iterator<NlpDTO> it = rList.iterator();

			while (it.hasNext()) {
				NlpDTO rDTO = it.next();

				if (rDTO == null) {
					rDTO = new NlpDTO();
				}

				// 일치하는 값이 존재한다면,
				if (text.indexOf(CmmUtil.nvl(rDTO.getWord())) > -1) {
					log.info("DIC-word : " + CmmUtil.nvl(rDTO.getWord()));
					log.info("DIC-word getPolarity : " + CmmUtil.nvl(rDTO.getPolarity()));
					log.info("text : " + text);
					res += Integer.parseInt(CmmUtil.nvl(rDTO.getPolarity(), "0"));

					// 데이터 사전에 검색이 되어있기 떄문에 더이상 while 실행하지 않는다.
					break;
				}
			}
		}
		return res;
	}

}
