package com.example.demo.file;

import org.junit.Test;

public class ClearPdfEBooks {
	public static void main(String[] args) throws Exception {
		String path1 = "E:\\迅雷下载\\sharepdf";
		String path2 = "E:\\BaiduNetdiskDownload\\sharepdf";
		new AddLink(path1,path1+"\\newpdf\\").execute();
		new AddLink(path2,path2+"\\newpdf\\").execute();
		new FileRenameService().rename(path1+"\\newpdf\\",path2+"\\newpdf\\");
	}
	

}
