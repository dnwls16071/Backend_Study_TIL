package com.example.younghanapi1.repository;

import com.example.younghanapi1.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

	@PersistenceContext
	EntityManager em;

	public void save(Member member) {
		em.persist(member);
	}

	public Member findByOne(Long id) {
		return em.find(Member.class, id);
	}

	public List<Member> findAll() {
		return em.createQuery("SELECT m FROM Member m", Member.class).getResultList();
	}

	public List<Member> findByName(String name) {
		return em.createQuery("SELECT m FROM Member m WHERE m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
	}
}
