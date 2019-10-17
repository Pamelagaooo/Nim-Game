/* *****************************************************************************
 * NAME: Ziping GAO
 * STUDENT ID: 686274
 * DESCRIPTION: The class, NimPlayer, gives information about players' basic 
 * information and the their game statistics. It also contains methods to
 * modify players' information and statistics.
 * 
 * WRITTEN: 25/04/2019
 * LAST UPDATED: 05/05/2019
 **************************************************************************** */

public class NimPlayer implements Comparable<NimPlayer>
{
	private String playerUserName;
	private String givenName;
	private String familyName;
	private int numberOfGamePlayed;
	private int numberOfGameWon;
	
	//constructor for playerUserName, givenName and familyName
	public NimPlayer(String userName, String familyName, String givenName)
	{
		this.playerUserName = userName;
		this.familyName = familyName;
		this.givenName = givenName;
	}

	//set the username for each player
	public void setPlayerUserName(String playerName)
	{
		this.playerUserName = playerName;
	}

	//get the username for each player
	public String getPlayerUserName()
	{
		return this.playerUserName;
	}

	//set given name for each player
	public void setGivenName(String givenName)
	{
		this.givenName = givenName;
	}

	//get given name for each player
	public String getGivenName()
	{
		return this.givenName;
	}

	//set family name for each player
	public void setFamilyName(String familyName)
	{
		this.familyName = familyName;
	}

	//get family name for each player
	public String getFamilyName()
	{
		return this.familyName;
	}
	
	//set the number of game played for each player
	public void setNumberOfGamePlayed(int numberOfGamePlayed)
	{
		this.numberOfGamePlayed = numberOfGamePlayed;
	}

	//get the number of game played for each player
	public int getNumberOfGamePlayed()
	{
		return this.numberOfGamePlayed;
	}
	
	//set the number of game won for each player
	public void setNumberOfGameWon(int numberOfGameWon)
	{
		this.numberOfGameWon = numberOfGameWon;
	}

	//get the number of game won for each player
	public int getNumberOfGameWon()
	{
		return this.numberOfGameWon;
	}
	
	//update the number of game won for each player
	public void addGameWon()
	{
		numberOfGameWon ++;
	}
	
	//add the number of game played for each player
	public void addGamePlayed()
	{
		numberOfGamePlayed++;
	}
	
	//remove stones and update stones left
	public int removeStone(int stonesRemoved, int currentStoneCount)
	{
		return currentStoneCount = currentStoneCount - stonesRemoved;
	}
		
	//edit player's give name and family name
	public void editPlayerDetails(String newFamilyName, String newGivenName)
	{
		this.givenName = newGivenName;
		this.familyName = newFamilyName;
	}
	
	//set player's statistics to zero
	public void resetPlayerStat()
	{
		this.numberOfGamePlayed = 0;
		this.numberOfGameWon = 0;
	}
	
	//display players' information and statistics
	public void displayPlayerDetails()
	{
		System.out.println(playerUserName + ","+ givenName +","+ familyName +"," 
		+ numberOfGamePlayed +" games," + numberOfGameWon+ " wins");
	}
	
	//calculate winning ratio for each player
	public double getWinningRatio()
	{
		if(numberOfGamePlayed == 0)
		{
			return 0;
		}
		return new Double(numberOfGameWon)/new Double(numberOfGamePlayed);	
	}
	
	// compare two players' username alphabetically
	public int compareTo(NimPlayer player)
	{
		if(this.playerUserName.compareTo(player.playerUserName) > 0)
		{
			return 1;
		} else if(this.playerUserName.compareTo(player.playerUserName) < 0)
		{
			return -1;
		} else 
		{
			return 0;
		}
	}
}