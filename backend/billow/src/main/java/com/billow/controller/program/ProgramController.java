package com.billow.controller.program;

import com.billow.domain.dto.addtion.RatingRequest;
import com.billow.domain.dto.program.ProgramResponse;
import com.billow.model.service.program.ProgramService;
import com.billow.util.JwtUtil;
import com.billow.util.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/program")
public class ProgramController {

    private final ProgramService programService;

    @GetMapping
    public ResponseEntity<Object> searchProgram(@RequestParam(value = "word") String word) {
        log.info("프로그램 검색 API 호출");
        List<ProgramResponse> responses = programService.searchProgram(word);
        log.info("프로그램 검색 성공");
        return ResponseEntity.ok()
                .body(responses);
    }

    @GetMapping("/{programId}")
    public ResponseEntity<Object> selectProgram(@PathVariable("programId") Long programId) {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }

    @GetMapping("/random")
    public ResponseEntity<Object> randomProgram(@RequestHeader("Auth-access") String token) {
        log.info("사용자 초기 데이터 수집용 랜덤 프로그램 출력 API 호출");
        List<ProgramResponse> response = programService.randomProgram();
        log.info("랜덤 프로그램 출력 성공");
        return ResponseEntity.ok()
                .body(response);
    }

    @PostMapping("/{programId}")
    public ResponseEntity<Object> postProgramRating(@RequestHeader("Auth-access") String token, @PathVariable("programId") Long programId, @RequestBody RatingRequest ratingRequest) {
        log.info("프로그램 평점 등록 API 호출");
        Message response = programService.postProgramRating(JwtUtil.getUserId(token), programId, ratingRequest);
        log.info("프로그램 평점 등록 성공");
        return ResponseEntity.ok()
                .body(response);
    }
}
