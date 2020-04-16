package com.zetcode;

import java.util.stream.Stream;
import java.nio.file.*;
import java.io.*;
/**
 *  
 * Reading text file Buschtaben with Java Stream !
 * 
 * @author G.I.
 *
 */
public class ReadBuchstaben {

 public static void main(String[] args) {

    String filename = "images\\Buschtaben.txt";

    try(Stream<String> stream = Files.lines(Paths.get(filename))) {

          stream.forEach(System.out:: println);

     } catch (IOException e) {

        e.printStackTrace();
     }

   }

 }
