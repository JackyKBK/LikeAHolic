package org.champsoft.likeaholic.presentationLayer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.champsoft.likeaholic.dataAccessLayer.Likes;
import org.champsoft.likeaholic.dataAccessLayer.User;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseModel {



private String postId;
    private String id;
    private String title;
    private String content;
    private String imageUrl;
    private LocalDateTime createdAt;

    @JsonProperty("user_id")
    private String userId;
}
