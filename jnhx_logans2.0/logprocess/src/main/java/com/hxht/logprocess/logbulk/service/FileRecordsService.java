package com.hxht.logprocess.logbulk.service;


import com.github.pagehelper.PageHelper;
import com.hxht.logprocess.logbulk.dao.DirRecordsMapper;
import com.hxht.logprocess.logbulk.dao.FileRecordsMapper;
import com.hxht.logprocess.logbulk.model.DirRecords;
import com.hxht.logprocess.logbulk.model.FileRecords;
import com.hxht.logprocess.logbulk.model.FileRecordsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FileRecordsService {


    @Autowired
    private FileRecordsMapper fileRecordsMapper;

    @Autowired
    private DirRecordsMapper dirRecordsMapper;


    /**
     * 修改文件
     */
    public int updateFileRecords(FileRecords fileRecords) {
        //在修改文件状态之后要修改对应文件夹的跳过总量，如果是跳过文件就加一，如果是恢复文件就减一
        FileRecords fileRecords1 = fileRecordsMapper.selectByPrimaryKey(fileRecords.getId());
        DirRecords dirRecords = dirRecordsMapper.selectByPrimaryKey(fileRecords1.getFiledirId());
        int total = dirRecords.getJumpnums();
        if (fileRecords.getRunStatus() == -4) {
            dirRecords.setJumpnums(total + 1);
            dirRecordsMapper.updateByPrimaryKeySelective(dirRecords);

        } else if (fileRecords.getRunStatus() == 1) {
            dirRecords.setJumpnums(total - 1);
            dirRecordsMapper.updateByPrimaryKeySelective(dirRecords);
        }
        int n = fileRecordsMapper.updateByPrimaryKeySelective(fileRecords);
        return n;

    }

    public List<Map<String, Object>> selectFilesById(String id, int from, int size) {
        int page = (from - 1) * size;
        return fileRecordsMapper.selectByFileId(id, page, size);
    }

    public Long selectCountById(String id, int from, int size) {
        int page = (from - 1) * size;
        return fileRecordsMapper.selectCountByFileId(id, page, size);
    }

    public List<FileRecords> selectFilesByDirId(String dirId,Integer pageNum,Integer size){
        FileRecordsExample example = new FileRecordsExample();
        FileRecordsExample.Criteria criteria = example.createCriteria();
        FileRecordsExample.Criteria criteria1 = criteria.andFiledirIdEqualTo(dirId);
        PageHelper.startPage(pageNum,size);
        List<FileRecords> fileRecords = this.fileRecordsMapper.selectByExample(example);
        fileRecords.stream().forEach(fileRecord->fileRecord.setType("fileRecords"));
        return fileRecords;
    }

}
