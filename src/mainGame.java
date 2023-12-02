import Fight.Fight;
import Map.Map;
import Obstacle.Monster.Boss.Boss;
import Obstacle.Monster.Monster;
import Obstacle.Monster.Slime.Slime;
import Obstacle.Obstacle;
import Obstacle.Stone.Stone;
import Player.Elf.Elf;
import Player.Player;
import Player.Warrior.Warrior;
import Player.Wizard.Wizard;
import Store.Store;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import javax.swing.*;


public class mainGame {
    public static void main(String[] args) {


        //Interface graphique
        JFrame window = new JFrame("RPG Game");
        window.setSize(700, 700);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.getContentPane().setBackground(Color.GRAY);

        GridLayout lay = new GridLayout(0, 2);

        window.setLayout(lay);

        JLabel labClass, labName, elf, warrior, wizard, vide;
        JTextField name, pClass;
        JButton submit;

        labName = new JLabel("Please enter your player's name :");
        labName.setHorizontalAlignment(JLabel.CENTER);
        window.add(labName);

        name = new JTextField();
        window.add(name);

        elf = new JLabel("1 : Elf : A powerful creature from the forest");
        elf.setHorizontalAlignment(JLabel.CENTER);
        window.add(elf);

        warrior = new JLabel("2 : Warrior: A strong man which only swear with strength");
        warrior.setHorizontalAlignment(JLabel.CENTER);
        window.add(warrior);

        wizard = new JLabel("3 : Wizard : A strange character which only use spells");
        wizard.setHorizontalAlignment(JLabel.CENTER);
        window.add(wizard);

        vide = new JLabel();
        window.add(vide);


        labClass = new JLabel("Please enter your player's class (Just the number):");
        labClass.setHorizontalAlignment(JLabel.CENTER);
        window.add(labClass);

        pClass = new JTextField();
        window.add(pClass);

        final String[] enterClass = new String[1];
        final String[] namePlayer = new String[1];

        submit = new JButton("START");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setVisible(false);
                enterClass[0] = pClass.getText();
                namePlayer[0] = name.getText();


                int playerClass = Integer.parseInt(enterClass[0]);

                Player choosePlayer;

                 choosePlayer = switch (playerClass) {
                    case 1 -> new Elf(namePlayer[0]);
                    case 2 -> new Warrior(namePlayer[0]);
                    case 3 -> new Wizard(namePlayer[0]);
                    default -> choosePlayer = new Elf(namePlayer[0]);
                };


                final Player player;

                player = choosePlayer;

                JFrame playerStats = new JFrame("Your stats");
                playerStats.setSize(700, 700);
                playerStats.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                playerStats.getContentPane().setBackground(Color.GRAY);

                GridLayout statGrid = new GridLayout(0, 2);
                playerStats.setLayout(statGrid);

                JLabel nameText, nameLab, dmgLab, dmgText, mgDmgLab, mgDmgText, defLab, defText, mgResLab, mgResText, hpLab, hpText, coinsLab, coinsText, weaponText, weaponLab, lvlText, lvlLab;
                JButton startGame;

                nameLab = new JLabel("Your name : ");
                nameLab.setHorizontalAlignment(JLabel.CENTER);
                playerStats.add(nameLab);

                nameText = new JLabel(player.getName());
                nameText.setHorizontalAlignment(JLabel.CENTER);
                playerStats.add(nameText);


                dmgText = new JLabel("Your default damage : ");
                dmgText.setHorizontalAlignment(JLabel.CENTER);
                playerStats.add(dmgText);

                dmgLab = new JLabel(String.valueOf(player.getDamage()));
                dmgLab.setHorizontalAlignment(JLabel.CENTER);
                playerStats.add(dmgLab);

                mgDmgText = new JLabel("Your magic damage : ");
                mgDmgText.setHorizontalAlignment(JLabel.CENTER);
                playerStats.add(mgDmgText);

                mgDmgLab = new JLabel(String.valueOf(player.getMagicDamage()));
                mgDmgLab.setHorizontalAlignment(JLabel.CENTER);
                playerStats.add(mgDmgLab);

                defLab = new JLabel("Your defense : ");
                defLab.setHorizontalAlignment(JLabel.CENTER);
                playerStats.add(defLab);

                defText = new JLabel(String.valueOf(player.getDefense()));
                defText.setHorizontalAlignment(JLabel.CENTER);
                playerStats.add(defText);

                mgResLab = new JLabel("Your resistance magic : ");
                mgResLab.setHorizontalAlignment(JLabel.CENTER);
                playerStats.add(mgResLab);

                mgResText = new JLabel(String.valueOf(player.getMgRes()));
                mgResText.setHorizontalAlignment(JLabel.CENTER);
                playerStats.add(mgResText);

                hpText = new JLabel("Your hp :");
                hpText.setHorizontalAlignment(JLabel.CENTER);
                playerStats.add(hpText);

                hpLab = new JLabel(String.valueOf(player.getHp()));
                hpLab.setHorizontalAlignment(JLabel.CENTER);
                playerStats.add(hpLab);

                coinsText = new JLabel("Your money :");
                coinsText.setHorizontalAlignment(JLabel.CENTER);
                playerStats.add(coinsText);

                coinsLab = new JLabel(String.valueOf(player.getMoney()));
                coinsLab.setHorizontalAlignment(JLabel.CENTER);
                playerStats.add(coinsLab);

                weaponText = new JLabel("Your start only with your hands");
                weaponText.setHorizontalAlignment(JLabel.CENTER);
                playerStats.add(weaponText);

                weaponLab = new JLabel(String.valueOf(player.getWeapon()));
                playerStats.add(weaponLab);

                lvlText = new JLabel("Your level :");
                lvlText.setHorizontalAlignment(JLabel.CENTER);
                playerStats.add(lvlText);

                lvlLab = new JLabel(String.valueOf(player.getLvl()));
                lvlLab.setHorizontalAlignment(JLabel.CENTER);
                playerStats.add(lvlLab);

                startGame = new JButton("Start the game");
                startGame.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Monster bs = new Boss();
                        Map myMap = new Map();
                        int hpBoss = bs.getHp();

                        JFrame gameStart = new JFrame("Game start");
                        gameStart.setSize(700, 700);
                        gameStart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        gameStart.getContentPane().setBackground(Color.GRAY);

                        GridLayout gameGrid = new GridLayout(0, 2);
                        gameStart.setLayout(gameGrid);

                        JLabel mapText, map, row, col;
                        JTextField rowField, colField;
                        JButton move, store, stats;
                        JPanel mapPanel;


                        mapText = new JLabel("""
                                <html>
                                1-> Can't move here <br>
                                2-> Player<br>
                                3-> Simple and destructible obstacle<br>
                                5-> Boss<br>
                                6-> Shop
                                <html>""");
                        mapText.setHorizontalAlignment(JLabel.CENTER);
                        gameStart.add(mapText);

                        //MAP DANS LE PANEL ICI
                        mapPanel = new JPanel();
                        mapPanel = myMap.displayMap(mapPanel, player, bs);
                        gameStart.add(mapPanel);


                        colField = new JTextField();
                        gameStart.add(colField);


                        stats = new JButton("STATS");
                        stats.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JFrame showStat = new JFrame("ACTUAL STATS");
                                showStat.setSize(700, 700);
                                showStat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                showStat.getContentPane().setBackground(Color.GRAY);

                                GridLayout gridStat = new GridLayout(0, 2);
                                showStat.setLayout(gridStat);

                                JLabel nameText, nameLab, dmgLab, dmgText, mgDmgLab, mgDmgText, defLab, defText, mgResLab, mgResText, hpLab, hpText, coinsLab, coinsText, weaponText, weaponLab, lvlText, lvlLab;
                                JButton startGame;

                                nameLab = new JLabel("Your name : ");
                                nameLab.setHorizontalAlignment(JLabel.CENTER);
                                showStat.add(nameLab);

                                nameText = new JLabel(player.getName());
                                nameText.setHorizontalAlignment(JLabel.CENTER);
                                showStat.add(nameText);


                                dmgText = new JLabel("Your default damage : ");
                                dmgText.setHorizontalAlignment(JLabel.CENTER);
                                showStat.add(dmgText);

                                dmgLab = new JLabel(String.valueOf(player.getDamage()));
                                dmgLab.setHorizontalAlignment(JLabel.CENTER);
                                showStat.add(dmgLab);

                                mgDmgText = new JLabel("Your magic damage : ");
                                mgDmgText.setHorizontalAlignment(JLabel.CENTER);
                                showStat.add(mgDmgText);

                                mgDmgLab = new JLabel(String.valueOf(player.getMagicDamage()));
                                mgDmgLab.setHorizontalAlignment(JLabel.CENTER);
                                showStat.add(mgDmgLab);

                                defLab = new JLabel("Your defense : ");
                                defLab.setHorizontalAlignment(JLabel.CENTER);
                                showStat.add(defLab);

                                defText = new JLabel(String.valueOf(player.getDefense()));
                                defText.setHorizontalAlignment(JLabel.CENTER);
                                showStat.add(defText);

                                mgResLab = new JLabel("Your resistance magic : ");
                                mgResLab.setHorizontalAlignment(JLabel.CENTER);
                                showStat.add(mgResLab);

                                mgResText = new JLabel(String.valueOf(player.getMgRes()));
                                mgResText.setHorizontalAlignment(JLabel.CENTER);
                                showStat.add(mgResText);

                                hpText = new JLabel("Your hp :");
                                hpText.setHorizontalAlignment(JLabel.CENTER);
                                showStat.add(hpText);

                                hpLab = new JLabel(String.valueOf(player.getHp()));
                                hpLab.setHorizontalAlignment(JLabel.CENTER);
                                showStat.add(hpLab);

                                coinsText = new JLabel("Your money :");
                                coinsText.setHorizontalAlignment(JLabel.CENTER);
                                showStat.add(coinsText);

                                coinsLab = new JLabel(String.valueOf(player.getMoney()));
                                coinsLab.setHorizontalAlignment(JLabel.CENTER);
                                showStat.add(coinsLab);

                                if (player.getWeapon() != null){
                                    weaponText = new JLabel("Your weapon :");
                                    weaponText.setHorizontalAlignment(JLabel.CENTER);
                                    showStat.add(weaponText);

                                    weaponLab = new JLabel(String.valueOf(player.getWeapon().getName()));
                                    showStat.add(weaponLab);
                                }else {
                                    weaponText = new JLabel("You d'ant have any weapon yet");
                                    weaponText.setHorizontalAlignment(JLabel.CENTER);
                                    showStat.add(weaponText);

                                    showStat.add(new JLabel());
                                }


                                lvlText = new JLabel("Your level :");
                                lvlText.setHorizontalAlignment(JLabel.CENTER);
                                showStat.add(lvlText);

                                lvlLab = new JLabel(String.valueOf(player.getLvl()));
                                lvlLab.setHorizontalAlignment(JLabel.CENTER);
                                showStat.add(lvlLab);

                                showStat.setVisible(true);
                            }
                        });
                        gameStart.add(stats);
                        gameStart.setVisible(true);

                    }
                });
                playerStats.add(startGame);

                playerStats.setVisible(true);

            }
        });

        window.add(submit);


        window.setVisible(true);

    }
}