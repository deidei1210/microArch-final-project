package cn.edu.tongji.Emotions.service;

import cn.edu.tongji.Emotions.model.MusicEmotion;
import cn.edu.tongji.Emotions.repository.MusicEmotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MusicEmotionService {

    public Page<MusicEmotion> findAll(Pageable pageable);

    public Optional<MusicEmotion> findById(String id);

    public MusicEmotion save(MusicEmotion musicEmotion);

    public void deleteById(String id) ;

    // Additional business logic methods can be added here
}