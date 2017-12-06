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

      System.out.println();
      System.out.println("Welcome to the fantasy draft!!!!");
      System.out.println("This is an NBA fantasy draft.");
      System.out.println("The stats are Points, Steals, Blocks, Assists, and Rebounds.");
      System.out.println("Good luck!");
      System.out.println();

      System.out.println("How many teams are in your draft?:");
      int teams = TextIO.getlnInt();

      // Read csv
      TextIO.readFile("fantasy.csv");
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
    				player[selection] = "-";

            System.out.printf("%n%nDraft Completed");
        		System.out.println("===============");
        		for(int i = 0; i < teams; i++) {
        			System.out.printf("Team %d: ", i);
        			for(int j = 0; j < rounds; j++) {
        				System.out.println(player[draftResults[i][j]]);
        			}
        			System.out.println();
        		}

        }
      }

  } // end of main

} // end of class
