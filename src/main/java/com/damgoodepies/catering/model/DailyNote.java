package com.damgoodepies.catering.model;

import java.time.LocalDateTime;

public class DailyNote 
{
	private int dailyNoteId;
	private String dailyNote;
	private LocalDateTime createDateTime;
	
	public DailyNote(int dailyNoteId, String dailyNote, LocalDateTime createDateTime)
	{
		this.dailyNoteId = dailyNoteId;
		this.dailyNote = dailyNote;
		this.createDateTime = createDateTime;
	}
	
	public DailyNote()
	{
		
	}

	public int getDailyNoteId() 
	{
		return dailyNoteId;
	}

	public String getDailyNote() 
	{
		return dailyNote;
	}

	public LocalDateTime getCreateDateTime() 
	{
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) 
	{
		this.createDateTime = createDateTime;
	}

	public void setDailyNoteId(int dailyNoteId) 
	{
		this.dailyNoteId = dailyNoteId;
	}

	public void setDailyNote(String dailyNote) 
	{
		this.dailyNote = dailyNote;
	}
}
