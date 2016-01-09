package pl.rkarczew.webApp;

	import java.net.URI;

	public class Test {
		public static void main(String[] args) {
		    String r = "http://karczewskirl.adbeck.pl:8181";
			URI uri = URI.create(r);
			System.out.println(uri.getHost());
			// prints null
		}
	}
