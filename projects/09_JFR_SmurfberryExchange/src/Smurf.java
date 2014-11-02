public class Smurf extends Thread {
	public final static Logger LOGGER = Logger.getLogger();
	private final SmurfBerryExchange smx;

	public Smurf(SmurfBerryExchange smx, int id) {
		super("Smurf " + id);
		this.smx = smx;
	}

	public void run() {
		while (true) {
			smx.doTransaction(this);
			Thread.yield();
		}
	}
}
