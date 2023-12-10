package com.example.controller;

import com.example.service.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.stream.Stream;

import com.example.dto.ResponseMessage;
import com.example.model.FileInfo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/dummyApi")
public class FileController {

    @Autowired
    FileService fileService;

    @PostMapping("/upload")
    public Mono<ResponseEntity<ResponseMessage>> uploadFile(@RequestPart("file") Mono<FilePart> filePartMono) {
        return fileService.save(filePartMono).map(
                (filename) -> ResponseEntity.ok().body(new ResponseMessage("Uploaded the file successfully: " + filename)));
    }

    @GetMapping("/files")
    public ResponseEntity<Flux<FileInfo>> getListFiles() {
        Stream<FileInfo> fileInfoStream = fileService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = UriComponentsBuilder.newInstance().path("/files/{filename}").buildAndExpand(filename).toUriString();
            return new FileInfo(filename, url);
        });

        Flux<FileInfo> fileInfosFlux = Flux.fromStream(fileInfoStream);

        return ResponseEntity.status(HttpStatus.OK).body(fileInfosFlux);
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Flux<DataBuffer>> getFile(@PathVariable String filename) {
        Flux<DataBuffer> file = fileService.load(filename);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM).body(file);
    }

    @GetMapping("/test")
    public Mono<Map<String, Object>> getConfig() {
        return fileService.getConfig();
    }

}
