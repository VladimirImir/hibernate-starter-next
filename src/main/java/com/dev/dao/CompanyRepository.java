package com.dev.dao;

import com.dev.entity.Company;
import org.hibernate.SessionFactory;


public class CompanyRepository extends RepositoryBase<Integer, Company> {

    public CompanyRepository(SessionFactory sessionFactory) {
        super(Company.class, sessionFactory);
    }

}
