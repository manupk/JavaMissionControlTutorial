import com.oracle.jrockit.jfr.ContentType;
import com.oracle.jrockit.jfr.EventDefinition;
import com.oracle.jrockit.jfr.EventToken;
import com.oracle.jrockit.jfr.TimedEvent;
import com.oracle.jrockit.jfr.ValueDefinition;

@EventDefinition(path="smx/transaction", name = "SMX Transaction", description="An Smurfberry Exchange transaction.", stacktrace=true, thread=true)
public class TransactionEvent extends TimedEvent {
	@ValueDefinition(name="Price", description="The price at which the transaction was made.", contentType=ContentType.None)
	private float price;

	@ValueDefinition(name="Quantity", description="The quantity of smurfberries transferred.", contentType=ContentType.None)
	private int quantity;

	public TransactionEvent(EventToken eventToken) {
		super(eventToken);
	}

	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
