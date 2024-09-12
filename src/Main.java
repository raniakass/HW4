import java.util.Random;

public class Main {
    public static int bossHealth = 650;
    public static int bossDamage = 50;
    public static String bossDefence;
    public static int[] heroesHealth = {270, 280, 250, 260, 300, 500, 230};
    public static int[] heroesDamage = {20, 15, 10, 5};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Golem"};
    public static int roundNumber = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameOver()) {
            playRound();
        }
    }

    private static void printStatistics() {
    }

    public static void healing() {
        Random random = new Random();
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[3] > 0) {
                if (heroesHealth[i] < 100 && i != 3) {
                    int heal = random.nextInt(100);
                    heroesHealth[i] += heal;
                    System.out.println("Hero healing");
                    break;
                }
            }
        }
    }

    public static void witcher() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[4] > 0) {
                if (heroesHealth[i] < 0 && i != 4) {
                    heroesHealth[i] += heroesHealth[4];
                    heroesHealth[4] = 0;
                }
            }
        }
    }


    public static boolean isGameOver() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        bossAttack();
        witcher();
        healing();
        heroesAttack();
        printStatistics();
    }

    private static void heroesAttack() {
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0, 1, 2
        bossDefence = heroesAttackType[randomIndex];
    }

    public static void bossAttack() {
        int newBossDamage = (int) (bossDamage * 0.2);
        Random random = new Random();
        if (random.nextBoolean()) {
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[i] > 0) {
                    if (heroesHealth[i] - bossDamage < 0) {
                        heroesHealth[i] = 0;
                    } else {
                        if (heroesHealth[6] > 0) {
                            boolean luk = random.nextBoolean();
                            if (heroesHealth[5] > 0) {
                                if (i != 5) {
                                    if (luk && i == 6) {
                                        heroesHealth[6] += newBossDamage;
                                        System.out.println("Lucky dodge");
                                    }
                                    heroesHealth[i] = heroesHealth[i] - newBossDamage;
                                } else {
                                    heroesHealth[i] -= (int) (bossDamage * 0.8);
                                }
                            } else {
                                heroesHealth[i] -= bossDamage;
                            }
                        }
                    }
                }
            }
        } else {
            System.out.println("Thor stunned");
        }
    }}

