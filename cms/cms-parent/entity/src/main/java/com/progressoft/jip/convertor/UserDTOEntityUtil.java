package com.progressoft.jip.convertor;

import com.progressoft.jip.datastracture.User;
import com.progressoft.jip.entity.UserEntity;
import com.progressoft.jip.entity.view.UserView;

public final class UserDTOEntityUtil {

	private UserDTOEntityUtil() {
	}

	public static User toDTO(UserView userEntity) {
		User userDto = new User();
		userDto.setFullName(userEntity.getFullName());
		userDto.setIsDisable(userEntity.getIsDisable());
		userDto.setUserName(userEntity.getUserName());
		return userDto;
	}

	public static UserView toEntity(User userDto) {
		UserEntity userEntity = new UserEntity();
		userEntity.setFullName(userDto.getFullName());
		userEntity.setIsDisable(userDto.getIsDisable());
		userEntity.setUserName(userDto.getUserName());
		return userEntity;
	}

}
