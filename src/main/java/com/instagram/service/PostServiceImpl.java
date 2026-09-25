package com.instagram.service;

import com.instagram.dao.PostDAO;
import com.instagram.dao.PostDAOImpl;
import com.instagram.model.Post;
import java.util.List;

public class PostServiceImpl implements PostService {

    private PostDAO postDAO;

    public PostServiceImpl() {
        this.postDAO = new PostDAOImpl();
    }

    @Override
    public boolean createPost(Post post) {
        // TODO: Add validation and call PostDAO
        return postDAO.addPost(post);
    }

    @Override
    public Post getPostById(int postId) {
        // TODO: Call PostDAO
        return postDAO.getPostById(postId);
    }

    @Override
    public List<Post> getPostsByUserId(int userId) {
        // TODO: Call PostDAO
        return postDAO.getPostsByUserId(userId);
    }

    @Override
    public List<Post> getAllPosts() {
        // TODO: Call PostDAO
        return postDAO.getAllPosts();
    }

    @Override
    public boolean updatePost(Post post) {
        // TODO: Add validation and call PostDAO
        return postDAO.updatePost(post);
    }

    @Override
    public boolean deletePost(int postId) {
        // TODO: Call PostDAO
        return postDAO.deletePost(postId);
    }
}