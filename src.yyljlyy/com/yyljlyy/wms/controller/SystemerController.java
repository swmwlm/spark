package com.yyljlyy.wms.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import com.jfinal.plugin.activerecord.Page;
import com.yyljlyy.base.BaseController;
import com.yyljlyy.wms.model.Storage;

import static com.yyljlyy.wms.common.ModelConfigWms.*;


public class SystemerController extends BaseController {
	public ArrayList<String> syslist= new ArrayList<String>();
	public void index() {
		Page<Storage> pager = Storage.dao.paginate(
				getParaToInt("pager.pageNumber", 1),
				getParaToInt("pager.pageSize", 20),
				"select * ", 
				" from " + TABLE_Storage);
		setAttr("pager",pager);
		render("../w_uploadify.html");
	}
	
	public void run() {
		String hostname = "192.168.8.88";
		String username = "root";
		String password = "lij090915";
		try {
			Connection conn = new Connection(hostname);
			conn.connect();
			/* Authenticate */
			boolean isAuthenticated = conn.authenticateWithPassword(username,
					password);
			if (isAuthenticated == false)
				throw new IOException("Authentication failed.");
			Session sess = conn.openSession();
			sess.execCommand("/root/run.sh");
			System.out.println("执行情况:");
			InputStream stdout = new StreamGobbler(sess.getStdout());
			BufferedReader br = new BufferedReader(
					new InputStreamReader(stdout));
			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				System.out.println(line);
				syslist.add(line + "\n");
			}
			/* Show exit status, if available (otherwise "null") */
			System.out.println("ExitCode: " + sess.getExitStatus());
			/* Close this session */
			sess.close();
			/* Close the connection */
			conn.close();
		} catch (IOException e) {
			e.printStackTrace(System.err);
			System.exit(2);
		}
	 setAttr("line",syslist);
	 render("../reset.html");
	}
	public void stop() {
		String hostname = "192.168.8.88";
		String username = "root";
		String password = "lij090915";
		try {
			Connection conn = new Connection(hostname);
			conn.connect();
			/* Authenticate */
			boolean isAuthenticated = conn.authenticateWithPassword(username,
					password);
			if (isAuthenticated == false)
				throw new IOException("Authentication failed.");
			Session sess = conn.openSession();
			sess.execCommand("/root/stop.sh");
			System.out.println("执行情况:");
			InputStream stdout = new StreamGobbler(sess.getStdout());
			BufferedReader br = new BufferedReader(
					new InputStreamReader(stdout));
			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				System.out.println(line);
				syslist.add(line + "\n");
			}
			/* Show exit status, if available (otherwise "null") */
			System.out.println("ExitCode: " + sess.getExitStatus());
			/* Close this session */
			sess.close();
			/* Close the connection */
			conn.close();
		} catch (IOException e) {
			e.printStackTrace(System.err);
			System.exit(2);
		}
	 setAttr("line",syslist);
	 render("../reset.html");
	}
}
