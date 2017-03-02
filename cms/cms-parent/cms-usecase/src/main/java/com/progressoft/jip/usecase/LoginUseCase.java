package com.progressoft.jip.usecase;

import com.progressoft.jip.entity.UserEntity;
import com.progressoft.jip.entity.view.UserView;

public interface LoginUseCase {
	UserView findByUserNameAndPassword(UserView userView);
	UserEntity login(String userName, String password);

}
