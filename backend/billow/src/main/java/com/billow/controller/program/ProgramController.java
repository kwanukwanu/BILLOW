package com.billow.controller.program;

import com.billow.domain.dto.addtion.RatingRequest;
import com.billow.util.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/program")
public class ProgramController {

    @GetMapping
    public ResponseEntity<Object> searchProgram(@RequestParam(value = "word") String word) {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }

    @GetMapping("/{programId}")
    public ResponseEntity<Object> selectProgram(@PathVariable("programId") Long programId) {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }

    @GetMapping("/random")
    public ResponseEntity<Object> randomProgram(){
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }

    @PostMapping("/{programId}")
    public ResponseEntity<Object> postProgramRating(@PathVariable("programId") Long programId, @RequestBody RatingRequest ratingRequest) {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }
}