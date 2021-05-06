package poly.dto;

public class Melon2DTO {

    private String collectTime; // 수집 시간
    private String song; // 노래제목
    private String singer; // 가수

    private int singerCnt;

    public String getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(String collectTime) {
        this.collectTime = collectTime;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public int getSingerCnt() {
        return singerCnt;
    }

    public void setSingerCnt(int singerCnt) {
        this.singerCnt = singerCnt;
    }
}

