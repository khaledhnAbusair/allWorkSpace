/**
 * 
 */
package com.progressoft.jip.social.messaging;

import com.progressoft.jip.social.Post;
import com.progressoft.jip.social.User;
import com.progressoft.jip.social.repository.PostRepository;
import com.progressoft.jip.social.repository.UserRepository;

/**
 * @author PSLPT 147
 *
 */
public class PostMessageHandlerImpl implements PostMessageHandler {

	private UserRepository userRepository;
	private PostRepository postRepository;

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void setPostRepository(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public void processMessage(PostMessage postMessage) {
		String userEmail = postMessage.getEmail();
		User user = userRepository.findOneByEmail(userEmail);
		if (user == null)
			throw new UserNotFoundException("No user with email: " + userEmail);
		Post post = new Post();
		post.setUser(user);
		post.setContent(postMessage.getContent());

		postRepository.save(post);
	}
}
