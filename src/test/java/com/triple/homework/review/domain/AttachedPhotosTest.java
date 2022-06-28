package com.triple.homework.review.domain;

import com.triple.homework.fixture.AttachedPhotoFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class AttachedPhotosTest {

    @DisplayName("첨부파일 수정 - 성공 / 기존의 첨부파일을 제거하는 경우")
    @Test
    void update_success_delete_attached_photos() throws Exception {

        // given
        AttachedPhoto attachedPhoto1 = AttachedPhotoFixture.attachedPhoto();
        AttachedPhoto attachedPhoto2 = AttachedPhotoFixture.attachedPhoto();
        AttachedPhotos attachedPhotos = AttachedPhotos.builder()
                .attachedPhotos(new ArrayList<>(List.of(
                        attachedPhoto1,
                        attachedPhoto2
                )))
                .build();

        // when
        attachedPhotos.update(attachedPhoto1.getReview(), Collections.emptyList());

        // then
        assertThat(attachedPhotos.isEmpty()).isTrue();
    }

    @DisplayName("첨부파일 수정 - 성공 / 첨부파일을 추가하는 경우")
    @Test
    void update_success_add_attached_photos() throws Exception {

        // given
        AttachedPhoto attachedPhoto1 = AttachedPhotoFixture.attachedPhoto();
        AttachedPhotos attachedPhotos = AttachedPhotos.builder()
                .attachedPhotos(new ArrayList<>())
                .build();

        // when
        attachedPhotos.update(attachedPhoto1.getReview(), new ArrayList<>(List.of(UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString())));

        // then
        assertAll(
                () -> assertThat(attachedPhotos.isEmpty()).isFalse(),
                () -> assertThat(attachedPhotos.getAttachedPhotos().size()).isEqualTo(3)
        );
    }

    @DisplayName("첨부파일 수정 - 성공 / 두장에서 한장으로 변경하는 경우")
    @Test
    void update_success_modify_attached_photos() throws Exception {

        // given
        AttachedPhoto attachedPhoto1 = AttachedPhotoFixture.attachedPhoto();
        AttachedPhoto attachedPhoto2 = AttachedPhotoFixture.attachedPhoto();
        AttachedPhotos attachedPhotos = AttachedPhotos.builder()
                .attachedPhotos(new ArrayList<>(List.of(
                        attachedPhoto1,
                        attachedPhoto2
                )))
                .build();

        // when
        attachedPhotos.update(attachedPhoto1.getReview(), new ArrayList<>(List.of(UUID.randomUUID().toString())));

        // then
        assertAll(
                () -> assertThat(attachedPhotos.isEmpty()).isFalse(),
                () -> assertThat(attachedPhotos.getAttachedPhotos().size()).isEqualTo(1)
        );
    }
}
