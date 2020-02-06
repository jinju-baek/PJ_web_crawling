package daum;

import java.io.IOException;
/*
 * 다음 뉴스 1건에서 제목과 본문을 수집 
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class OneNews {
	public static void main(String[] args) throws IOException {
		String url="https://entertain.v.daum.net/v/20200205143853187";
		
		// url문서에 전체 태그를 select 함
		Document doc = Jsoup.connect(url).get();
		
		// 전체 태그에서 원하는 항목만 Select함
		Elements title = doc.select("h3.tit_view"); // 제목 추출
		Elements content = doc.select("div#harmonyContainer"); // 본문 추출
		
		// .text() >> 태그 및 속성은 지우고 content 내용만 추출
		System.out.println(title.text()); // 제목 출력
		System.out.println(content.text()); // 제목 출력
	}
}
