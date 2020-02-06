package naver;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import domain.MovieDTO;
import persistance.MovieDAO;

public class NaverMovie {
	public static void main(String[] args) throws IOException {

		// 네이버 하이큐!! 땅 VS 하늘 영화 댓글 수집
		// Oracle DB에 저장
		// DAO, DTO 그대로 DAUM에서 사용하던거 사용

		String base = "https://movie.naver.com/movie/bi/mi/pointWriteFormList.nhn?code=191431&type=after&isActualPointWriteExecute=false&isMileageSubscriptionAlready=false&isMileageSubscriptionReject=false&page=";
		int page = 1;
		int count = 1;
		int score = 0;
		int regdate = 0;
		String writer = "";
		String content = "";
		String title = "하이큐!! 땅 VS 하늘";
		MovieDAO mDao = new MovieDAO();

		int total = 0;
		double scoreAvg = 0.0;

		String compare = "";

		label: while (true) {
			String url = base + page;
			Document doc = Jsoup.connect(url).get();
			Elements review = doc.select("div.score_result li");

			for (int i = 0; i < review.size(); i++) {
				score = Integer.parseInt(review.get(i).select("div.star_score em").text());
				writer = review.get(i).select("div.score_reple dl dt em a").text();

				content = review.get(i).select("div.score_reple p").text();

				String basedate = review.get(i).select("div.score_reple dl dt em:nth-child(2)").text();
				String subdate = basedate.substring(0, 10);
				regdate = Integer.parseInt(subdate.replace(".", ""));

				MovieDTO mDto = new MovieDTO(title, content, writer, score, "naver", regdate);
				mDao.addMovie(mDto);

				if (i == 0) {
					if (compare.equals(writer)) {
						break label;
					} else {
						compare = writer;
					}
				}

				System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
				System.out.println("▒▒ " + count + "건");
				System.out.println("▒▒ 평점 : " + score);
				System.out.println("▒▒ 작성자 : " + writer);
				System.out.println("▒▒ 내용 : " + content);
				System.out.println("▒▒ 작성일 : " + regdate);

				count++;
				total += score;
			}
			System.out.println("■■ " + page + " 페이지");
			page++;
		}

		// 평균 평점 계산
		scoreAvg = (double) total / count;

		// 소수점 첫번째 자리까지 출력 (버림)
		double result = Math.floor(scoreAvg);

		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		System.out.println("▒▒ '" + title + "' Daum 영화 평점 수집 결과 ");
		System.out.println("▒▒ " + page + "페이지에서");
		System.out.println("▒▒ 총 " + count + "건의 평점을 수집완료");
		System.out.println("▒▒ 평균평점은 " + result + "점 :)");
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");

	}
}
