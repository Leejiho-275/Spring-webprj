package com.myapp.webprj.board.mapper;

import com.myapp.webprj.board.domain.Board;
import com.myapp.webprj.board.service.BoardService;
import com.myapp.webprj.common.Criteria;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// 자동주입을 받을 수 있음
@SpringBootTest
class BoardMapperTest {

    @Autowired
    private BoardMapper mapper;

    @Autowired
    private BoardService service;

    @Test
    @DisplayName("데이터베이스에서 게시글 전체를 조회할 수 있어야 한다.")
    void findAllTest() {

        // when
        List<Board> boardList = mapper.getList();

        // then
        assertTrue(boardList.size() == 5);

        System.out.println("============================================");
        for (Board board : boardList) {
            System.out.println(board);
        }
        System.out.println("===========================================");
    }

    @Test
    @DisplayName("데이터베이스 저장에 성공해야 한다.")
    void insertTest() {

        // given
        Board newArticle = new Board();
        newArticle.setTitle("아기공룡 둘리");
        newArticle.setContent("cartoon");
        newArticle.setWriter("고길동");

        // when
        mapper.save(newArticle);

        // then
        assertTrue(mapper.getList().get(0).getBno() == 6);
    }

    @Test
    @DisplayName("글내용, 글제목, 작성자를 수정할 수 있어야 한다")
    void updateTest() {

        Board newBoard = new Board();
        newBoard.setBno(5L);
        newBoard.setTitle("update title");
        newBoard.setContent("update content");
        newBoard.setWriter("update writer");

        int resultNum = mapper.update(newBoard);
        assertTrue(resultNum == 1);
        assertTrue(mapper.findByBno(5L).getWriter().equals("update writer"));
    }

    @Test
    @DisplayName("글 번호를 전달하면 해당 글 정보가 삭제되어야 한다.")
    void deleteTest() {

        mapper.delete(4L);

        assertNull(mapper.findByBno(4L));
    }

    @Test
    @DisplayName("테스트 게시물 300개를 추가해야 함")
    void bulkInsert() {
        for (int i = 0; i <= 300; i++) {
            Board board = new Board();
            board.setTitle("테스트제목" + i);
            board.setContent("테스트중");
            board.setWriter("USER" + i);

            mapper.save(board);
        }
    }

    @Test
    @DisplayName("페이지 정보대로 목록이 조회되어야 함")
    void pagingTest() {
        Criteria cri = new Criteria(2, 10);
        ;
        List<Board> list = mapper.getListWithPaging(cri);

        for (Board board : list) {
            System.out.println(board);
        }
    }

    @Test
    @DisplayName("총 게시물 수를 조회해야 함")
    void totalCountTest() {
        int totalCount = mapper.getTotalCount();

        System.out.println("totalCount = " + totalCount);

        assertTrue(totalCount == 309);
    }

    @Test
    @DisplayName("제목으로 검색 수행")
    void searchByTitleTest() {
        Criteria cri = new Criteria();
        cri.setKeyword("28");

        List<Board> list = mapper.getListByTitle(cri);
        for (Board board : list) {
            System.out.println(board);

        }
    }

    @Test
    @DisplayName("파일 첨부 기능 테스트")
    void addFileTest() {
        Board board = new Board();
        board.setWriter("테스트쟁이");
        board.setTitle("메롱");
        board.setContent("fdsfdsfdsfds");
        board.setFileNames(Arrays.asList("dog.jpg", "cat.jpg"));

        service.register(board);
    }
}