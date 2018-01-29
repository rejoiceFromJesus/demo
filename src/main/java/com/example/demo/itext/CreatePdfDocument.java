package com.example.demo.itext;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfArray;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfObject;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Link;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

public class CreatePdfDocument {

    public static void main(String... args) throws Exception {

        PdfDocument pdfDoc =
        new PdfDocument(new PdfReader("HeaderFooter.pdf"), new PdfWriter("HeaderFooter2.pdf"));
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
            float y = pdfDoc.getPage(i).getPageSize().getTop() - 50;
            document.showTextAligned(header.setFontColor(Color.RED), x, y, i,
                    TextAlignment.LEFT, VerticalAlignment.BOTTOM, 0);
            
            x = pdfDoc.getPage(i).getPageSize().getWidth() / 2-100;
            y = pdfDoc.getPage(i).getPageSize().getBottom() + 50;
            document.showTextAligned(header.setFontColor(Color.RED), x, y, i,
                    TextAlignment.LEFT, VerticalAlignment.BOTTOM, 0);

        }
        pdfDoc.close();
    }
}