package com.oracle.example;

import java.net.URISyntaxException;

import com.oracle.jrockit.jfr.*;

public class Example {
	private final static String PRODUCER_URI = "http://www.example.com/demo/";
	private Producer 	myProducer;
	private EventToken 	myToken;

	public Example() throws URISyntaxException, InvalidEventDefinitionException, InvalidValueException {
		myProducer = new Producer("Demo Producer", "A demo event producer.", PRODUCER_URI);
		myToken = myProducer.addEvent(MyEvent.class);
	}

	@EventDefinition(path="demo/myevent", name = "My Event", description="An event triggered by doStuff.", stacktrace=true, thread=true)
	private class MyEvent extends TimedEvent {
		@ValueDefinition(name="Message", description="The logged important stuff.")
		private String text;
		public MyEvent(EventToken eventToken) {
			super(eventToken);
		}

		public void setText(String text) {
			this.text = text;
		}
	}
	
	public void doStuff() {
		MyEvent event = new MyEvent(myToken);
		event.begin();
		String importantResultInStuff = "";
		// Generate the string, then set it...
		event.setText(importantResultInStuff);
		event.end();
		event.commit();
	}
	
	private MyEvent event = new MyEvent(myToken);
	public void doStuffReuse() {
		event.reset();
		event.begin();
		String importantResultInStuff = "";
		// Generate the string, then set it...
		event.setText(importantResultInStuff);
		event.end();
		event.commit();
	}
}
