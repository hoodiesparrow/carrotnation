package com.ssafy.special.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcraft.jsch.JSchException;
import com.ssafy.special.controller.SSHUtils;
import com.ssafy.special.domain.ProductSellArticleSimiler;
import com.ssafy.special.domain.ProductSellList;
import com.ssafy.special.dto.ProductSellArticleSimilerResponseDTO;
import com.ssafy.special.repository.ProductRepository;
import com.ssafy.special.repository.ProductSellArticleSimilerRepository;
import com.ssafy.special.repository.ProductSellListRepository;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class SimilarityServiceImpl implements SimilarityService {

	@Autowired
	ProductRepository productRepository;
	@Autowired
	ProductSellListRepository productSellListRepository;
	@Autowired
	ProductSellArticleSimilerRepository productSellArticleSimilerRepository;
	
	@Autowired
	SSHUtils ssh;
	private String sendFilePath = "/home/ubuntu/mysqltablefile/";
//	private String sendFilePath = "C:\\SSAFY\\sshtest\\";
	private String receiveFilePath = "/home/j5d205/receive/";
	private String hadoopdefault = "/usr/local/hadoop/bin/";
	@Override
	public void similarityProduct(List<Long> pidList) {
		// session 연결 상태 아님
		if (!ssh.checksession()) {
			try {
				ssh.connectSSH();
			} catch (JSchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ssh.getSSHResponse("/usr/local/hadoop/bin/hadoop fs -rm -r Set; /usr/local/hadoop/bin/hadoop fs -mkdir Set");
		
		LocalDateTime now = LocalDateTime.now().minusHours(1);
		Long cycle = Long.parseLong(now.format(DateTimeFormatter.ofPattern("yyMMddHH")));
		Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
//		List<Product> aa = productRepository.findAll();
		StringBuilder sb,temp;
		for (Long p : pidList) {
			log.info("*********"+p+"에 해당하는 유사도 처리를 시작합니다.");
			sb = new StringBuilder();
			
			List<ProductSellList> list = productSellListRepository.getRecentProductSellList(cycle, p)
					.orElse(new ArrayList<ProductSellList>());

			String strToAnalyze;
			KomoranResult analyzeResultList;
			List<Token> tokenList;
			for (ProductSellList psl : list) {
				if ("".equals(psl.getContent()) || psl.getContent() == null)
					continue;
				try {
					strToAnalyze = psl.getContent().replaceAll("[^\\uAC00-\\uD7AF\\u1100-\\u11FF\\u3130-\\u318F]+",
							" ");

					analyzeResultList = komoran.analyze(strToAnalyze);

					tokenList = analyzeResultList.getTokenList();
				} catch (NullPointerException e) {
					e.printStackTrace();
					continue;
				}
				temp = new StringBuilder();
				boolean checkword=false;
				temp.append(psl.getId()).append("|").append(psl.getMarket()).append(" ");
				for (Token token : tokenList) {
					if ("NNP".equals(token.getPos()) || "NNG".equals(token.getPos())) {
						String s = token.getMorph();
						if(!temp.toString().contains(token.getMorph())) {
							if(!checkword)
								checkword=true;
							temp.append(token.getMorph()).append(" ");
						}
					}
				}
				if(checkword) {
					sb.append(temp.toString()).append("\n");
				}
			}
			File file = new File(sendFilePath+p+".txt");
			if(file.exists()) {
				file.delete();
				log.info("파일삭제 성공");
			}
				
			try {
				FileWriter fw = new FileWriter(file, true);
				// 파일안에 문자열 쓰기
				fw.write(sb.toString());
				fw.flush();

				// 객체 닫기
				fw.close();
				log.info("*******파일쓰기 완료*********");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				ssh.sendFileToOtherServer(sendFilePath+p+".txt", receiveFilePath, p + ".txt");
				String f = "/usr/local/hadoop/bin/hadoop fs -put /home/j5d205/receive/" + p + ".txt "+ "Set/"+p+".txt";
				ssh.getSSHResponse(f);
				ssh.getSSHResponse("/usr/local/hadoop/bin/hadoop fs -rm -r Setoutput;/usr/local/hadoop/bin/hadoop fs -mkdir Setoutput");
//				 "/home/j5d205/mapReducer/S05P21D205/mapReducerMaven/ssafy.jar"
				String cmd =hadoopdefault+"hadoop jar /home/j5d205/mapReducer/S05P21D205/mapReducerMaven/ssafy.jar setjoin 0.3 Set/"+p+".txt Setoutput";
				ssh.getSSHResponse(cmd);
				String result =ssh.getSSHResponse(hadoopdefault+"hadoop fs -cat Setoutput/* | more");
				
				StringTokenizer st = new StringTokenizer(result,"\r\n");
				StringTokenizer ss,tempst;
				ProductSellArticleSimiler psas;
				double similarityScore=0.0;
				while(st.hasMoreTokens()) {
					ss = new StringTokenizer(st.nextToken(),"\t");
					psas = new ProductSellArticleSimiler();
					tempst = new StringTokenizer(ss.nextToken(),"|");
					psas.setArticleA(new ProductSellList(Long.parseLong(tempst.nextToken()),tempst.nextToken()));

					
					tempst = new StringTokenizer(ss.nextToken(),"|");
					psas.setArticleB(new ProductSellList(Long.parseLong(tempst.nextToken()), tempst.nextToken()));
					similarityScore =Double.parseDouble(ss.nextToken());
					if(similarityScore<=70&&similarityScore>=30) {
						psas.setSimilarity(similarityScore);
						insertProductSellArticleSimiler(psas);
					}
					
				}
				
				log.info("********전송끝 *******");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

//	@Override
//	public void similarityProduct(List<Product> product) {
//		// session 연결 상태 아님
//		if (!ssh.checksession()) {
//			try {
//				ssh.connectSSH();
//			} catch (JSchException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		ssh.getSSHResponse("/usr/local/hadoop/bin/hadoop fs -rm -r Set; /usr/local/hadoop/bin/hadoop fs -mkdir Set");
//		
//		LocalDateTime now = LocalDateTime.now().minusHours(1);
//		Long cycle = Long.parseLong(now.format(DateTimeFormatter.ofPattern("yyMMddHH")));
//		Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
//		List<Product> aa = productRepository.findAll();
//		StringBuilder sb,temp;
//		for (Product p : aa) {
//			sb = new StringBuilder();
//			
//			List<ProductSellList> list = productSellListRepository.getRecentProductSellList(cycle, p.getId())
//					.orElse(new ArrayList<ProductSellList>());
//
//			String strToAnalyze;
//			KomoranResult analyzeResultList;
//			List<Token> tokenList;
//			for (ProductSellList psl : list) {
//				if ("".equals(psl.getContent()) || psl.getContent() == null)
//					continue;
//				try {
//					strToAnalyze = psl.getContent().replaceAll("[^\\uAC00-\\uD7AF\\u1100-\\u11FF\\u3130-\\u318F]+",
//							" ");
//
//					analyzeResultList = komoran.analyze(strToAnalyze);
//
//					tokenList = analyzeResultList.getTokenList();
//				} catch (NullPointerException e) {
//					// TODO: handle exception
//					continue;
//				}
//				temp = new StringBuilder();
//				boolean checkword=false;
//				temp.append(psl.getId()).append("|").append(psl.getMarket()).append(" ");
//				for (Token token : tokenList) {
//					if ("NNP".equals(token.getPos()) || "NNG".equals(token.getPos())) {
//						String s = token.getMorph();
//						if(!temp.toString().contains(token.getMorph())) {
//							if(!checkword)
//								checkword=true;
//							temp.append(token.getMorph()).append(" ");
//						}
//					}
//				}
//				if(checkword) {
//					sb.append(temp.toString()).append("\n");
//				}
//			}
//			File file = new File(sendFilePath+p.getId()+".txt");
//			if(file.exists()) {
//				file.delete();
//				log.info("파일삭제 성공");
//			}
//				
//			try {
//				FileWriter fw = new FileWriter(file, true);
//				// 파일안에 문자열 쓰기
//				fw.write(sb.toString());
//				fw.flush();
//
//				// 객체 닫기
//				fw.close();
//				log.info("*******파일쓰기 완료*********");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			try {
//				ssh.sendFileToOtherServer(sendFilePath+p.getId()+".txt", receiveFilePath, p.getId() + ".txt");
//				String f = "/usr/local/hadoop/bin/hadoop fs -put /home/j5d205/receive/" + p.getId() + ".txt "+ "Set/"+p.getId()+".txt";
//				ssh.getSSHResponse(f);
//				ssh.getSSHResponse("/usr/local/hadoop/bin/hadoop fs -rm -r Setoutput;/usr/local/hadoop/bin/hadoop fs -mkdir Setoutput");
////				 "/home/j5d205/mapReducer/S05P21D205/mapReducerMaven/ssafy.jar"
//				String cmd =hadoopdefault+"hadoop jar /home/j5d205/mapReducer/S05P21D205/mapReducerMaven/ssafy.jar setjoin 0.3 Set/"+p.getId()+".txt Setoutput";
//				ssh.getSSHResponse(cmd);
//				String result =ssh.getSSHResponse(hadoopdefault+"hadoop fs -cat Setoutput/* | more");
//				
//				StringTokenizer st = new StringTokenizer(result,"\r\n");
//				StringTokenizer ss,tempst;
//				ProductSellArticleSimiler psas;
//				while(st.hasMoreTokens()) {
//					ss = new StringTokenizer(st.nextToken(),"\t");
//					psas = new ProductSellArticleSimiler();
//					tempst = new StringTokenizer(ss.nextToken(),"|");
//					psas.setArticleA(new ProductSellList(Long.parseLong(tempst.nextToken()),tempst.nextToken()));
//
//					
//					tempst = new StringTokenizer(ss.nextToken(),"|");
//					psas.setArticleB(new ProductSellList(Long.parseLong(tempst.nextToken()), tempst.nextToken()));
//					
//					psas.setSimilarity(Double.parseDouble(ss.nextToken()));
//					insertProductSellArticleSimiler(psas);
//				}
//				
//				log.info("********전송끝 *******");
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//	}
	
	@Transactional
	private boolean insertProductSellArticleSimiler(ProductSellArticleSimiler psas) {
		try {
			productSellArticleSimilerRepository.save(psas);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<ProductSellArticleSimilerResponseDTO> returnSimilarity(long pid, String market) {
		LocalDateTime now = LocalDateTime.now().minusHours(1);
		Long cycle = Long.parseLong(now.format(DateTimeFormatter.ofPattern("yyMMddHH")));		
		return productSellArticleSimilerRepository.getProductSellArticleSimiler(pid,market,cycle).orElse(new ArrayList<ProductSellArticleSimilerResponseDTO>());
	}


}
