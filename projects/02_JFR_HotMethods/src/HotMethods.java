import java.io.IOException;

import com.oracle.jrockit.jfr.EventToken;
import com.oracle.jrockit.jfr.InstantEvent;
import com.oracle.jrockit.jfr.Producer;

/**
 * Never mind this class. These are not the droi... This is not the class you're looking for. 
 * It's just for setting up the worker threads and the custom JFR event type. 
 * 
 * The problem is elsewhere. ;)
 * 
 * @author Marcus Hirt
 */
public class HotMethods {
	// Hint: You may want to set NUMBER_OF_THREADS close to the number of hardware threads for maximum saturation
	private static final int NUMBER_OF_THREADS = 4;
	private static final String PRODUCER_URI = "http://www.oracle.com/jmc/tutorial/example2";
	static final Producer PRODUCER;
	static final EventToken WORK_EVENT_TOKEN;
	static {
		PRODUCER = createProducer();
		WORK_EVENT_TOKEN = createToken(WorkEvent.class);
		PRODUCER.register();
	}

	/**
	 * Creates our event token.
	 */
	public static EventToken createToken(Class<? extends InstantEvent> clazz) {
		try {
			return PRODUCER.addEvent(clazz);
		} catch (Exception e) {
			// Add proper exception handling.
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * Creates our producer.
	 */
	private static Producer createProducer() {
		try {
			return new Producer("Tutorial Example 2",
					"A demo event producer for the HotMehods Example.", PRODUCER_URI);
		} catch (Exception e) {
			// Add proper exception handling.
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) throws IOException {		
		Thread[] threads = new Thread[NUMBER_OF_THREADS];		
		for (int i = 0; i < threads.length; i++ ) {
			threads[i] = new Thread(new Worker(), "Worker Thread " + i);
			threads[i].setDaemon(true);
			threads[i].start();
		}
		System.out.print("Press enter to quit!");
		System.out.flush();
		System.in.read();		
	}

}
