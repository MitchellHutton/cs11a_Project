/**
This program 
*/
public class GroupProject {

  static int csvLength = 249;

  static String[] player = new String[csvLength];    // col 0
  static String[] pos = new String[csvLength];       // col 1
  static String[] team = new String[csvLength];      // col 2

  static int[] rebounds = new int[csvLength];        // col 3
  static int[] assists = new int[csvLength];         // col 4
  static int[] steals = new int[csvLength];          // col 5
  static int[] blocks = new int[csvLength];          // col 6
  static int[] points = new int[csvLength];          // col 7

  static String[] playerTracker = new String[csvLength];

  static int numberOfTeams = 0;
  static int numberOfRounds = 10;

  public static void main(String[] args) {
      intro();
      readData();
      numberOfTeams = getUserInfo();
      draft();
  }

  public static void intro() {
    System.out.println();
    System.out.println("Welcome to the NBA Fantasy Draft!!!!");
    System.out.printf("The stats are Points, Steals, Blocks, Assists, and Rebounds.%n%n");
    System.out.println("The score is calculated as follows:");
    System.out.printf("Points: 1 pt, Steals: 4pts, Blocks: 4pts, Assists: 3 pts, Rebounds: 2pts%n%n");
    System.out.println("Good luck!");
    System.out.println();
  }

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
  //Draft();
  }

  public static int getUserInfo() {
    System.out.println("How many teams are in your draft? (12 Teams Max):");
    int numberOfTeams = TextIO.getlnInt();
    while (numberOfTeams > 12 || numberOfTeams <= 0) {
          System.out.println("Invalid amount of teams! How many teams are in your draft?:");
          numberOfTeams = TextIO.getlnInt();
    }
    return numberOfTeams;
  }

  public static void printPlayers() {
    System.out.println("Available players: ");
    System.out.println("-----------");
    System.out.printf("%-2s %-25s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%n", "ID","Player", "Pos", "Team", "Rebounds", "Assists", "Steals", "Blocks", "Points");
    for(int k = 0; k < player.length; k++) {
      if (player[k].equals("-")) {
          System.out.println("-");
      } else {
      System.out.printf("%-2d: %-25s%-15s%-15s%-15d%-15d%-15d%-15d%-15d%n", k, player[k], pos[k], team[k], rebounds[k], assists[k], steals[k], blocks[k], points[k]);
      }
    }
  }

  public static void draft() {
    int[][] draftResults = new int[numberOfTeams][numberOfRounds]; // 2D array for draft results
    int selectionCounter = 0;

    for(int i = 0; i< numberOfRounds; i++){
      System.out.println("============");
      System.out.println("Round" + (i + 1));
      System.out.println("============");
      for (int j = 0; j < numberOfTeams; j++) {
        printPlayers();
        System.out.printf("%nPlayer %d make your choice.%n",j+1);
  			int selection = TextIO.getlnInt();

  			while (selection > player.length || player[selection].equals("-")) {
  			  System.out.println("Invalid selection. Pick again.");
  			  selection = TextIO.getlnInt();
  			}

        System.out.printf("%nUser selects %s %n",player[selection]);
  			draftResults[j][i] = selection;
        playerTracker[selection] = player[selection];
        player[selection] = "-";
        selectionCounter++;
      }
    }
    draftComplete(draftResults);
  }

  public static void draftComplete(int[][] draftResults) {
    System.out.printf("%n%nDraft Completed");
    System.out.printf("%n===============%n%n");

    for(int l = 0; l < numberOfTeams; l++) {
      System.out.printf("Team %d:%n", l+1);
      for(int m = 0; m < numberOfRounds; m++) {
        System.out.println(playerTracker[draftResults[l][m]]);
      }
      System.out.println();
    }

    int count = 0;
    int[] teamScores = new int[numberOfTeams];
    System.out.printf("%nScores");
    System.out.printf("%n===============%n%n");
    for (int n = 0; n < numberOfTeams; n++) {

        int[] teamPlayers = draftResults[n];

        int pointsScore = 0;
        int assistsScore = 0;
        int reboundsScore = 0;
        int blocksScore = 0;
        int stealsScore = 0;

        if (n == 0) {
            System.out.printf("Team %d%n", n+1);
        } else {
            System.out.printf("%nTeam %d%n", n+1);
        }

        for (int x = 0; x < teamPlayers.length; x++) {
          pointsScore += points[teamPlayers[x]];
          assistsScore += assists[teamPlayers[x]];
          reboundsScore += rebounds[teamPlayers[x]];
          blocksScore += blocks[teamPlayers[x]];
          stealsScore += steals[teamPlayers[x]];
        }

        int teamScore = pointsScore + (assistsScore*3) + (stealsScore*4) + (reboundsScore*2) + (blocksScore*4);
        teamScores[n] = teamScore;
        System.out.println(teamScore);
    }
    printWinner(teamScores);
  }

  public static void printWinner(int[] teamScores) {
    System.out.println();
    System.out.println();
    int winningTeamNumber = -1;
    int winner = -1;
    for(int r = 0; r < teamScores.length; r++){
        if (teamScores[r] > winner){
            winner = teamScores[r];
            winningTeamNumber = r + 1;
        }
    }
    System.out.printf("Team %d is the winner with a score of %d!!!%n", winningTeamNumber, winner);
  }
} // end of class
