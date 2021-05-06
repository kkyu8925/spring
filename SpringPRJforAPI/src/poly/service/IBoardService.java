package poly.service;

import java.util.List;

import poly.dto.BoardDTO;

public interface IBoardService {

	int insertPost(BoardDTO pDTO) throws Exception;

	List<BoardDTO> getBoardList() throws Exception;

	BoardDTO getBoardDetail(BoardDTO pDTO) throws Exception;

	int updatePost(BoardDTO pDTO) throws Exception;

	int deletePost(BoardDTO pDTO) throws Exception;

}
