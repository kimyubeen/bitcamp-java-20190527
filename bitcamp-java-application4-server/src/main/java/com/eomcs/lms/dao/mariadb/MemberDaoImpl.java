package com.eomcs.lms.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.DataSource;

public class MemberDaoImpl implements MemberDao {

  SqlSessionFactory sqlSessionFactory;
  DataSource dataSource;

  public MemberDaoImpl( DataSource dataSource, SqlSessionFactory sqlSessionFactory) {
    this.dataSource = dataSource;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(Member member) throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      int count = sqlSession.insert("MemberDao.insert", member);
      sqlSession.commit();
      return count;

    } catch (Exception e) {
      sqlSession.rollback();
      throw e;

    } finally {
      sqlSession.close();
    }

  }

  @Override
  public List<Member> findAll() throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("MemberDao.findAll");
    }
  }

  @Override
  public Member findBy(int no) throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      Member member = sqlSession.selectOne("MemberDao.findBy", no);
      return member;

    } catch (Exception e) {
      throw e;

    } finally {
      sqlSession.close();
    }
  }

  @Override
  public List<Member> findByKeyword(String keyword) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("MemberDao.findByKeyword", keyword);
    }
  }

  @Override
  public int update(Member member) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
      return sqlSession.update("MemberDao.update", member);
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
      return sqlSession.delete("MemberDao.delete", no);
    }
  }

  @Override
  public Member findByEmailPassword(String email, String password) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      Member member = new Member();
      member.setEmail(email);
      member.setPassword(password);
      return sqlSession.selectOne("MemberDao.findByEmailPassword", member);
    }
  }


}
