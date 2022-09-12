package com.packt.project.dao.impl;

import com.packt.project.dao.IDoctorDao;
import com.packt.project.entity.DoctorEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository("IDoctorDao")
@Transactional
@PersistenceContext(unitName = "PACKT.DB")
public class DoctorDaoImpl implements IDoctorDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<DoctorEntity> findByLocationAndSpeciality(String location, String speciality) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("findByLocationAndSpeciality");
        query.setParameter("location", location);
        query.setParameter("speciality", speciality);
        List<DoctorEntity> doctorEntityList = query.getResultList();
        return doctorEntityList;
    }
}
