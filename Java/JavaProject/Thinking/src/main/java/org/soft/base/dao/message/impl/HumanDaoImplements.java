package org.soft.base.dao.message.impl;

import org.soft.base.dao.message.HumanDao;
import org.soft.base.mapper.HumanMapper;
import org.soft.base.model.Human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("humanDao")
public class HumanDaoImplements implements HumanDao {

    @Autowired
    private HumanMapper humanMapper;
    @Override
    public boolean humanRegisterMapper(Human human) {
        boolean b = humanMapper.humanRegisterMapper(human);
        return b;
    }

    @Override
    public Human humanLoginMapper(Human human) {
        human = humanMapper.humanLoginMapper(human);
        return human;
    }

    @Override
    public boolean humanUpdateMapper(Human human) {
        boolean b = humanMapper.humanUpdateMapper(human);
        return b;
    }

    @Override
    public Human humanById(int humanId) {
        Human human = humanMapper.humanById(humanId);
        return human;
    }
}
