package com.example;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import io.github.cdimascio.dotenv.Dotenv;

public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String dir = dotenv.get("dir");
        Path path = Paths.get(dir + "Main.java");
        try {
			String content = Files.readString(path);
            String ast = ASTTransformer.transform(content);
            System.out.println(ast);
            
		} catch(IOException ex) {
			ex.printStackTrace();
		}
    }
}