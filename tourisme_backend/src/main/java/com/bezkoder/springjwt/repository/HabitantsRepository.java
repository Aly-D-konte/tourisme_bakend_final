package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Habitants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitantsRepository extends JpaRepository<Habitants, Long> {
}
