package com.example;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import io.github.cdimascio.dotenv.Dotenv;

public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        
        
        try {
            // javaのソースコードを取得
            String source_dir = dotenv.get("JAVA_SOURCE_CODE_DIR");
            Path sourceFilePath = Paths.get(source_dir, "Main.java");
            String content = Files.readString(sourceFilePath);

            // ソースコードをjsonに変換
            String ast = ASTTransformer.convertToJson(content);

            // jsonをファイルに書き込む
            String output_json_dir = dotenv.get("OUTPUT_JSON_DIR");
            Path outPutPath = Paths.get(output_json_dir, "ast_output.json");
            Files.write(outPutPath, ast.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            // System.out.println(ASTTransformer.convertFromJson(Files.readString(outPutPath)));
            
		} catch(IOException ex) {
			ex.printStackTrace();
		}
    }
}