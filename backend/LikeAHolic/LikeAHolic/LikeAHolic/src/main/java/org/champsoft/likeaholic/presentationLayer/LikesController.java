package org.champsoft.likeaholic.presentationLayer;

import org.champsoft.likeaholic.businessLogicLayer.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class LikesController {

    private final LikesService likesService;

    @Autowired
    public LikesController(LikesService likesService) {
        this.likesService = likesService;
    }


    @PostMapping("/likes")
    public ResponseEntity<String> addLike(@RequestBody LikesRequestModel likesRequestModel) {
        try {
            likesService.addLike(likesRequestModel.getUserId(), likesRequestModel.getPostId());
            return new ResponseEntity<>("Like added successfully", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/likes")
    public ResponseEntity<String> removeLike(@RequestBody LikesRequestModel likesRequestModel) {
        try {
            likesService.removeLike(likesRequestModel.getUserId(), likesRequestModel.getPostId());
            return new ResponseEntity<>("Like removed successfully", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/likes")
    public ResponseEntity<Boolean> isLiked(@RequestParam String userId, @RequestParam String postId) {
        boolean isLiked = likesService.isLiked(userId, postId);
        return new ResponseEntity<>(isLiked, HttpStatus.OK);
    }
}