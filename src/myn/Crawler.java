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
			System.out.println(url+"���ӽ�������ȷ����");
		}
	}
	public String DownloadHtmlStr(){
		StringBuffer htmlsrc = new StringBuffer();
		try {
			if(flag==0)
					throw new Exception("δָ����վurl!");
			System.out.println("\nĿ����վ��"+url.getHostName());
			URL src = new URL(url.getHostName());
			System.out.println("������ȡ"+url.getHostName()+":"+url.getHostAddress());
			System.out.println();
			//��ȡ ��Դ���ֽ�������
			InputStream _in = src.openStream();
			//���ֽ������� ת��Ϊ �ַ�������
			InputStreamReader in =new InputStreamReader(_in,"utf-8");
			//Ϊ�ַ���������ӻ���
			BufferedReader buf = new BufferedReader(in);
			String data = buf.readLine();
			int n=1;
			while(data!=null){
				htmlsrc.append(data+"\n");
				data = buf.readLine();
				n++;
			}
			System.out.println("�Ѷ�ȡ"+n+"��HTML����");
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
		System.out.println("������: ");
		System.out.println("-------------------------------------------------------------");
		Document doc = Jsoup.parse(htmlStr);
		Element title = doc.getElementsByTag("head").first().getElementsByTag("title").first();
		System.out.println("���� \n: "+title.html());
		
		Element temp = doc.getElementById("blk_yw_01").getElementById("syncad_1"); 
		System.out.println("Ҫ�� : ");
		for (Element item : temp.getElementsByTag("h1")){
			System.out.println("  - "+item.getElementsByTag("a").first().html());
		}
		
		 temp = doc.getElementById("Blk01_Focus_Cont");
		System.out.println("����ͼ : ");
		for (Element item : temp.getElementsByTag("div")){
			System.out.println("  - "+item.getElementsByTag("a").first().html());
		}
		
		System.out.println("�������� : ");
		temp = doc.getElementById("blk_new_gnxw");
		for (Element item : temp.getElementsByTag("li")){
			System.out.println("  - "+item.getElementsByTag("a").first().html());
		}
		
		System.out.println("�������� : ");
		temp = doc.getElementById("blk_gjxw_01");
		for (Element item : temp.getElementsByTag("li")){
			System.out.println("  - "+item.getElementsByTag("a").first().html());
		}
		
	}

	
	public void printInetAddressInfo(){
		InetAddress inetAddress;
		try {
			System.out.println("Ŀ��InetAddress : ");
			inetAddress = this.url;
			System.out.println("HostName : "+inetAddress.getHostName());
			System.out.println("Address : "+inetAddress.getHostAddress());
			System.out.println();
			System.out.println("����InetAddress : ");
			inetAddress = InetAddress.getLocalHost();
			System.out.println("HostName : "+inetAddress.getHostName());
			System.out.println("Address : "+inetAddress.getHostAddress());	
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}		
	}

}
