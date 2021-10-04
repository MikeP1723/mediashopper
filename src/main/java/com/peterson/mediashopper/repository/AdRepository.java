package com.peterson.mediashopper.repository;

import com.peterson.mediashopper.entity.AdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdRepository extends JpaRepository<AdEntity, Integer> {
    List<AdEntity> findAdEntitiesByTier(final String tier);
    List<AdEntity> findAdEntitiesByDay(final String day);
    List<AdEntity> findAdEntitiesByDayAndTier(final String day, final String tier);
    List<AdEntity> findAdEntitiesByAdIdIn(final List<Integer> adIds);
}
