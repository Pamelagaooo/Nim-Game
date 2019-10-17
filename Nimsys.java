/* *******************************************************************************
 * NAME: Ziping GAO
 * STUDENT ID: 686274
 * DESCRIPTION: The class, Nimsys is to manage the whole Nim program including 
 * 				methods manipulating the database of players' information and 
 * 				statistics and controlling the game playing/idle status of the 
 * 				program           
 *
 * WRITTEN: 25/04/2019
 * LAST UPDATED: 05/05/2019
 ****************************************************************************** */

import java.util.Arrays;
import java.util.Scanner;

public class Nimsys
{
	private Scanner scanner;
    private NimPlayer[] players = new NimPlayer[100];
    
    //the array to store player action commands
    private String[] userAction = new String[]
    {
    	"addplayer","removeplayer","editplayer",
    	"resetstats","displayplayer","rankings", "startgame",
    	"exit"
    };
    
    //constructor for scanner
    public Nimsys(Scanner scanner)
    {
    	this.scanner = scanner;
    }
    
    //read player's input and split the action command out
    public void readUserInput(String userCommand)
    {
    	String [] input = userCommand.split(" ");
    	String action = input[0];
    	String parameter = input.length > 1 ? input[1] : null;
    	int index = -1;
    	for(int i=0; i < userAction.length; i++)
    	{
    		if(userAction[i].equals(action))
    		{
    			index = i;
    			break;    				
    		}    		
    	}
    	try
    	{
    		switch(index)
    		{
	        	case 0:
	        		addPlayer(parameter);
	        		break;
	        	case 1:
	        		removePlayer(parameter, scanner);
	        		break;
	        	case 2:
	        		editPlayer(parameter);
	        		break;
	        	case 3:
	        		resetStats(parameter, scanner);
	        		break;
	        	case 4:
	        		displayPlayer(parameter);
	        		break;
	        	case 5:
	        		displayRankings(parameter);
	        		break;
	        	case 6:
	        		startGame(parameter,scanner);
	        		break;
	        	case 7:
	        		System.out.println();
	        		System.exit(0);
	        		break;
	        	default:
	        		throw new exception("'" + parameter + "'" + " is not a valid command.");
	        		break;
    		}	
    	}
    	catch(Exception e)
    	{
    		String message = e.getMessage();
    		System.out.println(message);
    		System.exit(0);
    	}
    }
    
    //add player to the players array
    public void addPlayer(String parameter)
    {
        String[] segment = parameter.split(",");        
        String userName = segment[0], givenName = segment[1];
        String familyName = segment[2];

        if(doesUserExist(userName) != -1)
        {
        	System.out.println("The player already exists.");
        }else
        {
        	int index = getNullUser();
        	if (index == -1)
        	{
        		System.out.println("Can't add more users. Maximum number of users are 100.");                
            }else
            {
            	NimPlayer player = new NimPlayer(userName, givenName, familyName);
                players[index] = player;
            }
        }
    }
    
    //get the array index of the null elements
    private int getNullUser()
    {
    	for(int i=0; i< players.length; i++)
    	{
    		if(players[i] == null)
    		{
    			return i;
    		}
    	}
    	return -1;
    }
    
    //remove a player from the array or remove all players
    public void removePlayer(String userName, Scanner keyboard)
    {
    	if(userName == null || userName == "")
    	{
    		System.out.println("Are you sure you want to remove all players? (y/n)");
            String input = keyboard.next();
            String junk = keyboard.nextLine();
            if(input.equals("y"))
            {
            	players = new NimPlayer[100];                
            }else if(input.equals("n"))
            {
            }
        }else
        {
        	int index = doesUserExist(userName);
            if (index == -1)
            {
            	System.out.println("The player does not exist.");
            }else
            {
            	players[index] = null; 
            }
        }
    }

    //edit player's given name and family name
    public void editPlayer(String parameter)
    {
    	String[] segment= parameter.split(",");
    	String userName = segment[0],newGivenName = segment[1],newFamilyName = segment[2];
    	int index = doesUserExist(userName);
        if(index == -1)
        {
        	System.out.println("The player does not exist.");
        }else
        {
        	players[index].editPlayerDetails(newGivenName, newFamilyName);
        }
    }

    //reset all players' statistics to zero or reset a player's statistics
    public void resetStats(String userName, Scanner keyboard)
    {
    	if(userName == null || userName == "")
    	{
    		System.out.println("Are you sure you want to reset all player statistics? (y/n)");
            String input = keyboard.next();
            String junk = keyboard.nextLine();
            if(input.equals("y"))
            {
            	for(NimPlayer player : players)
            	{
            		if (player != null)
            		{
            			player.resetPlayerStat();
            		}
            	}
            }else if(input.equals("n"))
            {
            }
        }else
        {
        	int index = doesUserExist(userName);
            if (index == -1)
            {
            	System.out.println("The player does not exist.");
            }else
            {
            	players[index].resetPlayerStat();
            }
        }
    }
    
    //count the number of not null players
    public int countPlayer()
    {
    	int count=0;
    	for(NimPlayer player:players)
    	{
    		if(player!=null) {count++;}
    	}
    	return count;
    }
     
    //copy the the array - "players"
    public NimPlayer[] clonePlayer()
    {
    	int index=0;
    	NimPlayer[] temp = new NimPlayer[countPlayer()];
    	for(NimPlayer player:players)
    	{
    		if(player!=null)
    		{
    			temp[index]=player;
    			index++;
    		}
    	}
    	return temp;
    }
    
    //display all information of a certain player or display all players information 
    //by username alphabetically
    public void displayPlayer(String userName)
    {
    	if (userName == null || userName == "")
    	{
    		NimPlayer[] tempArray=clonePlayer();
            Arrays.sort(tempArray);
            for(NimPlayer player : tempArray)
            {
            	player.displayPlayerDetails();
            }
    	}else
    	{
    		int index = doesUserExist(userName);
            if (index == -1)
            {
            	System.out.println("The player does not exist.");
            }else
            {
            	players[index].displayPlayerDetails();
            }
        }
    }
    
    //start a game, display initial game information, update game played and game won
    public void startGame(String parameter, Scanner keyboard)
    {
    	String[] segment= parameter.split(",");
    	String  userName1 = segment[2], userName2 = segment[3];
    	int initialStoneCount = Integer.parseInt(
    			segment[0]), upperBoundOfStoneRemoval =Integer.parseInt(segment[1]
    					);
    	int player1Index = selectPlayer(userName1);
    	int player2Index = selectPlayer(userName2);
    	if (player1Index == -1 || player2Index == -1)
    	{
    		System.out.println("One of the players does not exist.");
    		return;
    	}
    	players[player1Index].addGamePlayed();
    	players[player2Index].addGamePlayed();
    	 
    	System.out.print("\n");
    	System.out.println("Initial stone count: " + initialStoneCount);
    	
        System.out.println("Maximum stone removal: " + upperBoundOfStoneRemoval); 
        
        System.out.println("Player 1: " + players[player1Index].getGivenName() + " " 
        		+ players[player1Index].getFamilyName());
        
        System.out.println("Player 2: " + players[player2Index].getGivenName() + " " 
        		+ players[player2Index].getFamilyName());
        
        System.out.println();
    	
        NimGame game= new NimGame(initialStoneCount,upperBoundOfStoneRemoval,
        		players[player1Index], players[player2Index]);
        
        game.playGame(keyboard);
    }
    
    //select a not null player to play a game
    private int selectPlayer(String userName)
    {
    	for (int i = 0; i < players.length-1; i++)
    	{
    		if (players[i] == null) continue;
    		if (players[i].getPlayerUserName().equals(userName))
    		{
    			return i;
    		}    		
    	}    	
    	return -1;    	
    }
    
    //display rankings of players in desc/asc orders
    public void displayRankings(String parameter)
    {
    	NimPlayer[] temp;    	
    	if(parameter == null || parameter == " " || parameter.equals("desc"))
    	{
    		temp = selectionSort();
    	}else if(parameter.equals("asc"))
    	{
    		temp = reverseSelectionSort();
    	}else
    	{
    		return;
    	}
    	 
    	for(NimPlayer player : temp)
    	{
    		String ratio = Math.round(player.getWinningRatio() * 100) + "%";
    		System.out.println(
    				String.format("%-4s", ratio) + " | " + 
					String.format("%02d",player.getNumberOfGamePlayed()) + " games" + " | " 
    				+ player.getGivenName() + " " + player.getFamilyName()
    				);    			
		}    		
	}
    
    //check whether the user has existed in the array
    public int doesUserExist(String userName)
    {
    	for(int i = 0; i <= 99; i++)
    	{
    		if(players[i]!=null)
    		{
    			if (players[i].getPlayerUserName().equals(userName))
                {
    				return i;
    			}
            }
    	}
    	return -1;
    }
            
    //sort players by their winning ratios in a descending order and alphabetically
    public NimPlayer[] selectionSort()
    {
    	NimPlayer[] temp=this.clonePlayer();
    	for(int i=0;i<temp.length-1;i++)
    	{
    		int max=i; 	
    		for(int j=i+1;j<temp.length;j++)
    		{
    			if(temp[j].getWinningRatio()>temp[max].getWinningRatio())
    			{
    				max=j;
    			}else if(temp[j].getWinningRatio()==temp[i].getWinningRatio())
    			{
    				if(temp[j].getPlayerUserName().compareTo(temp[i].getPlayerUserName())<0)
    				{
    					max=j;
    				}
    			}
    		}
    		NimPlayer swap=temp[i];
    		temp[i]=temp[max];
    		temp[max]=swap;
    	}
    	return temp;
    }
    
    //reverse the order of players of the selection sort method above
    public NimPlayer[] reverseSelectionSort()
    {
    	NimPlayer[] temp = this.clonePlayer();
    	NimPlayer[] sortedListDesc = selectionSort();
    	int i = 0, j=temp.length - 1;
    	for(;i<temp.length;i++)
    	{
    		temp[i] = sortedListDesc[j];
    		j--;
    	}
    	return temp;
    }
    
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);        
        Nimsys sys = new Nimsys(keyboard);
        System.out.println("Welcome to Nim\n");
        while(true)
        {
        	System.out.print("$");
        	sys.readUserInput(keyboard.nextLine());
        	System.out.println();       	
        }       
    }
}