package poly.persistance.mapper;

import java.util.List;

import config.Mapper;
import poly.dto.UserDTO;

@Mapper("UserMapper")
public interface IUserMapper {

	UserDTO getUserInfo(UserDTO pDTO) throws Exception;

	List<UserDTO> getUserList(UserDTO uDTO);

}
