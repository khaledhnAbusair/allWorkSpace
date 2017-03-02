package com.progressoft.jip.usecase;

import com.progressoft.jip.entity.UserEntity;
import com.progressoft.jip.entity.view.UserView;

public interface UserUseCase {

	public void addUser(UserView userView);

	public void editUserFullName(UserView userView);

	public void disableUser(UserView userView);

	public void resetUserPassword(UserView userView);

	public void deleteUser(UserView userView);

	public Iterable<UserEntity> getAllUsers();

	UserView findOneUserByUserName(String userName);

	void enableUser(UserView userView);

	void changeUserPassword(String userName, String password, String newPassword, String confirmPassword);

}
