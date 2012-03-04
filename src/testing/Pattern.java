package testing;

public class Pattern {
	int row;
	int stitch;
	String draw;
	
	
	public Pattern(int row, int stitch, String draw){
		this.row = row;
		this.stitch = stitch;
		this.draw = draw;
	}
	
	public int getRow(){
		return this.row;
	}
	
	public int getStitch(){
		return this.stitch;
	}
	
	public String getDraw(){
		return this.draw;
	}
	
	public void changeDraw(String draw){
		this.draw = draw;
	}
}
