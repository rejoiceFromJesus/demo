package com.example.demo.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PdfToImage {

    private static final String OUTPUT_DIR = "E:\\迅雷下载\\sharepdf\\images\\";
    private static final String SOURCE_DIR = "E:\\迅雷下载\\sharepdf\\newpdf\\";

    public static void main(String[] args) throws Exception{
    	 File directory = new File(SOURCE_DIR);
 	    if (!directory.exists()) {
 	      System.err.println("路径[" + SOURCE_DIR + "]对应的pdf文件不存在!");
 	      return;
 	    }
 	    File[] listFiles = directory.listFiles();
 	    for (File file : listFiles) {
 	    	 try (final PDDocument document = PDDocument.load(file)){
 	            PDFRenderer pdfRenderer = new PDFRenderer(document);
 	            System.err.println(file.getName());
 	           // document.getNumberOfPages();
 	            for (int page = 0; page < 1; ++page)
 	            {
 	                BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
 	                String fileName = OUTPUT_DIR + file.getName()+".jpg";
 	                ImageIOUtil.writeImage(bim, fileName, 300);
 	            }
 	            document.close();
 	        } catch (IOException e){
 	            System.err.println("Exception while trying to create pdf document - " + e);
 	        }
 	    }

       
    }

}