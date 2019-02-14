package LogicPart;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Cinema
{
	
		
	// Func creates a Matrix for the Cinema
	public static void CreateMat(Hall cinema)
	{
		Sit[][] hall = new Sit[cinema.getNum_of_rows()][cinema.getNum_of_columms()];
		
		for (int i = 0; i < cinema.getNum_of_rows(); i++)
		{
			// Mark as transparent and isEnabled = false
			for (int j = i; j < (cinema.getNum_of_columms() - (i)); j++)
			{
				Sit empty = new Sit(0, 0);
				// Way thru --> chair are unavailable (null)
				if (j + 1 == cinema.getNum_of_columms() / 2)
				{
					j++;
					continue;
				}
				
				else
				{
					hall[i][j] = empty;
					hall[i][j].setRow(i+1);
					hall[i][j].setCol(j+1);
				}
			}
		}
		
		cinema.setHall(hall);
	}
	
	// Func gets a matrix and prints it 
	public static void printMat(Hall cinema)
	{ 
		// * - Unavailable, T - Taken, C - Available chair 
		
		for (int i = 0; i < cinema.getNum_of_rows(); i++)
		{
			for (int j = 0; j < cinema.getNum_of_columms(); j++)
			{
				if (cinema.getHall()[i][j] == null)
					System.out.print("*");
				
				else if (cinema.getHall()[i][j].isTaken())
					System.out.println("T");
				
				else
					System.out.print("C");
			}
			
			System.out.println("");
		}
	}
	
	// Func gets a Matrix and a number and marks all the available places according to num
	public static void SelectPlaces(Hall cinema, int num, VBox vBox, Image image)
	{		
		int counter = 0;
		boolean done = true;
		
		// Paint all the available places
		for (int i = 0; i < cinema.getNum_of_rows(); i++)
		{
			for (int j = i; j < cinema.getNum_of_columms()-num-i-1; j++)
			{
				counter = 0;
				
				if (cinema.getHall()[i][j] == null)
				{
					j+=2;
				}

				
				if (cinema.getHall()[i][j].isTaken() == false)
				{
					for (int k = j; k < j + num; k++)
					{
						if (j+num<cinema.getNum_of_columms()-i)
						{

							if (cinema.getHall()[i][k] != null)
							{
								if(cinema.getHall()[i][k].isTaken() == false)
								{
									counter++;
								}
							}

						}

					}
					
					// Mark the mat[i][j] --> mat[i][j+num]
					if (counter == num)
					{
						for (int k = j; k < j + num && j< cinema.getNum_of_columms(); k++)
						{
							//cinema.getHall()[i][k].setTaken(true);
							HBox hBox = ((HBox)vBox.getChildren().toArray()[i]);
							if (hBox.getChildren().toArray()[k] instanceof Label)
								continue;
							Button tempBtn = ((Button)hBox.getChildren().toArray()[k]);
							ImageView tempView = new ImageView(image);
							tempView.setFitHeight(25);
							tempView.setFitWidth(25);
							tempBtn.setGraphic(tempView);
						}
					}
						
				}
			}			
		}
	}

	public static void FewChairs(Hall cinema, int num, VBox vBox, Image image)
	{
		int counter = 0;

		for (int i = 0; i < cinema.getNum_of_rows(); i++)
		{
			for (int j = i; j < cinema.getNum_of_columms() - i; j++)
			{
				if (j + num == cinema.getNum_of_columms() / 2) // Skip the way thru
				{
					j += num + 1;
					continue;
				}

				else if (j + num >= cinema.getNum_of_columms() - (2*i))
					break;

				else if (cinema.getHall()[i][j].isTaken() == false)
				{
					for (int k = j; k < num + j; k++)
					{
						if (cinema.getHall()[i][k].isTaken() == true)
							break;

						counter++;
					}

					if (counter == num) // Places are good
					{
						for (int k = j; k < num + j; k++)
						{
							System.out.println(i + " " + k);
							HBox hBox = ((HBox)vBox.getChildren().toArray()[i]);
							if (hBox.getChildren().toArray()[k] instanceof Label)
								continue;
							Button tempBtn = ((Button)hBox.getChildren().toArray()[k]);
							ImageView tempView = new ImageView(image);
							tempView.setFitHeight(25);
							tempView.setFitWidth(25);
							tempBtn.setGraphic(tempView);
						}
					}
				}
			}
		}
	}
	
	public static boolean CheckSits(Hall hall, Sit firstSit, int num)
	{
		boolean success = true;
		int col = firstSit.getCol();
		int row = firstSit.getRow();
		
		for (int i = 0; i < num; i++)
		{
			if (hall.getHall()[row + i][col + i].isTaken() == true)
			{
				success = false;
				break;
			}
		}

		if (success) 
		{
			for (int i = 0; i < num; i++)
			{
				if (hall.getHall()[row + i][col + i].isTaken() == false)
				{
					hall.getHall()[row + i][col + i].setTaken(true);
					// CHANGE PICTURE TO TAKEN
				}
			}	
			
			System.out.println("Places have been selected successfuly! Enjoy you movie");	
		}
		
		else
			System.out.println("The places you have chosen have already been chosen by another customer.\nPlease selecet diffrent places");
		
		return success;
	}
	
	public static void main(String[] args) {
		
		Hall cinema_hall = new Hall();
		CreateMat(cinema_hall);
		
		printMat(cinema_hall);
	}
}
