

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 * Servlet implementation class News
 */
@WebServlet("/News")
public class News extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Charset UTF8_CHARSET = Charset.forName("UTF-8");

    /**
     * @see HttpServlet#HttpServlet()
     */
    public News() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");

		final PrintWriter out = response.getWriter();
		class RSSNews{
		public void getNews(String URLAddress, String title) throws ServletException, IOException{
			URL url = new URL(URLAddress);
	        HttpURLConnection httpcon = (HttpURLConnection)url.openConnection();
	        // Reading the feed
	        SyndFeedInput input = new SyndFeedInput();
	        SyndFeed feed;
			try {
				feed = input.build(new XmlReader(httpcon));
				 List entries = feed.getEntries();
			     Iterator itEntries = entries.iterator();
			     out.print("<h2>" + title + "</h2>");
			     for(int i = 0; i < 4 && itEntries.hasNext(); i++){
			    	 SyndEntry entry = (SyndEntry) itEntries.next();
			    	 out.print("<h3><a href=\"" + entry.getLink() + "\">" + entry.getTitle() + "</a></h3>");
			    	 out.print("<div>" + entry.getDescription().getValue() + "</div>");
			     }
			} catch (IllegalArgumentException | FeedException e) {
				e.printStackTrace();
			}
		}
		}
		RSSNews news = new RSSNews();
		out.print("<html lang=\"bg\">");
		out.print("<head><meta charset=\"utf-8\"></head>");
	    out.print("<body>");
		news.getNews("http://www.dnevnik.bg/rssc/?rubrid=1657", "Новини от България");
		news.getNews("http://www.sportal.bg/uploads/rss_category_5.xml", "Волейбол");
		news.getNews("http://www.dnevnik.bg/rssc/?rubrid=1660", "Технологии");
		out.print("</body>");
		out.print("</html>");
	}
}
