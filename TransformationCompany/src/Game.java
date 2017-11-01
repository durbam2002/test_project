import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
	List<Transformer> transformers = new ArrayList<Transformer>();

	public List<Transformer> getTransformers() {
		return transformers;
	}

	public void setTransformers(List<Transformer> transformers) {
		this.transformers = transformers;
	}

	private List<Transformer> sortByRank(List<Transformer> transformers) {
		Transformer temp;
		for (int i = 1; i < transformers.size(); i++) {
			if (transformers.get(i - 1).getRank() < transformers.get(i).getRank()) {
				temp = transformers.get(i - 1);
				transformers.set(i - 1, transformers.get(i));
				transformers.set(i, temp);
			}
		}

		return transformers;

	}

	private List<Transformer> getAutobots(List<Transformer> transformers) {
		List<Transformer> autobots = new ArrayList<Transformer>();
		for (int i = 0; i < transformers.size(); i++) {
			if (transformers.get(i).getType().equalsIgnoreCase("A")) {
				autobots.add(transformers.get(i));
			}
		}

		autobots = sortByRank(autobots);
		return autobots;
	}

	private List<Transformer> getDecepticons(List<Transformer> transformers) {
		List<Transformer> decepticons = new ArrayList<Transformer>();
		for (int i = 0; i < transformers.size(); i++) {
			if (transformers.get(i).getType().equalsIgnoreCase("D")) {
				decepticons.add(transformers.get(i));
			}
		}

		decepticons = sortByRank(decepticons);
		return decepticons;
	}

	public static List<Transformer> readTransformers() {
		System.out.println("Enter number of transformers:");
		Scanner s1 = new Scanner(System.in);
		int count = s1.nextInt();
		s1.nextLine();

		System.out.println("Enter transformer in following format:");
		System.out.println("Name (String), type (A/D), strength (int), intelligence (int), speed (int), "
				+ "endurance (int), rank (int), courage (int), firepower (int), skill (int)");

		Scanner s2 = new Scanner(System.in);
		List<String> inputList = new ArrayList<String>();
		for (int i = 0; i < count; i++) {
			inputList.add(s2.nextLine());
		}

		s1.close();
		s2.close();

		List<Transformer> transformers = new ArrayList<Transformer>();

		for (int j = 0; j < count; j++) {
			String[] parts = inputList.get(j).split(",");
			Transformer tx = new Transformer();
			tx.setName(parts[0].trim());
			tx.setType(parts[1].trim());
			tx.setStrength(Integer.parseInt(parts[2].trim()));
			tx.setIntelligence(Integer.parseInt(parts[3].trim()));
			tx.setSpeed(Integer.parseInt(parts[4].trim()));
			tx.setEndurance(Integer.parseInt(parts[5].trim()));
			tx.setRank(Integer.parseInt(parts[6].trim()));
			tx.setCourage(Integer.parseInt(parts[7].trim()));
			tx.setFirepower(Integer.parseInt(parts[8].trim()));
			tx.setSkill(Integer.parseInt(parts[9].trim()));
			transformers.add(tx);
			parts = null;
			tx = null;
		}
		return transformers;
	}

	public void displayGameScore(List<Transformer> transformers) {
		List<Transformer> autobots = getAutobots(transformers);
		List<Transformer> decepticons = getDecepticons(transformers);

		int numAutobots = autobots.size();
		int numDecepticons = decepticons.size();

		int numBattle = 0, countAutobot = 0, countDecepticon = 0, countSurvivor = 0, battleCount = 0;
		List<String> winningAutobots = new ArrayList<String>();
		List<String> winningDecepticons = new ArrayList<String>();
		List<String> survivorAutobots = new ArrayList<String>();
		List<String> survivorDecepticons = new ArrayList<String>();

		if (numAutobots > numDecepticons) {
			numBattle = numDecepticons;
			countSurvivor = numAutobots - numDecepticons;
			for (int i = numAutobots - 1; i > numAutobots - 1 - countSurvivor; i--) {
				survivorAutobots.add(autobots.get(i).getName());
			}
		} else if (numAutobots < numDecepticons) {
			numBattle = numAutobots;
			countSurvivor = numDecepticons - numAutobots;
			for (int j = 0; j < countSurvivor; j++) {
				survivorDecepticons.add(decepticons.get(numDecepticons - 1 - j).getName());
			}
		} else {
			numBattle = numAutobots;
		}
		Battle battle = new Battle();
		Transformer winningTeam = new Transformer();
		for (int i = 0; i < numBattle; i++) {
			if (autobots.get(i).getName().equalsIgnoreCase("Optimas Prime")
					&& decepticons.get(i).getName().equalsIgnoreCase("Predaking")) {
				countAutobot = 0;
				countDecepticon = 0;
				survivorAutobots.clear();
				survivorDecepticons.clear();
				battleCount = 1;
				break;
			}

			winningTeam = battle.getBattleScore(autobots.get(i), decepticons.get(i));
			if (winningTeam != null) {
				if (winningTeam.getType().equalsIgnoreCase("A")) {
					countAutobot++;
					winningAutobots.add(autobots.get(i).getName());
				} else {
					countDecepticon++;
					winningDecepticons.add(decepticons.get(i).getName());
				}
			}
			battleCount = Battle.getBattleCount();
		}

		System.out.println(battleCount + " battle");
		if (countAutobot > countDecepticon) {
			System.out.println("Winning team (Autobots): " + winningAutobots);
			if (survivorAutobots.size() > 0) {
				System.out.println("Survivors from the winning team (Autobots): " + survivorAutobots);
			} else if (survivorDecepticons.size() > 0) {
				System.out.println("Survivors from the losing team (Decepticons): " + survivorDecepticons);
			}
		} else if (countDecepticon > countAutobot) {
			System.out.println("Winning team (Decepticons): " + winningDecepticons);
			if (survivorDecepticons.size() > 0) {
				System.out.println("Survivors from the winning team (Decepticons): " + survivorDecepticons);
			} else if (survivorAutobots.size() > 0) {
				System.out.println("Survivors from the losing team (Autobots): " + survivorAutobots);
			}
		} else {
			System.out.println("Winning team: None");
			if (survivorAutobots.size() > 0) {
				System.out.println("Survivors from Autobots: " + survivorAutobots);
			} else if (survivorDecepticons.size() > 0) {
				System.out.println("Survivors from Decepticons: " + survivorDecepticons);
			}
		}
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.displayGameScore(Game.readTransformers());
	}
}