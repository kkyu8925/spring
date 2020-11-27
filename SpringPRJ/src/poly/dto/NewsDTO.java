package poly.dto;

public class NewsDTO {

    // 호출하는 url
    private String url;

    // https://developers.naver.com/docs/search/news/
    // api 응답 피라미터
    private String title; // 개별 검색 결과
    private String originallink; // 검색 결과 문서의 제공 언론사 하이퍼텍스트 link
    private String link; // 검색 결과 문서의 제공 네이버 하이퍼텍스트 link
    private String description; // 검색 결과 문서의 내용을 요약한 패시지 정보
    private String pubDate; // 검색 결과 문서가 네이버에 제공된 시간

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginallink() {
        return originallink;
    }

    public void setOriginallink(String originallink) {
        this.originallink = originallink;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
}
