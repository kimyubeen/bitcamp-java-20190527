package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.DataSource;

public class BoardDaoImpl implements BoardDao {
  DataSource dataSource;

  public BoardDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public int insert(Board board) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "insert into lms_board(conts)"
                + " values(?)")) {

      stmt.setString(1, board.getContents());

      return stmt.executeUpdate();

    }

  }

  @Override
  public List<Board> findAll() throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "select *"
                + " from lms_board"
                + " order by board_id desc")) {

      try (ResultSet rs = stmt.executeQuery()) {

        ArrayList<Board> list = new ArrayList<>();

        while (rs.next()) {
          Board board = new Board();
          board.setNo(rs.getInt("board_id"));
          board.setContents(rs.getString("conts"));
          board.setCreatedDate(rs.getDate("cdt"));
          board.setViewCount(rs.getInt("vw_cnt"));

          list.add(board);
        }
        return list;
      }
    }
  }

  @Override
  public Board findBy(int no) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "select *"
                + " from lms_board"
                + " where board_id=?")) {

      stmt.setInt(1, no);

      try (ResultSet rs = stmt.executeQuery()) {

        Board board = new Board();
        if (rs.next()) {
          board.setNo(rs.getInt("board_id"));
          board.setContents(rs.getString("conts"));
          board.setCreatedDate(rs.getDate("cdt"));
          board.setViewCount(rs.getInt("vw_cnt"));

          // 게시글 찾았으면 조회수를 증가시킨다.
          stmt.executeUpdate("update lms_board set"
              + " vw_cnt=vw_cnt + 1 where board_id=" + no);

          return board;

        } else {
          return null;
        }
      }
    }
  }

  @Override
  public int update(Board board) throws Exception {

    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "update lms_board set"
                + " conts=?"
                + " where board_id=?")) {

      stmt.setString(1, board.getContents());
      stmt.setInt(2, board.getNo());

      return stmt.executeUpdate();

    }

  }

  @Override
  public int delete(int no) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "delete from lms_board where board_id=?")) {

      stmt.setInt(1, no);

      return stmt.executeUpdate();
    }

  }
}
