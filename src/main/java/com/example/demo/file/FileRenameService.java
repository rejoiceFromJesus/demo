package com.example.demo.file;

import java.io.File;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class FileRenameService {
	public static void rename(String ...paths) {
		for (String path : paths) {
			File folder = new File(path);
			Stream.of(folder.listFiles()).forEach(file -> {
				String oldPath = file.getAbsolutePath();
				if(!oldPath.endsWith(".pdf") && !oldPath.endsWith(".azw3") && !oldPath.endsWith(".mobi") && !oldPath.endsWith(".epub")) {
					return;
				}
				String newPath = oldPath.replace("[www.java1234.com]", "").replace("@www.java1234.com", "").replaceAll("\\(ED2000.COM\\)", "")
					;
				File newFile = new File(newPath);
				System.out.println("new file:"+newPath);
				file.renameTo(newFile);
			});
		}
	}
	
	@Test
	public void absolutePath() {
		File file = new File("D:\\BaiduNetdiskDownload\\sharepdf\\[Anonymizing.Health.Data(2013.12)].Khaled.El.Emam.文字版.pdf");
		System.out.println(file.getAbsolutePath());
	}

}
