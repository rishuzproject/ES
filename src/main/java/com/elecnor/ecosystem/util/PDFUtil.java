package com.elecnor.ecosystem.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFUtil extends PdfPageEventHelper {

	 Font smallNormal=new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL, BaseColor.BLACK);  
//	public PDFUtil()
//	{
//		
//	}
  @Override
   public void onEndPage(PdfWriter writer, Document document)
     {
   	        System.out.println("Entered end of the page"+ writer.getPageSize().getLeft()+" rigt"+writer.getPageSize().getRight()+"get bottom"+writer.getPageSize().getBottom()+"   top"+writer.getPageSize().getTop());
            Rectangle rect = writer.getPageSize();
             ColumnText.showTextAligned(writer.getDirectContent(),
             Element.ALIGN_CENTER, new Phrase(" *** To avoid excess penality charges ,Please make payments within 30 days of the due date.There will be 2% intrest charge per month on all late invoices",smallNormal),
             (rect.getLeft() + rect.getRight()) / 2, rect.getBottom() + 18, 0);
 }
	
}
