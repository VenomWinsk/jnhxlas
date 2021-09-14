package com.hxht.logprocess.setting.controller;

import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.core.http.model.ResQueryMessage;
import com.hxht.logprocess.setting.model.Dictionary;
import com.hxht.logprocess.setting.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/server/dictionary")
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 添加字典
     */
    @RequestMapping("addDictionary")
    public ResMessage addDictionary(@RequestBody Dictionary dictionary) {
        try {
            if (isContains(dictionary)) {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.NameError, "字典KEY重复", dictionary.getDictKey());
            }
            int n = dictionaryService.addDictionary(dictionary);
            if (n > 0) {
                return ResMessage.genSucessMessage("添加成功", n);
            } else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "添加失败", n);
            }
        } catch (Exception e) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError, "添加异常", e.getMessage());
        }
    }


    /**
     * 修改字典
     */
    @RequestMapping("updateDictionary")
    public ResMessage updateDictionary(@RequestBody Dictionary dictionary) {
        try {
            if (StringUtils.isBlank(dictionary.getId())) {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.NameError, "字典ID为空", null);
            }
            //判断是否有重复值
            if (isContains(dictionary)) {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.NameError, "字典KEY重复", dictionary.getDictKey());
            }
            int n = dictionaryService.updateDictionary(dictionary);
            if (n > 0) {
                return ResMessage.genSucessMessage("修改成功", n);
            } else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "修改失败", n);
            }
        } catch (Exception e) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError, "修改异常", e.getMessage());
        }
    }

    /**
     * 删除字典
     */
    @DeleteMapping("deleteDictionary")
    public ResMessage deleteDictionary(@RequestParam String id) {
        try {
            //todo  启用状态  启用后不可删除    删除--伪删除
            Dictionary dictionary = dictionaryService.selectById(id);
            if(dictionary.getIsEnabled()==1){
                return ResMessage.genErrorMessage(ResMessage.CodeDict.ServiceError, "启用状态的不可删除", null);
            }
            int n = dictionaryService.deleteDictionary(id);
            if (n > 0) {
                return ResMessage.genSucessMessage("删除成功", n);
            } else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "删除失败", null);
            }
        } catch (Exception e) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError, "删除异常", e.getMessage());
        }
    }

    /**
     * 查询字典
     */
    @GetMapping("getDictionary")
    public ResMessage getDictionary(@RequestParam int from, @RequestParam int size) {
        try {
            long count = dictionaryService.countByExample(new Dictionary());
            if (from <= 0) {
                from = 1;
            }
            List list = dictionaryService.selectDictionaryByExample(new Dictionary(), true, from - 1, size);
            if (list.size() > 0) {
                return ResQueryMessage.genSucessMessage("查询成功", list, list, size, count);
            } else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "查询数据为空", list);
            }
        } catch (Exception e) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError, "查询异常", e.getMessage());
        }
    }

    /**
     * 查询字典
     */
    @GetMapping("getDictionaryByParam")
    public ResMessage getDictionaryByParam(@RequestBody Dictionary dictionary, @RequestParam int from, @RequestParam int size) {
        try {
            List list = dictionaryService.selectDictionaryByExample(dictionary, true, from - 1, size);
            if (list.size() > 0) {
                return ResMessage.genSucessMessage("查询成功", list);
            } else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "查询数据为空", list);
            }
        } catch (Exception e) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError, "查询异常", e.getMessage());
        }
    }

    private boolean isContains(Dictionary dictionary) {
        if (StringUtils.isNotBlank(dictionary.getDictKey())) {
            long num = dictionaryService.countByExample(dictionary);
            return num > 0;
        } else {
            return false;
        }
    }
}
