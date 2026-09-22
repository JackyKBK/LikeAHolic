package org.champsoft.likeaholic.presentationLayer;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LikesResponseModel {

        private String userId;
        private String userName;
        private String postId;
        private String createdAt;

}
