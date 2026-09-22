package org.champsoft.likeaholic.dataMapperLayer;

import org.champsoft.likeaholic.dataAccessLayer.Post;
import org.champsoft.likeaholic.presentationLayer.PostResponseModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface PostResponseMapper {

    PostResponseModel entityToResponseModel(Post post);
    List<PostResponseModel> entityListToResponseModelList(List<Post> posts);
}
