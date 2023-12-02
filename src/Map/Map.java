package Map;

import Fight.Fight;
import Obstacle.Monster.Boss.Boss;
import Obstacle.Monster.Monster;
import Obstacle.Monster.Slime.Slime;
import Obstacle.Obstacle;
import Obstacle.Stone.Stone;
import Player.Player;
import Store.Store;
import Weapon.Weapon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {

    static int[][] MAP_TEMPLATE_DEFAULT = {
            {2, 3, 3, 3, 1, 0},
            {0, 0, 1, 0, 3, 1},
            {1, 3, 6, 3, 3, 1},
            {1, 3, 3, 3, 6, 3},
            {1, 0, 3, 3, 0, 0},
            {1, 3, 3, 3, 6, 5},

    };
    private int[][] map;
    public Map(){this.map = MAP_TEMPLATE_DEFAULT; }

    public int[][] getMap(){return this.map;}


    public int[][]setMap(int[][] newMap){
        MAP_TEMPLATE_DEFAULT = newMap;
        return MAP_TEMPLATE_DEFAULT;
    }

    public JPanel displayMap(JPanel pan, Player p, Monster ob){
        Map curentMap = this;
        int count =0;
        GridLayout gameGrid = new GridLayout(0, 6);
        pan.setLayout(gameGrid);
        int[][] gridMap = map;
        for (int i = 0; i < gridMap.length; i++ ){
            count+=1;
            for (int j = 0; j < gridMap[i].length; j++){
                count+=1;
                JLabel pose = new JLabel(String.valueOf(gridMap[i][j]));
                int finalCount = count;
                pose.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        super.mouseClicked(e);

                        curentMap.cellType(p, ob, pose);
                        System.out.println(this);
                        pan.revalidate();
                    }
                });
                pan.add(pose);

            }
        }
        return pan;
    }

    private boolean isAdjacent(int row1, int col1, int row2, int col2) {
        return Math.abs(row1 - row2) <= 1 && Math.abs(col1 - col2) <= 1;
    }

    public void cellType(Player p, Monster bs, JLabel jLab){
        switch (Integer.parseInt(jLab.getText())){
            case 0:
                jLab.setText("2");
                break;
            case 1:
                JDialog error = new JDialog(new Frame(), "ERROR");
                error.add(new JLabel("YOU JUST HURTED A WALL, TRY ANOTHER POSITION"));
                error.setSize(500,100);
                error.setVisible(true);
                jLab.setText("1");
                break;
            case 2:
                JDialog errorPlayer = new JDialog(new Frame(), "YOU");
                errorPlayer.add(new JLabel("IT'S YOU, YOU CAN't MOVE HERE"));
                errorPlayer.setSize(500,100);
                errorPlayer.setVisible(true);
                break;
            case 3:
                Obstacle ob = new Stone();
                Fight f = new Fight(ob, p);
                f.destroy();
                p.battleGain();
                jLab.setText("2");
                break;
            case 5:
                JFrame endGame = new JFrame("END");
                endGame.add(new JLabel("CONGRATULATION YOU ARRIVED AT THE END"));
                endGame.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
                endGame.setSize(500,100);
                endGame.setVisible(true);
                break;
            case 6:
                String shopName = "Crous";
                int money = 999;
                JFrame shopFrame =  new JFrame(shopName);
                shopFrame.setSize(500, 500);
                shopFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                shopFrame.getContentPane().setBackground(Color.GRAY);

                GridLayout gameGrid = new GridLayout(0, 3);
                shopFrame.setLayout(gameGrid);

                JLabel weaponWanted;
                JTextField weaponPChoosen;
                JButton buy;

                ArrayList<Weapon> storeInventory = new ArrayList<Weapon>();
                Store sh = new Store(shopName, money);
                storeInventory = sh.getStoreInventory();
                for(int i = 0; i < storeInventory.size(); i++){
                    Weapon weap;
                    String weaponName;
                    weap = storeInventory.get(i);
                    weaponName = weap.getName();
                    shopFrame.add(new JLabel("Item " + i + " : " + weaponName));
                    shopFrame.add(new JLabel("Damage :"+ weap.getDamage()));
                    shopFrame.add(new JLabel("Price :"+weap.getPrice()));
                }


                weaponWanted = new JLabel("CHOOSE WHAT YOU WANT TO BUY PLEASE (just enter the number)");
                weaponWanted.setHorizontalAlignment(JLabel.CENTER);
                shopFrame.add(weaponWanted);

                weaponPChoosen = new JTextField();
                shopFrame.add(weaponPChoosen);

                buy = new JButton("BUY");
                shopFrame.add(buy);
                ArrayList<Weapon> finalStoreInventory = storeInventory;
                buy.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (weaponPChoosen.getText() != null){
                            p.buy(sh, finalStoreInventory.get(Integer.parseInt(weaponPChoosen.getText())));
                            shopFrame.dispose();
                        }else {
                            JDialog errorPlayer = new JDialog(new Frame(), "WEAPON ERROR");
                            errorPlayer.add(new JLabel("PLEASE ENTER A VALID NUMBER"));
                            errorPlayer.setSize(500,100);
                            errorPlayer.setVisible(true);
                        }
                    }
                });
                jLab.setText("2");
                shopFrame.setVisible(true);
                break;


        }
    }
}
