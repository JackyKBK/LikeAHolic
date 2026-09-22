package org.champsoft.likeaholic.dataMapperLayer;

import org.champsoft.likeaholic.dataAccessLayer.Post;
import org.champsoft.likeaholic.presentationLayer.PostRequestModel;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface PostRequestMapper {


    Post requestModelToEntity(PostRequestModel postRequestModel);

    List<Post> requestModelListToEntity(List<PostRequestModel> postRequestModel);

}
