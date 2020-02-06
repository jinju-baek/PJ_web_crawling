/* 
 * 다음 뉴스 목록에서 각 기사마다 제목과 본문을 수집
 * (1page내의 목록만 ex) 목록 10개 >> 10건의 기사
 */ 

package daum;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ListNews {
	public static void main(String[] args) throws IOException{
		String url = "https://news.daum.net/breakingnews/digital";
		Document doc = Jsoup.connect(url).get();
		
		Elements urls = doc.select("ul.list_allnews strong.tit_thumb > a.link_txt");

		int count = 0;

		for(Element element : urls) {
			count ++;
			// attr()을 사용하여 원하는 속성값 추출
			String href = element.attr("href");
			
			// url문서에 전체 태그를 select 함
			Document docnews = Jsoup.connect(href).get();
			
			// 전체 태그에서 원하는 항목만 Select함
			Elements title = docnews.select("h3.tit_view"); // 제목 추출
			Elements content = docnews.select("div#harmonyContainer"); // 본문 추출
			
			// .text() >> 태그 및 속성은 지우고 content 내용만 추출
			System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■" + count + "건 ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
			System.out.println(title.text()); // 제목 출력
			System.out.println(content.text()); // 제목 출력
		}
		System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.println("■■ Daum 수집한 뉴스 총 " + count + "건 수집 ■■");
	}
}
