/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

<<<<<<< HEAD
import java.util.Properties;
import java.util.Set;
import com.google.gson.*;
import dtos.PersonDTO;
=======
import dtos.MovieDTO;
import dtos.RenameMeDTO;
import java.util.Properties;
import java.util.Set;
import com.google.gson.*;
import entities.Movie;
>>>>>>> d1dc78caf6e152eee2a33021df97f71d8889899c

import java.io.UnsupportedEncodingException;

/**
 *
 * @author tha
 */
public class Utility {
    private static Gson gson = new GsonBuilder().create();
    
    public static void printAllProperties() {
            Properties prop = System.getProperties();
            Set<Object> keySet = prop.keySet();
            for (Object obj : keySet) {
                    System.out.println("System Property: {" 
                                    + obj.toString() + "," 
                                    + System.getProperty(obj.toString()) + "}");
            }
    }
    
<<<<<<< HEAD
    public static PersonDTO json2DTO(String json) throws UnsupportedEncodingException{
            return gson.fromJson(new String(json.getBytes("UTF8")), PersonDTO.class);
    }
    
    public static String DTO2json(PersonDTO personDTO){
        return gson.toJson(personDTO, PersonDTO.class);
=======
    public static MovieDTO json2DTO(String json) throws UnsupportedEncodingException{
            return gson.fromJson(new String(json.getBytes("UTF8")), MovieDTO.class);
    }
    
    public static String DTO2json(MovieDTO movieDTO){
        return gson.toJson(movieDTO, MovieDTO.class);
>>>>>>> d1dc78caf6e152eee2a33021df97f71d8889899c
    }
    
    public static void main(String[] args) throws UnsupportedEncodingException {
//        printAllProperties();
        
        //Test json2DTO and back again
        String str2 = "{'id':1, 'str1':'Dette er den første tekst', 'str2':'Her er den ANDEN'}";
<<<<<<< HEAD
        PersonDTO pDTO = json2DTO(str2);
        System.out.println(pDTO);
        
        String backAgain = DTO2json(pDTO);
=======
        MovieDTO movieDTO = json2DTO(str2);
        System.out.println(movieDTO);
        
        String backAgain = DTO2json(movieDTO);
>>>>>>> d1dc78caf6e152eee2a33021df97f71d8889899c
        System.out.println(backAgain);
    }

}
