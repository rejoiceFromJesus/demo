package com.example.demo.pdf;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

public class ScreenShot {
	public static void main(String[] args) {
		createScreenShoot("E:\\迅雷下载\\sharepdf\\newpdf", "E:\\迅雷下载\\sharepdf\\images\\");
	}
	public static boolean createScreenShoot(String source, String target) {
	    File directory = new File(source);
	    if (!directory.exists()) {
	      System.err.println("路径[" + source + "]对应的pdf文件不存在!");
	      return false;
	    }
	    File[] listFiles = directory.listFiles();
	    for (File file : listFiles) {
	    	System.err.println(file.getName());
	    	  try{
	    	      RandomAccessFile raf = new RandomAccessFile(file, "r");
	    	      FileChannel channel = raf.getChannel();
	    	      ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
	    	      PDFFile pdffile = new PDFFile(buf);
	    	         PDFPage page = pdffile.getPage(1);
	    	          // get the width and height for the doc at the default zoom
	    	          Rectangle rect = new Rectangle(0, 0, (int) page.getBBox()
	    	              .getWidth(), (int) page.getBBox().getHeight());
	    	          // generate the image
	    	          Image img = page.getImage(rect.width, rect.height, // width &
	    	              rect, // clip rect
	    	              null, // null for the ImageObserver
	    	              true, // fill background with white
	    	              true // block until drawing is done
	    	              );
	    	          BufferedImage tag = new BufferedImage(rect.width, rect.height,   BufferedImage.TYPE_INT_RGB);
	    	          tag.getGraphics().drawImage(img, 0, 0, rect.width, rect.height,null);
	    	          FileOutputStream out = new FileOutputStream(target+file.getName()+".jpg");
	    	          JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
	    	          encoder.encode(tag); // JPEG编码
	    	          out.close();
	    	    }catch(Exception e){
	    	      e.printStackTrace();
	    	    }
			
		}
	    return true;
	    
	    
}
	
	
	
}

