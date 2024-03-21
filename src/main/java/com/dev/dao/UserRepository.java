package com.dev.dao;

import com.dev.entity.User;

import javax.persistence.EntityManager;

public class UserRepository extends RepositoryBase<Long, User> {

    public UserRepository(EntityManager entityManager) {
        super(User.class, entityManager);
    }
    // todo


}
