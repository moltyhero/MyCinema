package LogicPart;

public class Hall {
	
	private int num_of_rows;
	private int num_of_columms;
	private boolean is_used;
	private Sit[][] hall;
	
	public Hall()
	{
		this.num_of_rows = 10;
		this.num_of_columms = 40;
		this.is_used = false;
		this.hall = new Sit[num_of_rows][num_of_columms];
	}
	
	public Hall(int rows, int columms)
	{
		this.num_of_rows = rows;
		this.num_of_columms = columms;
		this.is_used = false;
		this.hall = new Sit[num_of_rows][num_of_columms];
	}
	
	public Hall(int rows, int columms, boolean used)
	{
		this(rows, columms);
		this.is_used = used;
	}

	public int getNum_of_rows() {
		return num_of_rows;
	}

	public void setNum_of_rows(int num_of_rows) {
		this.num_of_rows = num_of_rows;
	}

	public int getNum_of_columms() {
		return num_of_columms;
	}

	public void setNum_of_columms(int num_of_columms) {
		this.num_of_columms = num_of_columms;
	}

	public boolean isIs_used() {
		return is_used;
	}

	public void setIs_used(boolean is_used) {
		this.is_used = is_used;
	}

	public Sit[][] getHall() {
		return hall;
	}

	public void setHall(Sit[][] hall) {
		this.hall = hall;
	}
}
