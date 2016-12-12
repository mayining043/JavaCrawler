package myn;

import java.io.*;
import java.net.*;
import org.jsoup.*;
import org.jsoup.nodes.*;


public class Crawler {
	protected int flag=0;
	public InetAddress url;
	public Crawler(String u){
		try {
			this.url=InetAddress.getByAddress(u,new byte[]{127,0,0,1});
			flag=1;
		} catch (UnknownHostException e) {
			flag=0;
			System.out.println(url+"链接解析不正确。。");
		}
	}
	public String DownloadHtmlStr(){
		StringBuffer htmlsrc = new StringBuffer();
		try {
			if(flag==0)
					throw new Exception("未指定网站url!");
			System.out.println("\n目标网站："+url.getHostName());
			URL src = new URL(url.getHostName());
			System.out.println("正在爬取"+url.getHostName()+":"+url.getHostAddress());
			System.out.println();
			//获取 资源的字节输入流
			InputStream _in = src.openStream();
			//将字节输入流 转换为 字符输入流
			InputStreamReader in =new InputStreamReader(_in,"utf-8");
			//为字符输入流添加缓冲
			BufferedReader buf = new BufferedReader(in);
			String data = buf.readLine();
			int n=1;
			while(data!=null){
				htmlsrc.append(data+"\n");
				data = buf.readLine();
				n++;
			}
			System.out.println("已读取"+n+"行HTML代码");
			buf.close();
			in.close();
			_in.close();	
		} catch(Exception e){
			System.out.println(e);
		}
		return htmlsrc.toString();
	}
	public void ParseAndPrint(String htmlStr) {
		System.out.println();
		System.out.println("解析中: ");
		System.out.println("-------------------------------------------------------------");
		Document doc = Jsoup.parse(htmlStr);
		Element title = doc.getElementsByTag("head").first().getElementsByTag("title").first();
		System.out.println("标题 \n: "+title.html());
		
		Element temp = doc.getElementById("blk_yw_01").getElementById("syncad_1"); 
		System.out.println("要闻 : ");
		for (Element item : temp.getElementsByTag("h1")){
			System.out.println("  - "+item.getElementsByTag("a").first().html());
		}
		
		 temp = doc.getElementById("Blk01_Focus_Cont");
		System.out.println("焦点图 : ");
		for (Element item : temp.getElementsByTag("div")){
			System.out.println("  - "+item.getElementsByTag("a").first().html());
		}
		
		System.out.println("国内新闻 : ");
		temp = doc.getElementById("blk_new_gnxw");
		for (Element item : temp.getElementsByTag("li")){
			System.out.println("  - "+item.getElementsByTag("a").first().html());
		}
		
		System.out.println("国际新闻 : ");
		temp = doc.getElementById("blk_gjxw_01");
		for (Element item : temp.getElementsByTag("li")){
			System.out.println("  - "+item.getElementsByTag("a").first().html());
		}
		
	}

	
	public void printInetAddressInfo(){
		InetAddress inetAddress;
		try {
			System.out.println("目标InetAddress : ");
			inetAddress = this.url;
			System.out.println("HostName : "+inetAddress.getHostName());
			System.out.println("Address : "+inetAddress.getHostAddress());
			System.out.println();
			System.out.println("本地InetAddress : ");
			inetAddress = InetAddress.getLocalHost();
			System.out.println("HostName : "+inetAddress.getHostName());
			System.out.println("Address : "+inetAddress.getHostAddress());	
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}		
	}

}
