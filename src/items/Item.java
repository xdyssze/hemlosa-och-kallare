package items;

public interface Item {
    public int getId();
    public String[] getInfo();
    public String getName();
	public void equip();
    public void dequip();
    public int calculateAdd();
}
