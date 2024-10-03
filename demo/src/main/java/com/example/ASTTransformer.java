package com.example;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.serialization.JavaParserJsonSerializer;
import jakarta.json.Json;
import jakarta.json.stream.JsonGenerator;
import java.io.StringWriter;


public class ASTTransformer {


    public static String convertToJson(String javaSource ) {
        try {
            // ソースコードをパースしてCompilationUnitを生成
            CompilationUnit cu = StaticJavaParser.parse(javaSource);

            // JSONシリアライズのための準備
            StringWriter writer = new StringWriter();
            JsonGenerator jsonGenerator = Json.createGenerator(writer);

            // jsonを整形する時はこっちを使う
            // Map<String, Object> config = new HashMap<>();
            // config.put(JsonGenerator.PRETTY_PRINTING, true);
            // JsonGenerator jsonGenerator = Json.createGeneratorFactory(config).createGenerator(writer);

            
            // JSONにシリアライズ
            JavaParserJsonSerializer serializer = new JavaParserJsonSerializer();
            serializer.serialize(cu, jsonGenerator);
            jsonGenerator.close();

            // 生成されたJSONを文字列として返す
            return writer.toString();

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
