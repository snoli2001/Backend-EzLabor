package com.acme.ezlabor.accounts.domain.repository;

import com.acme.ezlabor.accounts.domain.model.Freelancer;
import com.acme.ezlabor.accounts.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreelancerRepository extends JpaRepository<Freelancer, Long> {
    Freelancer findByFirstname(String firstname);
    Freelancer findByLastname(String lastname);
    Freelancer findByProfession(String profession);
    Freelancer findByUsername(String username);
}
