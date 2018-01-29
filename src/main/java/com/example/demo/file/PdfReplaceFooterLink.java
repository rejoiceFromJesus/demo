package com.example.demo.file;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.stream.Stream;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.log.SysoCounter;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
 
public class PdfReplaceFooterLink {
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


		public PdfReplaceFooterLink() {
			// TODO Auto-generated constructor stub
		}
		public PdfReplaceFooterLink(String path, String dest) {
			super();
			this.path = path;
			this.dest = dest;
		}

		public void execute() throws IOException, DocumentException {
    	File folder = new File(path);
    	File[] listFiles = folder.listFiles();
    	System.out.println(listFiles.length);
    	for (File file2 : listFiles) {
			String oldPath = file2.getAbsolutePath();
			System.out.println(oldPath);
			if(!oldPath.endsWith(".pdf")) {
				continue;
			}
			String newPath = dest+file2.getName();
			try {
				this.manipulatePdf(oldPath, newPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		/*Stream.of(folder.listFiles()).forEach(file -> {
			
		});*/
    }
 
    public void manipulatePdf(String src, String dest) throws Exception, IllegalAccessException {
    	System.out.println(dest);
    		PdfReader reader = new PdfReader(src);
        Field f = PdfReader.class.getDeclaredField("ownerPasswordUsed");
        f.setAccessible(true); 
       f.set(reader, Boolean.TRUE); 
        int numberOfPages = reader.getNumberOfPages();
        for(int i = 1; i <= numberOfPages; i++) {
        	PdfDictionary dict = reader.getPageN(i);
            dict.remove(PdfName.ANNOTS);
            PdfObject pdfObject2 = dict.get(PdfName.RESOURCES);
            if(pdfObject2 instanceof PdfDictionary) {
            	  PdfDictionary dictionary = (PdfDictionary)pdfObject2;
                  PdfDictionary pdfObject = (PdfDictionary) dictionary.get(PdfName.FONT);
                  if(pdfObject != null) {
                	  pdfObject.getKeys().stream().forEach(key -> {
                		  if(key.toString().startsWith("/F") && key.length() == 8) {
                			  pdfObject.remove(key);
                		  }
                	  });
                  }
            }
          
        }
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.close();
        reader.close();
    }
}