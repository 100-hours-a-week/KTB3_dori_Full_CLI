package com.example.week7.service.like;

import com.example.week7.common.exception.custom.DuplicatedException;
import com.example.week7.common.exception.custom.ResourceNotFoundException;
import com.example.week7.common.exception.custom.UnauthorizedException;
import com.example.week7.repository.post.PostRepository;
import com.example.week7.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import static com.example.week7.common.exception.ErrorMessage.*;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeServiceImpl implements LikeService {

    private final ConcurrentHashMap<Long, Set<String>> likeMap = new ConcurrentHashMap<>();
    private final UserRepository userRepository;
    private final PostRepository postRepository;


    @Override
    @CachePut(value = "likes", key = "#postId")
    public long likePost(Long postId, String email) {
        userRepository.findByEmail(email).orElseThrow(
                () -> new UnauthorizedException(UNAUTHORIZED)
        );

        postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException(RESOURCE_NOT_FOUND)
        );

        likeMap.putIfAbsent(postId, new CopyOnWriteArraySet<>());
        Set<String> likedUsers = likeMap.get(postId);
        if (likedUsers.contains(email)) {
            throw new DuplicatedException(DUPLICATED_LIKES);
        }

        likedUsers.add(email);
        return likedUsers.size();
    }

    @Override
    @CachePut(value = "likes", key = "#postId")
    public long unlikePost(Long postId, String email) {
        userRepository.findByEmail(email).orElseThrow(
                () -> new UnauthorizedException(UNAUTHORIZED)
        );

        postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException(RESOURCE_NOT_FOUND)
        );
        Set<String> likedUsers = likeMap.get(postId);
        if (likedUsers != null) {
            likedUsers.remove(email);
        }
        return likedUsers != null ? likedUsers.size() : 0L;
    }

    @Override
    @Cacheable(value = "likes", key = "#postId")
    public long getLikeCount(Long postId) {
        postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException(RESOURCE_NOT_FOUND)
        );

        Set<String> likedUsers = likeMap.get(postId);
        return likedUsers != null ? likedUsers.size() : 0L;
    }

    @Override
    @CacheEvict(value = "likes", key = "#postId")
    public void clearLikes(Long postId) {
        likeMap.remove(postId);
    }
}
