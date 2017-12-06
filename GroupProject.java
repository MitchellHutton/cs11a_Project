public class GroupProject {
  public static void main(String[] args) {

      int csvLength = 249;

      String[] player = new String[csvLength];    // col 0
      String[] pos = new String[csvLength];       // col 1
      String[] team = new String[csvLength];      // col 2

      int[] rebounds = new int[csvLength];        // col 3
      int[] assists = new int[csvLength];         // col 4
      int[] steals = new int[csvLength];          // col 5
      int[] blocks = new int[csvLength];          // col 6
      int[] points = new int[csvLength];          // col 7

      String[] playerTracker = new String[csvLength];

      int[] reboundsTracker = new int[csvLength];
      int[] assistsTracker = new int[csvLength];
      int[] stealsTracker = new int[csvLength];
      int[] blocksTracker = new int[csvLength];
      int[] pointsTracker = new int[csvLength];

      System.out.println();
      System.out.println("Welcome to the NBA Fantasy Draft!!!!");
      System.out.printf("The stats are Points, Steals, Blocks, Assists, and Rebounds.%n%n");
      System.out.println("The score is calculated as follows:");
      System.out.printf("Points: 1 pt, Steals: 4pts, Blocks: 4pts, Assists: 3 pts, Rebounds: 2pts%n%n");
      System.out.println("Good luck!");
      System.out.println();

      System.out.println("How many teams are in your draft?:");
      int teams = TextIO.getlnInt();

      // Read csv
      TextIO.readFile("fantasy.csv");
      TextIO.getln();  // skip the line of headers...
      int cate = 0; // categories
      int selectionCounter = 0;

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

      int draftPool = cate;

      int rounds = 10; // number of players per team and rounds of the draft
      int[][] draftResults = new int[teams][rounds]; // 2D array for draft results

      for(int i = 0; i< rounds; i++){
        System.out.println("============");
        System.out.println("Round" + (i + 1));
        System.out.println("============");
        for (int j = 0; j < teams; j++) {

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

            System.out.printf("%nPlayer %d make your choice.%n",j+1);
    			int selection = TextIO.getlnInt();

    			while (selection > player.length || player[selection].equals("-")) {
    			  System.out.println("Invalid selection. Pick again.");
    			  selection = TextIO.getlnInt();
    				}

    				System.out.printf("%nUser selects %s %n",player[selection]);
    				draftResults[j][i] = selection;
                    playerTracker[selection] = player[selection];
                    reboundsTracker[selection] = rebounds[selection];
                    assistsTracker[selection] = assists[selection];
                    stealsTracker[selection] = steals[selection];
                    blocksTracker[selection] = blocks[selection];
                    pointsTracker[selection] = points[selection];
                    player[selection] = "-";


            selectionCounter++;

        }
      }

      System.out.printf("%n%nDraft Completed");
      System.out.printf("%n===============%n%n");
      for(int l = 0; l < teams; l++) {
        System.out.printf("Team %d:%n", l+1);
        for(int m = 0; m < rounds; m++) {
          System.out.println(playerTracker[draftResults[l][m]]);
        }
        System.out.println();
      }

      System.out.printf("%nScores");
      System.out.printf("%n===============%n%n");
      for (int n = 0; n < teams; n++) {

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

          for (int p = n; p < selectionCounter; p+=teams) {

              pointsScore += pointsTracker[p];
              assistsScore += assistsTracker[p];
              reboundsScore += reboundsTracker[p];
              blocksScore += blocksTracker[p];
              stealsScore += stealsTracker[p];

          }

          for (int q = n; q <= n; q++) {

                  switch (n) {
                    case 0: System.out.printf("Score: %d", pointsScore + (assistsScore*3) + (stealsScore*4) + (reboundsScore*2) + (blocksScore*4));
                    break;
                    case 1: System.out.printf("Score: %d", pointsScore + (assistsScore*3) + (stealsScore*4) + (reboundsScore*2) + (blocksScore*4));
                    break;
                    case 2: System.out.printf("Score: %d", pointsScore + (assistsScore*3) + (stealsScore*4) + (reboundsScore*2) + (blocksScore*4));
                    break;
                    case 3: System.out.printf("Score: %d", pointsScore + (assistsScore*3) + (stealsScore*4) + (reboundsScore*2) + (blocksScore*4));
                    break;
                    case 4: System.out.printf("Score: %d", pointsScore + (assistsScore*3) + (stealsScore*4) + (reboundsScore*2) + (blocksScore*4));
                    break;
                    case 5: System.out.printf("Score: %d", pointsScore + (assistsScore*3) + (stealsScore*4) + (reboundsScore*2) + (blocksScore*4));
                    break;
                    case 6: System.out.printf("Score: %d", pointsScore + (assistsScore*3) + (stealsScore*4) + (reboundsScore*2) + (blocksScore*4));
                    break;
                    case 7: System.out.printf("Score: %d", pointsScore + (assistsScore*3) + (stealsScore*4) + (reboundsScore*2) + (blocksScore*4));
                    break;
                    case 8: System.out.printf("Score: %d", pointsScore + (assistsScore*3) + (stealsScore*4) + (reboundsScore*2) + (blocksScore*4));
                    break;
                    case 9: System.out.printf("Score: %d", pointsScore + (assistsScore*3) + (stealsScore*4) + (reboundsScore*2) + (blocksScore*4));
                    break;
                    case 10: System.out.printf("Score: %d", pointsScore + (assistsScore*3) + (stealsScore*4) + (reboundsScore*2) + (blocksScore*4));
                    break;
                    case 11: System.out.printf("Score: %d", pointsScore + (assistsScore*3) + (stealsScore*4) + (reboundsScore*2) + (blocksScore*4));
                    break;
                    default: System.out.printf("End of scoring.");
                }


          }


    }

    System.out.println();
    System.out.println();

  } // end of main

} // end of class
