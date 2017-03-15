package Model;

import Model.Obstacle;

public class ObstacleBack extends Obstacle{

    //highest point of shape
    private int height;
    private String name;
    private int length;
    private int depth;

    public ObstacleBack(String name, int height, int length, int depth) {
        super();
        this.name = name;
        this.height = height;
        this.length = length;
        this.depth = depth;

        sideViewX = new int[]{0,length,length};
        sideViewY = new int[]{0,0,height};
        topViewX = new int[]{0,length,length,0};
        topViewZ = new int[]{0,0,depth,depth};
    }

    public ObstacleBack(){
        this("", 0, 0, 0);
    }


    //returns max y coord
    public int getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public int getLength() { return length; }

    public int getDepth() { return depth; }
}
