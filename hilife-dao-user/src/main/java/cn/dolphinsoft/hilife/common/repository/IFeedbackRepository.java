package cn.dolphinsoft.hilife.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.dolphinsoft.hilife.common.domain.Feedback;

public interface IFeedbackRepository extends JpaRepository<Feedback, Integer> {

    @Query(value = "select * from FEEDBACK f order by f.REVIEWS DESC limit 5", nativeQuery = true)
    List<Feedback> findHotFeedback();

    @Query("update Feedback f set f.reviews = f.reviews + 1 where f.fbId = ?1")
    @Modifying
    void hasReviewed(Integer fbId);

}
