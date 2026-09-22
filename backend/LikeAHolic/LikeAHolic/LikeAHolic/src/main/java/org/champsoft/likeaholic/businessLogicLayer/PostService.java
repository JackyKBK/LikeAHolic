package org.champsoft.likeaholic.businessLogicLayer;

import org.champsoft.likeaholic.presentationLayer.PostRequestModel;
import org.champsoft.likeaholic.presentationLayer.PostResponseModel;

import java.util.List;

public interface PostService {

    List<PostResponseModel> getAllPosts();


    PostResponseModel getPostByPostId(String post_id);
    String updatePost(String post_id, PostRequestModel PostRequestModel);
    String deletePost(String post_id);
String addPost(PostRequestModel PostRequestModel);

}
