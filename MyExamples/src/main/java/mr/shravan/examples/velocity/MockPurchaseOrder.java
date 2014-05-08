package mr.shravan.examples.velocity;

public class MockPurchaseOrder {
    private String id="1";
    private String name="Mock_PO1";
    private String itemName="Sony TV";
    private String itemCode="TV";
    private String itemCost="100";
    private String itemSupplierCost="90";
    private String itemShipperCost="120";
    public String getID() {
        return id;
    }
    public void setID(String _id) {
        this.id = _id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getItemCode() {
        return itemCode;
    }
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
    public String getItemCost() {
        return itemCost;
    }
    public void setItemCost(String itemCost) {
        this.itemCost = itemCost;
    }
    public String getItemSupplierCost() {
        return itemSupplierCost;
    }
    public void setItemSupplierCost(String itemSupplierCost) {
        this.itemSupplierCost = itemSupplierCost;
    }
    public String getItemShipperCost() {
        return itemShipperCost;
    }
    public void setItemShipperCost(String itemShipperCost) {
        this.itemShipperCost = itemShipperCost;
    }
}
