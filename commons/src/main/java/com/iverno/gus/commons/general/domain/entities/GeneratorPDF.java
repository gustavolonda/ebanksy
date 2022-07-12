package com.iverno.gus.commons.general.domain.entities;

import java.awt.Color;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import lombok.SneakyThrows;


public class GeneratorPDF {
	@SneakyThrows
	public static void create(Document document, String subTitle, List<FieldHead> headList, List<List<String>> fieldBodyList,  String title)  {
		
		try{
			 document.open();

			  
  
			  PdfPTable tableMessage = new PdfPTable(2);
			  tableMessage.setHorizontalAlignment(PdfPTable.ALIGN_CENTER);
			 
			  		  
			  Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
			  font.setStyle(Font.BOLD);

			  font.setSize(14);

			// Create Object of Paragraph
			  Paragraph paragraph = new Paragraph(title, font);
			  paragraph.setAlignment(Paragraph.ALIGN_CENTER);
			  document.add(paragraph);
			  //subtitle
			  Font fontSubtitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);

			  fontSubtitle.setSize(12);
			  
			  // Create Object of Paragraph
			  Paragraph subtitelParagraph = new Paragraph(subTitle, fontSubtitle);
			  subtitelParagraph.setAlignment(Paragraph.ALIGN_RIGHT);
			  document.add(subtitelParagraph);
			  
			  PdfPTable table = new PdfPTable(headList.size());
			  table.setWidthPercentage(100f);
		
			  table.setSpacingBefore(headList.size());
			  // Create Table Header
			  PdfPCell cell = new PdfPCell();
			  cell.setBackgroundColor(Color.LIGHT_GRAY);
			  cell.setPadding(5);
			  cell.setBorderColor(Color.LIGHT_GRAY);
			  cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		
			  headList.forEach(h -> {
				  Font fontHead = FontFactory.getFont(FontFactory.TIMES_ROMAN);
				  fontHead.setSize(11);
				  fontHead.setColor(Color.BLACK);
				  cell.setPhrase(new Phrase(h.getTitle(), fontHead));
				  table.addCell(cell);
			  }); 
			  
			 fieldBodyList.forEach(t -> {
				 
				  PdfPCell cellBody = new PdfPCell();
				  cellBody.setBackgroundColor(Color.WHITE);
				  cellBody.setPadding(10);
				  cellBody.setPaddingBottom(35);
				  cellBody.setPaddingTop(35);
				  cellBody.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				  cellBody.setBorderColor(Color.LIGHT_GRAY);
				
				  // Add Font
				  Font fontBody = FontFactory.getFont(FontFactory.TIMES_ROMAN);
				  fontBody.setSize(11);
				  fontBody.setColor(Color.BLACK);
				  headList.forEach(h -> {
						  					
						  					cellBody.setPhrase(new Phrase(t.get(h.getNum()-1), fontBody));
						  					table.addCell(cellBody);
		
						  				}); 
			  });
			  
			  // Add table to document
			  document.add(table);
			  document.close();
			  

        }catch (Exception e){
        	throw e;
        }
		
	 
	 }
	


}
