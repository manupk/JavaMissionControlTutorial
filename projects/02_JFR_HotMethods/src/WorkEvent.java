
import com.oracle.jrockit.jfr.ContentType;
import com.oracle.jrockit.jfr.DurationEvent;
import com.oracle.jrockit.jfr.EventDefinition;
import com.oracle.jrockit.jfr.ValueDefinition;

@EventDefinition(path="jmc_tutorial/example2/work", name = "WorkUnit", description="One lap in the worker thread. Used to see any performance improvements.", stacktrace=true, thread=true)
public class WorkEvent extends DurationEvent {	
	@ValueDefinition(name="Intersection Size", description="The number of elements that exist in both collections.", contentType=ContentType.None)
	private int intersectionSize;

	public WorkEvent() {
		super(HotMethods.WORK_EVENT_TOKEN);
	}

	public int getIntersectionSize() {
		return intersectionSize;
	}

	public void setIntersectionSize(int intersectionSize) {
		this.intersectionSize = intersectionSize;
	}
}
