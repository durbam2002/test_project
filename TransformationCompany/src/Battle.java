public class Battle {
	static int battleCount = 0;

	Score battleScore = new Score();

	public static int getBattleCount() {
		return battleCount;
	}

	public static void setBattleCount(int battleCount) {
		Battle.battleCount = battleCount;
	}

	public Score getBattleScore() {
		return battleScore;
	}

	public void setBattleScore(Score battleScore) {
		this.battleScore = battleScore;
	}

	public Transformer getBattleScore(Transformer autobot, Transformer decepticon) {
		battleCount++;
		int autobotRating = 0, decepticonRating = 0;
		if (autobot.getName().compareToIgnoreCase("Optimas Prime") == 0) {
			battleScore.setWinningTeam(autobot);
		} else if (decepticon.getName().compareToIgnoreCase("Predaking") == 0) {
			battleScore.setWinningTeam(decepticon);
		} else if ((autobot.getCourage() <= decepticon.getCourage() - 4)
				&& (autobot.getStrength() <= decepticon.getStrength() - 3)) {
			battleScore.setWinningTeam(decepticon);
		} else if ((decepticon.getCourage() <= autobot.getCourage() - 4)
				&& (decepticon.getStrength() <= autobot.getStrength() - 3)) {
			battleScore.setWinningTeam(autobot);
		} else {
			autobotRating = autobot.getCourage() + autobot.getEndurance() + autobot.getFirepower()
					+ autobot.getIntelligence() + autobot.getRank() + autobot.getSkill() + autobot.getSpeed()
					+ autobot.getStrength();

			decepticonRating = decepticon.getCourage() + decepticon.getEndurance() + decepticon.getFirepower()
					+ decepticon.getIntelligence() + decepticon.getRank() + decepticon.getSkill()
					+ decepticon.getSpeed() + decepticon.getStrength();

			if (autobotRating == decepticonRating) {
				battleScore.setWinningTeam(null);
			} else if (autobotRating > decepticonRating) {
				battleScore.setWinningTeam(autobot);
			} else {
				battleScore.setWinningTeam(decepticon);
			}
		}
		return battleScore.getWinningTeam();
	}
}
