package cn.edu.tongji.Emotions.service;

import cn.edu.tongji.Emotions.model.MusicEmotion;
import cn.edu.tongji.Emotions.repository.MusicEmotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicEmotionService {

    private final MusicEmotionRepository musicEmotionRepository;

    @Autowired
    public MusicEmotionService(MusicEmotionRepository musicEmotionRepository) {
        this.musicEmotionRepository = musicEmotionRepository;
    }

    public List<MusicEmotion> findAll() {
        return musicEmotionRepository.findAll();
    }

    public Optional<MusicEmotion> findById(String id) {
        return musicEmotionRepository.findById(id);
    }

    public MusicEmotion save(MusicEmotion musicEmotion) {
        return musicEmotionRepository.save(musicEmotion);
    }

    public void deleteById(String id) {
        musicEmotionRepository.deleteById(id);
    }

    // Additional business logic methods can be added here
}