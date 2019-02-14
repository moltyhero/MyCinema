package LogicPart;

public class Sit {
	
    int col;
    int row;
    boolean taken;

    public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setTaken(boolean taken) {
		this.taken = taken;
	}

	public Sit(int col, int row)
    {
        this.col = col;
        this.row = row;
        this.taken = false;
    }

    public boolean isTaken()
    {
        return taken;
    }

    public void takeSit()
    {
        this.taken = true;
    }
}
