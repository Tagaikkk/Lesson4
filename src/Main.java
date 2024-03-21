import java.util.Random;
public class Main {
            public static int bossHealth = 700;
            public static int bossDamage = 50;
            public static String bossDefence;
            public static int[] heroesHealth = {270, 280, 250, 100};
            public static int[] heroesDamage = {20, 10, 15, 0}; //
            public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Healer"}; //
            public static int roundNumber = 0;


            public static void main(String[] args) {
                showStatistics();
                while (!isGameOver()) {
                    playRound();
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
                }
                return allHeroesDead;
            }

            public static void playRound() {
                roundNumber++;
                chooseDefence();
                bossAttacks();
                heroesAttack();
                healHeroes();
                showStatistics();
            }

            public static void chooseDefence() {
                Random random = new Random();
                int randomIndex = random.nextInt(heroesAttackType.length);
                bossDefence = heroesAttackType[randomIndex];
            }

            public static void heroesAttack() {
                for (int i = 0; i < heroesDamage.length; i++) {
                    if (heroesHealth[i] > 0 && bossHealth > 0) {
                        int damage = heroesDamage[i];
                        if (heroesAttackType[i].equals(bossDefence)) {
                            Random random = new Random();
                            int coeff = random.nextInt(9) + 2; //2,3,4,5,6,7,8,9,10
                            damage = heroesDamage[i] * coeff;
                            System.out.println("Critical damage: " + damage);
                        }
                        if (bossHealth - damage < 0) {
                            bossHealth = 0;
                        } else {
                            bossHealth = bossHealth - damage;
                        }
                    }
                }
            }

            public static void bossAttacks() {
                for (int i = 0; i < heroesHealth.length; i++) {
                    if (heroesHealth[i] > 0) {
                        if (heroesHealth[i] - bossDamage < 0) {
                            heroesHealth[i] = 0;
                        } else {
                            heroesHealth[i] = heroesHealth[i] - bossDamage;
                        }
                    }
                }
            }

            public static void healHeroes() {
                // Лечим только героев со здоровьем менее 100 единиц
                for (int i = 0; i < heroesHealth.length; i++) {
                    if (heroesHealth[i] > 0 && heroesHealth[i] < 100) {
                        heroesHealth[i] += 30; // Примерный объем лечения
                        System.out.println("Medic heals " + heroesAttackType[i] + " for 30 health points.");
                    }
                }
            }

            public static void showStatistics() {
                System.out.println("ROUND " + roundNumber + " ----------------");
                System.out.println("Boss health: " + bossHealth + " damage: " + bossDamage + " defence: " +
                        (bossDefence == null ? "No defence" : bossDefence));
                for (int i = 0; i < heroesHealth.length; i++) {
                    System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + " damage: " + heroesDamage[i]);
                }
            }
        }