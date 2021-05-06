package poly.persistance.mapper;

import java.util.List;

import config.Mapper;
import poly.dto.BoardDTO;

@Mapper("BoardMapper")
public interface IBoardMapper {

	int insertPost(BoardDTO pDTO) throws Exception;

	List<BoardDTO> getBoardList() throws Exception;

	BoardDTO getBoardDetail(BoardDTO pDTO) throws Exception;

	int updatePost(BoardDTO pDTO) throws Exception;

	int deletePost(BoardDTO pDTO) throws Exception;

}
