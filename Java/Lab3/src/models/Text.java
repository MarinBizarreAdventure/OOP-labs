package models;

import utils.Utils;

import java.nio.file.Path;

public class Text extends Record{
    public Text(Path path){
        super(path);
    }

    @Override
    public String getInfo(){

        return super.getInfo()+ "\nLineCount:" +Integer.toString(Utils.getLineCount(this))+ "\nWordCount:"
                +Integer.toString(Utils.getWordCount(this)) +"\nCharacterCount:"
                + Integer.toString(Utils.getCharacterCount(this)) ;
    }
}
