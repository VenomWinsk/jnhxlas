package com.hxht.logprocess.logbulk.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.core.http.model.ResQueryMessage;
import com.hxht.logprocess.logbulk.dao.FileRecordsMapper;
import com.hxht.logprocess.logbulk.model.DirRecords;
import com.hxht.logprocess.logbulk.model.FileRecords;
import com.hxht.logprocess.logbulk.model.FileRecordsVO;
import com.hxht.logprocess.logbulk.service.FileRecordsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/server/fileRecords")
public class FileRecordsController {


    @Autowired
    private FileRecordsService fileRecordsService;


    /**
     * 修改文件
     */
    @RequestMapping("/updateFileRecords")
    public ResMessage updateFileRecords(@RequestBody FileRecords fileRecords) {

        int n = fileRecordsService.updateFileRecords(fileRecords);
        if (n > 0) {
            return ResMessage.genSucessMessage("修改成功", n);
        } else {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "修改失败", n);
        }
    }

    /**
     * 查询文件
     */
    @RequestMapping("/selectFileRecords")
    public ResMessage selectFileRecords(String fileId,Integer page,Integer size) {

        List<Map<String, Object>> n = fileRecordsService.selectFilesById(fileId,page,size);
        Long count =fileRecordsService.selectCountById(fileId,page,size);
        if (n.size() > 0) {
            return ResQueryMessage.genSucessMessage("查询成功", n,n.size(),count);
        } else {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "修改失败", n);
        }
    }

    /**
     * 分页查询
     */
    @PostMapping("/selectFilesByPage")
    public ResMessage selectFilesByPage(@RequestParam("dirId") String dirId,@RequestParam("pageNum")  Integer pageNum,@RequestParam("size") Integer size) {
        List<FileRecords> fileRecords = fileRecordsService.selectFilesByDirId(dirId,pageNum,size);
        PageInfo<FileRecords> page=new PageInfo<>(fileRecords);
        return ResQueryMessage.genSucessMessage("查询成功", page);
    }
}
