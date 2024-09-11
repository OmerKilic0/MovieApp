package com.omer.sakila.movimo.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.pdfbox.io.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.omer.sakila.movimo.entity.Customer;
import com.omer.sakila.movimo.entity.Film;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	private final JavaMailSender mailSender;

	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendPurchaseNotification(Customer customer, String filmTitle, byte[] pdfInvoice) throws Exception {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo("omerfarukkilic.17@gmail.com"); // change with customer.getEmail()
		helper.setFrom("mailtrap@demomailtrap.com");
		helper.setSubject("Purchase Confirmation");
		helper.setText("Dear " + customer.getFirstName() + ",\n\nYou have successfully purchased the film: " + filmTitle
				+ ".\n\nThank you for your purchase!\n\nBest regards,\nMovimo");
		helper.addAttachment("Invoice.pdf", new ByteArrayResource(pdfInvoice));
		mailSender.send(message);
	}

	public byte[] createInvoicePdf(Customer customer, Film film, double amount) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, byteArrayOutputStream);
			document.open();

			InputStream imageStream = getClass().getClassLoader().getResourceAsStream("static/images/logo/logo.jpg");
	        byte[] imageBytes = IOUtils.toByteArray(imageStream);
	        Image logo = Image.getInstance(imageBytes);
	        logo.scaleToFit(100, 100);
	        logo.setAlignment(Element.ALIGN_CENTER);
	        document.add(logo);

			Font titleFont = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD);
			Paragraph title = new Paragraph("Hi " + customer.getFirstName() + "!", titleFont);
			title.setAlignment(Element.ALIGN_CENTER);
			title.setSpacingAfter(20);
			document.add(title);

			Font subtitleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
			Paragraph subtitle = new Paragraph("Thank You For Your Purchase.", subtitleFont);
			subtitle.setAlignment(Element.ALIGN_CENTER);
			subtitle.setSpacingAfter(10);
			document.add(subtitle);

			LocalDate currentDate = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String formattedDate = currentDate.format(formatter);
			
			Font sectionTitleFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.GRAY);
		    Paragraph orderInfoTitle = new Paragraph("YOUR ORDER INFORMATION:", sectionTitleFont);
		    orderInfoTitle.setSpacingBefore(20);
		    orderInfoTitle.setSpacingAfter(5);
		    document.add(orderInfoTitle);

			PdfPTable table = new PdfPTable(2);
			table.setWidthPercentage(100);
			table.setSpacingBefore(10);
			table.setSpacingAfter(20);

			table.addCell(getStyledCell("Customer Address:", PdfPCell.ALIGN_LEFT, BaseColor.LIGHT_GRAY, true));
	        table.addCell(getStyledCell(customer.getAddress().getAddress(), PdfPCell.ALIGN_LEFT, BaseColor.WHITE, false));
	        table.addCell(getStyledCell("Order Date:", PdfPCell.ALIGN_LEFT, BaseColor.LIGHT_GRAY, true));
	        table.addCell(getStyledCell(formattedDate, PdfPCell.ALIGN_LEFT, BaseColor.WHITE, false));
	        table.addCell(getStyledCell("Bill to:", PdfPCell.ALIGN_LEFT, BaseColor.LIGHT_GRAY, true));
	        table.addCell(getStyledCell(customer.getEmail(), PdfPCell.ALIGN_LEFT, BaseColor.WHITE, false));
	        table.addCell(getStyledCell("Source:", PdfPCell.ALIGN_LEFT, BaseColor.LIGHT_GRAY, true));
	        table.addCell(getStyledCell("Movimo Store", PdfPCell.ALIGN_LEFT, BaseColor.WHITE, false));

	        document.add(table);

	        Paragraph orderTitle = new Paragraph("HERE'S WHAT YOU ORDERED:", sectionTitleFont);
	        orderTitle.setSpacingBefore(20);
	        orderTitle.setSpacingAfter(5);
	        document.add(orderTitle);
	        
			PdfPTable orderTable = new PdfPTable(2);
			orderTable.setWidthPercentage(100);
			orderTable.setSpacingBefore(10);
			orderTable.setSpacingAfter(10);

			orderTable.addCell(getHeaderCell("Description"));
			orderTable.addCell(getHeaderCell("Price"));

			orderTable.addCell(getStyledCell(film.getTitle(), PdfPCell.ALIGN_CENTER, BaseColor.WHITE, false));
	        orderTable.addCell(getStyledCell(String.format("%.2f TRY", film.getPrice()), PdfPCell.ALIGN_CENTER, BaseColor.WHITE, false));

	        document.add(orderTable);

			PdfPTable totalTable = new PdfPTable(2);
            totalTable.setWidthPercentage(100);
            totalTable.setSpacingBefore(10);

            totalTable.addCell(getStyledCell("TOTAL:", PdfPCell.ALIGN_LEFT, BaseColor.LIGHT_GRAY, true));
            totalTable.addCell(getStyledCell(String.format("%.2f TRY", amount), PdfPCell.ALIGN_RIGHT, BaseColor.LIGHT_GRAY, true));

            document.add(totalTable);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			document.close();
		}

		return byteArrayOutputStream.toByteArray();
	}

	private PdfPCell getStyledCell(String text, int alignment, BaseColor bgColor, boolean bold) {
		Font font = new Font(Font.FontFamily.HELVETICA, 12, bold ? Font.BOLD : Font.NORMAL);
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		cell.setPadding(8);
		cell.setHorizontalAlignment(alignment);
		cell.setBackgroundColor(bgColor);
		cell.setBorderWidth(1);
		return cell;
	}

	private PdfPCell getHeaderCell(String text) {
		Font font = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		cell.setPadding(8);
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.setBorderWidth(1.5f);
		return cell;
	}
}
