package org.champsoft.likeaholic.presentationLayer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.champsoft.likeaholic.dataAccessLayer.User;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestModel {

private String postId;
private String title;
private String content;
@JsonProperty("imageUrl")
private  String imageUrl;
private LocalDateTime createdAt;
@JsonProperty("userId")
private String userId;


}