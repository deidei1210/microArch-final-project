package cn.edu.tongji.Emotions.controller;

import cn.edu.tongji.Emotions.interfaces.DiaryServiceClient;
import cn.edu.tongji.Emotions.interfaces.LoginServiceClient;
import cn.edu.tongji.Emotions.model.DiaryEmotion;
import cn.edu.tongji.Emotions.service.DiaryEmotionService;
import feign.FeignException;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diaryEmotions")
public class DiaryEmotionController {

    @Autowired
    private DiaryServiceClient diaryServiceClient;

    @Resource
    private final DiaryEmotionService diaryEmotionService;

    @Autowired
    public DiaryEmotionController(DiaryEmotionService diaryEmotionService) {
        this.diaryEmotionService = diaryEmotionService;
    }

    @GetMapping
    public ResponseEntity<Page<?>> getAllDiaryEmotions(Pageable pageable) {
        return ResponseEntity.ok(diaryEmotionService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDiaryEmotionById(@PathVariable String id) {
        return diaryEmotionService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createDiaryEmotion(@RequestBody DiaryEmotion diaryEmotion) {
        int diaryId = 0;
        try {
            diaryId = diaryEmotion.getDiaryId();
            ResponseEntity<?> response = diaryServiceClient.getDiaryById(diaryId);

            if (response.getStatusCode() != HttpStatus.OK) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Diary with ID " + diaryId + " not found");
            }

            return ResponseEntity.ok(diaryEmotionService.save(diaryEmotion));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid diary ID format");
        } catch (FeignException e) {
            // 处理Feign客户端异常
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Diary with ID " + diaryId + " not found");
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<DiaryEmotion> updateDiaryEmotion(@PathVariable String id, @RequestBody DiaryEmotion diaryEmotion) {
        if (!diaryEmotionService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        diaryEmotion.setId(id);
        return ResponseEntity.ok(diaryEmotionService.save(diaryEmotion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiaryEmotion(@PathVariable String id) {
        if (!diaryEmotionService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        diaryEmotionService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
