package com.triple.homework.review.adapter.out;

import com.triple.homework.fixture.AttachedPhotoFixture;
import com.triple.homework.review.domain.AttachedPhoto;
import com.triple.homework.support.JpaRepositoryTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Import({AttachedPhotoRepository.class})
class AttachedPhotoRepositoryTest extends JpaRepositoryTest {

    @Autowired AttachedPhotoRepository attachedPhotoRepository;

    @DisplayName("리뷰 사진 저장 - 성공")
    @Test
    void review_saveAll_success() throws Exception {

        // given
        AttachedPhoto attachedPhoto1 = AttachedPhotoFixture.attachedPhoto();
        AttachedPhoto attachedPhoto2 = AttachedPhotoFixture.attachedPhoto();

        // when
        List<AttachedPhoto> attachedPhotos = attachedPhotoRepository.saveAll(List.of(attachedPhoto1, attachedPhoto2));
        flushAndClear();

        // then
        assertAll(
                () -> assertThat(entityManager.find(AttachedPhoto.class, attachedPhoto1.getId()).getId())
                        .isEqualTo(attachedPhoto1.getId()),
                () -> assertThat(entityManager.find(AttachedPhoto.class, attachedPhoto2.getId()).getId())
                        .isEqualTo(attachedPhoto2.getId())
        );
    }

    @DisplayName("리뷰 사진 저장 - 성공 / 빈 리스트 저장 시 아무것도 저장되지 않음")
    @Test
    void review_saveAll_success_empty_list() throws Exception {

        // when, then
        assertDoesNotThrow(() -> attachedPhotoRepository.saveAll(Collections.emptyList()));
        flushAndClear();
    }

    @DisplayName("리뷰 ID로 사진 찾기 - 성공")
    @Test
    void review_findById_success() throws Exception {

        // given
        AttachedPhoto attachedPhoto1 = AttachedPhotoFixture.attachedPhoto();
        entityManager.persist(attachedPhoto1);
        flushAndClear();

        // when, then
        assertThat(attachedPhotoRepository.findByReviewId(attachedPhoto1.getReviewId()).size())
                .isEqualTo(1);
    }
}
