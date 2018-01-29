package com.example.demo.file;
 
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.stream.Stream;

import com.itextpdf.kernel.pdf.PdfDictionary;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfObject;
import com.itextpdf.kernel.pdf.PdfReader;
 
public class PdfReplaceFooterFm {
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

	

	public PdfReplaceFooterFm(String path, String dest) {
		super();
		this.path = path;
		this.dest = dest;
	}

	public void execute() throws Exception {
        File folder = new File(path);
		Stream.of(folder.listFiles()).forEach(file -> {
			String oldPath = file.getAbsolutePath();
			if(!oldPath.endsWith(".pdf")) {
				return;
			}
			String newPath = dest+file.getName();
				try {
					this.manipulatePdf(oldPath, newPath);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException();
				}
		});
    }
 
    public void manipulatePdf(String src, String dest) throws Exception {
        PdfReader reader = new PdfReader(src);
        Field f = PdfReader.class.getDeclaredField("ownerPasswordUsed");
        f.setAccessible(true); 
       f.set(reader, Boolean.TRUE); 
        int numberOfPages = reader.;
        for(int i = 1; i <= numberOfPages; i++) {
        	PdfDictionary dict = reader.getPageN(i);
        	PdfObject pdfObject = dict.get(PdfName.RESOURCES);
        	if(pdfObject instanceof PdfDictionary) {
        		 PdfDictionary dic2 = (PdfDictionary) pdfObject;
        		 PdfObject pdfObject2 = dic2.get(PdfName.XOBJECT);
        		 if(pdfObject2 instanceof  PdfDictionary) {
        			  PdfDictionary  dic3 = (PdfDictionary) pdfObject2;
                      if(dic3 == null) return;
                      dic3.remove(new PdfName("Fm0"));
        		 }else {
        			 return;
        		 }
        	}else {
        		return;
        	}
            
        }
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.close();
        reader.close();
    }
}