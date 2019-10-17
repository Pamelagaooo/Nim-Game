/* *******************************************************************************
 * NAME: Ziping GAO
 * STUDENT ID: 686274
 * DESCRIPTION: The class, NimGame is to manage the game process, prompting players
 *              to enter key inputs and display game outcomes
 *
 * WRITTEN: 25/04/2019
 * LAST UPDATED: 05/05/2019
 ****************************************************************************** */

import java.util.Scanner;

public class NimGame
{
	private int currentStoneCount, upperBoundOfStoneRemoval;
	private NimPlayer player1, player2;

	//constructor for class variables above
	public NimGame(int currentStoneCount, int upperBoundOfStoneRemoval,
			NimPlayer player1, NimPlayer player2)
	{
		this.currentStoneCount = currentStoneCount;
		this.upperBoundOfStoneRemoval = upperBoundOfStoneRemoval;
		this.player1 = player1;
		this.player2 = player2;
	}

	// display the number of stones left using asterisks
	public String displayAsterisks()
	{
		String stonesLeftDisplay = "";
		for(int n = 0; n < this.currentStoneCount; n++)
		{
			stonesLeftDisplay = stonesLeftDisplay.concat(" *");
		}
		return stonesLeftDisplay;
	}

	/*
	 * Prompt each user to enter the number of stones removed 
	 * Update the total number of stones left afterwards 
	 * Repeat this process
	 * terminate it when stones left is zero return the 'game over' message and the winner
	 */
	public void playGame(Scanner keyboard)
	{			
		while (this.currentStoneCount > 0)
		{
			boolean flag = false;
			
			//the scenario when player 2 is the winner.
			do{
				System.out.println(this.currentStoneCount+" stones left:"+this.displayAsterisks());
				System.out.println(player1.getGivenName() + "'s turn - remove how many?");
				int stoneRemoval = keyboard.nextInt();
				String junk = keyboard.nextLine();
				flag = !checkValid(stoneRemoval);
				if (checkValid(stoneRemoval))
				{
					currentStoneCount = player1.removeStone(stoneRemoval, currentStoneCount);
				}else
				{
					if(currentStoneCount > upperBoundOfStoneRemoval)
					{
						System.out.println();
						System.out.println("Invalid move. You must remove between 1 and " 
						+ upperBoundOfStoneRemoval + " stones.");
					}else
					{
						System.out.println();
						System.out.println("Invalid move. You must remove between 1 and " 
						+ currentStoneCount + " stones.");
					}
				}
				if(this.currentStoneCount == 0)
				{
					System.out.println("\nGame Over\n" + player2.getGivenName() + " " 
							+ player2.getFamilyName() + " wins!");
					
					player2.addGameWon();
					break;
				}
				System.out.println();
				}while (flag);
			
			//same process as above when player 1 is the winner.
			if (currentStoneCount > 0)
			{
				flag = false;
				do{
					System.out.println(this.currentStoneCount + " stones left:" 
							+ this.displayAsterisks());
					
					System.out.println(player2.getGivenName() + "'s turn - remove how many?");
					int stoneRemoval = keyboard.nextInt();
					String junk = keyboard.nextLine();
					flag = !checkValid(stoneRemoval);
					if(checkValid(stoneRemoval))
					{
						currentStoneCount = player2.removeStone(stoneRemoval, currentStoneCount);
					}else
					{
						if(currentStoneCount > upperBoundOfStoneRemoval)
						{
							System.out.println();
							System.out.println("Invalid move. You must remove between 1 and " 
									+ upperBoundOfStoneRemoval + " stones.");
						}else
						{
							System.out.println();
							System.out.println("Invalid move. You must remove between 1 and " 
									+ currentStoneCount + " stones.");
						}
					}
					if (this.currentStoneCount == 0)
					{
						System.out.println("\nGame Over\n" + player1.getGivenName() + " " 
								+ player1.getFamilyName() + " wins!");
						
						player1.addGameWon();
						break;
					}
					
					System.out.println();
					
				}while(flag);
			}
		}
	}
	
	// check the validity of the stones removed by each player
	public boolean checkValid(int stonesRemoved)
	{
		if (stonesRemoved > upperBoundOfStoneRemoval || stonesRemoved < 1 
			|| currentStoneCount < stonesRemoved)
		{
			return false;
		}
		return true;
	}
}