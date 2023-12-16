package org.soft.base.dao.message;

import org.soft.base.model.Human;

public interface HumanDao {
    public boolean humanRegisterMapper(Human human);

    public Human humanLoginMapper(Human human);

    public boolean humanUpdateMapper(Human human);

    public Human humanById(int humanId);
}
