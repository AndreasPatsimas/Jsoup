package jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

public class InlineWriteToView {

	public static void main(String[] args) throws IOException {

		String html = "<html><head><title>Sample Title</title></head>" + "<body>" + "<p>Sample Content</p>"
				+ "<div id='sampleDiv'><a href='www.google.com'>Google</a>" + "<h3><a>Sample</a><h3>" + "</div>"
				+ "<div id='imageDiv' class='header'><img name='google' src='google.png' />"
				+ "<img name='yahoo' src='yahoo.jpg' />" + "</div>" + "</body></html>";
		Document document = Jsoup.parse(html);

		// a with href
		Elements links = document.select("a[href]");

		for (Element link : links) {
			System.out.println("Href: " + link.attr("href"));
			System.out.println("Text: " + link.text());
		}

		// img with src ending .png
		Elements pngs = document.select("img[src$=.png]");

		for (Element png : pngs) {
			System.out.println("Name: " + png.attr("name"));
		}

		// div with class=header
		Element headerDiv = document.select("div.header").first();
		System.out.println("Id: " + headerDiv.id());

		// direct a after h3
		Elements sampleLinks = document.select("h3 > a");

		for (Element link : sampleLinks) {
			System.out.println("Text: " + link.text());
		}

		// Loading from URL
		System.out.println("...................");
		String url = "http://localhost:8080/jsoup/";
		Document document1 = Jsoup.connect(url).get();
		System.out.println(document1.title());
		
		//Extract HTML
		System.out.println("...................");
		String html1 = "<html><head><title>Sample Title</title></head>"
		         + "<body>"
		         + "<p>Sample Content</p>"
		         + "<div id='sampleDiv'><a href='www.google.com'>Google</a>"
		         + "<h3><a>Sample</a><h3>"
		         +"</div>"
		         +"</body></html>";
		      Document document2 = Jsoup.parse(html1);
		      
		      Element link = document2.select("a").first();         

		      System.out.println("Outer HTML: " + link.outerHtml());
		      System.out.println("Inner HTML: " + link.html());
		      
		      //Set HTML
		      System.out.println("...................");
		      String html2 = "<html><head><title>Sample Title</title></head>"
		    	         + "<body>"
		    	         + "<div id='sampleDiv'><a id='googleA' href='www.google.com'>Google</a></div>"
		    	         +"</body></html>";
		    	      Document document3 = Jsoup.parse(html);

		    	      Element div = document3.getElementById("sampleDiv");
		    	      System.out.println("Outer HTML Before Modification :\n"  + div.outerHtml());
		    	      div.html("<p>This is a sample content.</p>");
		    	      System.out.println("Outer HTML After Modification :\n"  + div.outerHtml());
		    	      div.prepend("<p>Initial Text</p>");
		    	      System.out.println("After Prepend :\n"  + div.outerHtml());
		    	      div.append("<p>End Text</p>");
		    	      System.out.println("After Append :\n"  + div.outerHtml());
		    	      
		    	      //Sanitize(Καθαρίστε)
		    	      System.out.println("...................");
		    	      String html3 = "<p><a href='http://example.com/'"
		    	    	         +" onclick='checkData()'>Link</a></p>";

		    	    	      System.out.println("Initial HTML: " + html3);
		    	    	      String safeHtml =  Jsoup.clean(html3, Whitelist.basic());

		    	    	      System.out.println("Cleaned HTML: " +safeHtml);

	}

}
