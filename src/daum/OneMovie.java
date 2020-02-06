package daum;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import domain.MovieDTO;
import persistance.MovieDAO;

public class OneMovie {
	public static void main(String[] args) throws IOException {
		String base = "https://movie.daum.net/moviedb/grade?movieId=134091&type=netizen&page=";
		int page = 1; // 수집한 페이지수
		String url = base + page;
		int count = 0; // 전체댓글수
		String title = ""; // 영화제목
		int total = 0; // 평점을 모두 더하는 변수
		Double scoreAvg = 0.0; // 평균 평점
		MovieDAO mDao = new MovieDAO(); // 페이지를 돌면서 댓글을 수집

		while (true) {
			// 1페이지의 평점 10건 수집
			Document doc = Jsoup.connect(url).get();
			Elements reply = doc.select("ul.list_netizen div.review_info");

			// 영화 제목 수집
			Elements movieName = doc.select("h2.tit_rel");
			title = movieName.text();

			if (reply.isEmpty()) {
				break;
			}
			int score, regdate = 0;
			String writer, content, basedate, subdate = "";

			// 평점 1건 수집
			for (Element one : reply) {
				count++;
				writer = one.select("em.link_profile").text();
				score = Integer.parseInt(one.select("em.emph_grade").text());
				content = one.select("p.desc_review").text();
				basedate = one.select("span.info_append").text();
				subdate = basedate.substring(0, 10);
				regdate = Integer.parseInt(subdate.replace(".", ""));

				// 누적 평점을 계산
				total += score;

				// 1건에 평점을 DTO에 담음
				MovieDTO mDto = new MovieDTO(title, content, writer, score, "daum", regdate);

				// DTO에 담긴 1건의 평점을 DB에 저장!
				mDao.addMovie(mDto);
				System.out.println("▶▶▶▶▶▶▶▶▶▶▶▶▶" + count + "건◀◀◀◀◀◀◀◀◀◀◀◀◀◀");
				System.out.println("영화 : " + title);
				System.out.println("평점 : " + score);
				System.out.println("작성자 : " + writer);
				System.out.println("내용 : " + content);
				System.out.println("작성일 : " + regdate);
			}
			// 다음 페이지로 이동하기 위해 page+1증가
			page = page + 1;

			// 다음 페이지로 이동할때 URL작성
			url = base + page;
		}
		// 평균 평점 계산
		scoreAvg = (double) total / count;

		// 소수점 첫번째 자리까지 출력 (버림)
		double result = Math.floor(scoreAvg);

		// 수집 및 분석결과 출력
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		System.out.println("▒▒ '" + title + "' Daum 영화 평점 수집 결과 ");
		System.out.println("▒▒ " + (page - 1) + "페이지에서");
		System.out.println("▒▒ 총 " + count + "건의 평점을 수집완료");
		System.out.println("▒▒ 평균평점은" + result + "점 :)");
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
	}
}
