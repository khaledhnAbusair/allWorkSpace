package com.progressoft.jip.usecase.impl;

import java.util.Objects;

import com.progressoft.jip.entity.UserEntity;
import com.progressoft.jip.entity.view.UserView;
import com.progressoft.jip.repository.UserRepository;
import com.progressoft.jip.usecase.LoginUseCase;
import com.progressoft.jip.usecase.impl.exception.BadCredentialsException;
import com.progressoft.jip.usecase.impl.exception.UserAccountIsDisabledException;
import com.progressoft.jip.usecase.impl.exception.UserNotFoundException;

public class LoginUseCaseImple implements LoginUseCase {

	private UserRepository userRepository;

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserView findByUserNameAndPassword(UserView userView) {
		UserView findByUserName = userRepository.findByUserName(userView.getUserName());
		if (Objects.isNull(findByUserName))
			throw new UserNotFoundException();
		if (!userView.getPassword().equals(findByUserName.getPassword()))
			throw new BadCredentialsException();
		if (!findByUserName.getIsDisable())
			throw new UserAccountIsDisabledException();
		return findByUserName;
	}

	@Override
	public UserEntity login(String userName, String password) {
		UserView findByUserName = userRepository.findByUserName(userName);
		if (Objects.isNull(findByUserName))
			throw new UserNotFoundException();
		if (!findByUserName.getIsDisable())
			throw new UserAccountIsDisabledException();
		UserEntity login = userRepository.login(userName, password);
		if (Objects.isNull(login))
			throw new BadCredentialsException();
		return login;
	}

}
