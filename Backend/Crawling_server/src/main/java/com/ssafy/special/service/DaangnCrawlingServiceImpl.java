package com.ssafy.special.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ssafy.special.dto.Product;

@Service
public class DaangnCrawlingServiceImpl implements DaangnCrawlingService {

	// carrot1 + "�˻���" + caroot2 + ��������ȣ(1���� ����)
	private static String carrot1 = "https://www.daangn.com/search/";
	private static String carrot2 = "/more/flea_market?page=";
	
	private static String carrotDetail = "https://www.daangn.com";
	
	@Override
	@Scheduled(fixedRate = 10000)
	public void crawling() {

		List<Product> productListAll= new ArrayList<Product>();
		List<Product> productList= null;
		
		//������ ���̽��� productName�� �˻��ؼ� �ش� ��ǰ�� ã������ �˻� query, ����Ű����, �ʼ�Ű������� ������
		// ���� �����ͺ��̽����� �������°����� ���� �ʿ�
		List<String> exceptionKeyword=new ArrayList<>();
		List<String> phoneCategory=new ArrayList<String>(); 
//		List<String> phoneCategory2=new ArrayList<String>(); 
		
		int page=1;
		while(true) {	
			productList=null;
			//��Ͽ��� ��ǰ ����Ʈ ������
			try {
				productList=listCrawling("������12", "������12 �ƽ�", Integer.toString(page));
				page++;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
			
			//���͸��� ���� �ʿ���� ǰ��� ���ܽ�Ŵ		
			//����Ű����
			exceptionKeyword.add("�̴�");			
//			exceptionKeyword.add("����");
//			exceptionKeyword.add("��ȯ");		
//			exceptionKeyword.add("��ϴ�");
//			exceptionKeyword.add("����");
//			
//			exceptionKeyword.add("��ü");
//			exceptionKeyword.add("���̽�");
//			exceptionKeyword.add("�ɿ���");
//			
//			exceptionKeyword.add("Ŀ��");
//			exceptionKeyword.add("�ʸ�");
//			exceptionKeyword.add("��ȭ����");
			
			
			//�ʼ� Ű����
			phoneCategory.add("����");
			phoneCategory.add("pro");	
			
//			phoneCategory2.add("�ƽ�");
//			phoneCategory2.add("�߽�");
//			phoneCategory2.add("max");
			
			
			keywordFilter(productList, exceptionKeyword, phoneCategory);
			
			//�� ������ũ�Ѹ��� ���� ��¥, �ð������� �޾ƿ�
			try {
				detailCrawling(productList);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
			for(Product product: productList) {
				if(product.getName()!=null)
					productListAll.add(product);
			}
			if(page>1)
				break;
		}
		for(Product product:productListAll) {
			System.out.println(product);
		}
		System.out.println("����: "+productListAll.size());
	}
	
	// ��� ũ�Ѹ��� ���� �˻�� �ش��ϴ� �Խñ� id�� ������
	private List<Product> listCrawling(String query, String productName, String page) throws IOException {
		String url = carrot1 + query + carrot2 + page;
		Document doc = Jsoup.connect(url).get();

		Elements contents = doc.select("article");

		List<Product> productList = new ArrayList<Product>();
		

		for (Element content : contents) {
			String title = content.select(".article-title").text();

			// �ɷ��� �����͵鸸 ����ǰ���			
			// �ð���������� ����Ʈ���� ����
			Product product = new Product();
			// ǰ��
			product.setName(productName);
			
			// ����
			product.setTitle(content.select(".article-title").text());
			
			// ����
			String price = content.select(".article-price ").text();
			if(price.contains("����"))
				continue;
			price = price.substring(0, price.length() - 1);// �� ����
			price = price.replace(",", "");// �޸� ����
			if(price.length()==0)// ���� ����(-)�� ���� �н�
				continue;
			product.setPrice(Long.parseLong(price));
			
			// ���� �Խñ� ��ũ
			product.setLink(carrotDetail+content.select("a").attr("href"));
			
			// �̹��� ��ũ
			product.setImg(content.select(".card-photo > img").attr("src"));
			
			//����
			product.setLocation(content.select(".article-region-name").text());
			
			productList.add(product);
			//�ð������� �� �Խñۿ� ��������			
		}
		return productList;
	}
	
	private List<Product> detailCrawling(List<Product> productList)throws IOException{
		
		// �Խñ� �󼼺��� ũ�Ѹ�
		for (Product product : productList) {
			if(product.getName()==null)
				continue;
			Document doc = Jsoup.connect(product.getLink()).get();

			Element content = doc.selectFirst("#content > #article-description");

			// ��¥, ��ð� ������
			String time=content.select("#article-category time").text();

			String[] tmp= time.split(" ");
			time= tmp[tmp.length-2];
			
			LocalDateTime dateTime;
			
			if(time.contains("��")) {
				dateTime=LocalDateTime.now().minusYears(Long.parseLong(time.replace("��", "")));
			}else if(time.contains("��")) {
				dateTime=LocalDateTime.now().minusMonths(Long.parseLong(time.replace("��", "")));
			}else if(time.contains("��")) {
				dateTime=LocalDateTime.now().minusDays(Long.parseLong(time.replace("��", "")));
			}else if(time.contains("�ð�")) {
				dateTime=LocalDateTime.now().minusHours(Long.parseLong(time.replace("�ð�", "")));
			}else if(time.contains("��")) {
				dateTime=LocalDateTime.now().minusMinutes(Long.parseLong(time.replace("��", "")));
			}else {
				dateTime=LocalDateTime.now().minusSeconds(Long.parseLong(time.replace("��", "")));
			}
			
			product.setTime(time.replace("����", ""));
			product.setDate(dateTime);
		}
		return productList;		
	}
	
	private List<Product> keywordFilter(List<Product> productList, List<String> exceptionKeyword, List<String> ...andOrKeyword){
		for(Product product : productList) {
			//����Ű����
			for(String keyword : exceptionKeyword) {
				if(product.getTitle().contains(keyword)) {
					//���� Ű���带 �����ϰ� ������ ��������
					product.setName(null);//arraylist�� remove�� ����ð� �ʹ������ɸ�
					product.setTitle(null);
					product.setPrice(0);
					product.setDate(null);
					product.setTime(null);
					product.setLink(null);
					product.setImg(null);
					product.setLocation(null);
					break;
				}
			}
			if(product.getName()==null)
				continue;
			//andOrŰ����
			/* ����) ������ 12 ���θ� �˻��ϰ� ������� �˻�Ű����� ������ 12 & (���� | pro)�� �� ���̴�
			 * �̷� ��쿡 ũ�Դ� And���������� ���������� or������ �ʿ��� ��찡 �����Ѵ� �̸� ���� ������
			 * 
			 * �뷮�� ��� : ������ 12 & (����|pro) & (128)
			 */
			for(List<String> orKeyword : andOrKeyword) {
				boolean isContain=false;//or�����̱� ������ �ϳ��� �����ϸ� ��
				for(String keyword: orKeyword) {
					//���� �ҹ��ڷ� ��ȯ��Ű�� ���ϵ�����
					if(product.getTitle().toLowerCase().contains(keyword)) {
						isContain=true;
					}
				}
				if(!isContain) {
					//�ϳ��� ���Ծ��ϸ� ��������
					product.setName(null);
					product.setTitle(null);
					product.setPrice(0);
					product.setDate(null);
					product.setTime(null);
					product.setLink(null);
					product.setImg(null);
					product.setLocation(null);				
					
					break;
				}
			}
		}
		return productList;
	}
}
