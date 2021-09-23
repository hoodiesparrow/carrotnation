package com.ssafy.special.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Component;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpProgressMonitor;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class SSHUtils {
	private final String username = "j5d205";
	private final String host = "cluster2.p.ssafy.io";
	private final int port = 22;
	//경로 수정
	private final String pwdPath = "/home/ubuntu/pemkey/J5D205T.pem";
//	private final String pwdPath = "C:\\SSAFY\\aws\\J5D205T.pem";
//	private final String sendFilePath = "C:\\SSAFY\\aws\\check.txt";
//	private final String receiveFilePath = "/home/j5d205/receive/test.txt";
	private static Session session;
	private ChannelExec channelExec;
	private static Channel channel = null;
	private ChannelSftp channelSftp;

	public void connectSSH() throws JSchException {
		JSch jsch = new JSch();
		jsch.addIdentity(pwdPath);
		System.out.println("identity added ");
//		jsch.setConfig("StrictHostKeyChecking", "no");
		session = jsch.getSession(username, host, port);
		session.setConfig("StrictHostKeyChecking", "no");
		session.connect();
		
	}

	public void command(String command) {
		try {
//			connectSSH();
			channelExec = (ChannelExec) session.openChannel("exec"); // 실행할 channel 생성

			channelExec.setCommand(command); // 실행할 command 설정
			channelExec.connect(); // command 실행

		} catch (JSchException e) {
			log.error("JSchException");
		} finally {
			this.disConnectSSH();
		}
	}
	 public void sendFileToOtherServer(String sourcePath, String destinationPath, String filename) throws Exception {
		 channel = session.openChannel("sftp");
	        channel.connect();
	        channelSftp = (ChannelSftp) channel;
	        channelSftp.put(sourcePath, destinationPath+filename, new SftpProgressMonitor() {
	            private long max = 0;  //최대
	            private long count = 0;  //계산을 위해 담아두는 변수
	            private long percent = 0;  //퍼센트
	            @Override
	            public void init(int op, String src, String dest, long max) {  //설정
	                this.max = max;
	            }
	            @Override
	            public void end() {
	                //종료시 할 행동
	            }
	            @Override
	            public boolean count(long bytes) {
	                this.count += bytes;  //전송한 바이트를 더한다.
	                long percentNow = this.count*100/max;  //현재값에서 최대값을 뺀후
	                if(percentNow>this.percent){  //퍼센트보다 크면
	                    this.percent = percentNow;
	                    System.out.println("progress : " + this.percent); //Progress
	                }
	                return true;//기본값은 false이며 false인 경우 count메소드를 호출하지 않는다.
	            }
	        });
	        channelSftp.disconnect();
	        channel.disconnect();
	    }
	 
	private void disConnectSSH() {
		if (session != null)
			session.disconnect();
		if (channelExec != null)
			channelExec.disconnect();
	}

	public String getSSHResponse(String command) {
		StringBuilder response = null;
		try {
//			connectSSH();
			channelExec = (ChannelExec) session.openChannel("exec");
			channelExec.setCommand(command);

			InputStream inputStream = channelExec.getInputStream();
			channelExec.connect();

			byte[] buffer = new byte[8192];
			int decodedLength;
			response = new StringBuilder();

			while ((decodedLength = inputStream.read(buffer, 0, buffer.length)) > 0)
				response.append(new String(buffer, 0, decodedLength));

		} catch (JSchException e) {
			log.error("JSchException");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			this.disConnectSSH();
		}
		return response.toString();
	}
}
