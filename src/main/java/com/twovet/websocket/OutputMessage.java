package com.twovet.websocket;

import java.util.List;

import com.twovet.catalog.dto.EventDTO;

public class OutputMessage extends Message{

	private String timestamp;
	
	private List<EventDTO> events;
	
	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public OutputMessage() {
		// TODO Auto-generated constructor stub
	}
	
	public OutputMessage(String from, String text, String timestamp) {
		super();
		super.setFrom(from);
		super.setText(text);
		this.timestamp = timestamp;
	}

	public List<EventDTO> getEvents() {
		return events;
	}

	public void setEvents(List<EventDTO> events) {
		this.events = events;
	}
	
	
}
