package com.mrg.onboarding.document.service;

import com.mrg.onboarding.document.Document;
import com.mrg.onboarding.document.DocumentHtmlDto;
import com.mrg.onboarding.utils.FileUtils;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class RenderService {

    @Value("${document.path}")
    private String documentPath;
    public Map<String, String> renderMarkDownBySections(Document document) throws IOException, URISyntaxException {
        Path path = Paths.get(documentPath + document.getUuid() + ".md");
        Stream<String> lines = Files.lines(path);
        Map<String, String> contentMap = new LinkedHashMap<>();
        StringBuilder bodyBuilder = new StringBuilder();
        StringBuilder headingBuilder = new StringBuilder();
        lines.forEach(line -> {
            if(line.startsWith("##")){
                headingBuilder.delete(0, headingBuilder.length());
                String heading = line.substring(3, line.length() - 1);
                headingBuilder.append(heading);
                contentMap.put(heading, null);
            }else{
                bodyBuilder.append("\n").append(line);
                contentMap.put(headingBuilder.toString(), bodyBuilder.toString());
            }
        });
        return contentMap;
    }

    public String renderMarkDownRaw(Document document) {
        return FileUtils.readMarkdownFile(documentPath + document.getUuid() + ".md");
    }

    public DocumentHtmlDto renderMarkDownHtml(Document document)  {
        String markdownContent = FileUtils.readMarkdownFile(documentPath + document.getUuid() + ".md");
        Parser parser = Parser.builder().build();
        Node doc = parser.parse(markdownContent);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String html = renderer.render(doc);
        return DocumentHtmlDto.builder().content(html).build();
    }

}
