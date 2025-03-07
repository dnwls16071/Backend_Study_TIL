package com.jwj.querydsl_2nd.repository;

import com.jwj.querydsl_2nd.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom, QuerydslPredicateExecutor<Member> {

	List<Member> findByUsername(String username);
}
