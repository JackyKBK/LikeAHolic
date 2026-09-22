package org.champsoft.likeaholic.dataMapperLayer;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.champsoft.likeaholic.dataAccessLayer.Post;
import org.champsoft.likeaholic.presentationLayer.PostRequestModel;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-21T18:42:34-0400",
    comments = "version: 1.6.0, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class PostRequestMapperImpl implements PostRequestMapper {

    @Override
    public Post requestModelToEntity(PostRequestModel postRequestModel) {
        if ( postRequestModel == null ) {
            return null;
        }

        Post post = new Post();

        post.setPostId( postRequestModel.getPostId() );
        post.setTitle( postRequestModel.getTitle() );
        post.setContent( postRequestModel.getContent() );
        post.setImageUrl( postRequestModel.getImageUrl() );
        post.setCreatedAt( postRequestModel.getCreatedAt() );
        post.setUserId( postRequestModel.getUserId() );

        return post;
    }

    @Override
    public List<Post> requestModelListToEntity(List<PostRequestModel> postRequestModel) {
        if ( postRequestModel == null ) {
            return null;
        }

        List<Post> list = new ArrayList<Post>( postRequestModel.size() );
        for ( PostRequestModel postRequestModel1 : postRequestModel ) {
            list.add( requestModelToEntity( postRequestModel1 ) );
        }

        return list;
    }
}
