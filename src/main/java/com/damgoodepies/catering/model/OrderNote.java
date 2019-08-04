package com.damgoodepies.catering.model;

import java.util.ArrayList;
import java.util.List;

public class OrderNote 
{
	private static int lastCateringNoteIdUsed = 100;
	private int cateringNoteId;
	private int orderId;
	private String note;
	
	public OrderNote(int orderId, String note)
	{
		this.cateringNoteId = ++lastCateringNoteIdUsed;
		this.orderId = orderId;
		this.note = note;
	}
	
	@Override
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("orderID: " + orderId + "\n");
		buffer.append("cateringNoteId: " + cateringNoteId + "\n");
		buffer.append("CateringNote: " + note);
		return buffer.toString();
	}

	public String getNote()
	{
		return note;
	}

	public int getCateringNoteId() 
	{
		return cateringNoteId;
	}

	public int getOrderId()
	{
		return orderId;
	}

	public void setCateringNoteId(int cateringNoteId) 
	{
		this.cateringNoteId = cateringNoteId;
	}

	public void setOrderId(int orderId) 
	{
		this.orderId = orderId;
	}

	public void setNote(String note) 
	{
		this.note = note;
	}
}
