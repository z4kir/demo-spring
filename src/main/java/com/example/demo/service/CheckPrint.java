package com.example.demo.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class CheckPrint implements Printable {

	private String recipientName;

	public CheckPrint(String recipientName) {
		this.recipientName = recipientName;
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		if (pageIndex > 0)
			return NO_SUCH_PAGE;

		Graphics2D g2d = (Graphics2D) graphics;
		g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

		// Customize font and color
		g2d.setFont(new Font("Serif", Font.BOLD, 14));
		g2d.setColor(Color.BLACK);

		// Print recipient's name at desired coordinates (adjust for your check layout)
		int x = 100; // horizontal position (points)
		int y = 120; // vertical position (points)
		g2d.drawString("Pay to the Order of: " + recipientName, x, y);

		return PAGE_EXISTS;
	}

}
