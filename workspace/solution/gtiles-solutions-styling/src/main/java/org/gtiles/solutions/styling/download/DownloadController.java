package org.gtiles.solutions.styling.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.util.UrlEncoded;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 下载文件
 * 
 * @author IF阳光请疯狂
 *
 */
@RequestMapping("/port/download")
@Controller("org.gtiles.solutions.styling.download.DownloadController")
public class DownloadController {

	@RequestMapping("/downFile")
	public void downFile(HttpServletRequest request, HttpServletResponse response) {
		try {
			File file = new File("D:\\面试题搜集\\Java就业面试题大全.doc");
			response.addHeader("Content-Type", "applicaction/x-msdownload");
			response.addHeader("Content-Disposition", "attachment:filename=" + URLEncoder.encode("dddd", "utf-8"));
			String realPath = request.getServletContext().getRealPath("file/sss.txt");
			FileInputStream is = new FileInputStream(file);
			ServletOutputStream os = response.getOutputStream();
			int len = 0;
			byte[] by = new byte[1024];
			while ((len = is.read(by)) != -1) {
				os.write(by, 0, len);
			}
			os.flush();
			os.close();
			is.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
