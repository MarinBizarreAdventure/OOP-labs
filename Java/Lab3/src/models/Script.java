package models;

import utils.Utils;

import java.nio.file.Path;

public class Script extends Record{
    public Script(Path path){
        super(path);
    }
    @Override
    public String getInfo(){
        return super.getInfo()+ "\nLineCount:"+ Integer.toString(Utils.getLineCount(this))+ "\nClassCount:"
                +Integer.toString(Utils.getClassCount(this)) +"\nMethodCount:"
                + Integer.toString(Utils.getMethodCount(this)) ;
    }
}
