package Fight;

import Obstacle.Monster.Monster;
import Obstacle.Obstacle;
import Player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Scanner;

public class Fight {

    private Obstacle ob;

    private Player p;

    public Obstacle getOb() {
        return ob;
    }

    public void setOb(Obstacle ob) {
        this.ob = ob;
    }

    public Player getP() {
        return p;
    }

    public void setP(Player p) {
        this.p = p;
    }

    public Fight(Obstacle ob, Player p){
        this.p = p;
        this.ob = ob;
    }

    public void destroy(){
        Fight newF = this;

        JFrame destroyObstacle = new JFrame("OBSTACLE DESTRUCTION !!!!");
        destroyObstacle.setSize(500, 500);
        destroyObstacle.getContentPane().setBackground(Color.GRAY);
        destroyObstacle.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GridLayout gameGrid = new GridLayout(0, 4);
        destroyObstacle.setLayout(gameGrid);

        JLabel choiceText, communication;
        JTextField choice;
        JButton choose;

        communication = new JLabel("Here is an obstacle with :" + this.getOb().getHp() + "HP");
        destroyObstacle.add(communication);

        choiceText = new JLabel("Would you destroy it, or just past by it ?");
        destroyObstacle.add(choiceText);

        choice = new JTextField();
        destroyObstacle.add(choice);

        choose = new JButton("YES/NO ?");
        destroyObstacle.add(choose);
        choose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String choose = choice.getText().toUpperCase();
                if(Objects.equals(choose, "YES")){
                    newF.menuFight(newF.getOb(), newF.getP());
                } else {
                    JDialog errorChoose = new JDialog(new Frame(), "ERROR");
                    errorChoose.add(new JLabel("JUST SAY YES PLEASE"));
                    errorChoose.setSize(500,100);
                    errorChoose.setVisible(true);
                    System.out.println(choice.getText());
                }
            }
        });

        destroyObstacle.setVisible(true);
    }

    public void menuFight(Obstacle ob, Player p){
        String choice;
            JFrame fightFrame = new JFrame("FIGHT !!!!!");
            fightFrame.setSize(700,700);
            fightFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            fightFrame.getContentPane().setBackground(Color.GRAY);

            GridLayout gameGrid = new GridLayout(0, 3);
            fightFrame.setLayout(gameGrid);

            JLabel action, monsterHp, playerHp;
            JTextField choosenAction;
            JButton actionBut;

            monsterHp = new JLabel(String.valueOf("Monster HP : " + ob.getHp()));
            fightFrame.add(monsterHp);

            fightFrame.add(new JLabel());

            playerHp = new JLabel("Player HP : " + p.getHp());
            fightFrame.add(playerHp);

            action = new JLabel("<html> What would you ? <br>" +
                    "Attack ? <br>" +
                    "Heal <br>" +
                    "Heal mana <html>");
            fightFrame.add(action);

            choosenAction = new JTextField();
            fightFrame.add(choosenAction);

            actionBut = new JButton("ACTION");
            fightFrame.add(actionBut);
            actionBut.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(Objects.equals(choosenAction.getText(), "Attack")){
                        p.attack(ob);
                        if(ob.getHp() <0){
                            ob.setHp(0);
                        }
                        monsterHp.setText(String.valueOf("Monster HP :" + ob.getHp()));
                        if (ob.getHp() <= 0){
                            fightFrame.add(new JLabel("FIGHT COMPLETE"));
                        }
                    } else if (Objects.equals(choosenAction.getText(), "Heal")) {
                        p.heal();
                        playerHp.setText("Player HP : "+p.getHp());
                    } else if (Objects.equals(choosenAction.getText(), "Heal mana")) {
                        p.healMana();
                    }else {
                        JFrame error = new JFrame("ERROR");
                        error.setSize(100,100);
                        error.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        error.getContentPane().setBackground(Color.GRAY);
                        error.add(new JLabel("SORRY, WRONG ENTRY"));
                    }
                }
            });

            fightFrame.setVisible(true);
    }
}
