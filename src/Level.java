import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Level {
    private final int GUARD_WIDTH = 60;
    private final int GUARD_HEIGHT = 60;

    private final int TREE_WIDTH = 70;
    private final int TREE_HEIGHT = 70;

    private final int BASKET_WIDTH = 30;
    private final int BASKET_HEIGHT = 30;

    private final int ROCK_WIDTH = 80;
    private final int ROCK_HEIGHT = 80;

    private int lvl;

    ArrayList<Tree> trees;
    ArrayList<Guard> guards;
    ArrayList<Basket> baskets;
    ArrayList<Rock> rocks;
    ArrayList<Heart> hearts;

    public Level(String levelPath) throws IOException {
        loadLevel(levelPath);
    }

    public void loadLevel(String levelPath) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(levelPath));
        trees = new ArrayList<>();
        baskets = new ArrayList<>();
        guards = new ArrayList<>();
        rocks = new ArrayList<>();
        hearts = new ArrayList<>();
        int y = 0;
        String line;
        while ((line = br.readLine()) != null) {
            int x = 0;
            for (int i = 0; i < 16; i++) {
                if (line.charAt(i) == '1') {
                    Image guardimage = new ImageIcon("src/guard.png").getImage();
                    guards.add(new Guard(i * 50, y * 50, GUARD_WIDTH, GUARD_HEIGHT, guardimage));
                }
                if (line.charAt(i) == '2') {
                    Image treeimage = new ImageIcon("src/tree.png").getImage();
                    trees.add(new Tree(i * 50, y * 50, TREE_WIDTH, TREE_HEIGHT, treeimage));

                }
                if (line.charAt(i) == '3') {
                    Image basketimage = new ImageIcon("src/basketfinal.png").getImage();
                    baskets.add(new Basket(i * 50 - 10, y * 50 - 10, BASKET_WIDTH, BASKET_HEIGHT, basketimage));
                }
                if (line.charAt(i) == '4') {
                    Image rockimage = new ImageIcon("src/rock.png").getImage();
                    rocks.add(new Rock(i * 50 - 10, y * 50 - 10, ROCK_WIDTH, ROCK_HEIGHT, rockimage));
                }
                if(line.charAt(i) == '5'){
                    Image heartimage = new ImageIcon("src/heartfinal.png").getImage();
                    hearts.add(new Heart(i*50,y*50,20,20,heartimage));

                }
            }
            y++;
        }

    }

    public boolean collidesWithBasket(Bear bear) {
        Basket collidedWithBasket = null;
        for (Basket basket : baskets) {
            if (bear.collides(basket)) {
                collidedWithBasket = basket;
                break;
            }
        }
        if (collidedWithBasket != null) {
            baskets.remove(collidedWithBasket);
            return true;
        }
        return false;
    }

    public boolean collidesWithGuard(Bear bear){
        Guard collidedWithGuard = null;
        for(Guard guard : guards){
            if(bear.collides2(guard)){
                collidedWithGuard = guard;
                break;
            }
        }
        if(collidedWithGuard != null){
            return true;
        }
        return false;
    }

    public boolean collidedWithObject(Bear bear){
        Tree collidedWithTree = null;
        Rock collidedWithRock = null;
        for(Tree tree : trees){
            if(bear.collides(tree)){
                collidedWithTree = tree;
                break;
            }
        }
        for(Rock rock: rocks){
            if(bear.collides(rock)){
                collidedWithRock = rock;
            }
        }
        if(collidedWithTree != null || collidedWithRock != null){
            return true;
        }
        return false;

    }

    public boolean isOver() {
        return baskets.isEmpty();
    }
    public void draw(Graphics g) {
        for (Basket basket : baskets) {
            basket.draw(g);
        }
    }
    public int getlvl(){
        return this.lvl;
    }
    public ArrayList<Basket> getBaskets(){
        return baskets;
    }
    public void setBaskets(ArrayList<Basket> baskets) { this.baskets = baskets; }
    public ArrayList<Guard> getGuards(){
        return guards;
    }
    public ArrayList<Tree> getTrees(){
        return trees;
    }
    public ArrayList<Rock> getRocks(){
        return rocks;
    }
    public ArrayList<Heart> getHearts(){
        return hearts;
    }

}