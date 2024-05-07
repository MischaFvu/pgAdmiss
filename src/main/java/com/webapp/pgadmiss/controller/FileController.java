package com.webapp.pgadmiss.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.webapp.pgadmiss.constant.ErrorResponse;
import com.webapp.pgadmiss.dto.ResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Properties;
import java.util.UUID;

import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@Slf4j
@RequestMapping("/file")
public class FileController {

    @Operation(summary = "Upload file", description = "Rest API to upload files.")
    // @ApiResponses({
    //     @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
    //     @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error",
    //             content = @Content(schema = @Schema(implementation = ResponseDto.class))
    //     )
    // })
    @GetMapping("/upload") 
    public ResponseEntity<ResponseDto> uploadFile(String stuName, MultipartFile file) {
        ResponseDto respDto = new ResponseDto();
        respDto.setApiPath("/file/upload");
        // 1. Verify file
        respDto = verifyFile(file, respDto);
        if (null != respDto.getResultCode()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respDto);
        }
        // 2. Generate file name
        String orgFileName = file.getOriginalFilename();
        int index = orgFileName.lastIndexOf(".");
        String newFileName = UUID.randomUUID().toString() + orgFileName.substring(index);
        // 3. Upload file
        String filePath = getFilePath();
        if (filePath.isEmpty()) {
            respDto.setResultCode(ErrorResponse.CODE_NULL);
            respDto.setResultMsg(ErrorResponse.MSG_IS_EXIST);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respDto);
        }
        boolean isExist = readFile(newFileName, filePath);
        if (!isExist) {
            try {
                file.transferTo(new java.io.File(filePath + newFileName));
            } catch (IllegalStateException | IOException e) {
                respDto.setResultCode(ErrorResponse.CODE_INVALID_FORMAT);
                respDto.setResultMsg(ErrorResponse.MSG_INVALID_FORMAT + ": " + e.getMessage());
            }
        }else {
            respDto.setResultCode(ErrorResponse.CODE_IS_EXIST);
            respDto.setResultMsg(ErrorResponse.MSG_IS_EXIST);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respDto);
        }


        return ResponseEntity.status(HttpStatus.OK).body(respDto);
    }
    public ResponseDto verifyFile(MultipartFile file, ResponseDto respDto) {
        // Not null
        if (file.isEmpty()) {
            respDto.setResultCode(ErrorResponse.CODE_NULL);
            respDto.setResultMsg(ErrorResponse.MSG_NULL);
        }
        // Type limitation: PDF
        if (null != file.getContentType() || !file.getContentType().equals("application/pdf")) {
            respDto.setResultCode(ErrorResponse.CODE_INVALID_FORMAT);
            respDto.setResultMsg(ErrorResponse.MSG_INVALID_FORMAT + ": PDF");
        }
        // Size limitation: 10MB
        if (file.getSize() > 1024 * 1024 * 10) {
            respDto.setResultCode(ErrorResponse.CODE_INVALID_SIZE);
            respDto.setResultMsg(ErrorResponse.MSG_INVALID_SIZE + ": 10MB");
        }
        // Uniqueness check
        return respDto;
    }

    public ResponseDto saveFile(ResponseDto respDto, MultipartFile file, String newFileName) {

        return respDto;
    }
    
    private boolean readFile(String fileName, String filePath) {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(fileName, filePath);
            FileChannel fileChannel = raf.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int bytesRead = fileChannel.read(buffer);
            while (bytesRead != -1) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }
                buffer.clear();
                bytesRead = fileChannel.read(buffer);
            }
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }finally{
            try{
                if(null != raf){
                    raf.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return true;
	}
    
    private boolean writeFile(String fileName, String filePath) {
        RandomAccessFile aFile = null;
        try{
            aFile = new RandomAccessFile(fileName,"rw");
            FileChannel fileChannel = aFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            int bytesWrite = fileChannel.write(buf);
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }finally{
            try{
                if(aFile != null){
                    aFile.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return true;
	}
    public String getFilePath() {
        Properties prop = new Properties();
        try {
            InputStream input = PropertiesLoaderUtils.class.getResourceAsStream("/file.properties");
            // Load the properties file
            prop.load(input);
            if (null != prop && null != prop.getProperty("filepath")) {
                return prop.getProperty("filepath");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    
    }
}
