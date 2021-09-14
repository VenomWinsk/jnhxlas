package com.hxht.logprocess.buttonmanage.service;

import com.hxht.logprocess.buttonmanage.dao.ButtonMapper;
import com.hxht.logprocess.buttonmanage.model.Button;
import com.hxht.logprocess.buttonmanage.model.ButtonExample;
import com.hxht.logprocess.menumanage.dao.MenuMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ButtonService {

    @Resource
    private ButtonMapper buttonMapper;


    /**
     * 添加按钮
     *
     * @param button 按钮表
     * @return int
     */
    public int addButton(Button button) {
        return buttonMapper.insertSelective(button);
    }

    /**
     * 根据按钮id更新按钮信息
     *
     * @param button 按钮表
     * @return int
     */
    public int updateButtonByButtonId(Button button) {
        return buttonMapper.updateByPrimaryKeySelective(button);
    }

    /**
     * 根据按钮id更新按钮信息
     *
     * @param id 按钮表
     * @return int
     */
    public Button getButtonByButtonId(String id) {
        return buttonMapper.selectByPrimaryKey(id);
    }

    /**
     * 删除按钮
     *
     * @param buttonId id
     * @return int
     */
    public int deleteByButtonId(String buttonId) {
        return buttonMapper.deleteByPrimaryKey(buttonId);
    }


    /**
     * 根据按钮
     *
     * @return int
     */
    public long selectButtonCount(Button button) {
        ButtonExample buttonExample = new ButtonExample();
        ButtonExample.Criteria criteria = buttonExample.createCriteria();
        if (StringUtils.isNotBlank(button.getBtnName())) {
            criteria.andBtnNameEqualTo(button.getBtnName());
        }
        return buttonMapper.countByExample(buttonExample);
    }

    /**
     * 获取按钮列表
     *
     * @param button 按钮信息
     * @param page 起始页
     * @param size 分页大小
     * @return List<menu>
     */
    public List<Button> getButtonList(Button button, int page, int size) {
        ButtonExample menuExample = new ButtonExample();
        ButtonExample.Criteria criteria = menuExample.createCriteria();
        if (StringUtils.isNotBlank(button.getId())) {
            criteria.andIdEqualTo(button.getId());
        }
        if (StringUtils.isNotBlank(button.getBtnName())) {
            criteria.andBtnNameEqualTo(button.getBtnName());
        }
        int from = page - 1;
        int pageSize = from * size;
        menuExample.setOrderByClause(" create_time desc   limit " + from + " , +" + pageSize + " ");//根据某一字段进行排序,并分页
        return buttonMapper.selectByExample(menuExample);
    }
}
