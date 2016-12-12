package myn;

public class Main {
	public static void main(String[]args){
		Crawler bug=new Crawler("http://news.sina.com.cn/");
		bug.printInetAddressInfo();
		String html=bug.DownloadHtmlStr();
		bug.ParseAndPrint(html);
	}
}
