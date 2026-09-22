package org.champsoft.likeaholic.dataMapperLayer;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.champsoft.likeaholic.dataAccessLayer.Post;
import org.champsoft.likeaholic.presentationLayer.PostResponseModel;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-21T18:42:34-0400",
    comments = "version: 1.6.0, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class PostResponseMapperImpl implements PostResponseMapper {

    @Override
    public PostResponseModel entityToResponseModel(Post post) {
        if ( post == null ) {
            return null;
        }

        PostResponseModel postResponseModel = new PostResponseModel();

        postResponseModel.setPostId( post.getPostId() );
        postResponseModel.setId( post.getId() );
        postResponseModel.setTitle( post.getTitle() );
        postResponseModel.setContent( post.getContent() );
        postResponseModel.setImageUrl( post.getImageUrl() );
        postResponseModel.setCreatedAt( post.getCreatedAt() );
        postResponseModel.setUserId( post.getUserId() );

        return postResponseModel;
    }

    @Override
    public List<PostResponseModel> entityListToResponseModelList(List<Post> posts) {
        if ( posts == null ) {
            return null;
        }

        List<PostResponseModel> list = new ArrayList<PostResponseModel>( posts.size() );
        for ( Post post : posts ) {
            list.add( entityToResponseModel( post ) );
        }

        return list;
    }
}
