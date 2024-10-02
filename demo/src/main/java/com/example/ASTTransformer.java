package com.example;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.serialization.JavaParserJsonSerializer;
import jakarta.json.Json;
import jakarta.json.stream.JsonGenerator;
import java.io.StringWriter;

public class ASTTransformer {

    public static String transform(String sourceCode) {
        try {
            // ソースコードをパースしてCompilationUnitを生成
            CompilationUnit cu = StaticJavaParser.parse(sourceCode);

            // JSONシリアライズのための準備
            StringWriter writer = new StringWriter();
            JsonGenerator jsonGenerator = Json.createGenerator(writer);
            JavaParserJsonSerializer serializer = new JavaParserJsonSerializer();

            // ASTをJSONにシリアライズ
            serializer.serialize(cu, jsonGenerator);
            jsonGenerator.close();

            // 生成されたJSONを文字列として返す
            return writer.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
