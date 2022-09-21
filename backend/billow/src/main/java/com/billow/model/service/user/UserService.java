package com.billow.model.service.user;

import com.billow.domain.dto.addtion.RatingRequest;
import com.billow.domain.dto.addtion.RatingResponse;
import com.billow.domain.dto.user.AuthTokenResponse;
import com.billow.domain.dto.user.LoginResponse;
import com.billow.domain.entity.addition.Rating;
import com.billow.domain.entity.user.User;
import com.billow.exception.BadRequestException;
import com.billow.exception.NotFoundException;
import com.billow.exception.WrongFormException;
import com.billow.model.repository.addition.RatingRepository;
import com.billow.model.repository.user.UserRepository;
import com.billow.util.JwtUtil;
import com.billow.util.KakaoOAuth2;
import com.billow.util.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private static final String EMAIL_NOT_FOUND = "이메일 수집에 동의해주세요.";
    private static final String USER_NOT_FOUND = "해당 유저를 찾을 수 없습니다.";
    private static final String RATING_NOT_FOUND = "해당 평점을 찾을 수 없습니다.";
    private static final String BAD_REQUEST = "잘못된 요청입니다.";
    private static final String TOKEN_NOT_VALID = "토큰 정보가 올바르지 않습니다.";

    private final UserRepository userRepository;
    private final RatingRepository ratingRepository;
    private final KakaoOAuth2 kakaoOAuth2;

    public LoginResponse kakaoLogin(String code) throws ParseException {
        User kakaoUser = kakaoOAuth2.getUserInfo(code);
        log.info(kakaoUser.toString());

        if (kakaoUser.getEmail() == null) {
            throw new NotFoundException(EMAIL_NOT_FOUND);
        } else {
            User user = userRepository.findByEmail(kakaoUser.getEmail());
            if (user == null) {
                User.builder()
                        .name(kakaoUser.getNickName())
                        .email(kakaoUser.getEmail())
                        .build();
                userRepository.save(user);
            }
            String authToken = JwtUtil.createAuthToken(user.getId(), user.getEmail(), user.getName());
            String refreshToken = JwtUtil.createRefreshToken();
            saveRefreshToken(user.getEmail(), refreshToken);

            return LoginResponse.builder()
                    .email(user.getEmail())
                    .name(user.getName())
                    .nickName(user.getNickName())
                    .authToken(authToken)
                    .refreshToken(refreshToken)
                    .build();
        }
    }

    public AuthTokenResponse refresh(String email, String refreshToken) {
        User user = userRepository.findByEmail(email);
        // 데이터베이스에 있는 리프레시 토큰과 같은지 판단
        if (!user.getRefreshToken().equals(refreshToken)) {
            throw new WrongFormException(TOKEN_NOT_VALID);
        }
        return AuthTokenResponse.builder()
                .AuthToken(JwtUtil.createAuthToken(user.getId(), user.getEmail(), user.getName()))
                .build();
    }

    public List<RatingResponse> selectRating(Long userId) {
        return ratingRepository.findByUser_Id(userId)
                .stream()
                .map(rating -> RatingResponse.builder()
                        .title(rating.getProgram().getTitle())
                        .score(rating.getScore())
                        .build())
                .collect(Collectors.toList());
    }

    public Message updateRating(Long userId, Long ratingId, RatingRequest ratingRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        Rating rating = ratingRepository.findById(ratingId)
                .orElseThrow(() -> new NotFoundException(RATING_NOT_FOUND));
        if (!user.equals(rating.getUser())) {
            throw new BadRequestException(BAD_REQUEST);
        }
        rating.updateRating(ratingRequest.getScore());
        ratingRepository.save(rating);

        /*
         * 질문 !
         * 연관관계 매핑에서 cascade
         */
        return new Message("회원님의 평점내역 수정에 성공하였습니다.");
    }

    public Message deleteRating(Long userId, Long ratingId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        Rating rating = ratingRepository.findById(ratingId)
                .orElseThrow(() -> new NotFoundException(RATING_NOT_FOUND));
        if (!user.equals(rating.getUser())) {
            throw new BadRequestException(BAD_REQUEST);
        }
        rating.getProgram().updateAverageRatingByDelete(rating.getScore());
        ratingRepository.delete(rating);

        return new Message("회원님의 평점내역 삭제에 성공하였습니다.");
    }

    private void saveRefreshToken(String email, String refreshToken) {
        User user = userRepository.findByEmail(email);
        user.saveRefreshToken(refreshToken);
        userRepository.save(user);
    }
}
