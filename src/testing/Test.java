package testing;

import java.util.Scanner;

public class Test {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		
		
		int row = 3;
		int stitches = 10;
		Pattern[] pattern = new Pattern[row*stitches + 1];
		
		int patternnr = 0;
		
		for(int i=1;i<=row;i++){		
			
			for(int j=1;j<=stitches;j++){
				pattern[patternnr] = new Pattern(i, j, "");
				patternnr++;
			}		
			
		}
		
		
		printPattern(pattern, row, stitches);
		
		int changeRow = 0;
		int changeStitch = 0;
		String draw = null;
		String line = null;
		
		while(true){

			System.out.print("Vilken rad vill du ändra på? ");
			changeRow = in.nextInt();
	
			System.out.print("Vilken maska på rad " + changeRow + " vill du ändra på?  ");
			changeStitch = in.nextInt();
		
			System.out.print("Vad vill du ändra maskan till? ");
			draw = in.next();
		
			System.out.println("Du ville ändra på rad " + changeRow + ", maska " + changeStitch + " till " + draw);
			Pattern s = findStitch(pattern, changeRow, changeStitch, row*stitches);
			s.changeDraw(draw);

			printPattern(pattern, row, stitches);
		
		}		
	}
	
	public static void printPattern(Pattern[] pattern, int row, int stitches){
		int patternnr = 0;
		for(int i=1;i<=row;i++){
			System.out.print(i + ". ");
			for(int j=1;j<=stitches;j++){
				System.out.print(pattern[patternnr].getDraw() + " ");
				patternnr++;
			}
			System.out.println("");
		}
		
	}
	
	public static Pattern findStitch(Pattern[] pattern, int row, int stitch, int nostitches){
		
		Pattern p = null;
		
		for(int i=0;i<nostitches;i++){
			if(pattern[i].getRow() == row){
				if(pattern[i].getStitch() == stitch){
					p = pattern[i];
				}
			}
		}
		
		return p;
	}
}
