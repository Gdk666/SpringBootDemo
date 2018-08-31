package Guoz.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @Data 9:11
 * @Version 1.0
 **/
@Controller
@RequestMapping("/uploads")
public class FileUpLoadController {
    private static final Logger log = LoggerFactory.getLogger(FileUpLoadController.class);

    @GetMapping
    public String index(){
        return "index";
    }
    @PostMapping("/upload1")
    @ResponseBody
    public Map<String,String> upload1(@RequestParam("file")MultipartFile file) throws IOException{
        log.info("[文件类型] - [{}]", file.getContentType());
        log.info("[文件名称] - [{}]", file.getOriginalFilename());
        log.info("[文件大小] - [{}]", file.getSize());
            file.transferTo(new File("E:\\uploadFile\\"+file.getOriginalFilename()));
            Map<String,String> map = new HashMap<>(16);
            map.put("contentType",file.getContentType());
            map.put("fileName",file.getOriginalFilename());
            map.put("fileSize",file.getSize()+"");
        return map;
    }
    @PostMapping("/upload2")
    @ResponseBody
    public List<Map<String,String>> upload2(@RequestParam("file") MultipartFile[] files) throws IOException{
              if (files == null || files.length == 0){
                  return null;
              }
              List<Map<String,String>> maps = new ArrayList<>();
              for (MultipartFile file : files){
                  file.transferTo(new File("E:\\uploadFile\\"+file.getOriginalFilename()));
                  Map<String,String> map = new HashMap<>(16);
                  map.put("contentType",file.getContentType());
                  map.put("fileName",file.getOriginalFilename());
                  map.put("fileSize",file.getSize()+"");
                  maps.add(map);
              }
              return maps;
    }

}
