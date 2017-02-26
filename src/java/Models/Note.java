/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Smeki
 */
public class Note {
    private int id;
    private String title;
    private String content;
    private String author;
    private String createType;

    public Note(int id,String title, String content, String createType, String author){
        this.id = id;
        this.title = title;
        this.content = content;
        this.createType = createType;
        this.author = author;
    }  

    public void setId(int id){
        this.id = id;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public void setContent(String content){
        this.content = content;
    }
    
    public void setcreateType(String createType){
        this.createType = createType;
    }
    
    public void setAuthor(String author){
        this.author = author;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }
    
    public String getcreateType(){
        return createType;
    }
    
    public String getAuthor(){
        return author;
    }
    
    public int getId(){
        return id;
    }
    
    public void Cry(int howMuch){
        for(int i = 0; i < howMuch; i++){
            System.out.println("I am crying :D");
        }
    }
    
    public void Smile(int howMuch){
        for(int i = 0; i < howMuch; i++){
            System.out.println("I am smiling :DD");
        }
    }
    
    /**
     * 
     * 
     * 
     * 
     * ThIS is only modification in this file for this commit
     */
    
    public void FirstModifInBranche(){
        System.out.println("This is first modification in branche ISSUE01");
    }
    
    public void SecondModInBranche(){
        System.out.println("This is second modification in branche ISSUE01");
    }
    
}
