import java.util.Random;

import com.oracle.jrockit.jfr.EventToken;
import com.oracle.jrockit.jfr.Producer;

/**
 * Demonstrates how to create custom events.
 * 
 * @author Marcus Hirt
 */
public class SmurfBerryExchange {
	private static Random RND = new Random();
	private final static String PRODUCER_URI = "http://www.smx.com/smx/";
	private static final Producer PRODUCER;
	private static final EventToken TRANSACTION_EVENT_TOKEN;
	
	static {
		PRODUCER = createProducer();
		TRANSACTION_EVENT_TOKEN = createToken();
		PRODUCER.register();
	}
	
	private final static float NOMINAL_PRICE = 50;
	private volatile float price = NOMINAL_PRICE;
	
	/**
	 * Creates our producer.
	 */
	private static Producer createProducer() {
		try {
			return new Producer("Smurfberry Exchange producer",
					"A demo event producer for the fictional Smurfberry Exchange.", PRODUCER_URI);
		} catch (Exception e) {
			// Add proper exception handling.
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Creates our event token.
	 */
	private static EventToken createToken() {
		try {
			return PRODUCER.addEvent(TransactionEvent.class);
		} catch (Exception e) {
			// Add proper exception handling.
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws InterruptedException {
		Thread.sleep(2000);
		Thread[] threads;
		threads = new Thread[20];
		SmurfBerryExchange smx = new SmurfBerryExchange();
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Smurf(smx, i);
			threads[i].start();
		}
	}

	private int loopCount = 300000;
	
	public void doTransaction(Smurf smurf) {
		TransactionEvent event = new TransactionEvent(SmurfBerryExchange.TRANSACTION_EVENT_TOKEN);
		event.begin();
		doWork();
		float price = getPrice();
		int quantity = getQuantity();
		Logger.getLogger().log("Transaction by smurf " + smurf.getName() + " " + quantity + "@" + price);
		event.setPrice(price);
		event.setQuantity(quantity);
		event.end();
		event.commit();
	}

	private int getQuantity() {
		return RND.nextInt() % 1 * RND.nextInt(30) + 1;
	}

	private synchronized float getPrice() {
		float delta = RND.nextInt() % 5;
		
		if (price < NOMINAL_PRICE / 4) { 
			price += Math.abs(delta);
		} else if (price > price * 2) { 
			price -= Math.abs(delta);
		} else {
			price += delta;
		}
		return price;
	}

	private void doWork() {
		int x = 1;
		int y = 1;
		for (int i = 1; i < loopCount; i++) {
			x += 1;
			y = y % (this.loopCount + 3);
			if (x % (this.loopCount + 4) == 0 || y == 0) {
				System.out.println("Should not happen");
			}
		}
	}
}
