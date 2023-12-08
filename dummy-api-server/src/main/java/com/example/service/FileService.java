package com.example.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RefreshScope
public class FileService {

    private final Path root = Paths.get("uploads");

    @Value("${service.name}")
    private String serviceName;

    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("업로드 할 수 있는 폴더를 초기화할 수 없습니다.");
        }
    }

    public Mono<String> save(Mono<FilePart> filePartMono) {

        return filePartMono.doOnNext(fp -> log.info("받은 파일:" + fp.filename())).flatMap(filePart -> {
            String filename = filePart.filename();
            return filePart.transferTo(root.resolve(filename)).then(Mono.just(filename));
        });
    }

    public Flux<DataBuffer> load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return DataBufferUtils.read(resource, new DefaultDataBufferFactory(), 4096);
            } else {
                throw new RuntimeException("파일을 읽을 수 없습니다.");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("에러 : " + e.getMessage());
        }
    }

    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1)
                    .filter(path -> !path.equals(this.root))
                    .map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("파일을 불러 올 수 없습니다.");
        }
    }

    public Mono<Map<String, Object>> getConfig() {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("serviceName", serviceName);

        return Mono.just(configMap);
    }

}
