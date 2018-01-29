package com.example.demo.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.stream.Stream;

import org.springframework.util.FileCopyUtils;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Link;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

public class AddLink {
	private String path;
	private String dest;
	
	
    public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void execute() throws Exception {
        File folder = new File(path);
		Stream.of(folder.listFiles()).forEach(file -> {
			String oldPath = file.getAbsolutePath();
			String newPath = dest+file.getName();
			if(!oldPath.endsWith(".pdf")) {
				if(oldPath.endsWith(".azw3") || oldPath.endsWith(".mobi") || oldPath.endsWith(".epub")) {
					System.out.println("move to:"+newPath);
					file.renameTo(new File(newPath));
					
				}
				return;
			}
				try {
					this.manipulatePdf(oldPath, newPath);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("failed,move to:"+newPath);
					try {
						FileCopyUtils.copy(file, new File(newPath));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
		});
    }

	public AddLink(String path, String dest) {
		super();
		this.path = path;
		this.dest = dest;
	}

	public static void manipulatePdf(String src, String dest) throws Exception {
		PdfReader pdfReader = new PdfReader(src);
		/* Field f = PdfReader.class.getDeclaredField("encrypted");
	        f.setAccessible(true); 
	       f.set(pdfReader, Boolean.TRUE); */
	     PdfDocument pdfDoc =
	    	        new PdfDocument(pdfReader, new PdfWriter(dest));
	    	        Document document = new Document(pdfDoc);
	    	        Rectangle pageSize;
	    	        PdfCanvas canvas;
	    	        int n = pdfDoc.getNumberOfPages();
	    	        Link link = new Link("http://blog.csdn.net/jiongyi1", PdfAction.createURI("http://blog.csdn.net/jiongyi1"));
	    	        Paragraph header = new Paragraph().add(link)
	    	                .setFont(PdfFontFactory.createFont(FontConstants.HELVETICA))
	    	                .setFontSize(14)
	    	                .setFontColor(Color.RED);
	    	        for (int i = 1; i <= n; i++) {
	    	            PdfPage page = pdfDoc.getPage(i);
	    	            pageSize = page.getPageSize();
	    	            canvas = new PdfCanvas(page);
	    	            
	    	            float x = pdfDoc.getPage(i).getPageSize().getWidth() / 2-100;
	    	            float y = pdfDoc.getPage(i).getPageSize().getTop() - 20;
	    	            document.showTextAligned(header.setFontColor(Color.RED), x, y, i,
	    	                    TextAlignment.LEFT, VerticalAlignment.BOTTOM, 0);
	    	            
	    	         /*   x = pdfDoc.getPage(i).getPageSize().getWidth() / 2-100;
	    	            y = pdfDoc.getPage(i).getPageSize().getBottom() + 30;
	    	            document.showTextAligned(header.setFontColor(Color.RED), x, y, i,
	    	                    TextAlignment.LEFT, VerticalAlignment.BOTTOM, 0);*/

	    	        }
	    	        pdfDoc.close();
	}
}
