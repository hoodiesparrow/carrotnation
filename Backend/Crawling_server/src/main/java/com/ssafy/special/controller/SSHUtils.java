package com.ssafy.special.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Component;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class SSHUtils {
	private final String username = "j5d205";
	private final String host = "cluster2.p.ssafy.io";
	private final int port = 22;
	private final String pwdPath = "C:\\SSAFY\\aws\\J5D205T.pem";

	private Session session;
	private ChannelExec channelExec;

	private void connectSSH() throws JSchException {
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
			connectSSH();
			channelExec = (ChannelExec) session.openChannel("exec"); // 실행할 channel 생성

			channelExec.setCommand(command); // 실행할 command 설정
			channelExec.connect(); // command 실행

		} catch (JSchException e) {
			log.error("JSchException");
		} finally {
			this.disConnectSSH();
		}
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
			connectSSH();
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
