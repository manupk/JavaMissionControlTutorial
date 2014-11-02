
public class Worker implements Runnable {
	private int i = 0;
	
	public void run() {
		while (true) {			
			System.out.println("Lap: " + i++);
			WorkEvent event = new WorkEvent();
			event.begin();
			Initiator i1 = new Initiator();
			Initiator i2 = new Initiator();
			i1.initiate(3);
			i2.initiate(5);
			int intersectionSize = i1.countIntersection(i2);
			event.setIntersectionSize(intersectionSize);
			System.out.println("Intersection size: " + intersectionSize);
			event.end();
			event.commit();
			Thread.yield();
		}
	}
}
