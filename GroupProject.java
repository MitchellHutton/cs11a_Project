/**
This program reads a csv file of basketball players and stats and uses tha data from the csv
file to have users select players for a fantasy draft. After going through 10 rounds
the program prints out each user's team and assigns them a score based on their
players' performance over five categories in the 2016-2017 season. Lastly it
prints out the highest score and assigns a team as a winner.
*/
public class GroupProject {

  static int csvLength = 249; //length of the csv file

  static String[] player = new String[csvLength];    // col 0
  static String[] pos = new String[csvLength];       // col 1
  static String[] team = new String[csvLength];      // col 2

  static int[] rebounds = new int[csvLength];        // col 3
  static int[] assists = new int[csvLength];         // col 4
  static int[] steals = new int[csvLength];          // col 5
  static int[] blocks = new int[csvLength];          // col 6
  static int[] points = new int[csvLength];          // col 7

  static String[] playerTracker = new String[csvLength]; //keeps track of players selected

  static int numberOfTeams = 0; //initializes number of teams
  static int numberOfRounds = 10; //initializes number or rounds

  public static void main(String[] args) {
      intro(); //calls the intro method
      readData(); //calls the readData method
      numberOfTeams = getUserInfo(); //sets number of teams to getUserInfo method
      draft(); //calls draft method
  } //end of main method

/**
Text that introduces the user to the draft
*/
  public static void intro() {
      System.out.println();
      System.out.println("Welcome to the NBA Fantasy Draft!!!!");
      System.out.printf("The stats are Points, Steals, Blocks, Assists, and Rebounds.%n%n");
      System.out.println("The score is calculated as follows:");
      System.out.printf("Points: 1 pt, Steals: 4pts, Blocks: 4pts, Assists: 3 pts, Rebounds: 2pts%n%n");
      System.out.println("Good luck!");
      System.out.println();
  }

/**
Reads in the csv file and the different columns containing player data
*/
  public static void readData() {

      TextIO.readFile("fantasy.csv"); // Read csv
      TextIO.getln();  // skip the line of headers...
      int cate = 0; // categories

      while (!TextIO.eof()) {

        String cell = TextIO.getln(); // read in the next cell from the file
        String[] columns = cell.split(","); // split the columns apart

        //establish categories (cate)
        player[cate] = columns[0];
        pos[cate] = columns[1];
        team[cate] = columns[2];
        rebounds[cate] = Integer.parseInt(columns[3]);
        assists[cate] = Integer.parseInt(columns[4]);
        steals[cate] = Integer.parseInt(columns[5]);
        blocks[cate] = Integer.parseInt(columns[6]);
        points[cate] = Integer.parseInt(columns[7]);

        cate++; // increments the categories

      } // end of while loop

      TextIO.readStandardInput();

  }

/**
Gets user input about number of teams and checks for max teams
*/
  public static int getUserInfo() {

    System.out.println("How many teams are in your draft? (12 Teams Max):");
    int numberOfTeams = TextIO.getlnInt(); //prompts user for input

    while (numberOfTeams > 12 || numberOfTeams <= 0) { // sets number of teams between 1 and 12
          System.out.println("Invalid amount of teams! How many teams are in your draft?:");
          numberOfTeams = TextIO.getlnInt();
    }

    return numberOfTeams;

  }

/**
Prints out the players that are available and organizes them according to their stats
*/
  public static void printPlayers() {

      System.out.println("Available players: ");
      System.out.println("-----------");
      System.out.printf("%-2s %-25s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%n", "ID","Player", "Pos", "Team", "Rebounds", "Assists", "Steals", "Blocks", "Points");

      for(int k = 0; k < player.length; k++) {
          if (player[k].equals("-")) { //Reprints out dashes for unavailable players
              System.out.println("-");
          } else {
              //Prints out the players' stats
              System.out.printf("%-2d: %-25s%-15s%-15s%-15d%-15d%-15d%-15d%-15d%n", k, player[k], pos[k], team[k], rebounds[k], assists[k], steals[k], blocks[k], points[k]);
          }
      }
  }

/**
This starts the draft, and goes through the rounds, as players are prompted each time to make a selection
*/
  public static void draft() {

      int[][] draftResults = new int[numberOfTeams][numberOfRounds]; // 2D array for draft results
      int selectionCounter = 0; //keep track of total number of selections

      for(int i = 0; i< numberOfRounds; i++){

          System.out.println("============");
          System.out.println("Round" + (i + 1)); //increments rounds
          System.out.println("============");

          for (int j = 0; j < numberOfTeams; j++){ //loops through teams to choose players

              printPlayers();
              System.out.printf("%nPlayer %d make your choice.%n",j+1);
  			      int selection = TextIO.getlnInt(); //prompts user for draft selection

              //sees if player is available or not or if selection is out of array index
  			      while (selection > player.length || player[selection].equals("-")) {

                     System.out.println("Invalid selection. Pick again.");
  			             selection = TextIO.getlnInt();

  			      }

              System.out.printf("%nUser selects %s %n",player[selection]); //says player that user selected
  			      draftResults[j][i] = selection; //organizes the players into teams
              playerTracker[selection] = player[selection]; //keeps seperate array without dashes
              player[selection] = "-"; //gives a dash once player has been selected
              selectionCounter++; //increments total number of draft selections

          }

    }

    draftComplete(draftResults); //calls draft complete method

  }

/**
@param draftResults players selected by the teams
This method prints the draft results
*/
  public static void draftComplete(int[][] draftResults) {

      System.out.printf("%n%nDraft Completed");
      System.out.printf("%n===============%n%n");

      for(int l = 0; l < numberOfTeams; l++) { //this loops through the teams and prints them out in order

          System.out.printf("Team %d:%n", l+1);

          for(int m = 0; m < numberOfRounds; m++) { // this loop prints out players that have been selected

              System.out.println(playerTracker[draftResults[l][m]]);

          }

          System.out.println();

      }

      // int count = 0;//
      int[] teamScores = new int[numberOfTeams]; //This creates an array of the team scores
      System.out.printf("%nScores");
      System.out.printf("%n===============%n%n");
      for (int n = 0; n < numberOfTeams; n++) {

          int[] teamPlayers = draftResults[n]; //indexes the players picked to keep track of the players
          //counts the different stats categories
          int pointsScore = 0;
          int assistsScore = 0;
          int reboundsScore = 0;
          int blocksScore = 0;
          int stealsScore = 0;

          if (n == 0) {//spaces out the teams
              System.out.printf("Team %d%n", n+1);
          } else {
              System.out.printf("%nTeam %d%n", n+1);
          }

          for (int x = 0; x < teamPlayers.length; x++) { //this totals up players stats

              pointsScore += points[teamPlayers[x]];
              assistsScore += assists[teamPlayers[x]];
              reboundsScore += rebounds[teamPlayers[x]];
              blocksScore += blocks[teamPlayers[x]];
              stealsScore += steals[teamPlayers[x]];

          }

          // This is the formula that calculates the team score of a user
          int teamScore = pointsScore + (assistsScore*3) + (stealsScore*4) + (reboundsScore*2) + (blocksScore*4);
          teamScores[n] = teamScore; //This stores the teams scores in an array called team score
          System.out.println(teamScore);

    }

    printWinner(teamScores);

  }

/**
This method prints the winner of the fantasy draft
*/
  public static void printWinner(int[] teamScores) {

      System.out.println();
      System.out.println();
      int winningTeamNumber = 0; //initialzes the winning team number
      int winner = 0;

      for(int r = 0; r < teamScores.length; r++){ //this goes through the team scores and determines the winner

          if (teamScores[r] > winner){
              winner = teamScores[r];
              winningTeamNumber = r + 1;
          }

      }

      System.out.printf("Team %d is the winner with a score of %d!!!%n", winningTeamNumber, winner);

  }


} // end of class
