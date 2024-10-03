package com.example;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.serialization.JavaParserJsonDeserializer;
import com.github.javaparser.serialization.JavaParserJsonSerializer;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.stream.JsonGenerator;

import java.io.StringReader;
import java.io.StringWriter;

public class ASTTransformer {

    public static String convertToJson(String javaSource) {
        try {
            // ソースコードをパースしてCompilationUnitを生成
            CompilationUnit cu = StaticJavaParser.parse(javaSource);

            // JSONシリアライズのための準備
            StringWriter writer = new StringWriter();
            JsonGenerator jsonGenerator = Json.createGenerator(writer);

            // jsonを整形する時はこっちを使う
            // Map<String, Object> config = new HashMap<>();
            // config.put(JsonGenerator.PRETTY_PRINTING, true);
            // JsonGenerator jsonGenerator =
            // Json.createGeneratorFactory(config).createGenerator(writer);

            // JSONにシリアライズ
            JavaParserJsonSerializer serializer = new JavaParserJsonSerializer();
            serializer.serialize(cu, jsonGenerator);
            jsonGenerator.close();

            // 生成されたJSONを文字列として返す
            return writer.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static CompilationUnit convertFromJson(String json) {
        try (JsonReader jsonReader = Json.createReader(new StringReader(json))){
            JavaParserJsonDeserializer deserializer = new JavaParserJsonDeserializer();
            return (CompilationUnit)deserializer.deserializeObject(jsonReader);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
