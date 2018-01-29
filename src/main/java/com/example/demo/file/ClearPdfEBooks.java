package com.example.demo.file;

import org.junit.Test;

public class ClearPdfEBooks {
	public static void main(String[] args) throws Exception {
		String path1 = "D:\\迅雷下载";
		String path2 = "D:\\BaiduNetdiskDownload\\sharepdf";
		new AddLink(path1,path1+"\\newpdf\\").execute();
		new AddLink(path2,path2+"\\newpdf\\").execute();
		new FileRenameService().rename(path1+"\\newpdf\\",path2+"\\newpdf\\");
	}
	
	@Test
	public void linkTest() throws IllegalAccessException, Exception {
		String path = "D:\\迅雷下载\\Learn Salesforce Lightning.pdf";
		String dest = "D:\\迅雷下载\\test.pdf";
		new PdfReplaceFooterLink().manipulatePdf(path, dest);;
		
	}

}
