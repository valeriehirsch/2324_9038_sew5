
//TODO: Mein Name in der Javadoc

public class Labyrinth {
	public static String[][] maps = {{
		"############",
		"#  #     # #",
		"## # ### # #",
		"#  # # # # #",
		"## ### # # #",
		"#        # #",
		"## ####### #",
		"#          #",
		"# ######## #",
		"# #   #    #",
		"#   #   # ##",
		"######A#####"
	}, {
		"################################",
		"#                              #",
		"# ############################ #",
		"# # ###       ##  #          # #",
		"# #     ##### ### # ########## #",
		"# #   ##### #     # #      ### #",
		"# # ##### #   ###   # # ## # # #",
		"# # ### # ## ######## # ##   # #",
		"# ##### #  # #   #    #    ### #",
		"# # ### ## # # # # ####### # # #",
		"# #        # #   #     #     # #",
		"# ######## # ######### # ### # #",
		"# ####     #  # #   #  # ##### #",
		"# # #### #### # # # # ## # ### #",
		"#                      # #     #",
		"###########################A####"
	}, {
		"###########################A####",
		"#   #      ## # # ###  #     # #",
		"# ###### #### # # #### ##### # #",
		"# # ###  ## # # # #          # #",
		"# # ### ### # # # # # #### # # #",
		"# #     ### # # # # # ## # # # #",
		"# # # # ### # # # # ######## # #",
		"# # # #     #          #     # #",
		"# ### ################ # # # # #",
		"# #   #             ## # #   # #",
		"# # #### ############# # #   # #",
		"# #                    #     # #",
		"# # #################### # # # #",
		"# # #### #           ###     # #",
		"# # ## # ### ### ### ### # ### #",
		"# #    #     ##  ##  # ###   # #",
		"# ####   ###### #### # ###  ## #",
		"###########################A####"
	}, {
		"#############",
		"#           #",
		"#           #",
		"#           #",
		"###########A#"
	}};

	/**
	 * Wandelt (unveränderliche) Strings in Char-Arrays
	 * @param map  der Plan, ein String je Zeile
	 * @return char[][] des Plans
	 */
	public static char[][] fromStrings(String[] map) {
		char res[][] = new char[map.length][];
		for (int i = 0; i < map.length; i++) {
			res[i] = map[i].toCharArray();
		}
		return res;
	}


	/**
	 * Ausgabe des Layrinths
	 * @param lab
	 */
	public static void printLabyrinth(char[][] lab) {
		for (char[] chars : lab) {
			System.out.println();
			for (char aChar : chars) {
				System.out.print(aChar);
			}
		}
		System.out.println();
	}

	/**
	 * Suche den Weg
	 * @param zeile     aktuelle Position
	 * @param spalte     aktuelle Position
	 * @param lab
	 * @throws InterruptedException    für die verlangsamte Ausgabe mit sleep()
	 */
	public static boolean suchen(int zeile, int spalte, char[][] lab) throws InterruptedException {
		if (lab[zeile][spalte] == 'A'){
			return true;
		}
		if (lab[zeile][spalte] == '#'){
			return false;
		}
		if (lab[zeile][spalte] == '+'){
			return false;
		}
		if (zeile < 0 || spalte < 0 || zeile >= lab.length || spalte >= lab[zeile].length){
			return false;
		}

		lab[zeile][spalte] = '+';
		boolean oben = suchen(zeile - 1, spalte, lab);
		boolean unten = suchen(zeile + 1, spalte, lab);
		boolean links = suchen(zeile, spalte-1, lab);
		boolean rechts = suchen(zeile, spalte+1, lab);

		if (links){
			return links;
		}

		if (rechts){
			return rechts;
		}

		if (oben){
			return oben;
		}

		if (unten){
			return unten;
		}

		lab[zeile][spalte] = ' ';

	return false;
	}

	public static int suchenAlle(int zeile, int spalte, char[][] lab) throws InterruptedException {
		if (zeile < 0 || spalte < 0 || zeile >= lab.length || spalte >= lab[zeile].length){
			return 0;
		}
		if (lab[zeile][spalte] == 'A'){
			return 1;
		}
		if (lab[zeile][spalte] == '#'){
			return 0;
		}
		if (lab[zeile][spalte] == '+'){
			return 0;
		}


		lab[zeile][spalte] = '+';
		int oben = suchenAlle(zeile - 1, spalte, lab);
		int unten = suchenAlle(zeile + 1, spalte, lab);
		int links = suchenAlle(zeile, spalte-1, lab);
		int rechts = suchenAlle(zeile, spalte+1, lab);

		lab[zeile][spalte] = ' ';

		return oben+unten+links+rechts;
	}

	public static void main(String[] args) throws InterruptedException {
		char[][] labyrinth = fromStrings(maps[2]);
		printLabyrinth(labyrinth);
		//System.out.println("Ausgang gefunden: " + (suchen(5, 5, labyrinth) ? "ja" : "nein"));
		printLabyrinth(labyrinth);
		System.out.println("Anzahl Wege: " + suchenAlle(5, 5, labyrinth));
	}
}
