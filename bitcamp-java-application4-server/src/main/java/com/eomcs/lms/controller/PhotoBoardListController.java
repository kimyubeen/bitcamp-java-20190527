package com.eomcs.lms.controller;

import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.domain.PhotoBoard;

@Component("/photoboard/list")
public class PhotoBoardListController implements PageController {

  @Resource
  private PhotoBoardDao photoBoardDao;

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {

    List<PhotoBoard> photoBoards = photoBoardDao.findAll();

    request.setAttribute("photoBoards", photoBoards);
    return "/jsp/photoboard/list.jsp";
  }
}
