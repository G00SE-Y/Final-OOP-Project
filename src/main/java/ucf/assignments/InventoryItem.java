package ucf.assignments;

import javafx.beans.property.SimpleStringProperty;

public class InventoryItem {
	private final SimpleStringProperty price = new SimpleStringProperty();
	private final SimpleStringProperty serial = new SimpleStringProperty();
	private final SimpleStringProperty name = new SimpleStringProperty();

	public InventoryItem(String price, String serial, String name) {
		this.price.set(price+"");
		this.serial.set(serial+"");
		this.name.set(name+"");
	}

	public String getPrice() {
		return price.get();
	}

	public SimpleStringProperty priceProperty() {
		return price;
	}

	public void setPrice(String price) {
		this.price.set(price);
	}

	public String getSerial() {
		return serial.get();
	}

	public SimpleStringProperty serialProperty() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial.set(serial);
	}

	public String getName() {
		return name.get();
	}

	public SimpleStringProperty nameProperty() {
		return name;
	}

	public void setName(String name) {
		this.name.set(name);
	}

	@Override
	public String toString() {
		return "InventoryItem{" +
				"price=" + price.get() +
				", serial=" + serial.get() +
				", name=" + name.get() +
				'}';
	}
}
